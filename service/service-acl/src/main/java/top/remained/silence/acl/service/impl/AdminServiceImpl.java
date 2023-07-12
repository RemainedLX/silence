package top.remained.silence.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.StringUtil;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.remained.silence.acl.mapper.AdminMapper;
import top.remained.silence.acl.service.AdminService;
import top.remained.silence.model.acl.Admin;
import top.remained.silence.vo.acl.AdminQueryVo;

import java.util.Optional;

/**
 * @Author: lx
 * @CreateTime: 2023-07-12  22:44
 * @Description: TODO
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public IPage<Admin> selectPageList(Page<Admin> page, AdminQueryVo adminQueryVo) {
        Page<Admin> adminPage =null ;
            adminPage = adminMapper.selectPage(page, new LambdaQueryWrapper<Admin>()
                    .eq(StringUtil.isNotEmpty(adminQueryVo.getName()), Admin::getName, adminQueryVo.getName())
                    .eq(StringUtil.isNotEmpty(adminQueryVo.getUsername()), Admin::getUsername, adminQueryVo.getUsername()));

        return  Optional.ofNullable(adminPage).orElse(new Page<>());
    }
}

