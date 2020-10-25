CREATE TABLE `springboot`.`persistent_logins`  (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp(255) NOT NULL DEFAULT CURRENT_TIMESTAMP(255) ON UPDATE CURRENT_TIMESTAMP(255),
  PRIMARY KEY (`series`)
) ENGINE = InnoDB CHARACTER SET = utf8;