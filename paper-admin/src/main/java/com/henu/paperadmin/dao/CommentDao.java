package com.henu.paperadmin.dao;

import com.henu.paperadmin.domain.CommentDO;
import com.henu.paperadmin.domain.UserCommentIslikeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentDao {
    List<CommentDO> list(CommentDO commentDO);

    int count(Map<String, Object> map);

    int save(CommentDO commentDO);

    int remove(Long commentId);

    int updateLikeNumPlus(List<CommentDO> commentDOs);

    int updateLikeNumMinus(List<CommentDO> commentDOs);
}
