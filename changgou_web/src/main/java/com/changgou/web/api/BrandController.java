package com.changgou.web.api;

import com.changgou.entity.Result;
import com.changgou.goods.service.BrandService;
import com.changgou.pojo.Brand;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import java.util.List;

import static com.changgou.constant.ServiceConstants.PATH_V2;

/**
 * 品牌信息
 * @author: hui.jin
 * @date: 2021/9/8 17:31
 */
@Slf4j
@Api("品牌接口")
@RestController
@RequestMapping(value = PATH_V2)
@RequiredArgsConstructor
public class BrandController {

    private BrandService brandService;

    /**
     * 查询所有的品牌列表
     * @return
     */
    @ApiOperation("品牌列表")
    @GetMapping(value = "brand/allList")
    public Result findBrandAll(){
        return Result.success("查询成功", brandService.findAll());
    }

    /**
     * 通过id查询品牌
     * @param id 品牌id
     * @return
     */
    @ApiOperation("通过id查询品牌")
    @GetMapping(value = "brand/{id}")
    public Result findBrandById(@PathVariable Integer id) {
        return Result.success("查询成功", brandService.findBrandById(id));
    }
}
