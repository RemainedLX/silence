package top.remained.silence.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.mapstruct.Mapper;
import top.remained.silence.model.acl.RolePermission;

import java.util.List;
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    int insertList(@Param("roleId") Long roleId,@Param("permissionIds") List<Long> permissionIds);
}
