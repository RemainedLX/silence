package top.remained.silence.acl.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.reamined.silence.common.utils.MD5;
import top.remained.silence.acl.service.AdminRoleService;
import top.remained.silence.acl.service.AdminService;
import top.remained.silence.common.result.Result;
import top.remained.silence.model.acl.Admin;
import top.remained.silence.vo.acl.AdminQueryVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: lx
 * @CreateTime: 2023-07-12  22:44
 * @Description: TODO
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation("用户列表")
    @GetMapping("{current}/{limit}")
    public Result pageList(@PathVariable Long current,
                        @PathVariable Long limit,
                        AdminQueryVo adminQueryVo) {
        Page<Admin> page = new Page<>(current, limit) ;
        IPage<Admin> adminList = adminService.selectPageList(page,adminQueryVo);
        return Result.ok(adminList);
    }
    @ApiOperation("根据Id查询")
    @GetMapping("get/{id}")
    public Result getAdminById(Long id) {
        return Result.ok(adminService.getById(id));
    }
    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(Admin admin) {
        // MD5 加密
        String password = admin.getPassword();
        String encryptPassword = MD5.encrypt(password);
        admin.setPassword(encryptPassword);
        boolean flag = adminService.save(admin);
        if (!flag){
            Result.fail(null);
        }
        return Result.ok(null);
    }
    @ApiOperation("修改用户")
    @PutMapping("update")
    public Result update(@RequestBody Admin admin) {
        boolean flag = adminService.updateById(admin);
        if (!flag){
            Result.fail(null);
        }
        return Result.ok(null);
    }
    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean flag = adminService.removeById(id);
        if (!flag){
            Result.fail(null);
        }
        return Result.ok(null);
    }
    @ApiOperation("批量删除用户")
    @DeleteMapping("batchRemove")
    public Result remove(List<Long> ids) {
        boolean flag = adminService.removeByIds(ids);
        if (!flag){
            Result.fail(null);
        }
        return Result.ok(null);
    }
    @ApiOperation("获取用户角色")
    @GetMapping("toAssign/{adminId}")
    public Result getUserRole(@PathVariable Long adminId) {
      Map<String, Object> map = adminRoleService.getRoleByAdminId(adminId);
        return Result.ok(map);
    }
    @ApiOperation("为用户分配角色")
    @GetMapping("doAssign")
    public Result doAssign(@RequestParam Long adminId,
                           @RequestParam List<Long> roleId) {
      boolean flag = adminRoleService.saveAdminRole(adminId,roleId);
        if (flag ) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }


}

