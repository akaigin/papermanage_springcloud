package com.henu.paperadmin.dto;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.henu.paperadmin.domain.ArticleDO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-06-26
 */
public class ArticleDTO extends ArticleDO {

    private static final long serialVersionUID = 1L;

	/**
	 * 对应角色
	 */
	List<Long> roleId;
	/**
	 * 对应角色
	 */
	String roleNames;

	public List<Long> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
}
