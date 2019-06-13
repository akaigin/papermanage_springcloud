package com.bootdo.clouddobase.controller;


import com.bootdo.clouddocommon.dto.LogDO;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.ResultBean;
import com.bootdo.clouddobase.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/log")
@RestController
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping()
    ResultBean list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return ResultBean.page(new PageUtils(logService.queryList(query), logService.count(query)));
    }

    @PostMapping("/save")
    ResultBean save(@RequestBody LogDO logDO) {
        if (logService.save(logDO) > 0) {
            return ResultBean.ok();
        }
        return ResultBean.error();
    }

    @DeleteMapping()
    ResultBean remove(Long id) {
        if (logService.remove(id) > 0) {
            return ResultBean.ok();
        }
        return ResultBean.error();
    }

    @PostMapping("/batchRemove")
    ResultBean batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = logService.batchRemove(ids);
        if (r > 0) {
            return ResultBean.ok();
        }
        return ResultBean.error();
    }


}
