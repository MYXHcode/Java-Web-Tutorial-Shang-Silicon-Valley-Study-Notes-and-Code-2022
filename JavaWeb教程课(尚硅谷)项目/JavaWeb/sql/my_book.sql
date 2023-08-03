CREATE DATABASE IF NOT EXISTS my_book CHAR SET utf8;

USE my_book;

-- 表 t_user 的表结构

CREATE TABLE IF NOT EXISTS `t_user`
(
    `id`       int(11)     NOT NULL AUTO_INCREMENT,
    `name`     varchar(20) NOT NULL UNIQUE,
    `password` varchar(32) NOT NULL,
    `email`    varchar(100) DEFAULT NULL,
    `role`     tinyint(4)   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8;

-- 表 t_user 的数据

INSERT INTO `t_user`(`id`, `name`, `password`, `email`, `role`)
VALUES (1, 'MYXH', '520.ILY!', '1735350920@qq.com', 0),
       (2, 'root', 'ok', 'root@qq.com', 1);

-- 表 t_book 的表结构

CREATE TABLE IF NOT EXISTS `t_book`
(
    `id`         INT(11)     NOT NULL AUTO_INCREMENT,
    `bookName`   VARCHAR(20) NOT NULL UNIQUE,
    `author`     VARCHAR(20) NOT NULL,
    `price`      DECIMAL(8, 2) DEFAULT NULL,
    `saleCount`  INT(11)       DEFAULT NULL,
    `bookCount`  INT(11)       DEFAULT NULL,
    `bookImage`  VARCHAR(200)  DEFAULT NULL,
    `bookStatus` INT(11)       DEFAULT 0,
    PRIMARY KEY (`id`)
) CHARSET = utf8,
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;

-- 表 t_book 的数据

INSERT INTO t_book(`id`, `bookName`, `author`, `price`, `saleCount`, `bookCount`, `bookImage`, `bookStatus`)
VALUES (1, '皮囊', '蔡崇达', 99.00, 15, 100, 'pinang.jpg', 0),
       (2, '看见', '柴静', 79.00, 10, 50, 'kanjian.jpg', 0),
       (3, '小王子', '安托万', 99.00, 15, 100, 'xiaowangzi.jpg', 0),
       (4, '中国哲学史', '冯友兰', 99.00, 15, 100, 'zhongguozhexueshi.jpg', 0),
       (5, 'C 语言入门经典', '霍尔顿', 99.00, 8, 197, 'cyuyanrumenjingdian.jpg', 0),
       (6, '三体', '刘慈欣', 48.95, 18, 892, 'santi.jpg', 0),
       (7, '艾伦图灵传', '安德鲁', 50.00, 12, 143, 'ailuntulingzhuan.jpg', 0),
       (8, '百年孤独', '马尔克斯', 40.00, 3, 98, 'bainiangudu.jpg', 0),
       (9, '边城', '沈从文', 30.00, 2, 99, 'biancheng.jpg', 0),
       (10, '解忧杂货店', '东野圭吾', 27.00, 5, 100, 'jieyouzahuodian.jpg', 0),
       (11, '忽然七日', '劳伦', 19.00, 50, 200, 'huranqiri.jpg', 0),
       (12, '苏东坡传', '林语堂', 20.00, 50, 300, 'sudongpozhuan.jpg', 0),
       (13, '扶桑', '严歌岑', 20.00, 10, 89, 'fusang.jpg', 0),
       (14, '给孩子的诗', '北岛', 23.00, 5, 99, 'geihaizideshi.jpg', 0),
       (15, '活着', '余华', 11.00, 20, 100, 'huozhe.jpg', 0);

-- 表 t_cart_item 的表结构

CREATE TABLE IF NOT EXISTS `t_cart_item`
(
    `id`       INT(11) NOT NULL AUTO_INCREMENT,
    `book`     INT(11) NOT NULL,
    `buyCount` INT(11) DEFAULT NULL,
    `user`     INT(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_cart_book` (`book`),
    KEY `FK_cart_user` (`user`),
    CONSTRAINT `FK_cart_book` FOREIGN KEY (`book`) REFERENCES `t_book` (`id`),
    CONSTRAINT `FK_cart_user` FOREIGN KEY (`user`) REFERENCES `t_user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8;

-- 表 t_cart_item 的数据

INSERT INTO `t_cart_item` (`id`, `book`, `buyCount`, `user`)
VALUES (1, 3, 1, 1),
       (2, 6, 1, 1),
       (3, 7, 1, 1),
       (4, 8, 1, 1),
       (5, 10, 1, 1),
       (6, 14, 1, 1);

-- 表 t_order 的表结构

CREATE TABLE IF NOT EXISTS `t_order`
(
    `id`          INT(11)      NOT NULL AUTO_INCREMENT,
    `orderNo`     VARCHAR(128) NOT NULL,
    `orderDate`   DATETIME     NOT NULL,
    `orderUser`   INT(11)       DEFAULT NULL,
    `orderMoney`  DECIMAL(8, 2) DEFAULT NULL,
    `orderStatus` TINYINT(4)    DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `orderNo` (`orderNo`),
    KEY `FK_order_user` (`orderUser`),
    CONSTRAINT `FK_order_user` FOREIGN KEY (`orderUser`) REFERENCES `t_user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;

-- 表 t_order 的数据

INSERT INTO `t_order` (`id`, `orderNo`, `orderDate`, `orderUser`, `orderMoney`, `orderStatus`)
VALUES (1, '1df772e7-2ab2-41af-9f30-002f9d1afed8_20237231200', '2023-07-23 12:00:00', 1, 287.95, 0);

-- 表 t_order_item 的表结构

CREATE TABLE IF NOT EXISTS `t_order_item`
(
    `id`       INT(11) NOT NULL AUTO_INCREMENT,
    `book`     INT(11) DEFAULT NULL,
    `buyCount` INT(11) DEFAULT NULL,
    `order`    INT(11) DEFAULT NULL,
    PRIMARY KEY (id),
    KEY `FK_item_book` (`book`),
    KEY `FK_item_order` (`order`),
    CONSTRAINT `FK_item_book` FOREIGN KEY (`book`) REFERENCES `t_book` (`id`),
    CONSTRAINT `FK_item_order` FOREIGN KEY (`order`) REFERENCES `t_order` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8;

-- 表 t_order_item 的数据

INSERT INTO `t_order_item` (`id`, `book`, `buyCount`, `order`)
VALUES (1, 3, 1, 1),
       (2, 6, 1, 1),
       (3, 7, 1, 1),
       (5, 8, 1, 1),
       (6, 10, 1, 1),
       (7, 14, 1, 1);
