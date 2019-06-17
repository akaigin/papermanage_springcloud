package com.henu.papermanage.common.system.model;

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
 * @since 2019-06-16
 */
@TableName("oauth_access_token")
public class OauthAccessToken extends Model<OauthAccessToken> {

    private static final long serialVersionUID = 1L;

	@TableField("token_id")
	private String tokenId;
	private String token;
    @TableId("authentication_id")
	private String authenticationId;
	@TableField("user_name")
	private String userName;
	@TableField("client_id")
	private String clientId;
	private String authentication;
	@TableField("refresh_token")
	private String refreshToken;


	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	protected Serializable pkVal() {
		return this.authenticationId;
	}

	@Override
	public String toString() {
		return "OauthAccessToken{" +
			", tokenId=" + tokenId +
			", token=" + token +
			", authenticationId=" + authenticationId +
			", userName=" + userName +
			", clientId=" + clientId +
			", authentication=" + authentication +
			", refreshToken=" + refreshToken +
			"}";
	}
}
