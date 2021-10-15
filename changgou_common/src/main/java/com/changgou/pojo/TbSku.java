package com.changgou.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * stock keeping unit 库存量单位
 * @author: hui.jin
 * @date: 2021/10/14 13:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_sku")
public class TbSku {

    @Id
    private String id ;

    /**
     * 商品条码
     */
    @Column(name = "sn")
    private String sn;

    /**
     * sku名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 价格
     */
    @Column(name = "price")
    private Integer price;

    /**
     * 库存量
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 预警库存量
     */
    @Column(name = "alert_num")
    private Integer alertNum;

    /**
     * 商品图片
     */
    @Column(name = "image")
    private String image;

    /**
     * 商品图片列表
     */
    @Column(name = "images")
    private String images;

    /**
     * 重量
     */
    @Column(name = "weight")
    private Integer weight;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * spuid
     */
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * 类目id
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 品牌名称
     */
    @Column(name = "brand_name")
    private String brandName;

    /**
     * 规格
     */
    @Column(name = "spec")
    private String spec;

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
     * 状态  1-正常, 2-下架, 3-删除
     */
    @Column(name = "status")
    private String status;
}
