/*
 Navicat Premium Data Transfer

 Source Server         : ysmz
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : rm-uf68bj46v537i6mqt8o.mysql.rds.aliyuncs.com:3306
 Source Schema         : xiaobai_dev

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 13/04/2021 17:23:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                              `code` varchar(6) DEFAULT NULL COMMENT '地址编码',
                              `detail` varchar(100) DEFAULT NULL COMMENT '详细地址',
                              `name` varchar(11) DEFAULT NULL COMMENT '收货人姓名',
                              `mobile` varchar(11) DEFAULT NULL COMMENT '收货人电话',
                              `is_default` tinyint(4) DEFAULT NULL COMMENT '是否为默认收货地址:1.是2.否',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COMMENT='用户收货地址';

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `email` varchar(50) NOT NULL COMMENT '邮箱',
                            `mobile` varchar(16) NOT NULL COMMENT '电话',
                            `name` varchar(16) DEFAULT NULL COMMENT '姓名',
                            `password` varchar(64) NOT NULL COMMENT '密码',
                            `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
                            `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.正常2.禁用',
                            `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                            `signin_at` bigint(20) DEFAULT NULL COMMENT '最后登录时间',
                            `img` varchar(1024) DEFAULT NULL COMMENT '头像',
                            `area_id` int(11) DEFAULT NULL COMMENT '地区编码',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='管理员\n';

-- ----------------------------
-- Table structure for admin_session
-- ----------------------------
DROP TABLE IF EXISTS `admin_session`;
CREATE TABLE `admin_session` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `admin_id` int(11) DEFAULT NULL COMMENT '管理员ID',
                                    `token` varchar(64) DEFAULT NULL COMMENT '登录标志',
                                    `signin_at` bigint(20) DEFAULT NULL COMMENT '登录时间',
                                    `expire_at` bigint(20) DEFAULT NULL COMMENT '登陆过期时间',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=645 DEFAULT CHARSET=utf8mb4 COMMENT='管理员登录日志 & token 持久化\n';

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
                           `id` int(11) NOT NULL COMMENT 'ID',
                           `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
                           `type` int(11) DEFAULT NULL COMMENT '类型（1：国家，2：省份，3：城市，4：区县）',
                           `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
                           `full_name` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '全名',
                           `gcj02_lng` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'gcj02经度',
                           `gcj02_lat` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'gcj02纬度',
                           `pinyin` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拼音',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地址信息基础表';

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `admin_id` int(11) DEFAULT NULL COMMENT '发布者ID',
                              `title` varchar(128) DEFAULT NULL COMMENT '标题',
                              `intro` varchar(512) DEFAULT NULL COMMENT '简介',
                              `content` longtext COMMENT '内容',
                              `img` varchar(1024) DEFAULT NULL COMMENT '封面图',
                              `browse_num` int(11) DEFAULT '0' COMMENT '浏览量',
                              `favour_num` int(11) DEFAULT '0' COMMENT '点赞量',
                              `star_num` int(11) DEFAULT '0' COMMENT '收藏量',
                              `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.启用2.禁用',
                              `created_at` bigint(20) DEFAULT NULL COMMENT '发布时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='文章';

-- ----------------------------
-- Table structure for artisan
-- ----------------------------
DROP TABLE IF EXISTS `artisan`;
CREATE TABLE `artisan` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `name` varchar(50) DEFAULT NULL COMMENT '姓名',
                              `avatar` varchar(1024) DEFAULT NULL COMMENT '头像',
                              `mobile` varchar(20) NOT NULL COMMENT '电话',
                              `password` varchar(64) NOT NULL COMMENT '密码',
                              `identity` varchar(18) DEFAULT NULL COMMENT '身份证号',
                              `type` tinyint(3) DEFAULT NULL COMMENT '工种：1.设计师2.水电工3.瓦工4.美缝5.木工6.防水7.油漆工8.垃圾清运9.砸墙10.封阳台11.保洁',
                              `years` tinyint(3) DEFAULT NULL COMMENT '工龄',
                              `state` tinyint(1) DEFAULT NULL COMMENT '接单状态：0.不接单1.接单中',
                              `status` tinyint(1) DEFAULT '1' COMMENT '审核状态：1.待审核2.已审核3.审核未通过4.已封禁',
                              `created_at` bigint(20) DEFAULT NULL COMMENT '注册时间',
                              `signin_at` bigint(20) DEFAULT NULL COMMENT '最近登录时间',
                              `price` varchar(10) DEFAULT NULL COMMENT '报价或工价',
                              `hot` int(11) DEFAULT '0' COMMENT '热度',
                              `avaliable` tinyint(2) DEFAULT NULL COMMENT '施工状态 ：1.正在施工 2.空闲中',
                              `case_num` int(11) DEFAULT NULL COMMENT '设计师案例数量',
                              `furnish_style` varchar(500) DEFAULT NULL COMMENT '设计师擅长风格',
                              `tik_tok_mobile` varchar(50) DEFAULT NULL COMMENT '抖音号',
                              `star_num` int(11) DEFAULT NULL COMMENT '粉丝数',
                              `service_area` varchar(10) DEFAULT NULL COMMENT '设计师服务范围',
                              `service_type` varchar(500) DEFAULT NULL COMMENT '设计师服务类型',
                              `sex` tinyint(1) DEFAULT NULL COMMENT '性别：1.男2.女',
                              `verify` varchar(1024) DEFAULT NULL COMMENT '身份证核实',
                              `today_revenue` int(11) DEFAULT '0' COMMENT '今日收益',
                              `credit_score` float DEFAULT NULL COMMENT '信用积分',
                              `location` varchar(400) DEFAULT NULL COMMENT '当前位置信息',
                              `count_star` int(11) DEFAULT NULL COMMENT '设计师的所有案例和图集被收藏的总数',
                              `growth_value` int(11) DEFAULT '0' COMMENT '工人成长值',
                              `grade` tinyint(4) DEFAULT '1' COMMENT '工人等级',
                              `area_id` int(11) DEFAULT NULL COMMENT '地区编码',
                              `device_id` varchar(64) DEFAULT NULL COMMENT '设备ID',
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE KEY `uk_mobile` (`mobile`) USING BTREE,
                              UNIQUE KEY `uk_identity` (`identity`) USING BTREE,
                              UNIQUE KEY `tik_tok_mobile` (`tik_tok_mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100209 DEFAULT CHARSET=utf8mb4 COMMENT='设计师&&工人\n';

-- ----------------------------
-- Table structure for artisan_bill
-- ----------------------------
DROP TABLE IF EXISTS `artisan_bill`;
CREATE TABLE `artisan_bill` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `artisan_id` bigint(20) NOT NULL COMMENT '工人ID',
                                   `bill_type` tinyint(4) NOT NULL COMMENT '类型：1.收益2.支出',
                                   `state` tinyint(4) NOT NULL COMMENT '来源：1.商家补贴2.平台奖励3.提现',
                                   `status` tinyint(4) NOT NULL COMMENT '状态：1.未发放2.已发放3.提现中4.已提现',
                                   `remark` varchar(1025) NOT NULL COMMENT '详情',
                                   `creact_at` bigint(20) NOT NULL COMMENT '创建时间',
                                   `balance` int(11) NOT NULL COMMENT '返佣金额',
                                   `payload` varchar(3000) DEFAULT NULL COMMENT '记录数据产生的金额，微信号以及公众号',
                                   `notes` varchar(500) DEFAULT NULL COMMENT '备注',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=315 DEFAULT CHARSET=utf8mb4 COMMENT='工人流水账单\n';

-- ----------------------------
-- Table structure for artisan_cart
-- ----------------------------
DROP TABLE IF EXISTS `artisan_cart`;
CREATE TABLE `artisan_cart` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `artisan_id` bigint(20) DEFAULT NULL COMMENT '工人ID',
                                   `product_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
                                   `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                                   `count` int(11) DEFAULT NULL COMMENT '商品总数量',
                                   `product_sno` varchar(512) DEFAULT NULL COMMENT '商品规格号',
                                   `merchant_id` int(11) DEFAULT NULL COMMENT '店铺ID',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=472 DEFAULT CHARSET=utf8mb4 COMMENT='工人购物车';

-- ----------------------------
-- Table structure for artisan_case
-- ----------------------------
DROP TABLE IF EXISTS `artisan_case`;
CREATE TABLE `artisan_case` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `artisan_id` bigint(20) DEFAULT NULL COMMENT '设计师ID',
                                   `title` varchar(255) DEFAULT NULL COMMENT '标题',
                                   `content` longtext COMMENT '内容',
                                   `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                                   `favour_num` int(6) DEFAULT NULL COMMENT '点赞数量',
                                   `star_num` int(6) DEFAULT NULL COMMENT '收藏数量',
                                   `img_cover` varchar(100) DEFAULT NULL COMMENT '封面图',
                                   `spec` longtext COMMENT '案例规格',
                                   `para` longtext COMMENT '案例参数',
                                   `floor_plan` varchar(2048) DEFAULT NULL COMMENT '户型图',
                                   `status` tinyint(4) DEFAULT NULL COMMENT '上下架状态：1.上架2.下架',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='设计师案例';

-- ----------------------------
-- Table structure for artisan_case_img
-- ----------------------------
DROP TABLE IF EXISTS `artisan_case_img`;
CREATE TABLE `artisan_case_img` (
                                       `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `artisan_id` bigint(20) DEFAULT NULL COMMENT '设计师ID',
                                       `title` varchar(255) DEFAULT NULL COMMENT '标题',
                                       `imgs` text COMMENT '图集',
                                       `favour_num` int(6) DEFAULT NULL COMMENT '点赞数',
                                       `star_num` int(6) DEFAULT NULL COMMENT '收藏数',
                                       `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                                       `status` tinyint(4) DEFAULT NULL COMMENT '上下架状态：1.上架2.下架',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COMMENT='设计师图集';

-- ----------------------------
-- Table structure for artisan_grade_detail
-- ----------------------------
DROP TABLE IF EXISTS `artisan_grade_detail`;
CREATE TABLE `artisan_grade_detail` (
                                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                           `artisan_id` bigint(20) NOT NULL COMMENT '工人ID',
                                           `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                                           `grade` int(11) DEFAULT '0' COMMENT '分数',
                                           `remark` varchar(100) NOT NULL COMMENT '详情',
                                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COMMENT='工人等级积分账单\n';

-- ----------------------------
-- Table structure for artisan_location
-- ----------------------------
DROP TABLE IF EXISTS `artisan_location`;
CREATE TABLE `artisan_location` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `artisan_id` bigint(20) DEFAULT NULL COMMENT '工人ID',
                                       `artisan_type` tinyint(4) DEFAULT NULL COMMENT '工种',
                                       `area_id` int(11) DEFAULT NULL COMMENT '地区编码',
                                       `lng` double(10,6) NOT NULL COMMENT '经度',
  `lat` double(10,6) NOT NULL COMMENT '纬度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1241 DEFAULT CHARSET=utf8mb4 COMMENT='工人&装修工地坐标，支持一个工人同时出现在多个工地\n';

-- ----------------------------
-- Table structure for artisan_session
-- ----------------------------
DROP TABLE IF EXISTS `artisan_session`;
CREATE TABLE `artisan_session` (
                                      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `artisan_id` bigint(20) DEFAULT NULL COMMENT '工人ID',
                                      `token` varchar(64) DEFAULT NULL COMMENT '登录标志',
                                      `signin_at` bigint(20) DEFAULT NULL COMMENT '登录时间',
                                      `expire_at` bigint(20) DEFAULT NULL COMMENT '过期时间',
                                      `ip` varchar(32) DEFAULT NULL COMMENT '登录IP',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1603 DEFAULT CHARSET=utf8mb4 COMMENT='工人会话&&设计师会话\n';

-- ----------------------------
-- Table structure for artisan_subsidy
-- ----------------------------
DROP TABLE IF EXISTS `artisan_subsidy`;
CREATE TABLE `artisan_subsidy` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `artisan_id` bigint(20) DEFAULT NULL COMMENT '工人的ID',
                                      `ren_trade_id` int(11) DEFAULT NULL COMMENT '订单ID',
                                      `sum` int(11) DEFAULT NULL COMMENT '金额',
                                      `admin_id` int(11) DEFAULT NULL COMMENT '管理员ID',
                                      `created_at` bigint(20) DEFAULT NULL COMMENT '发放时间',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='工人津贴';

-- ----------------------------
-- Table structure for artisan_wallet
-- ----------------------------
DROP TABLE IF EXISTS `artisan_wallet`;
CREATE TABLE `artisan_wallet` (
                                     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `artisan_id` bigint(20) NOT NULL COMMENT '工人ID',
                                     `today_income` int(11) DEFAULT '0' COMMENT '今日收益',
                                     `month_income` int(11) DEFAULT '0' COMMENT '月收益',
                                     `history_income` int(11) DEFAULT '0' COMMENT '历史收益',
                                     `balance` int(11) DEFAULT '0' COMMENT '工人余额',
                                     `withdrawed` int(11) DEFAULT '0' COMMENT '已提现',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8mb4 COMMENT='工人钱包';

-- ----------------------------
-- Table structure for artisan_weixin_app
-- ----------------------------
DROP TABLE IF EXISTS `artisan_weixin_app`;
CREATE TABLE `artisan_weixin_app` (
                                         `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `artisan_id` bigint(20) NOT NULL COMMENT '工人ID',
                                         `app_id` varchar(32) NOT NULL COMMENT '手机应用程序软件（微信公众号）编号',
                                         `openid` varchar(45) NOT NULL COMMENT '公众号的普通用户的唯一标识',
                                         `subscribed` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订阅状态：0.未订阅1.已订阅',
                                         `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                                         PRIMARY KEY (`id`) USING BTREE,
                                         UNIQUE KEY `appid_artisanid` (`app_id`,`artisan_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10030 DEFAULT CHARSET=utf8mb4 COMMENT='工人与微信号授权关系\n';

-- ----------------------------
-- Table structure for artisan_wrap
-- ----------------------------
DROP TABLE IF EXISTS `artisan_wrap`;
CREATE TABLE `artisan_wrap` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `artisan_id` bigint(20) NOT NULL COMMENT '工人ID',
                                   `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                   `status` tinyint(4) NOT NULL COMMENT '状态：1.无操作2.接受3.拒绝',
                                   `artisans` varchar(1024) NOT NULL COMMENT '推荐的工人',
                                   `house_id` bigint(20) NOT NULL COMMENT '用户房屋ID',
                                   `remark` text,
                                   `created_at` bigint(20) NOT NULL DEFAULT '1588066140654',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb4 COMMENT='工人推送工人给用户\n';

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
                             `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `title` varchar(64) DEFAULT NULL COMMENT '标题',
                             `payload` varchar(1000) DEFAULT NULL COMMENT '跳转方式',
                             `status` tinyint(1) DEFAULT NULL COMMENT '状态：1.显示2.隐藏',
                             `priority` int(11) DEFAULT NULL COMMENT '权重',
                             `type` tinyint(4) DEFAULT NULL COMMENT '种类',
                             `img` varchar(1024) DEFAULT NULL COMMENT '照片',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='横幅&&轮播图';

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `name` varchar(50) NOT NULL COMMENT '名称',
                            `logo` varchar(1024) NOT NULL COMMENT '徽标',
                            `product_category_sequences` varchar(1000) NOT NULL COMMENT '商品种类编码',
                            `priority` int(11) DEFAULT '1' COMMENT '权重',
                            `status` tinyint(4) NOT NULL COMMENT '状态：1.上架2.下架3.应用',
                            `applicant` varchar(200) DEFAULT NULL COMMENT '申请人',
                            `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COMMENT='品牌库\n';

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                           `product_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
                           `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                           `count` int(11) DEFAULT NULL COMMENT '购买数量',
                           `product_sno` varchar(512) DEFAULT NULL COMMENT '商品规格编码',
                           `merchant_id` int(11) DEFAULT NULL COMMENT '商户ID',
                           `product_from` tinyint(4) DEFAULT NULL COMMENT '此单来源，区分是否是工人推荐的商品：1.用户2.工人',
                           `cart_wrap_id` bigint(20) DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1264 DEFAULT CHARSET=utf8mb4 COMMENT='购物车';

-- ----------------------------
-- Table structure for cart_wrap
-- ----------------------------
DROP TABLE IF EXISTS `cart_wrap`;
CREATE TABLE `cart_wrap` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `artisan_id` bigint(20) NOT NULL COMMENT '工人ID',
                                `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                `status` tinyint(4) NOT NULL COMMENT '状态 ：1.无操作 2.接受 3.拒绝',
                                `commend_cart` mediumtext COMMENT '购物车存商品Id,商品规格,购买数量等购买详情',
                                `ren_trade_id` bigint(20) NOT NULL COMMENT '装修订单ID',
                                `architect_id` int(11) DEFAULT '0' COMMENT '推荐工匠的设计师ID',
                                `remark` text COMMENT '备注',
                                `created_at` bigint(20) NOT NULL DEFAULT '1588066140654',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8mb4 COMMENT='工人推送商品给用户\n';

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
                              `check_status` tinyint(1) NOT NULL DEFAULT '3',
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `trade_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
                              `kind` tinyint(4) DEFAULT NULL COMMENT '1.用户对工人2.用户对设计师3.工人对用户4.用户对商5.用户对服务6.用户对物流',
                              `from_id` bigint(20) DEFAULT NULL COMMENT '该评论或动态发布人ID',
                              `to_id` bigint(20) DEFAULT NULL COMMENT '被评论的事物ID',
                              `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                              `content` longtext COMMENT '内容',
                              `score` int(11) DEFAULT NULL COMMENT '评分',
                              `merchant_id` int(11) DEFAULT NULL COMMENT '商户ID',
                              `product_sno` varchar(512) DEFAULT NULL COMMENT '商品规格编号',
                              `status` tinyint(4) DEFAULT NULL COMMENT '评价状态：1.已评价2.未评价',
                              `imgs` varchar(1024) DEFAULT NULL COMMENT '图集',
                              `favour_num` tinyint(6) DEFAULT '0' COMMENT '点赞量',
                              `comment_at` bigint(20) DEFAULT NULL COMMENT '发布时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1137 DEFAULT CHARSET=utf8mb4 COMMENT='评论&&工人动态\n';

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `kind` tinyint(1) NOT NULL DEFAULT '1',
                             `name` varchar(20) NOT NULL COMMENT '名称',
                             `duration` bigint(20) NOT NULL COMMENT '持续时长(天)',
                             `type` tinyint(1) NOT NULL COMMENT '种类：1.工价2.材料',
                             `pro_code` varchar(6) DEFAULT NULL COMMENT '材料券适用产品分类编码',
                             `ar_type` tinyint(1) DEFAULT NULL COMMENT '工价券适用工种',
                             `rule` varchar(5000) NOT NULL COMMENT '使用规则',
                             `status` tinyint(1) NOT NULL COMMENT '状态：1.启用2.禁用',
                             `cre_at` bigint(20) NOT NULL COMMENT '创建时间',
                             `area_id` int(11) DEFAULT NULL COMMENT '地区市级ID',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券';

-- ----------------------------
-- Table structure for customer_service
-- ----------------------------
DROP TABLE IF EXISTS `customer_service`;
CREATE TABLE `customer_service` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `merchant_id` bigint(20) NOT NULL COMMENT '商户ID',
                                       `mobile` varchar(11) NOT NULL COMMENT '电话',
                                       `qrcode` varchar(1024) NOT NULL COMMENT '二维码',
                                       `tip` varchar(1024) DEFAULT NULL COMMENT '提示',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='客服设置\n';

-- ----------------------------
-- Table structure for econtract
-- ----------------------------
DROP TABLE IF EXISTS `econtract`;
CREATE TABLE `econtract` (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `payload` varchar(512) DEFAULT NULL COMMENT '负载信息',
                                `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                                `url` varchar(512) DEFAULT NULL COMMENT '全球资源定位器',
                                `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
                                `status` tinyint(4) DEFAULT NULL COMMENT '生成状态：1.已生成2.未生成',
                                `imgs` varchar(10000) DEFAULT NULL COMMENT 'pdf转图片',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8mb4 COMMENT='PDF生成记录\n';

-- ----------------------------
-- Table structure for favour
-- ----------------------------
DROP TABLE IF EXISTS `favour`;
CREATE TABLE `favour` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                             `favour_id` bigint(20) NOT NULL COMMENT '被点赞的对象的ID',
                             `type` tinyint(2) NOT NULL COMMENT '点赞的种类：1.设计师案例2.设计师案例图集3.工人评论4.文章',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1452 DEFAULT CHARSET=utf8mb4 COMMENT='点赞\n';

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                            `mobile` varchar(20) DEFAULT NULL COMMENT '电话',
                            `nick` varchar(20) DEFAULT NULL COMMENT '昵称',
                            `area` bigint(20) DEFAULT NULL COMMENT '面积',
                            `status` tinyint(1) DEFAULT NULL COMMENT '房屋状态：1.毛坯房2.简装房3.精装房',
                            `room` int(11) DEFAULT NULL COMMENT '室数',
                            `hall` int(11) DEFAULT NULL COMMENT '厅数',
                            `kitchen` int(11) DEFAULT NULL COMMENT '厨数',
                            `toilet` int(11) DEFAULT NULL COMMENT '卫数',
                            `state` tinyint(1) DEFAULT NULL COMMENT '装修进度：1.待装修2.正在装修',
                            `created_at` bigint(20) DEFAULT NULL COMMENT '发布时间',
                            `def` tinyint(1) DEFAULT '1' COMMENT '是否为默认房屋：1.是2.否',
                            `bespoke_num` int(11) DEFAULT '0' COMMENT '房屋总共预约次数',
                            `location` varchar(400) DEFAULT NULL COMMENT '房屋位置信息',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8mb4 COMMENT='房屋';

-- ----------------------------
-- Table structure for mer_weixin_app
-- ----------------------------
DROP TABLE IF EXISTS `mer_weixin_app`;
CREATE TABLE `mer_weixin_app` (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `mer_id` bigint(20) NOT NULL COMMENT '店铺ID',
                                     `app_id` varchar(32) NOT NULL COMMENT '应用编号',
                                     `open_id` varchar(45) NOT NULL COMMENT '初始店铺管理员微信标识',
                                     `created_at` bigint(20) NOT NULL COMMENT '授权时间',
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `appid_mer_id` (`app_id`,`mer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
                               `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `name` varchar(50) NOT NULL COMMENT '姓名',
                               `logo` varchar(1024) NOT NULL COMMENT '徽标',
                               `product_category_sequences` varchar(1000) NOT NULL COMMENT '商品分类编码',
                               `valid_thru` bigint(20) NOT NULL COMMENT '有效期',
                               `state` tinyint(4) NOT NULL COMMENT '状态：1.正常2.封禁3.欠费',
                               `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                               `setting` varchar(5000) DEFAULT NULL COMMENT '商铺设置',
                               `phone` varchar(16) DEFAULT NULL COMMENT '电话',
                               `total_purchases` int(11) DEFAULT '0' COMMENT '总收益',
                               `location` varchar(400) DEFAULT NULL COMMENT '店铺位置信息',
                               `star_num` int(11) DEFAULT '0' COMMENT '该店铺被收藏总数',
                               `average_score` varchar(100) DEFAULT NULL COMMENT '平均评分',
                               `area_id` int(11) DEFAULT NULL COMMENT '地区编码',
                               `rate` int(11) NOT NULL DEFAULT '0',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COMMENT='店铺\n';

-- ----------------------------
-- Table structure for merchant_admin
-- ----------------------------
DROP TABLE IF EXISTS `merchant_admin`;
CREATE TABLE `merchant_admin` (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `merchant_id` int(11) NOT NULL COMMENT '店铺ID',
                                     `name` varchar(30) DEFAULT NULL COMMENT '姓名',
                                     `mobile` varchar(20) NOT NULL COMMENT '电话',
                                     `password` varchar(128) NOT NULL COMMENT '密码',
                                     `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.正常2.停用',
                                     `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                                     `signin_at` bigint(20) DEFAULT NULL COMMENT '最近登录时间',
                                     `area_id` int(11) DEFAULT NULL COMMENT '地区编码',
                                     `device_id` varchar(64) DEFAULT NULL COMMENT '设备ID',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     UNIQUE KEY `username` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COMMENT='店铺管理员\n';

-- ----------------------------
-- Table structure for merchant_admin_session
-- ----------------------------
DROP TABLE IF EXISTS `merchant_admin_session`;
CREATE TABLE `merchant_admin_session` (
                                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                             `admin_id` bigint(20) DEFAULT NULL COMMENT '店铺管理员ID',
                                             `role` varchar(128) DEFAULT NULL COMMENT '店铺角色ID',
                                             `name` varchar(128) DEFAULT NULL COMMENT '店铺管理员姓名',
                                             `token` varchar(64) DEFAULT NULL COMMENT '登录标志',
                                             `signin_at` bigint(20) DEFAULT NULL COMMENT '登录时间',
                                             `expire_at` bigint(20) DEFAULT NULL COMMENT '过期时间',
                                             `ip` varchar(32) DEFAULT NULL COMMENT '登录IP',
                                             PRIMARY KEY (`id`) USING BTREE,
                                             UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1075 DEFAULT CHARSET=utf8mb4 COMMENT='商户管理员登录日志 & token 持久化\n';

-- ----------------------------
-- Table structure for merchant_bill
-- ----------------------------
DROP TABLE IF EXISTS `merchant_bill`;
CREATE TABLE `merchant_bill` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `merchant_id` int(11) NOT NULL COMMENT '店铺ID',
                                    `type` tinyint(4) NOT NULL COMMENT '交易类型：1.支出2.充值/收入',
                                    `created_at` bigint(20) NOT NULL COMMENT '交易时间',
                                    `amount` int(11) NOT NULL COMMENT '交易金额',
                                    `order_number` varchar(32) NOT NULL COMMENT '订单编号',
                                    `remark` varchar(128) NOT NULL COMMENT '备注',
                                    `withdraw_status` tinyint(4) DEFAULT '0' COMMENT '提现状态：0.默认1.提现成功2.提现失败',
                                    `payload` varchar(3000) DEFAULT NULL COMMENT '记录数据产生的金额，微信号以及公众号',
                                    `notes` varchar(500) DEFAULT NULL COMMENT '备注',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1132 DEFAULT CHARSET=utf8mb4 COMMENT='店铺提现\n';

-- ----------------------------
-- Table structure for merchant_income
-- ----------------------------
DROP TABLE IF EXISTS `merchant_income`;
CREATE TABLE `merchant_income` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `merchant_id` int(11) NOT NULL COMMENT '店铺ID',
                                      `today_income` int(11) DEFAULT '0' COMMENT '今日收益',
                                      `month_income` int(11) DEFAULT '0' COMMENT '月收益',
                                      `history_income` int(11) DEFAULT '0' COMMENT '历史收益',
                                      `balance` int(11) DEFAULT '0' COMMENT '商户余额',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COMMENT='店铺收益';

-- ----------------------------
-- Table structure for merchant_weixin_app
-- ----------------------------
DROP TABLE IF EXISTS `merchant_weixin_app`;
CREATE TABLE `merchant_weixin_app` (
                                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                          `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                                          `merchant_id` int(11) DEFAULT NULL COMMENT '店铺ID',
                                          `name` varchar(64) DEFAULT NULL COMMENT '名称',
                                          `app_id` varchar(64) NOT NULL DEFAULT '' COMMENT '手机应用程序软件（微信公众号）编号',
                                          `secret` varchar(64) DEFAULT NULL,
                                          `weixin_mch` text,
                                          `type` varchar(10) NOT NULL DEFAULT '',
                                          `state` tinyint(4) NOT NULL DEFAULT '1',
                                          `primary` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否首要APP：0.是1.否',
                                          `msg_settings` varchar(3000) DEFAULT NULL COMMENT '消息设置',
                                          PRIMARY KEY (`id`) USING BTREE,
                                          UNIQUE KEY `app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺微信公众平台\n';

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `from_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '消息发出方ID：0.系统消息',
                              `to_id` bigint(20) NOT NULL COMMENT '消息接收方ID',
                              `created_at` bigint(20) NOT NULL COMMENT '发出时间',
                              `payload` varchar(10000) DEFAULT NULL COMMENT '负载信息',
                              `type` tinyint(4) NOT NULL COMMENT '消息种类：种类繁多，详见枚举类MessageTpl',
                              `status` tinyint(4) NOT NULL DEFAULT '2' COMMENT '消息状态：1.已查阅2.未查阅',
                              `house_id` bigint(20) DEFAULT '0' COMMENT '房屋ID',
                              `to_type` tinyint(1) DEFAULT NULL COMMENT '接收消息方用户类型:1.工人2.商家3.用户',
                              `kind` tinyint(1) DEFAULT NULL COMMENT '消息类型',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4273 DEFAULT CHARSET=utf8mb4 COMMENT='消息\n';

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `title` varchar(128) DEFAULT NULL COMMENT '名称',
                              `category_id` int(11) DEFAULT NULL COMMENT '分类ID',
                              `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID',
                              `specs` longtext COMMENT '产品规格',
                              `params` text COMMENT '商品参数',
                              `content` longtext COMMENT '详情介绍',
                              `guarantee` varchar(5000) DEFAULT NULL COMMENT '质保卡详情',
                              `delivery` varchar(1000) DEFAULT NULL COMMENT '送货方式',
                              `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                              `merchant_id` int(11) DEFAULT NULL COMMENT '店铺ID',
                              `priority` int(11) DEFAULT '1' COMMENT '权重',
                              `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.正常2.停用',
                              `price` int(16) DEFAULT NULL COMMENT '最低价格',
                              `pub_at` bigint(20) DEFAULT NULL COMMENT '上架时间',
                              `sales_count` int(16) DEFAULT NULL COMMENT '销量',
                              `rate` int(11) DEFAULT '0' COMMENT '付定金百分比',
                              `furnish_style` tinyint(4) DEFAULT NULL COMMENT '风格',
                              `commission` int(11) DEFAULT '0' COMMENT '返佣比例',
                              `area_id` int(11) DEFAULT NULL COMMENT '地区编码',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8mb4 COMMENT='产品库\n';

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `sequence` varchar(12) DEFAULT NULL COMMENT '类别唯一序列编码',
                                       `name` varchar(10) DEFAULT NULL COMMENT '名称',
                                       `icon` varchar(200) DEFAULT NULL COMMENT '类别图标',
                                       `parent_id` int(11) DEFAULT NULL COMMENT '父级分类ID',
                                       `priority` int(11) DEFAULT NULL COMMENT '权重',
                                       `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.正常2.停用',
                                       `props` varchar(100) DEFAULT NULL COMMENT '配置信息',
                                       PRIMARY KEY (`id`) USING BTREE,
                                       UNIQUE KEY `sequence` (`sequence`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=477 DEFAULT CHARSET=utf8mb4 COMMENT='产品分类\n';

-- ----------------------------
-- Table structure for product_template
-- ----------------------------
DROP TABLE IF EXISTS `product_template`;
CREATE TABLE `product_template` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `title` varchar(128) DEFAULT NULL COMMENT '名称',
                                       `category_id` int(11) DEFAULT NULL COMMENT '分类ID',
                                       `params` text COMMENT '参数：内容属性部分',
                                       `content` longtext COMMENT '内容图文部分',
                                       `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                                       `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.正常2.停用',
                                       `specs` varchar(10000) DEFAULT NULL COMMENT '参数',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='产品模板\n';

-- ----------------------------
-- Table structure for regulations
-- ----------------------------
DROP TABLE IF EXISTS `regulations`;
CREATE TABLE `regulations` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `content` mediumtext COMMENT '规范文本',
                                  `type` tinyint(4) DEFAULT NULL COMMENT '类型：详见枚举类RegulationType',
                                  `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.正常2.停用',
                                  `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                                  `title` varchar(100) NOT NULL COMMENT '标题',
                                  `kind` tinyint(1) NOT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='平台规范须知\n';

-- ----------------------------
-- Table structure for ren_trade
-- ----------------------------
DROP TABLE IF EXISTS `ren_trade`;
CREATE TABLE `ren_trade` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `order_num` varchar(32) DEFAULT NULL COMMENT '装修订单编号',
                                `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                `status` tinyint(1) DEFAULT '1' COMMENT '1.待预约2.待量房3.合同签订4.施工中5.待评价6.工人已评价等待用户评价7.订单关闭8.用户已评价等待工人评价9.已完成',
                                `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                                `total_amount` int(11) DEFAULT NULL COMMENT '总施工工钱',
                                `house_id` bigint(20) DEFAULT NULL COMMENT '房屋ID',
                                `artisan_id` bigint(20) DEFAULT NULL COMMENT '工人ID',
                                `artisan_type` tinyint(2) DEFAULT NULL COMMENT '工人类型',
                                `cycle` int(11) DEFAULT NULL COMMENT '预计施工时长',
                                `roadwork_time` bigint(20) DEFAULT NULL COMMENT '开工时间',
                                `measure_time` bigint(20) DEFAULT NULL COMMENT '上门量房时间',
                                `construction_area` int(11) DEFAULT '0' COMMENT '施工面积',
                                `architect_id` bigint(20) DEFAULT NULL COMMENT '推荐工人的设计师ID',
                                `e_contract_id` int(11) DEFAULT '0' COMMENT '电子合同ID',
                                `area_id` int(11) DEFAULT NULL COMMENT '地区编码',
                                `pay_status` tinyint(1) DEFAULT NULL COMMENT '支付状态：1.默认未支付2.已支付',
                                `user_coupon_id` bigint(20) DEFAULT NULL COMMENT '该订单业主使用的优惠券ID',
                                `remark` text,
                                PRIMARY KEY (`id`) USING BTREE,
                                KEY `index_orderNumber` (`order_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=808 DEFAULT CHARSET=utf8mb4 COMMENT='装修订单\n';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `name` varchar(16) DEFAULT NULL COMMENT '角色名称',
                           `permissions` varchar(1024) DEFAULT NULL COMMENT '角色权限',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `type` tinyint(4) NOT NULL COMMENT '规则种类：1.分享2.佣金3.服务区域4.工人工种',
                           `config` varchar(5000) DEFAULT NULL COMMENT '规则配置详情',
                           `update_at` bigint(20) NOT NULL COMMENT '更新时间',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE KEY `uk_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='规则表\n';

-- ----------------------------
-- Table structure for skills_video
-- ----------------------------
DROP TABLE IF EXISTS `skills_video`;
CREATE TABLE `skills_video` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `title` varchar(100) DEFAULT NULL COMMENT '标题',
                                   `img` varchar(1024) DEFAULT NULL COMMENT '封面图',
                                   `url` varchar(1024) DEFAULT NULL COMMENT '视频地址',
                                   `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.正常2.停用',
                                   `video_intro` varchar(10000) DEFAULT NULL COMMENT '视频介绍详情',
                                   `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                                   `summary` varchar(100) DEFAULT NULL COMMENT '视频摘要',
                                   `browse_num` int(11) DEFAULT NULL COMMENT '学习次数',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='装修技巧表\n';

-- ----------------------------
-- Table structure for star
-- ----------------------------
DROP TABLE IF EXISTS `star`;
CREATE TABLE `star` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                           `star_id` bigint(20) NOT NULL COMMENT '被收藏的主体的ID',
                           `type` tinyint(1) NOT NULL COMMENT '被收藏的主体种类：1.商品2.设计师3.设计师案例4.设计师案例图集5.匠人6.匠人评论7.文章8.店铺',
                           `user_type` tinyint(1) DEFAULT NULL COMMENT '收藏人的种类：1.用户2.工人',
                           PRIMARY KEY (`id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1583 DEFAULT CHARSET=utf8mb4 COMMENT='收藏\n';

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `refund_status` tinyint(1) DEFAULT NULL COMMENT '退货状态',
                            `order_number` varchar(32) DEFAULT NULL COMMENT '订单编号',
                            `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                            `artisan_id` bigint(20) DEFAULT NULL COMMENT '工人ID',
                            `ren_tread_id` bigint(20) DEFAULT NULL COMMENT '装修订单ID',
                            `status` tinyint(4) DEFAULT NULL COMMENT '订单状态：1.待付款2.待发货3.待收货4.已收货5.已完成',
                            `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                            `remark` varchar(512) DEFAULT NULL COMMENT '备注',
                            `total_price` int(11) DEFAULT NULL COMMENT '商品价格',
                            `items` longtext,
                            `merchant_id` int(11) DEFAULT NULL COMMENT '店铺ID',
                            `address` varchar(1000) DEFAULT NULL COMMENT '下单地址JSON字符串',
                            `ship_info` varchar(100) DEFAULT NULL COMMENT '配置信息JSON字符串',
                            `commented` tinyint(3) DEFAULT NULL COMMENT '订单评价状态：1.已评价2.未评价',
                            `paid` tinyint(3) DEFAULT NULL COMMENT '订单完成状态：1.已完成2.未完成',
                            `total_amount` int(11) DEFAULT NULL COMMENT '订单总价格',
                            `paid_amount` int(11) DEFAULT NULL COMMENT '实际付款总价格',
                            `delivery_fee` int(11) DEFAULT '0' COMMENT '运费',
                            `deposit_amount` int(11) DEFAULT '0' COMMENT '定金，为0时不需要支付定金',
                            `paid_at` bigint(20) DEFAULT NULL COMMENT '付款时间',
                            `user_coupon_id` bigint(20) DEFAULT NULL COMMENT '该订单业主使用的优惠券ID',
                            `online_price` int(11) DEFAULT NULL COMMENT '订单商品有全款时需付',
                            `discount` int(11) DEFAULT NULL COMMENT '折扣金额',
                            `refund_reason` text COMMENT '退款缘由',
                            PRIMARY KEY (`id`) USING BTREE,
                            KEY `index_orderNumber` (`order_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=676 DEFAULT CHARSET=utf8mb4 COMMENT='订单\n';

-- ----------------------------
-- Table structure for trade_bill
-- ----------------------------
DROP TABLE IF EXISTS `trade_bill`;
CREATE TABLE `trade_bill` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `trade_id` bigint(20) NOT NULL COMMENT '订单ID',
                                 `summary` varchar(300) NOT NULL DEFAULT '',
                                 `amount` int(11) NOT NULL COMMENT '支付金额',
                                 `state` tinyint(4) NOT NULL,
                                 `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付方式：1.微信2.线下',
                                 `paid_at` bigint(20) DEFAULT NULL,
                                 `pay_info` varchar(8000) DEFAULT NULL,
                                 `pay_expire` bigint(20) DEFAULT NULL,
                                 `pay_to_platform` tinyint(4) DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 KEY `idx_userid` (`user_id`) USING BTREE,
                                 KEY `idx_tradeid` (`trade_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=659 DEFAULT CHARSET=utf8mb4 COMMENT='订单流水账单\n';

-- ----------------------------
-- Table structure for ui
-- ----------------------------
DROP TABLE IF EXISTS `ui`;
CREATE TABLE `ui` (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `title` varchar(128) DEFAULT NULL COMMENT '名称',
                         `type` tinyint(4) DEFAULT NULL COMMENT '类型：1.用户主页2.店铺主页',
                         `components` longtext COMMENT '组件详情',
                         `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                         `is_default` tinyint(4) DEFAULT NULL COMMENT '是否默认：1.是2.否',
                         `area_id` int(11) DEFAULT NULL COMMENT '地区编码',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='自定义ui\n';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `nick` varchar(30) DEFAULT '' COMMENT '昵称',
                           `name` varchar(30) DEFAULT '' COMMENT '姓名',
                           `avatar` varchar(500) DEFAULT '' COMMENT '头像',
                           `mobile` varchar(20) DEFAULT '' COMMENT '电话',
                           `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
                           `detail` varchar(8000) NOT NULL DEFAULT '' COMMENT '用户详情：性别、地区、职业',
                           `unionid` varchar(50) NOT NULL DEFAULT '' COMMENT '公众号的普通用户的唯一标识',
                           `balance` int(11) NOT NULL DEFAULT '0' COMMENT '余额',
                           `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '地区编码',
                           `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：1正常',
                           `signin_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最近一次登录时间',
                           `created_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
                           `updated_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE KEY `mobile` (`mobile`) USING BTREE,
                           KEY `idx_unionid` (`unionid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=101113 DEFAULT CHARSET=utf8mb4 COMMENT='用户\n';

-- ----------------------------
-- Table structure for user_bill
-- ----------------------------
DROP TABLE IF EXISTS `user_bill`;
CREATE TABLE `user_bill` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                `transaction_time` bigint(20) NOT NULL COMMENT '交易时间',
                                `transaction_price` int(11) NOT NULL COMMENT '交易金额',
                                `order_number` varchar(32) NOT NULL COMMENT '订单编号',
                                `merchant_id` bigint(20) DEFAULT NULL COMMENT '涉及的店铺ID',
                                `notes` varchar(1000) DEFAULT NULL COMMENT '备注',
                                `refund_status` tinyint(1) DEFAULT NULL COMMENT '退货状态',
                                `payload` varchar(3000) DEFAULT NULL COMMENT '退款负载信息',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=648 DEFAULT CHARSET=utf8mb4 COMMENT='用户交易流水账单\n';

-- ----------------------------
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `usr_id` bigint(20) NOT NULL COMMENT '业主ID',
                                  `cou_id` bigint(20) NOT NULL COMMENT '优惠券ID',
                                  `type` tinyint(1) NOT NULL COMMENT '种类：1.工价2.材料',
                                  `status` tinyint(1) NOT NULL COMMENT '状态：1.待使用2.已使用3.已过期',
                                  `get_at` bigint(20) NOT NULL COMMENT '领取时间',
                                  `exp_at` bigint(20) NOT NULL COMMENT '过期时间',
                                  `area_id` int(11) DEFAULT NULL COMMENT '地区市级ID',
                                  `kind` tinyint(1) NOT NULL DEFAULT '1',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COMMENT='业主领取的优惠券';

-- ----------------------------
-- Table structure for user_session
-- ----------------------------
DROP TABLE IF EXISTS `user_session`;
CREATE TABLE `user_session` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                   `token` varchar(64) DEFAULT NULL COMMENT '用户登录唯一标识',
                                   `signin_at` bigint(20) DEFAULT NULL COMMENT '登录时间',
                                   `expire_at` bigint(20) DEFAULT NULL COMMENT '过期时间',
                                   `ip` varchar(32) DEFAULT NULL COMMENT '登录IP',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=530 DEFAULT CHARSET=utf8mb4 COMMENT='用户登录日志 & token 持久化\n';

-- ----------------------------
-- Table structure for user_weixin_app
-- ----------------------------
DROP TABLE IF EXISTS `user_weixin_app`;
CREATE TABLE `user_weixin_app` (
                                      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                      `app_id` varchar(32) NOT NULL COMMENT '手机应用程序软件（微信公众号）编号',
                                      `openid` varchar(45) NOT NULL COMMENT '公众号的普通用户的唯一标识',
                                      `subscribed` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订阅状态：0.未订阅1.已订阅',
                                      `created_at` bigint(20) NOT NULL COMMENT '创建时间',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      UNIQUE KEY `appid_openid` (`app_id`,`openid`) USING BTREE,
                                      UNIQUE KEY `appid_userid` (`app_id`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=436 DEFAULT CHARSET=utf8mb4 COMMENT='用户与微信号授权关系\n';

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `type` tinyint(2) NOT NULL COMMENT '版本更新类型：1.商户端2.工匠端',
                              `version_number` varchar(20) NOT NULL COMMENT '版本号',
                              `content` varchar(2000) DEFAULT NULL COMMENT '更新内容',
                              `status` tinyint(4) DEFAULT NULL COMMENT '状态：1.上架2.下架',
                              `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
                              `version_level` int(11) NOT NULL COMMENT 'API版本',
                              `url` varchar(500) DEFAULT NULL COMMENT '全球资源定位器',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='版本更新内容\n';

-- ----------------------------
-- Table structure for weixin_app_ticket
-- ----------------------------
DROP TABLE IF EXISTS `weixin_app_ticket`;
CREATE TABLE `weixin_app_ticket` (
                                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                        `app_id` varchar(32) NOT NULL COMMENT '手机应用程序软件（微信公众号）编号',
                                        `val` varchar(512) NOT NULL,
                                        `expiry_time` bigint(20) NOT NULL COMMENT '过期时间',
                                        `check_time` bigint(20) NOT NULL,
                                        PRIMARY KEY (`id`) USING BTREE,
                                        UNIQUE KEY `app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for weixin_app_token
-- ----------------------------
DROP TABLE IF EXISTS `weixin_app_token`;
CREATE TABLE `weixin_app_token` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `app_id` varchar(32) NOT NULL COMMENT '手机应用程序软件（微信公众号）编号',
                                       `access_token` varchar(512) NOT NULL,
                                       `refresh_token` varchar(512) NOT NULL,
                                       `expiry_time` bigint(20) NOT NULL COMMENT '过期时间',
                                       `check_time` bigint(20) NOT NULL,
                                       PRIMARY KEY (`id`) USING BTREE,
                                       UNIQUE KEY `app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for weixin_component_ticket
-- ----------------------------
DROP TABLE IF EXISTS `weixin_component_ticket`;
CREATE TABLE `weixin_component_ticket` (
                                              `id` int(11) NOT NULL COMMENT '主键',
                                              `ticket` varchar(512) NOT NULL,
                                              `updated_at` bigint(20) NOT NULL COMMENT '更新时间',
                                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for weixin_component_token
-- ----------------------------
DROP TABLE IF EXISTS `weixin_component_token`;
CREATE TABLE `weixin_component_token` (
                                             `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                             `token` varchar(512) NOT NULL COMMENT '登录标识',
                                             `updated_at` bigint(20) NOT NULL COMMENT '更新时间',
                                             `expiry_time` bigint(20) NOT NULL COMMENT '过期时间',
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for weixin_msg_tpl
-- ----------------------------
DROP TABLE IF EXISTS `weixin_msg_tpl`;
CREATE TABLE `weixin_msg_tpl` (
                                     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `app_id` varchar(64) NOT NULL COMMENT '手机应用程序软件（微信公众号）编号',
                                     `raw_id` varchar(64) NOT NULL,
                                     `tpl_id` varchar(128) NOT NULL,
                                     PRIMARY KEY (`id`) USING BTREE,
                                     UNIQUE KEY `appid_rawid` (`app_id`,`raw_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for weixin_plat_app_token
-- ----------------------------
DROP TABLE IF EXISTS `weixin_plat_app_token`;
CREATE TABLE `weixin_plat_app_token` (
                                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                            `app_id` varchar(32) NOT NULL COMMENT '手机应用程序软件（微信公众号）编号',
                                            `access_token` varchar(512) NOT NULL,
                                            `ticket` varchar(512) NOT NULL,
                                            `expiry_time` bigint(20) NOT NULL COMMENT '过期时间',
                                            `check_time` bigint(20) NOT NULL,
                                            PRIMARY KEY (`id`) USING BTREE,
                                            UNIQUE KEY `app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
