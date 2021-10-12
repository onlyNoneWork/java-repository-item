package com.changgou.web.api;

import com.changgou.domain.Admin;
import com.changgou.entity.Result;
import com.changgou.goods.service.impl.LoginServiceImpl;
import com.changgou.pojo.TAdmin;
import com.changgou.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.changgou.constant.ServiceConstants.PATH_V2;

/**
 * @author: hui.jin
 * @date: 2021/10/12 14:00
 */
@Slf4j
@Api
@RestController
@RequestMapping(value = PATH_V2)
@RequiredArgsConstructor
public class LoginController {

    private LoginServiceImpl loginService;

    /**
     * 新增管理员
     * @param admin
     * @return
     */
    @ApiOperation(value = "新增管理员")
    @PostMapping(value = "add/")
    public Result<Boolean> addAdmin(@RequestBody Admin admin){

        return Result.booleanResule(loginService.addAdmin(admin));
    }

    @ApiOperation(value = "登录")
    @PostMapping(value = "login/")
    public Result<Boolean> login(@RequestBody Admin admin){

        Boolean login = loginService.login(admin);
        if (login) {
            //登录验证成功,获取token并返回
            Map<String, String> map = new HashMap<>();
            String token = JWTUtil.createJWT(UUID.randomUUID().toString(), admin.getLoginName(), null);
            map.put("username", admin.getLoginName());
            map.put("token", token);
            return Result.success("登录成功", map);
        } else {
            //登录验证失败
            return Result.booleanResule(login);
        }
    }


}
