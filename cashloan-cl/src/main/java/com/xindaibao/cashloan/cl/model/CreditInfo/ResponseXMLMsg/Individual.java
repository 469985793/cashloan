package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Individual {

    private com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.Contact contact;

    private com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.General general;

    private com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.Identifications identifications;

    private com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.MainAddress mainAddress;

    private com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.SecondaryAddress secondaryAddress;

    public com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.Contact getContact() {
        return contact;
    }

    public void setContact(com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.Contact contact) {
        this.contact = contact;
    }

    public com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.General getGeneral() {
        return general;
    }

    public void setGeneral(com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.General general) {
        this.general = general;
    }

    public com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.Identifications getIdentifications() {
        return identifications;
    }

    public void setIdentifications(com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.Identifications identifications) {
        this.identifications = identifications;
    }

    public com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.MainAddress getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.MainAddress mainAddress) {
        this.mainAddress = mainAddress;
    }

    public com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.SecondaryAddress getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg._Individual.SecondaryAddress secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }
}
