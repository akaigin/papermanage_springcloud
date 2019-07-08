package com.henu.paperadmin.controller;

import com.henu.paperadmin.config.ThreadConfig;
import com.henu.paperadmin.domain.CommentDO;
import com.henu.paperadmin.domain.UserCommentIslikeDO;
import com.henu.paperadmin.dto.CommentDTO;
import com.henu.paperadmin.dto.UserCommentDTO;
import com.henu.paperadmin.service.CommentService;
import com.henu.paperadmin.service.UserCommentService;
import com.henu.paperadmin.service.impl.UserCommentServiceImpl;
import com.henu.paperadmin.utils.CommentDOConvert;
import com.henu.paperadmin.utils.SecuityUtils;
import com.henu.paperadmin.utils.ToolUtils;
import com.henu.papercommon.annotation.Log;
import com.henu.papercommon.utils.PageUtils;
import com.henu.papercommon.utils.Query;
import com.henu.papercommon.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RequestMapping("/comment")
@RestController
@Api("评论操作接口")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Resource
    UserCommentService userCommentService;

    @Resource
    private ExecutorService executorService;

    @PreAuthorize("hasAuthority('admin:comment')")
    @ApiOperation("根据文章ID获取评论列表")
    //@Log("根据文章ID获取评论列表")
    @GetMapping("/list")
    ResultBean list(@ApiParam(name="articleId", value = "评论所处文章id") Long articleId) {
        CommentDO commentDO=new CommentDO();
        commentDO.setArticleId(articleId);
        List<CommentDO> commentDOS = commentService.list(commentDO);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(CommentDO commentDOResponse:commentDOS){
            System.out.println(Thread.currentThread().getName()+":");
            CommentDTO commentDTO = CommentDOConvert.commentDOToCommentDTO(commentDOResponse);
            Future fal  = executorService.submit(new Callable<String>() {
                @Override
                public String call() {
                    commentDTO.setIsLike(userCommentService.isLikeComment(SecuityUtils.getCurrentUser().getId(),commentDOResponse.getCommentId())>0);
                    return "处理成功！";
                }
            });
            commentDTOS.add(commentDTO);
        }
        List<CommentDTO> ResultCommentDTOS=new ArrayList<>();
        for(CommentDTO commentDTO:commentDTOS){
            if(commentDTO.getParentId()==0){
                List<CommentDTO> commentDTOS1=new ArrayList<>();
                for(CommentDTO commentDTO1:commentDTOS){
                    if(commentDTO1.getParentId()==commentDTO.getCommentId()){
                        commentDTOS1.add(commentDTO1);
                    }
                }
                commentDTO.setReplies(commentDTOS1);
                ResultCommentDTOS.add(commentDTO);
            }
        }
        return ResultBean.ok().put("data",ResultCommentDTOS);
    }
    /**
     * 增加评论
     * @param commentDTO
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:comment')")
    @ApiOperation("发表评论")
    @Log("发表评论")
    @PostMapping()
    ResultBean save(@ApiParam(name="commentDTO", value = "评论相关信息") @RequestBody CommentDTO commentDTO) {
        commentDTO.setLikeNum(ToolUtils.intToLong(0));
        String createUser=SecuityUtils.getCurrentUser().getName();
        commentDTO.setCreateTime(new Date());
        commentDTO.setAuthor(createUser);
        return ResultBean.operate(commentService.save(commentDTO) > 0);
    }


    /**
     * 删除评论
     * @param
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:comment')")
    @ApiOperation("删除评论")
    @Log("删除评论")
    @DeleteMapping()
    ResultBean remove(@ApiParam(name="id", value = "评论ID") CommentDTO commentDTO) {
        return ResultBean.operate (commentService.remove(commentDTO.getCommentId()) > 0);
    }

    /**
     * 获取评论内容
     * @param
     * @return
    //@PreAuthorize("hasAuthority('admin:comment:delete')")
    @ApiOperation("获取评论内容")
    @Log("获取评论内容")
    @DeleteMapping()
    ResultBean viewComment(@ApiParam(name="id", value = "评论ID") Long id) {
        return ResultBean.operate (commentService.remove(id) > 0);
    }*/

    /**
     * 点击量+1
     * @param
     * @return
     */
    @ApiOperation("根据页面关闭后评论点赞的变更情况更新数据库")
    //@Log("根据页面关闭后评论点赞的变更情况更新数据库")
    @PutMapping("updateLikeNum")
    ResultBean updateLikeNum(@ApiParam(name="commentDTO", value = "评论相关信息") @RequestBody List<UserCommentDTO> userCommentDTOS) {
        for(UserCommentDTO userCommentDTO:userCommentDTOS){
            userCommentDTO.setUserId(SecuityUtils.getCurrentUser().getId());
        }
        return ResultBean.operate (commentService.updateLikeNumPlus(userCommentDTOS) > 0);
    }

}
