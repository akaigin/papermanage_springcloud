package com.henu.papercomment.service;

import com.henu.papercomment.domain.CommentDO;
import com.henu.papercomment.dto.CommentDTO;

import java.util.List;
import java.util.Map;

public interface CommentService {
    List<CommentDTO> list(Map<String, Object> query, CommentDO commentDO);

    int count(Map<String, Object> map);

    int save(CommentDTO user);

    int update(CommentDTO user);

    int remove(Long userId);

    int updateClickPlus(Long commentId);
}