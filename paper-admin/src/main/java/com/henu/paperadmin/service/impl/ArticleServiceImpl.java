package com.henu.paperadmin.service.impl;

import com.henu.paperadmin.dao.ArticleDao;
import com.henu.paperadmin.dao.ArticleRoleDao;
import com.henu.paperadmin.dao.RoleDao;
import com.henu.paperadmin.dao.UserRoleDao;
import com.henu.paperadmin.domain.ArticleDO;
import com.henu.paperadmin.domain.ArticleRoleDO;
import com.henu.paperadmin.dto.ArticleDTO;
import com.henu.paperadmin.service.ArticleService;
import com.henu.paperadmin.utils.ArticleDOConvert;
import com.henu.paperadmin.utils.SecuityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleMapper;

    @Autowired
    ArticleRoleDao articleRoleMapper;

    @Autowired
    RoleDao roleMapper;

    @Autowired
    UserRoleDao userRoleMapper;
    @Override
    public List<ArticleDTO> list(Map<String,Object> query, ArticleDO articleDO){
        List<ArticleDO> articleDOS=articleMapper.list(query,articleDO);
        List<ArticleDTO> articleDTOS= new ArrayList<>();
        for(ArticleDO articleDOResponse:articleDOS){
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setArticleId(articleDOResponse.getArticleId());
            articleDTO.setTitle(articleDOResponse.getTitle());
            articleDTO.setContent(articleDOResponse.getContent());
            articleDTO.setTag(articleDOResponse.getTag());
            articleDTO.setClick(articleDOResponse.getClick());
            articleDTO.setCreateTime(articleDOResponse.getCreateTime());
            articleDTO.setModifyTime(articleDOResponse.getModifyTime());
            articleDTO.setCreateUser(articleDOResponse.getCreateUser());
            /*List<Long> roleIds=articleRoleMapper.getRoleIdsByArticleId(articleDOResponse.getArticleId());
            String roleNames="";
            List<String> roleNamesResponse=roleMapper.getRoleNamesByRoleIds(roleIds);
            for(String roleNameResponse:roleNamesResponse){
                roleNames+=roleNameResponse+" ";
            }
            articleDTO.setRoleNames(roleNames);*/
            articleDTOS.add(articleDTO);
        }
        return articleDTOS;
    }

    @Override
    public int count(Map<String,Object> map){
        return articleMapper.count(map);
    }
    @Override
    public int save(ArticleDTO article) {
        ArticleDO articleDO= ArticleDOConvert.articleDTOToArticleDO(article);
        int count = articleMapper.save(articleDO);
        Long articleId = articleDO.getArticleId();
        List<Long> roles = new ArrayList<>();
        roles.add(userRoleMapper.getRoleIdByUserId(SecuityUtils.getCurrentUser().getId()));
        List<ArticleRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            ArticleRoleDO pr = new ArticleRoleDO();
            pr.setArticleId(articleId);
            pr.setRoleId(roleId);
            list.add(pr);
        }
        if (list.size() > 0) {
            articleRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    //@CacheEvict(allEntries = true,beforeInvocation =true)
    public int update(ArticleDTO article) {
        int r = articleMapper.updateById(ArticleDOConvert.articleDTOToArticleDO(article));
        return r;
    }

    @Override
    //@CacheEvict(allEntries = true)
    public int remove(Long articleId) {
        articleRoleMapper.remove(articleId);
        return articleMapper.remove(articleId);
    }

    @Override
    public int updateClickPlus(Long articleId) {
        return articleMapper.updateClickPlus(articleId);
    }
}
