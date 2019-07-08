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
@TableName("pap_report")
public class PapReport extends Model<PapReport> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 计划说明
     */
	private String description;
	@TableField("file_name")
	private String fileName;
    /**
     * 文件存储地址
     */
	@TableField("file_path")
	private String filePath;
    /**
     * 上传时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 修改时间
     */
	@TableField("modify_time")
	private Date modifyTime;
    /**
     * 当前状态（1：通过 2：未通过 3：待审核）
     */
	private Long status;
    /**
     * 指导意见
     */
	private String guidance;
    /**
     * 上传者
     */
	@TableField("create_user")
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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getGuidance() {
		return guidance;
	}

	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "PapReport{" +
			", id=" + id +
			", description=" + description +
			", fileName=" + fileName +
			", filePath=" + filePath +
			", createTime=" + createTime +
			", modifyTime=" + modifyTime +
			", status=" + status +
			", guidance=" + guidance +
			", createUser=" + createUser +
			"}";
	}
}
