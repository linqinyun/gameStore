CREATE DATABASE IF NOT EXISTS gamestore DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

USE `gamestore`;

# 产品分类表
CREATE TABLE IF NOT EXISTS `gs_product_category`
(
    `product_category_id`   INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `product_category_name` VARCHAR(100)     NOT NULL COMMENT '名称',
    `priority`              int(2)   DEFAULT '0' COMMENT '权重',
    `create_time`           DATETIME DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`product_category_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

# 产品表
CREATE TABLE IF NOT EXISTS `gs_product`
(
    `product_id`          INT(100) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'product_id',
    `product_category_id` INT(11) UNSIGNED  NULL COMMENT '分类ID',
    `product_name`        VARCHAR(100)      NOT NULL COMMENT '名称',
    `product_desc`        VARCHAR(2000)              DEFAULT NULL COMMENT '描述',
    `img_addr`            VARCHAR(2000)              DEFAULT '' COMMENT '缩略图片地址',
    `normal_price`        DECIMAL(10, 2)             DEFAULT 0.00 COMMENT '普通价格',
    `promotion_price`     DECIMAL(10, 2)             DEFAULT 0.00 COMMENT '促销价格',
    `total`               INT(10)                    DEFAULT '0' COMMENT '数量',
    `priority`            INT(2)            NOT NULL DEFAULT '0' COMMENT '优先级',
    `enable_status`       TINYINT(1)                 DEFAULT 0 COMMENT '是否上下架 0未上架默认，1上架展示',
    `create_time`         DATETIME                   DEFAULT NULL COMMENT '创建时间',
    `update_time`         DATETIME                   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`product_id`),
    CONSTRAINT `fk_category_product` FOREIGN KEY (`product_category_id`) REFERENCES `gs_product_category` (`product_category_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

# 产品图片表
CREATE TABLE IF NOT EXISTS `gs_product_img`
(
    `product_img_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '图片id',
    `img_desc`       VARCHAR(100)              DEFAULT '示例图片' COMMENT '图片描述',
    `img_addr`       VARCHAR(2000)             DEFAULT '' COMMENT '图片地址',
    `priority`       INT(2)           NOT NULL DEFAULT '0' COMMENT '优先级',
    `create_time`    DATETIME                  DEFAULT NULL COMMENT '创建时间',
    `product_id`     int(100) UNSIGNED         DEFAULT NULL,
    PRIMARY KEY (`product_img_id`),
    CONSTRAINT `fk_img_product` FOREIGN KEY (`product_id`) REFERENCES `gs_product` (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

# 管理员表
CREATE TABLE IF NOT EXISTS `gs_admin_user`
(
    `admin_user_id` INT(100) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'admin_user_id',
    `username`      VARCHAR(100)      NOT NULL COMMENT '用户名',
    `password`      VARCHAR(100)      NOT NULL COMMENT '密码',
    `create_time`   DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time`   DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`admin_user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;