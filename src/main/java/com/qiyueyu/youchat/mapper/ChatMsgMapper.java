package com.qiyueyu.youchat.mapper;

import com.qiyueyu.youchat.entity.ChatMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qiyueyu
 * @since 2021-10-25
 */
@Mapper
@Repository
public interface ChatMsgMapper extends BaseMapper<ChatMsg> {

    /**
     * 批量更新消息签收状态
     * @param msgIdCheckList mgsIdList
     */
    void batchUpdateSignFlagByMsgIds(List<String> msgIdCheckList);
}
