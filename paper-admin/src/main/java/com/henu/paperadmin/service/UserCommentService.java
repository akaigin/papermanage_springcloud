package com.henu.paperadmin.service;

import com.henu.paperadmin.domain.CommentDO;
import com.henu.paperadmin.dto.CommentDTO;

import java.util.List;
import java.util.Map;

public interface UserCommentService {
    int isLikeComment(Long userId, Long commentId);
}