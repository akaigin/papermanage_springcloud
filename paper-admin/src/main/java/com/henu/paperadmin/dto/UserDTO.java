package com.henu.paperadmin.dto;

import com.henu.paperadmin.domain.UserMO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserDTO extends UserMO {
    private static final long serialVersionUID = 1L;
    //角色
    private List<Long> roleIds;

    private String major;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }


}