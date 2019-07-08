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
 * @since 2019-07-07
 */
@TableName("logging_event_exception")
public class LoggingEventException extends Model<LoggingEventException> {

    private static final long serialVersionUID = 1L;

    @TableId("event_id")
	private Long eventId;
	private Integer i;
	@TableField("trace_line")
	private String traceLine;


	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public String getTraceLine() {
		return traceLine;
	}

	public void setTraceLine(String traceLine) {
		this.traceLine = traceLine;
	}

	@Override
	protected Serializable pkVal() {
		return this.eventId;
	}

	@Override
	public String toString() {
		return "LoggingEventException{" +
			", eventId=" + eventId +
			", i=" + i +
			", traceLine=" + traceLine +
			"}";
	}
}
