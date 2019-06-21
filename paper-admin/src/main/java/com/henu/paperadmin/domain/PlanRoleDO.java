package com.henu.paperadmin.domain;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-06-21
 */
@TableName("pap_plan_role")
public class PlanRoleDO extends Model<PlanRoleDO> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @TableField("role_id")
    private Long roleId;
    @TableField("plan_id")
    private Long planId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PapPlanRole{" +
                ", id=" + id +
                ", roleId=" + roleId +
                ", planId=" + planId +
                "}";
    }
}
