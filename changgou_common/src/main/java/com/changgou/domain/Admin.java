package com.changgou.domain;

import lombok.*;

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
public class Admin {

    private long id;

    /**
     * 登录名
     */
    @NonNull
    private String loginName;

    /**
     * 登录密码
     */
    @NonNull
    private String password;

    /**
     * 状态
     */
    private String status;
}
