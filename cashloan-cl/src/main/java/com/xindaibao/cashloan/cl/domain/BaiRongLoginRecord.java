package com.xindaibao.cashloan.cl.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by xiezhao on 2017/10/21.
 */
@Getter
@Setter
@NoArgsConstructor
public class BaiRongLoginRecord {

    private Long id;
    private String tokenId;
    private Date requestTime;
    private Date expireTime;
}
