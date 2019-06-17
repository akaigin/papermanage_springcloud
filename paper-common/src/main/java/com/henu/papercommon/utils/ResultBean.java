package com.henu.papercommon.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultBean extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public ResultBean() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static ResultBean error() {
        return error(500, "操作失败");
    }

    public static ResultBean operate(boolean b){
        if(b){
            return ResultBean.ok();
        }
        return ResultBean.error();
    }

    public static ResultBean error(String msg) {
        return error(500, msg);
    }

    public static ResultBean error(int code, String msg) {
        ResultBean r = new ResultBean();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResultBean ok(String msg) {
        ResultBean r = new ResultBean();
        r.put("msg", msg);
        return r;
    }

    public static ResultBean ok(Map<String, Object> map) {
        ResultBean r = new ResultBean();
        r.putAll(map);
        return r;
    }

    public static ResultBean ok() {
        return new ResultBean();
    }

    public static ResultBean error401() {
        return error(401, "你还没有登录");
    }

    public static ResultBean error403() {
        return error(403, "你没有访问权限");
    }

    public static ResultBean data(Object data){
        return ResultBean.ok().put("data",data);
    }

    public static ResultBean page(Object page){
        return ResultBean.ok().put("page",page);
    }

    @Override
    public ResultBean put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
