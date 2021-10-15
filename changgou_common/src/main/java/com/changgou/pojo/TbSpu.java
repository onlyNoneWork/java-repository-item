package com.changgou.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * standard product unit 标准商品单位
 * @author: hui.jin
 * @date: 2021/10/14 10:53
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_spu")
public class TbSpu {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 货号
     */

    private String sn;

    /**
     * spu商品名
     */
    private String name;

    /**
     * 副标题
     */
    private String caption;

    /**
     * 品牌Id
     */
    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * 一级分类
     */
    @Column(name = "category_id")
    private Integer category1Id;

    /**
     * 二级分类
     */
    @Column(name = "category_id")
    private Integer category2Id;

    /**
     * 三级分类
     */
    @Column(name = "category_id")
    private Integer category3Id;

    /**
     * 模板id
     */
    @Column(name = "template_id")
    private Integer templateId;

    /**
     * 运费id
     */
    @Column(name = "freight_id")
    private Integer freightId;

    /**
     * 图片
     */
    private String image;

    /**
     * 图片列表
     */
    private String images;

    /**
     * 售后服务
     */
    @Column(name = "sale_service")
    private String saleService;

    /**
     * 介绍
     */
    @Column(name = "introduction")
    private String introduction;

    /**
     * 规格列表
     */
    @Column(name = "spec_items")
    private String specItems;

    /**
     * 参数列表
     */
    @Column(name = "para_items")
    private String paraItems;

    /**
     * 销量
     */
    @Column(name = "sale_num")
    private Integer saleNum;

    /**
     * 评论数
     */
    @Column(name = "comment_num")
    private Integer commentNum;

    /**
     * 是否上架 0-下架, 1-上架
     */
    @Column(name = "is_marketable")
    private String isMarketable;

    /**
     * 是否启用规格
     */
    @Column(name = "is_enable_spec")
    private String isEnableSpec;

    /**
     * 是否删除 0-正常, 1-删除
     */
    @Column(name = "is_delete")
    private String isDelete;

    /**
     * 状态 0-初始化状态(待审核),1-审核后的上架状态
     */
    private String status;

}
