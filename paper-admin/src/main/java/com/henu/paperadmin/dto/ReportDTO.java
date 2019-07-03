package com.henu.paperadmin.dto;

import com.henu.paperadmin.domain.ReportDO;

import java.util.Date;
import java.util.List;

public class ReportDTO extends ReportDO {

    static final long serialVersionUID = 1L;

    /**
     * 对应角色
     */
    List<Long> roleId;
    /**
     * 对应角色
     */
    String roleNames;

    /**
     * 导师Id
     */
    Long tutorId;

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

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }
}