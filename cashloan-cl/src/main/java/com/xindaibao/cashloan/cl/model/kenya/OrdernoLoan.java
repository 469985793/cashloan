package com.xindaibao.cashloan.cl.model.kenya;

public class OrdernoLoan {
    private Integer id;

    private String indentNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo == null ? null : indentNo.trim();
    }
}