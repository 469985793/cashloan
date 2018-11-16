package com.xindaibao.cashloan.cl.model.mohe;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Getter
public enum MoheRelationEnum {
    FATHER("父亲"),
    MOTHER("母亲"),
    SPOUSE("配偶"),
    CHILD("子女"),
    OTHER_RELATIVE("其他亲属"),
    FRIEND("朋友"),
    COWORKER("同事"),
    OTHERS("其他关系");

    private String desc;


    public static MoheRelationEnum matchByDesc(String desc) {
        if (StringUtils.isEmpty(desc)) {
            return OTHERS;
        }

        desc = StringUtils.trim(desc);
        for (MoheRelationEnum relationEnum : MoheRelationEnum.values()) {
            if (StringUtils.equals(desc, relationEnum.getDesc())) {
                return relationEnum;
            }
        }
        return OTHERS;
    }

}
