package com.changgou.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 规格表
 * @author: hui.jin
 * @date: 2021/9/17 18:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_spec")
public class TbSpec {

    /**
     * 规格id
     */
    private Integer id;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 规格选项
     */
    private String options;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 模板id
     */
    @Column(name = "template_id")
    private Integer templateId;
}
