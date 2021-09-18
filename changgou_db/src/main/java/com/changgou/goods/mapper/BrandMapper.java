package com.changgou.goods.mapper;

import com.changgou.goods.config.MyMapper;
import com.changgou.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: jin.hui
 * @date: 2021/9/8 14:12
 */
public interface BrandMapper extends MyMapper<Brand> {

    List<Brand> selectBrandByCate(@Param("name") String name);
}
