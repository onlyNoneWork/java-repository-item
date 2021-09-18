package com.changgou.goods.service;

import com.changgou.pojo.TbSpec;

import java.util.List;

/**
 * @author: jin.hui
 * @date: 2021/9/18 11:26
 */
public interface SpecService {


    List<TbSpec> findSpecByCate(String name);
}
