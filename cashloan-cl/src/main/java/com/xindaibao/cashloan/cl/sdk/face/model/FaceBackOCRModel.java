package com.xindaibao.cashloan.cl.sdk.face.model;

import java.util.List;

/**
 * 背面身份证信息
 * Created by junlou.liu on 2017/9/8.
 */
public class FaceBackOCRModel {

    /**
     * cards : [{"issued_by":"北京市公安局海淀分局","side":"back","valid_date":"2010.11.13-2020.11.13"}]
     * time_used : 2151
     * request_id : 1473759244,40dfde25-6d1a-4c90-a994-813556c81e30
     */

    private int time_used;
    private String request_id;
    private String error_message;
    private List<CardsBean> cards;

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public List<CardsBean> getCards() {
        return cards;
    }

    public void setCards(List<CardsBean> cards) {
        this.cards = cards;
    }

    public static class CardsBean {
        /**
         * issued_by : 北京市公安局海淀分局
         * side : back
         * valid_date : 2010.11.13-2020.11.13
         */

        private String issued_by;
        private String side;
        private String valid_date;

        public String getIssued_by() {
            return issued_by;
        }

        public void setIssued_by(String issued_by) {
            this.issued_by = issued_by;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public String getValid_date() {
            return valid_date;
        }

        public void setValid_date(String valid_date) {
            this.valid_date = valid_date;
        }
    }
}
