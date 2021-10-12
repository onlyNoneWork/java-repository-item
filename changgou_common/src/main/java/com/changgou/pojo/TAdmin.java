package com.changgou.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author: hui.jin
 * @date: 2021/10/12 13:48
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TAdmin {

    @Id
    private long id;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登录密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;
}
