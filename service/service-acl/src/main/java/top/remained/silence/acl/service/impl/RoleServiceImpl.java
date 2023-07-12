package top.remained.silence.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.remained.silence.acl.mapper.RoleMapper;
import top.remained.silence.acl.service.RoleService;
import top.remained.silence.model.acl.Role;
import top.remained.silence.vo.acl.RoleQueryVo;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public IPage<Role> getPage(Page<Role> page, RoleQueryVo queryVo) {
        String roleName = queryVo.getRoleName();
        return roleMapper.selectPage(page,new LambdaQueryWrapper<Role>().like(!StringUtil.isEmpty(roleName),Role::getRoleName,roleName));
    }
}
