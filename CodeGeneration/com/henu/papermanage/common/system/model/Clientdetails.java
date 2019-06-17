package com.henu.papermanage.common.system.model;

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
 * @since 2019-06-16
 */
@TableName("clientdetails")
public class Clientdetails extends Model<Clientdetails> {

    private static final long serialVersionUID = 1L;

	private String appId;
	private String resourceIds;
	private String appSecret;
	private String scope;
	private String grantTypes;
	private String redirectUrl;
	private String authorities;
	@TableField("access_token_validity")
	private Integer accessTokenValidity;
	@TableField("refresh_token_validity")
	private Integer refreshTokenValidity;
	private String additionalInformation;
	private String autoApproveScopes;


	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAutoApproveScopes() {
		return autoApproveScopes;
	}

	public void setAutoApproveScopes(String autoApproveScopes) {
		this.autoApproveScopes = autoApproveScopes;
	}

	@Override
	protected Serializable pkVal() {
		return this.appId;
	}

	@Override
	public String toString() {
		return "Clientdetails{" +
			", appId=" + appId +
			", resourceIds=" + resourceIds +
			", appSecret=" + appSecret +
			", scope=" + scope +
			", grantTypes=" + grantTypes +
			", redirectUrl=" + redirectUrl +
			", authorities=" + authorities +
			", accessTokenValidity=" + accessTokenValidity +
			", refreshTokenValidity=" + refreshTokenValidity +
			", additionalInformation=" + additionalInformation +
			", autoApproveScopes=" + autoApproveScopes +
			"}";
	}
}
