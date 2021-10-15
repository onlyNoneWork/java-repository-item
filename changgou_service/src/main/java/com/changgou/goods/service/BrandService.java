package com.changgou.goods.service;

import com.changgou.pojo.TbBrand;

import java.util.List;

/**
 * 品牌的service
 * @author: jin.hui
 * @date: 2021/9/8 17:22
 */
public interface BrandService {

    List<TbBrand> findAll();

    TbBrand findBrandById(Integer id);

    Boolean brandAdd(TbBrand brand);

    Boolean brandUpdate(TbBrand brand);

    Boolean brandDelete(Integer id);

    List<TbBrand> findBrandBy(TbBrand brand);

    List<TbBrand> findBrandByCate(String name);
}
