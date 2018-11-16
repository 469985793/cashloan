package com.xindaibao.cashloan.cl.sdk.face.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 正面身份证信息
 * Created by junlou.liu on 2017/9/8.
 */
public class FaceFrontOCRModel {

    /**
     * cards : [{"gender":"女","name":"牛XX","id_card_number":"XXXXXX19841013XXXX","birthday":"1984-10-13","race":"汉","address":"广东省深圳市XXXXXXXX","legality":{"Edited":0.001,"Photocopy":0,"ID Photo":0.502,"Screen":0.496,"Temporary ID Photo":0},"type":1,"side":"front"}]
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
         * gender : 女
         * name : 牛XX
         * id_card_number : XXXXXX19841013XXXX
         * birthday : 1984-10-13
         * race : 汉
         * address : 广东省深圳市XXXXXXXX
         * legality : {"Edited":0.001,"Photocopy":0,"ID Photo":0.502,"Screen":0.496,"Temporary ID Photo":0}
         * type : 1
         * side : front
         */

        private String gender;
        private String name;
        private String id_card_number;
        private String birthday;
        private String race;
        private String address;
        private LegalityBean legality;
        private int type;
        private String side;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId_card_number() {
            return id_card_number;
        }

        public void setId_card_number(String id_card_number) {
            this.id_card_number = id_card_number;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public LegalityBean getLegality() {
            return legality;
        }

        public void setLegality(LegalityBean legality) {
            this.legality = legality;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public static class LegalityBean {
            /**
             * Edited : 0.001
             * Photocopy : 0.0
             * ID Photo : 0.502
             * Screen : 0.496
             * Temporary ID Photo : 0.0
             */

            private double Edited;
            private double Photocopy;
            @JSONField(name = "ID Photo")
            private double _$IDPhoto278; // FIXME check this code
            private double Screen;
            @JSONField(name = "Temporary ID Photo")
            private double _$TemporaryIDPhoto167; // FIXME check this code

            public double getEdited() {
                return Edited;
            }

            public void setEdited(double Edited) {
                this.Edited = Edited;
            }

            public double getPhotocopy() {
                return Photocopy;
            }

            public void setPhotocopy(double Photocopy) {
                this.Photocopy = Photocopy;
            }

            public double get_$IDPhoto278() {
                return _$IDPhoto278;
            }

            public void set_$IDPhoto278(double _$IDPhoto278) {
                this._$IDPhoto278 = _$IDPhoto278;
            }

            public double getScreen() {
                return Screen;
            }

            public void setScreen(double Screen) {
                this.Screen = Screen;
            }

            public double get_$TemporaryIDPhoto167() {
                return _$TemporaryIDPhoto167;
            }

            public void set_$TemporaryIDPhoto167(double _$TemporaryIDPhoto167) {
                this._$TemporaryIDPhoto167 = _$TemporaryIDPhoto167;
            }
        }
    }
}
