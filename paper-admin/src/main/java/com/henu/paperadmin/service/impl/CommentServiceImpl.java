package com.henu.paperadmin.service.impl;

import com.henu.paperadmin.dao.CommentDao;
import com.henu.paperadmin.dao.RoleDao;
import com.henu.paperadmin.dao.UserRoleDao;
import com.henu.paperadmin.domain.CommentDO;
import com.henu.paperadmin.dto.CommentDTO;
import com.henu.paperadmin.service.CommentService;
import com.henu.paperadmin.utils.CommentDOConvert;
import com.henu.paperadmin.utils.SecuityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentMapper;

    @Autowired
    RoleDao roleMapper;

    @Autowired
    UserRoleDao userRoleMapper;
    @Override
    public List<CommentDO> list(Map<String,Object> query, CommentDO commentDO){
        List<CommentDO> commentDOS=commentMapper.list(query,commentDO);
        return commentDOS;
    }

    @Override
    public int count(Map<String,Object> map){
        return commentMapper.count(map);
    }
    @Override
    public int save(CommentDTO comment) {
        CommentDO commentDO= CommentDOConvert.commentDTOToCommentDO(comment);
        int count = commentMapper.save(commentDO);
        return count;
    }

    @Override
    //@CacheEvict(allEntries = true)
    public int remove(Long commentId) {
        return commentMapper.remove(commentId);
    }

    @Override
    public int updateLikeNumPlus(Long commentId) {
        return commentMapper.updateLikeNumPlus(commentId);
    }
}
