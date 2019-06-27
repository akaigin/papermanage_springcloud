package com.henu.papercomment.service.impl;

import com.henu.papercomment.dao.CommentDao;
import com.henu.papercomment.domain.CommentDO;
import com.henu.papercomment.domain.CommentRoleDO;
import com.henu.papercomment.dto.CommentDTO;
import com.henu.papercomment.service.CommentService;
import com.henu.papercomment.utils.CommentDOConvert;
import com.henu.papercomment.utils.SecuityUtils;
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
    CommentRoleDao commentRoleMapper;

    @Autowired
    RoleDao roleMapper;

    @Autowired
    UserRoleDao userRoleMapper;
    @Override
    public List<CommentDTO> list(Map<String,Object> query, CommentDO commentDO){
        List<CommentDO> commentDOS=commentMapper.list(query,commentDO);
        List<CommentDTO> commentDTOS= new ArrayList<>();
        for(CommentDO commentDOResponse:commentDOS){
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setCommentId(commentDOResponse.getCommentId());
            commentDTO.setTitle(commentDOResponse.getTitle());
            commentDTO.setContent(commentDOResponse.getContent());
            commentDTO.setTag(commentDOResponse.getTag());
            commentDTO.setCreateTime(commentDOResponse.getCreateTime());
            commentDTO.setModifyTime(commentDOResponse.getModifyTime());
            commentDTO.setCreateUser(commentDOResponse.getCreateUser());
            /*List<Long> roleIds=commentRoleMapper.getRoleIdsByCommentId(commentDOResponse.getCommentId());
            String roleNames="";
            List<String> roleNamesResponse=roleMapper.getRoleNamesByRoleIds(roleIds);
            for(String roleNameResponse:roleNamesResponse){
                roleNames+=roleNameResponse+" ";
            }
            commentDTO.setRoleNames(roleNames);*/
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }

    @Override
    public int count(Map<String,Object> map){
        return commentMapper.count(map);
    }
    @Override
    public int save(CommentDTO comment) {
        CommentDO commentDO= CommentDOConvert.commentDTOToCommentDO(comment);
        int count = commentMapper.save(commentDO);
        Long commentId = commentDO.getCommentId();
        List<Long> roles = new ArrayList<>();
        roles.add(userRoleMapper.getRoleIdByUserId(SecuityUtils.getCurrentUser().getId()));
        List<CommentRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            CommentRoleDO pr = new CommentRoleDO();
            pr.setCommentId(commentId);
            pr.setRoleId(roleId);
            list.add(pr);
        }
        if (list.size() > 0) {
            commentRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    //@CacheEvict(allEntries = true,beforeInvocation =true)
    public int update(CommentDTO comment) {
        int r = commentMapper.updateById(CommentDOConvert.commentDTOToCommentDO(comment));
        return r;
    }

    @Override
    //@CacheEvict(allEntries = true)
    public int remove(Long commentId) {
        commentRoleMapper.remove(commentId);
        return commentMapper.remove(commentId);
    }

    @Override
    public int updateClickPlus(Long commentId) {
        return commentMapper.updateClickPlus(commentId);
    }
}
