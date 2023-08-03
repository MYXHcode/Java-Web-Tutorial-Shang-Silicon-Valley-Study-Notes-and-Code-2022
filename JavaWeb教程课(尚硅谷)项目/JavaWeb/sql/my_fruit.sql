CREATE DATABASE IF NOT EXISTS my_fruit CHARSET utf8;

USE my_fruit;

-- 表 t_fruit 的表结构

CREATE TABLE IF NOT EXISTS `t_fruit`
(
    `id`     INT(11)     NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(20) NOT NULL,
    `price`  DECIMAL(8, 2) DEFAULT NULL,
    `count`  INT(11)       DEFAULT NULL,
    `remark` VARCHAR(50)   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8;

-- 表 t_fruit 的数据

INSERT INTO `t_fruit`(`id`, `name`, `price`, `count`, `remark`)
VALUES (1, '红富士', 5, 16, '红富士也是苹果!'),
       (2, '大瓜', 5, 100, '王校长的瓜真香'),
       (3, '南瓜', 4, 456, '水果真好吃'),
       (4, '苦瓜', 5, 55, '苦瓜很好吃'),
       (5, '莲雾', 9, 99, '莲雾是一种神奇的水果'),
       (6, '羊角蜜', 4, 30, '羊角蜜是一种神奇的瓜'),
       (7, '啃大瓜', 13, 123, '孤瓜');
