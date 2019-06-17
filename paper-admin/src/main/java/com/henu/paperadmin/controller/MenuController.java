package com.henu.paperadmin.controller;

import com.henu.paperadmin.domain.MenuDO;
import com.henu.paperadmin.domain.Tree;
import com.henu.paperadmin.service.MenuService;
import com.henu.paperadmin.utils.SecuityUtils;
import com.henu.papercommon.annotation.Log;
import com.henu.papercommon.context.FilterContextHandler;
import com.henu.papercommon.dto.MenuDTO;
import com.henu.papercommon.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tiger 1992lcg@163.com
 * @version V1.0
 */
@RequestMapping("/menu")
@RestController()
public class MenuController {
    @Autowired
    MenuService menuService;

    @Log("获取当前用户的菜单")
    @GetMapping("currentUserMenus")
    ResultBean currentUserMenus() {
        return ResultBean.ok().put("currentUserMenus",menuService.RouterDTOsByUserId(SecuityUtils.getCurrentUser().getId()));
    }

    @Log("访问菜单")
    @GetMapping("tree")
    Tree<MenuDO> tree() {
        return menuService.getTree();
    }

    @GetMapping
    List<Tree<MenuDO>> list() {
        return menuService.getTree().getChildren();
    }

    @GetMapping("{id}")
    MenuDO get(@PathVariable("id") Long id) {
        MenuDO menu = menuService.get(id);
        return menu;
    }

    @GetMapping("list")
    List<MenuDO> list(@RequestParam Map<String, Object> params) {
        List<MenuDO> menus = menuService.list(params);
        return menus;
    }

    @PutMapping()
    ResultBean update(@RequestBody MenuDO menuDO) {
        if (menuService.update(menuDO) > 0) {
            return ResultBean.ok();
        }
        return ResultBean.error();
    }

    @PostMapping
    ResultBean save(@RequestBody MenuDO menuDO) {
        return ResultBean.operate(menuService.save(menuDO) > 0);
    }

    @DeleteMapping()
    ResultBean remove(Long id) {
        if (menuService.remove(id) > 0) {
            return ResultBean.ok();
        }
        return ResultBean.error();
    }

    @GetMapping("userMenus")
    List<MenuDTO> userMenus() {
        List<MenuDO> menuDOS = menuService.userMenus(Long.parseLong(FilterContextHandler.getUserID()));
        List<MenuDTO> menuDTOS = new ArrayList<>();
        for (MenuDO menuDO : menuDOS) {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenuId(menuDO.getMenuId());
            menuDTO.setUrl(menuDO.getUrl());
            menuDTO.setPerms(menuDO.getPerms());
            menuDTOS.add(menuDTO);
        }
        return menuDTOS;
    }

    @GetMapping("clearCache")
    ResultBean clearCache() {
        Boolean flag = menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        if (flag) {
            return ResultBean.ok();
        }
        return ResultBean.error();
    }

//    /**
//     * 当前用户菜单的树形结构
//     *
//     * @return
//     */
//    @RequestMapping("/currentUserMenus")
//    List<Tree<MenuDO>> currentUserMenus() {
//        List<Tree<MenuDO>> menus = menuService.listMenuTree(Long.parseLong(FilterContextHandler.getUserID()));
//        return menus;
//    }

    @GetMapping("/roleId")
    List<Long> menuIdsByRoleId(Long roleId) {
        return menuService.MenuIdsByRoleId(roleId);
    }
}
