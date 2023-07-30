package top.remained.silence.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.remained.silence.model.product.SkuPoster;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-04-04
 */
public interface SkuPosterService extends IService<SkuPoster> {

    //根据id查询商品海报列表
    List<SkuPoster> getPosterListBySkuId(Long id);
}
