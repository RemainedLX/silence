package top.remained.silence.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;
import top.remained.silence.model.acl.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
