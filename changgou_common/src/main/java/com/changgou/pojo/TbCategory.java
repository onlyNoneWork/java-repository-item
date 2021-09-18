package com.changgou.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: hui.jin
 * @date: 2021/9/17 18:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_category")
public class TbCategory {

    private int id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 商品数量
     */
    @Column(name = "goods_num")
    private int goodsNum;

    /**
     * 是否显示
     */
    @Column(name = "is_show")
    private String isShow;

    /**
     * 是否导航
     */
    @Column(name = "is_menu")
    private String isMenu;

    /**
     * 排序
     */
    private int seq;

    /**
     * 上级id
     */
    @Column(name = "parend_id")
    private int parendId;

    /**
     * 模板id
     */
    @Column(name = "template_id")
    private int templateId;
}
