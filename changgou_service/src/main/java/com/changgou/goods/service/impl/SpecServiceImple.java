package com.changgou.goods.service.impl;

import com.changgou.goods.mapper.TbSpecMapper;
import com.changgou.goods.service.SpecService;
import com.changgou.pojo.TbSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格的servie
 * @author: hui.jin
 * @date: 2021/9/18 11:26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SpecServiceImple implements SpecService {

    private TbSpecMapper specMapper;

    @Override
    public List<TbSpec> findSpecByCate(String name) {
        return specMapper.findSpecByCate(name);
    }
}
