CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `real_name` varchar(20) NOT NULL COMMENT '真实名称',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(20) NOT NULL COMMENT '加密盐',
  `sex` tinyint(2) NOT NULL DEFAULT '10' COMMENT '性别10:男;11:女;12:其他',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像路径',
  `status` tinyint(2) NOT NULL DEFAULT '10' COMMENT '状态10:正常;11:锁定;12:注销',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识0:未删除;1:已删除',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';
INSERT INTO sys_user (user_name,real_name,password,salt,sex,avatar,status,del_flag,create_by,create_time,update_by,update_time,remark) VALUES
('admin','超级管理员','$2a$10$j3XmUHGzMFLZFH.Qioq4Z.nM/iFMd4Wk6GDI.mC7U2yIztdyV6oUe','111111',10,'',10,0,'admin','2020-09-30 08:34:18.0','admin','2020-09-30 08:34:18.0','')
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_code` varchar(20) NOT NULL COMMENT '角色编号',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '10' COMMENT '角色状态10:正常;11:停用',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识0:未删除;1:已删除',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';
INSERT INTO sys_role (role_code,role_name,status,del_flag,create_by,create_time,update_by,update_time,remark) VALUES
('admin','超级管理员',10,0,'admin',NULL,'admin',NULL,NULL);
CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) unsigned DEFAULT '0' COMMENT '父级菜单ID',
  `order_num` tinyint(2) unsigned DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(100) NOT NULL DEFAULT '#' COMMENT '请求地址',
  `menu_type` tinyint(2) unsigned DEFAULT NULL COMMENT '菜单类型10:目录;20:菜单;30:按钮',
  `visible` tinyint(2) unsigned NOT NULL DEFAULT '10' COMMENT '菜单状态10:显示;20:隐藏',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) NOT NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT='菜单表';
INSERT INTO sys_menu (menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark) VALUES
('系统管理',0,1,'#',10,10,'','#','admin',NULL,'admin',NULL,NULL)
,('用户管理',1,1,'/system/user',20,10,'user:view','#','admin',NULL,'admin',NULL,NULL)
,('用户查询',10,1,'#',30,10,'user:list','#','admin',NULL,'admin',NULL,NULL)
,('用户新增',10,2,'#',30,10,'user:add','#','admin',NULL,'admin',NULL,NULL)
,('用户修改',10,3,'#',30,10,'user:update','#','admin',NULL,'admin',NULL,NULL)
,('用户删除',10,4,'#',30,10,'user:delete','#','admin',NULL,'admin',NULL,NULL);
CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色表';
INSERT INTO sys_user_role (user_id,role_id) VALUES (1,1);
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) unsigned NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';
INSERT INTO sys_role_menu (role_id,menu_id) VALUES
(1,1)
,(1,10)
,(1,101)
,(1,102)
,(1,103)
,(1,104);
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE = InnoDB CHARACTER SET = utf8;