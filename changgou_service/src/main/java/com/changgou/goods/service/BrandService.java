package com.changgou.goods.service;

import com.changgou.pojo.Brand;

import java.util.List;

/**
 * 品牌的service
 * @author: jin.hui
 * @date: 2021/9/8 17:22
 */
public interface BrandService {

    List<Brand> findAll();

    Brand findBrandById(Integer id);

    Boolean brandAdd(Brand brand);

    Boolean brandUpdate(Brand brand);

    Boolean brandDelete(Integer id);

    List<Brand> findBrandBy(Brand brand);

    List<Brand> findBrandByCate(String name);
}
