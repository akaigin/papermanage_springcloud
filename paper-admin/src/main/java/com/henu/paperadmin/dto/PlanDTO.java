package com.henu.paperadmin.dto;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

public class PlanDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 计划说明
     */
    private String description;
    /**
     * 对应角色
     */
    private Long roleId;
    /**
     * 文件存储地址
     */
    private String filePath;
    /**
     * 上传时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 上传者
     */
    private String createUser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }



    @Override
    public String toString() {
        return "PapPlan{" +
                ", id=" + id +
                ", description=" + description +
                ", roleId=" + roleId +
                ", filePath=" + filePath +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", createUser=" + createUser +
                "}";
    }
}