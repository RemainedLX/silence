package top.remained.silence.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.acl.service.RoleService;
import top.remained.silence.common.result.Result;
import top.remained.silence.model.acl.Role;
import top.remained.silence.vo.acl.RoleQueryVo;

import java.util.List;

/**
 * @Author: lx
 * @CreateTime: 2023-07-11  00:38
 * @Description: TODO
 */
@Api(tags = "角色接口")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * @param: [current 当前页, limit 记录数, queryVo 查询参数]
     **/
    @ApiOperation("条件分页查询")
    @GetMapping("{current}/{limit}")
    public Result pages(@PathVariable Long current, @PathVariable Long limit,
                        RoleQueryVo queryVo) {
        Page<Role> page = new Page<>(current, limit);
        IPage<Role> rolePage = roleService.getPage(page, queryVo);
        return Result.ok(rolePage);
    }
    @ApiOperation("id查询")
    @GetMapping("get/{id}")
    public Result getRole( @PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.ok(role);
    }
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role) {
        boolean save = roleService.save(role);
        if (save ) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }
    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result update(@RequestBody Role role) {
        boolean save = roleService.updateById(role);
        if (save ) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }
    @ApiOperation("删除角色")
    @DeleteMapping("remove/{id}")
    public Result update(@PathVariable Long id) {
        boolean save = roleService.removeById(id);
        if (save ) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }
    @ApiOperation("批量删除角色")
    @DeleteMapping("batchRemove")
    public Result update( @RequestBody List<Long> ids) {
        boolean save = roleService.removeByIds(ids);
        if (save ) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }
}

