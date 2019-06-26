package com.henu.paperadmin.domain;

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
 * @since 2019-06-26
 */
@TableName("pap_article")
public class ArticleDO extends Model<ArticleDO> {

	private static final long serialVersionUID = 1L;

	/**
	 * 文章id
	 */
	@TableId(value="article_id", type= IdType.AUTO)
	private Long articleId;
	/**
	 * 文章标题
	 */
	private String title;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * 文章标签
	 */
	private String tag;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField("modify_time")
	private Date modifyTime;
	/**
	 * 点击量
	 */
	private Long click;
	/**
	 * 是否公开
	 */
	@TableField("is_secret")
	private Long isSecret;
	/**
	 * 作者
	 */
	@TableField("create_user")
	private String createUser;


	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public Long getClick() {
		return click;
	}

	public void setClick(Long click) {
		this.click = click;
	}

	public Long getIsSecret() {
		return isSecret;
	}

	public void setIsSecret(Long isSecret) {
		this.isSecret = isSecret;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	protected Serializable pkVal() {
		return this.articleId;
	}

	@Override
	public String toString() {
		return "PapArticle{" +
				", articleId=" + articleId +
				", title=" + title +
				", content=" + content +
				", tag=" + tag +
				", createTime=" + createTime +
				", modifyTime=" + modifyTime +
				", click=" + click +
				", isSecret=" + isSecret +
				", createUser=" + createUser +
				"}";
	}
}
