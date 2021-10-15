package com.changgou.goods.mapper;

import com.changgou.goods.config.MyMapper;
import com.changgou.pojo.TbBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: jin.hui
 * @date: 2021/9/8 14:12
 */
public interface BrandMapper extends MyMapper<TbBrand> {

    List<TbBrand> selectBrandByCate(@Param("name") String name);
}
