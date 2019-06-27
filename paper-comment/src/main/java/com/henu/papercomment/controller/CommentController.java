package com.henu.papercomment.controller;

import com.henu.papercomment.domain.CommentDO;
import com.henu.papercomment.dto.CommentDTO;
import com.henu.papercomment.service.CommentService;
import com.henu.papercomment.utils.ToolUtils;
import com.henu.papercommon.annotation.Log;
import com.henu.papercommon.utils.PageUtils;
import com.henu.papercommon.utils.Query;
import com.henu.papercommon.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/comment")
@RestController
@Api("文章操作接口")
public class CommentController {
    @Autowired
    CommentService commentService;

    //@PreAuthorize("hasAuthority('admin:comment:comment')")
    @ApiOperation("获取文章列表并分页")
    @Log("获取文章列表")
    @GetMapping("/list")
    ResultBean list(@ApiParam(name="params", value = "分页配置相关信息") @RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        String createUser= SecuityUtils.getCurrentUser().getName();
        CommentDO commentDO=new CommentDO();
        if(params.get("isSecret").equals("1")){
            commentDO.setIsSecret(ToolUtils.intToLong(1));
        }else if(params.get("isSecret").equals("2")){
            commentDO.setIsSecret(ToolUtils.intToLong(2));
        }
        Map<String,Object> map=new HashMap<>();
        if(params.get("personal").equals("1")){
            commentDO.setCreateUser(createUser);
            map.put("createUser",createUser);
        }
        List<CommentDTO> commentDTOS = commentService.list(query,commentDO);
        int total = commentService.count(map);
        PageUtils pageUtil = new PageUtils(commentDTOS, total);
        return ResultBean.ok().put("page",pageUtil);
    }
    /**
     * 增加文章
     * @param commentDTO
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:comment:add')")
    @ApiOperation("增加文章")
    @Log("增加文章")
    @PostMapping()
    ResultBean save(@ApiParam(name="commentDTO", value = "文章相关信息") @RequestBody CommentDTO commentDTO) {
        commentDTO.setClick(ToolUtils.intToLong(0));
        String createUser=SecuityUtils.getCurrentUser().getName();
        commentDTO.setCreateTime(new Date());
        commentDTO.setCreateUser(createUser);
        return ResultBean.operate(commentService.save(commentDTO) > 0);
    }

    /**
     * 修改文章
     * @param commentDTO
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:comment:update')")
    @ApiOperation("修改文章")
    @Log("修改文章")
    @PutMapping()
    public ResultBean update(@ApiParam(name="commentDTO", value = "文章相关信息") @RequestBody CommentDTO commentDTO) {
        commentDTO.setModifyTime(new Date());
        return ResultBean.operate(commentService.update(commentDTO) > 0);
    }


    /**
     * 删除文章
     * @param
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:comment:delete')")
    @ApiOperation("删除文章")
    @Log("删除文章")
    @DeleteMapping()
    ResultBean remove(@ApiParam(name="id", value = "文章ID") CommentDTO commentDTO) {
        return ResultBean.operate (commentService.remove(commentDTO.getCommentId()) > 0);
    }

    /**
     * 获取文章内容
     * @param
     * @return
    //@PreAuthorize("hasAuthority('admin:comment:delete')")
    @ApiOperation("获取文章内容")
    @Log("获取文章内容")
    @DeleteMapping()
    ResultBean viewComment(@ApiParam(name="id", value = "文章ID") Long id) {
        return ResultBean.operate (commentService.remove(id) > 0);
    }*/

    /**
     * 点击量+1
     * @param
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:comment:delete')")
    @ApiOperation("点击量+1")
    @Log("点击量+1")
    @PutMapping("click")
    ResultBean clickPlus(@ApiParam(name="commentDTO", value = "文章相关信息") @RequestBody CommentDTO commentDTO) {
        return ResultBean.operate (commentService.updateClickPlus(commentDTO.getCommentId()) > 0);
    }
}
