package top.remained.silence.vo.product;

import top.remained.silence.model.product.SkuAttrValue;
import top.remained.silence.model.product.SkuImage;
import top.remained.silence.model.product.SkuInfo;
import top.remained.silence.model.product.SkuPoster;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SkuInfoVo extends SkuInfo {

	@ApiModelProperty(value = "海报列表")
	private List<SkuPoster> skuPosterList;

	@ApiModelProperty(value = "属性值")
	private List<SkuAttrValue> skuAttrValueList;

	@ApiModelProperty(value = "图片")
	private List<SkuImage> skuImagesList;

}

