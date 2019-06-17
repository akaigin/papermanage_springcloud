package com.henu.papermanage.common.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-06-16
 */
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

	@TableId(value="dept_id", type= IdType.AUTO)
	private Long deptId;
    /**
     * 上级部门ID，一级部门为0
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 部门名称
     */
	private String name;
    /**
     * 排序
     */
	@TableField("order_num")
	private Integer orderNum;
    /**
     * 是否删除  -1：已删除  0：正常
     */
	@TableField("del_flag")
	private Integer delFlag;


	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
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

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	protected Serializable pkVal() {
		return this.deptId;
	}

	@Override
	public String toString() {
		return "SysDept{" +
			", deptId=" + deptId +
			", parentId=" + parentId +
			", name=" + name +
			", orderNum=" + orderNum +
			", delFlag=" + delFlag +
			"}";
	}
}
