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
 * 
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-07-07
 */
@TableName("pap_user")
public class PapUser extends Model<PapUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String username;
	private String name;
	private String password;
	private String email;
	private String phone;
    /**
     * 专业id
     */
	@TableField("major_id")
	private Long majorId;
	private String status;
	@TableField("create_time")
	private Date createTime;
	@TableField("modify_time")
	private Date modifyTime;
	private String sex;
    /**
     * 导师id，若角色为老师或管理员则为0或null
     */
	@TableField("tutor_id")
	private Long tutorId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getMajorId() {
		return majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "PapUser{" +
			", id=" + id +
			", username=" + username +
			", name=" + name +
			", password=" + password +
			", email=" + email +
			", phone=" + phone +
			", majorId=" + majorId +
			", status=" + status +
			", createTime=" + createTime +
			", modifyTime=" + modifyTime +
			", sex=" + sex +
			", tutorId=" + tutorId +
			"}";
	}
}
