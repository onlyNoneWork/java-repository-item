package com.changgou.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 品牌对象
 * @author: hui.jin
 * @date: 2021/9/8 14:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_brand")
public class TbBrand implements Serializable {

    /**
     * 品牌 id
     */
    @Id
    private int id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌图片地址
     */
    private String image;

    /**
     * 品牌的首字母
     */
    private String letter;

    /**
     * 排序
     */
    private Integer seq;
}
