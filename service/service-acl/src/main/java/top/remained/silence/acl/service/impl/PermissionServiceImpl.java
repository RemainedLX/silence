package top.remained.silence.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import jodd.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.remained.silence.acl.mapper.PermissionMapper;
import top.remained.silence.acl.mapper.RolePermissionMapper;
import top.remained.silence.acl.service.PermissionService;
import top.remained.silence.acl.util.PermissionHelper;
import top.remained.silence.common.exception.SilenceException;
import top.remained.silence.model.acl.Permission;
import top.remained.silence.model.acl.RolePermission;
import top.remained.silence.model.base.BaseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: lx
 * @CreateTime: 2023-07-13  23:59
 * @Description: TODO
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
   @Autowired
   private PermissionMapper permissionMapper;
   @Autowired
   private RolePermissionMapper rolePermissionMapper;
    @Override
    public List<Permission> queryAllPermission() {
        List<Permission> list = permissionMapper.selectList(null);
        return PermissionHelper.buildPermission(list);
    }

    @Override
    public boolean removeAll(Long id) {

        List<Long> resultList = new ArrayList<>();
        List<Long> flag = getPermissionChildren(id);
        while (CollectionUtils.isEmpty(flag)) {
            flag =  getPermissionChildren(id);
            resultList.addAll(flag);
        }
        resultList.add(id);
        permissionMapper.deleteBatchIds(resultList);
        return true;
    }

    @Override
    public boolean savePermissionRole(Long roleId,List<Long> permissionId) {
        // 删除、
        rolePermissionMapper.delete(new LambdaQueryWrapper<RolePermission>()
              .eq(RolePermission::getRoleId, roleId));
        // 新增
        int count = rolePermissionMapper.insertList(roleId, permissionId);
        if (count == permissionId.size()) {
            return true;
        }

        return false;
    }

    @Override
    public Map<String, Object> getPermissionByRoleId(Long roleId) {
        Map<String, Object> map  = new HashMap<>();
        List<Permission> allPermissions = permissionMapper.selectList(null);
        map.put("allPermissions",PermissionHelper.buildPermission(allPermissions));
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(new LambdaQueryWrapper<RolePermission>()
                .eq(RolePermission::getRoleId, roleId));
//                .stream().map(RolePermission::getId).collect(Collectors.toList());
        map.put("rolePermissions",rolePermissions);
        return map;
    }

    // 获取其子部门id
    public  List<Long>  getPermissionChildren(Long id) {
        Permission permission = permissionMapper.selectOne(new LambdaQueryWrapper<Permission>().eq(Permission::getId, id));
        if (permission != null && permission.getType()==1) {
            return permissionMapper.selectList(new LambdaQueryWrapper<Permission>()
                    .eq(Permission::getPid, id).or().eq(Permission::getId, id)
                    .eq(Permission::getStatus, 1))
                    .stream().map(Permission::getId)
                    .collect(Collectors.toList());
        }
        return new ArrayList<Long>() ;

    }
}

