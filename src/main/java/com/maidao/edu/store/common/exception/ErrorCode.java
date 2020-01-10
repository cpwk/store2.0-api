package com.maidao.edu.store.common.exception;

public interface ErrorCode {
    //common
    public static final int ERR_UNKNOWN_ERROR = 1;
    public static final int ERR_ILLEGAL_ARGUMENT = 2;
    public static final int ERR_PERMISSION_DENIED = 3;
    public static final int ERR_DETAILED_MESSAGE = 4;
    public static final int ERR_SESSION_EXPIRES = 5;
    public static final int ERR_OPERATION_TOO_FREQUENT = 6;
    public static final int ERR_DATA_NOT_FOUND = 7;
    public static final int ERR_NEED_UPGRADE = 8;
    public static final int ERR_NEED_ADMIN_ID = 9;
    public static final int ERR_NEED_USER_ID = 10;
    public static final int ERROR_SEND = 11;
    public static final int ERROR_UNFIND_FILE = 12;
    public static final int UNREACHED = 13;

    //admin
    public static final int ERR_ADMIN_EXISTED = 50;
    public static final int ERR_ADMIN_PASSWORD_ERROR = 51;
    public static final int ERR_ADMIN_NOTEXIST = 52;


    //banner
    public static final int ERR_BANNER_IMG_NULL = 100;

    //sort
    public static final int ERR_PARENTID_VALID_DENIED = 150;
    public static final int ERR_NAME_VALID_DENIED = 151;

    //user
    public static final int ERR_VCODE_INVALID = 201;
    public static final int ERR_VCODE_OVERTIME = 202;
    public static final int ERR_ALIYUN_EXCEPTION = 203;
    public static final int ERR_MOBILE_VCODE_OVERTIME = 204;
    public static final int ERR_USER_MOBILE_INVALID = 205;
    public static final int ERR_USER_EMAIE_INVALID = 206;
    public static final int ERR_USER_NICK_FORMAT = 207;
    public static final int ERR_USER_PASSWORD_FORMAT = 208;
    public static final int ERR_USER_MOBILE_USED = 209;
    public static final int ERR_USER_EMAIE_USED = 210;
    public static final int ERR_USER_NOTEXIST = 211;
    public static final int ERR_USER_PASSWORD_ERROR = 212;
    public static final int ERR_USER_MOBILE_DIFFER = 213;
    public static final int ERR_MOBILECODE_NONE = 214;

    //oss
    public static final int ERR_FILE_THANMAX = 250;

    //vip
    public static final int ERR_BAD_DURATION = 300;
    public static final int ERR_VIP_USER_EXIST = 301;
    public static final int ERR_VIP_TYPE_NONE = 302;
    public static final int ERR_VIP_DURATION_NONE = 303;
    public static final int ERR_MONEY_LESS = 304;
    public static final int ERR_VIP_NAME_EXIST = 305;
    public static final int ERR_VIP_GRADE_EXIST = 306;


}
