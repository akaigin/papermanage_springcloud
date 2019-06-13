package com.henu.papermanage.common.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-06-12
 */
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

	@TableId(value="menu_id", type= IdType.AUTO)
	private Long menuId;
    /**
     * 父菜单ID，一级菜单为0
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 菜单名称
     */
	private String name;
    /**
     * 菜单URL
     */
	private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
	private String perms;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
	private Integer type;
    /**
     * 菜单图标
     */
	private String icon;
    /**
     * 排序
     */
	@TableField("order_num")
	private Integer orderNum;
    /**
     * 创建时间
     */
	@TableField("gmt_create")
	private Date gmtCreate;
	private String component;
    /**
     * 修改时间
     */
	@TableField("gmt_modified")
	private Date gmtModified;
	private String redirect;


	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	@Override
	protected Serializable pkVal() {
		return this.menuId;
	}

	@Override
	public String toString() {
		return "SysMenu{" +
			", menuId=" + menuId +
			", parentId=" + parentId +
			", name=" + name +
			", url=" + url +
			", perms=" + perms +
			", type=" + type +
			", icon=" + icon +
			", orderNum=" + orderNum +
			", gmtCreate=" + gmtCreate +
			", component=" + component +
			", gmtModified=" + gmtModified +
			", redirect=" + redirect +
			"}";
	}
}
