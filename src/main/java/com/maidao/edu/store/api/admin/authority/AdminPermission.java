package com.maidao.edu.store.api.admin.authority;

import com.maidao.edu.store.common.entity.Constants;

public enum AdminPermission {

    //    none
    NONE("", ""),

    /* 功能模块 */
    // admin&role
    ROLE_EDIT("管理组管理", Constants.LEVEL_IMPORTANT),
    ADMIN_LIST("管理员列表", Constants.LEVEL_IMPORTANT),
    ADMIN_EDIT("编辑管理员", Constants.LEVEL_IMPORTANT),
    SORT_EDIT("编辑产品类型", Constants.LEVEL_IMPORTANT),
    SORT_REMOVE("删除产品类型", Constants.LEVEL_IMPORTANT),

    //info
    BANNER_EDIT("Banner管理", Constants.LEVEL_PRIMARY),
    PRODUCT_EDIT("Product管理", Constants.LEVEL_PRIMARY),
    ORDER_EDIT("订单管理", Constants.LEVEL_PRIMARY),
    COUPON_EDIT("优惠券管理", Constants.LEVEL_PRIMARY),
    VIP_EDIT("VIP管理", Constants.LEVEL_PRIMARY),
    COMMENT_EDIT("评论管理", Constants.LEVEL_PRIMARY),

    USER_EDIT("用户管理", Constants.LEVEL_PRIMARY);

    private String val;
    private String level;

    AdminPermission(String val, String level) {
        this.val = val;
        this.level = level;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
