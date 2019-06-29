package com.henu.papercommon.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date  2018/3/30 20:37
 *
 */
@Getter
@Setter
public class SimpleResponse extends BaseResponse {

    private Object item;

    private String msg;

    private int status;

    protected SimpleResponse() {
    }

    protected SimpleResponse(int status, String msg, Object item) {
        super(status, msg);
        this.item = item;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
