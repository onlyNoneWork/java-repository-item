package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.domain.Goods;
import com.changgou.exception.NullParamException;
import com.changgou.goods.mapper.*;
import com.changgou.pojo.*;
import com.changgou.util.IdWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: hui.jin
 * @date: 2021/10/14 16:04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsService {

    private final TbSkuMapper tbSkuMapper;

    private final TbSpuMapper tbSpuMapper;

    private final BrandMapper brandMapper;

    private final TbCategoryMapper categoryMapper;

    private final TbCategoryBrandMapper categoryBrandMapper;

    private final IdWorker idWorker;

    /**
     * 新增商品
     * @param goods
     * @return
     */
    public Boolean addGoods(Goods goods) {
        TbSpu tbSpu = goods.getTbSpu();

        long id = idWorker.nextId();
        tbSpu.setId(String.valueOf(id));
        tbSpu.setIsDelete("0");
        tbSpu.setStatus("0");
        tbSpu.setIsMarketable("o");

        int spuI = tbSpuMapper.insert(tbSpu);

        int skuI = saveSkuList(goods);

        return spuI == 1 && skuI == goods.getSkuList().size();
    }

    /**
     * 新增sku商品信息
     * 新增分类和品牌的关联信息
     * @param goods
     * @return
     */
    private int saveSkuList(Goods goods) {
        int i = 1;
        TbSpu tbSpu = goods.getTbSpu();

        //获取品牌详情
        TbBrand tbBrand = brandMapper.selectByPrimaryKey(TbBrand
                .builder()
                .id(tbSpu.getBrandId())
                .build());

        //获取分类详情
        TbCategory tbCategory = categoryMapper.selectByPrimaryKey(TbCategory
                .builder()
                .id(tbSpu.getCategory3Id())
                .build());

        //添加分类和品牌之间的关联
        TbCategoryBrand tbCategoryBrand = TbCategoryBrand.builder()
                .brandId(tbBrand.getId())
                .categoryId(tbCategory.getId())
                .build();
        List<TbCategoryBrand> brandList = categoryBrandMapper.select(tbCategoryBrand);
        if (brandList == null || brandList.size() < 1) {

            categoryBrandMapper.insert(tbCategoryBrand);
        }

        List<TbSku> skuList = goods.getSkuList();
        if (skuList != null) {
            skuList.stream().forEach(t -> {
                long l = idWorker.nextId();
                t.setId(String.valueOf(l));

                if (!StringUtils.hasLength(t.getSpec())) {
                    t.setSpec("{}");
                }
                //设置sku名称(商品名称+规格)
                String name = t.getName();
                //将规格装换成json
                Map<String, String> map = JSON.parseObject(t.getSpec(), Map.class);
                if (map != null && map.size()>0) {
                    for (String value : map.values()) {
                        name += value;
                    }
                }
                t.setName(name);
                t.setSpuId(Long.parseLong(tbSpu.getId()));
                t.setCreateTime(new Date());
                t.setUpdateTime(new Date());
                t.setCategoryId(tbCategory.getId());
                t.setCategoryName(tbCategory.getName());
                t.setBrandName(tbBrand.getName());
            });

            i = tbSkuMapper.insertList(skuList);
        }

        return i;
    }

    /**
     * 通过商品id查询商品信息
     * @param id
     * @return
     */
    public Goods findGoodsById(String id) {

        TbSpu spu = tbSpuMapper.selectByPrimaryKey(id);

        List<TbSku> skuList = tbSkuMapper.select(TbSku.builder().spuId(Long.parseLong(id)).build());

        return Goods.builder()
                .tbSpu(spu)
                .skuList(skuList)
                .build();
    }

    /**
     * 修改商品信息
     * @param goods
     * @return
     */
    public Boolean updateGoods(Goods goods) {
        TbSpu tbSpu = goods.getTbSpu();
        int spuI = tbSpuMapper.updateByPrimaryKey(tbSpu);

        //先删除之前的sku再进行新增
        int deleteSku = tbSkuMapper.delete(TbSku.builder().spuId(Long.parseLong(tbSpu.getId())).build());
        if (deleteSku > 0) {
            log.info("修改商品时删除sku--商品id:"+tbSpu.getId()+", 删除个数:" + deleteSku);
        }

        int saveSku = saveSkuList(goods);
        if (saveSku > 0) {
            log.info("修改商品时新增sku: 新增sku个数:"+saveSku);
        }

        return 1 == spuI;
    }

    /**
     * 审核商品
     * @param id
     * @return
     */
    public Boolean examineGoods(String id) {

        TbSpu tbSpu = tbSpuMapper.selectByPrimaryKey(id);
        if (tbSpu == null) {
            throw new NullParamException("审核商品id:"+ id);
        }

        if ("1".equals(tbSpu.getIsDelete())) {
            throw new NullParamException("审核商品名称:"+tbSpu.getName());
        }

        int i = tbSpuMapper.updateByPrimaryKey(TbSpu.builder()
                .id(id)
                .isMarketable("1")
                .status("1")
                .build());
        return 1 == i;
    }

    /**
     * 上架商品
     * @param id
     * @return
     */
    public Boolean putSellGoods(String id) {

        TbSpu tbSpu = tbSpuMapper.selectByPrimaryKey(id);
        if (tbSpu == null) {
            throw new NullParamException("上架商品id:"+ id);
        }

        if ("1".equals(tbSpu.getIsDelete())) {
            throw new NullParamException("上架商品名称:"+tbSpu.getName());
        }

        int i = tbSpuMapper.updateByPrimaryKey(TbSpu.builder()
                .id(id)
                .isMarketable("1")
                .build());
        return 1 == i;
    }

    /**
     * 下架商品
     * @param id
     * @return
     */
    public Boolean offSellGoods(String id) {

        TbSpu tbSpu = tbSpuMapper.selectByPrimaryKey(id);
        if (tbSpu == null) {
            throw new NullParamException("下架商品id:"+ id);
        }

        if ("1".equals(tbSpu.getIsDelete())) {
            throw new NullParamException("下架商品名称:"+tbSpu.getName());
        }

        int i = tbSpuMapper.updateByPrimaryKey(TbSpu.builder()
                .id(id)
                .isMarketable("0")
                .build());
        return 1 == i;
    }

    /**
     * 逻辑删除商品
     * @param id
     * @return
     */
    public Boolean logicDeleteGoods(String id) {

        TbSpu tbSpu = tbSpuMapper.selectByPrimaryKey(id);
        if (tbSpu == null) {
            throw new NullParamException("删除商品id:"+ id);
        }

        if ("1".equals(tbSpu.getIsDelete())) {
            throw new NullParamException("删除商品名称:"+tbSpu.getName());
        }

        int i = tbSpuMapper.updateByPrimaryKey(TbSpu.builder()
                .id(id)
                .isDelete("1")
                .build());
        return 1 == i;
    }

    /**
     * 还原删除的商品
     * @param id
     * @return
     */
    public Boolean reductionGoods(String id) {

        TbSpu tbSpu = tbSpuMapper.selectByPrimaryKey(id);
        if (tbSpu == null) {
            throw new NullParamException("还原删除商品id:"+ id);
        }

        if ("1".equals(tbSpu.getIsDelete())) {
            throw new NullParamException("还原删除商品名称:"+tbSpu.getName());
        }

        int i = tbSpuMapper.updateByPrimaryKey(TbSpu.builder()
                .id(id)
                .isDelete("0")
                .isMarketable("1")
                .build());
        return 1 == i;
    }

    /**
     * 物理删除商品
     * @param id
     * @return
     */
    public Boolean diskDeleteGoods(String id) {
        TbSpu tbSpu = tbSpuMapper.selectByPrimaryKey(id);
        if (tbSpu == null) {
            throw new NullParamException("物理删除商品id:"+ id);
        }

        if (!"1".equals(tbSpu.getIsDelete())) {
            throw new NullParamException("物理删除商品名称:"+tbSpu.getName());
        }

        int i = tbSpuMapper.deleteByPrimaryKey(id);
        return 1 == i;
    }
}
