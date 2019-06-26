package com.henu.paperadmin.controller;

import com.henu.paperadmin.domain.ArticleDO;
import com.henu.paperadmin.dto.GuidanceDTO;
import com.henu.paperadmin.dto.ArticleDTO;
import com.henu.paperadmin.service.ArticleService;
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
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PUT;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/article")
@RestController
@Api("文章操作接口")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    //@PreAuthorize("hasAuthority('admin:article:article')")
    @ApiOperation("获取文章列表并分页")
    @Log("获取文章列表")
    @GetMapping("/list")
    ResultBean list(@ApiParam(name="params", value = "分页配置相关信息") @RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        String createUser= SecuityUtils.getCurrentUser().getName();
        ArticleDO articleDO=new ArticleDO();
        if(params.get("isSecret").equals("1")){
            articleDO.setIsSecret(ToolUtils.intToLong(1));
        }else if(params.get("isSecret").equals("2")){
            articleDO.setIsSecret(ToolUtils.intToLong(2));
        }
        Map<String,Object> map=new HashMap<>();
        if(params.get("personal").equals("1")){
            articleDO.setCreateUser(createUser);
            map.put("createUser",createUser);
        }
        List<ArticleDTO> articleDTOS = articleService.list(query,articleDO);
        int total = articleService.count(map);
        PageUtils pageUtil = new PageUtils(articleDTOS, total);
        return ResultBean.ok().put("page",pageUtil);
    }
    /**
     * 增加文章
     * @param articleDTO
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:article:add')")
    @ApiOperation("增加文章")
    @Log("增加文章")
    @PostMapping()
    ResultBean save(@ApiParam(name="articleDTO", value = "文章相关信息") @RequestBody ArticleDTO articleDTO) {
        articleDTO.setClick(ToolUtils.intToLong(0));
        String createUser=SecuityUtils.getCurrentUser().getName();
        articleDTO.setCreateTime(new Date());
        articleDTO.setCreateUser(createUser);
        return ResultBean.operate(articleService.save(articleDTO) > 0);
    }

    /**
     * 修改文章
     * @param articleDTO
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:article:update')")
    @ApiOperation("修改文章")
    @Log("修改文章")
    @PutMapping()
    public ResultBean update(@ApiParam(name="articleDTO", value = "文章相关信息") @RequestBody ArticleDTO articleDTO) {
        articleDTO.setModifyTime(new Date());
        return ResultBean.operate(articleService.update(articleDTO) > 0);
    }


    /**
     * 删除文章
     * @param
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:article:delete')")
    @ApiOperation("删除文章")
    @Log("删除文章")
    @DeleteMapping()
    ResultBean remove(@ApiParam(name="id", value = "文章ID") ArticleDTO articleDTO) {
        return ResultBean.operate (articleService.remove(articleDTO.getArticleId()) > 0);
    }

    /**
     * 获取文章内容
     * @param
     * @return
    //@PreAuthorize("hasAuthority('admin:article:delete')")
    @ApiOperation("获取文章内容")
    @Log("获取文章内容")
    @DeleteMapping()
    ResultBean viewArticle(@ApiParam(name="id", value = "文章ID") Long id) {
        return ResultBean.operate (articleService.remove(id) > 0);
    }*/

    /**
     * 点击量+1
     * @param
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:article:delete')")
    @ApiOperation("点击量+1")
    @Log("点击量+1")
    @PutMapping("click")
    ResultBean clickPlus(@ApiParam(name="articleDTO", value = "文章相关信息") @RequestBody ArticleDTO articleDTO) {
        return ResultBean.operate (articleService.updateClickPlus(articleDTO.getArticleId()) > 0);
    }
}
