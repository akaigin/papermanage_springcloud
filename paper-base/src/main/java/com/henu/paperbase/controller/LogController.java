package com.henu.paperbase.controller;


import com.henu.papercommon.dto.LogDO;
import com.henu.papercommon.utils.PageUtils;
import com.henu.papercommon.utils.Query;
import com.henu.papercommon.utils.ResultBean;
import com.henu.paperbase.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
