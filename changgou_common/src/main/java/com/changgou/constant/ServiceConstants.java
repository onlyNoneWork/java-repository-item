package com.changgou.constant;

public interface ServiceConstants {

    /**
     * 不需要鉴权
     */
    String PATH = "/chang_gou/v1/";

    /**
     * 需要鉴权
     */
    String PATH_V2 = "/chang_gou/v2/";

    /**
     * 正常
     */
    Integer STATUS_NORMAL = 1;

    /**
     * 删除
     */
    Integer STATUS_DEL = -1;

    /**
     * 初始化中
     */
    Integer STATUS_INIT = 0;

    String HEADER = "token";

}
