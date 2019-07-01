package com.henu.paperadmin.service;

import com.henu.paperadmin.domain.CommentDO;
import com.henu.paperadmin.domain.UserCommentIslikeDO;
import com.henu.paperadmin.dto.CommentDTO;
import com.henu.paperadmin.dto.UserCommentDTO;

import java.util.List;
import java.util.Map;

public interface CommentService {
    List<CommentDO> list(CommentDO commentDO);

    int count(Map<String, Object> map);

    int save(CommentDTO user);

    int remove(Long userId);

    int updateLikeNumPlus(List<UserCommentDTO> userCommentDTOS);
}