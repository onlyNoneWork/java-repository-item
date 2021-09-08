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

    @Override
    public List<Brand> findAll() {
        List<Brand> brands = brandMapper.selectAll();
        return brands;
    }

}
