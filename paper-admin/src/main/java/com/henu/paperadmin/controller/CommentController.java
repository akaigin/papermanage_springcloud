package com.henu.paperadmin.controller;

import com.henu.paperadmin.domain.CommentDO;
import com.henu.paperadmin.dto.CommentDTO;
import com.henu.paperadmin.service.CommentService;
import com.henu.paperadmin.service.UserCommentService;
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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/comment")
@RestController
@Api("评论操作接口")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    UserCommentService userCommentService;
    //@PreAuthorize("hasAuthority('admin:comment:comment')")
    @ApiOperation("获取评论列表并分页")
    @Log("获取评论列表并分页")
    @GetMapping("/list")
    ResultBean list(@ApiParam(name="articleId", value = "评论所处文章id") Long articleId) {
        CommentDO commentDO=new CommentDO();
        commentDO.setArticleId(articleId);
        List<CommentDO> commentDOS = commentService.list(commentDO);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(CommentDO commentDOResponse:commentDOS){
            CommentDTO commentDTO = CommentDOConvert.commentDOToCommentDTO(commentDOResponse);
            commentDTO.setIsLike(userCommentService.isLikeComment(SecuityUtils.getCurrentUser().getId(),commentDOResponse.getCommentId())>0);
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
    //@PreAuthorize("hasAuthority('admin:comment:add')")
    @ApiOperation("增加评论")
    @Log("增加评论")
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
    //@PreAuthorize("hasAuthority('admin:comment:delete')")
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
    //@PreAuthorize("hasAuthority('admin:comment:delete')")
    @ApiOperation("点赞数+1")
    @Log("点赞数+1")
    @PutMapping("click")
    ResultBean clickPlus(@ApiParam(name="commentDTO", value = "评论相关信息") @RequestBody CommentDTO commentDTO) {
        return ResultBean.operate (commentService.updateLikeNumPlus(commentDTO.getCommentId()) > 0);
    }

    String getToWho(Long parentId, List<CommentDO> commentDOS){
        for(CommentDO commentDO:commentDOS){
            if(commentDO.getArticleId()==parentId) {
                return commentDO.getAuthor();
            }
        }
        return "";
    }
}
