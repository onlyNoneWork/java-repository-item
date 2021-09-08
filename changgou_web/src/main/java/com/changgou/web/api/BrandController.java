package com.changgou.web.api;

import com.changgou.goods.service.BrandService;
import com.changgou.pojo.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: hui.jin
 * @date: 2021/9/8 17:31
 */
@Slf4j
@RestController
@RequestMapping

@Controller
@RequiredArgsConstructor
public class BrandController {

    private BrandService brandService;


    public List<Brand> findBrandAll(){
        return brandService.findAll();
    }
}
