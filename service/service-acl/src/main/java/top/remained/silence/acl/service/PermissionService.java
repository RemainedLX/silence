package top.remained.silence.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.remained.silence.model.acl.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService extends IService<Permission> {
    List<Permission> queryAllPermission();

    boolean removeAll(Long id);

    boolean savePermissionRole(Long roleId,List<Long> permissionId);

    Map<String, Object> getPermissionByRoleId(Long roleId);
}
