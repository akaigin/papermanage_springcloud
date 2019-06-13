package com.henu.papermanage.common.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-06-12
 */
@TableName("oauth_approvals")
public class OauthApprovals extends Model<OauthApprovals> {

    private static final long serialVersionUID = 1L;

	private String userId;
	private String clientId;
	private String scope;
	private String status;
	private Date expiresAt;
	private Date lastModifiedAt;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OauthApprovals{" +
			", userId=" + userId +
			", clientId=" + clientId +
			", scope=" + scope +
			", status=" + status +
			", expiresAt=" + expiresAt +
			", lastModifiedAt=" + lastModifiedAt +
			"}";
	}
}
