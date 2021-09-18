package com.changgou.web.api;

import com.changgou.entity.Result;
import com.changgou.goods.service.SpecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.changgou.constant.ServiceConstants.PATH_V2;

/**
 * 规格表controller
 *
 * @author: hui.jin
 * @date: 2021/9/18 11:13
 */
@Slf4j
@Api(value = "规格的接口")
@RestController
@RequestMapping(value = PATH_V2)
@RequiredArgsConstructor
public class SpecController {

    private SpecService specService;

    @ApiOperation(value = "通过商品分类获取规格")
    @GetMapping(value = "specByCate/{name}")
    public Result findSpecByCate(@PathVariable String name){
        return new Result(true,200,"查询成功", specService.findSpecByCate(name));
    }

}
