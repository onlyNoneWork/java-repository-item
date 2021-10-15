package com.changgou.domain;

import com.changgou.pojo.TbSku;
import com.changgou.pojo.TbSpu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: hui.jin
 * @date: 2021/10/14 16:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    private TbSpu tbSpu;

    private List<TbSku> skuList;
}
