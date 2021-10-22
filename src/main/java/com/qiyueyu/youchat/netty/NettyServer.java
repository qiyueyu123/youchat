package com.qiyueyu.youchat.netty;

import com.qiyueyu.youchat.netty.common.CommonConstants;
import com.qiyueyu.youchat.netty.handler.ChatHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * netty服务器
 */

@Component
@Slf4j
@Order
public class NettyServer implements CommandLineRunner {

    private ChannelFuture channelFuture;

    private EventLoopGroup bossGroup = null;

    private EventLoopGroup workerGroup = null;

    private EventLoopGroup business = null;

    @Value("${youchat.netty-server.threads}")
    private int threads;

    @Value("${youchat.netty-server.idle}")
    private int idleTime;

    @Value("${youchat.httpObjectAggregator.maxContentLength}")
    private int httpObjectAggregatorMaxContentLength;

    @Value("${youchat.WebSocketServerProtocolHandler.websocketPath}")
    private String websocketPath;

    @Autowired
    private ChatHandler chatHandler;

    public void start() {
        bossGroup = new NioEventLoopGroup(new DefaultThreadFactory("boss"));
        workerGroup = new NioEventLoopGroup(new DefaultThreadFactory("work"));
        business = new DefaultEventLoopGroup(threads, new DefaultThreadFactory("business"));

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_REUSEADDR, true);

        try {
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(business, "idleCheck", new IdleStateHandler(0, 0, idleTime));
                    pipeline.addLast(business, new LoggingHandler());
                    pipeline.addLast(business, "httpServerCodec", new HttpServerCodec());
                    pipeline.addLast(business,"chunkedWriteHandler", new ChunkedWriteHandler());
                    pipeline.addLast(business, "httpObjectAggregator", new HttpObjectAggregator(httpObjectAggregatorMaxContentLength));
                    pipeline.addLast(business, "webSocketServerProtocolHandler", new WebSocketServerProtocolHandler(websocketPath));
                    pipeline.addLast(business, "chatHandler",chatHandler);
                }
            });
            channelFuture = serverBootstrap.bind(CommonConstants.NETTY_SERVER_PORT).sync();
            log.info("端口启动成功：{}", CommonConstants.NETTY_SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            business.shutdownGracefully();
        }
    }

    @Async
    @Override
    public void run(String... args) throws Exception {
        start();
    }
}
