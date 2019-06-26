package com.henu.paperadmin.dao;

import com.henu.paperadmin.domain.ArticleRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleRoleDao {
    int batchSave(List<ArticleRoleDO> list);

    int batchUpdate(List<ArticleRoleDO> list);

    List<Long> getRoleIdsByArticleId(Long articleId);

    int remove(Long articleId);
}
