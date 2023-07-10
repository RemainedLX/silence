package top.remained.silence.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.remained.silence.model.acl.Role;
import top.remained.silence.vo.acl.RoleQueryVo;

public interface RoleService extends IService<Role> {
    IPage<Role> getPage(Page<Role> page, RoleQueryVo queryVo);
}
