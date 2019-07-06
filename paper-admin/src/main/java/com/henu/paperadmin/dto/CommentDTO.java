package com.henu.paperadmin.dto;

import com.henu.paperadmin.domain.CommentDO;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-06-27
 */
public class CommentDTO extends CommentDO {

	private static final long serialVersionUID = 1L;

	private boolean isLike;

	private List<CommentDTO> replies;

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


}