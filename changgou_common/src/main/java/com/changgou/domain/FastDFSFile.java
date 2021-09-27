package com.changgou.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: hui.jin
 * @date: 2021/9/27 16:20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FastDFSFile {

    /**
     * 文件名字
     */
    private String name;

    /**
     * 文件内容
     */
    private byte[] context;

    /**
     * 文件扩展名
     */
    private String ext;

    /**
     * 文件MD5摘要值
     */
    private String md5;

    /**
     * 文件创建作者
     */
    private String author;

}
