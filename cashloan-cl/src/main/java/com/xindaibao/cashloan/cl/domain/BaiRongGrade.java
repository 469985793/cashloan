package com.xindaibao.cashloan.cl.domain;

import com.xindaibao.cashloan.cl.sdk.bairong.model.BaiRongRradeRespDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 百融评分实体
 */
@Getter
@Setter
@NoArgsConstructor
 public class BaiRongGrade extends BaiRongRradeRespDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    private Long userId;

    private String remark;

    private Date createTime;

   public static BaiRongGrade of(Long userId){
      BaiRongGrade baiRongGrade = new BaiRongGrade();
      baiRongGrade.setUserId(userId);
      baiRongGrade.setCreateTime(new Date());
      return baiRongGrade;
   }

}