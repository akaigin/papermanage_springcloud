package com.henu.paperadmin.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-06-27
 */
public class CommentDTO extends Model<CommentDTO> {

	private static final long serialVersionUID = 1L;

	/**
	 * 评论id
	 */
	private Long commentId;
	/**
	 * 回复对象id
	 */
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
	private Long likeNum;
	/**
	 * 评论/回复时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:MM:SS")
	@JSONField(format="yyyy/MM/dd HH:MM:SS")
	private Date createTime;
	/**
	 * 哪篇文章下的评论
	 */
	private Long articleId;

	private boolean isLike;

	private String toWho;

	List<CommentDTO> replies;

	public List<CommentDTO> getReplies() {
		return replies;
	}

	public void setReplies(List<CommentDTO> replies) {
		this.replies = replies;
	}

	public boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(boolean like) {
		this.isLike = like;
	}

	public String getToWho() {
		return toWho;
	}

	public void setToWho(String toWho) {
		this.toWho = toWho;
	}

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
				"}";
	}
}
