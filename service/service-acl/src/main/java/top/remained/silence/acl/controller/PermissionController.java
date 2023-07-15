package top.remained.silence.acl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.acl.service.PermissionService;
import top.remained.silence.common.result.Result;
import top.remained.silence.model.acl.Permission;

import java.util.List;
import java.util.Map;

/**
 * @Author: lx
 * @CreateTime: 2023-07-13  23:57
 * @Description: TODO
 */
@Api(tags = "菜单")
@RestController
@RequestMapping("/admin/acl/permission")
@CrossOrigin
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @ApiOperation("查询菜单")
    @GetMapping
    public Result list(){
      List<Permission> list =  permissionService.queryAllPermission();
      return Result.ok(list);
    }
    @ApiOperation("添加菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission){
        permissionService.save(permission);
        return Result.ok(null);
    }
    @ApiOperation("递归删除菜单")
    @PostMapping("remove/{id}")
    public Result removeAll(@PathVariable Long id){
        boolean flag = permissionService.removeAll(id);
        if (flag) {
            return Result.ok(null);
        }
        ;return Result.ok(null);
    }
    @ApiOperation("修改菜单")
    @PostMapping("update")
    public Result update(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return Result.ok(null);
    }
    @ApiOperation("为角色分配菜单")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long roleId,@RequestParam List<Long> permissionId) {
        boolean flag = permissionService.savePermissionRole(roleId,permissionId);
        if (flag ) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }
    @ApiOperation("获取角色菜单")
    @GetMapping("toAssign/{roleId}")
    public Result getUserRole(@PathVariable Long roleId) {
        //
        Map<String, Object> map = permissionService.getPermissionByRoleId(roleId);
        return Result.ok(map);
    }}

