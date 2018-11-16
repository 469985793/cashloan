package com.xindaibao.cashloan.cl.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 融360评分实体
 */
@Getter
@Setter
@NoArgsConstructor
 public class Rong360Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    private Long userId;

    private Integer score;

    private String tag;

    private String remark;

    private Date createTime;

}