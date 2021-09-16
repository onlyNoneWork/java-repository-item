package com.changgou.goods.service.impl;

import com.changgou.goods.mapper.BrandMapper;
import com.changgou.goods.service.BrandService;
import com.changgou.pojo.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌列表的逻辑处理
 * @author: hui.jin
 * @date: 2021/9/8 17:24
 */
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private BrandMapper brandMapper;

    /**
     * 查询所有的品牌列表
     * @return
     */
    @Override
    public List<Brand> findAll() {
        List<Brand> brands = brandMapper.selectAll();
        return brands;
    }

    /**
     * 通过id查询品牌
     * @param id
     * @return
     */
    @Override
    public Brand findBrandById(Integer id) {
        return brandMapper.selectOne(Brand.builder().id(id).build());
    }

}
