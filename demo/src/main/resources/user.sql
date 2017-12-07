-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `springroot`.`user`;
CREATE TABLE `springroot`.`user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `account` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `aaabb` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `springroot`.`user` VALUES ('72', '超级管理员', 'admin');