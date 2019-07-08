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
@TableName("logging_event_property")
public class LoggingEventProperty extends Model<LoggingEventProperty> {

    private static final long serialVersionUID = 1L;

    @TableId("event_id")
	private Long eventId;
	@TableField("mapped_key")
	private String mappedKey;
	@TableField("mapped_value")
	private String mappedValue;


	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getMappedKey() {
		return mappedKey;
	}

	public void setMappedKey(String mappedKey) {
		this.mappedKey = mappedKey;
	}

	public String getMappedValue() {
		return mappedValue;
	}

	public void setMappedValue(String mappedValue) {
		this.mappedValue = mappedValue;
	}

	@Override
	protected Serializable pkVal() {
		return this.eventId;
	}

	@Override
	public String toString() {
		return "LoggingEventProperty{" +
			", eventId=" + eventId +
			", mappedKey=" + mappedKey +
			", mappedValue=" + mappedValue +
			"}";
	}
}
