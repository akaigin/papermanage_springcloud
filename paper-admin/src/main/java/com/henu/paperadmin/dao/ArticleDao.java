package com.henu.paperadmin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.henu.paperadmin.domain.ArticleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleDao {
    List<ArticleDO> list(@Param("query") Map<String, Object> map, @Param("article") ArticleDO articleDO);

    int count(Map<String, Object> map);

    int save(ArticleDO articleDO);

    int updateById(ArticleDO articleDO);

    int remove(Long articleId);

    int getContentById(Long articleId);

    int updateClickPlus(Long articleId);
}
