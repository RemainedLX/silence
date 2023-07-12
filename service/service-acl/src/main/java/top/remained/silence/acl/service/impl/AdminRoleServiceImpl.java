package top.remained.silence.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.remained.silence.acl.mapper.AdminRoleMapper;
import top.remained.silence.acl.mapper.RoleMapper;
import top.remained.silence.acl.service.AdminRoleService;
import top.remained.silence.model.acl.AdminRole;
import top.remained.silence.model.acl.Role;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: lx
 * @CreateTime: 2023-07-12  23:41
 * @Description: TODO
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Override
    public Map<String, Object> getRoleByAdminId(Long adminId) {
        Map<String, Object> map = new HashMap<>();
        List<Role> allRolesList = roleMapper.selectList(null);
        map.put("allRolesList",allRolesList);
        // 用户已有角色
        List<Long> assignRoles = adminRoleMapper.selectList(
                new LambdaQueryWrapper<AdminRole>().eq(AdminRole::getAdminId, adminId)).
                stream().map(AdminRole::getRoleId).collect(Collectors.toList());
        map.put("assignRoles",assignRoles);
        return map;
    }

    @Override
    public boolean saveAdminRole(Long adminId,List<Long> roleId) {
        // 删除
        List<AdminRole> adminRoles = adminRoleMapper.selectList(new LambdaQueryWrapper<AdminRole>().eq(AdminRole::getAdminId, adminId));
        if (!CollectionUtils.isEmpty(adminRoles)) {
            adminRoleMapper.deleteBatchIds(adminRoles);
        }
        // 重新分配
        roleId.forEach(r ->
           adminRoleMapper.insert(AdminRole.builder().adminId(adminId).roleId(r).build())
         );
        return true;
    }
}

