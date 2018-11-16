package com.xindaibao.cashloan.cl.sdk.operator.tongdun.model;

/**
 * @author
 * @Description:
 * @Date: Created in 2017/9/14 上午12:17
 * @Modified:
 */
public class CreditLoanTDModel {

    private MobileInfo mobileInfo;
    private UserInfo userInfo;
    private InfoMatch infoMatch;

    public class InfoMatch{

        /**
         * real_name_check_yys : 不匹配
         * identity_code_check_yys : 模糊匹配
         */

        private String real_name_check_yys;
        private String identity_code_check_yys;

        public String getReal_name_check_yys() {
            return real_name_check_yys;
        }

        public void setReal_name_check_yys(String real_name_check_yys) {
            this.real_name_check_yys = real_name_check_yys;
        }

        public String getIdentity_code_check_yys() {
            return identity_code_check_yys;
        }

        public void setIdentity_code_check_yys(String identity_code_check_yys) {
            this.identity_code_check_yys = identity_code_check_yys;
        }
    }
}
