package com.henu.paperadmin.dao;

import com.henu.paperadmin.domain.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentDao {
    List<CommentDO> list(@Param("query") Map<String, Object> map, @Param("comment") CommentDO commentDO);

    int count(Map<String, Object> map);

    int save(CommentDO commentDO);

    int remove(Long commentId);

    int updateLikeNumPlus(Long commentId);
}
