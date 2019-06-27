package com.henu.paperadmin.dao;

import com.henu.paperadmin.domain.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserCommentDao {
    int isLikeComment(Long userId, Long commentId);
}
