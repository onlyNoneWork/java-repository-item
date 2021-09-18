package com.changgou.goods.service.impl;

import com.changgou.exception.NullParamException;
import com.changgou.goods.mapper.BrandMapper;
import com.changgou.goods.service.BrandService;
import com.changgou.pojo.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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

    /**
     * 新建品牌
     * @param brand 品牌
     * @return
     */
    @Override
    public Boolean brandAdd(Brand brand) {

        String check = checkBrand(brand);
        if (StringUtils.hasLength(check)) {
            throw new NullParamException(check);
        }

        int i = brandMapper.insert(brand);

        return i == 1;
    }

    /**
     * 修改品牌
     * @param brand
     * @return
     */
    @Override
    public Boolean brandUpdate(Brand brand) {

        if (brand.getId() <= 0) {
            throw new NullParamException("品牌id");
        }

        int i = brandMapper.updateByPrimaryKeySelective(brand);
        return i == 1;
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @Override
    public Boolean brandDelete(Integer id) {

        int i = brandMapper.delete(Brand.builder().id(id).build());

        return i == 1;
    }

    @Override
    public List<Brand> findBrandBy(Brand brand) {

        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.hasLength(brand.getName())) {
            criteria.andLike("name", "%"+brand.getName()+"%");
        }
        if (StringUtils.hasLength(brand.getLetter())) {
            criteria.andEqualTo("letter", brand.getLetter());
        }

        return brandMapper.selectByExample(example);
    }

    /**
     * 通过商品分类获取品牌列表
     * @param name 商品分类
     * @return 品牌分类
     */
    @Override
    public List<Brand> findBrandByCate(String name) {

        return brandMapper.selectBrandByCate(name);
    }

    /**
     * 校验品牌
     * @param brand
     * @return
     */
    private String checkBrand(Brand brand) {
        String check = "";
        if (!StringUtils.hasLength(brand.getImage())) {
            check = "品牌图片地址";
        }
        if (!StringUtils.hasLength(brand.getName())) {
            check = "品牌名称";
        }
        if (!StringUtils.hasLength(brand.getLetter())) {
            check = "品牌的首字母";
        }
        if (brand.getSeq() < 1) {
            check = "排序";
        }

        return check;
    }


}
