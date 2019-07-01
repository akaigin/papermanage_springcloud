package com.henu.paperadmin.dao;

import com.henu.paperadmin.domain.CommentDO;
import com.henu.paperadmin.domain.UserCommentIslikeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserCommentDao {
    int isLikeComment(Long userId, Long commentId);

    List<UserCommentIslikeDO> getLikeList(Long userId);

    int addIsLike(List<UserCommentIslikeDO> commentIslikeDOS);

    int deleteIsNotLike(List<Long> commentIslikeIdS);
}
