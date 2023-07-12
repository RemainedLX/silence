package top.remained.silence.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.remained.silence.model.acl.AdminRole;

import java.util.List;
import java.util.Map;

public interface AdminRoleService extends IService<AdminRole> {
    Map<String, Object> getRoleByAdminId(Long adminId);

    boolean saveAdminRole(Long adminId,List<Long> roleId);
}
