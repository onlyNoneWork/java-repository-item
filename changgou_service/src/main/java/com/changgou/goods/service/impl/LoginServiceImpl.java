package com.changgou.goods.service.impl;

import com.changgou.domain.Admin;
import com.changgou.goods.mapper.TAdminMapper;
import com.changgou.pojo.TAdmin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * 登录的service
 *
 * @author: hui.jin
 * @date: 2021/10/12 14:07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl {

    private TAdminMapper adminMapper;

    /**
     * 新增登录用户
     * @param admin 登录用户
     * @return
     */
    public Boolean addAdmin(Admin admin) {

        //使用BCrypt对其密码进行加密
        String password = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());

        int insert = adminMapper.insert(TAdmin.builder()
                .loginName(admin.getLoginName())
                .password(password)
                .status("1")
                .build());

        return 1 == insert;
    }

    /**
     * 登录用户
     * @param admin
     * @return
     */
    public Boolean login(Admin admin) {

        //根据用户名称进行判断用户名密码是否正确
        TAdmin tAdmin = adminMapper.selectByPrimaryKey(TAdmin.builder()
                .loginName(admin.getLoginName())
                .status("1")
                .build());

        if (tAdmin == null) {

            return false;
        } else {

            return BCrypt.checkpw(admin.getPassword(), tAdmin.getPassword());
        }

    }
}
