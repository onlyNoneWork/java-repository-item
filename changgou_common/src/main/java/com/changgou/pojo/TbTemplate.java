package com.changgou.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 模板表
 * @author: hui.jin
 * @date: 2021/9/17 18:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_template")
public class TbTemplate {

    /**
     * 模板id
     */
    private Integer id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 规格数量
     */
    @Column(name = "spec_num")
    private Integer specNum;

    /**
     * 参数数量
     */
    @Column(name = "para_num")
    private Integer paraNum;
}
