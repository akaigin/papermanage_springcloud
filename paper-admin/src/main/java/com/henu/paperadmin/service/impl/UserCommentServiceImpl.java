package com.henu.paperadmin.service.impl;

import com.henu.paperadmin.dao.UserCommentDao;
import com.henu.paperadmin.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserCommentServiceImpl implements UserCommentService {
    @Autowired
    UserCommentDao userCommentMapper;

    @Override
    public int isLikeComment(Long userId, Long commentId){
        return userCommentMapper.isLikeComment(userId,commentId);
    }
}
