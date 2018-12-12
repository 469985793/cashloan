package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

/**
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/2
 */
public class PersonalInformation {

    //CreditInfo 返回登记的真实姓名
    private String FullName;

    //CreditInfo 返回登记的出生年月
    private String DateOfBirth;

    //年龄
    private String Age;

    //    性别
    private String Gender;

    //    是否结婚
    private String MaritalStatus;

    private String EmploymentStatus;

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }

    public String getEmploymentStatus() {
        return EmploymentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        EmploymentStatus = employmentStatus;
    }
}
