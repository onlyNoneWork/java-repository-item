package com.changgou.web.api;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.goods.service.BrandService;
import com.changgou.pojo.Brand;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.websocket.server.PathParam;
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
public class BrandController{

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

    /**
     * 多条件分页查询品牌
     * @param brand 品牌
     * @param page 第几页
     * @param size 每页的数量
     * @return
     */
    @ApiOperation("多条件查询品牌")
    @GetMapping(value = "brand/selectBrands")
    public Result findBrandBy(@RequestBody Brand brand, @RequestBody Integer page, @RequestBody Integer size) {

        PageHelper.startPage(page, size);
        Page<Brand> brandPage = (Page<Brand>) brandService.findBrandBy(brand);
        PageResult result = new PageResult(brandPage.getTotal(), brandPage.getResult());

        return Result.success("查询成功", result);
    }

    /**
     * 通过商品分类获取品牌列表
     * @param name 商品分类
     * @return
     */
    @ApiOperation("多条件查询品牌")
    @GetMapping(value = "brandByCate/{name}")
    public Result findBrandByCate(@PathVariable String name) {

        return Result.success("查询成功", brandService.findBrandByCate(name));
    }

    /**
     * 新增品牌
     * @param brand 品牌
     * @return
     */
    @ApiOperation("新增品牌")
    @PostMapping(value = "brand/add")
    public Result brandAdd(@RequestBody Brand brand) {
        return Result.booleanResule(brandService.brandAdd(brand));
    }

    /**
     * 修改品牌
     * @param brand 品牌
     * @return
     */
    @ApiOperation("修改品牌")
    @PostMapping(value = "brand/update")
    public Result brandUpdate(@RequestBody Brand brand) {
        return Result.booleanResule(brandService.brandUpdate(brand));
    }

    /**
     * 删除品牌
     * @param id 品牌id
     * @return
     */
    @ApiOperation("删除品牌")
    @PostMapping(value = "brandDelete/{id}")
    public Result brandDelete(@PathVariable Integer id) {
        return Result.booleanResule(brandService.brandDelete(id));
    }
}
