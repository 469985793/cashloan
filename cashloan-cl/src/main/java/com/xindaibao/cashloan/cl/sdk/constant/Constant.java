package com.xindaibao.cashloan.cl.sdk.constant;

/**
 * Created by junlou.liu on 2017/9/7.
 */
public class Constant {
    // api_key和api_secret不匹配,注：不返回request_id和time_used
    public static final int FACE_AUTHENTICATION_ERROR = 401;
    // api_key没有调用本API的权限，具体原因为：用户自己禁止该api_key调用、管理员禁止该api_key调用、由于账户余额不足禁止调用
    // Denied by Client(用户自己禁止该api_key调用）,Denied by Admin（管理员禁止该api_key调用,Insufficient Account Balance（由于账户余额不足禁止调用）
    public static final int FACE_AUTHORIZATION_ERROR = 403;
    // 并发数超过限制,
    public static final int FACE_CONCURRENCY_LIMIT_EXCEEDED = 403;
    // 缺少某个必选参数
    public static final int FACE_MISSING_ARGUMENTS = 400;
    // 某个参数解析出错（比如必须是数字，但是输入的是非数字字符串; 或者长度过长，etc.）
    public static final int FACE_BAD_ARGUMENTS = 400;
    // 同时传入了要求是二选一或多选一的参数。如有特殊说明则不返回此错误
    public static final int FACE_COEXISTENCE_ARGUMENTS = 400;
    // 客户发送的请求大小超过了2MB限制。该错误的返回格式为纯文本，不是json格式。Request Entity Too Large
    public static final int FACE_REQUEST_ENTITY_TOO_LARGE = 413;
    // 所调用的API不存在
    public static final int FACE_API_NOT_FOUND = 404;
    // 服务器内部错误，当此类错误发生时请再次请求，如果持续出现此类错误，请及时联系技术支持团队。
    public static final int FACE_INTERNAL_ERROR = 500;

    public static final int ERROR_CODE = 400;
    public static final int SUCCESS_CODE = 200;

    public static final String ERROR_REQUEST_MESSAGE = "请求发送短信验证码失败";
    public static final String SUCCESS_REQUEST_MESSAGE = "请求发送短信验证码成功";

    public static final String ERROR_IO_EXCEPTION = "IO异常";
    public static final String ERROR_IO_CLOSE_EXCEPTION = "操作文件关闭异常";
    public static final String ERROR_EXCEPTION = "系统异常";

    // 人脸识别
    public static final String FACE_PP_URL = "https://api-cn.faceplusplus.com/facepp/v3/detect";
    // 证件识别
    public static final String FACE_CARD_URL = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard";
    // 网易云信验证码
    public static final String NET_EASE_VALIDATE_URL = "https://api.netease.im/sms/sendcode.action";
    // 网易云信通知
    public static final String NET_EASE_NOTIFY_URL = "https://api.netease.im/sms/sendtemplate.action";
    // 网易云信运营
    public static final String NET_EASE_OPERATE_URL = "https://api.netease.im/sms/sendtemplate.action";

    // 同盾运营商接口地址，需添加 合作方标识、合作方密钥
    public static final String TD_OPERATE_URL = "https://api.shujumohe.com/octopus/report.task.query/v2?partner_code=%s&partner_key=%s";

    public static final String TEMP_API_KEY = "fD11YY_jxgUb_aU8vVcErbezTO8TKeIC";
    public static final String TEMP_CECRET_KEY = "9p71BvUGNT8cpeflf7QiV7RadmnQYstk";


    /**
     * 请求体类型
     */
    public static class ENTITY_TYPE {
        public static final String BASIC_HTTP_ENTITY = "basic";
        public static final String BUFFERED_HTTP_ENTITY = "buffered";
        public static final String BYTE_ARRAY_ENTITY = "byteArray";
        public static final String ENTITY_TEMPLATE = "entityTemplate";
        public static final String FILE_ENTITY = "file";
        public static final String HTTP_ENTITY_WRAPPER = "wrapper";
        public static final String INPUT_STREAM_ENTITY = "inputstream";
        public static final String SERIALIZABLE_ENTITY = "serialise";
        public static final String STRING_ENTITY = "string";
        public static final String URL_ENCODED_FORM_ENTITY = "urlencodedform";
    }
}
