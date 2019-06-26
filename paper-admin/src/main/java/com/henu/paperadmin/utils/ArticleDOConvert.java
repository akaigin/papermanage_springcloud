package com.henu.paperadmin.utils;
import java.util.List;
import java.lang.Long;
import java.util.Date;
import com.henu.paperadmin.dto.ArticleDTO;
import com.henu.paperadmin.domain.ArticleDO;
import java.lang.String;
import java.util.ArrayList;
/**
create by tiger's tool
*/
public class ArticleDOConvert {
public static ArticleDO articleDTOToArticleDO(ArticleDTO articleDTO){
if(articleDTO == null){
return null;
}
ArticleDO articleDO = new ArticleDO();
articleDO.setArticleId(articleDTO.getArticleId());
articleDO.setTitle(articleDTO.getTitle());
articleDO.setContent(articleDTO.getContent());
articleDO.setTag(articleDTO.getTag());
articleDO.setCreateTime(articleDTO.getCreateTime());
articleDO.setModifyTime(articleDTO.getModifyTime());
articleDO.setClick(articleDTO.getClick());
articleDO.setIsSecret(articleDTO.getIsSecret());
articleDO.setCreateUser(articleDTO.getCreateUser());
return articleDO;
}
public static ArticleDTO articleDOToArticleDTO(ArticleDO articleDO){
if(articleDO == null){
return null;
}
ArticleDTO articleDTO = new ArticleDTO();
articleDTO.setArticleId(articleDO.getArticleId());
articleDTO.setTitle(articleDO.getTitle());
articleDTO.setContent(articleDO.getContent());
articleDTO.setTag(articleDO.getTag());
articleDTO.setCreateTime(articleDO.getCreateTime());
articleDTO.setModifyTime(articleDO.getModifyTime());
articleDTO.setClick(articleDO.getClick());
articleDTO.setIsSecret(articleDO.getIsSecret());
articleDTO.setCreateUser(articleDO.getCreateUser());
return articleDTO;
}
public static List<ArticleDTO>articleDOListToArticleDTOList(List<ArticleDO> articleDOList){
if(articleDOList == null){
return null;
}
List<ArticleDTO>articleDTOList = new ArrayList();
for(ArticleDO articleDO : articleDOList){
articleDTOList.add(articleDOToArticleDTO(articleDO));
}
return articleDTOList;
}
public static List<ArticleDO>articleDTOListToArticleDOList(List<ArticleDTO> articleDTOList){
if(articleDTOList == null){
return null;
}
List<ArticleDO>articleDOList = new ArrayList();
for(ArticleDTO articleDTO : articleDTOList){
articleDOList.add(articleDTOToArticleDO(articleDTO));
}
return articleDOList;
}
}
