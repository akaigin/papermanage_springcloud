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
 * @since 2019-06-27
 */
@TableName("pap_comment")
public class PapComment extends Model<PapComment> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
	@TableId(value="comment_id", type= IdType.AUTO)
	private Long commentId;
    /**
     * 回复对象id
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 评论/回复内容
     */
	private String content;
    /**
     * 作者
     */
	private String author;
    /**
     * 点赞数
     */
	@TableField("like_num")
	private Long likeNum;
    /**
     * 评论/回复时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 哪篇文章下的评论
     */
	@TableField("article_id")
	private Long articleId;
    /**
     * 向谁回复
     */
	@TableField("to_who")
	private String toWho;


	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Long likeNum) {
		this.likeNum = likeNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getToWho() {
		return toWho;
	}

	public void setToWho(String toWho) {
		this.toWho = toWho;
	}

	@Override
	protected Serializable pkVal() {
		return this.commentId;
	}

	@Override
	public String toString() {
		return "PapComment{" +
			", commentId=" + commentId +
			", parentId=" + parentId +
			", content=" + content +
			", author=" + author +
			", likeNum=" + likeNum +
			", createTime=" + createTime +
			", articleId=" + articleId +
			", toWho=" + toWho +
			"}";
	}
}
