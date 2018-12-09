package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
public class Individual {

    private model.CreditInfo.ResponseXMLMsg._Individual.Contact contact;

    private model.CreditInfo.ResponseXMLMsg._Individual.General general;

    private model.CreditInfo.ResponseXMLMsg._Individual.Identifications identifications;

    private model.CreditInfo.ResponseXMLMsg._Individual.MainAddress mainAddress;

    private model.CreditInfo.ResponseXMLMsg._Individual.SecondaryAddress secondaryAddress;

    public model.CreditInfo.ResponseXMLMsg._Individual.Contact getContact() {
        return contact;
    }

    public void setContact(model.CreditInfo.ResponseXMLMsg._Individual.Contact contact) {
        this.contact = contact;
    }

    public model.CreditInfo.ResponseXMLMsg._Individual.General getGeneral() {
        return general;
    }

    public void setGeneral(model.CreditInfo.ResponseXMLMsg._Individual.General general) {
        this.general = general;
    }

    public model.CreditInfo.ResponseXMLMsg._Individual.Identifications getIdentifications() {
        return identifications;
    }

    public void setIdentifications(model.CreditInfo.ResponseXMLMsg._Individual.Identifications identifications) {
        this.identifications = identifications;
    }

    public model.CreditInfo.ResponseXMLMsg._Individual.MainAddress getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(model.CreditInfo.ResponseXMLMsg._Individual.MainAddress mainAddress) {
        this.mainAddress = mainAddress;
    }

    public model.CreditInfo.ResponseXMLMsg._Individual.SecondaryAddress getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(model.CreditInfo.ResponseXMLMsg._Individual.SecondaryAddress secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }
}
