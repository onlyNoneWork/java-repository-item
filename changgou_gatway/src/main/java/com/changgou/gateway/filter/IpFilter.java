package com.changgou.gateway.filter;

import org.springframework.core.Ordered;

/**
 * @author: hui.jin
 * @date: 2021/9/30 15:03
 */
public class IpFilter implements Ordered {



    @Override
    public int getOrder() {
        return 0;
    }
}
