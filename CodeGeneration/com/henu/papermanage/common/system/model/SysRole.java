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
 * 角色
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-06-12
 */
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

	@TableId(value="role_id", type= IdType.AUTO)
	private Long roleId;
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 角色标识
     */
	@TableField("role_sign")
	private String roleSign;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建用户id
     */
	@TableField("user_id_create")
	private Long userIdCreate;
    /**
     * 创建时间
     */
	@TableField("gmt_create")
	private Date gmtCreate;
    /**
     * 创建时间
     */
	@TableField("gmt_modified")
	private Date gmtModified;


	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleSign() {
		return roleSign;
	}

	public void setRoleSign(String roleSign) {
		this.roleSign = roleSign;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(Long userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

	@Override
	public String toString() {
		return "SysRole{" +
			", roleId=" + roleId +
			", roleName=" + roleName +
			", roleSign=" + roleSign +
			", remark=" + remark +
			", userIdCreate=" + userIdCreate +
			", gmtCreate=" + gmtCreate +
			", gmtModified=" + gmtModified +
			"}";
	}
}
