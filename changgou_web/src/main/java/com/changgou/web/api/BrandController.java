package com.changgou.web.api;

import com.changgou.goods.service.BrandService;
import com.changgou.pojo.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * 品牌信息
 * @author: hui.jin
 * @date: 2021/9/8 17:31
 */
@Slf4j
@Api("特征接口")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class BrandController {

    private BrandService brandService;

    /**
     * 查询所有的品牌列表
     * @return
     */
    public List<Brand> findBrandAll(){
        return brandService.findAll();
    }
}
