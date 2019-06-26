package com.henu.paperadmin.service;

import com.henu.paperadmin.domain.ArticleDO;
import com.henu.paperadmin.dto.ArticleDTO;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<ArticleDTO> list(Map<String, Object> query, ArticleDO articleDO);

    int count(Map<String, Object> map);

    int save(ArticleDTO user);

    int update(ArticleDTO user);

    int remove(Long userId);

    int updateClickPlus(Long articleId);
}