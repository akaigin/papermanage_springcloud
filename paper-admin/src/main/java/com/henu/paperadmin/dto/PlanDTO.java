package com.henu.paperadmin.dto;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PlanDTO {

     static final long serialVersionUID = 1L;

     Long id;
    /**
     * 计划说明
     */
     String description;
    /**
     * 对应角色
     */
     List<Long> roleId;
    /**
     * 对应角色
     */
    String roleNames;
    /**
     * 文件名
     */
    String fileName;
    /**
     * 文件存储地址
     */
     String filePath;
    /**
     * 上传时间
     */
     Date createTime;
    /**
     * 修改时间
     */
     Date modifyTime;
    /**
     * 上传者
     */
     String createUser;


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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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