package com.henu.paperadmin.utils;
import java.util.List;
import java.lang.Long;
import java.util.Date;
import com.henu.paperadmin.domain.CommentDO;
import java.lang.String;
import com.henu.paperadmin.dto.CommentDTO;
import java.util.ArrayList;
/**
create by tiger's tool
*/
public class CommentDOConvert {
public static CommentDO commentDTOToCommentDO(CommentDTO commentDTO){
if(commentDTO == null){
return null;
}
CommentDO commentDO = new CommentDO();
commentDO.setCommentId(commentDTO.getCommentId());
commentDO.setParentId(commentDTO.getParentId());
commentDO.setContent(commentDTO.getContent());
commentDO.setAuthor(commentDTO.getAuthor());
commentDO.setLikeNum(commentDTO.getLikeNum());
commentDO.setCreateTime(commentDTO.getCreateTime());
commentDO.setArticleId(commentDTO.getArticleId());
commentDO.setToWho(commentDTO.getToWho());
return commentDO;
}
public static CommentDTO commentDOToCommentDTO(CommentDO commentDO){
if(commentDO == null){
return null;
}
CommentDTO commentDTO = new CommentDTO();
commentDTO.setCommentId(commentDO.getCommentId());
commentDTO.setParentId(commentDO.getParentId());
commentDTO.setContent(commentDO.getContent());
commentDTO.setAuthor(commentDO.getAuthor());
commentDTO.setLikeNum(commentDO.getLikeNum());
commentDTO.setCreateTime(commentDO.getCreateTime());
commentDTO.setArticleId(commentDO.getArticleId());
commentDTO.setToWho(commentDO.getToWho());
return commentDTO;
}
public static List<CommentDTO>commentDOListToCommentDTOList(List<CommentDO> commentDOList){
if(commentDOList == null){
return null;
}
List<CommentDTO>commentDTOList = new ArrayList();
for(CommentDO commentDO : commentDOList){
commentDTOList.add(commentDOToCommentDTO(commentDO));
}
return commentDTOList;
}
public static List<CommentDO>commentDTOListToCommentDOList(List<CommentDTO> commentDTOList){
if(commentDTOList == null){
return null;
}
List<CommentDO>commentDOList = new ArrayList();
for(CommentDTO commentDTO : commentDTOList){
commentDOList.add(commentDTOToCommentDO(commentDTO));
}
return commentDOList;
}
}
