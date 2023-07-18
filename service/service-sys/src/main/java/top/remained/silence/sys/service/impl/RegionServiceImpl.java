package top.remained.silence.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.remained.silence.model.sys.Region;
import top.remained.silence.sys.mapper.RegionMapper;
import top.remained.silence.sys.service.RegionService;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author lx
 * @since 2023-07-18
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

}
