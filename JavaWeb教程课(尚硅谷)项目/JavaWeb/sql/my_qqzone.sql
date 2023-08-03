CREATE DATABASE IF NOT EXISTS my_qqzone CHARSET utf8;

USE my_qqzone;

-- 表 t_user_basic 的表结构

CREATE TABLE IF NOT EXISTS `t_user_basic`
(
    `id`        INT(11)     NOT NULL AUTO_INCREMENT,
    `loginId`   VARCHAR(20) NOT NULL,
    `nickName`  VARCHAR(50) NOT NULL,
    `password`  VARCHAR(20) NOT NULL,
    `headImage` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `loginId` (`loginId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

-- 表 t_user_basic 的数据

INSERT INTO `t_user_basic`(`id`, `loginId`, `nickName`, `password`, `headImage`)
VALUES (1, 'u001', '末影小黑xh', '520.ILY!', '末影小黑xh.png'),
       (2, 'u002', '大户爱', 'ok', '大户爱.png'),
       (3, 'u003', '爱酱', 'ok', '爱酱.png'),
       (4, 'u004', '薇尔莉特', 'ok', '薇尔莉特.png'),
       (5, 'u005', '不死', 'ok', '不死.png');

-- 表 t_user_detail 的表结构

CREATE TABLE IF NOT EXISTS `t_user_detail`
(
    `id`       INT(11) NOT NULL,
    `realName` VARCHAR(20) DEFAULT NULL,
    `tel`      VARCHAR(11) DEFAULT NULL,
    `email`    VARCHAR(30) DEFAULT NULL,
    `birth`    DATETIME    DEFAULT NULL,
    `star`     VARCHAR(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_detail_basic` FOREIGN KEY (`id`) REFERENCES `t_user_basic` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

-- 表 t_user_detail 的数据

INSERT INTO t_user_detail(id, realName, tel, email, birth, star)
VALUES (1, '邓磊', '18812612826', '1735350920@qq.com', '2002-01-09', '摩羯座'),
       (2, '大户爱', '13888888888', 'zhangsan@qq.com', '2001-01-01', '金牛座'),
       (3, '爱酱', '13999999999', 'lisi@qq.com', '2002-02-02', '水瓶座'),
       (4, '薇尔莉特', '13666666666', 'wangwu@qq.com', '2003-03-03', '双鱼座'),
       (5, '不死', '13555555555', 'zhaoliu@qq.com', '2004-04-04', '白羊座');

-- 表 t_friend 的表结构

CREATE TABLE IF NOT EXISTS `t_friend`
(
    `id`  INT(11) NOT NULL AUTO_INCREMENT,
    `uid` INT(11) DEFAULT NULL,
    `fid` INT(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_friend_basic_uid` (`uid`),
    KEY `FK_friend_basic_fid` (`fid`),
    CONSTRAINT `FK_friend_basic_fid` FOREIGN KEY (`fid`) REFERENCES `t_user_basic` (`id`),
    CONSTRAINT `FK_friend_basic_uid` FOREIGN KEY (`uid`) REFERENCES `t_user_basic` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8;

-- 表 t_friend 的数据

INSERT INTO `t_friend`(`id`, `uid`, `fid`)
VALUES (1, 1, 2),
       (2, 1, 3),
       (3, 1, 4),
       (4, 1, 5),
       (5, 2, 1),
       (6, 2, 3),
       (7, 3, 1),
       (8, 3, 2),
       (9, 4, 1),
       (10, 5, 1);

-- 表 t_topic 的表结构

CREATE TABLE IF NOT EXISTS `t_topic`
(
    `id`        INT(11)      NOT NULL AUTO_INCREMENT,
    `title`     VARCHAR(100) NOT NULL,
    `content`   VARCHAR(500) NOT NULL,
    `topicDate` DATETIME     NOT NULL,
    `author`    INT(11)      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_topic_basic` (`author`),
    CONSTRAINT `FK_topic_basic` FOREIGN KEY (`author`) REFERENCES `t_user_basic` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

-- 表 t_topic 的数据

INSERT INTO `t_topic`(`id`, `title`, `content`, `topicDate`, `author`)
VALUES (1, '我的空间开通了，先做一下自我介绍！', '大家好，我是大户爱！', '2023-07-11 14:19:00', 1);


-- 表 t_reply 的表结构

CREATE TABLE IF NOT EXISTS `t_reply`
(
    `id`        INT(11)      NOT NULL AUTO_INCREMENT,
    `content`   VARCHAR(500) NOT NULL,
    `replyDate` DATETIME     NOT NULL,
    `author`    INT(11)      NOT NULL,
    `topic`     INT(11)      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_reply_basic` (`author`),
    KEY `FK_reply_topic` (`topic`),
    CONSTRAINT `FK_reply_basic` FOREIGN KEY (`author`) REFERENCES `t_user_basic` (`id`),
    CONSTRAINT `FK_reply_topic` FOREIGN KEY (`topic`) REFERENCES `t_topic` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8;

-- 表 t_reply 的数据

INSERT INTO `t_reply`(`id`, `content`, `replyDate`, `author`, `topic`)
VALUES (1, '这里是回复1', '2023-07-17 14:20:00', 2, 1),
       (2, '这里是回复2', '2023-07-17 14:21:00', 2, 1);

-- 表 t_host_reply 的表结构

CREATE TABLE IF NOT EXISTS `t_host_reply`
(
    `id`            INT(11)      NOT NULL AUTO_INCREMENT,
    `content`       VARCHAR(500) NOT NULL,
    `hostReplyDate` DATETIME     NOT NULL,
    `author`        INT(11)      NOT NULL,
    `reply`         INT(11)      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_host_basic` (`author`),
    KEY `FK_host_reply` (`reply`),
    CONSTRAINT `FK_host_basic` FOREIGN KEY (`author`) REFERENCES `t_user_basic` (`id`),
    CONSTRAINT `FK_host_reply` FOREIGN KEY (`reply`) REFERENCES `t_reply` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

-- 表 t_host_reply 的数据

INSERT INTO `t_host_reply`(`id`, `content`, `hostReplyDate`, `author`, `reply`)
VALUES (1, '这里是主人回复', '2023-07-17 14:22:00', 1, 1);
