package com.henu.paperadmin.service.impl;

import com.henu.paperadmin.dao.CommentDao;
import com.henu.paperadmin.dao.RoleDao;
import com.henu.paperadmin.dao.UserCommentDao;
import com.henu.paperadmin.dao.UserRoleDao;
import com.henu.paperadmin.domain.CommentDO;
import com.henu.paperadmin.domain.UserCommentIslikeDO;
import com.henu.paperadmin.dto.CommentDTO;
import com.henu.paperadmin.dto.UserCommentDTO;
import com.henu.paperadmin.service.CommentService;
import com.henu.paperadmin.utils.CommentDOConvert;
import com.henu.paperadmin.utils.SecuityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    UserCommentDao userCommentMapper;

    @Override
    public List<CommentDO> list( CommentDO commentDO){
        List<CommentDO> commentDOS=commentMapper.list(commentDO);
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
    public int updateLikeNumPlus(List<UserCommentDTO> userCommentDTOS) {
        List<UserCommentIslikeDO> likeList=userCommentMapper.getLikeList(SecuityUtils.getCurrentUser().getId());
        Map<String,Boolean> likeMap = new HashMap<>();
        Map<String,Long> IdMap = new HashMap<>();
        for(UserCommentIslikeDO userCommentIslikeDO:likeList){
            likeMap.put(userCommentIslikeDO.getUserId().toString()+"like"+userCommentIslikeDO.getCommentId().toString(),true);
            IdMap.put(userCommentIslikeDO.getUserId().toString()+"like"+userCommentIslikeDO.getCommentId().toString(),userCommentIslikeDO.getId());
        }
        List<UserCommentIslikeDO> userCommentIslikeDOS=new ArrayList<>();
        List<Long> userCommentIsNotlikeIdS=new ArrayList<>();
        List<CommentDO> commentLikePlusDOS=new ArrayList<>();
        List<CommentDO> commentLikeMinusDOS=new ArrayList<>();
        for(UserCommentDTO userCommentDTO:userCommentDTOS){
            if(likeMap.get(userCommentDTO.getUserId().toString()+"like"+userCommentDTO.getCommentId().toString())==null){
                if(userCommentDTO.getIsLike()){
                    UserCommentIslikeDO userCommentIslikeDO=new UserCommentIslikeDO();
                    userCommentIslikeDO.setUserId(userCommentDTO.getUserId());
                    userCommentIslikeDO.setCommentId(userCommentDTO.getCommentId());
                    userCommentIslikeDOS.add(userCommentIslikeDO);
                    CommentDO commentDO=new CommentDO();
                    commentDO.setCommentId(userCommentDTO.getCommentId());
                    commentLikePlusDOS.add(commentDO);
                }
            }else if(!likeMap.get(userCommentDTO.getUserId().toString()+"like"+userCommentDTO.getCommentId().toString()).equals(userCommentDTO.getIsLike())){
                userCommentIsNotlikeIdS.add(IdMap.get(userCommentDTO.getUserId().toString()+"like"+userCommentDTO.getCommentId().toString()));
                CommentDO commentDO=new CommentDO();
                commentDO.setCommentId(userCommentDTO.getCommentId());
                commentLikeMinusDOS.add(commentDO);
            }
        }
        if(userCommentIslikeDOS!=null&&!userCommentIslikeDOS.isEmpty()){
            userCommentMapper.addIsLike(userCommentIslikeDOS);
        }
        if(userCommentIsNotlikeIdS!=null&&!userCommentIsNotlikeIdS.isEmpty()) {
            userCommentMapper.deleteIsNotLike(userCommentIsNotlikeIdS);
        }
        if(commentLikePlusDOS!=null&&!commentLikePlusDOS.isEmpty()) {
            commentMapper.updateLikeNumPlus(commentLikePlusDOS);
        }
        if(commentLikeMinusDOS!=null&&!commentLikeMinusDOS.isEmpty()) {
            commentMapper.updateLikeNumMinus(commentLikeMinusDOS);
        }
        return 1;
    }
}
