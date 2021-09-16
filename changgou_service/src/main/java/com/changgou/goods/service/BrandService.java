package com.changgou.goods.service;

import com.changgou.pojo.Brand;

import java.util.List;

/**
 * 品牌的service
 * @author: jin.hui
 * @date: 2021/9/8 17:22
 */
public interface BrandService {

    public List<Brand> findAll();

    Brand findBrandById(Integer id);
}
