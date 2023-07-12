package top.remained.silence.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.remained.silence.model.acl.Admin;
import top.remained.silence.vo.acl.AdminQueryVo;

public interface AdminService extends IService<Admin> {
    IPage<Admin> selectPageList(Page<Admin> page, AdminQueryVo adminQueryVo);
}
