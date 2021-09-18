package com.changgou.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 商品和品牌的关系表
 *
 * @author: hui.jin
 * @date: 2021/9/17 18:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_category_brand")
public class TbCategoryBrand {

    /**
     * 商品id
     */
    @Column(name = "category_id")
    private int categoryId;

    /**
     * 品牌id
     */
    @Column(name = "brand_id")
    private int brandId;
}
