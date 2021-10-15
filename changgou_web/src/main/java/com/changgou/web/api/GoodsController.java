package com.changgou.web.api;

import com.changgou.domain.Goods;
import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.goods.service.BrandService;
import com.changgou.goods.service.impl.GoodsService;
import com.changgou.pojo.TbBrand;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.changgou.constant.ServiceConstants.PATH_V2;

/**
 * 商品api
 * @author: hui.jin
 * @date: 2021/9/8 17:31
 */
@Slf4j
@Api("商品接口")
@RestController
@RequestMapping(value = PATH_V2)
@RequiredArgsConstructor
public class GoodsController {

    private GoodsService goodsService;

    /**
     * 新增商品
     * @return
     */
    @ApiOperation("新增商品")
    @PostMapping(value = "goods/add/")
    public Result<Boolean> addGoods(@RequestBody Goods goods){

        return Result.booleanResule(goodsService.addGoods(goods));
    }

    /**
     * 根据商品id查询商品
     * @return
     */
    @ApiOperation("根据商品id查询商品")
    @PostMapping(value = "goods/find/{id}")
    public Result<Goods> findGoodsById(@PathVariable String id){

        return Result.success("查询成功", goodsService.findGoodsById(id));
    }

    /**
     * 根据商品修改商品
     * @return
     */
    @ApiOperation("根据商品修改商品")
    @PostMapping(value = "goods/update/")
    public Result<Boolean> updateGoods(@RequestBody Goods goods){

        return Result.booleanResule(goodsService.updateGoods(goods));
    }

    /**
     * 审核商品
     * @return
     */
    @ApiOperation("审核商品")
    @PostMapping(value = "goods/examine/{id}")
    public Result<Boolean> examineGoods(@PathVariable String id){

        return Result.booleanResule(goodsService.examineGoods(id));
    }

    /**
     * 上架商品
     * @return
     */
    @ApiOperation("上架商品")
    @PostMapping(value = "goods/putSell/{id}")
    public Result<Boolean> putSellGoods(@PathVariable String id){

        return Result.booleanResule(goodsService.putSellGoods(id));
    }

    /**
     * 下架商品
     * @return
     */
    @ApiOperation("下架商品")
    @PostMapping(value = "goods/offSell/{id}")
    public Result<Boolean> offSellGoods(@PathVariable String id){

        return Result.booleanResule(goodsService.offSellGoods(id));
    }

    /**
     * 逻辑删除商品
     * @return
     */
    @ApiOperation("逻辑删除商品")
    @PostMapping(value = "goods/logicDelete/{id}")
    public Result<Boolean> logicDeleteGoods(@PathVariable String id){

        return Result.booleanResule(goodsService.logicDeleteGoods(id));
    }

    /**
     * 还原删除的商品
     * @return
     */
    @ApiOperation("还原删除的商品")
    @PostMapping(value = "goods/reduction/{id}")
    public Result<Boolean> reductionGoods(@PathVariable String id){

        return Result.booleanResule(goodsService.reductionGoods(id));
    }

    /**
     * 物理删除商品
     * @return
     */
    @ApiOperation("下架商品")
    @PostMapping(value = "goods/diskDelete/{id}")
    public Result<Boolean> diskDeleteGoods(@PathVariable String id){

        return Result.booleanResule(goodsService.diskDeleteGoods(id));
    }

}
