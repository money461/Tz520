/*
 Navicat Premium Data Transfer

 Source Server         : tz520
 Source Server Type    : MySQL
 Source Server Version : 100032
 Source Host           : 116.62.174.247:3306
 Source Schema         : tz520

 Target Server Type    : MySQL
 Target Server Version : 100032
 File Encoding         : 65001

 Date: 26/04/2018 16:50:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tz_520web
-- ----------------------------
DROP TABLE IF EXISTS `tz_520web`;
CREATE TABLE `tz_520web`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `web_introduce` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '520网站信息介绍',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '该信息创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '该信息更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '520商城信息介绍表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tz_app
-- ----------------------------
DROP TABLE IF EXISTS `tz_app`;
CREATE TABLE `tz_app`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'app名称',
  `down` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下载地址',
  `version` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作记录人',
  `updated_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新的信息',
  `is_force` int(2) NULL DEFAULT 0 COMMENT '是否强制更新 1：是  0：否',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_app
-- ----------------------------
INSERT INTO `tz_app` VALUES (59, 'love520', 'group1/M00/00/12/rBBH51olHHaAISzaAIPwDguw0Ys037.apk?filename=love520_v1.0.0.apk', 'v1.0.0', '2017-12-04 17:59:33', '2017-12-04 17:59:33', '96479df69b21468ca8a1e0ce754df5bb', NULL, 0, 1);
INSERT INTO `tz_app` VALUES (60, 'love520', 'group1/M00/00/12/rBBH51onxwCALVI3AIQIE3Q1HAs067.apk?filename=love520_v1.0.1.apk', 'v1.0.1', '2017-12-06 18:31:33', '2017-12-06 18:31:33', '96479df69b21468ca8a1e0ce754df5bb', '版本更新：|1、app系统性能优化；|2、修复了未登录清空购物车失效。', 1, 2);
INSERT INTO `tz_app` VALUES (61, 'love520', 'group1/M00/00/13/rBBH51oqX3aAJTDfAIQem-eaxnA457.apk?filename=love520_v1.0.3.apk', 'v1.0.3', '2017-12-08 17:46:33', '2017-12-08 17:46:33', '96479df69b21468ca8a1e0ce754df5bb', NULL, 1, 3);
INSERT INTO `tz_app` VALUES (62, 'love520', 'group1/M00/00/13/rBBH51o3leGAULqwAIQp-V-dak0643.apk?filename=love520_v1.0.4.apk', 'v1.0.4', '2017-12-18 18:18:19', '2017-12-18 18:18:19', '96479df69b21468ca8a1e0ce754df5bb', '版本更新：|1、注册后闪退问题；|2、界面优化。', 1, 4);
INSERT INTO `tz_app` VALUES (63, 'love520', 'group1/M00/00/13/rBBH51pVj8aAClIIAITDtkjSjRk603.apk?filename=love520_v1.0.5.apk', 'v1.0.5', '2018-01-10 12:00:10', '2018-01-10 12:00:10', '96479df69b21468ca8a1e0ce754df5bb', '版本更新：|1、修复了会员分享二维码注册失效；|2、修复了一些其他bug。', 1, 5);

-- ----------------------------
-- Table structure for tz_auth_function
-- ----------------------------
DROP TABLE IF EXISTS `tz_auth_function`;
CREATE TABLE `tz_auth_function`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '父级点编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `page` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `generatemenu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否生成菜单',
  `zindex` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优先级',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_auth_function
-- ----------------------------
INSERT INTO `tz_auth_function` VALUES ('0', '', '权限目录', 'authfunction', '所有权限都在该目录下', NULL, '1', NULL, '96479df69b21468ca8a1e0ce754df5bb', '2017-11-01 15:35:25', '2017-11-01 15:35:35');
INSERT INTO `tz_auth_function` VALUES ('069de4a4334340018c9811a59ebd2bc7', '0', '会员管理权限', 'memberManager', '操作会员管理列表信息', 'http://192.168.10.30:8080/tz/admin/user', '1', '2', '001', '2017-12-01 10:13:19', '2017-12-01 10:13:19');
INSERT INTO `tz_auth_function` VALUES ('06d355757fac4907a97fb6f9a4bc4db4', '069de4a4334340018c9811a59ebd2bc7', '爱心值消费明细列表查询权限', 'queryLoveValueConsume', '该权限可以对爱心值消费明细列表进行查询操作', 'http://192.168.10.30:8080/tz/admin/user', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:34:30', '2017-12-04 16:34:30');
INSERT INTO `tz_auth_function` VALUES ('0949fba7524741418315c4313370448a', '32ff800897b74990ab4b34317741b490', '快递管理列表查询权限', 'queryExpress', '该权限可以查询快递管理列表信息', 'http://192.168.10.30:8080/tz/admin/express', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 13:14:17', '2017-12-05 13:14:17');
INSERT INTO `tz_auth_function` VALUES ('105c29f5ee2244ff8d0316b1cbb45a35', 'b35bd7c0d7f9458e9d231ea2d821150e', '修改会员权限', 'updateUser', '该权限可以修改会员信息操作', 'http://192.168.10.30/tz/admin/user/updateUser', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:02:16', '2017-12-04 16:07:10');
INSERT INTO `tz_auth_function` VALUES ('10f59d3cd63c4f07b7e1fb0f663c65e2', '0', '管理员管理权限', 'manager', '对管理员管理列表信息操作', 'http://192.168.10.30:8080/tz/admin/manager', '1', '1', '001', '2017-12-01 10:10:26', '2017-12-01 10:10:26');
INSERT INTO `tz_auth_function` VALUES ('139eac53150c46be899b084adae9ebb0', '2c62a2a15ff649f7872805fba37a3bd3', '发货权限', 'sendOrder', '该权限可以发货处理', 'http://192.168.10.30/tz/admin/order', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 14:46:56', '2017-12-04 14:46:56');
INSERT INTO `tz_auth_function` VALUES ('1d1c2df77bde466bbd4dabbc446e61d8', 'a44d0e8d134d40b9b52ff4f17b0a3f80', '修改内容分类权限', 'updateContentCategory', '该权限可以对某一内容分类进行修改操作', 'http://192.168.10.30:8080/tz/admin/contentCategory', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 09:30:51', '2017-12-05 09:30:51');
INSERT INTO `tz_auth_function` VALUES ('258abf9022ff4ce0aacfc9fd8ba4489f', '71018a6a85cf4318a89923e4020e0ce0', '意向用户列表查询权限', 'queryIntentionalUser', '该权限可以对意向用户列表信息进行查询', 'http://192.168.10.30/tz/admin/intentionalUser', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 13:16:33', '2017-12-05 13:16:33');
INSERT INTO `tz_auth_function` VALUES ('28890b0d6a434a26b10dabebb7bbb658', 'd30ea60913404d30848659ad455a640b', '添加商品分类权限', 'addItemCategory', '该权限可以多商品分类添加操作', 'http://192.168.10.30/8080/tz/itemCategory', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 11:14:46', '2017-12-04 11:29:11');
INSERT INTO `tz_auth_function` VALUES ('2c62a2a15ff649f7872805fba37a3bd3', 'ba33f15525c64b219fe6097619c5a712', '查询订单列表权限', 'queryOrder', '该权限可以查询订单列表信息', 'http://192.168.1.30:8080/tz/admin/order', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 14:39:54', '2017-12-04 14:39:54');
INSERT INTO `tz_auth_function` VALUES ('32088b30731c4342b06fd4f3c65f38d7', 'fce230c0f8404e11bf1af7246b3275fa', '删除某一推荐记录权限', 'delCommend', '该权限是可以对推荐管理列表中某一记录进行删除操作', 'http://192.1368.10.30/tz/admin/commend/delComemend', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:17:55', '2017-12-04 16:17:55');
INSERT INTO `tz_auth_function` VALUES ('32ff800897b74990ab4b34317741b490', '0', '物流管理权限', 'expressManager', '操作物流管理信息', 'http://192.168.10.30:8080/tz/admin/express', '1', '6', '001', '2017-12-01 10:30:24', '2017-12-01 10:30:24');
INSERT INTO `tz_auth_function` VALUES ('34419249936640a6be9e8c5e6b39fea2', '2c62a2a15ff649f7872805fba37a3bd3', '查询订单详情权限', 'queryOrderDetail', '该权限可以查看某一订单详细信息', 'http://192.168.10.30/tz/admin/order/queryOrderDetail', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 14:41:58', '2017-12-04 14:41:58');
INSERT INTO `tz_auth_function` VALUES ('37f7ad5d097749c3bdbd9597a1096000', 'b35bd7c0d7f9458e9d231ea2d821150e', '添加会员权限', 'addUser', '该权限可以添加会员用户操作', 'http://192.168.10.30/tz/admin/user/addUser', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:52:06', '2017-12-04 16:06:53');
INSERT INTO `tz_auth_function` VALUES ('4190ad5abde04aad9b393be105e3f92f', 'b35bd7c0d7f9458e9d231ea2d821150e', '冻结会员权限', 'frozenUser', '该权限可以对某一会员冻结操作', 'http://192.168.10.30/tz/admin/user', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:05:11', '2017-12-04 16:06:02');
INSERT INTO `tz_auth_function` VALUES ('4e2f3e78f9be43688d25d38fa2f6749f', '10f59d3cd63c4f07b7e1fb0f663c65e2', '权限列表信息查询权限', 'queryFucntion', '该权限可以对权限列表信息进行查询操作', 'http://192.168.10.30:8080/tz/admin/funtion', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 09:42:34', '2017-12-05 09:42:34');
INSERT INTO `tz_auth_function` VALUES ('4efaba06cb9543f682b6d6eff4239801', '9d0fbc88bb7845eca549621f4078f57e', '添加商品权限', 'addItem', '该权限可以对商品进行添加操作', 'http://192.168.10.30/tz/admin/item/addmanager', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-01 10:52:47', '2017-12-04 11:30:18');
INSERT INTO `tz_auth_function` VALUES ('532b4ffc48ed4fae9a61dc1e844bd075', '06d355757fac4907a97fb6f9a4bc4db4', '删除某一用户爱心值消费记录权限', 'delLoveValueConsume', '该权限可以对删除某一用户爱心值消费记录进行操作', 'http://192.168.10.30:8080/tz/admin', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:38:39', '2017-12-04 16:38:39');
INSERT INTO `tz_auth_function` VALUES ('5c0855c073374b4cb7642fb6ef75f392', '10f59d3cd63c4f07b7e1fb0f663c65e2', '角色管理查询权限', 'queryRole', '该权限可以对角色信息进行查询操作', 'http://192.168.10.30:8080/tz/admin/manager', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 09:37:26', '2017-12-05 09:37:26');
INSERT INTO `tz_auth_function` VALUES ('6008e429b50f4ddd8a31f0243af148d1', 'd402046784104c4a8a713142aa33a5f6', '删除内容权限', 'delContent', '该权限可以对内容进行删除', 'http:19.2.168.10.30:8080/tz/admin/content/delContent', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 14:35:40', '2017-12-04 14:35:40');
INSERT INTO `tz_auth_function` VALUES ('62064c349f4b469993ef8f7b05272d6f', 'd30ea60913404d30848659ad455a640b', '删除商品分类权限', 'delItemCategory', '该权限可以删除商品分类操作', 'http://192.168.10.30/tz/admin/itemCategory', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 11:21:59', '2017-12-04 15:30:44');
INSERT INTO `tz_auth_function` VALUES ('6479e8bad7e94952ae2f417a8c8bf4fc', 'd402046784104c4a8a713142aa33a5f6', '修改内容权限', 'updateContent', '该权限可以对内容进行修改操作', 'http://192.168.10.30/tz/admin/content/updateContent', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 14:32:32', '2017-12-04 14:32:44');
INSERT INTO `tz_auth_function` VALUES ('71018a6a85cf4318a89923e4020e0ce0', '0', '意向用户管理权限', 'intentionalUserManger', '操作意向用户管理权限', 'http://192.168.10.30:8080/tz/admin/Intentional', '1', '7', '001', '2017-12-01 10:32:20', '2017-12-01 10:32:20');
INSERT INTO `tz_auth_function` VALUES ('71db7597bb2343409d6b467cd93f4a93', 'b35bd7c0d7f9458e9d231ea2d821150e', '查询我的推荐/我的团队/我的合伙人权限', 'queryUser*', '该权限可以对某一用户的推荐、团队、合伙人进行详情查询操作', 'http://192.168.10.30:8080/tz/admin/user', '0', '4', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:12:42', '2017-12-04 16:12:42');
INSERT INTO `tz_auth_function` VALUES ('822b52b7607b417588daeac1626dc8fd', 'a44d0e8d134d40b9b52ff4f17b0a3f80', '添加内容分类权限', 'addContentCategory', '该权限可以对内容分类进行添加操作', 'http://192.168.10.30:8080/tz/admin/contentCategory', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 09:29:07', '2017-12-05 10:45:01');
INSERT INTO `tz_auth_function` VALUES ('84f4d26c511744e484df165e2fdba411', '9d0fbc88bb7845eca549621f4078f57e', '商品上下架权限', 'itemUpOrDown', '可以对商品上下架处理操作', 'http://192.168.10.30:8080/tz/admin/item/upOrDown', '0', '4', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-01 14:45:41', '2017-12-04 11:31:06');
INSERT INTO `tz_auth_function` VALUES ('8b887cc1bc454cf599304f692b9819ae', 'a44d0e8d134d40b9b52ff4f17b0a3f80', '删除内容分类权限', 'delContentCategory', '该权限可以对某一内容分类进行删除操作', 'http://192.168.10.30:9090/tz/admin/contentCategory', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 09:32:44', '2017-12-05 09:32:44');
INSERT INTO `tz_auth_function` VALUES ('9d0fbc88bb7845eca549621f4078f57e', 'f13e9c7224d746bbbe20b6538b32bef8', '商品列表查询权限', 'queryItem', '该权限可以进行查询商品信息操作', 'http://192.168.10.30/tz/admin/queryItem', '1', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 11:25:09', '2017-12-04 11:25:09');
INSERT INTO `tz_auth_function` VALUES ('a44d0e8d134d40b9b52ff4f17b0a3f80', 'c945e885beb34c5c83b8f04698549bb9', '内容分类列表查询权限', 'queryContentCategory', '该权限乐意查询内容分类列表信息操作', 'http://192.168.10.30:8080/tz/admin/contentCategory', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 09:26:16', '2017-12-05 09:26:16');
INSERT INTO `tz_auth_function` VALUES ('a713ab2f73de4067909fd6318681a7ff', '2c62a2a15ff649f7872805fba37a3bd3', '编辑/修改物流权限', 'updateExpress', '该权限可以编辑修改某一订单收货人信息', 'http://192.168.10.30/tz/admin/order/updateExpress', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 14:45:16', '2017-12-04 14:45:16');
INSERT INTO `tz_auth_function` VALUES ('a992cd21a7d8413db61042f208da321f', '069de4a4334340018c9811a59ebd2bc7', '爱心值体现管理列表查询权限', 'queryLoveValueWithdraws', '该权限可以查询爱心值提现管理列表信息操作', 'http://192.168.10.30:8080/tz/admin/user', '0', '4', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 09:17:16', '2017-12-05 09:17:16');
INSERT INTO `tz_auth_function` VALUES ('b35bd7c0d7f9458e9d231ea2d821150e', '069de4a4334340018c9811a59ebd2bc7', '会员列表查询权限', 'queryUser', '该权限可以查询会员列表信息', 'http://192.168.10.30/tz/admin/user/querUser', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:50:01', '2017-12-04 15:50:01');
INSERT INTO `tz_auth_function` VALUES ('b59dbe4406234784867dbd56f5b030c3', '9d0fbc88bb7845eca549621f4078f57e', '修改商品权限', 'updateItem', '该权限可以对商品进行修改操作', 'http://192.168.10.30:8080/tz/admin/updateItem', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-01 10:55:09', '2017-12-04 11:30:35');
INSERT INTO `tz_auth_function` VALUES ('ba33f15525c64b219fe6097619c5a712', '0', '订单管理权限', 'orderManager', '操作订单管理列表信息', 'http://192.168.10.30:8080/tz/admin/order', '1', '5', '001', '2017-12-01 10:27:14', '2017-12-01 10:27:14');
INSERT INTO `tz_auth_function` VALUES ('c945e885beb34c5c83b8f04698549bb9', '0', '内容管理权限', 'contentManager', '操作内容管理信息', 'http://192.168.10.30:8080/tz/admin/content', '1', '4', '001', '2017-12-01 10:25:40', '2017-12-01 10:25:40');
INSERT INTO `tz_auth_function` VALUES ('cb9d4425d8324c3299e1dbb5fd775d0e', 'ba33f15525c64b219fe6097619c5a712', '导出订单列表数据信息', 'exportOrderData', '该权限可以下载生成excel表格', 'http://192.168.10.30/tz/order/exportOrder', '0', '6', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-11 09:26:19', '2017-12-11 09:26:19');
INSERT INTO `tz_auth_function` VALUES ('cf48e9158b5d4163a49e0dc6d1dff1f8', 'f5d227ce91ef4b9aa6e3be9f4e310fc3', '删除某一用户的爱心值信息权限', 'delLoveValue', '该权限可以对某一用户的爱心值进行删除操作', 'http://192.168.10.30:8080/tz/admin', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:30:54', '2017-12-04 16:31:06');
INSERT INTO `tz_auth_function` VALUES ('d30ea60913404d30848659ad455a640b', 'f13e9c7224d746bbbe20b6538b32bef8', '商品分类列表查询权限', 'queryItemCategory', '该权限可以对商品分类进行查询操作', 'http://192.168.10.30/tz/admin/itemcategory', '1', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 11:27:37', '2017-12-04 11:27:46');
INSERT INTO `tz_auth_function` VALUES ('d402046784104c4a8a713142aa33a5f6', 'c945e885beb34c5c83b8f04698549bb9', '内容列表查询权限', 'queryContent', '该权限可以查询内容列表信息', 'http://192.168.10.30/tz/admin/queryContent', '1', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 11:43:03', '2017-12-04 11:43:03');
INSERT INTO `tz_auth_function` VALUES ('d4e8328ee5644a389a9381023cf8acd6', 'b35bd7c0d7f9458e9d231ea2d821150e', '删除会员权限', 'delUser', '该权限可以删除某一会员操作', 'http://192.168.10.30:8080/tz/admin/user', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:09:09', '2017-12-04 16:09:09');
INSERT INTO `tz_auth_function` VALUES ('d8d811b49e3543b88f921dc57870162f', '10f59d3cd63c4f07b7e1fb0f663c65e2', '查询管理员列表信息权限', 'queryManager', '该权限可以对管理员列表信息进行查询操作', 'http://192.168.10.30:8080/tz/admin/manager', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 09:35:24', '2017-12-05 09:35:24');
INSERT INTO `tz_auth_function` VALUES ('e218390dff224fa9a804fe1c57d5d0e1', '9d0fbc88bb7845eca549621f4078f57e', '删除商品权限', 'delItem', '该权限可以对商品进行删除', 'http://192.168.10.30:8080/tz/admin/delItem', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-01 10:57:08', '2017-12-04 11:30:54');
INSERT INTO `tz_auth_function` VALUES ('e56e2dff864f4390b84e6952f822e6e6', 'ba33f15525c64b219fe6097619c5a712', '查看订单总金额和商品总数量信息', 'queryOrderInfo', '该权限可以查询订单总金额和总商品数量', 'http://192.168.10.30/8080/order', '0', '4', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-19 11:37:37', '2017-12-19 11:37:37');
INSERT INTO `tz_auth_function` VALUES ('e8f575305fce49c3a9a5d220718877b8', 'a992cd21a7d8413db61042f208da321f', '导出爱心值提现列表信息', 'exportLoveValue', '该权限可以对爱心值提现信息导出excel操作', 'http://192.168.10.30/tz/admin', '0', '3', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-11 09:28:49', '2017-12-11 09:28:49');
INSERT INTO `tz_auth_function` VALUES ('e9142879ecbc4c4fbc745a6168f6ccec', '2c62a2a15ff649f7872805fba37a3bd3', '修改订单权限', 'updateOrder', '该权限可以对订单进行修改操作', 'http://192.168.10.30/tz/admin/order/updateOrder', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 14:49:34', '2017-12-04 14:49:34');
INSERT INTO `tz_auth_function` VALUES ('ebdf5205737c43e495fd74e5409bcedb', 'd30ea60913404d30848659ad455a640b', '修改商品分类权限', 'updateItemCategory', '该权限可以对商品分类进行修改操作', 'http://192.168.10.30/tz/admin/itemCategory', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 11:19:53', '2017-12-04 11:28:43');
INSERT INTO `tz_auth_function` VALUES ('ee24b7ae92474b82ace2128f15478cc1', 'f5d227ce91ef4b9aa6e3be9f4e310fc3', '冻结某一用户爱心值权限', 'frozenLoveValue', '该权限可以对某一用户的爱心值进行冻结不能使用操作', 'http://192.168.10.30:8080/tz/admin', '0', '2', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:28:36', '2017-12-04 16:28:36');
INSERT INTO `tz_auth_function` VALUES ('f13e9c7224d746bbbe20b6538b32bef8', '0', '商品管理列表权限', 'itemManager', '操作商品管理列表', 'http://192.168.10.30/tz/admin/item', '1', '3', '001', '2017-12-01 10:22:19', '2017-12-01 10:22:19');
INSERT INTO `tz_auth_function` VALUES ('f503d9d6ec56430992ef43270514fe5b', 'a992cd21a7d8413db61042f208da321f', '删除爱心值提现管理列表中某一记录权限', 'delLoveValueWithdraws', '该权限可以对爱心值提现管理列表信息中某一记录进行删除操作', 'http://192.168.10.30:8080/tz/admin/user', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 09:20:17', '2017-12-05 09:20:17');
INSERT INTO `tz_auth_function` VALUES ('f5d227ce91ef4b9aa6e3be9f4e310fc3', '069de4a4334340018c9811a59ebd2bc7', '爱心值管理列表查询权限', 'queryLoveValue', '该权限可以对爱心值管理列表查询操作', 'http://192.168.10.30/tz/admin/user/queryLoveValue', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:25:40', '2017-12-04 16:25:40');
INSERT INTO `tz_auth_function` VALUES ('f5dd166e37044b08956c3b85ad9bf215', 'd402046784104c4a8a713142aa33a5f6', '添加内容权限', 'addContent', '该权限可以对内容进行添加操作', 'http://192.1687.10.30/tz/admin/itemContent', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 11:54:17', '2017-12-04 11:54:17');
INSERT INTO `tz_auth_function` VALUES ('fbaf464c9fd2458680112a90c52e81ff', '0', '系统管理权限', 'systemManager', '操作系统管理列表信息', 'http://192.168.10.30:9090/tz/admin/system', '1', '8', '001', '2017-12-01 10:35:45', '2017-12-01 10:35:45');
INSERT INTO `tz_auth_function` VALUES ('fce230c0f8404e11bf1af7246b3275fa', '069de4a4334340018c9811a59ebd2bc7', '推荐管理列表查询权限', 'commendManager', '该权限可以对推荐管理进行列表查询操作', 'http://192.168.10.30:8080/tz/admin/commend', '0', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 16:15:20', '2017-12-04 16:15:20');

-- ----------------------------
-- Table structure for tz_cart
-- ----------------------------
DROP TABLE IF EXISTS `tz_cart`;
CREATE TABLE `tz_cart`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id 指定该购物车信息是哪个用户的',
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品编号id',
  `num` int(5) NOT NULL COMMENT '商品已经选中购买的数量',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '该记录创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '该记录修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`item_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户添加商品至购物车展示信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_cart
-- ----------------------------
INSERT INTO `tz_cart` VALUES ('04ea4321223445dca061fd4f644bd3b7', 'a2abd46b9a2e467bb99a491cd177e095', 'c4cd1d8d16fa4bcab350ca756ec02684', 1, '2017-11-21 10:47:01', '2017-11-21 10:47:01');
INSERT INTO `tz_cart` VALUES ('10b2796afd0249aca85e0cf77afa5535', 'fccc0ab9b68047d682ede1b5cd111a4a', '7a18f1ded4b04d758963a8f04e5e9108', 1, '2017-11-21 15:47:39', '2017-11-21 15:47:39');
INSERT INTO `tz_cart` VALUES ('11cdd4a06a6d4d0f8afe7adc4ee07769', '6d3b18c0050a4a13ae4e687ac269edf2', 'c4cd1d8d16fa4bcab350ca756ec02684', 6, '2017-11-22 17:45:49', '2017-11-23 12:06:13');
INSERT INTO `tz_cart` VALUES ('129b028334f24acf984d61f7884b4646', '45289be39735424cbcbac90b70066e3d', '0be61153c5b0407c8d627e2727e2f949', 1, '2017-11-20 18:21:44', '2017-11-20 18:21:44');
INSERT INTO `tz_cart` VALUES ('12b866c2336044508ffe9ec9b8ac6478', 'f1a88380463645bca982a9b626ef3c6a', 'be1836fe6a37498e853c0a52d0a26177', 1, '2017-12-18 09:09:49', '2017-12-18 09:09:49');
INSERT INTO `tz_cart` VALUES ('12bfd7c9f7194040b5e573860ebc8ce7', '307dbb33773a4324976ac3cc83907337', 'c4cd1d8d16fa4bcab350ca756ec02684', 1, '2017-11-22 17:36:12', '2017-11-23 11:58:44');
INSERT INTO `tz_cart` VALUES ('1cf0a0b9368345be9f706d6bebcadc4d', 'b2572ce53c23461ea802900a4813fd39', '8dca2aefd538483bbc737e28a64ddedd', 4, '2017-12-06 11:34:31', '2017-12-11 10:05:28');
INSERT INTO `tz_cart` VALUES ('25d5ac560a5844b3b923f311a5f4c2e0', '45289be39735424cbcbac90b70066e3d', '770c96859b474282b492d4bc6f13176f', 1, '2017-11-21 10:07:34', '2017-11-21 10:07:34');
INSERT INTO `tz_cart` VALUES ('2bdbc0f4c4144531b455fb38c0e16e90', '37fc05b8bc8b496fa14222af53d98fe3', '30a1d300698f45bcb43ab9c2cb9b9475', 1, '2017-11-20 11:25:00', '2017-11-20 11:25:00');
INSERT INTO `tz_cart` VALUES ('2cff465273d24085b649747bba5f8235', 'f1321311934541b7b77a8120ad530195', '8dca2aefd538483bbc737e28a64ddedd', 2, '2017-11-22 18:47:57', '2017-11-22 18:48:41');
INSERT INTO `tz_cart` VALUES ('3397bc165d524deeb88d4fdf8ae3f8a0', 'f6435051f9124414b144f28910e60594', '8dca2aefd538483bbc737e28a64ddedd', 1, '2017-11-23 17:35:56', '2017-11-23 17:35:56');
INSERT INTO `tz_cart` VALUES ('377f3b14221447839ccde89367a4e042', '7c14552fef36487bb868b3125e3e7779', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '2017-11-22 17:44:58', '2017-11-22 17:44:58');
INSERT INTO `tz_cart` VALUES ('3bac6ef9c4844814897daee4656a79de', '28408d0512c4421288a37b5a4e6539a1', 'be1836fe6a37498e853c0a52d0a26177', 1, '2017-11-23 17:34:42', '2017-11-23 17:34:42');
INSERT INTO `tz_cart` VALUES ('3fa1d0ef2bea4a4d9581b5eee1068b5e', 'a2abd46b9a2e467bb99a491cd177e095', '9acb91dcbc0941539e7039e434dc29ce', 2, '2017-11-22 09:25:31', '2017-11-22 09:25:31');
INSERT INTO `tz_cart` VALUES ('42c58b702fdf49e0b334d7ed49cbdd0f', '45289be39735424cbcbac90b70066e3d', 'e9262813f2d141c0a5e982e4bb858995', 2, '2017-11-20 18:21:44', '2017-11-21 09:58:41');
INSERT INTO `tz_cart` VALUES ('436389418bad4ec6bc96c3763eb7ab4c', 'ed61ed3cad704e94b6bbdb36d2f31445', '67bb35e22d49424ca902710174033af8', 1, '2017-11-24 16:44:59', '2017-11-24 16:44:59');
INSERT INTO `tz_cart` VALUES ('437cb6475cde415eb16e8286686cab3f', '307dbb33773a4324976ac3cc83907337', 'f2c06d95aae7414289cda2efef424e34', 1, '2017-11-22 18:26:26', '2017-11-22 18:26:26');
INSERT INTO `tz_cart` VALUES ('46802cc02b1847fe8a09f01e0556e6b5', 'a2abd46b9a2e467bb99a491cd177e095', '8dca2aefd538483bbc737e28a64ddedd', 1, '2017-11-21 10:47:06', '2017-11-21 10:47:06');
INSERT INTO `tz_cart` VALUES ('594e382062be4d4dbddb56ff0a990e31', '55bef243e1f646c7a2bd28ba15e42c96', '8dca2aefd538483bbc737e28a64ddedd', 1, '2017-12-15 11:08:53', '2017-12-15 11:08:53');
INSERT INTO `tz_cart` VALUES ('5bea8978e965435489b58fab5300dcfa', 'dfc30e9eafe246d69541ecf104d6d80f', '601698b88c2a4c2b89aac3b600eea755', 2, '2017-11-21 10:19:11', '2017-11-21 10:19:11');
INSERT INTO `tz_cart` VALUES ('5c519499bfa74f3caf8ec2eae6c2feb6', '99e83f3b1d1849ac821fd42c31c6f748', '67bb35e22d49424ca902710174033af8', 1, '2017-11-17 15:13:24', '2017-11-17 15:13:24');
INSERT INTO `tz_cart` VALUES ('5f31b7ffd54f4c689169a9fcb02bf0db', '5395bf9c84484df298c57c0a2fb1acb2', '81b2922a27094271a0b58240ad78b794', 3, '2017-11-24 20:01:23', '2017-11-24 20:01:24');
INSERT INTO `tz_cart` VALUES ('6064518c076642d7af885d6eb2378c46', '1a048c6799024e54aa29a962a545020b', '8dca2aefd538483bbc737e28a64ddedd', 1, '2017-12-08 16:49:31', '2017-12-08 16:49:31');
INSERT INTO `tz_cart` VALUES ('610c732ccfd546a99b7038c089456537', '307dbb33773a4324976ac3cc83907337', '3b6f547ac02f4f58bfdbb0142e068623', 2, '2017-11-22 18:26:26', '2017-11-22 18:26:26');
INSERT INTO `tz_cart` VALUES ('672f86f972924ad0aab3e03334ca8978', 'dfc30e9eafe246d69541ecf104d6d80f', '8dca2aefd538483bbc737e28a64ddedd', 1, '2017-11-22 10:52:29', '2017-11-22 10:52:29');
INSERT INTO `tz_cart` VALUES ('6881e9858187479199120c00825e14a2', '55bef243e1f646c7a2bd28ba15e42c96', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '2017-12-15 11:09:12', '2017-12-15 11:09:12');
INSERT INTO `tz_cart` VALUES ('68970123e5214d06b58a1f30e23f556d', 'fccc0ab9b68047d682ede1b5cd111a4a', '30a1d300698f45bcb43ab9c2cb9b9475', 1, '2017-11-21 14:09:01', '2017-11-21 14:10:38');
INSERT INTO `tz_cart` VALUES ('74bd77ffc99548528c2846b104259ade', '77cf694f6ee6410da0ec9cd8c770a7ae', 'be1836fe6a37498e853c0a52d0a26177', 1, '2017-11-22 18:27:19', '2017-11-22 18:27:19');
INSERT INTO `tz_cart` VALUES ('758680913943432abbc4cfe035b2a15a', '0c4dde1df1d7478ab28d2d8429efab2c', '81b2922a27094271a0b58240ad78b794', 1, '2017-11-17 15:10:38', '2017-11-17 15:10:38');
INSERT INTO `tz_cart` VALUES ('78ed3e7ffced4ed686492297346ad3f1', '6d3b18c0050a4a13ae4e687ac269edf2', '30a1d300698f45bcb43ab9c2cb9b9475', 2, '2017-11-22 17:46:21', '2017-11-23 12:06:13');
INSERT INTO `tz_cart` VALUES ('7a68909f1e314839810cd13d537de038', '8e5509cc0cf241ca8d61b65d86482dd6', '121312', 2, '2017-11-19 16:53:29', '2017-11-19 16:53:42');
INSERT INTO `tz_cart` VALUES ('7b5ca35c1e5544588cfd41d2cef9d202', '1385383320a644318eb8c40ffe747dd8', 'be1836fe6a37498e853c0a52d0a26177', 1, '2017-11-22 10:32:21', '2017-11-22 10:32:21');
INSERT INTO `tz_cart` VALUES ('7fb74c2d4a8b49f396704ed882fa61a6', '307dbb33773a4324976ac3cc83907337', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '2017-11-22 17:34:34', '2017-11-23 15:24:00');
INSERT INTO `tz_cart` VALUES ('80614306c0ad4d319a497ac9d81392d9', '1a048c6799024e54aa29a962a545020b', '20b19960498244eeadf56d4dc22f522e', 1, '2017-12-01 20:37:29', '2017-12-01 20:37:29');
INSERT INTO `tz_cart` VALUES ('810e0f8de1714ee9bf19e9366a749093', '45289be39735424cbcbac90b70066e3d', 'd3f9a98ffce846b79292321a086c1727', 1, '2017-11-21 10:07:34', '2017-11-21 10:07:34');
INSERT INTO `tz_cart` VALUES ('821f59dba9da4930afa2e6efc2ef2cd8', '1385383320a644318eb8c40ffe747dd8', '455452104aa140c683bd2a69bed0d489', 1, '2017-11-22 10:32:09', '2017-11-22 10:32:09');
INSERT INTO `tz_cart` VALUES ('826818f6c48741088b26d03db2732e6e', 'f1321311934541b7b77a8120ad530195', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '2017-11-22 18:47:41', '2017-11-22 18:47:41');
INSERT INTO `tz_cart` VALUES ('82b84cc8f04f43fba5aa4bd1224e5740', '1a048c6799024e54aa29a962a545020b', '30a1d300698f45bcb43ab9c2cb9b9475', 2, '2017-12-08 16:49:19', '2017-12-08 16:49:27');
INSERT INTO `tz_cart` VALUES ('83f30b2e25544508afe6c58d4cfecc54', '8e5509cc0cf241ca8d61b65d86482dd6', '67bb35e22d49424ca902710174033af8', 3, '2017-11-19 16:53:20', '2017-11-19 22:43:25');
INSERT INTO `tz_cart` VALUES ('89dd80663517490a818705f12757a757', '45289be39735424cbcbac90b70066e3d', 'a324199f9278465abf63c52bdaf105ba', 1, '2017-11-21 10:07:34', '2017-11-21 10:07:34');
INSERT INTO `tz_cart` VALUES ('8b94164854794567a541ff95b7ea14ac', '7c14552fef36487bb868b3125e3e7779', '81b2922a27094271a0b58240ad78b794', 1, '2017-11-22 17:45:01', '2017-11-22 17:45:01');
INSERT INTO `tz_cart` VALUES ('8f423719866f4350beac2d7fcaae57f1', '0c4dde1df1d7478ab28d2d8429efab2c', '8dca2aefd538483bbc737e28a64ddedd', 1, '2017-11-17 15:10:41', '2017-11-17 15:10:41');
INSERT INTO `tz_cart` VALUES ('997183496367406aa028c7de02a5e4da', '505c25f7fcd24b31a7decf8f90028978', '3b6f547ac02f4f58bfdbb0142e068623', 1, '2017-11-22 18:11:59', '2017-11-22 18:11:59');
INSERT INTO `tz_cart` VALUES ('9acb91dcbc0941539e7039e434dc29ce', 'a2abd46b9a2e467bb99a491cd177e095', 'be1836fe6a37498e853c0a52d0a26177', 2, '2017-11-21 10:46:54', '2017-11-21 10:47:08');
INSERT INTO `tz_cart` VALUES ('a5b40e1b02d04ffc9f80373ac9a5eb08', 'c33c373d414741cab5ab80f4ffff2b0a', '30a1d300698f45bcb43ab9c2cb9b9475', 2, '2017-12-06 23:33:55', '2017-12-06 23:33:57');
INSERT INTO `tz_cart` VALUES ('a7b12d1797994c61b49cd9667f99f08a', '6e12cc0c8d6e482f8395e224a982e853', '4aa06c6db34a447ca56f90e450e5c03c', 3, '2017-11-27 12:18:18', '2017-11-27 12:18:39');
INSERT INTO `tz_cart` VALUES ('ae82b2bc39ef4c7a9fc65ca1050eee7d', 'a2abd46b9a2e467bb99a491cd177e095', 'e3515513295a48fc8b7e90216db7893e', 1, '2017-11-22 09:25:31', '2017-11-22 09:25:31');
INSERT INTO `tz_cart` VALUES ('b9701b35ceab41a38693d00bcd680387', '0c4dde1df1d7478ab28d2d8429efab2c', 'c4cd1d8d16fa4bcab350ca756ec02684', 2, '2017-11-17 15:10:32', '2017-11-17 15:10:34');
INSERT INTO `tz_cart` VALUES ('c22929ed92e04a22b06b213768cc440c', '1a048c6799024e54aa29a962a545020b', 'c4cd1d8d16fa4bcab350ca756ec02684', 4, '2017-12-08 16:49:46', '2017-12-08 16:51:05');
INSERT INTO `tz_cart` VALUES ('c493cdbce6844b8d9bb42fdcb20ba1b5', '45289be39735424cbcbac90b70066e3d', 'de8ca855268746789c042dd61457761b', 1, '2017-11-21 14:28:17', '2017-11-21 14:28:17');
INSERT INTO `tz_cart` VALUES ('d48467b93c4148b281e77fcdeace41b0', '505c25f7fcd24b31a7decf8f90028978', 'f2c06d95aae7414289cda2efef424e34', 1, '2017-11-22 18:11:59', '2017-11-22 18:11:59');
INSERT INTO `tz_cart` VALUES ('d5e159d6d8324377b22a3c24da8fdf87', 'a2abd46b9a2e467bb99a491cd177e095', '04ea4321223445dca061fd4f644bd3b7', 1, '2017-11-22 09:25:31', '2017-11-22 09:25:31');
INSERT INTO `tz_cart` VALUES ('d6b1fd8767554a76915adcb89578e0ee', 'b2572ce53c23461ea802900a4813fd39', '81b2922a27094271a0b58240ad78b794', 1, '2017-12-01 20:17:56', '2017-12-01 20:17:56');
INSERT INTO `tz_cart` VALUES ('de3776bc2e9743c58451b69bb85c9526', '8e5509cc0cf241ca8d61b65d86482dd6', '81b2922a27094271a0b58240ad78b794', 1, '2017-11-19 16:53:24', '2017-11-19 16:53:24');
INSERT INTO `tz_cart` VALUES ('df963b36353f474e93999a98c6884e36', '28408d0512c4421288a37b5a4e6539a1', '67bb35e22d49424ca902710174033af8', 1, '2017-11-23 17:29:21', '2017-11-23 17:29:21');
INSERT INTO `tz_cart` VALUES ('e0961a9f65514baeaa85d6af53e59af0', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', '8dca2aefd538483bbc737e28a64ddedd', 1, '2017-12-08 17:40:06', '2017-12-08 17:40:06');
INSERT INTO `tz_cart` VALUES ('e3515513295a48fc8b7e90216db7893e', 'a2abd46b9a2e467bb99a491cd177e095', '455452104aa140c683bd2a69bed0d489', 1, '2017-11-21 10:47:12', '2017-11-21 10:47:12');
INSERT INTO `tz_cart` VALUES ('e721846a8116437e9c81397c3d732eb2', '1385383320a644318eb8c40ffe747dd8', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '2017-11-22 10:32:06', '2017-11-22 10:32:06');
INSERT INTO `tz_cart` VALUES ('e861d8ccb836435b888e48dfd679b1dd', '409be31c618c4c14b33c612c94dc80c1', 'be1836fe6a37498e853c0a52d0a26177', 1, '2017-11-28 13:37:02', '2017-11-28 13:37:02');
INSERT INTO `tz_cart` VALUES ('f177254e304644a9b79977bbd28c9d98', 'dfc30e9eafe246d69541ecf104d6d80f', '480e19e99eed47c5a5021a80b0d74348', 1, '2017-11-21 10:19:11', '2017-11-21 10:19:11');
INSERT INTO `tz_cart` VALUES ('f1e2422fb12e4b72b4ba26d803f4988b', '12d4ab2da7394354996db664828c7242', '67bb35e22d49424ca902710174033af8', 1, '2017-12-01 21:08:48', '2017-12-07 17:50:38');
INSERT INTO `tz_cart` VALUES ('f1ef80b2814a4e2aa354bb89bafa899e', '28408d0512c4421288a37b5a4e6539a1', '8dca2aefd538483bbc737e28a64ddedd', 1, '2017-11-23 17:34:59', '2017-11-23 17:34:59');
INSERT INTO `tz_cart` VALUES ('f21a62bc31964fa8aed7436ba9a09d2e', '99e83f3b1d1849ac821fd42c31c6f748', 'c4cd1d8d16fa4bcab350ca756ec02684', 2, '2017-11-17 15:34:55', '2017-11-17 15:35:23');
INSERT INTO `tz_cart` VALUES ('f22affd89a614d499b33b7de8ea0b26c', '6d3b18c0050a4a13ae4e687ac269edf2', '81b2922a27094271a0b58240ad78b794', 2, '2017-11-23 12:03:44', '2017-11-23 12:06:13');
INSERT INTO `tz_cart` VALUES ('f2b337e70cc342fc94d18000788eca58', 'fccc0ab9b68047d682ede1b5cd111a4a', 'a38d1ea2be6e443d92c099416b09c98c', 1, '2017-11-21 15:47:39', '2017-11-21 15:47:39');
INSERT INTO `tz_cart` VALUES ('f31468f41a34420489a91a3dfcee1426', '6d3b18c0050a4a13ae4e687ac269edf2', '90b690312e5d4fb0b89f4d82a0a8faba', 4, '2017-11-23 10:37:46', '2017-11-23 12:06:13');
INSERT INTO `tz_cart` VALUES ('f5c77fe8ab9843f5a3ea1e85c7fa7a78', '505c25f7fcd24b31a7decf8f90028978', 'c4cd1d8d16fa4bcab350ca756ec02684', 4, '2017-11-23 21:02:15', '2017-11-23 21:04:18');
INSERT INTO `tz_cart` VALUES ('fdf62a29c4c54b3c9a86a9f1e8f08c6d', '8e5509cc0cf241ca8d61b65d86482dd6', '8dca2aefd538483bbc737e28a64ddedd', 1, '2017-11-19 16:53:35', '2017-11-19 16:53:35');
INSERT INTO `tz_cart` VALUES ('ffab7ea1749f44c89d5695e34f92820b', 'a2abd46b9a2e467bb99a491cd177e095', '46802cc02b1847fe8a09f01e0556e6b5', 1, '2017-11-22 09:25:31', '2017-11-22 09:25:31');
INSERT INTO `tz_cart` VALUES ('ffed79726bc04ea8b37fa2238bff8414', '37fc05b8bc8b496fa14222af53d98fe3', '2bdbc0f4c4144531b455fb38c0e16e90', 1, '2017-11-20 11:44:40', '2017-11-20 11:44:40');

-- ----------------------------
-- Table structure for tz_city
-- ----------------------------
DROP TABLE IF EXISTS `tz_city`;
CREATE TABLE `tz_city`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `city_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市名',
  `state` int(11) NULL DEFAULT 1 COMMENT '状态：0 禁用 1 启用',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tz_city_user_consumer
-- ----------------------------
DROP TABLE IF EXISTS `tz_city_user_consumer`;
CREATE TABLE `tz_city_user_consumer`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `order_user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单用户id',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tz_content
-- ----------------------------
DROP TABLE IF EXISTS `tz_content`;
CREATE TABLE `tz_content`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content_category_id` int(11) NOT NULL COMMENT '内容类目ID',
  `content_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容标题',
  `sub_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子标题',
  `title_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题描述',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告商品链接',
  `pic_first_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片1绝对路径',
  `pic_second_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片2',
  `content_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容描述',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL,
  `updated_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告内容' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_content
-- ----------------------------
INSERT INTO `tz_content` VALUES ('062e6fe8c8bf41d4b80633db0d4616f0', 4, '商城首页轮播图片2', '', '', '67bb35e22d49424ca902710174033af8', 'group1/M00/00/0C/rBBH51oWnuCAJjXZAEVySLaSp0s359.png?banner1.png', NULL, '', '001', '2017-11-16 11:25:56', '2017-11-16 11:25:56');
INSERT INTO `tz_content` VALUES ('29194f6551794d0b888c72cbbef50035', 1, 'app首页轮播图1', '', '', '', 'group1/M00/00/0E/rBBH51oX2suAdWMZAANUoaU9030694.png?1shouyell.png', '', '', '001', '2017-11-16 11:20:30', '2017-11-24 16:39:37');
INSERT INTO `tz_content` VALUES ('39ea64a0875f45ec867194d075913afc', 3, '白酒养生有诀窍', '', '', 'http://www.520zhiai.com/app/news_zx_2.html', 'group1/M00/00/0B/rBBH51oWcJSAWPJzAANiwHhSlOU871.png?23123412.png', NULL, '白酒养生自古就有，因为传承时间长、实践起来也方便，很受广大群众的拥戴，很多人都喜欢没事喝两口，既过了酒瘾，又能养生，美的不要不要的', '001', '2017-11-16 11:23:53', '2017-11-23 15:04:05');
INSERT INTO `tz_content` VALUES ('450368b55fe8481a8188af4a9d74d13f', 4, '商城首页轮播图片1', '', '', 'c4cd1d8d16fa4bcab350ca756ec02684', 'group1/M00/00/0C/rBBH51oWnxaAepD0AEO7EhSxwco618.png?banner2.png', NULL, '', '001', '2017-11-16 11:25:14', '2017-11-24 14:45:42');
INSERT INTO `tz_content` VALUES ('4acc0fb689e440cba610977abc71375c', 3, '酒文化宝岛行--台湾酒文化', '', '', 'http://www.520zhiai.com/app/news_spirit_1.html', 'group1/M00/00/0B/rBBH51oWcVSAAaLoAADAGMvgbZ4440.jpg?baodao.jpg', NULL, '让与我们一起探寻台湾酒文化，带您领略宝岛台湾酒的特色与酒的礼节！感受宝岛台湾人民的当地文化，让您身临其境的感受宝岛人民的风土人情。', '001', '2017-11-16 11:23:06', '2017-11-23 15:03:00');
INSERT INTO `tz_content` VALUES ('7038b63bc4f446fc9d2156c886d3352c', 2, '红酒文化与品鉴', '', '', 'http://www.520zhiai.com/app/news_spirit_2.html', 'group1/M00/00/0B/rBBH51oWceaAee-NAACIzRa2ywg938.jpg?redcutrul.jpg', NULL, '你的生活缺少点什么?选择一瓶品质红酒,让你的生活更有品味!!!', '001', '2017-11-16 11:21:33', '2017-11-23 14:59:47');
INSERT INTO `tz_content` VALUES ('7c77d0a886ea4aefa8989b5d8f00507f', 1, 'app首页轮播图2', '', '', '', 'group1/M00/00/0C/rBBH51oWnxaAepD0AEO7EhSxwco618.png?banner2.png', NULL, '', '001', '2017-11-16 11:20:02', '2017-11-24 16:07:01');
INSERT INTO `tz_content` VALUES ('859a9a7cf6ac4032a2c56e6482948e2c', 4, '商城首页轮播图片3', '', '', 'c4cd1d8d16fa4bcab350ca756ec02684', 'group1/M00/00/0D/rBBH51oXyPaAfW0sAAmLgx9lX-g960.png?hengxiangbaijiutaocan.png', NULL, '', '001', '2017-11-16 11:26:30', '2017-11-24 15:24:02');
INSERT INTO `tz_content` VALUES ('ba5ac47798b7477c95d3faf587cca199', 1, 'app首页轮播图3', '', '', '', 'group1/M00/00/0E/rBBH51oX06OAfiQkAEVySLaSp0s257.png?2shouye.png', NULL, '', '001', '2017-11-16 11:19:02', '2017-11-24 16:09:04');
INSERT INTO `tz_content` VALUES ('fe390ee9ab284761a994bc7fb074e112', 2, '抗氧化食物保卫您的健康', '', '', 'http://www.520zhiai.com/app/news_food_1.html', 'group1/M00/00/0B/rBBH51oWcbeAU2SAAAF5KbBtJqU246.jpg?kangyanghua.jpg', NULL, '核心提示：越来越担心身体过度氧化，加速衰老？又不想吃营养品抗老化？药补不如食补，以下抗老食物吃起来，让你保住年轻身体和健康肌肤。', '001', '2017-11-16 11:22:06', '2017-11-23 14:59:00');

-- ----------------------------
-- Table structure for tz_content_category
-- ----------------------------
DROP TABLE IF EXISTS `tz_content_category`;
CREATE TABLE `tz_content_category`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `sort` int(4) NULL DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `logo_url` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Logo分类表示图片地址',
  `link_address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容分类跳转地址',
  `p_id` int(4) NULL DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  `is_parent` tinyint(1) NULL DEFAULT NULL COMMENT '该类目是否为父类目，1为true，0为false',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '内容分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_content_category
-- ----------------------------
INSERT INTO `tz_content_category` VALUES (1, 1, 'app首页轮播图', '', '', NULL, 1, '001', '2017-11-23 10:22:25', '2017-11-23 10:22:25');
INSERT INTO `tz_content_category` VALUES (2, 2, '新闻', '', '', NULL, 1, '001', '2017-11-23 10:25:16', '2017-11-23 10:25:16');
INSERT INTO `tz_content_category` VALUES (3, 3, '资讯', '', '', NULL, 1, '001', '2017-11-23 10:30:13', '2017-11-23 10:30:13');
INSERT INTO `tz_content_category` VALUES (4, 4, '商城首页轮播图', '', '', NULL, 1, '001', '2017-11-23 10:31:14', '2017-11-23 10:31:14');
INSERT INTO `tz_content_category` VALUES (5, 5, '商城介绍', 'group1/M00/00/07/rBBH51oOeVmARRUzAAAbeDOPqU4292.png?mallContent.png', 'http://www.520zhiai.com/app/mallContent.html', NULL, 1, '001', '2017-11-23 10:36:36', '2017-11-23 13:55:54');
INSERT INTO `tz_content_category` VALUES (6, 6, '白酒起源', 'group1/M00/00/07/rBBH51oOQuGABLkPAAAhLHR8yIQ348.png?whiteContent.png', 'http://www.520zhiai.com/app/whiteContent.html', NULL, 1, '001', '2017-11-23 10:37:30', '2017-11-23 10:37:30');
INSERT INTO `tz_content_category` VALUES (7, 7, '红酒起源', 'group1/M00/00/0C/rBBH51oWpMWAYFUIAAAhG60p9WQ814.png?redContent.png', 'http://www.520zhiai.com/app/redContent.html', NULL, 1, '001', '2017-11-23 10:38:32', '2017-11-23 18:36:51');
INSERT INTO `tz_content_category` VALUES (8, 8, '520面膜', 'group1/M00/00/0A/rBBH51oWNXmAc4G_AAApy0kR44c834.png?cosmeticsContent.png', 'http://www.520zhiai.com/app/cosmeticsContent.html', NULL, 1, '001', '2017-11-23 10:41:58', '2017-11-23 10:41:58');

-- ----------------------------
-- Table structure for tz_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tz_dictionary`;
CREATE TABLE `tz_dictionary`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `type` int(2) NULL DEFAULT NULL COMMENT '类型',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型名称',
  `module` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属模块',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tz_dictionary
-- ----------------------------
INSERT INTO `tz_dictionary` VALUES ('c5b8def36a684e9c96f2f74de13d0e57', 0, 'null', '权限目录', '所有模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:07:34', '2017-12-04 15:07:34');
INSERT INTO `tz_dictionary` VALUES ('d24ed3c660d94beaa2bb9ef9241ca892', 0, 'manager', '管理员权限管理', '管理员模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:09:20', '2017-12-04 15:09:20');
INSERT INTO `tz_dictionary` VALUES ('c4b37190696d4e12bfa35e71ab7f3bfe', 0, 'memberManager', '操作会员管理列表信息', '会员管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:20:39', '2017-12-04 15:20:39');
INSERT INTO `tz_dictionary` VALUES ('0da32d1634d84ac4b131cd249e448058', 0, 'itemManager', '商品管理列表权限', '商品管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:21:43', '2017-12-04 15:21:43');
INSERT INTO `tz_dictionary` VALUES ('c0dbd41869ac48eea572c4c82d626022', 0, 'contentManager', '内容管理权限', '内容管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:22:22', '2017-12-04 15:22:22');
INSERT INTO `tz_dictionary` VALUES ('2ae9d66283604a0c9a038b6b8f33d4ef', 0, 'orderManager', '订单管理权限', '订单模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:23:21', '2017-12-04 15:23:21');
INSERT INTO `tz_dictionary` VALUES ('d8a10212d22744c48a75f3ce1b405367', 0, 'expressManager', '物流管理权限', '物流管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:24:20', '2017-12-04 15:24:20');
INSERT INTO `tz_dictionary` VALUES ('13091ca298d549d99bcb92af5b07210a', 0, 'intentionalUserManger', '意向用户管理权限', '意向用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:25:02', '2017-12-04 15:25:02');
INSERT INTO `tz_dictionary` VALUES ('d466273364564d87b5767189f276ac54', 0, 'systemManager', '系统管理权限', '系统管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:25:30', '2017-12-04 15:25:30');
INSERT INTO `tz_dictionary` VALUES ('e1ecfbc1a39840dfb26e51f5f78fd30b', 0, 'addItem', '添加商品权限', '商品管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:26:15', '2017-12-04 15:26:15');
INSERT INTO `tz_dictionary` VALUES ('8bbf5fd45b604026ab74f7d54a8c1f4c', 0, 'updateItem', '修改商品权限', '商品管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:26:53', '2017-12-04 15:26:53');
INSERT INTO `tz_dictionary` VALUES ('f8f0654943374dba89687741ebbd520b', 0, 'delItem', '删除商品权限', '商品管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:27:28', '2017-12-04 15:27:28');
INSERT INTO `tz_dictionary` VALUES ('9864e875cde5498fbe7aaa10b6e61734', 0, 'itemUpOrDown', '商品上下架权限', '商品管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:28:09', '2017-12-04 15:28:09');
INSERT INTO `tz_dictionary` VALUES ('709ba25925bd43d88b3f964064952b01', 0, 'addItemCategory', '添加商品分类权限', '商品分类管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:28:44', '2017-12-04 15:28:44');
INSERT INTO `tz_dictionary` VALUES ('94ef8c0f4f6f4855aec21297f5a6d4e7', 0, 'updateItemCategory', '修改商品分类权限', '商品分类管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:29:19', '2017-12-04 15:29:30');
INSERT INTO `tz_dictionary` VALUES ('42e65b046f7a4d20851e9015fdfe8031', 0, 'delItemCategory', '删除商品分类权限', '商品分类管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:30:16', '2017-12-04 15:30:32');
INSERT INTO `tz_dictionary` VALUES ('0a066c2efb21417f9cb40afd1c713a02', 0, 'queryItem', '商品列表查询权限', '商品管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:31:53', '2017-12-04 15:31:53');
INSERT INTO `tz_dictionary` VALUES ('f77f0818f06b4a55ba7badaeed5b7a9b', 0, 'queryItemCategory', '商品分类列表查询权限', '商品分类管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:32:54', '2017-12-04 15:33:38');
INSERT INTO `tz_dictionary` VALUES ('0308019034d84cbaaef30ed564e27126', 0, 'queryContent', '内容列表查询权限', '内容管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:34:27', '2017-12-04 15:34:27');
INSERT INTO `tz_dictionary` VALUES ('9a34c2ae9d724a8fac03a47d0ebf4250', 0, 'addContent', '添加内容权限', '内容管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:36:00', '2017-12-04 15:36:00');
INSERT INTO `tz_dictionary` VALUES ('2b31adea0a544271b7bed955fb8d815b', 0, 'updateContent', '修改内容权限', '内容管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:36:26', '2017-12-04 15:36:26');
INSERT INTO `tz_dictionary` VALUES ('d05a13a18b3448aba767e155b9d6477b', 0, 'delContent', '删除内容权限', '内容管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:36:58', '2017-12-04 15:36:58');
INSERT INTO `tz_dictionary` VALUES ('ca1a2a2a2907444d9766b074c07dc28c', 0, 'queryOrder', '查询订单列表权限', '订单管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:37:39', '2017-12-04 15:37:39');
INSERT INTO `tz_dictionary` VALUES ('c112d47d37744a0ea9783fa1de446321', 0, 'queryOrderDetail', '查询订单详情权限', '订单管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:38:16', '2017-12-04 15:38:16');
INSERT INTO `tz_dictionary` VALUES ('663f4b271d3d452d88b4015ef36d1282', 0, 'updateExpress', '编辑/修改物流权限', '订单管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:38:41', '2017-12-04 15:38:41');
INSERT INTO `tz_dictionary` VALUES ('71f8a062cef3465992893f49d117f511', 0, 'sendOrder', '发货权限', '订单管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:39:06', '2017-12-04 15:39:06');
INSERT INTO `tz_dictionary` VALUES ('6d5a3e5403324685ad033f95b1119009', 0, 'updateOrder', '修改订单权限', '订单管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-04 15:39:33', '2017-12-04 15:39:33');
INSERT INTO `tz_dictionary` VALUES ('e110fda003fe4ed9bc50c2395a8d4011', 0, 'queryUser', '会员列表查询权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:33:01', '2017-12-05 10:33:01');
INSERT INTO `tz_dictionary` VALUES ('ac326bb2b8d84f1285ff1a71507fabb5', 0, 'addUser', '添加会员权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:33:39', '2017-12-05 10:33:51');
INSERT INTO `tz_dictionary` VALUES ('de125b6a2eeb4b139088b779ca793eae', 0, 'updateUser', '修改会员权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:34:26', '2017-12-05 10:34:26');
INSERT INTO `tz_dictionary` VALUES ('d6be7f0ce5bd43e192c998b84feb2631', 0, 'frozenUser', '冻结会员权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:34:54', '2017-12-05 10:34:54');
INSERT INTO `tz_dictionary` VALUES ('70b5d1c37b05452897a3c6d658b6fa90', 0, 'delUser', '删除会员权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:35:16', '2017-12-05 10:35:16');
INSERT INTO `tz_dictionary` VALUES ('f4c334e25a454b43a530e2eb77c25568', 0, 'queryUser*', '查询我的推荐/我的团队/我的合伙人权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:35:39', '2017-12-05 10:35:39');
INSERT INTO `tz_dictionary` VALUES ('a6d35a746f9d4fa790db48799e636a3e', 0, 'commendManager', '推荐管理列表查询权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:36:07', '2017-12-05 10:36:07');
INSERT INTO `tz_dictionary` VALUES ('e6276e9bfeba428fa8a46f8dc9f9d69f', 0, 'delCommend', '删除某一推荐记录权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:36:31', '2017-12-05 10:36:31');
INSERT INTO `tz_dictionary` VALUES ('433ac321f09f4371889d91a46d4b2dee', 0, 'queryLoveValue', '爱心值管理列表查询权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:36:51', '2017-12-05 10:36:51');
INSERT INTO `tz_dictionary` VALUES ('a3edd7db7aa6401f8bb229d986c7e03d', 0, 'frozenLoveValue', '冻结某一用户爱心值权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:37:16', '2017-12-05 10:37:16');
INSERT INTO `tz_dictionary` VALUES ('b24a805c081a49b990aa9100fb52e1c7', 0, 'delLoveValue', '删除某一用户的爱心值信息权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:37:36', '2017-12-05 10:37:36');
INSERT INTO `tz_dictionary` VALUES ('39db58a14caf45219592deea28b8f9cf', 0, 'queryLoveValueConsume', '爱心值消费明细列表查询权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:38:06', '2017-12-05 10:38:06');
INSERT INTO `tz_dictionary` VALUES ('25944c6a95ee4f64a105f786c6b2c652', 0, 'delLoveValueConsume', '删除某一用户爱心值消费记录权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:38:43', '2017-12-05 10:38:43');
INSERT INTO `tz_dictionary` VALUES ('2341419dcd5345ea879d9e01a4db1e48', 0, 'queryLoveValueWithdraws', '爱心值体现管理列表查询权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:39:05', '2017-12-05 10:39:05');
INSERT INTO `tz_dictionary` VALUES ('0201c32fedc945fa8714b6a20fdfcd12', 0, 'delLoveValueWithdraws', '删除爱心值提现管理列表中某一记录权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:44:14', '2017-12-05 10:44:14');
INSERT INTO `tz_dictionary` VALUES ('4216322115c54d6f9f22a0a041e4a948', 0, 'queryContentCategory', '内容分类列表查询权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:44:46', '2017-12-05 10:44:46');
INSERT INTO `tz_dictionary` VALUES ('c0faba9533844ce2831642b2d3be2017', 0, 'addContentCategory', '添加内容分类权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:45:35', '2017-12-05 10:45:35');
INSERT INTO `tz_dictionary` VALUES ('ff7d4220d39944f6be16a565932cb313', 0, 'updateContentCategory', '修改内容分类权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:45:58', '2017-12-05 10:45:58');
INSERT INTO `tz_dictionary` VALUES ('5110ba218bc24432a524cc8fe75463d2', 0, 'delContentCategory', '删除内容分类权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:46:21', '2017-12-05 10:46:21');
INSERT INTO `tz_dictionary` VALUES ('b88c679f7e7f422ab8bcab88a12f1b7e', 0, 'queryManager', '查询管理员列表信息权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:46:48', '2017-12-05 10:46:48');
INSERT INTO `tz_dictionary` VALUES ('bd660143f7cf47c588babfaec14a2b16', 0, 'queryRole', '角色管理查询权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:47:13', '2017-12-05 10:47:13');
INSERT INTO `tz_dictionary` VALUES ('f02e9a49bc784bb8a8a87d6b258eae35', 0, 'queryFucntion', '权限列表信息查询权限', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:47:34', '2017-12-05 10:47:34');
INSERT INTO `tz_dictionary` VALUES ('cf768b274e9c41739fdc1c6b39276c40', 1, 'admin', '超级管理员', '所有模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:49:32', '2017-12-05 10:49:32');
INSERT INTO `tz_dictionary` VALUES ('f6edd87d29d746b4bcbd584de971efbe', 1, 'financialManager', '财务管理员', '商品管理模块、订单管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 10:50:27', '2017-12-05 10:50:27');
INSERT INTO `tz_dictionary` VALUES ('042f0f4e970b4a868c2cacd9f8b77653', 0, 'queryExpress', '快递管理列表查询权限', '物流管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 13:58:38', '2017-12-05 13:58:38');
INSERT INTO `tz_dictionary` VALUES ('b0326324baf743bab1328c1b113925ef', 0, 'queryIntentionalUser', '意向用户列表查询权限', '意向用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-05 13:59:08', '2017-12-05 13:59:08');
INSERT INTO `tz_dictionary` VALUES ('406d70121d46418888729987652ca50f', 0, 'exportOrderData', '导出订单列表数据信息', '订单管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-11 13:57:18', '2017-12-11 13:57:18');
INSERT INTO `tz_dictionary` VALUES ('9e56e816cb87424c8a36c7c23db403c6', 0, 'exportLoveValue', '导出爱心值提现列表信息', '用户管理模块', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-11 13:57:53', '2017-12-11 13:57:53');

-- ----------------------------
-- Table structure for tz_express_com
-- ----------------------------
DROP TABLE IF EXISTS `tz_express_com`;
CREATE TABLE `tz_express_com`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公司ID',
  `com` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递公司简码',
  `company_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递公司名称',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '快递公司简码表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_express_com
-- ----------------------------
INSERT INTO `tz_express_com` VALUES (1, 'sf', '顺丰', '2017-11-24 01:13:46', '2017-11-24 01:13:46');
INSERT INTO `tz_express_com` VALUES (2, 'sto', '申通', '2017-11-24 01:14:42', '2017-11-24 01:14:42');
INSERT INTO `tz_express_com` VALUES (3, 'yt', '圆通', '2017-11-24 09:49:24', '2017-11-24 09:49:24');
INSERT INTO `tz_express_com` VALUES (4, 'yd', '韵达', '2017-11-24 09:50:49', '2017-11-24 09:50:49');
INSERT INTO `tz_express_com` VALUES (5, 'tt', '天天', '2017-11-24 09:51:09', '2017-11-24 09:51:09');
INSERT INTO `tz_express_com` VALUES (6, 'ems', 'EMS', '2017-11-24 09:51:35', '2017-11-24 09:51:35');
INSERT INTO `tz_express_com` VALUES (7, 'zto', '中通', '2017-11-24 09:52:00', '2017-11-24 09:52:00');
INSERT INTO `tz_express_com` VALUES (8, 'ht', '汇通', '2017-11-24 09:52:22', '2017-11-24 09:52:22');
INSERT INTO `tz_express_com` VALUES (9, 'qf', '全峰', '2017-11-24 09:52:46', '2017-11-24 09:52:46');
INSERT INTO `tz_express_com` VALUES (10, 'db', '德邦', '2017-11-24 09:53:10', '2017-11-24 09:53:10');
INSERT INTO `tz_express_com` VALUES (11, 'jd', '京东快递', '2017-11-24 09:53:46', '2017-11-24 09:53:46');
INSERT INTO `tz_express_com` VALUES (12, 'zjs', '宅急送', '2017-11-24 09:54:04', '2017-11-24 09:54:04');
INSERT INTO `tz_express_com` VALUES (13, 'tdhy', '天地华宇', '2017-11-24 09:54:50', '2017-11-24 09:54:50');
INSERT INTO `tz_express_com` VALUES (14, 'axd', '安信达快递', '2017-11-24 09:55:12', '2017-11-24 09:55:12');
INSERT INTO `tz_express_com` VALUES (15, 'yousu', '优速快递', '2017-11-24 09:55:30', '2017-11-24 09:55:30');
INSERT INTO `tz_express_com` VALUES (16, 'jiaji', '佳吉快运', '2017-11-24 09:55:59', '2017-11-24 09:55:59');
INSERT INTO `tz_express_com` VALUES (17, 'ztky', '中铁快运', '2017-11-24 09:56:17', '2017-11-24 09:56:17');
INSERT INTO `tz_express_com` VALUES (18, 'yzgn', '邮政国内（挂号信)', '2017-11-24 10:00:56', '2017-11-24 10:00:56');
INSERT INTO `tz_express_com` VALUES (19, 'xfwl', '信丰物流', '2017-11-24 10:01:16', '2017-11-24 10:01:16');
INSERT INTO `tz_express_com` VALUES (20, 'zhongyou', '中邮物流', '2017-11-24 10:01:48', '2017-11-24 10:01:48');

-- ----------------------------
-- Table structure for tz_intentional_user
-- ----------------------------
DROP TABLE IF EXISTS `tz_intentional_user`;
CREATE TABLE `tz_intentional_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `qq_num` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ号码',
  `sex` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0男 1 女',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `agent_city` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代理意向城市片区',
  `address` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `message` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言',
  `email` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operater` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '意向用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_intentional_user
-- ----------------------------
INSERT INTO `tz_intentional_user` VALUES ('0ab1997c3f904649b14d8400ed4e3a01', '徐鹏', '18090732986', '', NULL, NULL, NULL, NULL, '', NULL, '2018-01-09 19:47:53', '2018-01-09 19:47:53', NULL);
INSERT INTO `tz_intentional_user` VALUES ('27419590ff1a4352b118e3cb075ed4ec', '张蓉', '13880483535', '1264922653', NULL, NULL, NULL, NULL, '', NULL, '2017-11-29 23:38:56', '2017-11-29 23:38:56', NULL);
INSERT INTO `tz_intentional_user` VALUES ('b216fb53f79f4f41a99efba50812d892', '蒲玲', '13699036809', '', NULL, NULL, NULL, NULL, '', NULL, '2017-12-11 12:31:00', '2017-12-11 12:31:00', NULL);
INSERT INTO `tz_intentional_user` VALUES ('b8701131f23a4fce900065dcff7a7f2f', '杨永安', '13953286637', '', NULL, NULL, NULL, NULL, '', NULL, '2017-11-25 17:39:45', '2017-11-25 17:39:45', NULL);
INSERT INTO `tz_intentional_user` VALUES ('cd73b30aa2004fb799f9aa1ecb462705', '寻寻', '13198278888', '136508529', NULL, NULL, NULL, NULL, '', NULL, '2017-12-10 17:48:35', '2017-12-10 17:48:35', NULL);
INSERT INTO `tz_intentional_user` VALUES ('e0a3e2e5522c43b8898a50694918276f', '杨永安', '13953286637', '', NULL, NULL, NULL, NULL, '', NULL, '2017-11-25 17:40:00', '2017-11-25 17:40:00', NULL);
INSERT INTO `tz_intentional_user` VALUES ('f50a3eb092d940179a7cc71f1e9fa06a', '杨永安', '13953286637', '', NULL, NULL, NULL, NULL, '', NULL, '2017-11-25 17:39:45', '2017-11-25 17:39:45', NULL);

-- ----------------------------
-- Table structure for tz_item
-- ----------------------------
DROP TABLE IF EXISTS `tz_item`;
CREATE TABLE `tz_item`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id，同时也是商品编号 由uuid生成唯一编号',
  `item_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品卖点介绍描述文字信息',
  `price` decimal(10, 2) NOT NULL COMMENT '商品价格，单位为：分',
  `love_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '爱心会员价钱',
  `member_price` decimal(10, 2) NOT NULL COMMENT '商品会员价格，单位为：分',
  `num` int(10) NOT NULL COMMENT '库存数量',
  `barcode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品条形码',
  `category_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品分类所属类目',
  `image_url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮播商品图片在图片服务器中的地址 包含1-5张图片地址以字符串形式保存，',
  `pricture_url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品详情图片地址',
  `homepage_url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '缩略图地址',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '商品状态，1-正常，2-下架，3-删除',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '该商品由哪个管理员添加产生',
  `item_post` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '该商品的邮费',
  `sales_num` int(10) NULL DEFAULT 500,
  `mall_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '该商品添加至哪个商家平台',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_item
-- ----------------------------
INSERT INTO `tz_item` VALUES ('20b19960498244eeadf56d4dc22f522e', '[爱心520]戌狗520红酒尊享大礼包', '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', 520.00, 520.00, 520.00, 984, '2333122asdfbas', '4', 'group1/M00/00/0E/rBBH51oY48mAPMasAAs6maeTLBk402.png?xugouzunxianglihezhutu.png', 'group1/M00/00/10/rBBH51oZCkSAZ2AGAABqfjwvyLI216.jpg?01xugouhongjiuxiangqing.jpg,group1/M00/00/10/rBBH51oZCkmADky6AAGfpcwkzP8595.jpg?02xugouhongjiuxiangqing.jpg,group1/M00/00/10/rBBH51oZCk6ATdKGAAB5dsQOk7A710.jpg?03xugouhongjiuxiangqing.jpg,group1/M00/00/10/rBBH51oZClKAHF5RAAG8k_-fSuE834.png?04xugouhongjiuxiangqing.png', 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', 1, '001', 0.00, 516, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 11:30:13', '2017-11-25 14:14:37');
INSERT INTO `tz_item` VALUES ('30a1d300698f45bcb43ab9c2cb9b9475', '【520保湿乳液】520恒润保湿精华乳', '微脂囊（Liposomes）科技，延长活泉水作用时间，舒缓抗刺激，深层补水并锁住水分，使皮肤倍感柔滑。', 285.00, 52.00, 87.00, 996, 'sdflsdkf12343543', '3', 'group1/M00/00/0D/rBBH51oXszeARrDaAAZ5PqdIDAA755.png?精华液主图.png', 'group1/M00/00/12/rBBH51og5AuAGgFNAAQgFyMzks4920.png?1.png,group1/M00/00/12/rBBH51og5BGATDyfAAZKd_fVVSY813.png?2.png,group1/M00/00/12/rBBH51og5BiAJwjtAAei3DFP854514.png?3.png,group1/M00/00/12/rBBH51og5B6AfS7GAAmApFb3fWk661.png?4.png,group1/M00/00/12/rBBH51og5CSANMqkAA383J4H2Rk027.png?5.png,group1/M00/00/12/rBBH51og5CuATEN8ABFG8SkQBCI202.png?7.png', 'group1/M00/00/0D/rBBH51oXszeARrDaAAZ5PqdIDAA755.png?精华液主图.png', 2, '96479df69b21468ca8a1e0ce754df5bb', 0.00, 515, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-16 11:05:45', '2017-12-01 13:10:00');
INSERT INTO `tz_item` VALUES ('41146ba6a1ac41a6b424f9d0390ffbcd', '[爱心520]52度520白酒尊享大礼盒', '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', 520.00, 520.00, 520.00, 996, 'dnasjksdhnajknh153141453', '4', 'group1/M00/00/0E/rBBH51oY4eWAKqW7AApuFyyBxoI363.png?52bajiuzunxiangzhutu.png', 'group1/M00/00/10/rBBH51oZCWWAKyGtAAByMyvngRQ409.jpg?01zunxiang52lihexiangqing.jpg,group1/M00/00/10/rBBH51oZCWqAegdBAAF9rB0ZxNQ340.jpg?02zunxiang52lihexiangqing.jpg,group1/M00/00/10/rBBH51oZCXCAReepAAB80KOWr88312.jpg?03zunxiang52lihexiangqing.jpg,group1/M00/00/10/rBBH51oZCXWAMGUoAAEAFZKdVGk768.png?04zunxiang52lihexiangqing.png', 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', 1, '001', 0.00, 510, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 11:22:12', '2017-11-27 17:22:22');
INSERT INTO `tz_item` VALUES ('4aa06c6db34a447ca56f90e450e5c03c', '[爱心520]申猴520红酒尊享大礼包', '申猴520红酒尊享大礼包-在与心和灵魂的共鸣。申猴520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', 520.00, 520.00, 520.00, 997, 'asdjkaljsd1231541521222', '4', 'group1/M00/00/0E/rBBH51oY4u2AGgc6AAyGRtyyclA746.png?shenghouhongjiuzunxiangzhutu.png', 'group1/M00/00/10/rBBH51oZCh2ABdg3AABpsSulyEs564.jpg?01shenhouhongjiuxiangqing.jpg,group1/M00/00/10/rBBH51oZCiKAJukOAAFz-vqr0kA199.jpg?02shenhouhongjiuxiangqing.jpg,group1/M00/00/10/rBBH51oZCieACnj2AAB5ZfkpVHY799.jpg?03shenhouhongjiuxiangqing.jpg,group1/M00/00/10/rBBH51oZCiuAdZPNAAHTWuuhTLo607.png?04shenhouhongjiuxiangqing.png', 'group1/M00/00/0E/rBBH51oY4uqAaKKGAAyGRtyyclA102.png?shenghouhongjiuzunxiangzhutu.png', 1, '001', 0.00, 507, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 11:26:32', '2017-11-25 14:13:58');
INSERT INTO `tz_item` VALUES ('67bb35e22d49424ca902710174033af8', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', 259.00, 52.00, 87.00, 970, '2sdfsmdkfl142245', '1', 'group1/M00/00/0F/rBBH51oZAVGAMT2EAAxHm61Lu6c465.png?1xugouhongjiu.png,group1/M00/00/0F/rBBH51oZAVGANPH8AAJXpp9aqJQ956.jpg?2xugouhongjiu.jpg', 'group1/M00/00/0F/rBBH51oY_TiAFlarAAWSI_DDzzE696.jpg?1xuhouhongjiu.jpg,group1/M00/00/0F/rBBH51oY_TmAL1D2AApUWsXw2xs745.png?2xuhouhongjiu.png,group1/M00/00/0F/rBBH51oY_TiAHObXAAM589-HNQM694.png?3xuhouhongjiu.png,group1/M00/00/0F/rBBH51oY_TmAb3FgAADxFXOBOgE847.png?4xuhouhongjiu.png,group1/M00/00/0F/rBBH51oY_TqAQKBDAAuZSe6ty6A473.png?5xuhouhongjiu.png,group1/M00/00/0F/rBBH51oY_TuAY_91AAFPux98bZY103.png?6xuhouhongjiu.png,group1/M00/00/0F/rBBH51oY_TuAXsmaAAVFRwBZW0o738.png?7xuhouhongjiu.png,group1/M00/00/0F/rBBH51oY_TuANjwyAAAWVZ5OlvI743.png?8xuhouhongjiu.png,group1/M00/00/0F/rBBH51oY_TuAcRfFAAJXpp9aqJQ367.jpg?9xuhouhongjiu.jpg', 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', 1, '001', 0.00, 564, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-16 11:01:27', '2017-11-25 13:36:12');
INSERT INTO `tz_item` VALUES ('796bcabea32045f3bb28a45201ef098a', '[爱心520苹果酵母组合套装]', '富含苹果及酵母萃取精华，能及时渗透肌肤，滋养营养改善肌肤光泽.', 520.00, 520.00, 520.00, 1000, 'ajdklasjjfdksfksdnjksd', '3', 'group1/M00/00/14/rBBH51pXEjCAdTlrAAezy2v-iws244.png?huahzuangptaocanzhutu.png', 'group1/M00/00/13/rBBH51pVjzaACZAWAAH89HZC3pU941.jpg?huazhuangptaozhaungxiangqing2.jpg', 'group1/M00/00/13/rBBH51pVjguAZFkxAAezy2v-iws172.png?huahzuangptaocanzhutu.png', 1, '96479df69b21468ca8a1e0ce754df5bb', 0.00, 500, '2cfc2e2d185e4609af521e2b744ddb98', '2018-01-10 11:52:54', '2018-01-11 15:28:51');
INSERT INTO `tz_item` VALUES ('7aea5ec85c8a416f82435f1c6fb687bd', '[爱心520]45度520白酒尊享大礼盒', '“香飘万里酒，知音酒不同。”  45度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', 520.00, 520.00, 520.00, 1003, 'asfdsfdmkl15312', '4', 'group1/M00/00/0E/rBBH51oY4S6AZzWvAAprKUuSq68542.png?45baijiuzenxianglihezhutu.png', 'group1/M00/00/10/rBBH51oZCA6AWUXmAABxn4rcXn8618.jpg?01zunixangbaijiulihexiangqing.jpg,group1/M00/00/10/rBBH51oZCA-AB1bBAAF9o5j-w8c682.jpg?02zunixangbaijiulihexiangqing.jpg,group1/M00/00/10/rBBH51oZCA-AK25hAAB84ADDrWU812.jpg?03zunixangbaijiulihexiangqing.jpg,group1/M00/00/10/rBBH51oZCA-AQJ5oAAEAFarwBv0822.png?04zunixangbaijiulihexiangqing.png', 'group1/M00/00/0E/rBBH51oY4TqAbF0KAAprKUuSq68194.png?45baijiuzenxianglihezhutu.png', 1, '001', 0.00, 497, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 11:19:16', '2017-11-25 11:19:40');
INSERT INTO `tz_item` VALUES ('81b2922a27094271a0b58240ad78b794', '【520红酒】申猴干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒。', '申猴干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', 259.00, 52.00, 87.00, 9979, 'klsdgmk14a5sd453a4dfa', '1', 'group1/M00/00/0D/rBBH51oXz6-AeU6oAAKE7cTx3G0824.jpg?2shenhouhongjiu.jpg,group1/M00/00/0D/rBBH51oXz76ACOIlAAxn_XvpBNs230.png?1shenhouhongjiu.png', 'group1/M00/00/0F/rBBH51oY_W-AUtaQAAWZg3A_TSg768.jpg?1shenghouhongjiu.jpg,group1/M00/00/0F/rBBH51oY_W-ADdqtAAnsKDtMuYk014.png?2shenhouhongjiu.png,group1/M00/00/0F/rBBH51oY_W6AFj0rAAM6SZzirMo500.png?3shenhouhongjiu.png,group1/M00/00/0F/rBBH51oY_XOAQ-GrAAkcby6ZEPE707.png?4shenhouhongjiu.png,group1/M00/00/0F/rBBH51oY_XKAG-3YAAPZjezKQ3o532.png?5shenhouhongjiu.png,group1/M00/00/0F/rBBH51oY_XCAcSQmAACbQFhimbA677.png?6shenhouhongjiu.png,group1/M00/00/0F/rBBH51oY_XWAHN_TAAVH6saeGro989.png?7shenhouhongjiu.png,group1/M00/00/0F/rBBH51oY_XOAWTzqAAAKl03ISE4301.png?8shenhouhongjiu.png,group1/M00/00/0F/rBBH51oY_XaAVNQyAAQQDpEPQ_I116.jpg?9shenhouhongjiu.jpg', 'group1/M00/00/0C/rBBH51oXj9aAbxzRAAxn_XvpBNs972.png?申猴红酒主图.png', 1, '001', 0.00, 527, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-16 11:00:12', '2017-11-25 13:19:44');
INSERT INTO `tz_item` VALUES ('8dca2aefd538483bbc737e28a64ddedd', '[520]45度520酒 500Ml瓶 浓香优雅，风味独特', '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', 259.00, 52.00, 87.00, 988, '123455656', '2', 'group1/M00/00/0E/rBBH51oX18-AYEgUAARTYe4Rsrg045.png?2baijiu45.png,group1/M00/00/0E/rBBH51oX19CAU9ffAAUugSMBvu8322.png?1baijiu45lunbotu.png', 'group1/M00/00/0E/rBBH51oYxxSAD_dKAAQTxdJOcOo154.png?baijiuxiangqing (1).png,group1/M00/00/0E/rBBH51oYxxSAdZ4aAAUrxpLeMmQ348.png?baijiuxiangqing (2).png,group1/M00/00/0E/rBBH51oYxxSALSmoAAXNPENIusc734.png?baijiuxiangqing (3).png,group1/M00/00/0E/rBBH51oYxxaAKfoMAAcKAe-wHhg699.png?baijiuxiangqing (4).png,group1/M00/00/0E/rBBH51oYxxaATpy_AAes_0xoO_k437.png?baijiuxiangqing (5).png,group1/M00/00/0E/rBBH51oYxxaAWnb7AAl1IAsinVA788.png?baijiuxiangqing (6).png', 'group1/M00/00/0D/rBBH51oXvTuAH7EaAAfsgJ3BM4g384.png?白酒主图.png', 1, '001', 0.00, 518, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-16 11:04:17', '2017-11-24 16:26:51');
INSERT INTO `tz_item` VALUES ('90b690312e5d4fb0b89f4d82a0a8faba', '[爱心520]爱心520红酒超值大礼盒', '520红酒-在与心和灵魂的共鸣。520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', 520.00, 520.00, 520.00, 992, '23as1f23sddg12dg5', '4', 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', 'group1/M00/00/12/rBBH51og4neAF44zAACEl_lmboY953.png?hongjiulihexiangqing01.png,group1/M00/00/12/rBBH51og4oWAYg07AAd0pdOCvb0937.png?hongjiulihexiangqing02.png,group1/M00/00/12/rBBH51og4oyARGW9AACnelBrcl8330.png?hongjiulihexiangqing03.png,group1/M00/00/12/rBBH51og4peAC7nbAAUwwhLQ_fs516.png?hongjiulihexiangqing04.png,group1/M00/00/12/rBBH51og4qKAE5m9AAP8oIbo1ZI494.png?hongjiulihexiangqing05.png,group1/M00/00/12/rBBH51og4qqAWQ-yAAMuGfL6MQ4631.png?hongjiulihexiangqing06.png', 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', 2, '96479df69b21468ca8a1e0ce754df5bb', 0.00, 544, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-16 11:11:12', '2017-12-01 13:03:37');
INSERT INTO `tz_item` VALUES ('9e103d36ac024616a11095ee99dc8abe', '[爱心520苹果酵母组合套装]', '富含苹果及酵母萃取精华，能及时渗透肌肤，滋养营养改善肌肤光泽.', 520.00, 520.00, 520.00, 998, 'sdknhfjsdfjkns', '4', 'group1/M00/00/14/rBBH51pXEXqABKWRAAezy2v-iws879.png?huahzuangptaocanzhutu.png', 'group1/M00/00/14/rBBH51plUY-Ab9Z9AAH8k8_TnXs115.jpg?fuhuzhuhetaocanxianqgingtu.jpg', 'group1/M00/00/14/rBBH51pXEWmALMSJAAezy2v-iws905.png?huahzuangptaocanzhutu.png', 1, '96479df69b21468ca8a1e0ce754df5bb', 0.00, 502, '2cfc2e2d185e4609af521e2b744ddb98', '2018-01-11 15:25:52', '2018-01-22 10:50:59');
INSERT INTO `tz_item` VALUES ('be1836fe6a37498e853c0a52d0a26177', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', 259.00, 52.00, 87.00, 992, 'asdhasjkdhajk143545', '2', 'group1/M00/00/0E/rBBH51oX1-aAdtEaAAQQ_Vb0d2I873.png?2baijiu52lunbo.png,group1/M00/00/0E/rBBH51oX1-iAYinnAAfsgJ3BM4g242.png?1baijiu52du.png', 'group1/M00/00/0E/rBBH51oYxxSAD_dKAAQTxdJOcOo154.png?baijiuxiangqing (1).png,group1/M00/00/0E/rBBH51oYxxSAdZ4aAAUrxpLeMmQ348.png?baijiuxiangqing (2).png,group1/M00/00/0E/rBBH51oYxxSALSmoAAXNPENIusc734.png?baijiuxiangqing (3).png,group1/M00/00/0E/rBBH51oYxxaAKfoMAAcKAe-wHhg699.png?baijiuxiangqing (4).png,group1/M00/00/0E/rBBH51oYxxaATpy_AAes_0xoO_k437.png?baijiuxiangqing (5).png,group1/M00/00/0E/rBBH51oYxxaAWnb7AAl1IAsinVA788.png?baijiuxiangqing (6).png', 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', 1, '001', 0.00, 517, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-16 11:02:57', '2017-11-25 09:27:45');
INSERT INTO `tz_item` VALUES ('c4cd1d8d16fa4bcab350ca756ec02684', '[爱心520]爱心520白酒超值大礼盒', '“香飘万里酒，知音酒不同。”   520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', 520.00, 520.00, 520.00, 979, 'ahdajkshdkja1', '4', 'group1/M00/00/0D/rBBH51oXxLWAdr8tAAxPujzrmOk351.png?baijiulihezhutu.png', 'group1/M00/00/11/rBBH51og1ZiATF4IAAB4mlHgb3M623.png?01bajiulihexaingqing01.png,group1/M00/00/11/rBBH51og1aOAEUhiAAeE0iOLkyE455.png?02bajiulihexaingqing02.png,group1/M00/00/11/rBBH51og1aqAVVv6AACuyuBIByc480.png?03bajiulihexaingqing03.png,group1/M00/00/11/rBBH51og1bqAZaZzAAUwwhFJGLk020.png?04bajiulihexaingqing04.png,group1/M00/00/11/rBBH51og1cqAD3r4AAP8mkjf0HE740.png?05bajiulihexaingqing05.png,group1/M00/00/11/rBBH51og1euAMFQaAAMtjP2XHmw298.png?06bajiulihexaingqing06.png', 'group1/M00/00/0D/rBBH51oXxLWAdr8tAAxPujzrmOk351.png?baijiulihezhutu.png', 2, '96479df69b21468ca8a1e0ce754df5bb', 0.00, 560, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-16 11:12:19', '2017-12-01 12:09:49');
INSERT INTO `tz_item` VALUES ('de31a4bd7c524e4e8865226729b43725', '【苹果酵母臻颜面膜】', '富含多种保温成分，补充肌肤所需水分，有效锁水储水，改善肌肤补水锁水能力，有效修护肌肤，淡化细纹，收敛毛孔，改善肤质，紧致提升松驰肌肤；密集补水同时带来深层滋养，补水，舒缓，修护面部肌肤；时刻似有水光萦绕，长期使用令肌肤亮润柔滑，弹润幼嫩，呈现肌肤动人光彩。', 318.00, 52.00, 87.00, 989, 'dsadjksdjkd2345fg', '3', 'group1/M00/00/13/rBBH51pXDMaABbB-AAjTL2EiHDs232.png?mianmozhutu.png,group1/M00/00/13/rBBH51pXDM2ABbHpAA0aSsKdgew223.png?mianmo2.png,group1/M00/00/13/rBBH51pXDNSAa2StAAnKO112zGU763.png?miamo3.png', 'group1/M00/00/14/rBBH51pXDQ-AOM9QAAHjqSUicok301.jpg?mianmoxiangqing.jpg', 'group1/M00/00/14/rBBH51pXDOyAH5rHAAjTL2EiHDs480.png?mianmozhutu.png', 1, '96479df69b21468ca8a1e0ce754df5bb', 0.00, 511, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-23 11:32:59', '2018-01-11 15:06:58');

-- ----------------------------
-- Table structure for tz_item_category
-- ----------------------------
DROP TABLE IF EXISTS `tz_item_category`;
CREATE TABLE `tz_item_category`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `sort` int(4) NULL DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  `logo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类图标地址',
  `link_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类链接跳转地址',
  `is_parent` tinyint(1) NULL DEFAULT NULL COMMENT '是否为父节点 1为true，0为false',
  `operater` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该记录操作人id',
  `p_id` int(4) NULL DEFAULT NULL COMMENT '分类父节点',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_item_category
-- ----------------------------
INSERT INTO `tz_item_category` VALUES (1, 1, '红酒', 'group1/M00/00/0A/rBBH51oWKzmADiSZAAAboXtp8lw672.png?red.png', '', 1, '001', NULL, '2017-11-23 09:58:15', '2017-11-23 13:16:44');
INSERT INTO `tz_item_category` VALUES (2, 2, '白酒', 'group1/M00/00/0B/rBBH51oWYtuAZUn6AAAhBg6s3tQ658.png?white.png', '', 1, '001', NULL, '2017-11-23 10:09:45', '2017-11-23 13:55:43');
INSERT INTO `tz_item_category` VALUES (3, 3, '化妆品', 'group1/M00/00/0A/rBBH51oWLgWAKDf6AAAgxVLRW6Y385.png?cosmetics.png', '', 1, '001', NULL, '2017-11-23 10:10:12', '2017-11-23 10:10:12');
INSERT INTO `tz_item_category` VALUES (4, 4, '套餐', 'group1/M00/00/0A/rBBH51oWLmSAUIBmAAAhzuSPb3s404.png?package.png', '', 1, '001', NULL, '2017-11-23 10:11:45', '2017-11-23 10:11:45');

-- ----------------------------
-- Table structure for tz_mall
-- ----------------------------
DROP TABLE IF EXISTS `tz_mall`;
CREATE TABLE `tz_mall`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mall_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '各购物平台名称',
  `mall_url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '各购物平台系统主界面的http请求展示地址',
  `mall_status` int(2) NULL DEFAULT 1 COMMENT '各个购物平台的运营状态 1.正常 2.非正常',
  `mall_introduce` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商场的介绍信息',
  `mall_manager` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该平台的管理者',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '该购物平台创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '购物平台的更新时间',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该记录操作人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物平台信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_mall
-- ----------------------------
INSERT INTO `tz_mall` VALUES ('2cfc2e2d185e4609af521e2b744ddb98', '爱心520体验店', '', 1, NULL, NULL, '2017-10-09 16:04:01', '2017-10-09 16:04:03', NULL);

-- ----------------------------
-- Table structure for tz_manager
-- ----------------------------
DROP TABLE IF EXISTS `tz_manager`;
CREATE TABLE `tz_manager`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `manager_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '后台管理者名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户code',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '注册邮箱',
  `mall_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属商家',
  `last_upload` datetime(0) NULL DEFAULT NULL COMMENT '管理者最后一次登录时间',
  `created_time` datetime(0) NULL,
  `updated_time` datetime(0) NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `manager_name`(`manager_name`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台管理员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_manager
-- ----------------------------
INSERT INTO `tz_manager` VALUES ('222728aa13bb4803889c726cc207ecc4', 'liuyufeng', '1b8388d280ae41a1c020755461795220', '18215589770', '0UGCeH4odFc', '271363661@qq.com', '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-15 10:55:30', '2017-12-07 15:04:50', '2017-12-13 11:17:07');
INSERT INTO `tz_manager` VALUES ('7f8b77f0f7204a7e981046ff7cafdb0d', 'zhaodan', '3dcb9963c2e8b349851c8c21aaca5bee', '13778256586', 'SU0fo5pY', '362034181@qq.com', '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-20 09:38:03', '2017-12-01 11:52:46', '2017-12-20 09:37:39');
INSERT INTO `tz_manager` VALUES ('96479df69b21468ca8a1e0ce754df5bb', 'admin', '278a45ed51581a9364131c3ff2ee9019', '13883254087', 'r2QaABJLKgUbQk', '2282500426@qq.com', '2cfc2e2d185e4609af521e2b744ddb98', '2018-02-16 21:06:20', '2017-12-01 11:07:55', '2017-12-11 10:44:45');

-- ----------------------------
-- Table structure for tz_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `tz_manager_role`;
CREATE TABLE `tz_manager_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `manager_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员主键',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_manager_role
-- ----------------------------
INSERT INTO `tz_manager_role` VALUES ('56b9290f4d5c477d90d7bbfcf25778cf', '222728aa13bb4803889c726cc207ecc4', '8f28111177b14497bc7ac86e1a2ba690', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-13 11:17:07', '2017-12-13 11:17:07');
INSERT INTO `tz_manager_role` VALUES ('5c5561b706134f8da100677b1c4a7ffa', '96479df69b21468ca8a1e0ce754df5bb', '05b63b913c8b45caaa36b1bef832a05a', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-11 10:44:47', '2017-12-11 10:44:47');
INSERT INTO `tz_manager_role` VALUES ('78b0f4fcd0b44d70abe17e36cc1a8aa9', '7f8b77f0f7204a7e981046ff7cafdb0d', '04a172874f4042918fdb5e6116f68866', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-11 13:53:45', '2017-12-11 13:53:45');

-- ----------------------------
-- Table structure for tz_order
-- ----------------------------
DROP TABLE IF EXISTS `tz_order`;
CREATE TABLE `tz_order`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '当前登录用户id',
  `shipping_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物流运单号',
  `status` int(2) NOT NULL COMMENT '订单状态   0、取消订单，1、未付款，2、已付款待发货、3、已发货，4、交易成功，5、交易关闭,6、已退款\'',
  `payment` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分',
  `payment_type` int(2) NULL DEFAULT NULL COMMENT '1.支付宝支付 2. 微信支付 3.网银支付 4.爱心值支付',
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '支付账户信息',
  `love_value` int(11) NULL DEFAULT 0 COMMENT '爱心值支付点数',
  `discount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '折扣券值',
  `discount_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '折扣券描述',
  `order_num` int(4) NOT NULL DEFAULT 0 COMMENT '订单中商品总件数',
  `post_fee` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分',
  `mall_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '该订单在哪家购物平台产生 购物平台编号id',
  `buyer_nick` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '买家昵称',
  `created_time` datetime(0) NOT NULL COMMENT '订单创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '订单更新时间',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '交易关闭时间',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `buyer_rate` int(2) NULL DEFAULT 0 COMMENT '买家是否已经评价',
  `buyer_message` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '买家留言',
  `type` int(11) NULL DEFAULT 1 COMMENT '订单类型： 0 ：会员升级 1：购物',
  `combo_num` int(4) NULL DEFAULT 0 COMMENT '订单中套餐数量',
  `pay_single_item` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '该订单支付单品的总价钱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单列表综合信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_order
-- ----------------------------
INSERT INTO `tz_order` VALUES ('00791d8598554484a69a12595cfa372c', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 52.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-12-01 20:17:04', '2017-12-04 17:36:05', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 52.00);
INSERT INTO `tz_order` VALUES ('060960b73af74bac8e2624cfe0bf6c52', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 52.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-27 20:53:16', '2017-11-28 22:01:32', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 52.00);
INSERT INTO `tz_order` VALUES ('08e1582ddb5b4cfbbfa243beb2ebfc9d', 'e040813c1f8149b7a7a56a4ee5f2a0de', '7913215086', 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:23:41', '2017-12-19 11:27:34', '2017-11-25 19:25:18', '2017-12-01 17:34:36', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '96479df69b21468ca8a1e0ce754df5bb', 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('0c774eafb6104767a351ed4ec3b9bdcd', '74f0add8c7ad471980617bbf4709a23d', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 18:07:14', '2017-12-03 12:11:33', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('0f46378a8b204e50a41f5d0d8bcf9696', 'c33c373d414741cab5ab80f4ffff2b0a', NULL, 0, 259.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_863175', '2017-12-06 23:27:12', '2017-12-06 23:31:56', NULL, NULL, '2017-12-06 23:31:56', NULL, NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('1154336cf67b473da74e4783032c3446', '12d4ab2da7394354996db664828c7242', NULL, 1, 87.00, 0, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_445126', '2017-12-01 18:53:29', '2017-12-01 18:53:29', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 87.00);
INSERT INTO `tz_order` VALUES ('11f2d2973b7b4dd1bc9056eabbdcd8f0', 'b852902504194934a2be63c2f394d335', NULL, 0, 259.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_511641', '2017-12-07 17:08:58', '2017-12-07 17:12:09', NULL, NULL, '2017-12-07 17:12:09', NULL, NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('15e94ed40f7940f1969b9abd536e6e11', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', NULL, 0, 87.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-12-01 14:18:37', '2017-12-01 20:28:40', NULL, NULL, '2017-12-01 20:28:40', NULL, NULL, 0, '', 1, 0, 87.00);
INSERT INTO `tz_order` VALUES ('168092d13132424887c19db65e65b360', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', NULL, 0, 87.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_527621', '2017-11-27 11:20:58', '2017-11-27 11:23:30', NULL, NULL, '2017-11-27 11:23:30', NULL, NULL, 0, '', 1, 0, 87.00);
INSERT INTO `tz_order` VALUES ('188ed236c05645989d9b56b512dd5453', '2029c8cfc0da4748ba83172d1e850dbc', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-30 19:22:22', '2017-12-02 18:23:36', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('1a97c583ab414b5b8ee300aba85522b2', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-29 13:43:26', '2017-11-30 13:52:23', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('1dc85fce30444e5083a212201a83e28b', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 52.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-27 20:53:00', '2017-11-28 22:01:32', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 52.00);
INSERT INTO `tz_order` VALUES ('202f62ff3aab430f80f8cb0f5ac4175e', '0ef7fdf322934c55b0a804d561228328', NULL, 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-28 13:32:19', '2017-12-19 11:27:34', '2017-11-28 13:33:11', '2017-11-29 15:26:24', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '001', 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('2153e6d947914ff3bdd1e474902c210e', '7bee0bfe55ea439693c01c7271a83d0e', '0', 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-12-04 10:05:39', '2017-12-19 11:27:34', '2017-12-04 10:05:51', '2017-12-04 10:45:26', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '7f8b77f0f7204a7e981046ff7cafdb0d', 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('2938368e9c0740158fea8db0663c295d', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 52.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-12-01 20:16:48', '2017-12-04 17:36:05', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 52.00);
INSERT INTO `tz_order` VALUES ('29679ddef20b40b5aff34c35a8ca8f8b', '3640cddab5394d01a3bc839599bcceb0', NULL, 5, 348.00, 0, NULL, 0, 0.00, '暂无优惠券可以使用！', 4, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '派大星', '2017-12-18 16:05:08', '2018-01-30 11:43:13', NULL, NULL, NULL, '2018-01-30 11:43:13', NULL, 0, '', 1, 0, 348.00);
INSERT INTO `tz_order` VALUES ('2bbb1860bf994152b7edf946f34da038', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 208.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 4, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-28 11:10:59', '2017-11-29 13:42:45', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 208.00);
INSERT INTO `tz_order` VALUES ('2fd0c92e93494dcd895cf38c55782e42', '74f0add8c7ad471980617bbf4709a23d', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 17:49:34', '2017-12-03 12:11:33', NULL, NULL, NULL, NULL, NULL, 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('30c4ea3921f242b1a6c3845eb7d4733d', '3640cddab5394d01a3bc839599bcceb0', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '派大星', '2018-03-20 23:33:13', '2018-03-27 12:26:53', NULL, NULL, NULL, '2018-03-27 12:26:53', NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('310641a069c946e4afef40aab46c7e51', 'b2572ce53c23461ea802900a4813fd39', NULL, 1, 52.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_547415', '2017-12-29 11:55:44', '2017-12-29 11:55:44', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 52.00);
INSERT INTO `tz_order` VALUES ('31c8d75127da4a7d9c794668311a4df6', 'fdc70f24eb954d77b2a5f5f5559bcfed', NULL, 1, 607.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 2, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:53:40', '2017-11-25 19:53:40', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 87.00);
INSERT INTO `tz_order` VALUES ('39391208f4904f498df934ea41dca2d8', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', NULL, 5, 607.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 2, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_来了', '2017-12-01 22:40:56', '2017-12-08 16:04:37', NULL, NULL, NULL, '2017-12-08 16:04:37', NULL, 0, '', 1, 1, 87.00);
INSERT INTO `tz_order` VALUES ('3c950aec833049b2a5cf1732226ef342', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 260.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 5, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-27 20:54:32', '2017-11-28 22:01:32', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 260.00);
INSERT INTO `tz_order` VALUES ('43e865a1dcc346b8b498b66b11788fca', '2029c8cfc0da4748ba83172d1e850dbc', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-30 13:59:07', '2017-12-02 18:23:36', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('43f8cf20f3084b56936fa40a6d312172', '7bee0bfe55ea439693c01c7271a83d0e', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-29 21:17:39', '2017-12-04 10:06:02', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('4ec9d6add713407399a356241a2ab68d', '2b6467eeb24a485784ef32adcc8a9cd2', NULL, 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-29 17:09:04', '2017-12-19 11:27:34', '2017-11-29 17:14:37', '2017-12-01 17:28:40', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '96479df69b21468ca8a1e0ce754df5bb', 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('4f7e015bb5944f4fa87f30b5e4454365', '2029c8cfc0da4748ba83172d1e850dbc', NULL, 5, 87.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-30 19:04:33', '2017-12-02 18:23:36', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 87.00);
INSERT INTO `tz_order` VALUES ('53f7d257f6ef4b02bedc4de5e2bddf09', '74f0add8c7ad471980617bbf4709a23d', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 18:08:42', '2017-12-03 12:11:33', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('561b82410e6e4fd9975a78dd4f5617d2', '3877878856b74d2c8e276945a392a44b', NULL, 1, 520.00, 0, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_368263', '2018-01-04 12:03:49', '2018-01-04 12:03:49', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('564f7d123aa94954b838c51d12b2c8e5', '88b6ecc45dcf44a2a2d1d8072e1d1832', NULL, 0, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 14:41:17', '2017-11-25 19:25:13', NULL, NULL, '2017-11-25 19:25:13', NULL, NULL, 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('5902c7c886964300977c102b071ff9c7', 'bc51ff97b0e64e028548eff8fdfad989', '1', 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-30 10:59:57', '2017-12-19 11:27:34', '2017-11-30 11:00:59', '2017-11-30 15:35:24', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '001', 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('5b434d790392450ea0fd60010f2d65cd', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_527621', '2017-11-27 11:20:28', '2017-11-29 15:28:20', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('5cc6a3fab149492ba40ea96dfa23575f', '7bab8480e4414c1cb322b4383bf55285', NULL, 0, 259.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_217481', '2017-12-05 16:13:17', '2017-12-05 16:13:45', NULL, NULL, '2017-12-05 16:13:45', NULL, NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('5d34b04a162049afae9c6576a95a41e6', '585dcde9e7144f22ba07e22a81abc288', NULL, 1, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2018-04-10 17:51:43', '2018-04-10 17:51:43', NULL, NULL, NULL, NULL, NULL, 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('5fcf23355a64476689bfe54e02f3c0b9', '1a048c6799024e54aa29a962a545020b', NULL, 5, 259.00, 0, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '我', '2017-12-01 20:38:40', '2017-12-08 16:46:36', NULL, NULL, NULL, '2017-12-08 16:46:36', NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('678e361cdde2437abb1b222ab00d4711', 'ffdaac2511c8411fabd64c7acbf1534d', NULL, 1, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_194818', '2017-12-19 15:56:18', '2017-12-19 15:56:18', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('6ada52219ea84b2d97920adf643cf50d', '3877878856b74d2c8e276945a392a44b', NULL, 1, 520.00, 0, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_368263', '2018-01-04 10:48:26', '2018-01-04 10:48:26', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('74a65c8eb721473fa7c70232a7fcb84b', 'c33c373d414741cab5ab80f4ffff2b0a', NULL, 1, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_863175', '2017-12-06 23:32:14', '2017-12-06 23:32:14', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('773ffb9a64954d81a40571c0b538ca30', 'e7196f6e94cc42f7ba293dabf1bc59a9', NULL, 0, 259.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_035816', '2017-12-11 11:17:15', '2017-12-11 11:18:07', NULL, NULL, '2017-12-11 11:18:07', NULL, NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('77c14b96f39345068c7a13973300b925', '3640cddab5394d01a3bc839599bcceb0', NULL, 5, 609.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 7, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '派大星', '2017-12-18 16:33:54', '2018-01-30 11:43:13', NULL, NULL, NULL, '2018-01-30 11:43:13', NULL, 0, '', 1, 0, 609.00);
INSERT INTO `tz_order` VALUES ('78afcb2583b044f98510a0977c244a1f', 'c33c373d414741cab5ab80f4ffff2b0a', NULL, 0, 259.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_863175', '2017-12-06 23:25:51', '2017-12-06 23:31:58', NULL, NULL, '2017-12-06 23:31:58', NULL, NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('7d8afbb5302745e69ad83e8c51bf8fef', '2029c8cfc0da4748ba83172d1e850dbc', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-30 14:16:27', '2017-12-02 18:23:36', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('92d98e9f8101474aa4d8eae494c50707', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', NULL, 5, 607.00, 0, NULL, 0, 0.00, '暂无优惠券可以使用！', 2, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_来了', '2017-12-01 20:28:27', '2017-12-08 16:04:37', NULL, NULL, NULL, '2017-12-08 16:04:37', NULL, 0, '', 1, 1, 87.00);
INSERT INTO `tz_order` VALUES ('9788d82c80a44efbbc768e3bb503e109', '7bab8480e4414c1cb322b4383bf55285', NULL, 0, 259.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_217481', '2017-12-05 16:11:45', '2017-12-05 16:12:01', NULL, NULL, '2017-12-05 16:12:01', NULL, NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('9a869ee38715457bba8656ab22ecec61', '88b6ecc45dcf44a2a2d1d8072e1d1832', NULL, 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:23:32', '2017-11-29 15:26:48', '2017-11-25 19:24:39', '2017-11-29 15:26:48', NULL, NULL, '001', 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('9e94111cdcbd4d1f81737c633d1d6692', '288d7a49141341ffac8be7ab1d799cc0', '7913215085', 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-30 12:32:45', '2017-12-19 11:27:34', '2017-11-30 12:33:40', '2017-12-01 17:32:18', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '96479df69b21468ca8a1e0ce754df5bb', 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('9ec9b88ee5e64074915970405c924974', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-26 00:17:57', '2017-11-27 19:37:22', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('a03681ac9a634b838e8df25a2bd06b7c', '062fa5c384ac4439bbeff8150e34246b', '7913215093', 4, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-27 15:32:28', '2017-12-19 11:27:34', '2017-11-27 15:44:48', '2017-11-27 17:51:07', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '001', 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('a2b8f4b6c6584ab1b215ce93beb76607', 'fdc70f24eb954d77b2a5f5f5559bcfed', NULL, 1, 261.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 3, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:52:39', '2017-11-25 19:52:39', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 261.00);
INSERT INTO `tz_order` VALUES ('a2c832a7ca43483f851735d03af4e0f5', 'c33c373d414741cab5ab80f4ffff2b0a', NULL, 1, 53.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 3, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_863175', '2017-12-06 23:33:10', '2017-12-06 23:33:10', NULL, NULL, NULL, NULL, NULL, 0, '', 1, -3, 1613.00);
INSERT INTO `tz_order` VALUES ('a500d15d941f409eb92cc3118ef767a2', '3640cddab5394d01a3bc839599bcceb0', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '派大星', '2017-12-18 16:35:27', '2018-01-30 11:43:13', NULL, NULL, NULL, '2018-01-30 11:43:13', NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('a6858f7223624964b3339e4f09360e6b', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', NULL, 1, 87.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_来了', '2017-12-08 16:17:43', '2017-12-08 16:17:43', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 87.00);
INSERT INTO `tz_order` VALUES ('a9c9aeb6a0c34a6c9a7cd64db910d74f', 'a521d908027d4ff8b4d3b93ec588b931', '0', 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-30 15:07:14', '2017-12-19 11:27:34', '2017-11-30 15:08:40', '2017-11-30 15:33:53', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '001', 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('af16a25f1b624d7a9255fa4e31f40279', '3640cddab5394d01a3bc839599bcceb0', NULL, 0, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '派大星', '2018-01-30 14:09:05', '2018-01-30 14:09:22', NULL, NULL, '2018-01-30 14:09:22', NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('b13f0b2764574107836f3f9adcb58a42', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', NULL, 0, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_来了', '2017-12-08 16:37:59', '2017-12-08 16:38:04', NULL, NULL, '2017-12-08 16:38:04', NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('b39ef014ff9c42a4938468b1a856f43c', '2029c8cfc0da4748ba83172d1e850dbc', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-30 13:54:56', '2017-12-02 18:23:36', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('b4fb7dbb628c4fb8aa723407084f6e5c', 'cd8d6ebda4b74123b2b7b4a32b8b66d9', '7913215087', 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-29 10:09:47', '2017-12-19 11:27:34', '2017-11-29 10:10:41', '2017-12-01 17:28:51', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '96479df69b21468ca8a1e0ce754df5bb', 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('ba033efc2b3541b58337600acabcc57e', '7bab8480e4414c1cb322b4383bf55285', NULL, 0, 259.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_217481', '2017-12-05 16:14:30', '2017-12-05 16:14:45', NULL, NULL, '2017-12-05 16:14:45', NULL, NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('cd36f485aff14bda9868b809cd775d5a', 'a368b9051f2647bfa2964f3e385bbcdd', NULL, 5, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:20:42', '2017-12-12 12:18:49', NULL, NULL, NULL, '2017-12-12 12:18:49', NULL, 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('ce27083f647a4a199238e7d1ca59aac3', '74f0add8c7ad471980617bbf4709a23d', '7913215084', 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:18:53', '2017-12-19 11:27:34', '2017-11-25 19:20:47', '2017-12-01 17:34:34', '2017-12-19 11:27:34', '2017-12-19 11:27:34', '96479df69b21468ca8a1e0ce754df5bb', 0, '', 1, 1, 0.00);
INSERT INTO `tz_order` VALUES ('d1af1febb063412b87dde213d8ec75e1', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 52.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_547415', '2017-12-04 17:42:39', '2017-12-11 16:34:42', NULL, NULL, NULL, '2017-12-11 16:34:42', NULL, 0, '', 1, 0, 52.00);
INSERT INTO `tz_order` VALUES ('d5a7f5be29744f48b11b0e8cbe31a2d9', '1a048c6799024e54aa29a962a545020b', NULL, 5, 259.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-27 11:18:17', '2017-12-01 20:31:26', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('daddb03a720b491d9fcf4136b1278524', 'b2572ce53c23461ea802900a4813fd39', NULL, 5, 52.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-30 13:52:35', '2017-12-01 20:16:05', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 52.00);
INSERT INTO `tz_order` VALUES ('df3ba1fc5bb54ef99280d45d0355d81f', '07e7ba64169a43b9b9889db66d9b4c1f', NULL, 1, 318.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-27 12:03:16', '2017-11-27 12:03:16', NULL, NULL, NULL, NULL, NULL, 0, '', 1, 0, 318.00);
INSERT INTO `tz_order` VALUES ('e2285a09710043cb871277e6f219e813', 'e7196f6e94cc42f7ba293dabf1bc59a9', NULL, 0, 259.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_035816', '2017-12-11 11:20:12', '2017-12-11 11:21:38', NULL, NULL, '2017-12-11 11:21:38', NULL, NULL, 0, '', 1, 0, 259.00);
INSERT INTO `tz_order` VALUES ('e327f62d599b47c68990d220131da448', '1a048c6799024e54aa29a962a545020b', NULL, 1, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '我', '2017-12-08 16:48:13', '2017-12-08 16:48:13', NULL, NULL, NULL, NULL, NULL, 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('e5287e6ef3f44be985f1d492f939ed75', '24f8c8dfa0fc4acb8d0a8bba7e1773d6', NULL, 4, 520.00, 1, '', 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:16:39', '2017-11-29 17:15:38', '2017-11-25 19:23:16', '2017-11-29 17:15:38', NULL, NULL, '001', 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('e6a0d1ba9979456a9e834c33f0614400', 'fdc70f24eb954d77b2a5f5f5559bcfed', NULL, 0, 957.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 11, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:44:58', '2017-11-25 19:46:30', NULL, NULL, '2017-11-25 19:46:30', NULL, NULL, 0, '', 1, 0, 957.00);
INSERT INTO `tz_order` VALUES ('edb3726aeb31424783c1a5e0e7ce2703', 'f49dd0101441464a8a9b3dbbe224da75', NULL, 1, 520.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_745870', '2018-02-16 20:57:23', '2018-02-16 20:57:23', NULL, NULL, NULL, NULL, NULL, 0, '', 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('f18dfa1b9cb54059a2fe5d9ad6e654c6', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', NULL, 0, 87.00, NULL, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', '520_527621', '2017-11-25 19:46:50', '2017-11-27 11:20:17', NULL, NULL, '2017-11-27 11:20:17', NULL, NULL, 0, '', 1, 0, 87.00);
INSERT INTO `tz_order` VALUES ('kig2yjthui7i7molujsspug2qpbdl5ko', 'a368b9051f2647bfa2964f3e385bbcdd', NULL, 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:48:58', '2017-11-25 19:48:58', '2017-11-25 19:48:58', '2017-11-25 19:48:58', '2017-11-25 19:48:58', NULL, '001', 0, NULL, 0, 1, 0.00);
INSERT INTO `tz_order` VALUES ('rohojm5e7icm7n2sf0wb6mkz3upzxlty', '3ae34de0ea984028a9a8cc72ba649a7d', '00', 2, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 10, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 20:08:34', '2017-12-13 14:48:29', '2017-11-25 20:08:34', '2017-12-13 14:48:29', '2017-11-25 20:08:34', NULL, '96479df69b21468ca8a1e0ce754df5bb', 0, NULL, 1, 0, 520.00);
INSERT INTO `tz_order` VALUES ('yzg1rrhb4gmvwqyelo3ryr6ary8iorc1', '25f7a23d920f48cf89b3eae0e69d8df3', NULL, 4, 520.00, 1, NULL, 0, 0.00, '暂无优惠券可以使用！', 1, 0.00, '2cfc2e2d185e4609af521e2b744ddb98', NULL, '2017-11-25 19:48:58', '2017-11-25 19:48:58', '2017-11-25 19:48:58', '2017-11-25 19:55:58', '2017-11-25 19:48:58', NULL, '001', 0, NULL, 0, 1, 0.00);

-- ----------------------------
-- Table structure for tz_order_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tz_order_feedback`;
CREATE TABLE `tz_order_feedback`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '反馈信息编号',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单编号id',
  `mall_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '该订单在哪家购物平台产生 购物平台编号id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前登录用户id',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户反馈该订单情况的文字内容',
  `imag_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户反馈上传的图片请求地址，以json字符串形式保存',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '该反馈写入时间',
  `status` int(2) NOT NULL COMMENT '该条反馈信息的当前状态，默认是 1.未处理 0 已处理',
  `reply` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家回复文字信息',
  `reply_date` datetime(0) NULL DEFAULT NULL COMMENT '商家回复时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户订单反馈信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tz_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tz_order_item`;
CREATE TABLE `tz_order_item`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单编号id',
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `num` int(10) NOT NULL COMMENT '商品购买数量',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `item_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品标题名称',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品单价，单位为：分',
  `real_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '该商品的最终支付价格',
  `member_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品会员价格，单位为：分',
  `item_post` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '该订单商品的邮费',
  `total_fee` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '商品总金额(包含该商品邮费)',
  `homepage_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品图片地址',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`item_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单与商品的详细信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_order_item
-- ----------------------------
INSERT INTO `tz_order_item` VALUES ('', '', '', 0, NULL, NULL, 0.00, 0.00, 0.00, 0.00, 0.00, NULL, NULL, NULL, NULL);
INSERT INTO `tz_order_item` VALUES ('025ff666d4344732a985aa2f7b5e7ffd', '202f62ff3aab430f80f8cb0f5ac4175e', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-28 13:32:19', '2017-11-28 13:32:19');
INSERT INTO `tz_order_item` VALUES ('05a50686576e437ea0570881153cb532', '1dc85fce30444e5083a212201a83e28b', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 52.00, 87.00, 0.00, 52.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-11-27 20:53:00', '2017-11-27 20:53:00');
INSERT INTO `tz_order_item` VALUES ('0658cf340a1b432eb8725e202078f526', 'e327f62d599b47c68990d220131da448', 'c4cd1d8d16fa4bcab350ca756ec02684', 1, '“香飘万里酒，知音酒不同。”   520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]爱心520白酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxLWAdr8tAAxPujzrmOk351.png?baijiulihezhutu.png', NULL, '2017-12-08 16:48:13', '2017-12-08 16:48:13');
INSERT INTO `tz_order_item` VALUES ('0862f143de9c4daaa3fb7a3344be2e62', '2153e6d947914ff3bdd1e474902c210e', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '520红酒-在与心和灵魂的共鸣。520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]爱心520红酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', NULL, '2017-12-04 10:05:39', '2017-12-04 10:05:39');
INSERT INTO `tz_order_item` VALUES ('0e60461018e24572923d92d2de59d2ce', '43e865a1dcc346b8b498b66b11788fca', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-30 13:59:07', '2017-11-30 13:59:07');
INSERT INTO `tz_order_item` VALUES ('15e5948fd57646218e8d568a787c2ec6', 'a500d15d941f409eb92cc3118ef767a2', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '520红酒-在与心和灵魂的共鸣。520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]爱心520红酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', NULL, '2017-12-18 16:35:27', '2017-12-18 16:35:27');
INSERT INTO `tz_order_item` VALUES ('22918714aa4e467fa02f62020ab171d7', 'e6a0d1ba9979456a9e834c33f0614400', '81b2922a27094271a0b58240ad78b794', 11, '申猴干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】申猴干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒。', 259.00, 87.00, 87.00, 0.00, 957.00, 'group1/M00/00/0C/rBBH51oXj9aAbxzRAAxn_XvpBNs972.png?申猴红酒主图.png', NULL, '2017-11-25 19:44:58', '2017-11-25 19:44:58');
INSERT INTO `tz_order_item` VALUES ('2a9fa9560c2e438b9fecb460ff2a68e5', '678e361cdde2437abb1b222ab00d4711', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '520红酒-在与心和灵魂的共鸣。520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]爱心520红酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', NULL, '2017-12-19 15:56:18', '2017-12-19 15:56:18');
INSERT INTO `tz_order_item` VALUES ('2e3d2eea93a9474992501e1991b9f913', '310641a069c946e4afef40aab46c7e51', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 52.00, 87.00, 0.00, 52.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-12-29 11:55:44', '2017-12-29 11:55:44');
INSERT INTO `tz_order_item` VALUES ('30ace83a3553438ea6490869a7062494', 'af16a25f1b624d7a9255fa4e31f40279', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '520红酒-在与心和灵魂的共鸣。520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]爱心520红酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', NULL, '2018-01-30 14:09:05', '2018-01-30 14:09:05');
INSERT INTO `tz_order_item` VALUES ('36c0e7205e0d499b94276ade2f5dbd06', '0f46378a8b204e50a41f5d0d8bcf9696', 'be1836fe6a37498e853c0a52d0a26177', -1, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 259.00, 87.00, 0.00, -259.00, 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', NULL, '2017-12-06 23:27:12', '2017-12-06 23:27:12');
INSERT INTO `tz_order_item` VALUES ('371bf39200094a9db8f43906a5ce6564', '39391208f4904f498df934ea41dca2d8', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-12-01 22:40:56', '2017-12-01 22:40:56');
INSERT INTO `tz_order_item` VALUES ('41acd440bd4648ecb82841a65daf4b9c', '6ada52219ea84b2d97920adf643cf50d', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '520红酒-在与心和灵魂的共鸣。520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]爱心520红酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', NULL, '2018-01-04 10:48:26', '2018-01-04 10:48:26');
INSERT INTO `tz_order_item` VALUES ('42d1a8528e004dfca0e5d658d16ba8df', '5cc6a3fab149492ba40ea96dfa23575f', 'be1836fe6a37498e853c0a52d0a26177', 1, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 259.00, 87.00, 0.00, 259.00, 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', NULL, '2017-12-05 16:13:17', '2017-12-05 16:13:17');
INSERT INTO `tz_order_item` VALUES ('4400712e9a324bf0aea64c9fd1cbecb6', 'b4fb7dbb628c4fb8aa723407084f6e5c', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-29 10:09:47', '2017-11-29 10:09:47');
INSERT INTO `tz_order_item` VALUES ('44427cfeaa864c71977f685043d7dade', '4ec9d6add713407399a356241a2ab68d', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-29 17:09:04', '2017-11-29 17:09:04');
INSERT INTO `tz_order_item` VALUES ('48ffde9d5dd344059c303f230cbde0ab', '29679ddef20b40b5aff34c35a8ca8f8b', '67bb35e22d49424ca902710174033af8', 4, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 87.00, 87.00, 0.00, 348.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-12-18 16:05:08', '2017-12-18 16:05:08');
INSERT INTO `tz_order_item` VALUES ('4b9b4e15bd7e44629a0470ecc32cfaa5', '77c14b96f39345068c7a13973300b925', 'be1836fe6a37498e853c0a52d0a26177', 3, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 87.00, 87.00, 0.00, 261.00, 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', NULL, '2017-12-18 16:33:54', '2017-12-18 16:33:54');
INSERT INTO `tz_order_item` VALUES ('4d94656ee7a543f4bf618d316f51212f', 'a2c832a7ca43483f851735d03af4e0f5', 'de31a4bd7c524e4e8865226729b43725', 1, '真正意义上的安全、健康、修复型的医美产品。与传统的美容产品不同，她是真正以改善皮肤本质问题为核心，从皮肤底层进行彻底修复。', '【520面膜】520聚糖修复面膜舒缓敏感修护保湿补水 建立肌底屏障', 318.00, 318.00, 87.00, 0.00, 318.00, 'group1/M00/00/0D/rBBH51oXsySAU59IAAZ8NaZUrOE765.png?面膜主图+520.png', NULL, '2017-12-06 23:33:10', '2017-12-06 23:33:10');
INSERT INTO `tz_order_item` VALUES ('50341a2f78de48c3852b855bd02cef8a', '92d98e9f8101474aa4d8eae494c50707', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-12-01 20:28:27', '2017-12-01 20:28:27');
INSERT INTO `tz_order_item` VALUES ('5500ea3554a64a498fda0ee4f2696eb5', '78afcb2583b044f98510a0977c244a1f', 'be1836fe6a37498e853c0a52d0a26177', 1, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 259.00, 87.00, 0.00, 259.00, 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', NULL, '2017-12-06 23:25:51', '2017-12-06 23:25:51');
INSERT INTO `tz_order_item` VALUES ('60d2aacc3a3f42b1a23fbbb4c4eb6fa0', '92d98e9f8101474aa4d8eae494c50707', 'de31a4bd7c524e4e8865226729b43725', 1, '真正意义上的安全、健康、修复型的医美产品。与传统的美容产品不同，她是真正以改善皮肤本质问题为核心，从皮肤底层进行彻底修复。', '【520面膜】520聚糖修复面膜舒缓敏感修护保湿补水 建立肌底屏障', 318.00, 87.00, 87.00, 0.00, 87.00, 'group1/M00/00/0D/rBBH51oXsySAU59IAAZ8NaZUrOE765.png?面膜主图+520.png', NULL, '2017-12-01 20:28:27', '2017-12-01 20:28:27');
INSERT INTO `tz_order_item` VALUES ('6104621332ef497ebb91e7195ec76273', 'b13f0b2764574107836f3f9adcb58a42', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '520红酒-在与心和灵魂的共鸣。520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]爱心520红酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', NULL, '2017-12-08 16:37:59', '2017-12-08 16:37:59');
INSERT INTO `tz_order_item` VALUES ('61329637875d46febb05db33b8eec63a', '2fd0c92e93494dcd895cf38c55782e42', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-25 17:49:34', '2017-11-25 17:49:34');
INSERT INTO `tz_order_item` VALUES ('6361445ebf234522a6b7e66df9302c88', '1154336cf67b473da74e4783032c3446', '81b2922a27094271a0b58240ad78b794', 1, '申猴干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】申猴干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒。', 259.00, 87.00, 87.00, 0.00, 87.00, 'group1/M00/00/0C/rBBH51oXj9aAbxzRAAxn_XvpBNs972.png?申猴红酒主图.png', NULL, '2017-12-01 18:53:29', '2017-12-01 18:53:29');
INSERT INTO `tz_order_item` VALUES ('643339f061e5467188386696d1961aed', 'a03681ac9a634b838e8df25a2bd06b7c', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-27 15:32:28', '2017-11-27 15:32:28');
INSERT INTO `tz_order_item` VALUES ('6a2b185a48ca4ca0b1a5122aeeb07e73', '30c4ea3921f242b1a6c3845eb7d4733d', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2018-03-20 23:33:13', '2018-03-20 23:33:13');
INSERT INTO `tz_order_item` VALUES ('6ab9f082d3264ad5bdffbe51aa105e97', '5fcf23355a64476689bfe54e02f3c0b9', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 259.00, 87.00, 0.00, 259.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-12-01 20:38:40', '2017-12-01 20:38:40');
INSERT INTO `tz_order_item` VALUES ('6b6d1babf13e4ce4a5c575aa1d8a830a', '7d8afbb5302745e69ad83e8c51bf8fef', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-30 14:16:27', '2017-11-30 14:16:27');
INSERT INTO `tz_order_item` VALUES ('6fc60d5d9434497d89d165a53f2c34e6', 'daddb03a720b491d9fcf4136b1278524', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 52.00, 87.00, 0.00, 52.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-11-30 13:52:35', '2017-11-30 13:52:35');
INSERT INTO `tz_order_item` VALUES ('701a6e56ea1f4638a4dabf0fb55f78d0', '4f7e015bb5944f4fa87f30b5e4454365', 'be1836fe6a37498e853c0a52d0a26177', 1, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 87.00, 87.00, 0.00, 87.00, 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', NULL, '2017-11-30 19:04:33', '2017-11-30 19:04:33');
INSERT INTO `tz_order_item` VALUES ('74a42zw56u1o2mclwyta7jmuk2i16lzt', 'rohojm5e7icm7n2sf0wb6mkz3upzxlty', 'de31a4bd7c524e4e8865226729b43725', 10, '真正意义上的安全、健康、修复型的医美产品。与传统的美容产品不同，她是真正以改善皮肤本质问题为核心，从皮肤底层进行彻底修复。', '【520面膜】520聚糖修复面膜舒缓敏感修护保湿补水 建立肌底屏障', 318.00, 52.00, 87.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXsySAU59IAAZ8NaZUrOE765.png?面膜主图+520.png', NULL, '2017-11-27 17:13:11', NULL);
INSERT INTO `tz_order_item` VALUES ('74ee5c6229384376b124387de4539124', '773ffb9a64954d81a40571c0b538ca30', 'be1836fe6a37498e853c0a52d0a26177', 1, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 259.00, 87.00, 0.00, 259.00, 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', NULL, '2017-12-11 11:17:15', '2017-12-11 11:17:15');
INSERT INTO `tz_order_item` VALUES ('76c86ab381024e2a9209cd5841a183b4', 'a2b8f4b6c6584ab1b215ce93beb76607', '30a1d300698f45bcb43ab9c2cb9b9475', 3, '微脂囊（Liposomes）科技，延长活泉水作用时间，舒缓抗刺激，深层补水并锁住水分，使皮肤倍感柔滑。', '【520保湿乳液】520恒润保湿精华乳', 285.00, 87.00, 87.00, 0.00, 261.00, 'group1/M00/00/0D/rBBH51oXszeARrDaAAZ5PqdIDAA755.png?精华液主图.png', NULL, '2017-11-25 19:52:39', '2017-11-25 19:52:39');
INSERT INTO `tz_order_item` VALUES ('7c10de6904b84295acb4e87bc12830f1', '561b82410e6e4fd9975a78dd4f5617d2', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '520红酒-在与心和灵魂的共鸣。520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]爱心520红酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', NULL, '2018-01-04 12:03:49', '2018-01-04 12:03:49');
INSERT INTO `tz_order_item` VALUES ('7e1251d3fe474f85b20aafeefb8268bf', '77c14b96f39345068c7a13973300b925', '8dca2aefd538483bbc737e28a64ddedd', 4, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]45度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 87.00, 87.00, 0.00, 348.00, 'group1/M00/00/0D/rBBH51oXvTuAH7EaAAfsgJ3BM4g384.png?白酒主图.png', NULL, '2017-12-18 16:33:54', '2017-12-18 16:33:54');
INSERT INTO `tz_order_item` VALUES ('7f227bcf9d5541d18320cd7869141f12', '53f7d257f6ef4b02bedc4de5e2bddf09', 'c4cd1d8d16fa4bcab350ca756ec02684', 1, '“香飘万里酒，知音酒不同。”   520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]爱心520白酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxLWAdr8tAAxPujzrmOk351.png?baijiulihezhutu.png', NULL, '2017-11-25 18:08:42', '2017-11-25 18:08:42');
INSERT INTO `tz_order_item` VALUES ('7f53fdaaca944981b3a0bf378515a7f5', '08e1582ddb5b4cfbbfa243beb2ebfc9d', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-25 19:23:41', '2017-11-25 19:23:41');
INSERT INTO `tz_order_item` VALUES ('7fbce2cf99ca46358cd031f38e1eafa8', 'd1af1febb063412b87dde213d8ec75e1', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 52.00, 87.00, 0.00, 52.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-12-04 17:42:39', '2017-12-04 17:42:39');
INSERT INTO `tz_order_item` VALUES ('7rmpc3n2r0f9qvksczo9o0grk6rhh8r8', 'yzg1rrhb4gmvwqyelo3ryr6ary8iorc1', '4aa06c6db34a447ca56f90e450e5c03c', 1, '申猴520红酒尊享大礼包-在与心和灵魂的共鸣。申猴520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '\r\n[爱心520]申猴520红酒尊享大礼包\r\n', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4uqAaKKGAAyGRtyyclA102.png?shenghouhongjiuzunxiangzhutu.png', NULL, '2017-11-27 16:58:53', '2017-11-27 16:58:53');
INSERT INTO `tz_order_item` VALUES ('810286382ee2438f96d4288e99912791', '9788d82c80a44efbbc768e3bb503e109', 'be1836fe6a37498e853c0a52d0a26177', 1, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 259.00, 87.00, 0.00, 259.00, 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', NULL, '2017-12-05 16:11:45', '2017-12-05 16:11:45');
INSERT INTO `tz_order_item` VALUES ('84eb0a7100ef4d6094af72c3808c1c1a', '0f46378a8b204e50a41f5d0d8bcf9696', '81b2922a27094271a0b58240ad78b794', 2, '申猴干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】申猴干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒。', 259.00, 259.00, 87.00, 0.00, 518.00, 'group1/M00/00/0C/rBBH51oXj9aAbxzRAAxn_XvpBNs972.png?申猴红酒主图.png', NULL, '2017-12-06 23:27:12', '2017-12-06 23:27:12');
INSERT INTO `tz_order_item` VALUES ('88cfeb873fe54d66b581f23a1d3b267b', '2938368e9c0740158fea8db0663c295d', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 52.00, 87.00, 0.00, 52.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-12-01 20:16:48', '2017-12-01 20:16:48');
INSERT INTO `tz_order_item` VALUES ('8e76ca9925fe43afaa75332103128239', 'a6858f7223624964b3339e4f09360e6b', '30a1d300698f45bcb43ab9c2cb9b9475', 1, '微脂囊（Liposomes）科技，延长活泉水作用时间，舒缓抗刺激，深层补水并锁住水分，使皮肤倍感柔滑。', '【520保湿乳液】520恒润保湿精华乳', 285.00, 87.00, 87.00, 0.00, 87.00, 'group1/M00/00/0D/rBBH51oXszeARrDaAAZ5PqdIDAA755.png?精华液主图.png', NULL, '2017-12-08 16:17:43', '2017-12-08 16:17:43');
INSERT INTO `tz_order_item` VALUES ('97cfc3a8eba04ea28e8ec196fded1d57', '1a97c583ab414b5b8ee300aba85522b2', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-29 13:43:26', '2017-11-29 13:43:26');
INSERT INTO `tz_order_item` VALUES ('99693a269f8046ed8f16b5827e8c0ea8', '31c8d75127da4a7d9c794668311a4df6', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-25 19:53:40', '2017-11-25 19:53:40');
INSERT INTO `tz_order_item` VALUES ('a4112ff6e0184528b8da1c86f13e008e', 'e2285a09710043cb871277e6f219e813', '81b2922a27094271a0b58240ad78b794', 1, '申猴干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】申猴干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒。', 259.00, 259.00, 87.00, 0.00, 259.00, 'group1/M00/00/0C/rBBH51oXj9aAbxzRAAxn_XvpBNs972.png?申猴红酒主图.png', NULL, '2017-12-11 11:20:12', '2017-12-11 11:20:12');
INSERT INTO `tz_order_item` VALUES ('aa272e1b495a4f57b2042349b704b905', 'f18dfa1b9cb54059a2fe5d9ad6e654c6', 'de31a4bd7c524e4e8865226729b43725', 1, '真正意义上的安全、健康、修复型的医美产品。与传统的美容产品不同，她是真正以改善皮肤本质问题为核心，从皮肤底层进行彻底修复。', '【520面膜】520聚糖修复面膜舒缓敏感修护保湿补水 建立肌底屏障', 318.00, 87.00, 87.00, 0.00, 87.00, 'group1/M00/00/0D/rBBH51oXsySAU59IAAZ8NaZUrOE765.png?面膜主图+520.png', NULL, '2017-11-27 11:18:48', '2017-11-27 11:18:48');
INSERT INTO `tz_order_item` VALUES ('aa5ed5d55ffe4b138b9a99e5534c0b7d', 'e5287e6ef3f44be985f1d492f939ed75', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-25 19:16:39', '2017-11-25 19:16:39');
INSERT INTO `tz_order_item` VALUES ('ac7bb3aa2d284dc9b39a0aa922b8ddfd', '0c774eafb6104767a351ed4ec3b9bdcd', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-25 18:07:14', '2017-11-25 18:07:14');
INSERT INTO `tz_order_item` VALUES ('ad061d46848442b69d24ab66d70ed3f1', '3c950aec833049b2a5cf1732226ef342', '67bb35e22d49424ca902710174033af8', 5, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 52.00, 87.00, 0.00, 260.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-11-27 20:54:32', '2017-11-27 20:54:32');
INSERT INTO `tz_order_item` VALUES ('ad992889b2b945e084274f88a826d67e', '43f8cf20f3084b56936fa40a6d312172', '4aa06c6db34a447ca56f90e450e5c03c', 1, '申猴520红酒尊享大礼包-在与心和灵魂的共鸣。申猴520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]申猴520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4uqAaKKGAAyGRtyyclA102.png?shenghouhongjiuzunxiangzhutu.png', NULL, '2017-11-29 21:17:39', '2017-11-29 21:17:39');
INSERT INTO `tz_order_item` VALUES ('b0a4b5cd2aba4bbdbb82ef730fd5253e', '15e94ed40f7940f1969b9abd536e6e11', '8dca2aefd538483bbc737e28a64ddedd', 1, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]45度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 87.00, 87.00, 0.00, 87.00, 'group1/M00/00/0D/rBBH51oXvTuAH7EaAAfsgJ3BM4g384.png?白酒主图.png', NULL, '2017-12-01 14:18:37', '2017-12-01 14:18:37');
INSERT INTO `tz_order_item` VALUES ('b2dc0535f96845bd9a46c9148b226df0', 'a2c832a7ca43483f851735d03af4e0f5', '7aea5ec85c8a416f82435f1c6fb687bd', -3, '“香飘万里酒，知音酒不同。”  45度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]45度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, -1560.00, 'group1/M00/00/0E/rBBH51oY4TqAbF0KAAprKUuSq68194.png?45baijiuzenxianglihezhutu.png', NULL, '2017-12-06 23:33:10', '2017-12-06 23:33:10');
INSERT INTO `tz_order_item` VALUES ('b6f589006f9b4efe83e855009cffff05', '168092d13132424887c19db65e65b360', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 87.00, 87.00, 0.00, 87.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-11-27 11:20:58', '2017-11-27 11:20:58');
INSERT INTO `tz_order_item` VALUES ('bae557dcdf3646d6a0160bf0899aef55', 'df3ba1fc5bb54ef99280d45d0355d81f', 'de31a4bd7c524e4e8865226729b43725', 1, '真正意义上的安全、健康、修复型的医美产品。与传统的美容产品不同，她是真正以改善皮肤本质问题为核心，从皮肤底层进行彻底修复。', '【520面膜】520聚糖修复面膜舒缓敏感修护保湿补水 建立肌底屏障', 318.00, 318.00, 87.00, 0.00, 318.00, 'group1/M00/00/0D/rBBH51oXsySAU59IAAZ8NaZUrOE765.png?面膜主图+520.png', NULL, '2017-11-27 12:03:16', '2017-11-27 12:03:16');
INSERT INTO `tz_order_item` VALUES ('bea3d23b74384bfd8ba8fd2077acd6a5', '060960b73af74bac8e2624cfe0bf6c52', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 52.00, 87.00, 0.00, 52.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-11-27 20:53:16', '2017-11-27 20:53:16');
INSERT INTO `tz_order_item` VALUES ('c1e26a1232ca416caae1c5fc86ae17b3', '188ed236c05645989d9b56b512dd5453', '90b690312e5d4fb0b89f4d82a0a8faba', 1, '520红酒-在与心和灵魂的共鸣。520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]爱心520红酒超值大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0D/rBBH51oXxSeANAtPAAxE_xHmq7E024.png?hongjiulihezhutu.png', NULL, '2017-11-30 19:22:22', '2017-11-30 19:22:22');
INSERT INTO `tz_order_item` VALUES ('c2cc533c2e0848608c56dbce2a5f3e6f', 'b39ef014ff9c42a4938468b1a856f43c', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-30 13:54:56', '2017-11-30 13:54:56');
INSERT INTO `tz_order_item` VALUES ('c71f704a46a848f8900d4ca5974925d5', '74a65c8eb721473fa7c70232a7fcb84b', '4aa06c6db34a447ca56f90e450e5c03c', 1, '申猴520红酒尊享大礼包-在与心和灵魂的共鸣。申猴520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]申猴520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4uqAaKKGAAyGRtyyclA102.png?shenghouhongjiuzunxiangzhutu.png', NULL, '2017-12-06 23:32:14', '2017-12-06 23:32:14');
INSERT INTO `tz_order_item` VALUES ('c8999baf3c51444cb17fb7c05658d15e', '9a869ee38715457bba8656ab22ecec61', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-25 19:23:32', '2017-11-25 19:23:32');
INSERT INTO `tz_order_item` VALUES ('cdee4ca211b14be8a64199cc07b9a0eb', 'edb3726aeb31424783c1a5e0e7ce2703', '9e103d36ac024616a11095ee99dc8abe', 1, '富含苹果及酵母萃取精华，能及时渗透肌肤，滋养营养改善肌肤光泽.', '[爱心520苹果酵母组合套装]', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/14/rBBH51pXEWmALMSJAAezy2v-iws905.png?huahzuangptaocanzhutu.png', NULL, '2018-02-16 20:57:23', '2018-02-16 20:57:23');
INSERT INTO `tz_order_item` VALUES ('d239bf4a64b44535be468025fd2402d4', '31c8d75127da4a7d9c794668311a4df6', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 87.00, 87.00, 0.00, 87.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-11-25 19:53:40', '2017-11-25 19:53:40');
INSERT INTO `tz_order_item` VALUES ('d69a4a9c50a445a2afb5f5469e8e7224', '9e94111cdcbd4d1f81737c633d1d6692', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-30 12:32:45', '2017-11-30 12:32:45');
INSERT INTO `tz_order_item` VALUES ('dc8d725d340d480999cd33197ee450ef', 'a2c832a7ca43483f851735d03af4e0f5', 'be1836fe6a37498e853c0a52d0a26177', -1, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 259.00, 87.00, 0.00, -259.00, 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', NULL, '2017-12-06 23:33:10', '2017-12-06 23:33:10');
INSERT INTO `tz_order_item` VALUES ('e1fd7e2d310747e5b8f367295ef79134', 'a9c9aeb6a0c34a6c9a7cd64db910d74f', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-30 15:07:14', '2017-11-30 15:07:14');
INSERT INTO `tz_order_item` VALUES ('e3cc684a08d74c0bb0346e22c5a567b3', '39391208f4904f498df934ea41dca2d8', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 87.00, 87.00, 0.00, 87.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-12-01 22:40:56', '2017-12-01 22:40:56');
INSERT INTO `tz_order_item` VALUES ('e84117753b794c7691777eea1682bee3', 'a2c832a7ca43483f851735d03af4e0f5', '81b2922a27094271a0b58240ad78b794', 3, '申猴干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】申猴干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒。', 259.00, 259.00, 87.00, 0.00, 777.00, 'group1/M00/00/0C/rBBH51oXj9aAbxzRAAxn_XvpBNs972.png?申猴红酒主图.png', NULL, '2017-12-06 23:33:10', '2017-12-06 23:33:10');
INSERT INTO `tz_order_item` VALUES ('ed9b87d8ac1b479ab2fa0f3f0a8a1a9d', 'd5a7f5be29744f48b11b0e8cbe31a2d9', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 259.00, 87.00, 0.00, 259.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-11-27 11:18:17', '2017-11-27 11:18:17');
INSERT INTO `tz_order_item` VALUES ('eec956cf3b1347da9b50ddcfab5304bc', 'ba033efc2b3541b58337600acabcc57e', 'be1836fe6a37498e853c0a52d0a26177', 1, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]52度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 259.00, 87.00, 0.00, 259.00, 'group1/M00/00/0D/rBBH51oXvUiAFZhOAAfsgJ3BM4g881.png?白酒主图.png', NULL, '2017-12-05 16:14:30', '2017-12-05 16:14:30');
INSERT INTO `tz_order_item` VALUES ('f0b5d50f70e64452a5ca8b5b0a143e35', 'a2c832a7ca43483f851735d03af4e0f5', '8dca2aefd538483bbc737e28a64ddedd', 3, '520商城与杜甫酒业精诚合作，强势打造出这一款口感细腻，醇馥幽郁、醇厚的浓香型白酒。', '[520]45度520酒 500Ml瓶 浓香优雅，风味独特', 259.00, 259.00, 87.00, 0.00, 777.00, 'group1/M00/00/0D/rBBH51oXvTuAH7EaAAfsgJ3BM4g384.png?白酒主图.png', NULL, '2017-12-06 23:33:10', '2017-12-06 23:33:10');
INSERT INTO `tz_order_item` VALUES ('f152fa6e7bdd42eb9ae314508625018d', '11f2d2973b7b4dd1bc9056eabbdcd8f0', '81b2922a27094271a0b58240ad78b794', 1, '申猴干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】申猴干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒。', 259.00, 259.00, 87.00, 0.00, 259.00, 'group1/M00/00/0C/rBBH51oXj9aAbxzRAAxn_XvpBNs972.png?申猴红酒主图.png', NULL, '2017-12-07 17:08:58', '2017-12-07 17:08:58');
INSERT INTO `tz_order_item` VALUES ('f1ae99b94bf641d99166e9e2fdd7ced8', '9ec9b88ee5e64074915970405c924974', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-26 00:17:57', '2017-11-26 00:17:57');
INSERT INTO `tz_order_item` VALUES ('f2120290d3c3412ba6736453fd69508e', 'cd36f485aff14bda9868b809cd775d5a', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-25 19:20:42', '2017-11-25 19:20:42');
INSERT INTO `tz_order_item` VALUES ('f23f1cdbc990492fb29b57269457f96f', '5b434d790392450ea0fd60010f2d65cd', '4aa06c6db34a447ca56f90e450e5c03c', 1, '申猴520红酒尊享大礼包-在与心和灵魂的共鸣。申猴520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]申猴520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4uqAaKKGAAyGRtyyclA102.png?shenghouhongjiuzunxiangzhutu.png', NULL, '2017-11-27 11:20:28', '2017-11-27 11:20:28');
INSERT INTO `tz_order_item` VALUES ('f50cf51c0934446b8808e37484a20b07', '564f7d123aa94954b838c51d12b2c8e5', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-25 14:41:17', '2017-11-25 14:41:17');
INSERT INTO `tz_order_item` VALUES ('f9ba66ee62c84593a2c5192ddb2dd732', '5902c7c886964300977c102b071ff9c7', '20b19960498244eeadf56d4dc22f522e', 1, '戌狗520红酒尊享大礼包-在与心和灵魂的共鸣。戌狗520红酒呈明亮红宝石色，果香馥郁，入口丰满细腻，口感平衡，余味悠长持久，情侣之间的馈赠佳品。', '[爱心520]戌狗520红酒尊享大礼包', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0F/rBBH51oY48uAXb10AAs6maeTLBk780.png?xugouzunxianglihezhutu.png', NULL, '2017-11-30 10:59:57', '2017-11-30 10:59:57');
INSERT INTO `tz_order_item` VALUES ('fb1115939fa64dd5a1c81df8ebd28f32', '5d34b04a162049afae9c6576a95a41e6', '9e103d36ac024616a11095ee99dc8abe', 1, '富含苹果及酵母萃取精华，能及时渗透肌肤，滋养营养改善肌肤光泽.', '[爱心520苹果酵母组合套装]', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/14/rBBH51pXEWmALMSJAAezy2v-iws905.png?huahzuangptaocanzhutu.png', NULL, '2018-04-10 17:51:43', '2018-04-10 17:51:43');
INSERT INTO `tz_order_item` VALUES ('fbad6361e60e47e0beedf2cb9f416d4c', '2bbb1860bf994152b7edf946f34da038', '67bb35e22d49424ca902710174033af8', 4, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 52.00, 87.00, 0.00, 208.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-11-28 11:10:59', '2017-11-28 11:10:59');
INSERT INTO `tz_order_item` VALUES ('fbd01ae9664047fe93e72d63207942c5', 'ce27083f647a4a199238e7d1ca59aac3', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-25 19:18:53', '2017-11-25 19:18:53');
INSERT INTO `tz_order_item` VALUES ('fc0d8f4a489d4df8bc5d12d7e00d456d', '00791d8598554484a69a12595cfa372c', '67bb35e22d49424ca902710174033af8', 1, '戌狗干红酒：戌狗干红葡萄酒。法国原瓶原装进口，产自法国波尔多，AOP法定产区酒。明亮红宝石色，果香馥郁，入口丰满腻，口感平衡，余味悠长持久。', '【520红酒】戌狗干红葡萄酒，法国原瓶原装进口产自法国波尔多，AOP法定产区酒', 259.00, 52.00, 87.00, 0.00, 52.00, 'group1/M00/00/0C/rBBH51oXjxSAanKjAAxHm61Lu6c819.png?TIM图片20171124111352.png', NULL, '2017-12-01 20:17:04', '2017-12-01 20:17:04');
INSERT INTO `tz_order_item` VALUES ('v4t0tpvxnc5knmnuq3mnbsa06askf9oh', 'kig2yjthui7i7molujsspug2qpbdl5ko', '41146ba6a1ac41a6b424f9d0390ffbcd', 1, '“香飘万里酒，知音酒不同。”  52度6瓶520浓香型白酒带给您和家人不一样的感受，口感细腻，醇馥幽郁；不仅仅是送伴侣，送朋友的选择，更是送长辈的佳品。', '[爱心520]52度520白酒尊享大礼盒', 520.00, 520.00, 520.00, 0.00, 520.00, 'group1/M00/00/0E/rBBH51oY4emAJe1nAApuFyyBxoI802.png?52bajiuzunxiangzhutu.png', NULL, '2017-11-25 20:42:24', '2017-11-27 17:42:54');

-- ----------------------------
-- Table structure for tz_order_shipping
-- ----------------------------
DROP TABLE IF EXISTS `tz_order_shipping`;
CREATE TABLE `tz_order_shipping`  (
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号ID',
  `shipping_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物流运单编号',
  `com` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '快递公司简称',
  `receiver_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人全名(真实姓名)',
  `receiver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话(公司电话)',
  `receiver_mobile` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '移动电话（手机）',
  `receiver_state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货详细地址，如：xx路xx号',
  `receiver_zip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码,如：310001',
  `created_time` datetime(0) NULL DEFAULT NULL,
  `updated_time` datetime(0) NULL DEFAULT NULL,
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单配送物流的详细信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_order_shipping
-- ----------------------------
INSERT INTO `tz_order_shipping` VALUES ('00791d8598554484a69a12595cfa372c', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '高新区', '欣怡华庭东区', NULL, '2017-12-01 20:17:04', '2017-12-01 20:17:04', NULL);
INSERT INTO `tz_order_shipping` VALUES ('060960b73af74bac8e2624cfe0bf6c52', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '双流县', '新怡华庭东区', NULL, '2017-11-27 20:53:16', '2017-11-27 20:53:16', NULL);
INSERT INTO `tz_order_shipping` VALUES ('08e1582ddb5b4cfbbfa243beb2ebfc9d', '7913215086', 'db', '杨燕', '', '13450164309', '广东省', '肇庆市', '四会市', '新风路十三座14号', '', '2017-11-25 19:23:41', '2017-12-01 17:33:49', '96479df69b21468ca8a1e0ce754df5bb');
INSERT INTO `tz_order_shipping` VALUES ('0c774eafb6104767a351ed4ec3b9bdcd', NULL, NULL, '杨永安', NULL, '13953286637', '山东省', '青岛市', '城阳区', '银盛泰国贸大厦1005室', NULL, '2017-11-25 18:07:14', '2017-11-25 18:07:14', NULL);
INSERT INTO `tz_order_shipping` VALUES ('0f46378a8b204e50a41f5d0d8bcf9696', NULL, NULL, '时文奇', NULL, '18435974530', '河北省', '邯郸市', '永年县', '临洺关镇开发区开发路5号电子商务中心1716', NULL, '2017-12-06 23:27:12', '2017-12-06 23:27:12', NULL);
INSERT INTO `tz_order_shipping` VALUES ('1154336cf67b473da74e4783032c3446', NULL, NULL, '无语', NULL, '15101582583', '四川省', '成都市', '高新区', '测试', NULL, '2017-12-01 18:53:29', '2017-12-01 18:53:29', NULL);
INSERT INTO `tz_order_shipping` VALUES ('11f2d2973b7b4dd1bc9056eabbdcd8f0', NULL, NULL, '李龙', NULL, '13020759398', '陕西省', '咸阳市', '泾阳县', '云阳镇东街', NULL, '2017-12-07 17:08:58', '2017-12-07 17:08:58', NULL);
INSERT INTO `tz_order_shipping` VALUES ('15e94ed40f7940f1969b9abd536e6e11', NULL, NULL, '我', NULL, '15528260013', '四川省', '成都市', '高新区', '我', NULL, '2017-12-01 14:18:37', '2017-12-01 14:18:37', NULL);
INSERT INTO `tz_order_shipping` VALUES ('168092d13132424887c19db65e65b360', NULL, NULL, '小王', NULL, '13688390131', '四川省', '成都市', '天府新区', '天府新区', NULL, '2017-11-27 11:20:58', '2017-11-27 11:20:58', NULL);
INSERT INTO `tz_order_shipping` VALUES ('188ed236c05645989d9b56b512dd5453', NULL, NULL, '罗雄', NULL, '15528260013', '四川省', '成都市', '高新区', '123', NULL, '2017-11-30 19:22:22', '2017-11-30 19:22:22', NULL);
INSERT INTO `tz_order_shipping` VALUES ('1a97c583ab414b5b8ee300aba85522b2', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '双流县', '新怡华庭东区', NULL, '2017-11-29 13:43:26', '2017-11-29 13:43:26', NULL);
INSERT INTO `tz_order_shipping` VALUES ('1dc85fce30444e5083a212201a83e28b', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '双流县', '新怡华庭东区', NULL, '2017-11-27 20:53:00', '2017-11-27 20:53:00', NULL);
INSERT INTO `tz_order_shipping` VALUES ('202f62ff3aab430f80f8cb0f5ac4175e', NULL, NULL, '赵宇', NULL, '18011302051', '四川省', '成都市', '温江区', '五一路161号安居苑4-101', NULL, '2017-11-28 13:32:19', '2017-11-28 13:32:19', NULL);
INSERT INTO `tz_order_shipping` VALUES ('2153e6d947914ff3bdd1e474902c210e', '0', 'sf', '陈渊', '', '13666287722', '四川省', '成都市', '温江区', '塞纳河畔15栋', '', '2017-12-04 10:05:39', '2017-12-05 09:12:53', '96479df69b21468ca8a1e0ce754df5bb');
INSERT INTO `tz_order_shipping` VALUES ('2938368e9c0740158fea8db0663c295d', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '高新区', '欣怡华庭东区', NULL, '2017-12-01 20:16:48', '2017-12-01 20:16:48', NULL);
INSERT INTO `tz_order_shipping` VALUES ('29679ddef20b40b5aff34c35a8ca8f8b', NULL, NULL, '波波', NULL, '15756265835', '四川省', '成都市', '高新区', '楚峰国际中心B座2908', NULL, '2017-12-18 16:05:08', '2017-12-18 16:05:08', NULL);
INSERT INTO `tz_order_shipping` VALUES ('2bbb1860bf994152b7edf946f34da038', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '双流县', '新怡华庭东区', NULL, '2017-11-28 11:10:59', '2017-11-28 11:10:59', NULL);
INSERT INTO `tz_order_shipping` VALUES ('2fd0c92e93494dcd895cf38c55782e42', NULL, NULL, '杨永安', NULL, '13953286637', '山东省', '青岛市', '城阳区', '银盛泰国贸大厦1005室', NULL, '2017-11-25 17:49:34', '2017-11-25 17:49:34', NULL);
INSERT INTO `tz_order_shipping` VALUES ('30c4ea3921f242b1a6c3845eb7d4733d', NULL, NULL, '波波', NULL, '15756265835', '四川省', '成都市', '高新区', '楚峰国际中心B座2908', NULL, '2018-03-20 23:33:13', '2018-03-20 23:33:13', NULL);
INSERT INTO `tz_order_shipping` VALUES ('310641a069c946e4afef40aab46c7e51', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '高新区', '欣怡华庭东区', NULL, '2017-12-29 11:55:44', '2017-12-29 11:55:44', NULL);
INSERT INTO `tz_order_shipping` VALUES ('31c8d75127da4a7d9c794668311a4df6', NULL, NULL, '1次', NULL, '15708438605', '四川省', '成都市', '高新区', '睡觉咯', NULL, '2017-11-25 19:53:40', '2017-11-25 19:53:40', NULL);
INSERT INTO `tz_order_shipping` VALUES ('39391208f4904f498df934ea41dca2d8', NULL, NULL, '我', NULL, '15528260013', '四川省', '成都市', '高新区', '我', NULL, '2017-12-01 22:40:56', '2017-12-01 22:40:56', NULL);
INSERT INTO `tz_order_shipping` VALUES ('3c950aec833049b2a5cf1732226ef342', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '双流县', '新怡华庭东区', NULL, '2017-11-27 20:54:32', '2017-11-27 20:54:32', NULL);
INSERT INTO `tz_order_shipping` VALUES ('43e865a1dcc346b8b498b66b11788fca', NULL, NULL, '罗雄', NULL, '15528260013', '四川省', '成都市', '高新区', '123', NULL, '2017-11-30 13:59:07', '2017-11-30 13:59:07', NULL);
INSERT INTO `tz_order_shipping` VALUES ('43f8cf20f3084b56936fa40a6d312172', NULL, NULL, '陈渊', NULL, '13666287722', '四川省', '成都市', '温江区', '塞纳河畔15栋', NULL, '2017-11-29 21:17:39', '2017-11-29 21:17:39', NULL);
INSERT INTO `tz_order_shipping` VALUES ('4ec9d6add713407399a356241a2ab68d', NULL, NULL, '林', NULL, '15708455258', '四川省', '成都市', '天府新区', '华府大道黄金海岸', NULL, '2017-11-29 17:09:04', '2017-11-29 17:09:04', NULL);
INSERT INTO `tz_order_shipping` VALUES ('4f7e015bb5944f4fa87f30b5e4454365', NULL, NULL, '罗雄', NULL, '15528260013', '四川省', '成都市', '高新区', '123', NULL, '2017-11-30 19:04:33', '2017-11-30 19:04:33', NULL);
INSERT INTO `tz_order_shipping` VALUES ('53f7d257f6ef4b02bedc4de5e2bddf09', NULL, NULL, '杨永安', NULL, '13953286637', '山东省', '青岛市', '城阳区', '银盛泰国贸大厦1005室', NULL, '2017-11-25 18:08:42', '2017-11-25 18:08:42', NULL);
INSERT INTO `tz_order_shipping` VALUES ('561b82410e6e4fd9975a78dd4f5617d2', NULL, NULL, '王洁', NULL, '13283817653', '北京市', '北京市', '东城区', '菜市口大街', NULL, '2018-01-04 12:03:49', '2018-01-04 12:03:49', NULL);
INSERT INTO `tz_order_shipping` VALUES ('564f7d123aa94954b838c51d12b2c8e5', NULL, NULL, '曾明强', NULL, '13388184403', '四川省', '成都市', '温江区', '鱼凫路十号', NULL, '2017-11-25 14:41:17', '2017-11-25 14:41:17', NULL);
INSERT INTO `tz_order_shipping` VALUES ('5902c7c886964300977c102b071ff9c7', '1', 'tt', '程永清', '', '13801303986', '北京市', '北京市', '朝阳区', '八里庄北里112号楼1702', '', '2017-11-30 10:59:57', '2017-12-07 14:55:41', '96479df69b21468ca8a1e0ce754df5bb');
INSERT INTO `tz_order_shipping` VALUES ('5b434d790392450ea0fd60010f2d65cd', NULL, NULL, '小王', NULL, '13688390131', '四川省', '成都市', '天府新区', '天府新区', NULL, '2017-11-27 11:20:28', '2017-11-27 11:20:28', NULL);
INSERT INTO `tz_order_shipping` VALUES ('5cc6a3fab149492ba40ea96dfa23575f', NULL, NULL, '严庆春', NULL, '13350865811', '四川省', '成都市', '温江区', '天府街道办事处地铁首座一期九栋一单元1005号', NULL, '2017-12-05 16:13:17', '2017-12-05 16:13:17', NULL);
INSERT INTO `tz_order_shipping` VALUES ('5d34b04a162049afae9c6576a95a41e6', NULL, NULL, '成都市', NULL, '13540201486', '四川省', '成都市', '锦江区', '龙舟路', NULL, '2018-04-10 17:51:43', '2018-04-10 17:51:43', NULL);
INSERT INTO `tz_order_shipping` VALUES ('5fcf23355a64476689bfe54e02f3c0b9', NULL, NULL, '龚婷', NULL, '18888888888', '北京市', '北京市', '东城区', '我', NULL, '2017-12-01 20:38:40', '2017-12-01 20:38:40', NULL);
INSERT INTO `tz_order_shipping` VALUES ('678e361cdde2437abb1b222ab00d4711', NULL, NULL, '测试', NULL, '18512345678', '浙江省', '杭州市', '拱墅区', '测试', NULL, '2017-12-19 15:56:18', '2017-12-19 15:56:18', NULL);
INSERT INTO `tz_order_shipping` VALUES ('6ada52219ea84b2d97920adf643cf50d', NULL, NULL, '王洁', NULL, '13283817653', '北京市', '北京市', '东城区', '菜市口大街', NULL, '2018-01-04 10:48:26', '2018-01-04 10:48:26', NULL);
INSERT INTO `tz_order_shipping` VALUES ('74a65c8eb721473fa7c70232a7fcb84b', NULL, NULL, '时文奇', NULL, '18435974530', '河北省', '邯郸市', '永年县', '临洺关镇开发区开发路5号电子商务中心1716', NULL, '2017-12-06 23:32:14', '2017-12-06 23:32:14', NULL);
INSERT INTO `tz_order_shipping` VALUES ('773ffb9a64954d81a40571c0b538ca30', NULL, NULL, '大喵', NULL, '18784733131', '四川省', '南充', '高坪区', '物流园司机之家内', NULL, '2017-12-11 11:17:15', '2017-12-11 11:17:15', NULL);
INSERT INTO `tz_order_shipping` VALUES ('77c14b96f39345068c7a13973300b925', NULL, NULL, '波波', NULL, '15756265835', '四川省', '成都市', '高新区', '楚峰国际中心B座2908', NULL, '2017-12-18 16:33:54', '2017-12-18 16:33:54', NULL);
INSERT INTO `tz_order_shipping` VALUES ('78afcb2583b044f98510a0977c244a1f', NULL, NULL, '时文奇', NULL, '18435974530', '河北省', '邯郸市', '永年县', '临洺关镇开发区开发路5号电子商务中心1716', NULL, '2017-12-06 23:25:51', '2017-12-06 23:25:51', NULL);
INSERT INTO `tz_order_shipping` VALUES ('7d8afbb5302745e69ad83e8c51bf8fef', NULL, NULL, '罗雄', NULL, '15528260013', '四川省', '成都市', '高新区', '123', NULL, '2017-11-30 14:16:27', '2017-11-30 14:16:27', NULL);
INSERT INTO `tz_order_shipping` VALUES ('92d98e9f8101474aa4d8eae494c50707', NULL, NULL, '我', NULL, '15528260013', '四川省', '成都市', '高新区', '我', NULL, '2017-12-01 20:28:27', '2017-12-01 20:28:27', NULL);
INSERT INTO `tz_order_shipping` VALUES ('9788d82c80a44efbbc768e3bb503e109', NULL, NULL, '严庆春', NULL, '13350865811', '四川省', '成都市', '温江区', '天府街道办事处地铁首座一期九栋一单元1005号', NULL, '2017-12-05 16:11:45', '2017-12-05 16:11:45', NULL);
INSERT INTO `tz_order_shipping` VALUES ('9a869ee38715457bba8656ab22ecec61', NULL, NULL, '曾明强', NULL, '13388184403', '四川省', '成都市', '温江区', '鱼凫路十号', NULL, '2017-11-25 19:23:32', '2017-11-25 19:23:32', NULL);
INSERT INTO `tz_order_shipping` VALUES ('9e94111cdcbd4d1f81737c633d1d6692', '7913215085', 'db', '姜颖妮', '', '18683789591', '四川省', '成都市', '高新区', '高新区中和大道香榭国际', '', '2017-11-30 12:32:45', '2017-12-01 17:27:52', '96479df69b21468ca8a1e0ce754df5bb');
INSERT INTO `tz_order_shipping` VALUES ('9ec9b88ee5e64074915970405c924974', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '双流县', '新怡华庭东区', NULL, '2017-11-26 00:17:57', '2017-11-26 00:17:57', NULL);
INSERT INTO `tz_order_shipping` VALUES ('a03681ac9a634b838e8df25a2bd06b7c', '7913215093', 'db', '刘超', '', '15196612685', '四川省', '成都市', '高新区', '华府大道蜀郡又一城117-1-2404', '', '2017-11-27 15:32:28', '2017-12-01 17:32:00', '96479df69b21468ca8a1e0ce754df5bb');
INSERT INTO `tz_order_shipping` VALUES ('a2b8f4b6c6584ab1b215ce93beb76607', NULL, NULL, '1次', NULL, '15708438605', '四川省', '成都市', '高新区', '睡觉咯', NULL, '2017-11-25 19:52:39', '2017-11-25 19:52:39', NULL);
INSERT INTO `tz_order_shipping` VALUES ('a2c832a7ca43483f851735d03af4e0f5', NULL, NULL, '时文奇', NULL, '18435974530', '河北省', '邯郸市', '永年县', '临洺关镇开发区开发路5号电子商务中心1716', NULL, '2017-12-06 23:33:10', '2017-12-06 23:33:10', NULL);
INSERT INTO `tz_order_shipping` VALUES ('a500d15d941f409eb92cc3118ef767a2', NULL, NULL, '波波', NULL, '15756265835', '四川省', '成都市', '高新区', '楚峰国际中心B座2908', NULL, '2017-12-18 16:35:27', '2017-12-18 16:35:27', NULL);
INSERT INTO `tz_order_shipping` VALUES ('a6858f7223624964b3339e4f09360e6b', NULL, NULL, '龚婷', NULL, '13688390131', '北京市', '北京市', '东城区', '我', NULL, '2017-12-08 16:17:43', '2017-12-08 16:17:43', NULL);
INSERT INTO `tz_order_shipping` VALUES ('a9c9aeb6a0c34a6c9a7cd64db910d74f', '0', 'sto', '余丹', '', '13126888885', '北京市', '北京市', '房山区', '良乡文水家园2号楼4单元1102', '', '2017-11-30 15:07:14', '2017-12-07 14:55:21', '96479df69b21468ca8a1e0ce754df5bb');
INSERT INTO `tz_order_shipping` VALUES ('af16a25f1b624d7a9255fa4e31f40279', NULL, NULL, '波波', NULL, '15756265835', '四川省', '成都市', '高新区', '楚峰国际中心B座2908', NULL, '2018-01-30 14:09:05', '2018-01-30 14:09:05', NULL);
INSERT INTO `tz_order_shipping` VALUES ('b13f0b2764574107836f3f9adcb58a42', NULL, NULL, '龚婷', NULL, '13688390131', '北京市', '北京市', '东城区', '我', NULL, '2017-12-08 16:37:59', '2017-12-08 16:37:59', NULL);
INSERT INTO `tz_order_shipping` VALUES ('b39ef014ff9c42a4938468b1a856f43c', NULL, NULL, '罗雄', NULL, '15528260013', '四川省', '成都市', '高新区', '123', NULL, '2017-11-30 13:54:56', '2017-11-30 13:54:56', NULL);
INSERT INTO `tz_order_shipping` VALUES ('b4fb7dbb628c4fb8aa723407084f6e5c', '7913215087', 'db', '黄磊', '', '15928482028', '四川省', '成都市', '金牛区', '红花南路29号', '', '2017-11-29 10:09:47', '2017-12-01 17:29:07', '96479df69b21468ca8a1e0ce754df5bb');
INSERT INTO `tz_order_shipping` VALUES ('ba033efc2b3541b58337600acabcc57e', NULL, NULL, '严庆春', NULL, '13350865811', '四川省', '成都市', '温江区', '天府街道办事处地铁首座一期九栋一单元1005号', NULL, '2017-12-05 16:14:30', '2017-12-05 16:14:30', NULL);
INSERT INTO `tz_order_shipping` VALUES ('cd36f485aff14bda9868b809cd775d5a', NULL, NULL, '徐开全', NULL, '13550669886', '四川省', '德阳市', '什邡市', '方亭镇', NULL, '2017-11-25 19:20:42', '2017-11-25 19:20:42', NULL);
INSERT INTO `tz_order_shipping` VALUES ('ce27083f647a4a199238e7d1ca59aac3', '7913215084', 'db', '杨永安', '', '13953286637', '山东省', '青岛市', '城阳区', '银盛泰国贸大厦1005室', '', '2017-11-25 19:18:53', '2017-12-01 17:34:24', '96479df69b21468ca8a1e0ce754df5bb');
INSERT INTO `tz_order_shipping` VALUES ('d1af1febb063412b87dde213d8ec75e1', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '高新区', '欣怡华庭东区', NULL, '2017-12-04 17:42:39', '2017-12-04 17:42:39', NULL);
INSERT INTO `tz_order_shipping` VALUES ('d5a7f5be29744f48b11b0e8cbe31a2d9', NULL, NULL, '小王', NULL, '13688390131', '四川省', '成都市', '高新区', '楚峰国际', NULL, '2017-11-27 11:18:17', '2017-11-27 11:18:17', NULL);
INSERT INTO `tz_order_shipping` VALUES ('daddb03a720b491d9fcf4136b1278524', NULL, NULL, '蒙林', NULL, '18113018416', '四川省', '成都市', '双流县', '新怡华庭东区', NULL, '2017-11-30 13:52:35', '2017-11-30 13:52:35', NULL);
INSERT INTO `tz_order_shipping` VALUES ('df3ba1fc5bb54ef99280d45d0355d81f', NULL, NULL, '刘霞', NULL, '13648083082', '四川省', '成都市', '青羊区', '大福建营巷19号', NULL, '2017-11-27 12:03:16', '2017-11-27 12:03:16', NULL);
INSERT INTO `tz_order_shipping` VALUES ('e2285a09710043cb871277e6f219e813', NULL, NULL, '大喵', NULL, '18784733131', '四川省', '南充', '高坪区', '物流园司机之家内', NULL, '2017-12-11 11:20:12', '2017-12-11 11:20:12', NULL);
INSERT INTO `tz_order_shipping` VALUES ('e327f62d599b47c68990d220131da448', NULL, NULL, '龚婷', NULL, '18888888888', '北京市', '北京市', '东城区', '我', NULL, '2017-12-08 16:48:13', '2017-12-08 16:48:13', NULL);
INSERT INTO `tz_order_shipping` VALUES ('e5287e6ef3f44be985f1d492f939ed75', NULL, NULL, '何会芳', NULL, '18980689935', '四川省', '成都市', '金牛区', '蜀汉路359号9-1-16', NULL, '2017-11-25 19:16:39', '2017-11-25 19:16:39', NULL);
INSERT INTO `tz_order_shipping` VALUES ('e6a0d1ba9979456a9e834c33f0614400', NULL, NULL, '1次', NULL, '15708438605', '四川省', '成都市', '高新区', '睡觉咯', NULL, '2017-11-25 19:44:58', '2017-11-25 19:44:58', NULL);
INSERT INTO `tz_order_shipping` VALUES ('edb3726aeb31424783c1a5e0e7ce2703', NULL, NULL, '哈哈哈', NULL, '18161336290', '北京市', '北京市', '东城区', '你的就是你', NULL, '2018-02-16 20:57:23', '2018-02-16 20:57:23', NULL);
INSERT INTO `tz_order_shipping` VALUES ('f18dfa1b9cb54059a2fe5d9ad6e654c6', NULL, NULL, '小王', NULL, '13688390131', '四川省', '成都市', '天府新区', '天府新区', NULL, '2017-11-27 11:18:48', '2017-11-27 11:18:48', NULL);
INSERT INTO `tz_order_shipping` VALUES ('kig2yjthui7i7molujsspug2qpbdl5ko', NULL, NULL, '徐总', NULL, '13550669886', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tz_order_shipping` VALUES ('rohojm5e7icm7n2sf0wb6mkz3upzxlty', '00', 'db', '王凤霞', '', '13808062866', '四川省', '成都市', '青羊区', '青羊大道99号1栋2单元', '1002号', '2017-11-27 17:14:59', '2017-12-05 09:11:23', '96479df69b21468ca8a1e0ce754df5bb');
INSERT INTO `tz_order_shipping` VALUES ('yzg1rrhb4gmvwqyelo3ryr6ary8iorc1', NULL, NULL, '吴小芳', NULL, '15957152212', '', NULL, NULL, NULL, NULL, '2017-11-25 19:52:39', '2017-11-25 19:52:39', NULL);

-- ----------------------------
-- Table structure for tz_receiverinfo
-- ----------------------------
DROP TABLE IF EXISTS `tz_receiverinfo`;
CREATE TABLE `tz_receiverinfo`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前登录用户id',
  `receiver_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人全名(真实姓名)',
  `receiver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话(公司电话)',
  `receiver_mobile` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '移动电话（手机）',
  `receiver_state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货详细地址，如：xx路xx号',
  `is_default` int(2) NOT NULL DEFAULT 0 COMMENT '设置地址默认为1 不默认为null',
  `receiver_zip` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码,如：310001',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户绑定多个收货地址信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_receiverinfo
-- ----------------------------
INSERT INTO `tz_receiverinfo` VALUES ('023774c81d9f4701962ab55fcef897ce', '0ce2930756144b41b623497f376e8a34', '顾浩', '', '13709061799', '四川省', '成都市', '高新区', '蜀锦路88号楚峰国际2907号', 1, '', '2017-11-25 14:18:06', '2017-11-25 14:18:06');
INSERT INTO `tz_receiverinfo` VALUES ('02a9f8457b364bbdbf8a28e09dd36435', 'cd8d6ebda4b74123b2b7b4a32b8b66d9', '黄磊', '', '15928482028', '四川省', '成都市', '金牛区', '红花南路29号', 1, '', '2017-11-29 10:09:39', '2017-11-29 10:09:39');
INSERT INTO `tz_receiverinfo` VALUES ('03a4f3fe216e4b2b827da39e02f8aba1', '0ef7fdf322934c55b0a804d561228328', '赵宇', '', '18011302051', '四川省', '成都市', '温江区', '五一路161号安居苑4-101', 1, '', '2017-11-28 13:32:14', '2017-11-28 13:32:14');
INSERT INTO `tz_receiverinfo` VALUES ('19023fad2659440f9e0ac8b52f1f9193', '12d4ab2da7394354996db664828c7242', '无语', '', '15101582583', '四川省', '成都市', '高新区', '测试', 1, '', '2017-11-28 10:01:46', '2017-11-28 10:01:46');
INSERT INTO `tz_receiverinfo` VALUES ('246ef309ba974d22ade1db7bf988e17f', 'b852902504194934a2be63c2f394d335', '李龙', NULL, '13020759398', '陕西省', '咸阳市', '泾阳县', '云阳镇东街', 1, NULL, '2017-12-07 17:08:34', '2017-12-07 17:08:34');
INSERT INTO `tz_receiverinfo` VALUES ('2ab02ee8955d4cae808d59758c1d2652', '3640cddab5394d01a3bc839599bcceb0', '波波', '', '15756265835', '四川省', '成都市', '高新区', '楚峰国际中心B座2908', 1, '', '2017-12-08 17:15:26', '2017-12-08 17:15:26');
INSERT INTO `tz_receiverinfo` VALUES ('2e059b28640a4664990190a726203aad', '7bee0bfe55ea439693c01c7271a83d0e', '陈渊', '', '13666287722', '四川省', '成都市', '温江区', '塞纳河畔15栋', 1, '', '2017-11-29 21:17:33', '2017-11-29 21:17:33');
INSERT INTO `tz_receiverinfo` VALUES ('364cac4156744b9a8dcaffd35efe5f89', 'fdc70f24eb954d77b2a5f5f5559bcfed', '1次', '', '15708438605', '四川省', '成都市', '高新区', '睡觉咯', 1, '', '2017-11-25 19:44:54', '2017-11-25 19:44:54');
INSERT INTO `tz_receiverinfo` VALUES ('3e6b6b0aa16840cc94363510632c453e', '2b6467eeb24a485784ef32adcc8a9cd2', '林', '', '15708455258', '四川省', '成都市', '天府新区', '华府大道黄金海岸', 1, '', '2017-11-29 17:09:01', '2017-11-29 17:09:01');
INSERT INTO `tz_receiverinfo` VALUES ('40760063f5004afc860e9d31e80d120e', '3877878856b74d2c8e276945a392a44b', '王洁', NULL, '13283817653', '北京市', '北京市', '东城区', '菜市口大街', 1, NULL, '2018-01-04 10:48:08', '2018-01-04 10:48:08');
INSERT INTO `tz_receiverinfo` VALUES ('4ffc332d1dc540b090daaa95a0b3ff53', '88b6ecc45dcf44a2a2d1d8072e1d1832', '曾明强', '', '13388184403', '四川省', '成都市', '温江区', '鱼凫路十号', 1, '', '2017-11-25 19:23:20', '2017-11-25 19:23:20');
INSERT INTO `tz_receiverinfo` VALUES ('5209f803163446ef905e4cd51ea8c5c8', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', '我', '', '15528260013', '四川省', '成都市', '高新区', '我', 1, '', '2017-12-08 17:39:24', '2017-12-08 17:39:24');
INSERT INTO `tz_receiverinfo` VALUES ('538ead61da9243da8aa6bf76980e1f8b', 'f49dd0101441464a8a9b3dbbe224da75', '哈哈哈', NULL, '18161336290', '北京市', '北京市', '东城区', '你的就是你', 1, NULL, '2018-02-16 20:57:11', '2018-02-16 20:57:11');
INSERT INTO `tz_receiverinfo` VALUES ('5a70a87f02cd40f28c70003ed7b20361', 'a521d908027d4ff8b4d3b93ec588b931', '余丹', '', '13126888885', '北京市', '北京市', '房山区', '良乡文水家园2号楼4单元1102', 1, '', '2017-11-30 15:06:27', '2017-11-30 15:06:27');
INSERT INTO `tz_receiverinfo` VALUES ('5e6acccfee75487fadebc9a9092d2cfc', 'e040813c1f8149b7a7a56a4ee5f2a0de', '杨燕', '', '13450164309', '广东省', '肇庆市', '四会市', '新风路十三座14号', 1, '', '2017-11-25 19:23:35', '2017-11-25 19:23:35');
INSERT INTO `tz_receiverinfo` VALUES ('808e9eeb6621403b86aa80d34b42167b', 'c33c373d414741cab5ab80f4ffff2b0a', '时文奇', NULL, '18435974530', '河北省', '邯郸市', '永年县', '临洺关镇开发区开发路5号电子商务中心1716', 1, NULL, '2017-12-06 23:23:12', '2017-12-06 23:23:12');
INSERT INTO `tz_receiverinfo` VALUES ('82d17d5243e645b4a20b15fabec3666a', '24f8c8dfa0fc4acb8d0a8bba7e1773d6', '何会芳', '', '18980689935', '四川省', '成都市', '金牛区', '蜀汉路359号9-1-16', 1, '', '2017-11-25 19:16:19', '2017-11-25 19:16:19');
INSERT INTO `tz_receiverinfo` VALUES ('8dcfe67c08e049beafc3582e23a59ef7', '07e7ba64169a43b9b9889db66d9b4c1f', '刘霞', '', '13648083082', '四川省', '成都市', '青羊区', '大福建营巷19号', 1, '', '2017-11-27 12:02:55', '2017-11-27 12:02:55');
INSERT INTO `tz_receiverinfo` VALUES ('902d591f97ca41458d3b12b7e51b9a35', '2029c8cfc0da4748ba83172d1e850dbc', '罗雄', '', '15528260013', '四川省', '成都市', '高新区', '123', 1, '', '2017-11-30 13:54:54', '2017-11-30 13:54:54');
INSERT INTO `tz_receiverinfo` VALUES ('9159253d5d004e9a8c816e5bafee5de6', 'ffdaac2511c8411fabd64c7acbf1534d', '测试', NULL, '18512345678', '浙江省', '杭州市', '拱墅区', '测试', 1, NULL, '2017-12-19 15:56:14', '2017-12-19 15:56:14');
INSERT INTO `tz_receiverinfo` VALUES ('a526cfc109544e9bbff83ae8a90d52b4', '585dcde9e7144f22ba07e22a81abc288', '成都市', '', '13540201486', '四川省', '成都市', '锦江区', '龙舟路', 1, '', '2018-04-10 17:51:39', '2018-04-10 17:51:39');
INSERT INTO `tz_receiverinfo` VALUES ('aa020742ebe342dd8cb76a37b3ee1631', '74f0add8c7ad471980617bbf4709a23d', '杨永安', '', '13953286637', '山东省', '青岛市', '城阳区', '银盛泰国贸大厦1005室', 1, '', '2017-11-25 17:47:06', '2017-11-25 17:47:06');
INSERT INTO `tz_receiverinfo` VALUES ('ac7ed36c35a2411b83ac601ede6142fa', '288d7a49141341ffac8be7ab1d799cc0', '姜颖妮', '', '18683789591', '四川省', '成都市', '高新区', '高新区中和大道香榭国际', 1, '', '2017-11-30 12:32:42', '2017-11-30 12:32:42');
INSERT INTO `tz_receiverinfo` VALUES ('ad9d18aafff04bbd9f75ec912b34e5f0', 'b2572ce53c23461ea802900a4813fd39', '蒙林', '', '18113018416', '四川省', '成都市', '高新区', '欣怡华庭东区', 1, '', '2017-11-30 17:50:42', '2017-11-30 17:50:42');
INSERT INTO `tz_receiverinfo` VALUES ('ae98d5ce8a434be087b071ff8c3e585c', '1a048c6799024e54aa29a962a545020b', '小王', '', '13688390131', '四川省', '成都市', '高新区', '楚峰国际', 0, '', '2017-12-01 20:36:25', '2017-12-01 20:36:25');
INSERT INTO `tz_receiverinfo` VALUES ('b9c1e4be8d864294a902f0265f9314f7', 'bc51ff97b0e64e028548eff8fdfad989', '程永清', '', '13801303986', '北京市', '北京市', '朝阳区', '八里庄北里112号楼1702', 1, '', '2017-11-30 10:59:53', '2017-11-30 10:59:53');
INSERT INTO `tz_receiverinfo` VALUES ('d899ea1b18f146f9ada562898fa25049', '1a048c6799024e54aa29a962a545020b', '龚婷', NULL, '18888888888', '北京市', '北京市', '东城区', '我', 1, NULL, '2017-12-08 16:43:34', '2017-12-08 16:43:34');
INSERT INTO `tz_receiverinfo` VALUES ('ebe3cc0073c44662bf9a04d7647ed68c', '7bab8480e4414c1cb322b4383bf55285', '严庆春', NULL, '13350865811', '四川省', '成都市', '温江区', '天府街道办事处地铁首座一期九栋一单元1005号', 1, NULL, '2017-12-05 16:11:33', '2017-12-05 16:11:33');
INSERT INTO `tz_receiverinfo` VALUES ('eeaed0bac2b546059245ee095608caa2', 'a368b9051f2647bfa2964f3e385bbcdd', '徐开全', '', '13550669886', '四川省', '德阳市', '什邡市', '方亭镇', 1, '', '2017-11-25 19:20:37', '2017-11-25 19:20:37');
INSERT INTO `tz_receiverinfo` VALUES ('fb8ca9dc758e4c40a7c2a91509cea95e', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', '龚婷', NULL, '13688390131', '北京市', '北京市', '东城区', '我', 0, NULL, '2017-12-08 16:26:28', '2017-12-08 16:26:28');
INSERT INTO `tz_receiverinfo` VALUES ('ff3f5b604eb14d0b87f69369195d3026', '062fa5c384ac4439bbeff8150e34246b', '刘超', '', '15196612685', '四川省', '成都市', '高新区', '华府大道蜀郡又一城117-1-2404', 1, '', '2017-11-27 15:32:23', '2017-11-27 15:32:23');

-- ----------------------------
-- Table structure for tz_recommend
-- ----------------------------
DROP TABLE IF EXISTS `tz_recommend`;
CREATE TABLE `tz_recommend`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被推荐用户id 如用户B->C',
  `referee_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推荐人id 如用户A->B->C',
  `grade` int(2) NOT NULL DEFAULT 1 COMMENT '推荐人与被推荐人之间关系 1.一级推荐 2.二级推荐',
  `real_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际推荐人id ,如用户B->C',
  `isplay` int(2) NOT NULL DEFAULT 0 COMMENT '被推荐者是否开通会员（注册时默认未开通会员，查询该用户是否与某购物平台id绑定)0 ，1',
  `feedback_status` int(2) NOT NULL DEFAULT 0 COMMENT '商家平台是否反馈 1.返账成功 0.没有返账',
  `feedback_fee` int(11) NULL DEFAULT NULL COMMENT '根据等级不同返账不同金额',
  `feedback_time` datetime(0) NULL DEFAULT NULL COMMENT '返账处理时间',
  `mall_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被推荐人注册会员的商家平台id',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '推荐记录创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '推荐记录的更新时间',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该记录操作人id',
  `top_two` int(11) NULL DEFAULT 0 COMMENT '查询前两位的推荐（付款成为会员的）',
  `city_love_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市爱心合伙人的id(便于直接找到这个城市爱心合伙人下所有的用户和用户的消费记录---单品有5%的抽成，如果礼包只是关联下一级)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户推荐关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_recommend
-- ----------------------------
INSERT INTO `tz_recommend` VALUES ('001', '25f7a23d920f48cf89b3eae0e69d8df3', 'e040813c1f8149b7a7a56a4ee5f2a0de', 1, 'e040813c1f8149b7a7a56a4ee5f2a0de', 1, 1, 100, '2017-11-25 20:20:23', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 20:20:34', '2017-11-25 20:20:36', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('08ac317f7de645abab0bea8b30c69fd1', '6e12cc0c8d6e482f8395e224a982e853', '2029c8cfc0da4748ba83172d1e850dbc', 1, '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-27 12:11:43', '2017-11-27 12:11:43', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('0cb11eb66f90490cbbbd7a8a9e28723e', 'b9b1eb84e5624caeb71f621b445f023f', '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-22 20:15:31', '2017-12-22 20:15:31', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('0deb77f4256a45ec91e927f1ba43be97', '4e70c024ff4a48d4a3df99de9e93c029', '92183126447f446ca783f3f19e144198', 1, '92183126447f446ca783f3f19e144198', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-30 18:45:38', '2017-11-30 18:45:38', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('153b4c88d2b04c09be3fce620167a084', '618e1fa97918493b9c61d029cef10fe2', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-12 11:56:23', '2017-12-12 11:56:23', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('1b7f6e105b7d46d9aa6e42b9e10688cb', 'a368b9051f2647bfa2964f3e385bbcdd', 'b2572ce53c23461ea802900a4813fd39', 1, 'b2572ce53c23461ea802900a4813fd39', 1, 1, 100, '2017-11-25 20:36:36', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 19:17:26', '2017-11-25 19:17:26', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('1e51e18a0074449e9a97bcc8e1fdc6b7', '3640cddab5394d01a3bc839599bcceb0', 'b2572ce53c23461ea802900a4813fd39', 1, 'b2572ce53c23461ea802900a4813fd39', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 14:25:13', '2017-11-25 14:25:13', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('1fbaa53ce1094092b2c4a7e0ee999310', '7bee0bfe55ea439693c01c7271a83d0e', '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, 1, 100, '2017-12-04 10:05:51', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 21:15:17', '2017-11-29 21:15:17', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('221e3862d93a4eeaa996128a704eda75', '1a048c6799024e54aa29a962a545020b', '2029c8cfc0da4748ba83172d1e850dbc', 1, '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-27 09:18:03', '2017-11-27 09:18:03', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('22a1477fabb742ee9af0f4a3e3874e78', 'e13e407d48f54bb38de52273eedbb28e', '49cab02d80a0412c88f7b437c19aa62d', 1, '49cab02d80a0412c88f7b437c19aa62d', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2018-01-09 12:38:00', '2018-01-09 12:38:00', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('30042631f8b04104b73754623909ef00', '7e2d0a001b9d402187f8de8061d5caae', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-07 17:15:51', '2017-12-07 17:15:51', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('3434a3f150874682aa731bb70eb0e5de', 'b17ffac68a0c43ccbf87437914d37ac5', '49cab02d80a0412c88f7b437c19aa62d', 1, '49cab02d80a0412c88f7b437c19aa62d', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2018-04-12 14:50:39', '2018-04-12 14:50:39', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('390e5372d44946d3a81f4054a96d2106', 'a99fc3742f224baa88027db7e4d50e27', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 17:00:51', '2017-11-29 17:00:51', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('4067981f1b7043e6a9a9c0f3d6568283', '288d7a49141341ffac8be7ab1d799cc0', '9b923977107645eca7703e3c3d26d455', 1, '9b923977107645eca7703e3c3d26d455', 1, 1, 100, '2017-11-30 12:33:40', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-30 11:05:41', '2017-11-30 11:05:41', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('413b2cd66cc24ad49c6d76c7745630c0', 'a0ad284f4e474378bc88a5b634be6927', '92183126447f446ca783f3f19e144198', 1, '92183126447f446ca783f3f19e144198', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-25 16:48:19', '2017-12-25 16:48:19', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('5035a2dba3fc4a249d37e2aa530488d9', '2029c8cfc0da4748ba83172d1e850dbc', 'b2572ce53c23461ea802900a4813fd39', 1, 'b2572ce53c23461ea802900a4813fd39', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 14:25:20', '2017-11-25 14:25:20', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('51f3d966998042a09e2cc64d31ccfe18', 'e928b6e92c474e3d8a101f421fd39387', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2018-01-22 16:50:55', '2018-01-22 16:50:55', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('59b8af146ac44a4e954ba689bd674eb3', '7bab8480e4414c1cb322b4383bf55285', '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-28 19:26:02', '2017-11-28 19:26:02', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('6c9728c407354251b373a6513f66e8bd', 'cea6956605ee47d49852515a9854feff', '92183126447f446ca783f3f19e144198', 1, '92183126447f446ca783f3f19e144198', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-30 14:36:34', '2017-11-30 14:36:34', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('6d6a69da9776484b868675157cb56820', '25849a752e5c402786bd5fdf0e32db8a', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-13 12:52:05', '2017-12-13 12:52:05', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('7231809ef5dd4820a9ca4b57cb19bb68', '3ae34de0ea984028a9a8cc72ba649a7d', '24f8c8dfa0fc4acb8d0a8bba7e1773d6', 1, '24f8c8dfa0fc4acb8d0a8bba7e1773d6', 1, 1, 100, '2017-11-25 20:14:50', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 19:13:56', '2017-11-25 19:13:56', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('737a453389794e8cbdf30af88adc088a', '2a0aa340ca924385af2606420fad5737', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 13:15:29', '2017-11-29 13:15:29', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('82c292dbc57f496cb3b40d05671a4465', 'a6fbf7e6cdfb4d45a241a581a06b5731', '3640cddab5394d01a3bc839599bcceb0', 1, '3640cddab5394d01a3bc839599bcceb0', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2018-01-10 11:39:36', '2018-01-10 11:39:36', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('832f652d76ac46c0a0b6d79306deb1f5', '1572f25e56ae4f82908d0f45d9e4b61f', '2029c8cfc0da4748ba83172d1e850dbc', 1, '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 11:13:50', '2017-11-29 11:13:50', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('865d5c2b525444eb9f0a8b1fc14d4171', 'f65fb8df88b84e4ca7e0e5569c8ecea9', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-14 14:42:56', '2017-12-14 14:42:56', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('8c43ddc0357a4ab58bce0aa9f7edcf7e', 'f7b95290d7be4d108f104ccc710a848f', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-30 14:38:35', '2017-11-30 14:38:35', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('8c85662f7f464493b665190d435ea56a', '65f80d5cab6a4aa484b7d6f5b08a9eac', 'b2572ce53c23461ea802900a4813fd39', 1, 'b2572ce53c23461ea802900a4813fd39', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-30 17:11:54', '2017-11-30 17:11:54', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('8d05f775d1e54602bc8d44e280c66b2f', 'c78c27faefef40178f3d2f05d14e592b', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2018-01-22 16:15:30', '2018-01-22 16:15:30', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('8e9524e5009043b3b36a32183f3aa9a3', 'cf1a6f3144744127a3c86d45252cdb91', '49cab02d80a0412c88f7b437c19aa62d', 1, '49cab02d80a0412c88f7b437c19aa62d', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2018-01-09 12:36:16', '2018-01-09 12:36:16', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('8f05b97e2e624da5a98eecf2f7d06e64', '9a152751b3004e1b94cc702e8e857edb', '9b923977107645eca7703e3c3d26d455', 1, '9b923977107645eca7703e3c3d26d455', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-27 15:36:21', '2017-11-27 15:36:21', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('934771ed55d34ee2b72781a098db7535', '54fcb3a70d2143149899ad17bc01ae9e', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-02 15:58:59', '2017-12-02 15:58:59', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('93aefaea678d4444af204999367f5b7a', '0ef7fdf322934c55b0a804d561228328', '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, 1, 100, '2017-11-28 13:33:11', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-28 12:04:41', '2017-11-28 12:04:41', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('9457f228a71a475fb292359624b7a004', '43d9cfadf0ae441dac90101796db1203', '49cab02d80a0412c88f7b437c19aa62d', 1, '49cab02d80a0412c88f7b437c19aa62d', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2018-04-12 14:49:32', '2018-04-12 14:49:32', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('9461e4b592d54958a234dad862ce5c51', '07e7ba64169a43b9b9889db66d9b4c1f', '2029c8cfc0da4748ba83172d1e850dbc', 1, '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-27 11:53:44', '2017-11-27 11:53:44', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('94fc7ae7f0584f889bf689fd2d8c31e9', '84b12c435a1843b5a2916cce1ccb73fc', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 14:19:37', '2017-11-29 14:19:37', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('9b8d1269cbe44ec49874aef336b388e2', 'dc4c2d899ed24ad491e28aca7dc27316', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-14 14:56:01', '2017-12-14 14:56:01', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('9c67298523654e79a60884000f90483d', '24f8c8dfa0fc4acb8d0a8bba7e1773d6', 'b2572ce53c23461ea802900a4813fd39', 1, 'b2572ce53c23461ea802900a4813fd39', 1, 1, 100, '2017-11-25 19:23:16', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 19:13:32', '2017-11-25 19:13:32', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('9d8f0a9cbe6147978ffe8408bfdaeb19', '062fa5c384ac4439bbeff8150e34246b', '9b923977107645eca7703e3c3d26d455', 1, '9b923977107645eca7703e3c3d26d455', 1, 1, 100, '2017-11-27 15:44:48', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-27 15:13:22', '2017-11-27 15:13:22', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('9fb3675265bb446dbf83142411ebec6e', '6de0009478834cbdbd81dcd557409d5e', '9b923977107645eca7703e3c3d26d455', 1, '9b923977107645eca7703e3c3d26d455', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-30 10:46:50', '2017-11-30 10:46:50', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('a1969f9687ce46ca8dd38a0f8261a819', 'e7d5293a218f45e3b6007e68eedeea86', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-12 19:57:07', '2017-12-12 19:57:07', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('a3b3ed8087c040a69097f218ff889f0e', 'a521d908027d4ff8b4d3b93ec588b931', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 1, 1, 100, '2017-11-30 15:08:40', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 14:14:08', '2017-11-29 14:14:08', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('a56bfaa83d964b6b9a79ac1592a9d56c', '2b6467eeb24a485784ef32adcc8a9cd2', '9b923977107645eca7703e3c3d26d455', 1, '9b923977107645eca7703e3c3d26d455', 1, 1, 100, '2017-11-29 17:14:37', '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 16:56:08', '2017-11-29 16:56:08', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('ad431607173442c3b1b8d628f95a0d64', 'df8ac616c5aa4332a32f53fda7139b9c', '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-04 20:40:45', '2017-12-04 20:40:45', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('addbbe8b408b4eb4b3aaab2a3ca61c2b', '72f0e57e463543e486fbbce19e627fe1', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-07 20:47:05', '2017-12-07 20:47:05', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('b446bb55845d4da281cda274e65ee83c', '9b78d521f7e34132b7dee46fc3093d42', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 15:14:02', '2017-11-29 15:14:02', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('c228760887f445b39442ca5fd37f5327', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 'b2572ce53c23461ea802900a4813fd39', 1, 'b2572ce53c23461ea802900a4813fd39', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 14:18:26', '2017-11-25 14:18:26', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('c41aabd9d3754840b260b5d6b377dd4f', '25f7a23d920f48cf89b3eae0e69d8df3', 'e040813c1f8149b7a7a56a4ee5f2a0de', 1, 'e040813c1f8149b7a7a56a4ee5f2a0de', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-25 19:33:10', '2017-11-25 19:33:10', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('c4d8fcf711a24dac9da109abb8ddc3c2', '5092f5f88686448287cd5ce587fa9e32', '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-04 20:54:17', '2017-12-04 20:54:17', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('c5ce4f042c0948c59fa18d3e569f38de', 'ceb28fc631e64c528d7865658ac4a38d', '2029c8cfc0da4748ba83172d1e850dbc', 1, '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2018-01-09 13:53:34', '2018-01-09 13:53:34', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('c623200cb9e141b8b35b5cf1983d38ef', '509001af9def4daa8c638d79e9deb67d', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 15:11:46', '2017-11-29 15:11:46', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('c64740294899421eb5391e6e2fdf16f5', '7d5bd2e611f342ec8921a18280b79c83', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-28 21:40:23', '2017-11-28 21:40:23', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('cb20a0bfcc764afcaf444667b8e36374', '409be31c618c4c14b33c612c94dc80c1', '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-28 13:26:43', '2017-11-28 13:26:43', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('d17d1db983834f7e90e32ea7ad335d6c', '702c5245714743db84d306d41a83a4d0', '92183126447f446ca783f3f19e144198', 1, '92183126447f446ca783f3f19e144198', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-21 13:53:46', '2017-12-21 13:53:46', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('ddcc4b67d8f04bf9833f9cdb04a5fc0b', 'ea7e627a8c2f4386b645731a8abf472f', '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2018-01-13 20:46:29', '2018-01-13 20:46:29', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('de0cb2efa40e4565a1ec4915014bd92e', '74e83089a7ea4533a28f4fa6f0bea485', 'b2572ce53c23461ea802900a4813fd39', 1, 'b2572ce53c23461ea802900a4813fd39', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 12:43:53', '2017-11-29 12:43:53', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('dea4bfefcc0848449919af4c0f2b1cf2', 'f49dd0101441464a8a9b3dbbe224da75', '92183126447f446ca783f3f19e144198', 1, '92183126447f446ca783f3f19e144198', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-30 14:37:06', '2017-11-30 14:37:06', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('e3b7e0bd4c204ce7b142f1fb8f763aad', '375c33c29d1442449acdf17deb5620a9', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-13 13:02:39', '2017-12-13 13:02:39', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('f1f68e337b624050a346d77ad4fbbbc5', '8540ba3ae8684646b970d6762fe6bab3', 'de83afa9bbd54278a16b8f6580d5f58f', 1, 'de83afa9bbd54278a16b8f6580d5f58f', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-14 14:20:28', '2017-12-14 14:20:28', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('fe4d1684e9d54871b8b0fcc29b20e5b2', '48fb416505854b27b788953e8aee9b34', '12d4ab2da7394354996db664828c7242', 1, '12d4ab2da7394354996db664828c7242', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-11-29 09:44:18', '2017-11-29 09:44:18', NULL, 0, NULL);
INSERT INTO `tz_recommend` VALUES ('fee7f728392a497eb47b07e6a5005a27', 'c6ee5f0a26d44ec2a6dea86aa1d75d9c', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 100, NULL, '2cfc2e2d185e4609af521e2b744ddb98', '2017-12-11 16:39:21', '2017-12-11 16:39:21', NULL, 0, NULL);

-- ----------------------------
-- Table structure for tz_role
-- ----------------------------
DROP TABLE IF EXISTS `tz_role`;
CREATE TABLE `tz_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '父节点目录',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色关键字',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `zindex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同级目录下优先级',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_role
-- ----------------------------
INSERT INTO `tz_role` VALUES ('0', '', '角色目录', 'role', '所有角色的顶级父目录', NULL, '96479df69b21468ca8a1e0ce754df5bb', '2017-11-01 15:21:46', '2017-12-01 15:21:47');
INSERT INTO `tz_role` VALUES ('04a172874f4042918fdb5e6116f68866', '0', '物流管理员', 'expressManager', '该角色负责对订单列表中数据添加修改物流信息以及发货处理', '', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-11 13:51:09', '2017-12-19 11:40:32');
INSERT INTO `tz_role` VALUES ('05b63b913c8b45caaa36b1bef832a05a', '0', '超级管理员角色', 'admin', '该角色拥有所有权限和所有角色的权限', '1', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-11 10:16:52', '2017-12-11 10:16:52');
INSERT INTO `tz_role` VALUES ('8f28111177b14497bc7ac86e1a2ba690', '0', '财务管理员', 'financialManager', '该角色可以对会员管理信息进行操作', '', '96479df69b21468ca8a1e0ce754df5bb', '2017-12-11 10:20:01', '2017-12-19 11:45:02');

-- ----------------------------
-- Table structure for tz_role_function
-- ----------------------------
DROP TABLE IF EXISTS `tz_role_function`;
CREATE TABLE `tz_role_function`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `function_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_role_function
-- ----------------------------
INSERT INTO `tz_role_function` VALUES ('0034f42047e6461ba2b555457cec6544', '37f7ad5d097749c3bdbd9597a1096000', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('00ac06bbed2f430d9b61d4b37baab008', 'f503d9d6ec56430992ef43270514fe5b', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('0437ac577ddd48689dd5252b45d0c1c9', '1d1c2df77bde466bbd4dabbc446e61d8', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('04533d81659548aea6cde5de22fae2e4', '37f7ad5d097749c3bdbd9597a1096000', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('051cea8ed8e04585aee6a98dc80a891b', 'e9142879ecbc4c4fbc745a6168f6ccec', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('06b3c5d7af374852acde02843450b896', 'f13e9c7224d746bbbe20b6538b32bef8', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('06d60f2b11d04e4e818a78dae62ec793', '2c62a2a15ff649f7872805fba37a3bd3', '04a172874f4042918fdb5e6116f68866', NULL, '2017-12-19 11:40:32', '2017-12-19 11:40:32');
INSERT INTO `tz_role_function` VALUES ('1397efe689fb43f0a7343db228a4402a', '71db7597bb2343409d6b467cd93f4a93', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('14a086f5e8774963824ad9c6090418f7', '62064c349f4b469993ef8f7b05272d6f', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('1580685b37dc49f6999e2df9c2f77ab6', '532b4ffc48ed4fae9a61dc1e844bd075', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('17306f8883a74f67b925f5e28e27d565', '4e2f3e78f9be43688d25d38fa2f6749f', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('18ac8bd3106c4f5bbfa03a5228e4ad94', '105c29f5ee2244ff8d0316b1cbb45a35', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('19680aa08af04941acf72bc067387862', '532b4ffc48ed4fae9a61dc1e844bd075', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('1e2ecc31d740491bb67af978cdd372be', '84f4d26c511744e484df165e2fdba411', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('2019b7f89d4343f7bdd45c390e44ad93', 'a713ab2f73de4067909fd6318681a7ff', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('267dbd4d7b2c4a619d30ceac2f6dcd94', 'a713ab2f73de4067909fd6318681a7ff', '04a172874f4042918fdb5e6116f68866', NULL, '2017-12-19 11:40:32', '2017-12-19 11:40:32');
INSERT INTO `tz_role_function` VALUES ('27dd60ff44f645a596c4f482cc9f4fec', 'fce230c0f8404e11bf1af7246b3275fa', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('2a245b0acbc547ed873057c56f6f6f18', 'd4e8328ee5644a389a9381023cf8acd6', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('2a2e7eda7e4b4828b29fe27a91827ac6', 'ebdf5205737c43e495fd74e5409bcedb', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('2f4313028f534d24afc2719e79c09c01', '139eac53150c46be899b084adae9ebb0', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('300555e8a644474693aff549ad24dd44', 'cb9d4425d8324c3299e1dbb5fd775d0e', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('31cf586ec90647e5b4af818e20d53a12', 'ba33f15525c64b219fe6097619c5a712', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('3249f38db42544a89fc3ce0a3af65c5c', 'cf48e9158b5d4163a49e0dc6d1dff1f8', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('3a97e0dc7a164e7aa5ff3f165975cdbf', 'b35bd7c0d7f9458e9d231ea2d821150e', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('3ca48a61d2b54b35a3993f120fb42027', '105c29f5ee2244ff8d0316b1cbb45a35', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('3f68f77d3cab4a91bc5c944882f8566d', '4190ad5abde04aad9b393be105e3f92f', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('44f236c52eb1429eb156f5b28d20a179', 'd8d811b49e3543b88f921dc57870162f', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('469cca09f58448708d101363c24d4f64', '069de4a4334340018c9811a59ebd2bc7', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('4c302969b78446a8b234590b99a848c4', 'c945e885beb34c5c83b8f04698549bb9', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('5202726d315d42c7a3a0df2e5daf6779', '139eac53150c46be899b084adae9ebb0', '04a172874f4042918fdb5e6116f68866', NULL, '2017-12-19 11:40:32', '2017-12-19 11:40:32');
INSERT INTO `tz_role_function` VALUES ('5a1123e32e284385bfca2b3a1eaa2ac4', '10f59d3cd63c4f07b7e1fb0f663c65e2', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('5e78dcb533e14d6690d53a0bb5dd007b', 'a713ab2f73de4067909fd6318681a7ff', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('6013c1cd9c6e49c891f0cd41b0527966', 'ba33f15525c64b219fe6097619c5a712', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('614aa3316e6042b6b830be963822a18d', 'a992cd21a7d8413db61042f208da321f', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('63629da2d9e342c7a53e026af8a8c320', 'e9142879ecbc4c4fbc745a6168f6ccec', '04a172874f4042918fdb5e6116f68866', NULL, '2017-12-19 11:40:32', '2017-12-19 11:40:32');
INSERT INTO `tz_role_function` VALUES ('6585af924d0b4045af9b8e57e0b4e8d7', 'f5dd166e37044b08956c3b85ad9bf215', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('65d7bb04e7d7408fa350497e30b20440', '4efaba06cb9543f682b6d6eff4239801', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('7143556f3b784bcd960215a3bbbd164f', '0949fba7524741418315c4313370448a', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('73008c0341b0467c9f26f306c05506b0', 'cb9d4425d8324c3299e1dbb5fd775d0e', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('75de0726ed9d4dc8a91fe33dad3196cc', '06d355757fac4907a97fb6f9a4bc4db4', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('7962081c5a234f979d10f05b173df72f', '258abf9022ff4ce0aacfc9fd8ba4489f', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('79aa6775ea0f406a87cf0856009bf186', '34419249936640a6be9e8c5e6b39fea2', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('7a3427ea5f0343ed95204f95672c5108', 'e8f575305fce49c3a9a5d220718877b8', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('7b2502553aae4330a55df9330e60a30c', '2c62a2a15ff649f7872805fba37a3bd3', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('7bd634ade8d545278c15d171784d7d9f', '71db7597bb2343409d6b467cd93f4a93', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('7ca09659f4b34e38a12c8aca0ac7842e', 'a44d0e8d134d40b9b52ff4f17b0a3f80', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('80000f99ed394b1ca97c8caab28ccdd8', '069de4a4334340018c9811a59ebd2bc7', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('8199deeadd544945a06e6200c977aba2', '32ff800897b74990ab4b34317741b490', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('8b190c5142c54cd184130c56a8632b2a', '71018a6a85cf4318a89923e4020e0ce0', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('8b7ba6d788284ae1aaa41e85d51a74cc', '5c0855c073374b4cb7642fb6ef75f392', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('8de30908973048339da9026010bdd4b1', '06d355757fac4907a97fb6f9a4bc4db4', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('9210544c788a4183bd818fdaeb7d04da', 'd30ea60913404d30848659ad455a640b', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('924cb53715b4483a8f133fba87e66353', '28890b0d6a434a26b10dabebb7bbb658', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('96e4ffdc56e54f79aa85dfd0a1c3df5d', '34419249936640a6be9e8c5e6b39fea2', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('9af846dc22a44162bc132cdc5653ecd0', '0', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('9e7c1d9d3ec04698826e9e1c9867faff', 'a992cd21a7d8413db61042f208da321f', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('a0467c1e49be44a19f59eca904c207f1', '822b52b7607b417588daeac1626dc8fd', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('a11156f3f5684e5eb8cb79e8a022f1ff', 'fbaf464c9fd2458680112a90c52e81ff', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('a3fc57170275416284825befcab28b1d', 'e218390dff224fa9a804fe1c57d5d0e1', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('a612e3a6b56649aba90a3f8c1dda910f', 'ee24b7ae92474b82ace2128f15478cc1', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('a836f3804cfb4b748661df0ca6995a38', '34419249936640a6be9e8c5e6b39fea2', '04a172874f4042918fdb5e6116f68866', NULL, '2017-12-19 11:40:32', '2017-12-19 11:40:32');
INSERT INTO `tz_role_function` VALUES ('b039fc7a51034c65ad64ca2f1a5a4eb3', 'b35bd7c0d7f9458e9d231ea2d821150e', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('b1d58a9a317b4392ab4c174fa2f2f92d', 'e56e2dff864f4390b84e6952f822e6e6', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('b4fa83d65189409baa0c2bc996dd8e73', 'ee24b7ae92474b82ace2128f15478cc1', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('b928ccc8ad2a44f3bc84ffe8aef434c7', '32088b30731c4342b06fd4f3c65f38d7', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('bc8f050f118f432786977c0ad54c4ac2', 'e8f575305fce49c3a9a5d220718877b8', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('bd9db711522f4e549c337d63ff4392ae', '6008e429b50f4ddd8a31f0243af148d1', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('c87257388d72436780e85c0a8bb21b2c', 'cf48e9158b5d4163a49e0dc6d1dff1f8', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('ca44629b40ff47b2957038fbc68685e7', 'b59dbe4406234784867dbd56f5b030c3', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('cab367c546894bd19b656d957023a438', '6479e8bad7e94952ae2f417a8c8bf4fc', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('cafe1257714f4c4289a96c3e13b19b75', 'f503d9d6ec56430992ef43270514fe5b', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('d529e09e6dab41b0a389336e497bf8a7', '139eac53150c46be899b084adae9ebb0', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('d744079a627643009030f01dbd1fa0b5', '8b887cc1bc454cf599304f692b9819ae', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('d8c6423353f44636a3978234a827619a', '32088b30731c4342b06fd4f3c65f38d7', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('d992cab5dcd24dc180b708b7cafc37c8', '4190ad5abde04aad9b393be105e3f92f', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('dbbe65ab5daf425ab6659b01a603e61e', 'fce230c0f8404e11bf1af7246b3275fa', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('df5d1cfbefaf4a0db9ea9ee082279b05', 'e9142879ecbc4c4fbc745a6168f6ccec', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('e20065512bf84b87bd25fca844c3a639', 'f5d227ce91ef4b9aa6e3be9f4e310fc3', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('e2bae0e24c1348269371156300d7e193', 'd402046784104c4a8a713142aa33a5f6', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('e746c6726f2a42d1b0e4222d0d84b06b', '9d0fbc88bb7845eca549621f4078f57e', '05b63b913c8b45caaa36b1bef832a05a', NULL, '2017-12-11 10:16:54', '2017-12-11 10:16:54');
INSERT INTO `tz_role_function` VALUES ('eb0fcfa84b0c440c9162ab8dfcc724e7', 'd4e8328ee5644a389a9381023cf8acd6', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('f187087d05734833acb1729f3e9b0ac6', '2c62a2a15ff649f7872805fba37a3bd3', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('fd0e0531092b4f42b5dd66cff98ca8e5', 'f5d227ce91ef4b9aa6e3be9f4e310fc3', '8f28111177b14497bc7ac86e1a2ba690', NULL, '2017-12-19 11:45:02', '2017-12-19 11:45:02');
INSERT INTO `tz_role_function` VALUES ('fe6644ef16a040b7a199c0ab7d1c759c', 'ba33f15525c64b219fe6097619c5a712', '04a172874f4042918fdb5e6116f68866', NULL, '2017-12-19 11:40:32', '2017-12-19 11:40:32');

-- ----------------------------
-- Table structure for tz_user
-- ----------------------------
DROP TABLE IF EXISTS `tz_user`;
CREATE TABLE `tz_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户注册登录名称',
  `user_nick` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '520' COMMENT '用户昵称',
  `user_sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别 0 男 2 女',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册短信发送手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱redis设置30分钟有效激活时间',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `head_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `is_member` int(2) NULL DEFAULT 0 COMMENT '是否成为了会员，注册默认不是会员',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '用户注册时间',
  `last_upload` datetime(0) NULL DEFAULT NULL COMMENT '用户最近登录的时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '用户最近修改时间',
  `status` int(2) NULL DEFAULT 1 COMMENT '用户账户是否冻结 0 冻结 1 不冻结 2 ：删除',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该记录操作人id',
  `userType` int(11) NULL DEFAULT 0 COMMENT '用户类型（后期使用）',
  `recommended_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户推荐码',
  `qr_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_user
-- ----------------------------
INSERT INTO `tz_user` VALUES ('00e3168425b946c6b630d8ebdcd028f9', NULL, '520_841085', '0', 'afdf2f8af807a86c02ebb3fb26b32863', '13880415746', NULL, '6N3nlYUcqtmeZ', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 17:00:04', '2017-11-25 17:00:04', '2017-11-25 17:00:04', 1, NULL, 0, '761789', NULL);
INSERT INTO `tz_user` VALUES ('01432206572d4746829e4ea40f42fba3', NULL, '520_902110', '0', '4f23163418292dcf1c38e26e64f736ea', '18384581875', NULL, 'daTrDOktuFDJv', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-18 17:17:51', '2017-12-18 17:17:51', '2017-12-18 17:17:51', 1, NULL, 0, '987356', NULL);
INSERT INTO `tz_user` VALUES ('01c9b354e2b34a25a4a9a79c88d2b218', NULL, '520_690856', '0', '832fdd6f01cdc9addf4e8289528e15c1', '18551425076', NULL, 'gSvCucUAs', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-18 09:06:51', '2017-12-18 09:06:51', '2017-12-18 09:06:51', 1, NULL, 0, '271435', NULL);
INSERT INTO `tz_user` VALUES ('062fa5c384ac4439bbeff8150e34246b', NULL, '520_377545', '0', '6339c951d834e77f2421dc99f7803ecc', '15196612685', NULL, 'JnGx0v78', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-27 15:13:22', '2017-11-27 15:18:23', '2017-11-27 15:13:22', 1, NULL, 0, '202236', NULL);
INSERT INTO `tz_user` VALUES ('07e7ba64169a43b9b9889db66d9b4c1f', NULL, '520_869650', '0', '8edcaa9242b99fe67ed6572685a51088', '13648083082', NULL, '0ngEZNJi8o', 'group1/M00/00/11/rBBH51obnbSAXiHnAABp3fIv1po732.png?upload1.png', 0, '2017-11-27 11:53:44', '2017-11-27 13:11:14', '2017-11-27 13:08:04', 1, NULL, 0, '840403', NULL);
INSERT INTO `tz_user` VALUES ('0ce2930756144b41b623497f376e8a34', '顾浩', '520_289752', '0', '08fb8cb93cb5cc9d434922e6e05491f4', '13709061799', '', 'ckS9ZERb', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 13:57:44', '2017-11-25 14:16:45', '2017-11-25 13:57:44', 1, '001', 0, '365154', NULL);
INSERT INTO `tz_user` VALUES ('0ef7fdf322934c55b0a804d561228328', NULL, '520_141467', '0', '49323e8f16f5ede15b0c6cceb60b6b90', '18011302051', NULL, 'HxiJQIvl7', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-28 12:04:41', '2017-11-28 12:49:55', '2017-11-28 13:35:41', 1, NULL, 0, '227587', 'group1/M00/00/11/rBBH51oc9ayAMh7yAAAJ5TQvegU007.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('12d4ab2da7394354996db664828c7242', '李项楠', '520_445126', '0', '5ada48a7a69551070daf6bf24db1476f', '15101582583', '', 'Bo5cepVC7X34MD9', 'group1/M00/00/10/rBBH51oZDZaAFjr3AAe-Z7JI_VU148.jpg?20171125142837.jpg', 0, '2017-11-25 14:25:01', '2018-01-10 12:31:57', '2018-01-10 12:32:01', 1, '001', 0, '097416', 'group1/M00/00/13/rBBH51pVl0GAG8zIAABXmeEIQfY463.jpg?20180110123201.jpg');
INSERT INTO `tz_user` VALUES ('1433defc747644e280e942e2b67dcfd8', NULL, '520_280080', '0', 'fa46878335425ff53f0741de12c38ff3', '13880310588', NULL, 'o8ZYhNW8SxV0vTu4', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 17:00:04', '2017-11-25 17:00:04', '2017-11-25 17:00:04', 1, NULL, 0, '699117', NULL);
INSERT INTO `tz_user` VALUES ('1572f25e56ae4f82908d0f45d9e4b61f', NULL, '520_088210', '0', '4b6ccf8d871d2ec927a071f0a4598fd5', '15281029351', NULL, 'WGmwv5TAVvKgn', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 11:13:50', '2017-11-29 11:15:02', '2017-11-29 11:13:50', 1, NULL, 0, '610801', NULL);
INSERT INTO `tz_user` VALUES ('1a048c6799024e54aa29a962a545020b', NULL, '我', '1', '34263cbfe3bf2162590dfb9d009736ea', '18384591919', NULL, '4MIh6wCezF79d8SD', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-27 09:18:02', '2017-12-08 17:16:06', '2017-12-08 16:41:44', 1, NULL, 0, '598765', NULL);
INSERT INTO `tz_user` VALUES ('1dc29c0808cf4e86bca3951e5a26dec8', NULL, '520_839209', '0', 'faf0ca3fbaf8d59e95f365c376bd1b45', '15723387167', NULL, '8qerG8T9o71syX', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 15:57:14', '2017-11-25 15:57:14', '2017-11-25 15:57:14', 1, NULL, 0, '000699', NULL);
INSERT INTO `tz_user` VALUES ('2029c8cfc0da4748ba83172d1e850dbc', '罗雄', '洛洛洛', '0', 'acae4039de45c6721655be6df9c87dbe', '15528260013', '', 'qxJfNBD', 'group1/M00/00/10/rBBH51oZDYKAQVMPAAAwCUD-3AU169.png?upload1.png', 0, '2017-11-25 14:25:20', '2018-01-22 15:14:16', '2017-11-25 14:28:20', 1, '001', 0, '160791', 'group1/M00/00/10/rBBH51oZDQ2APoDIAAAJ62KxvKg219.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('2367648fdbe443a08d80b22ebfb079a2', NULL, '520_927058', '0', '3914135257a7ff3c27bde0be92eb51fa', '18755036618', NULL, 'NvMOvXo64FW7', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-11 10:52:33', '2017-12-11 10:52:33', '2017-12-11 10:52:33', 1, NULL, 0, '154369', NULL);
INSERT INTO `tz_user` VALUES ('24f8c8dfa0fc4acb8d0a8bba7e1773d6', NULL, '520_538355', '0', '1fe56e01c5d99d02a0452316885c99ac', '18980689935', NULL, 'Ab9G6IwiiC', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 19:13:32', '2017-11-25 19:13:32', '2017-11-25 19:23:47', 1, NULL, 0, '343716', 'group1/M00/00/10/rBBH51oZUsOABayGAAANhr_ADnE816.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('25849a752e5c402786bd5fdf0e32db8a', NULL, '520_002734', '1', 'b06b67ad6b37327b91576787cdfd91e8', '13568965834', NULL, 'Q9rK36p3KoP', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-13 12:52:05', '2017-12-13 12:58:05', '2017-12-13 12:58:30', 1, NULL, 0, '945653', NULL);
INSERT INTO `tz_user` VALUES ('25f7a23d920f48cf89b3eae0e69d8df3', '吴小芳', '520_112452', '0', '706ea4fcf439f35e15c332afbfd3ce90', '15957152212', '', 'zapVzNxh8cKN', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 19:33:10', '2017-11-25 19:33:10', '2017-11-25 20:18:24', 1, '001', 0, '295423', NULL);
INSERT INTO `tz_user` VALUES ('288d7a49141341ffac8be7ab1d799cc0', NULL, '520_889323', '0', '7672f7f8c93e4a92f8fdf94af1bab3cc', '18683789591', NULL, 'j9NSpDp', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 11:05:41', '2017-11-30 12:32:14', '2017-11-30 11:05:41', 1, NULL, 0, '372051', NULL);
INSERT INTO `tz_user` VALUES ('2a0aa340ca924385af2606420fad5737', NULL, '520_816458', '0', '2d9e81324209f69578b81e37cca48e62', '13708222457', NULL, '5zGJnvpmaeSbWHAe', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 13:15:29', '2017-11-29 13:15:29', '2017-11-29 13:15:29', 1, NULL, 0, '272238', NULL);
INSERT INTO `tz_user` VALUES ('2b6467eeb24a485784ef32adcc8a9cd2', NULL, '520_113493', '0', '50742f68ace4602a213dfad07e954d90', '15708455258', NULL, 'af4mgdi82P1pI', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 16:56:08', '2017-11-29 17:08:02', '2017-11-29 16:56:08', 1, NULL, 0, '543483', NULL);
INSERT INTO `tz_user` VALUES ('2dfa9c00d82b47c796620533e22e4db0', NULL, '520_881965', '0', '2823b31db08f6fb948f8c896f09b20a3', '18680434262', NULL, '1I7ecjjlu', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-07 10:34:47', '2017-12-07 10:35:41', '2017-12-07 10:35:46', 1, NULL, 0, '972753', NULL);
INSERT INTO `tz_user` VALUES ('2fd496edc2d243b288e27f5f12538856', NULL, '520_675660', '0', '49a1c631010186dda55f12b2a326ffec', '13981842836', NULL, '0gsarXvinviPFUg', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 16:59:05', '2017-11-25 16:59:59', '2017-11-25 16:59:05', 1, NULL, 0, '613343', NULL);
INSERT INTO `tz_user` VALUES ('3640cddab5394d01a3bc839599bcceb0', '贾艳芳', '派大星', '1', '5145f0e86eed54fcfc749900b8aba9ac', '15756265835', '', 'FDtoPfZAsd', 'group1/M00/00/12/rBBH51oorxGADdToAABpK5WWWUs445.jpg?86bbb8bf-91e1-4aca-9ab9-600ffbf88dbc.jpg', 0, '2017-11-25 14:25:13', '2018-03-20 23:29:40', '2018-01-10 12:01:20', 1, '001', 0, '448648', 'group1/M00/00/13/rBBH51pVkBCARpgJAAK4eap7Rhk039.jpg?image.jpg');
INSERT INTO `tz_user` VALUES ('375c33c29d1442449acdf17deb5620a9', NULL, '520_170477', '0', '7112a215bf7a0a5103655334f713e235', '13689061586', NULL, 'V83YE8Hz31lCN', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-13 13:02:39', '2017-12-13 13:02:39', '2017-12-13 13:02:39', 1, NULL, 0, '482881', NULL);
INSERT INTO `tz_user` VALUES ('3877878856b74d2c8e276945a392a44b', NULL, '520_368263', '0', '10cde1e3ab4a4459e16d8ad8eacb53f1', '13283817653', NULL, 'fzuXGrh2lLrso', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-04 10:46:50', '2018-01-07 13:07:02', '2018-01-04 10:46:50', 1, NULL, 0, '210190', NULL);
INSERT INTO `tz_user` VALUES ('3ae34de0ea984028a9a8cc72ba649a7d', '王风霞', '520_348872', '0', '06da6c0a2ed09cd1f35d6d5e48d8da73', '13808062866', '', 'yCLp4jDv5z2', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 19:13:56', '2017-11-25 19:13:56', '2017-11-25 20:05:33', 1, '001', 0, '824705', NULL);
INSERT INTO `tz_user` VALUES ('3fa7e9e592d74c93a65d44244cec5612', '杨春', '520_867110', '0', '0fa26b605ae90be68f2c7368a6a296a', '15208190920', '', '3maMTg0MHlF', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 14:25:30', '2017-11-25 14:25:30', '2017-11-25 14:28:09', 1, '001', 0, '107072', NULL);
INSERT INTO `tz_user` VALUES ('409be31c618c4c14b33c612c94dc80c1', NULL, '520_986059', '0', '214d98ea8995891eee6b93cd679e7cf0', '13568911227', NULL, 'YTtUsXrPP9O', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-28 13:26:43', '2017-11-28 13:34:20', '2017-11-28 13:26:43', 1, NULL, 0, '351943', NULL);
INSERT INTO `tz_user` VALUES ('43d9cfadf0ae441dac90101796db1203', NULL, '520_682616', '0', '8ba2f636bb4b68c19fb0166977ad325c', '15882263675', NULL, '4j7lXYRU', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-04-12 14:49:32', '2018-04-12 14:51:40', '2018-04-12 14:49:32', 1, NULL, 0, '805154', NULL);
INSERT INTO `tz_user` VALUES ('45a6024bf01546fda014453b07966d5d', NULL, '520_281014', '0', '7df96445e1255037ddcbae29128962e0', '15719439001', NULL, '3ZtZTq2I0hkgpo', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-21 10:08:31', '2017-12-21 10:10:55', '2017-12-21 10:08:31', 1, NULL, 0, '669087', NULL);
INSERT INTO `tz_user` VALUES ('46c4472aa6434c8f95f0fab15b81bb53', '何姐', '520_815992', '1', '92f3a5352a92a0cf33d88bdab6110118', '13808060091', '', 'S8AKbhuYvVslW6Hc', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-27 10:59:39', '2017-11-27 12:12:42', '2017-11-27 12:03:35', 1, '001', 0, '999999', 'group1/M00/00/10/rBBH51objpeAE0bgAAAJ3gA8wLA425.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('46f04a3da4874ce88713e68d8cc96092', NULL, '520_301069', '0', '3d76e6206f0a6d330587b6e76fd67841', '18692015873', NULL, 'AScw3m8HmgZIxk', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-16 13:27:40', '2018-01-16 13:27:40', '2018-01-16 13:27:40', 1, NULL, 0, '106971', NULL);
INSERT INTO `tz_user` VALUES ('48fb416505854b27b788953e8aee9b34', NULL, '520_404159', '0', '470dce0f81a80ba5f6f49b3ab34bfbb7', '17600102108', NULL, 'WuQgmSFt9A', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 09:44:18', '2017-12-07 09:30:40', '2017-11-29 09:44:18', 1, NULL, 0, '357433', NULL);
INSERT INTO `tz_user` VALUES ('49cab02d80a0412c88f7b437c19aa62d', '王敬华', '520_623006', '0', '312fc10b56c5bccf2e2cae0756ab6c8c', '18982229408', '', 'WvNUphIABNWyFIt', 'group1/M00/00/14/rBBH51rO_qOAbviGAABTkieh4HE882.jpg?094ae5e6-53b6-4c23-8c49-85b604b1a7cd.jpg', 0, '2018-01-04 09:56:37', '2018-04-12 14:35:46', '2018-04-12 14:46:35', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '508833', 'group1/M00/00/14/rBBH51rPAMuAJLiMAAHmzbvQ1js072.jpg?image.jpg');
INSERT INTO `tz_user` VALUES ('4e70c024ff4a48d4a3df99de9e93c029', NULL, '520_432475', '0', 'c2f1cec6f15f88d863cf071fdc3fe4b6', '18284562392', NULL, 'OjtkW3dyUAEQjmWb', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 18:45:38', '2017-11-30 18:45:38', '2017-11-30 18:45:38', 1, NULL, 0, '478201', NULL);
INSERT INTO `tz_user` VALUES ('509001af9def4daa8c638d79e9deb67d', NULL, '520_275129', '0', '70116481a4ff56f3c93f007d4311ac95', '15291284444', NULL, '56c1nedu', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 15:11:46', '2017-11-29 15:11:46', '2017-11-29 15:11:46', 1, NULL, 0, '527916', NULL);
INSERT INTO `tz_user` VALUES ('5092f5f88686448287cd5ce587fa9e32', NULL, '520_653439', '0', 'c9fe3bc9ce3197b887c78019ce87a759', '13981882226', NULL, 'pcW2vhSeKGGs5d', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-04 20:54:17', '2017-12-04 20:55:06', '2017-12-04 20:54:17', 1, NULL, 0, '407082', NULL);
INSERT INTO `tz_user` VALUES ('50d561e804fa42feb4e557aa069dbb94', '曾馨煜', '520_815835', '0', '7e30f30670016bfeffd4183b8faa859f', '13402878852', '', 'AeFTiOMysU4wdT', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 14:25:16', '2017-11-25 14:25:16', '2017-11-25 14:28:52', 1, '001', 0, '676969', NULL);
INSERT INTO `tz_user` VALUES ('54fcb3a70d2143149899ad17bc01ae9e', NULL, '520_711883', '0', '932faf0ccc805ece6341ad9c3c010822', '15911052852', NULL, 'CVQrzFxUIiug', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-02 15:58:59', '2017-12-02 15:59:47', '2017-12-02 15:58:59', 1, NULL, 0, '900734', NULL);
INSERT INTO `tz_user` VALUES ('55bef243e1f646c7a2bd28ba15e42c96', NULL, '520_561109', '0', 'e4d22133a4144ca36c734fef6ad2edf9', '18575695421', NULL, 'wAYno1NqxQAqfi', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-07 10:37:01', '2017-12-15 11:08:37', '2017-12-07 10:37:01', 1, NULL, 0, '008309', NULL);
INSERT INTO `tz_user` VALUES ('585dcde9e7144f22ba07e22a81abc288', NULL, '520_111895', '0', '8551a584566d1a93c7ce61a8b8fff24e', '13880582919', NULL, 'fvLMHVVvSP1', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-04-10 17:46:26', '2018-04-10 17:46:26', '2018-04-10 17:46:26', 1, NULL, 0, '871834', NULL);
INSERT INTO `tz_user` VALUES ('591f524a04d94209974fafd6ffcb56d9', NULL, '520_682774', '0', '972207eaef223b9680dd4dbcb864253c', '15284930448', NULL, 'QPZueGB875Zf', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-04-03 23:24:36', '2018-04-03 23:24:36', '2018-04-03 23:24:36', 1, NULL, 0, '494315', NULL);
INSERT INTO `tz_user` VALUES ('59313dae44844f3d819e960ecfad605a', NULL, '520_676338', '0', '5a1331885aeec56e50bb2dfd16b3fc5d', '13032837878', NULL, 'igJUaocP', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-11 16:41:18', '2017-12-11 16:41:18', '2017-12-11 16:41:18', 1, NULL, 0, '396810', NULL);
INSERT INTO `tz_user` VALUES ('593a622faa884a02a5996ac688d3b156', NULL, '520_184202', '0', 'c8d481e4853259904c1f67c0ce0cddbd', '13808080974', NULL, 'GDAQLCTklgA1x', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 17:33:02', '2017-11-25 17:33:02', '2017-11-25 17:38:24', 1, NULL, 0, '852479', NULL);
INSERT INTO `tz_user` VALUES ('5de9cdaf39c744dd9fed887b25cf3187', '顾家铭', '520_745273', '0', 'fd084248a0022665c9b712000d968a73', '15982903311', '', '8tRMyocnQa', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-22 17:04:41', '2018-01-22 17:04:41', '2018-01-22 18:44:00', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '504056', NULL);
INSERT INTO `tz_user` VALUES ('618e1fa97918493b9c61d029cef10fe2', NULL, '520_233013', '0', 'c21a612643baabb57082dc9b079de538', '18090732986', NULL, 'IJE0nz8R', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-12 11:56:23', '2017-12-12 11:56:23', '2017-12-12 11:56:23', 1, NULL, 0, '627480', NULL);
INSERT INTO `tz_user` VALUES ('65f80d5cab6a4aa484b7d6f5b08a9eac', NULL, '520_412302', '0', '4730d1f0721bdc9b2971dcd7ed42e0d4', '18016163662', NULL, 'ohPOelkEg', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 17:11:54', '2017-11-30 17:11:54', '2017-11-30 17:11:54', 1, NULL, 0, '610921', NULL);
INSERT INTO `tz_user` VALUES ('6b01833621ec4daaa9804a5e28e7df43', NULL, '520_168774', '0', 'dd29730cf3f4a759cd0845f5ea08d3e6', '13198278888', NULL, 'Fk8R9mFKjAD79he', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 16:00:11', '2017-11-30 16:00:11', '2017-11-30 16:00:11', 1, NULL, 0, '171538', NULL);
INSERT INTO `tz_user` VALUES ('6de0009478834cbdbd81dcd557409d5e', NULL, '520_833707', '0', '7467a283ecf1c4dfb4468298dbdde3c2', '15082648883', NULL, '3PqbJqamtKYP', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 10:46:50', '2017-11-30 10:46:50', '2017-11-30 10:46:50', 1, NULL, 0, '988406', NULL);
INSERT INTO `tz_user` VALUES ('6e12cc0c8d6e482f8395e224a982e853', NULL, '哈哈哈哈哈哈', '0', 'c13812b907c1762ec14dc9a66b613962', '15196108493', NULL, 'KlJdMXfaRN9I', 'group1/M00/00/10/rBBH51obljmAG4YdAADTSQwRq7s763.png?upload1.png', 0, '2017-11-27 12:11:43', '2017-11-27 12:11:43', '2017-11-27 12:36:11', 1, NULL, 0, '790831', NULL);
INSERT INTO `tz_user` VALUES ('702c5245714743db84d306d41a83a4d0', 'cyy_01', '520_242570', '0', 'd94e29b87dfcc0532d872c66379b1555', '13880813949', '', 'dDGwobgTomlHD', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-21 13:53:46', '2017-12-21 13:53:46', '2017-12-26 17:30:36', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '576141', 'group1/M00/00/13/rBBH51pAFIWADvcRAAK-Kb6cKEc667.jpg?image.jpg');
INSERT INTO `tz_user` VALUES ('72f0e57e463543e486fbbce19e627fe1', NULL, '520_932536', '0', 'd5a9a53f554d0c37ca218c47a9adc370', '13009250167', NULL, 'S5kAGRSAn', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-07 20:47:05', '2017-12-07 20:47:05', '2017-12-07 20:47:05', 1, NULL, 0, '741284', NULL);
INSERT INTO `tz_user` VALUES ('74e83089a7ea4533a28f4fa6f0bea485', '李宁', '520_369096', '0', 'ada4ee5f9e542c6681928e560e0d1984', '17369007502', '', 'Bs4EecueVZ', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 12:43:53', '2018-01-22 18:24:07', '2018-01-22 18:48:26', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '832124', NULL);
INSERT INTO `tz_user` VALUES ('74f0add8c7ad471980617bbf4709a23d', NULL, '520_742014', '0', '088e201b08a85067355249cec9a1b26e', '13953286637', NULL, '8RIbwOXhl9bVSep', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 17:44:35', '2017-12-03 12:11:23', '2017-11-25 17:44:35', 1, NULL, 0, '125256', NULL);
INSERT INTO `tz_user` VALUES ('79c0d1fc1eeb4f29a048c8aeb79f7de2', '谢馨升', '520_958916', '0', 'ab943bf2861361a7c32a8ed5a67e43ea', '13881018838', '', '4Jser8QsCc47m', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-22 16:57:50', '2018-01-22 16:57:50', '2018-01-22 18:43:24', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '324230', NULL);
INSERT INTO `tz_user` VALUES ('7bab8480e4414c1cb322b4383bf55285', NULL, '520_217481', '0', '620363e17d65a35b0e3d2abd8a0a4369', '13350865811', NULL, 't5umjc6EvrtL', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-28 19:26:02', '2017-12-05 16:58:18', '2017-11-28 19:26:02', 1, NULL, 0, '161798', NULL);
INSERT INTO `tz_user` VALUES ('7bee0bfe55ea439693c01c7271a83d0e', NULL, '520_582602', '0', '1a063a8e129689fdb0661e4af4ea82d8', '13666287722', NULL, 'bU4Zh7PW2', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 21:15:16', '2017-11-29 21:16:18', '2017-11-29 21:15:16', 1, NULL, 0, '366279', NULL);
INSERT INTO `tz_user` VALUES ('7d5bd2e611f342ec8921a18280b79c83', NULL, '520_663315', '0', '09077837304bdb9b1f4dd3779d8d8e45', '13190087770', NULL, 'xGGLYNs0QF', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-28 21:40:23', '2017-11-28 21:41:50', '2017-11-28 21:40:23', 1, NULL, 0, '089478', NULL);
INSERT INTO `tz_user` VALUES ('7e2d0a001b9d402187f8de8061d5caae', NULL, '520_467361', '0', '47fb1ddd94c954d47652c314206ce64d', '18910551166', NULL, 'TF6s3SdvYrho', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-07 17:15:51', '2017-12-07 17:15:51', '2017-12-07 17:15:51', 1, NULL, 0, '712754', NULL);
INSERT INTO `tz_user` VALUES ('80c4f4606b114bf6911f1da608c77093', '赵靖', '520_435448', '0', '2f3239aee6642991ce3ba6dd30f01241', '13980613713', '', 'gl5NdMCf8S', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-22 18:00:13', '2018-01-22 18:00:13', '2018-01-22 18:41:09', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '980776', NULL);
INSERT INTO `tz_user` VALUES ('84b12c435a1843b5a2916cce1ccb73fc', NULL, '520_726093', '0', 'dbef661ec8747a0557890a925451d9bb', '15010024432', NULL, '20FhmWCb5z', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 14:19:37', '2017-11-29 14:19:37', '2017-11-29 14:19:37', 1, NULL, 0, '428338', NULL);
INSERT INTO `tz_user` VALUES ('8540ba3ae8684646b970d6762fe6bab3', '严强', '520_621793', '0', '7b2d66161ae43bfa903d92546a84122d', '17502837094', '', '0arIp2GCGzwN6', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-14 14:20:28', '2017-12-14 14:22:56', '2017-12-14 14:48:44', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '047635', NULL);
INSERT INTO `tz_user` VALUES ('88b6ecc45dcf44a2a2d1d8072e1d1832', NULL, '520_593932', '0', '5d27935f0eece41c8642e7c3b470c246', '13388184403', NULL, 'fATN01LE84CYmSz', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 14:36:17', '2018-01-13 20:40:34', '2017-11-25 19:26:35', 1, NULL, 0, '870437', 'group1/M00/00/10/rBBH51oZU2uAeqybAAANcmJCSLU340.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('92183126447f446ca783f3f19e144198', '肖总', '520_619226', '0', '5280a67e85cef67780301c432d0dc4f3', '15508186677', '', 'Q8ZvN2QtvfO8NKw', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 14:01:10', '2018-01-10 16:27:44', '2017-11-27 10:08:22', 1, '001', 0, '888888', 'group1/M00/00/10/rBBH51obc5aAPh0bAAANPzi3zCE406.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('9307cb848b7c49629fdd4f203a95b482', '杨军', '520_843416', '0', 'e21f7c6944a452d8cb91f4016336dc39', '18284231163', '', 'i0cCRvE6frHxc', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-22 16:50:30', '2018-01-29 12:19:51', '2018-01-22 18:44:27', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '255175', NULL);
INSERT INTO `tz_user` VALUES ('9a152751b3004e1b94cc702e8e857edb', NULL, '520_756358', '0', '2122eeb55d759763cd0c6a398adad194', '18380458770', NULL, 'AKqg7rxxFD', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-27 15:36:21', '2017-11-27 15:36:21', '2017-11-27 15:36:21', 1, NULL, 0, '460125', NULL);
INSERT INTO `tz_user` VALUES ('9b78d521f7e34132b7dee46fc3093d42', NULL, '520_547923', '0', '56c7e141abaa7d31291e573df062e9f9', '18792526490', NULL, 'PTx2k42PcXzXc4b', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 15:14:02', '2017-11-29 15:14:02', '2017-11-29 15:14:02', 1, NULL, 0, '741629', NULL);
INSERT INTO `tz_user` VALUES ('9b923977107645eca7703e3c3d26d455', '陈菀玉', '520_556626', '0', 'a0ecda071b6ab3565ea69ef42629c793', '18512813646', '', 'B63Oj6Pd5', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 13:59:12', '2018-01-22 10:36:09', '2018-01-22 10:36:16', 1, '001', 0, '535772', 'group1/M00/00/14/rBBH51plTiCACvOaAABXhrvfFwc244.jpg?20180122103616.jpg');
INSERT INTO `tz_user` VALUES ('9ef7d2d05dfa457ab534eca26e9aa5e9', NULL, '520_535258', '0', 'fc74df56fcf2722fb17bb9f1f786f46d', '13925502374', NULL, 'TRmEDiLTfblJk', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-20 11:43:06', '2017-12-20 11:43:06', '2017-12-20 11:43:06', 1, NULL, 0, '687124', NULL);
INSERT INTO `tz_user` VALUES ('a0ad284f4e474378bc88a5b634be6927', 'cwy_02', '520_206318', '0', '18e7be1e896ab9d9063a5b02c5460c8b', '13547910874', '', 'TKGBiUgX5xHi', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-25 16:48:19', '2017-12-25 16:48:19', '2017-12-26 17:30:25', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '398392', NULL);
INSERT INTO `tz_user` VALUES ('a368b9051f2647bfa2964f3e385bbcdd', '徐总', '520_659778', '0', '0b9d36ecfd8952b11d0e4ec8c5aa8c80', '13550669886', '', 'XrHgF5XCm7cDkDSf', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 19:17:26', '2018-01-22 16:09:55', '2018-01-22 16:49:37', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '374903', 'group1/M00/00/14/rBBH51plpaGAOlXzAABWRhAjQQU048.jpg?20180122164937.jpg');
INSERT INTO `tz_user` VALUES ('a521d908027d4ff8b4d3b93ec588b931', NULL, '520_192540', '0', '768d2bfb74b3faad7ed9ede73bf0ab47', '17780581383', NULL, '0gtCFhBArr7Ywt15', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 14:14:08', '2017-11-29 14:16:26', '2017-11-29 14:14:08', 1, NULL, 0, '162134', NULL);
INSERT INTO `tz_user` VALUES ('a6fbf7e6cdfb4d45a241a581a06b5731', NULL, '520_028207', '0', '428298488b0ace7de045e52e6f40564a', '18398369370', NULL, 'WuRe3Cii', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-10 11:39:36', '2018-01-10 11:39:36', '2018-01-10 11:39:36', 1, NULL, 0, '375551', NULL);
INSERT INTO `tz_user` VALUES ('a99fc3742f224baa88027db7e4d50e27', NULL, '520_784238', '0', 'c4905e430d714596e5043c3c2b564f1f', '18515442555', NULL, '2YmmVFVgaubCGV', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 17:00:51', '2017-11-29 17:00:51', '2017-11-29 17:00:51', 1, NULL, 0, '403138', NULL);
INSERT INTO `tz_user` VALUES ('ae0989bd15b544a2bbea1d463c6f5171', '何孝成', '520_034299', '0', '7a2263c267dd17f5886f2e5df7aa9764', '18581896606', '', 'xTOIPm2fnhEoVdx', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-22 16:57:57', '2018-01-22 16:57:57', '2018-01-22 18:32:05', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '782271', NULL);
INSERT INTO `tz_user` VALUES ('aed8e1c777764a649bf355258512a3e4', NULL, '520_357057', '0', '33072efe82dd4cabe9e6e1c4a894e593', '18982108219', NULL, 'Twpul0wNXK0JV6', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-10 17:51:46', '2017-12-10 17:51:46', '2017-12-10 17:51:46', 1, NULL, 0, '742575', NULL);
INSERT INTO `tz_user` VALUES ('b13bd70b6f40487c86e2f99641cf4719', '刘玉凤', '520_894634', '0', '4eda54bbb012abc107c3267938ffe8ee', '18215589770', '', 'WuSxTXFuPue', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 14:17:06', '2017-11-25 14:17:06', '2017-11-25 20:09:41', 1, '001', 0, '213530', 'group1/M00/00/10/rBBH51oZXYWAP_49AAAJ1r8Yy7o018.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('b17ffac68a0c43ccbf87437914d37ac5', NULL, '520_762732', '0', 'c7c5db8f5c8fa79ba61ec682244ab79d', '13980584295', NULL, '5qYyyc1Zz4XE6X9t', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-04-12 14:50:39', '2018-04-12 14:50:39', '2018-04-12 14:50:39', 1, NULL, 0, '177506', NULL);
INSERT INTO `tz_user` VALUES ('b2572ce53c23461ea802900a4813fd39', '蒙林', '520_547415', '0', 'c84e34b98a9a8501f60b7788182636b2', '18113018416', '', 'n28EWPrSvo1', 'group1/M00/00/12/rBBH51oqRaGAb0ebAABRYGfm4DA465.jpg?e9fd16f8-fcf0-459b-901e-7ab9a7a3bd3b.jpg', 0, '2017-11-25 13:54:00', '2018-01-10 11:48:18', '2018-01-10 11:48:22', 1, '001', 0, '666666', 'group1/M00/00/13/rBBH51pVjQaAKTM6AABXs4hq6IQ086.jpg?20180110114822.jpg');
INSERT INTO `tz_user` VALUES ('b852902504194934a2be63c2f394d335', NULL, '520_511641', '0', '2861718fcba131fedfd90bbf3d4d742e', '13020759398', NULL, 'bl9hSxKu0311EvL', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-07 16:58:38', '2017-12-07 17:07:52', '2017-12-07 16:58:38', 1, NULL, 0, '966126', NULL);
INSERT INTO `tz_user` VALUES ('b9b1eb84e5624caeb71f621b445f023f', NULL, '520_479736', '0', 'f3ab435553fc9fd6be5abcdeb96649a2', '15208338178', NULL, 'lMkNZqI', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-22 20:15:31', '2017-12-28 12:26:37', '2017-12-22 20:15:31', 1, NULL, 0, '860222', NULL);
INSERT INTO `tz_user` VALUES ('bc51ff97b0e64e028548eff8fdfad989', NULL, '520_586629', '0', '427ae7d9919ee26d38b53de01e9cc6f9', '13801303986', NULL, 'ycsTomNxI', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 10:58:12', '2017-11-30 10:58:12', '2017-11-30 10:58:12', 1, NULL, 0, '363540', NULL);
INSERT INTO `tz_user` VALUES ('c1f27b1689bc43d6a597c711225e324c', '唐总', '峰哥', '0', 'fc147ad6fc9de39cc7dc3001d3159349', '15196666913', '', 'bYNz7J0xNyre', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 14:44:21', '2017-11-30 14:44:21', '2017-11-30 14:47:14', 1, '001', 0, '342060', NULL);
INSERT INTO `tz_user` VALUES ('c2320cd159c24b9196b6d7aa48374ff3', NULL, '520_632739', '0', 'ddd025e0fa19bd81b7456d398065b48f', '18016136544', NULL, 'fX9CJA7TVKBV', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-18 17:08:40', '2017-12-18 17:08:40', '2017-12-18 17:08:40', 1, NULL, 0, '481852', NULL);
INSERT INTO `tz_user` VALUES ('c33c373d414741cab5ab80f4ffff2b0a', NULL, '520_863175', '0', 'b88031440b0f24f3ac291ab7c86916ec', '18435974530', NULL, 'QuHTBC63uDDKutqM', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-06 23:21:59', '2017-12-06 23:22:29', '2017-12-06 23:21:59', 1, NULL, 0, '226735', NULL);
INSERT INTO `tz_user` VALUES ('c6ee5f0a26d44ec2a6dea86aa1d75d9c', NULL, '520_885126', '0', '18091c9d610c6860a32318b60f007bc0', '13981024806', NULL, 'H24tXdZzeQ', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-11 16:39:21', '2017-12-11 16:39:21', '2017-12-11 16:39:21', 1, NULL, 0, '330827', NULL);
INSERT INTO `tz_user` VALUES ('c727455e63b14077b0a6d752410aabb9', NULL, '520_092942', '0', '7e4f9f109a73d2b711dd4a7b09ffead3', '15766639086', NULL, '8NvjF1JG32N', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-28 11:04:15', '2017-12-28 11:04:15', '2017-12-28 11:04:15', 1, NULL, 0, '089309', NULL);
INSERT INTO `tz_user` VALUES ('c78c27faefef40178f3d2f05d14e592b', '钟敏', '520_971499', '1', '21f1dc0a1f16ab861d59b3a9d7baaac1', '13696198448', '', 'kJxHUxXRmHY8tZh', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-22 16:15:30', '2018-01-22 16:15:30', '2018-01-22 18:42:29', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '969808', NULL);
INSERT INTO `tz_user` VALUES ('cd8d6ebda4b74123b2b7b4a32b8b66d9', NULL, '520_514036', '0', 'cbf77fd1e9bb6019795080aef93907b7', '17360191765', NULL, '3xqeRjSu8A', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-29 10:07:38', '2017-11-29 10:07:38', '2017-11-29 10:07:38', 1, NULL, 0, '007881', NULL);
INSERT INTO `tz_user` VALUES ('cea6956605ee47d49852515a9854feff', NULL, '520_694039', '0', '09dcb09d2b96196e779b445c08276a84', '18681708088', NULL, 'qE489KzonmGc4u', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 14:36:34', '2017-11-30 14:36:34', '2017-11-30 14:36:34', 1, NULL, 0, '020168', NULL);
INSERT INTO `tz_user` VALUES ('ceb28fc631e64c528d7865658ac4a38d', NULL, '520_579148', '0', 'd3505fa90d02457c62c178e91e2abb96', '18780254177', NULL, 'fQJq009xzUQTs', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-09 13:53:34', '2018-01-09 13:53:34', '2018-01-09 13:53:34', 1, NULL, 0, '609950', NULL);
INSERT INTO `tz_user` VALUES ('cf1a6f3144744127a3c86d45252cdb91', '马振东', '520_575845', '0', 'c410d0a9457c8ad047bb08b20ca73ecb', '15808354848', '', '7hQmQYF', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-09 12:36:16', '2018-01-22 16:55:02', '2018-01-22 18:40:04', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '269160', NULL);
INSERT INTO `tz_user` VALUES ('dc4c2d899ed24ad491e28aca7dc27316', NULL, '520_729962', '0', 'e3d9f26089fade67e353007c1ced7392', '13550666358', NULL, 'uG6mAWM8k', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-14 14:56:01', '2017-12-14 14:58:32', '2017-12-14 14:56:01', 1, NULL, 0, '484679', NULL);
INSERT INTO `tz_user` VALUES ('dda37e48c83d47aaabb63fa21e7753f2', '雷帆', '520_007647', '0', '3c98cec363ca216ce8d8e3544600d30c', '15928838401', '', '6fqhnaI', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 14:25:22', '2017-11-25 14:25:22', '2017-11-25 14:28:26', 1, '001', 0, '685641', NULL);
INSERT INTO `tz_user` VALUES ('de83afa9bbd54278a16b8f6580d5f58f', '文婕', '文婕', '1', '8bdf5704a36b075c6fe1c005577648a2', '13910210505', '', 'u8TCV9iwkK', 'group1/M00/00/10/rBBH51oZCZiAer99AABDZxga7MQ082.png?upload1.png', 0, '2017-11-25 13:58:09', '2018-03-06 15:47:51', '2017-11-28 13:17:42', 1, '001', 0, '850354', 'group1/M00/00/11/rBBH51oc8XaAJn0WAAAN0ca0aIg834.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('df8ac616c5aa4332a32f53fda7139b9c', NULL, '520_420572', '0', '20c540c162665a80ba55085951a09ca1', '13438901549', NULL, 'pd9BkgPfklhUSWWB', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-04 20:40:45', '2017-12-04 20:43:23', '2017-12-04 20:40:45', 1, NULL, 0, '066657', NULL);
INSERT INTO `tz_user` VALUES ('e040813c1f8149b7a7a56a4ee5f2a0de', NULL, '520_924462', '0', '340c1a2388a9f60208156b0d3a1d5a19', '13450164309', NULL, 'pfmT4OtSW0pJY8', 'group1/M00/00/0F/rBBH51oY_NiAcZaGAAALPq6KRzM787.png?TIM图片20171125131702.png', 0, '2017-11-25 17:27:34', '2017-12-24 21:28:29', '2017-11-25 19:27:26', 1, NULL, 0, '149688', 'group1/M00/00/10/rBBH51oZU56AJ27rAAANg43yFH4138.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('e13e407d48f54bb38de52273eedbb28e', NULL, '520_224415', '0', '999e710af10141ef5c4782c50045812d', '13880880682', NULL, 'ZAkpsbEVni65Uto', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-09 12:38:00', '2018-01-09 12:38:00', '2018-01-09 12:38:00', 1, NULL, 0, '164559', NULL);
INSERT INTO `tz_user` VALUES ('e7196f6e94cc42f7ba293dabf1bc59a9', NULL, '520_035816', '0', 'c0d6ceab09ec81ad27b6d42e2ee34eef', '18784733131', NULL, '65vySl1Yv', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-11 11:14:18', '2017-12-11 11:14:51', '2017-12-11 11:14:18', 1, NULL, 0, '956898', NULL);
INSERT INTO `tz_user` VALUES ('e7d5293a218f45e3b6007e68eedeea86', NULL, '520_024176', '0', '3b727c4c240a71bb851a97d5dd6d16fe', '13890210388', NULL, 'a5QYYUzFii', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-12 19:57:07', '2017-12-12 19:57:07', '2017-12-12 19:57:07', 1, NULL, 0, '971944', NULL);
INSERT INTO `tz_user` VALUES ('e928b6e92c474e3d8a101f421fd39387', '赵敏', '520_985301', '1', 'b86a24a8bbaadbfa0834281d121815cd', '15181018218', '', 'eo6pJvQ67h', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-22 16:50:54', '2018-01-22 16:55:23', '2018-01-22 18:41:51', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '001590', NULL);
INSERT INTO `tz_user` VALUES ('ea7e627a8c2f4386b645731a8abf472f', NULL, '520_471015', '0', '3c1c074bc7892da987d303b1dc793e29', '13681646178', NULL, 'fn2OKsGcdhbDAAd3', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-01-13 20:46:29', '2018-01-13 20:46:29', '2018-01-13 20:46:29', 1, NULL, 0, '614868', NULL);
INSERT INTO `tz_user` VALUES ('ec63f7ce9280416691aaea449a7252d1', NULL, '520_780081', '0', '8796cd781a34e19acf2c32ba33d25862', '13886505568', NULL, '9xZfuYfiabDDgd', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2018-04-09 12:10:36', '2018-04-09 12:10:36', '2018-04-09 12:10:36', 1, NULL, 0, '896077', NULL);
INSERT INTO `tz_user` VALUES ('f1a88380463645bca982a9b626ef3c6a', NULL, '520_477860', '0', '0b3959ec597644ad96a82e759b82f6d8', '15094314768', NULL, 'Sa72mLy', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-18 09:03:57', '2017-12-18 09:09:49', '2017-12-18 09:09:52', 1, NULL, 0, '086313', NULL);
INSERT INTO `tz_user` VALUES ('f49dd0101441464a8a9b3dbbe224da75', NULL, '520_745870', '0', 'e5bc41564fa2b80dff8ee3b8ba2bb86b', '18161336290', NULL, 'kIoTlTGa53', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 14:37:06', '2018-04-07 11:05:21', '2017-11-30 14:37:06', 1, NULL, 0, '827307', NULL);
INSERT INTO `tz_user` VALUES ('f65fb8df88b84e4ca7e0e5569c8ecea9', '郝x翔', '520_925002', '0', '98191c79c4739238097042bd858e81da', '13550668358', '', 'tXVkjb4zR67wHi', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-12-14 14:42:56', '2018-01-22 16:52:52', '2018-01-22 18:38:28', 1, '96479df69b21468ca8a1e0ce754df5bb', 0, '626290', NULL);
INSERT INTO `tz_user` VALUES ('f7b95290d7be4d108f104ccc710a848f', NULL, '520_013972', '0', 'ea53517945850c2704695226c43e8017', '18201540909', NULL, '4vdxcOnJWRUo', 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', 0, '2017-11-30 14:38:35', '2017-11-30 14:41:44', '2017-11-30 14:38:35', 1, NULL, 0, '519386', NULL);
INSERT INTO `tz_user` VALUES ('fd3ee4ebc15346bfb52dfa7af1a4fbbd', '龚婷', '520_来了', '1', '5eac945c6d1c66a8918e1d31fa50f469', '13688390131', '', 'H9GvIeFl', 'group1/M00/00/13/rBBH51oqXleAJi3gAABGBsJLhw4287.jpg?c97ffdb0-fa4a-4c1b-b2f6-e940da11b34f.jpg', 0, '2017-11-25 14:18:26', '2017-12-18 17:14:47', '2017-12-08 17:41:43', 1, '001', 0, '352788', 'group1/M00/00/13/rBBH51oqXf6AFyfxAAHrpRiGOYk094.jpg?image.jpg');
INSERT INTO `tz_user` VALUES ('fdc70f24eb954d77b2a5f5f5559bcfed', '方瑞琪', '海绵宝宝', '1', 'abb475601dd5eff27423c31a66a5874f', '15708438605', '', 'U7EEm6Sg', 'group1/M00/00/10/rBBH51oZDr-AEfDWAABX54FVG54654.png?upload1.png', 0, '2017-11-25 14:24:55', '2017-11-25 19:29:36', '2017-11-25 19:29:45', 1, '001', 0, '219876', 'group1/M00/00/10/rBBH51oZVCmAaJ7yAAANqe6pm5E060.png?qrCode.png');
INSERT INTO `tz_user` VALUES ('ffdaac2511c8411fabd64c7acbf1534d', NULL, '520_194818', '0', 'b1f5be0ce11094d80da7382615bbc007', '18551789135', NULL, 'U6ep7G1R', 'group1/M00/00/13/rBBH51o4xgOAG0emAAA3pi3Bpbw979.jpg?db4ad758-eaed-4028-93a9-8126db7dc371.jpg', 0, '2017-12-19 15:55:23', '2017-12-19 15:56:48', '2017-12-19 15:55:47', 1, NULL, 0, '333990', NULL);

-- ----------------------------
-- Table structure for tz_user_city
-- ----------------------------
DROP TABLE IF EXISTS `tz_user_city`;
CREATE TABLE `tz_user_city`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `city_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市合伙人代理的城市id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户主键id',
  `status` int(11) NULL DEFAULT 1 COMMENT '状态 0：代理禁用 1：启动 ',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人id',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `type` int(11) NULL DEFAULT 1 COMMENT '与城市关联的类型 0 团队 1 城市合伙人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tz_user_love
-- ----------------------------
DROP TABLE IF EXISTS `tz_user_love`;
CREATE TABLE `tz_user_love`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员id',
  `love_total` int(11) NULL DEFAULT 0 COMMENT '总爱心值',
  `love_surplus` int(11) NULL DEFAULT 0 COMMENT '剩余爱心值',
  `status` int(11) NULL DEFAULT 1 COMMENT '状态 0 冻结 1 启动',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `last_updated_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次消费时间',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_user_love
-- ----------------------------
INSERT INTO `tz_user_love` VALUES ('06660d2cdd784c58bf0fdae01017108b', '8540ba3ae8684646b970d6762fe6bab3', 0, 0, 1, '2017-12-14 14:20:28', '2017-12-14 14:20:28', '2017-12-14 14:20:28', NULL);
INSERT INTO `tz_user_love` VALUES ('07b59914006d418eb5c7e3c36d7fff48', '6b01833621ec4daaa9804a5e28e7df43', 0, 0, 1, '2017-11-30 16:00:11', '2017-11-30 16:00:11', '2017-11-30 16:00:11', NULL);
INSERT INTO `tz_user_love` VALUES ('081bb26c6e814253a50e40c0b3415d8c', 'a99fc3742f224baa88027db7e4d50e27', 0, 0, 1, '2017-11-29 17:00:51', '2017-11-29 17:00:51', '2017-11-29 17:00:51', NULL);
INSERT INTO `tz_user_love` VALUES ('0c6093ff8b424e3690268e850131a79d', '7d5bd2e611f342ec8921a18280b79c83', 0, 0, 1, '2017-11-28 21:40:23', '2017-11-28 21:40:23', '2017-11-28 21:40:23', NULL);
INSERT INTO `tz_user_love` VALUES ('11057f3206bd477d820153e4f9a305e2', '062fa5c384ac4439bbeff8150e34246b', 0, 0, 1, '2017-11-27 15:13:22', '2017-11-27 15:13:22', '2017-11-27 15:13:22', NULL);
INSERT INTO `tz_user_love` VALUES ('117ccd30653b4ac1bf22afd6be205285', '88b6ecc45dcf44a2a2d1d8072e1d1832', 200, 0, 1, '2017-11-25 14:36:17', '2017-11-25 14:36:17', '2017-12-04 10:05:51', NULL);
INSERT INTO `tz_user_love` VALUES ('14dd41b94f4f4976902e69036f1348f8', 'e7d5293a218f45e3b6007e68eedeea86', 0, 0, 1, '2017-12-12 19:57:07', '2017-12-12 19:57:07', '2017-12-12 19:57:07', NULL);
INSERT INTO `tz_user_love` VALUES ('155ee94e064148c69664eeb6af636895', '5de9cdaf39c744dd9fed887b25cf3187', 0, 0, 1, '2018-01-22 17:04:41', '2018-01-22 17:04:41', '2018-01-22 17:04:41', NULL);
INSERT INTO `tz_user_love` VALUES ('15f7689dce8e4b598c8ac11e1f741c18', '591f524a04d94209974fafd6ffcb56d9', 0, 0, 1, '2018-04-03 23:24:36', '2018-04-03 23:24:36', '2018-04-03 23:24:36', NULL);
INSERT INTO `tz_user_love` VALUES ('18f353a64c0e4c58bab783bb03879415', '702c5245714743db84d306d41a83a4d0', 0, 0, 1, '2017-12-21 13:53:46', '2017-12-21 13:53:46', '2017-12-21 13:53:46', NULL);
INSERT INTO `tz_user_love` VALUES ('1990173ea2a049dc97dcdaf88b8aa4ec', '0ce2930756144b41b623497f376e8a34', 0, 0, 1, '2017-11-25 13:57:44', '2017-11-25 13:57:44', '2017-11-25 13:57:44', NULL);
INSERT INTO `tz_user_love` VALUES ('1bd17332a0ab4a2e8871142700d05d8a', '1572f25e56ae4f82908d0f45d9e4b61f', 0, 0, 1, '2017-11-29 11:13:50', '2017-11-29 11:13:50', '2017-11-29 11:13:50', NULL);
INSERT INTO `tz_user_love` VALUES ('1d5e6577157840f5899eb37b8c3264e8', '55bef243e1f646c7a2bd28ba15e42c96', 0, 0, 1, '2017-12-07 10:37:01', '2017-12-07 10:37:01', '2017-12-07 10:37:01', NULL);
INSERT INTO `tz_user_love` VALUES ('1e94d727f6294bbda74d1e2f59c673c4', '288d7a49141341ffac8be7ab1d799cc0', 0, 0, 1, '2017-11-30 11:05:41', '2017-11-30 11:05:41', '2017-11-30 11:05:41', NULL);
INSERT INTO `tz_user_love` VALUES ('1f2cd77032694daeba456222669127e9', 'b9b1eb84e5624caeb71f621b445f023f', 0, 0, 1, '2017-12-22 20:15:31', '2017-12-22 20:15:31', '2017-12-22 20:15:31', NULL);
INSERT INTO `tz_user_love` VALUES ('24a1730dbe474c7096c5fe42b12dd155', '7bab8480e4414c1cb322b4383bf55285', 0, 0, 1, '2017-11-28 19:26:02', '2017-11-28 19:26:02', '2017-11-28 19:26:02', NULL);
INSERT INTO `tz_user_love` VALUES ('25053983d3314ac8ac034f708b0e30d5', 'c78c27faefef40178f3d2f05d14e592b', 0, 0, 1, '2018-01-22 16:15:30', '2018-01-22 16:15:30', '2018-01-22 16:15:30', NULL);
INSERT INTO `tz_user_love` VALUES ('25f30081c5d24bee8e4da762de2f1e80', '72f0e57e463543e486fbbce19e627fe1', 0, 0, 1, '2017-12-07 20:47:05', '2017-12-07 20:47:05', '2017-12-07 20:47:05', NULL);
INSERT INTO `tz_user_love` VALUES ('2935ecf72ffc4e40a341044674ad2473', '2367648fdbe443a08d80b22ebfb079a2', 0, 0, 1, '2017-12-11 10:52:33', '2017-12-11 10:52:33', '2017-12-11 10:52:33', NULL);
INSERT INTO `tz_user_love` VALUES ('2a822c5040794939b86629e273cdcb12', '84b12c435a1843b5a2916cce1ccb73fc', 0, 0, 1, '2017-11-29 14:19:37', '2017-11-29 14:19:37', '2017-11-29 14:19:37', NULL);
INSERT INTO `tz_user_love` VALUES ('3095ccd4d4e84fc28db2503c05b5f512', '3fa7e9e592d74c93a65d44244cec5612', 0, 0, 1, '2017-11-25 14:25:30', '2017-11-25 14:25:30', '2017-11-25 14:25:30', NULL);
INSERT INTO `tz_user_love` VALUES ('3214017347224f7c8984cc7b8a251673', 'c33c373d414741cab5ab80f4ffff2b0a', 0, 0, 1, '2017-12-06 23:21:59', '2017-12-06 23:21:59', '2017-12-06 23:21:59', NULL);
INSERT INTO `tz_user_love` VALUES ('326b019a5ec249018d617395f508b071', 'ceb28fc631e64c528d7865658ac4a38d', 0, 0, 1, '2018-01-09 13:53:34', '2018-01-09 13:53:34', '2018-01-09 13:53:34', NULL);
INSERT INTO `tz_user_love` VALUES ('35de4244cda244299818acebe9b0028f', '74f0add8c7ad471980617bbf4709a23d', 0, 0, 1, '2017-11-25 17:44:35', '2017-11-25 17:44:35', '2017-11-25 17:44:35', NULL);
INSERT INTO `tz_user_love` VALUES ('3fc45c41e4a44ee389c79750655e8993', 'b852902504194934a2be63c2f394d335', 0, 0, 1, '2017-12-07 16:58:38', '2017-12-07 16:58:38', '2017-12-07 16:58:38', NULL);
INSERT INTO `tz_user_love` VALUES ('427f9895aa3348e18915e66c941784ff', '9a152751b3004e1b94cc702e8e857edb', 0, 0, 1, '2017-11-27 15:36:21', '2017-11-27 15:36:21', '2017-11-27 15:36:21', NULL);
INSERT INTO `tz_user_love` VALUES ('44b4f31bcd45417895ab1dd74d8c5024', 'e040813c1f8149b7a7a56a4ee5f2a0de', 100, 0, 1, '2017-11-25 17:27:34', '2017-11-25 17:27:34', '2017-11-25 17:27:34', NULL);
INSERT INTO `tz_user_love` VALUES ('44b6a18beb33436ca3111b7024172876', 'e928b6e92c474e3d8a101f421fd39387', 0, 0, 1, '2018-01-22 16:50:54', '2018-01-22 16:50:54', '2018-01-22 16:50:54', NULL);
INSERT INTO `tz_user_love` VALUES ('45f5097ce23447c3b7a513f7174479ff', '80c4f4606b114bf6911f1da608c77093', 0, 0, 1, '2018-01-22 18:00:13', '2018-01-22 18:00:13', '2018-01-22 18:00:13', NULL);
INSERT INTO `tz_user_love` VALUES ('4639d545c3ca410597ad4f8ef3dac3b2', '54fcb3a70d2143149899ad17bc01ae9e', 0, 0, 1, '2017-12-02 15:58:59', '2017-12-02 15:58:59', '2017-12-02 15:58:59', NULL);
INSERT INTO `tz_user_love` VALUES ('4d596134597f4f61b256391012ed4995', '49cab02d80a0412c88f7b437c19aa62d', 0, 0, 1, '2018-01-04 09:56:37', '2018-01-04 09:56:37', '2018-01-04 09:56:37', NULL);
INSERT INTO `tz_user_love` VALUES ('4f7426e05a54421a948e2ee5c76f0a5e', '0ef7fdf322934c55b0a804d561228328', 0, 0, 1, '2017-11-28 12:04:41', '2017-11-28 12:04:41', '2017-11-28 12:04:41', NULL);
INSERT INTO `tz_user_love` VALUES ('51c314e4d6a94af2b397fcabbcecda38', '593a622faa884a02a5996ac688d3b156', 0, 0, 1, '2017-11-25 17:33:02', '2017-11-25 17:33:02', '2017-11-25 17:33:02', NULL);
INSERT INTO `tz_user_love` VALUES ('5430c858e4c042fd852b54e80f064097', 'f7b95290d7be4d108f104ccc710a848f', 0, 0, 1, '2017-11-30 14:38:35', '2017-11-30 14:38:35', '2017-11-30 14:38:35', NULL);
INSERT INTO `tz_user_love` VALUES ('5ed0a1b060a24f77b736a60d99e435c6', '45a6024bf01546fda014453b07966d5d', 0, 0, 1, '2017-12-21 10:08:31', '2017-12-21 10:08:31', '2017-12-21 10:08:31', NULL);
INSERT INTO `tz_user_love` VALUES ('6303a90aa3314bfd8ab741b92685a61a', '79c0d1fc1eeb4f29a048c8aeb79f7de2', 0, 0, 1, '2018-01-22 16:57:51', '2018-01-22 16:57:51', '2018-01-22 16:57:51', NULL);
INSERT INTO `tz_user_love` VALUES ('63bd8d945f61467d9c537809b6738abb', '92183126447f446ca783f3f19e144198', 0, 0, 1, '2017-11-25 14:01:10', '2017-11-25 14:01:10', '2017-11-25 14:01:10', NULL);
INSERT INTO `tz_user_love` VALUES ('651f5427bef44a36920bda7e36551a5a', 'a368b9051f2647bfa2964f3e385bbcdd', 0, 0, 1, '2017-11-25 19:17:26', '2017-11-25 19:17:26', '2017-11-25 19:17:26', NULL);
INSERT INTO `tz_user_love` VALUES ('663cb1fe82d040fc9584aa7be6097aa5', 'ae0989bd15b544a2bbea1d463c6f5171', 0, 0, 1, '2018-01-22 16:57:57', '2018-01-22 16:57:57', '2018-01-22 16:57:57', NULL);
INSERT INTO `tz_user_love` VALUES ('665a0790066d45abb4f4a41a2b5fc9ff', '4e70c024ff4a48d4a3df99de9e93c029', 0, 0, 1, '2017-11-30 18:45:38', '2017-11-30 18:45:38', '2017-11-30 18:45:38', NULL);
INSERT INTO `tz_user_love` VALUES ('67d2d5c880964794b0fcf3a0a4fd4872', '3640cddab5394d01a3bc839599bcceb0', 0, 0, 1, '2017-11-25 14:25:13', '2017-11-25 14:25:13', '2017-11-25 14:25:13', NULL);
INSERT INTO `tz_user_love` VALUES ('688dcc04d2d34d08835620dde44d4c8c', '25f7a23d920f48cf89b3eae0e69d8df3', 0, 0, 1, '2017-11-25 19:33:10', '2017-11-25 19:33:10', '2017-11-25 19:33:10', NULL);
INSERT INTO `tz_user_love` VALUES ('69a5f73d2291498095b75d002df7676d', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 0, 0, 1, '2017-11-25 14:18:26', '2017-11-25 14:18:26', '2017-11-25 14:18:26', NULL);
INSERT INTO `tz_user_love` VALUES ('6cafec4076374e0fb611d8c6d2a77089', 'ec63f7ce9280416691aaea449a7252d1', 0, 0, 1, '2018-04-09 12:10:36', '2018-04-09 12:10:36', '2018-04-09 12:10:36', NULL);
INSERT INTO `tz_user_love` VALUES ('75b90457e3344c9d90277f940e1fdd92', 'cd8d6ebda4b74123b2b7b4a32b8b66d9', 0, 0, 1, '2017-11-29 10:07:38', '2017-11-29 10:07:38', '2017-11-29 10:07:38', NULL);
INSERT INTO `tz_user_love` VALUES ('75bd38cc47d14ac9a568a126a1f8ceff', '01c9b354e2b34a25a4a9a79c88d2b218', 0, 0, 1, '2017-12-18 09:06:51', '2017-12-18 09:06:51', '2017-12-18 09:06:51', NULL);
INSERT INTO `tz_user_love` VALUES ('75e3e37b3dad4333800ce339ccda7849', 'fdc70f24eb954d77b2a5f5f5559bcfed', 0, 0, 1, '2017-11-25 14:24:55', '2017-11-25 14:24:55', '2017-11-25 14:24:55', NULL);
INSERT INTO `tz_user_love` VALUES ('76b39f6aad074b21b343408e2514dbe4', '2b6467eeb24a485784ef32adcc8a9cd2', 0, 0, 1, '2017-11-29 16:56:08', '2017-11-29 16:56:08', '2017-11-29 16:56:08', NULL);
INSERT INTO `tz_user_love` VALUES ('77681136ba6b4698845eb899c78b0ef5', '9307cb848b7c49629fdd4f203a95b482', 0, 0, 1, '2018-01-22 16:50:30', '2018-01-22 16:50:30', '2018-01-22 16:50:30', NULL);
INSERT INTO `tz_user_love` VALUES ('7860a6be851743a38b1ca990dfaf250c', '9b923977107645eca7703e3c3d26d455', 300, 0, 1, '2017-11-25 13:59:12', '2017-11-25 13:59:12', '2017-11-30 12:33:40', NULL);
INSERT INTO `tz_user_love` VALUES ('7a7254e7460340b7b536ac24f973a13e', 'c2320cd159c24b9196b6d7aa48374ff3', 0, 0, 1, '2017-12-18 17:08:40', '2017-12-18 17:08:40', '2017-12-18 17:08:40', NULL);
INSERT INTO `tz_user_love` VALUES ('8082c3c1e11c4aa7940850e3ef93030f', '2dfa9c00d82b47c796620533e22e4db0', 0, 0, 1, '2017-12-07 10:34:47', '2017-12-07 10:34:47', '2017-12-07 10:34:47', NULL);
INSERT INTO `tz_user_love` VALUES ('80ff94d0292d4d9294c5f37b47c8f1f0', '509001af9def4daa8c638d79e9deb67d', 0, 0, 1, '2017-11-29 15:11:46', '2017-11-29 15:11:46', '2017-11-29 15:11:46', NULL);
INSERT INTO `tz_user_love` VALUES ('864b3d62fdb74bcc98b4cde7cdce20b0', 'b13bd70b6f40487c86e2f99641cf4719', 0, 0, 1, '2017-11-25 14:17:06', '2017-11-25 14:17:06', '2017-11-25 14:17:06', NULL);
INSERT INTO `tz_user_love` VALUES ('88e9765ee86b4a7fab159df31e7a30f5', 'b17ffac68a0c43ccbf87437914d37ac5', 0, 0, 1, '2018-04-12 14:50:39', '2018-04-12 14:50:39', '2018-04-12 14:50:39', NULL);
INSERT INTO `tz_user_love` VALUES ('8901832f541942f38641cc61831479c0', 'e7196f6e94cc42f7ba293dabf1bc59a9', 0, 0, 1, '2017-12-11 11:14:18', '2017-12-11 11:14:18', '2017-12-11 11:14:18', NULL);
INSERT INTO `tz_user_love` VALUES ('8a9d1eb488974ceaa5d204ba65b6912e', 'bc51ff97b0e64e028548eff8fdfad989', 0, 0, 1, '2017-11-30 10:58:12', '2017-11-30 10:58:12', '2017-11-30 10:58:12', NULL);
INSERT INTO `tz_user_love` VALUES ('8ce5dec15ecf486c8a17478e84dc69fa', '1433defc747644e280e942e2b67dcfd8', 0, 0, 1, '2017-11-25 17:00:04', '2017-11-25 17:00:04', '2017-11-25 17:00:04', NULL);
INSERT INTO `tz_user_love` VALUES ('8d9af2f6bd48478988d2f63a97592868', 'f1a88380463645bca982a9b626ef3c6a', 0, 0, 1, '2017-12-18 09:03:57', '2017-12-18 09:03:57', '2017-12-18 09:03:57', NULL);
INSERT INTO `tz_user_love` VALUES ('90c6ad34f5e34023a72dbc274a75a950', '3ae34de0ea984028a9a8cc72ba649a7d', 0, 0, 1, '2017-11-25 19:13:56', '2017-11-25 19:13:56', '2017-11-25 19:13:56', NULL);
INSERT INTO `tz_user_love` VALUES ('92f0e5de84874bdc9e8dfd985b06ab6d', 'aed8e1c777764a649bf355258512a3e4', 0, 0, 1, '2017-12-10 17:51:46', '2017-12-10 17:51:46', '2017-12-10 17:51:46', NULL);
INSERT INTO `tz_user_love` VALUES ('92f91c1be775422a8d965b84823d64b3', '43d9cfadf0ae441dac90101796db1203', 0, 0, 1, '2018-04-12 14:49:32', '2018-04-12 14:49:32', '2018-04-12 14:49:32', NULL);
INSERT INTO `tz_user_love` VALUES ('938d2bd0e36645d2a46ff760814d0ca1', '24f8c8dfa0fc4acb8d0a8bba7e1773d6', 100, 100, 1, '2017-11-25 19:13:32', '2017-11-25 19:13:32', '2017-11-25 19:13:32', NULL);
INSERT INTO `tz_user_love` VALUES ('93bda8a995b4465aa2d498ded5769062', '7e2d0a001b9d402187f8de8061d5caae', 0, 0, 1, '2017-12-07 17:15:51', '2017-12-07 17:15:51', '2017-12-07 17:15:51', NULL);
INSERT INTO `tz_user_love` VALUES ('943b305323a949f095ff6e4798d46ee7', '74e83089a7ea4533a28f4fa6f0bea485', 0, 0, 1, '2017-11-29 12:43:53', '2017-11-29 12:43:53', '2017-11-29 12:43:53', NULL);
INSERT INTO `tz_user_love` VALUES ('97096711f02248b69153937f8b0358be', '50d561e804fa42feb4e557aa069dbb94', 0, 0, 1, '2017-11-25 14:25:16', '2017-11-25 14:25:16', '2017-11-25 14:25:16', NULL);
INSERT INTO `tz_user_love` VALUES ('9a113ba0eea843f3b83dd524c34804cd', '9ef7d2d05dfa457ab534eca26e9aa5e9', 0, 0, 1, '2017-12-20 11:43:06', '2017-12-20 11:43:06', '2017-12-20 11:43:06', NULL);
INSERT INTO `tz_user_love` VALUES ('9a577bb340f54f9db95970844992922f', 'a0ad284f4e474378bc88a5b634be6927', 0, 0, 1, '2017-12-25 16:48:19', '2017-12-25 16:48:19', '2017-12-25 16:48:19', NULL);
INSERT INTO `tz_user_love` VALUES ('9ec8f362348342659d1e176c39a2631e', 'cea6956605ee47d49852515a9854feff', 0, 0, 1, '2017-11-30 14:36:34', '2017-11-30 14:36:34', '2017-11-30 14:36:34', NULL);
INSERT INTO `tz_user_love` VALUES ('a0244d0c43fb4a5c88a9e9174e867c6c', 'c727455e63b14077b0a6d752410aabb9', 0, 0, 1, '2017-12-28 11:04:15', '2017-12-28 11:04:15', '2017-12-28 11:04:15', NULL);
INSERT INTO `tz_user_love` VALUES ('a083629278874b1b8207da04eea729b7', '12d4ab2da7394354996db664828c7242', 0, 0, 1, '2017-11-25 14:25:01', '2017-11-25 14:25:01', '2017-11-25 14:25:01', NULL);
INSERT INTO `tz_user_love` VALUES ('a380246876414ceead8880d3eab358ae', 'dda37e48c83d47aaabb63fa21e7753f2', 0, 0, 1, '2017-11-25 14:25:22', '2017-11-25 14:25:22', '2017-11-25 14:25:22', NULL);
INSERT INTO `tz_user_love` VALUES ('aa566f8ef7f74d899af5ac502983a596', '6de0009478834cbdbd81dcd557409d5e', 0, 0, 1, '2017-11-30 10:46:50', '2017-11-30 10:46:50', '2017-11-30 10:46:50', NULL);
INSERT INTO `tz_user_love` VALUES ('ab16791691464541bd0c8b344fc73434', '01432206572d4746829e4ea40f42fba3', 0, 0, 1, '2017-12-18 17:17:51', '2017-12-18 17:17:51', '2017-12-18 17:17:51', NULL);
INSERT INTO `tz_user_love` VALUES ('ac0ea98414c6473b8804b28dd1f46085', '585dcde9e7144f22ba07e22a81abc288', 0, 0, 1, '2018-04-10 17:46:26', '2018-04-10 17:46:26', '2018-04-10 17:46:26', NULL);
INSERT INTO `tz_user_love` VALUES ('ac93f09b35fb463495d8663b0e720679', '00e3168425b946c6b630d8ebdcd028f9', 0, 0, 1, '2017-11-25 17:00:04', '2017-11-25 17:00:04', '2017-11-25 17:00:04', NULL);
INSERT INTO `tz_user_love` VALUES ('acbef84ecfc743cc817a9a6331eb308c', '48fb416505854b27b788953e8aee9b34', 0, 0, 1, '2017-11-29 09:44:18', '2017-11-29 09:44:18', '2017-11-29 09:44:18', NULL);
INSERT INTO `tz_user_love` VALUES ('ae7765aeee9f412e9efead813bd35496', '1dc29c0808cf4e86bca3951e5a26dec8', 0, 0, 1, '2017-11-25 15:57:14', '2017-11-25 15:57:14', '2017-11-25 15:57:14', NULL);
INSERT INTO `tz_user_love` VALUES ('af36ab5c81014e0b8ae788e2b1d3c8c9', 'f65fb8df88b84e4ca7e0e5569c8ecea9', 0, 0, 1, '2017-12-14 14:42:56', '2017-12-14 14:42:56', '2017-12-14 14:42:56', NULL);
INSERT INTO `tz_user_love` VALUES ('b12028af6da0479ba543e7c80fbac9a6', '2fd496edc2d243b288e27f5f12538856', 0, 0, 1, '2017-11-25 16:59:05', '2017-11-25 16:59:05', '2017-11-25 16:59:05', NULL);
INSERT INTO `tz_user_love` VALUES ('b4584cf1a6c74965809fe53768d75249', '46f04a3da4874ce88713e68d8cc96092', 0, 0, 1, '2018-01-16 13:27:40', '2018-01-16 13:27:40', '2018-01-16 13:27:40', NULL);
INSERT INTO `tz_user_love` VALUES ('b666d879af4c4722824b8c82f2ae437a', 'a6fbf7e6cdfb4d45a241a581a06b5731', 0, 0, 1, '2018-01-10 11:39:36', '2018-01-10 11:39:36', '2018-01-10 11:39:36', NULL);
INSERT INTO `tz_user_love` VALUES ('ba0d0626d36d4b7f981354cc47219168', 'e13e407d48f54bb38de52273eedbb28e', 0, 0, 1, '2018-01-09 12:38:00', '2018-01-09 12:38:00', '2018-01-09 12:38:00', NULL);
INSERT INTO `tz_user_love` VALUES ('bc72eafcd1b04cc580f25ac1c44508e8', 'ea7e627a8c2f4386b645731a8abf472f', 0, 0, 1, '2018-01-13 20:46:29', '2018-01-13 20:46:29', '2018-01-13 20:46:29', NULL);
INSERT INTO `tz_user_love` VALUES ('bc83c806f61044cfba75683e6f1113bb', 'f49dd0101441464a8a9b3dbbe224da75', 0, 0, 1, '2017-11-30 14:37:06', '2017-11-30 14:37:06', '2017-11-30 14:37:06', NULL);
INSERT INTO `tz_user_love` VALUES ('bd01d4103c5641a38c3e0569f2ac4990', '07e7ba64169a43b9b9889db66d9b4c1f', 0, 0, 1, '2017-11-27 11:53:44', '2017-11-27 11:53:44', '2017-11-27 11:53:44', NULL);
INSERT INTO `tz_user_love` VALUES ('bd7767a014c54a0ea200b42dc5433371', 'b2572ce53c23461ea802900a4813fd39', 200, 200, 1, '2017-11-25 13:54:00', '2017-11-25 13:54:00', '2017-11-25 13:54:00', NULL);
INSERT INTO `tz_user_love` VALUES ('bd917281e6c44b1fabea6e349b3db5f1', 'a521d908027d4ff8b4d3b93ec588b931', 0, 0, 1, '2017-11-29 14:14:08', '2017-11-29 14:14:08', '2017-11-29 14:14:08', NULL);
INSERT INTO `tz_user_love` VALUES ('bfe11b3e2126483993e2edc5fad1a649', 'ffdaac2511c8411fabd64c7acbf1534d', 0, 0, 1, '2017-12-19 15:55:23', '2017-12-19 15:55:23', '2017-12-19 15:55:23', NULL);
INSERT INTO `tz_user_love` VALUES ('c1c75b4a447f45528654026f26371087', '59313dae44844f3d819e960ecfad605a', 0, 0, 1, '2017-12-11 16:41:18', '2017-12-11 16:41:18', '2017-12-11 16:41:18', NULL);
INSERT INTO `tz_user_love` VALUES ('cdc19cf27cd344cb8dcb8bb667e9a7ff', '3877878856b74d2c8e276945a392a44b', 0, 0, 1, '2018-01-04 10:46:50', '2018-01-04 10:46:50', '2018-01-04 10:46:50', NULL);
INSERT INTO `tz_user_love` VALUES ('d484d2e7c8544d1599c6fd3dd6c08e7f', '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, 1, '2017-11-25 14:25:20', '2017-11-25 14:25:20', '2017-11-25 14:25:20', NULL);
INSERT INTO `tz_user_love` VALUES ('d530b93791f645cf9461eb45421cbafa', '375c33c29d1442449acdf17deb5620a9', 0, 0, 1, '2017-12-13 13:02:39', '2017-12-13 13:02:39', '2017-12-13 13:02:39', NULL);
INSERT INTO `tz_user_love` VALUES ('d6bce74bcc2f4902869c4312c5c1a999', 'de83afa9bbd54278a16b8f6580d5f58f', 100, 100, 1, '2017-11-25 13:58:09', '2017-11-25 13:58:09', '2017-11-30 15:08:41', NULL);
INSERT INTO `tz_user_love` VALUES ('dc2ed708bf754bb7858f2b260441da91', '25849a752e5c402786bd5fdf0e32db8a', 0, 0, 1, '2017-12-13 12:52:05', '2017-12-13 12:52:05', '2017-12-13 12:52:05', NULL);
INSERT INTO `tz_user_love` VALUES ('e090bb967ea04a3b9e1c6c979d44a41c', 'cf1a6f3144744127a3c86d45252cdb91', 0, 0, 1, '2018-01-09 12:36:16', '2018-01-09 12:36:16', '2018-01-09 12:36:16', NULL);
INSERT INTO `tz_user_love` VALUES ('e3e7319f0bc440f280746fb642c8e6b7', '409be31c618c4c14b33c612c94dc80c1', 0, 0, 1, '2017-11-28 13:26:43', '2017-11-28 13:26:43', '2017-11-28 13:26:43', NULL);
INSERT INTO `tz_user_love` VALUES ('e4a60494d2fa4afdb957b8c1e1e4175a', '65f80d5cab6a4aa484b7d6f5b08a9eac', 0, 0, 1, '2017-11-30 17:11:54', '2017-11-30 17:11:54', '2017-11-30 17:11:54', NULL);
INSERT INTO `tz_user_love` VALUES ('ea19bd398821468f8850f1ece1eb8dcd', '6e12cc0c8d6e482f8395e224a982e853', 0, 0, 1, '2017-11-27 12:11:43', '2017-11-27 12:11:43', '2017-11-27 12:11:43', NULL);
INSERT INTO `tz_user_love` VALUES ('eb7a9e90ab0c42eab5f27f8f60c7e1cc', '618e1fa97918493b9c61d029cef10fe2', 0, 0, 1, '2017-12-12 11:56:23', '2017-12-12 11:56:23', '2017-12-12 11:56:23', NULL);
INSERT INTO `tz_user_love` VALUES ('ed127fe836d64ca5b3832673e25b13c3', 'c6ee5f0a26d44ec2a6dea86aa1d75d9c', 0, 0, 1, '2017-12-11 16:39:21', '2017-12-11 16:39:21', '2017-12-11 16:39:21', NULL);
INSERT INTO `tz_user_love` VALUES ('eff929b2968a40328919c00406e1a2c4', 'dc4c2d899ed24ad491e28aca7dc27316', 0, 0, 1, '2017-12-14 14:56:01', '2017-12-14 14:56:01', '2017-12-14 14:56:01', NULL);
INSERT INTO `tz_user_love` VALUES ('f28800ac108e4dfb8563aa7b1bf75855', '9b78d521f7e34132b7dee46fc3093d42', 0, 0, 1, '2017-11-29 15:14:02', '2017-11-29 15:14:02', '2017-11-29 15:14:02', NULL);
INSERT INTO `tz_user_love` VALUES ('f70ce98fcda94603a2b321a1a8446455', 'c1f27b1689bc43d6a597c711225e324c', 0, 0, 1, '2017-11-30 14:44:21', '2017-11-30 14:44:21', '2017-11-30 14:44:21', NULL);
INSERT INTO `tz_user_love` VALUES ('f893a4233e434e62b9a6f1832c43a741', '1a048c6799024e54aa29a962a545020b', 0, 0, 1, '2017-11-27 09:18:02', '2017-11-27 09:18:02', '2017-11-27 09:18:02', NULL);
INSERT INTO `tz_user_love` VALUES ('f93f2834be1f400e9497ee70a235125d', '5092f5f88686448287cd5ce587fa9e32', 0, 0, 1, '2017-12-04 20:54:17', '2017-12-04 20:54:17', '2017-12-04 20:54:17', NULL);
INSERT INTO `tz_user_love` VALUES ('f9f95f40e7f447c3aa1cb241c6137f02', 'df8ac616c5aa4332a32f53fda7139b9c', 0, 0, 1, '2017-12-04 20:40:45', '2017-12-04 20:40:45', '2017-12-04 20:40:45', NULL);
INSERT INTO `tz_user_love` VALUES ('fbf795cb5ed84239b2ff4ab5ad1b0e5f', '2a0aa340ca924385af2606420fad5737', 0, 0, 1, '2017-11-29 13:15:29', '2017-11-29 13:15:29', '2017-11-29 13:15:29', NULL);
INSERT INTO `tz_user_love` VALUES ('fdeeb90f61234156b5535b2e9f13a3de', '7bee0bfe55ea439693c01c7271a83d0e', 0, 0, 1, '2017-11-29 21:15:16', '2017-11-29 21:15:16', '2017-11-29 21:15:16', NULL);
INSERT INTO `tz_user_love` VALUES ('ff895b14010148e2a1bb6ffe58dda26d', '46c4472aa6434c8f95f0fab15b81bb53', 0, 0, 1, '2017-11-27 10:59:39', '2017-11-27 10:59:39', '2017-11-27 10:59:39', NULL);

-- ----------------------------
-- Table structure for tz_user_love_consumption_details
-- ----------------------------
DROP TABLE IF EXISTS `tz_user_love_consumption_details`;
CREATE TABLE `tz_user_love_consumption_details`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `type` int(11) NULL DEFAULT 0 COMMENT '消费类型 ( 0:提现 1:购物 3:秒到 4:推荐 )',
  `consume_val` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '消费爱心值',
  `show_type` int(11) NULL DEFAULT 2 COMMENT '提现类型 （0 ：支付宝 1：微信）',
  `show_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提现账号',
  `order_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '消费时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态 0:支出，1：收入',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称介绍',
  `fee` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '手续费',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_user_love_consumption_details
-- ----------------------------
INSERT INTO `tz_user_love_consumption_details` VALUES ('001', 'e040813c1f8149b7a7a56a4ee5f2a0de', 4, '+100', 2, NULL, NULL, '2017-11-25 20:21:48', '2017-11-25 20:21:51', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('002', '24f8c8dfa0fc4acb8d0a8bba7e1773d6', 4, '+100', 2, NULL, NULL, '2017-11-25 20:24:14', '2017-11-25 20:24:20', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('003', 'b2572ce53c23461ea802900a4813fd39', 4, '+100', 2, NULL, NULL, '2017-11-25 20:37:52', '2017-11-25 20:37:55', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('06844472232645e38beaadeffaee4d6b', '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, '-99', 0, NULL, NULL, '2017-11-29 10:28:38', '2017-11-29 10:28:38', NULL, 0, '支付宝爱心值提取！', '1');
INSERT INTO `tz_user_love_consumption_details` VALUES ('08668ece3b434b949ed1f3257905f29a', '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, '-99', 0, '13388184403', NULL, '2017-12-06 12:46:35', '2017-12-06 12:46:35', NULL, 0, '支付宝爱心值提取！', '1');
INSERT INTO `tz_user_love_consumption_details` VALUES ('169443c6f21b451691091c62e2b3f7ce', '88b6ecc45dcf44a2a2d1d8072e1d1832', 4, '+100', 2, NULL, '202f62ff3aab430f80f8cb0f5ac4175e', '2017-11-28 13:33:11', '2017-11-28 13:33:11', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('1c09beb4707c44faa5ad255954e68327', '9b923977107645eca7703e3c3d26d455', 4, '+100', 2, NULL, '4ec9d6add713407399a356241a2ab68d', '2017-11-29 17:14:38', '2017-11-29 17:14:38', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('1cd68a45e9c8487494779dd1607e2936', '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, '-1', 0, '13388184403', NULL, '2017-12-06 12:46:35', '2017-12-06 12:46:35', NULL, 0, '提取爱心值手续费', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('20aa892b55974933b5bdfbaccc45c8fc', 'de83afa9bbd54278a16b8f6580d5f58f', 4, '+100', 2, NULL, 'a9c9aeb6a0c34a6c9a7cd64db910d74f', '2017-11-30 15:08:41', '2017-11-30 15:08:41', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('26a4efc24a2a4fc58cff2d3beb61df69', '9b923977107645eca7703e3c3d26d455', 4, '+100', 2, NULL, '9e94111cdcbd4d1f81737c633d1d6692', '2017-11-30 12:33:40', '2017-11-30 12:33:40', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('2a5dceaeb15d4f009cf5a5f5d39dfc08', '9b923977107645eca7703e3c3d26d455', 0, '-1', 0, NULL, NULL, '2017-11-28 09:38:51', '2017-11-28 09:38:51', NULL, 0, '提取爱心值手续费', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('329d3f6182184feb9bb846c911146adf', 'e040813c1f8149b7a7a56a4ee5f2a0de', 0, '-99', 0, '13450164309', NULL, '2017-12-24 21:34:59', '2017-12-24 21:34:59', NULL, 0, '支付宝爱心值提取！', '1');
INSERT INTO `tz_user_love_consumption_details` VALUES ('484c59bf941f4f04a143ac68423e6906', 'e040813c1f8149b7a7a56a4ee5f2a0de', 0, '-1', 0, '13450164309', NULL, '2017-12-24 21:34:59', '2017-12-24 21:34:59', NULL, 0, '提取爱心值手续费', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('53a015c7cc8f4601ad9a28714fff6a86', '9b923977107645eca7703e3c3d26d455', 4, '+100', 2, NULL, 'a03681ac9a634b838e8df25a2bd06b7c', '2017-11-27 15:44:48', '2017-11-27 15:44:48', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('877fa08feee1458fa071d5da55554893', '88b6ecc45dcf44a2a2d1d8072e1d1832', 4, '+100', 2, NULL, '2153e6d947914ff3bdd1e474902c210e', '2017-12-04 10:05:51', '2017-12-04 10:05:51', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('8e03123247484d3298d086f562212f26', '9b923977107645eca7703e3c3d26d455', 0, '-99', 0, NULL, NULL, '2017-11-28 09:38:51', '2017-11-28 09:38:51', NULL, 0, '支付宝爱心值提取！', '1');
INSERT INTO `tz_user_love_consumption_details` VALUES ('91ce04549d2644fc811cda6ff178b05a', 'b2572ce53c23461ea802900a4813fd39', 4, '+100', 2, NULL, 'e5287e6ef3f44be985f1d492f939ed75', '2017-11-25 19:23:16', '2017-11-25 19:23:16', NULL, 0, '520商城推荐用户升级会员！', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('a4571df7a48a449ba9f16031006a6681', '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, '-1', 0, NULL, NULL, '2017-11-29 10:28:38', '2017-11-29 10:28:38', NULL, 0, '提取爱心值手续费', '0');
INSERT INTO `tz_user_love_consumption_details` VALUES ('a55225c129c2451a8dfd2cb8b62887a7', '9b923977107645eca7703e3c3d26d455', 0, '-199', 0, NULL, NULL, '2017-11-30 12:34:46', '2017-11-30 12:34:46', NULL, 0, '支付宝爱心值提取！', '1');
INSERT INTO `tz_user_love_consumption_details` VALUES ('e2df369954404e089b02be2f7c1892ab', '9b923977107645eca7703e3c3d26d455', 0, '-1', 0, NULL, NULL, '2017-11-30 12:34:46', '2017-11-30 12:34:46', NULL, 0, '提取爱心值手续费', '0');

-- ----------------------------
-- Table structure for tz_user_love_show
-- ----------------------------
DROP TABLE IF EXISTS `tz_user_love_show`;
CREATE TABLE `tz_user_love_show`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员id',
  `type` int(11) NULL DEFAULT 0 COMMENT '提现类型  0 ：支付宝  1 ：微信',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态 ： 0 :提现中 1 ： 提现完成',
  `consume_val` int(11) NULL DEFAULT 0 COMMENT '提现金额',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提现账号',
  `payee_real_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款用户真实姓名',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '提现时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `account_time` datetime(0) NULL COMMENT '到账时间',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_user_love_show
-- ----------------------------
INSERT INTO `tz_user_love_show` VALUES ('19b946e55aec40ccaa076184d9da5b24', 'e040813c1f8149b7a7a56a4ee5f2a0de', 0, 1, 100, '13450164309', '杨燕', '2017-12-24 21:34:58', '2017-12-24 21:34:58', '2017-12-24 21:34:59', NULL);
INSERT INTO `tz_user_love_show` VALUES ('61f3f39674e542c3a04e6138a649bab8', '9b923977107645eca7703e3c3d26d455', 0, 1, 200, '18380458770', '陈菀玉', '2017-11-30 12:34:46', '2017-11-30 12:34:46', '2017-11-30 12:34:46', NULL);
INSERT INTO `tz_user_love_show` VALUES ('651b12a0f2bb4fbfa32ee6b0719c2c7c', '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, 1, 100, '13388184403', '曾明强', '2017-12-06 12:46:34', '2017-12-06 12:46:34', '2017-12-06 12:46:35', NULL);
INSERT INTO `tz_user_love_show` VALUES ('a696ccd7174a46cc9f55d9f90e6630ba', '88b6ecc45dcf44a2a2d1d8072e1d1832', 0, 1, 100, '13388184403', '曾明强', '2017-11-29 10:28:37', '2017-11-29 10:28:37', '2017-11-29 10:28:38', NULL);
INSERT INTO `tz_user_love_show` VALUES ('e4f46fdc50434c7a8b53279f484210a0', '9b923977107645eca7703e3c3d26d455', 0, 1, 100, '18380458770', '陈菀玉', '2017-11-28 09:38:50', '2017-11-28 09:38:50', '2017-11-28 09:38:51', NULL);

-- ----------------------------
-- Table structure for tz_user_mall
-- ----------------------------
DROP TABLE IF EXISTS `tz_user_mall`;
CREATE TABLE `tz_user_mall`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `mall_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '在哪个购物平台注册 平台id',
  `pay_way` int(2) NULL DEFAULT 0 COMMENT '付款的方式 1 支付宝 2 微信 3 信用卡，4其他付款方式 作为返钱方式',
  `pay_account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付账户信息',
  `price` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '会员升级缴费金额单位为：分',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '会员绑定时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '会员修改时间',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该记录操作人id',
  `type` int(11) NULL DEFAULT 0 COMMENT '用户类型：0 ：普通用户  1： 会员 2：爱心会员 3：爱心合伙人 4：城市合伙人',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '会员开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '会员结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '各个购物平台绑定会员关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_user_mall
-- ----------------------------
INSERT INTO `tz_user_mall` VALUES ('00fcedde74ab4da5bd4c366ffdac38b9', '01432206572d4746829e4ea40f42fba3', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-18 17:17:51', '2017-12-18 17:17:51', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('052ae1f8d52b468fa7244e602310319b', 'f7b95290d7be4d108f104ccc710a848f', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-30 14:38:35', '2017-11-30 14:38:35', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('081433dfe20848068e019ed1ca5f3690', '375c33c29d1442449acdf17deb5620a9', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-13 13:02:39', '2017-12-13 13:02:39', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('0b2a17e0f4184dbfbe24c52a3bf7196b', '3877878856b74d2c8e276945a392a44b', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-01-04 10:46:50', '2018-01-04 10:46:50', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('0f28bb3a7def49f79d6b235e1b0aa51f', '3640cddab5394d01a3bc839599bcceb0', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:25:13', '2017-11-25 14:26:21', '001', 1, '2017-11-25 20:42:44', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('11e09f369a7c40b3b05156def34ddd8d', '6b01833621ec4daaa9804a5e28e7df43', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-30 16:00:11', '2017-11-30 16:00:11', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('19250abd7190402db118ef5b655cffea', '25849a752e5c402786bd5fdf0e32db8a', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-13 12:52:05', '2017-12-13 12:52:05', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('1cd4783d1f6645ee9441c2aeb642395a', 'ec63f7ce9280416691aaea449a7252d1', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-04-09 12:10:36', '2018-04-09 12:10:36', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('1f4586272f27484c9282c87061467026', 'a6fbf7e6cdfb4d45a241a581a06b5731', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-01-10 11:39:36', '2018-01-10 11:39:36', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('200c0a4a69f74e85834277b986648211', '43d9cfadf0ae441dac90101796db1203', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-04-12 14:49:32', '2018-04-12 14:49:32', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('203a21d63fd94253be8b69d1b0469ffc', 'c727455e63b14077b0a6d752410aabb9', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-28 11:04:15', '2017-12-28 11:04:15', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('242c59f1ae9f490aa43f7841e453c8fa', '409be31c618c4c14b33c612c94dc80c1', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-28 13:26:43', '2017-11-28 13:26:43', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('248e048dc4004049b3ae9246fa39545e', '9a152751b3004e1b94cc702e8e857edb', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-27 15:36:21', '2017-11-27 15:36:21', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('24f74f86945d42359833c58d1d9583df', '288d7a49141341ffac8be7ab1d799cc0', '2cfc2e2d185e4609af521e2b744ddb98', 1, '186****9591', '520.00', '2017-11-30 11:05:41', '2017-11-30 12:33:40', NULL, 1, '2017-11-30 12:33:40', '2018-12-09 12:33:40');
INSERT INTO `tz_user_mall` VALUES ('25b87ff63c0346c090bc8542475f81f6', '72f0e57e463543e486fbbce19e627fe1', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-07 20:47:05', '2017-12-07 20:47:05', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('25d23eae29024fd385222ed3dea67e98', '6e12cc0c8d6e482f8395e224a982e853', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-27 12:11:43', '2017-11-27 12:11:43', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('286db5812b664a71b4048e9fda7ad128', 'a368b9051f2647bfa2964f3e385bbcdd', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 19:17:26', '2017-12-15 10:17:40', '96479df69b21468ca8a1e0ce754df5bb', 4, '2017-11-25 20:42:42', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('28f84931d3064d8e819cacf4cb158a31', '01c9b354e2b34a25a4a9a79c88d2b218', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-18 09:06:51', '2017-12-18 09:06:51', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('2b56b07ca3fc428098cc282fe381c468', '7d5bd2e611f342ec8921a18280b79c83', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-28 21:40:23', '2017-11-28 21:40:23', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('2ea0be4be3854fd4b3f2bcd74474eee8', '591f524a04d94209974fafd6ffcb56d9', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-04-03 23:24:36', '2018-04-03 23:24:36', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('2efb7eba642b4039a9524c1da3d8f450', 'c33c373d414741cab5ab80f4ffff2b0a', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-06 23:21:59', '2017-12-06 23:21:59', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('331f7c60ee1c4d65a0fd4172e55ef705', '9b78d521f7e34132b7dee46fc3093d42', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-29 15:14:02', '2017-11-29 15:14:02', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('34b6a30b3c9a41dcae6e83fb2804e0f7', 'c1f27b1689bc43d6a597c711225e324c', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-30 14:44:21', '2017-11-30 14:47:14', '001', 1, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('35fe33d8099a4d109fb946b8e1696eb9', 'b17ffac68a0c43ccbf87437914d37ac5', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-04-12 14:50:39', '2018-04-12 14:50:39', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('36cf92822ad14b3ba62222abbf2bc4f1', 'c6ee5f0a26d44ec2a6dea86aa1d75d9c', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-11 16:39:21', '2017-12-11 16:39:21', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('37bbeb712b2846019f64224b1b5aef75', 'f49dd0101441464a8a9b3dbbe224da75', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-30 14:37:06', '2017-11-30 14:37:06', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('3a13bdc7105a40b1988ddd4aadf92162', '07e7ba64169a43b9b9889db66d9b4c1f', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-27 11:53:44', '2017-11-27 11:53:44', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('3a598472247b4d869894d8e569ae6780', 'a521d908027d4ff8b4d3b93ec588b931', '2cfc2e2d185e4609af521e2b744ddb98', 1, '177****1383', '520.00', '2017-11-29 14:14:08', '2017-11-30 15:08:41', NULL, 1, '2017-11-30 15:08:40', '2018-12-09 15:08:40');
INSERT INTO `tz_user_mall` VALUES ('3aead97fc4ae4c7da889b856aa18e569', 'ea7e627a8c2f4386b645731a8abf472f', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-01-13 20:46:29', '2018-01-13 20:46:29', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('3ddbe155d4d04d778fd4797b15634178', '1a048c6799024e54aa29a962a545020b', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-27 09:18:02', '2017-11-27 09:18:02', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('40fb9bd2b4ba4027ad81e28a34ca227c', '3ae34de0ea984028a9a8cc72ba649a7d', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 19:13:56', '2017-11-25 20:05:33', '001', 1, '2017-11-25 20:42:38', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('438a9f6c48d14acba691f2040841de85', '7bee0bfe55ea439693c01c7271a83d0e', '2cfc2e2d185e4609af521e2b744ddb98', 1, 'oXoc71rwTDXXyYWKwiKJ0Lw6EzpY', '52000', '2017-11-29 21:15:16', '2017-12-04 10:05:51', NULL, 1, '2017-12-04 10:05:51', '2018-12-13 10:05:51');
INSERT INTO `tz_user_mall` VALUES ('45c524bec8a248fca68410a652450e08', 'b2572ce53c23461ea802900a4813fd39', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 13:54:00', '2017-11-25 13:54:00', '001', 2, '2017-11-25 20:42:34', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('4735819dd44040249ad468088587cceb', '74f0add8c7ad471980617bbf4709a23d', '2cfc2e2d185e4609af521e2b744ddb98', 1, 'ang***@126.com', '520.00', '2017-11-25 17:44:35', '2017-11-25 19:20:47', NULL, 1, '2017-11-25 19:20:47', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('479ed2d6ed1f42489894da67ef5e94d0', '1dc29c0808cf4e86bca3951e5a26dec8', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-25 15:57:14', '2017-11-25 15:57:14', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('48a18ba64987467997244fab1d350afb', 'a99fc3742f224baa88027db7e4d50e27', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-29 17:00:51', '2017-11-29 17:00:51', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('49730bd63cf94752bae1a0108135625f', '0ef7fdf322934c55b0a804d561228328', '2cfc2e2d185e4609af521e2b744ddb98', 1, 'dna***@163.com', '520.00', '2017-11-28 12:04:41', '2017-11-28 13:33:11', NULL, 1, '2017-11-28 13:33:11', '2018-12-07 13:33:11');
INSERT INTO `tz_user_mall` VALUES ('5057778e60404da7a9406b016c60e44e', 'fdc70f24eb954d77b2a5f5f5559bcfed', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:24:55', '2017-11-25 14:27:42', '001', 1, '2017-11-25 20:42:48', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('572f7031b0874e5ba07a9a4f7362abc9', 'b13bd70b6f40487c86e2f99641cf4719', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:17:06', '2017-11-25 14:21:23', '001', 1, '2017-11-25 20:42:50', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('57e55edc808d4c1db61739cffca2d32c', '702c5245714743db84d306d41a83a4d0', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-12-21 13:53:46', '2017-12-26 17:30:36', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('5abe246f97cf4ed8a6337e2b4f91d849', 'cea6956605ee47d49852515a9854feff', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-30 14:36:34', '2017-11-30 14:36:34', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('5ae2e61e270b4deb8ec83b63ce6968cc', '3fa7e9e592d74c93a65d44244cec5612', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:25:30', '2017-11-25 14:28:09', '001', 1, '2017-11-25 20:42:53', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('5b0e46e21b4a4333bb014ae0b4543643', '55bef243e1f646c7a2bd28ba15e42c96', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-07 10:37:01', '2017-12-07 10:37:01', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('5c3e965c35f342e0829808c40825aebd', 'ae0989bd15b544a2bbea1d463c6f5171', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2018-01-22 16:57:57', '2018-01-22 18:32:05', '96479df69b21468ca8a1e0ce754df5bb', 3, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('5d536ea3561149f896088d9c87f1fd03', 'aed8e1c777764a649bf355258512a3e4', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-10 17:51:46', '2017-12-10 17:51:46', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('6a4bb6e14c12415696adbdc4dfa04d0d', 'dc4c2d899ed24ad491e28aca7dc27316', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-14 14:56:01', '2017-12-14 14:56:01', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('6d9d7fe2d75f4065b59f683cf5c7401c', '062fa5c384ac4439bbeff8150e34246b', '2cfc2e2d185e4609af521e2b744ddb98', 1, 'cha***@126.com', '520.00', '2017-11-27 15:13:22', '2017-11-27 15:44:48', NULL, 1, '2017-11-27 15:44:48', '2018-12-06 15:44:48');
INSERT INTO `tz_user_mall` VALUES ('6e3de89eb3514e38a802e26975bb3320', '46c4472aa6434c8f95f0fab15b81bb53', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-27 10:59:39', '2017-11-27 11:29:01', '001', 1, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('71ceeb7d0c32492db84d1403fed52123', '2367648fdbe443a08d80b22ebfb079a2', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-11 10:52:33', '2017-12-11 10:52:33', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('74b2ee39da3748c88849ec95ccfa502f', '9b923977107645eca7703e3c3d26d455', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 13:59:12', '2017-11-25 13:59:12', '001', 2, '2017-11-25 20:42:56', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('75aded6d97734c178b76211624e133f1', '24f8c8dfa0fc4acb8d0a8bba7e1773d6', '2cfc2e2d185e4609af521e2b744ddb98', 1, '181****6290', '520.00', '2017-11-25 19:13:32', '2017-11-25 19:23:16', NULL, 1, '2017-11-25 19:23:16', '2018-12-04 19:23:16');
INSERT INTO `tz_user_mall` VALUES ('75f3c553fcd946498fdee582339d57ae', '00e3168425b946c6b630d8ebdcd028f9', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-25 17:00:04', '2017-11-25 17:00:04', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('782911369eae47579484e35c9442f85d', '25f7a23d920f48cf89b3eae0e69d8df3', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 19:33:10', '2017-11-25 20:18:24', '001', 1, '2017-11-25 20:42:59', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('7a5e3fa8c94342a29ec7d54cad01cc5b', 'b9b1eb84e5624caeb71f621b445f023f', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-22 20:15:31', '2017-12-22 20:15:31', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('7a8738a889e54530b1d382f1e0a2056f', '593a622faa884a02a5996ac688d3b156', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-25 17:33:02', '2017-11-25 17:33:02', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('7c904529f6d74a55a7b9d7c6b2bc3669', '7bab8480e4414c1cb322b4383bf55285', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-28 19:26:02', '2017-11-28 19:26:02', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('7e72047a049e4bfabf95ec9022e5a01d', 'c78c27faefef40178f3d2f05d14e592b', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2018-01-22 16:15:30', '2018-01-22 18:42:29', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('7e790b7a0d324fe1a38a739cfa1b750b', 'e7196f6e94cc42f7ba293dabf1bc59a9', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-11 11:14:18', '2017-12-11 11:14:18', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('81d1cf2456ea4d14ae1d4e9a5c8d019a', 'e928b6e92c474e3d8a101f421fd39387', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2018-01-22 16:50:54', '2018-01-22 18:41:51', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('82516e56aab6488fa55e00bc89fc9084', '618e1fa97918493b9c61d029cef10fe2', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-12 11:56:23', '2017-12-12 11:56:23', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('87dfc873340e4e779c5cde6204848e48', 'dda37e48c83d47aaabb63fa21e7753f2', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:25:22', '2017-11-25 14:28:26', '001', 1, '2017-11-25 20:43:02', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('8b38437f3a2f47899577b742aa8395ee', '9307cb848b7c49629fdd4f203a95b482', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2018-01-22 16:50:30', '2018-01-22 18:44:27', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('8c47918981b140d1a697997922e16fe2', '7e2d0a001b9d402187f8de8061d5caae', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-07 17:15:51', '2017-12-07 17:15:51', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('8fb55f4b3f584f45bb40b5b00a0e1b79', 'ffdaac2511c8411fabd64c7acbf1534d', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-19 15:55:23', '2017-12-19 15:55:23', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('90ea362793554b66b34f2bbf54470beb', 'cf1a6f3144744127a3c86d45252cdb91', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2018-01-09 12:36:16', '2018-01-22 18:40:04', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('913ac81a5ca8430d8b2901290cf2fb59', '2029c8cfc0da4748ba83172d1e850dbc', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:25:20', '2017-11-25 14:26:00', '001', 1, '2017-11-25 20:43:04', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('915fabfca0e94d83906547d2dec5d5f6', 'df8ac616c5aa4332a32f53fda7139b9c', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-04 20:40:45', '2017-12-04 20:40:45', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('921b2c03007e4274b01d1c04b89a5ad9', '54fcb3a70d2143149899ad17bc01ae9e', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-02 15:58:59', '2017-12-02 15:58:59', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('9444c14f16094d2cab5c1226fa6fa018', '5de9cdaf39c744dd9fed887b25cf3187', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2018-01-22 17:04:41', '2018-01-22 18:44:00', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('9e45f4e2e3924760957080385991bbef', '0ce2930756144b41b623497f376e8a34', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 13:57:44', '2017-11-25 13:57:44', '001', 1, '2017-11-25 20:43:06', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('a203d61aeced45d68dc8923c8ca08abd', '1572f25e56ae4f82908d0f45d9e4b61f', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-29 11:13:50', '2017-11-29 11:13:50', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('a4d9a840cb1249df9b207de3a807ccda', '80c4f4606b114bf6911f1da608c77093', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2018-01-22 18:00:13', '2018-01-22 18:41:09', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('a5524ba21aee402daff01e4db34cc246', 'ceb28fc631e64c528d7865658ac4a38d', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-01-09 13:53:34', '2018-01-09 13:53:34', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('a84f52c7357d4fa1a5f524ced26f0c7c', '5092f5f88686448287cd5ce587fa9e32', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-04 20:54:17', '2017-12-04 20:54:17', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('aa77887e8b554ead9e2dae52d5d25af9', '92183126447f446ca783f3f19e144198', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:01:10', '2017-11-25 14:01:10', '001', 1, '2017-11-25 20:43:09', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('aae2a15beed64ca58e16bd658a1d2360', '74e83089a7ea4533a28f4fa6f0bea485', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-29 12:43:53', '2018-01-22 18:48:26', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('ac031f93d60e4924b641f2b946bb3928', '509001af9def4daa8c638d79e9deb67d', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-29 15:11:46', '2017-11-29 15:11:46', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('ac7f65cf7d32458e85158917deb72c21', '6de0009478834cbdbd81dcd557409d5e', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-30 10:46:50', '2017-11-30 10:46:50', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('af0108d2f4ee4bee80456eecf9ac3126', '88b6ecc45dcf44a2a2d1d8072e1d1832', '2cfc2e2d185e4609af521e2b744ddb98', 1, '133****4403', '520.00', '2017-11-25 14:36:17', '2017-11-25 19:24:39', NULL, 2, '2017-11-25 19:24:39', '2018-12-04 19:24:39');
INSERT INTO `tz_user_mall` VALUES ('af23ac140f384d569d3bcea23156ee11', 'f65fb8df88b84e4ca7e0e5569c8ecea9', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-12-14 14:42:56', '2018-01-22 18:38:28', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('afc658f304d4409dbb21e18ed96e86a8', '2fd496edc2d243b288e27f5f12538856', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-25 16:59:05', '2017-11-25 16:59:05', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('ba33205056e64eb4b065b46f796ceace', 'e7d5293a218f45e3b6007e68eedeea86', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-12 19:57:07', '2017-12-12 19:57:07', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('ba653bab654b4234ab501d9d3039b56e', '45a6024bf01546fda014453b07966d5d', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-21 10:08:31', '2017-12-21 10:08:31', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('baf8f28ad76c496c82a2e80111098592', '48fb416505854b27b788953e8aee9b34', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-29 09:44:18', '2017-11-29 09:44:18', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('c1ceb0da84964a36808f14364bce6573', '65f80d5cab6a4aa484b7d6f5b08a9eac', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-30 17:11:54', '2017-11-30 17:11:54', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('c349b4535e954c76af55a7d9d931c742', 'de83afa9bbd54278a16b8f6580d5f58f', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 13:58:09', '2017-11-25 13:58:09', '001', 1, '2017-11-25 20:43:11', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('c67549f730ee4417bc81945bcfe530ef', '4e70c024ff4a48d4a3df99de9e93c029', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-30 18:45:38', '2017-11-30 18:45:38', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('c9b40b40ceec4e4fb4e382650e063e61', '46f04a3da4874ce88713e68d8cc96092', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-01-16 13:27:40', '2018-01-16 13:27:40', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('cb6894ca8b58475797168f2510e88761', '585dcde9e7144f22ba07e22a81abc288', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-04-10 17:46:26', '2018-04-10 17:46:26', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('cbe95f35fa6d42c5b5584b01ad685043', '49cab02d80a0412c88f7b437c19aa62d', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2018-01-04 09:56:37', '2018-01-22 18:39:12', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('ce418136a4a14670b518146480be6266', '2b6467eeb24a485784ef32adcc8a9cd2', '2cfc2e2d185e4609af521e2b744ddb98', 1, '157****5258', '520.00', '2017-11-29 16:56:08', '2017-11-29 17:14:38', NULL, 1, '2017-11-29 17:14:37', '2018-12-08 17:14:37');
INSERT INTO `tz_user_mall` VALUES ('d3fc41e672fd470ab11a8280da31a836', '2dfa9c00d82b47c796620533e22e4db0', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-07 10:34:47', '2017-12-07 10:34:47', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('d48653ae6f3145ccb93f514e006de922', 'a0ad284f4e474378bc88a5b634be6927', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-12-25 16:48:19', '2017-12-26 17:30:25', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('d5dfd13776954990b633b787d6b41771', 'b852902504194934a2be63c2f394d335', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-07 16:58:38', '2017-12-07 16:58:38', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('d979800fc7ae48cb8b5aeaae1b59b8f8', '79c0d1fc1eeb4f29a048c8aeb79f7de2', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2018-01-22 16:57:51', '2018-01-22 18:43:24', '96479df69b21468ca8a1e0ce754df5bb', 4, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('da18262a37bb4bbbbd4e50b2e3042c2a', '2a0aa340ca924385af2606420fad5737', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-29 13:15:29', '2017-11-29 13:15:29', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('deb7305466334ebe9b67c2cda8199a7a', 'c2320cd159c24b9196b6d7aa48374ff3', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-18 17:08:40', '2017-12-18 17:08:40', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('df93e6255f5f408ca5d762dafa10a6da', '1433defc747644e280e942e2b67dcfd8', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-25 17:00:04', '2017-11-25 17:00:04', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('e09691dc959949d2ab4810bbf7e44570', '8540ba3ae8684646b970d6762fe6bab3', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-12-14 14:20:28', '2017-12-14 14:48:44', '96479df69b21468ca8a1e0ce754df5bb', 1, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('e1431e7510eb4017b742b709a1af5e69', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:18:26', '2017-11-25 14:23:41', '001', 1, '2017-11-25 20:43:15', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('e46a4f4ce9a245cd83e7ad125ee56191', '59313dae44844f3d819e960ecfad605a', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-11 16:41:18', '2017-12-11 16:41:18', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('e5d959f876fd46df9dfb631261c7c473', 'bc51ff97b0e64e028548eff8fdfad989', '2cfc2e2d185e4609af521e2b744ddb98', 1, '138****3986', '520.00', '2017-11-30 10:58:12', '2017-11-30 11:00:59', NULL, 1, '2017-11-30 11:00:59', '2018-12-09 11:00:59');
INSERT INTO `tz_user_mall` VALUES ('e6ce9c6867824f26b91cb570cf12e4ff', '9ef7d2d05dfa457ab534eca26e9aa5e9', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-20 11:43:06', '2017-12-20 11:43:06', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('e7cd5105743148dabcd5d565530288ab', '12d4ab2da7394354996db664828c7242', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:25:01', '2017-11-25 14:29:39', '001', 1, '2017-11-25 20:43:18', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('e8159ecd434748549ecb0ee16392d7a1', 'f1a88380463645bca982a9b626ef3c6a', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-12-18 09:03:57', '2017-12-18 09:03:57', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('ed628f827b58450885e0bf95729b7447', 'cd8d6ebda4b74123b2b7b4a32b8b66d9', '2cfc2e2d185e4609af521e2b744ddb98', 1, '170***@qq.com', '520.00', '2017-11-29 10:07:38', '2017-11-29 10:10:42', NULL, 1, '2017-11-29 10:10:41', '2018-12-08 10:10:41');
INSERT INTO `tz_user_mall` VALUES ('f5b10a604102445393fc11c6fbc80ef8', 'e13e407d48f54bb38de52273eedbb28e', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2018-01-09 12:38:00', '2018-01-09 12:38:00', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('f82cec3e66784a6480199b6ea6b68666', '50d561e804fa42feb4e557aa069dbb94', '2cfc2e2d185e4609af521e2b744ddb98', 4, 'tz520', '520', '2017-11-25 14:25:16', '2017-11-25 14:28:52', '001', 1, '2017-11-25 20:43:20', '2018-12-04 19:20:47');
INSERT INTO `tz_user_mall` VALUES ('f998779889224a7bae7085e4f3833a95', '84b12c435a1843b5a2916cce1ccb73fc', '2cfc2e2d185e4609af521e2b744ddb98', 0, NULL, '0', '2017-11-29 14:19:37', '2017-11-29 14:19:37', NULL, 0, NULL, NULL);
INSERT INTO `tz_user_mall` VALUES ('fc2d8e242f9348cd8aa01956e72ac4c9', 'e040813c1f8149b7a7a56a4ee5f2a0de', '2cfc2e2d185e4609af521e2b744ddb98', 1, '134****4309', '520.00', '2017-11-25 17:27:34', '2017-11-25 19:25:18', NULL, 1, '2017-11-25 19:25:18', '2018-12-04 19:25:18');

-- ----------------------------
-- Table structure for tz_user_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `tz_user_pay_log`;
CREATE TABLE `tz_user_pay_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `pay_type` int(11) NULL DEFAULT NULL COMMENT '支付类型 0：微信 1 :支付宝',
  `buy_type` int(11) NULL DEFAULT NULL COMMENT '购买类型 0 ：会员升级 1 ：商品购买',
  `total_mount` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付金额',
  `body` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `subject` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款信息',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款账号',
  `status` int(11) NULL DEFAULT NULL COMMENT '支付状态  0：未付款 1：已付款 2 ：付款失败 ',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '付款完成时间',
  `trade_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝交易号 ',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tz_user_pay_log
-- ----------------------------
INSERT INTO `tz_user_pay_log` VALUES ('01c3dfd54a214e8eb6617acdca41f7ce', 'bc51ff97b0e64e028548eff8fdfad989', 1, 0, '520.00', '爱心520商城', '爱心520商城账户会员升级,超值礼包购买！', '138****3986', 1, '2017-11-30 11:00:17', '2017-11-30 11:00:17', '2017-11-30 11:00:59', '2017113021001004310523457136', '5902c7c886964300977c102b071ff9c7');
INSERT INTO `tz_user_pay_log` VALUES ('04e4c89de11a497cac113c3b331ad234', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 22:42:01', '2017-12-01 22:42:01', NULL, NULL, '39391208f4904f498df934ea41dca2d8');
INSERT INTO `tz_user_pay_log` VALUES ('051f884a448141bcab3d6fb5b3acdc27', '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 19:04:37', '2017-11-30 19:04:37', NULL, NULL, '4f7e015bb5944f4fa87f30b5e4454365');
INSERT INTO `tz_user_pay_log` VALUES ('0749e057545144389a17f6e5e1e25d4c', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-01 22:52:18', '2017-12-01 22:52:18', NULL, NULL, '00791d8598554484a69a12595cfa372c');
INSERT INTO `tz_user_pay_log` VALUES ('097dd7feab0e439384cf597b57933b34', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-27 20:54:41', '2017-11-27 20:54:41', NULL, NULL, '3c950aec833049b2a5cf1732226ef342');
INSERT INTO `tz_user_pay_log` VALUES ('0b5ab6678c6748d993890364294d9f34', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-08 16:17:51', '2017-12-08 16:17:51', NULL, NULL, 'a6858f7223624964b3339e4f09360e6b');
INSERT INTO `tz_user_pay_log` VALUES ('0fbc40484b12479da562c8fe22483fb0', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-28 11:11:19', '2017-11-28 11:11:19', NULL, NULL, '2bbb1860bf994152b7edf946f34da038');
INSERT INTO `tz_user_pay_log` VALUES ('102152c29ed242ceadddd0c217bdc375', '1a048c6799024e54aa29a962a545020b', 1, 0, '520', '爱心520商城', '爱心520商城账户会员升级，超值礼包购买！', NULL, 0, '2017-12-08 16:48:17', '2017-12-08 16:48:17', NULL, NULL, 'e327f62d599b47c68990d220131da448');
INSERT INTO `tz_user_pay_log` VALUES ('14e7a17dcdb743de8bb18896794f22ad', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-01 22:52:29', '2017-12-01 22:52:29', NULL, NULL, '00791d8598554484a69a12595cfa372c');
INSERT INTO `tz_user_pay_log` VALUES ('167b7896f62241348c699d53a21cd81a', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-01 20:17:25', '2017-12-01 20:17:25', NULL, NULL, '15e94ed40f7940f1969b9abd536e6e11');
INSERT INTO `tz_user_pay_log` VALUES ('171c8966c8d14bbdbd49fa0b26ad2632', 'b2572ce53c23461ea802900a4813fd39', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 22:52:34', '2017-12-01 22:52:34', NULL, NULL, '00791d8598554484a69a12595cfa372c');
INSERT INTO `tz_user_pay_log` VALUES ('1b8a8185ede04426b13bad1a900b1ee3', '062fa5c384ac4439bbeff8150e34246b', 1, 0, '520.00', '爱心520商城', '爱心520商城账户会员升级,超值礼包购买！', 'cha***@126.com', 1, '2017-11-27 15:44:17', '2017-11-27 15:44:17', '2017-11-27 15:44:48', '2017112721001004410539395469', 'a03681ac9a634b838e8df25a2bd06b7c');
INSERT INTO `tz_user_pay_log` VALUES ('1bd0153e38f74ff4836bf28d598d3fd0', 'b852902504194934a2be63c2f394d335', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-07 17:09:07', '2017-12-07 17:09:07', NULL, NULL, '11f2d2973b7b4dd1bc9056eabbdcd8f0');
INSERT INTO `tz_user_pay_log` VALUES ('20620d0a859d42db8dbdcc64e95adfa4', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-29 14:32:46', '2017-11-29 14:32:46', NULL, NULL, '1a97c583ab414b5b8ee300aba85522b2');
INSERT INTO `tz_user_pay_log` VALUES ('2120d13a3a7541b9810ccfd772f5d643', '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 19:04:07', '2017-11-30 19:04:07', NULL, NULL, '7d8afbb5302745e69ad83e8c51bf8fef');
INSERT INTO `tz_user_pay_log` VALUES ('2779ea83af1748ec9f6e51576fec0661', '3877878856b74d2c8e276945a392a44b', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2018-01-04 10:48:30', '2018-01-04 10:48:30', NULL, NULL, '6ada52219ea84b2d97920adf643cf50d');
INSERT INTO `tz_user_pay_log` VALUES ('2cb9faf6a6dc4e00a0c0448d845ee748', '2b6467eeb24a485784ef32adcc8a9cd2', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-29 17:09:26', '2017-11-29 17:09:26', NULL, NULL, '4ec9d6add713407399a356241a2ab68d');
INSERT INTO `tz_user_pay_log` VALUES ('2ed41ae635a44506b28d53dfb6a896d7', 'b2572ce53c23461ea802900a4813fd39', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 20:32:22', '2017-11-30 20:32:22', NULL, NULL, 'daddb03a720b491d9fcf4136b1278524');
INSERT INTO `tz_user_pay_log` VALUES ('2ffd1ad5823f44039199c0fa05cfd899', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-28 11:12:01', '2017-11-28 11:12:01', NULL, NULL, '2bbb1860bf994152b7edf946f34da038');
INSERT INTO `tz_user_pay_log` VALUES ('308dff87eee5481ab583f990b721412f', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 20:17:16', '2017-12-01 20:17:16', NULL, NULL, '00791d8598554484a69a12595cfa372c');
INSERT INTO `tz_user_pay_log` VALUES ('32bcd3392bbc40a389ab35a520ac9623', '1a048c6799024e54aa29a962a545020b', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-01 20:39:12', '2017-12-01 20:39:12', NULL, NULL, '5fcf23355a64476689bfe54e02f3c0b9');
INSERT INTO `tz_user_pay_log` VALUES ('3b8e3bcce34b4d9e9ac57a2b01f14a89', '288d7a49141341ffac8be7ab1d799cc0', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 12:33:04', '2017-11-30 12:33:04', NULL, NULL, '9e94111cdcbd4d1f81737c633d1d6692');
INSERT INTO `tz_user_pay_log` VALUES ('3d7d1110687447a1b094eac9f510f088', '1a048c6799024e54aa29a962a545020b', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 20:38:43', '2017-12-01 20:38:43', NULL, NULL, '5fcf23355a64476689bfe54e02f3c0b9');
INSERT INTO `tz_user_pay_log` VALUES ('3d9416cbe4df4e65a4794f2f6ba17c90', '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 19:04:16', '2017-11-30 19:04:16', NULL, NULL, '7d8afbb5302745e69ad83e8c51bf8fef');
INSERT INTO `tz_user_pay_log` VALUES ('414d4bcc038b40c89943bb35595f1cfe', 'f49dd0101441464a8a9b3dbbe224da75', 1, 0, '520', '爱心520商城', '爱心520商城账户会员升级，超值礼包购买！', NULL, 0, '2018-02-16 20:57:26', '2018-02-16 20:57:26', NULL, NULL, 'edb3726aeb31424783c1a5e0e7ce2703');
INSERT INTO `tz_user_pay_log` VALUES ('4a2b1ec04fe9494ca464aff0ea459d42', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-11-27 15:14:17', '2017-11-27 15:14:17', NULL, NULL, '5b434d790392450ea0fd60010f2d65cd');
INSERT INTO `tz_user_pay_log` VALUES ('4a929e2fc8df40d0bbfeff650f6123a6', '74f0add8c7ad471980617bbf4709a23d', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-25 19:20:56', '2017-11-25 19:20:56', NULL, NULL, 'ce27083f647a4a199238e7d1ca59aac3');
INSERT INTO `tz_user_pay_log` VALUES ('4ff45d8f05044f01a5ac24a27677dfe8', '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 19:23:31', '2017-11-30 19:23:31', NULL, NULL, '188ed236c05645989d9b56b512dd5453');
INSERT INTO `tz_user_pay_log` VALUES ('518bf12d3fa147f4b9695d79333d90af', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 20:17:17', '2017-12-01 20:17:17', NULL, NULL, '15e94ed40f7940f1969b9abd536e6e11');
INSERT INTO `tz_user_pay_log` VALUES ('590c3b02b8f442da8612afa54bdf3c84', '2b6467eeb24a485784ef32adcc8a9cd2', 1, 1, '520.00', '爱心520商城', '爱心520商城订单购物支付！', '157****5258', 1, '2017-11-29 17:14:28', '2017-11-29 17:14:28', '2017-11-29 17:14:37', '2017112921001004750576580019', '4ec9d6add713407399a356241a2ab68d');
INSERT INTO `tz_user_pay_log` VALUES ('5d41460ef19f4a71a57285fd5c5cdc12', '3640cddab5394d01a3bc839599bcceb0', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2018-03-20 23:33:16', '2018-03-20 23:33:16', NULL, NULL, '30c4ea3921f242b1a6c3845eb7d4733d');
INSERT INTO `tz_user_pay_log` VALUES ('6279bc4cfe924480a0a9cfcd3bfce4ef', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 22:40:58', '2017-12-01 22:40:58', NULL, NULL, '39391208f4904f498df934ea41dca2d8');
INSERT INTO `tz_user_pay_log` VALUES ('6ed4f3670a8f4103b21891d28a9a0773', 'a521d908027d4ff8b4d3b93ec588b931', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 15:08:10', '2017-11-30 15:08:10', NULL, NULL, 'a9c9aeb6a0c34a6c9a7cd64db910d74f');
INSERT INTO `tz_user_pay_log` VALUES ('7328a568dc654495a7296f12ce4ad65e', '0ef7fdf322934c55b0a804d561228328', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-28 13:33:25', '2017-11-28 13:33:25', NULL, NULL, '202f62ff3aab430f80f8cb0f5ac4175e');
INSERT INTO `tz_user_pay_log` VALUES ('75aee58ce9874e939a3571fbdf8487a8', 'e040813c1f8149b7a7a56a4ee5f2a0de', 1, 0, '520.00', '爱心520商城', '爱心520商城账户会员升级,超值礼包购买！', '134****4309', 1, '2017-11-25 19:23:53', '2017-11-25 19:23:53', '2017-11-25 19:25:18', '2017112521001004400285980805', '08e1582ddb5b4cfbbfa243beb2ebfc9d');
INSERT INTO `tz_user_pay_log` VALUES ('78881413c37b47afa1d78345db778ab9', '288d7a49141341ffac8be7ab1d799cc0', 1, 1, '520.00', '爱心520商城', '爱心520商城订单购物支付！', '186****9591', 1, '2017-11-30 12:33:28', '2017-11-30 12:33:28', '2017-11-30 12:33:40', '2017113021001004080292607177', '9e94111cdcbd4d1f81737c633d1d6692');
INSERT INTO `tz_user_pay_log` VALUES ('7960e7896985418c90f80a1ab7037b53', 'ffdaac2511c8411fabd64c7acbf1534d', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-19 15:56:19', '2017-12-19 15:56:19', NULL, NULL, '678e361cdde2437abb1b222ab00d4711');
INSERT INTO `tz_user_pay_log` VALUES ('80ae7636ba824977be2b381aa6c24501', '24f8c8dfa0fc4acb8d0a8bba7e1773d6', 1, 0, '520', '爱心520商城', '爱心520商城账户会员升级,超值礼包购买！', NULL, 0, '2017-11-25 19:17:02', '2017-11-25 19:17:02', NULL, NULL, 'e5287e6ef3f44be985f1d492f939ed75');
INSERT INTO `tz_user_pay_log` VALUES ('80e391a8dfad4c06954d26d4a5f9f665', '74f0add8c7ad471980617bbf4709a23d', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-25 19:21:15', '2017-11-25 19:21:15', NULL, NULL, 'ce27083f647a4a199238e7d1ca59aac3');
INSERT INTO `tz_user_pay_log` VALUES ('8a3e796650c94345aa757d7ab671f7c6', 'fdc70f24eb954d77b2a5f5f5559bcfed', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-25 19:45:45', '2017-11-25 19:45:45', NULL, NULL, 'e6a0d1ba9979456a9e834c33f0614400');
INSERT INTO `tz_user_pay_log` VALUES ('8d0ec684891b4c13a9be7d16f8face97', '74f0add8c7ad471980617bbf4709a23d', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-25 19:21:16', '2017-11-25 19:21:16', NULL, NULL, 'ce27083f647a4a199238e7d1ca59aac3');
INSERT INTO `tz_user_pay_log` VALUES ('8fbc1f32d0aa416fb05471a253739da4', '1a048c6799024e54aa29a962a545020b', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 21:43:45', '2017-12-01 21:43:45', NULL, NULL, '5fcf23355a64476689bfe54e02f3c0b9');
INSERT INTO `tz_user_pay_log` VALUES ('93859e9d22d44a77bc65f1e8d6815964', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-28 11:28:24', '2017-11-28 11:28:24', NULL, NULL, '2bbb1860bf994152b7edf946f34da038');
INSERT INTO `tz_user_pay_log` VALUES ('948e70eb78e841f28837513982dabfff', 'b2572ce53c23461ea802900a4813fd39', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 08:23:21', '2017-12-01 08:23:21', NULL, NULL, 'daddb03a720b491d9fcf4136b1278524');
INSERT INTO `tz_user_pay_log` VALUES ('96421b4f45214c98b74de62609109e6c', '74f0add8c7ad471980617bbf4709a23d', 1, 1, '520.00', '爱心520商城', '爱心520商城订单购物支付！', 'ang***@126.com', 1, '2017-11-25 19:19:54', '2017-11-25 19:19:54', '2017-11-25 19:20:47', '2017112521001004200253348701', 'ce27083f647a4a199238e7d1ca59aac3');
INSERT INTO `tz_user_pay_log` VALUES ('9757838e0d6a4e178b83f093a05cebde', 'c33c373d414741cab5ab80f4ffff2b0a', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-06 23:33:12', '2017-12-06 23:33:12', NULL, NULL, 'a2c832a7ca43483f851735d03af4e0f5');
INSERT INTO `tz_user_pay_log` VALUES ('98c7568a3646403690d622ab0c4e790e', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-28 11:11:34', '2017-11-28 11:11:34', NULL, NULL, '2bbb1860bf994152b7edf946f34da038');
INSERT INTO `tz_user_pay_log` VALUES ('9fa7ae00c0914ac6b256f4a2cebc5b9c', 'b2572ce53c23461ea802900a4813fd39', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 20:16:52', '2017-12-01 20:16:52', NULL, NULL, '2938368e9c0740158fea8db0663c295d');
INSERT INTO `tz_user_pay_log` VALUES ('a0d7d600b9b24a468275cbd458c849db', '88b6ecc45dcf44a2a2d1d8072e1d1832', 1, 1, '520.00', '爱心520商城', '爱心520商城订单购物支付！', '133****4403', 1, '2017-11-25 19:23:58', '2017-11-25 19:23:58', '2017-11-25 19:24:39', '2017112521001004040266957851', '9a869ee38715457bba8656ab22ecec61');
INSERT INTO `tz_user_pay_log` VALUES ('a17f61a2a22d4766a1dadf6c2ab6113f', 'cd8d6ebda4b74123b2b7b4a32b8b66d9', 1, 0, '520.00', '爱心520商城', '爱心520商城账户会员升级,超值礼包购买！', '170***@qq.com', 1, '2017-11-29 10:10:06', '2017-11-29 10:10:06', '2017-11-29 10:10:41', '2017112921001004440272595677', 'b4fb7dbb628c4fb8aa723407084f6e5c');
INSERT INTO `tz_user_pay_log` VALUES ('a1f120f5d55146d9a4b8d7c62ca34fbf', '12d4ab2da7394354996db664828c7242', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-28 10:02:06', '2017-11-28 10:02:06', NULL, NULL, 'fadda7b7d0c949428dcedd1e007b20d7');
INSERT INTO `tz_user_pay_log` VALUES ('a514e1a011b34c87a113dbe507b600c7', '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 19:23:42', '2017-11-30 19:23:42', NULL, NULL, '188ed236c05645989d9b56b512dd5453');
INSERT INTO `tz_user_pay_log` VALUES ('a5492c14e8cb41d5aa45024e25aa4b32', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-26 00:18:27', '2017-11-26 00:18:27', NULL, NULL, '9ec9b88ee5e64074915970405c924974');
INSERT INTO `tz_user_pay_log` VALUES ('a675a1985fb34976bc56d462846d9498', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-08 16:33:27', '2017-12-08 16:33:27', NULL, NULL, 'a6858f7223624964b3339e4f09360e6b');
INSERT INTO `tz_user_pay_log` VALUES ('a69cac4cbf99474385b91b291083efb8', 'e7196f6e94cc42f7ba293dabf1bc59a9', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-11 11:20:53', '2017-12-11 11:20:53', NULL, NULL, 'e2285a09710043cb871277e6f219e813');
INSERT INTO `tz_user_pay_log` VALUES ('aa4e51a7956d45aebd8b305854d7188e', '24f8c8dfa0fc4acb8d0a8bba7e1773d6', 1, 0, '520.00', '爱心520商城', '爱心520商城账户会员升级,超值礼包购买！', '181****6290', 1, '2017-11-25 19:21:24', '2017-11-25 19:21:24', '2017-11-25 19:23:16', '2017112521001004630575458066', 'e5287e6ef3f44be985f1d492f939ed75');
INSERT INTO `tz_user_pay_log` VALUES ('aca860a1f05e416da1e200ef994610ac', 'a368b9051f2647bfa2964f3e385bbcdd', 1, 0, '520', '爱心520商城', '爱心520商城账户会员升级,超值礼包购买！', NULL, 0, '2017-11-25 19:21:09', '2017-11-25 19:21:09', NULL, NULL, 'cd36f485aff14bda9868b809cd775d5a');
INSERT INTO `tz_user_pay_log` VALUES ('b0182a633faf4ae69cc4794081abf749', 'e7196f6e94cc42f7ba293dabf1bc59a9', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-11 11:17:35', '2017-12-11 11:17:35', NULL, NULL, '773ffb9a64954d81a40571c0b538ca30');
INSERT INTO `tz_user_pay_log` VALUES ('b11e2cfdc3374c038ea9087c6537c74e', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-28 11:28:12', '2017-11-28 11:28:12', NULL, NULL, '2bbb1860bf994152b7edf946f34da038');
INSERT INTO `tz_user_pay_log` VALUES ('b536bcff4ad94bc1ae04446205772a43', '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 19:22:26', '2017-11-30 19:22:26', NULL, NULL, '188ed236c05645989d9b56b512dd5453');
INSERT INTO `tz_user_pay_log` VALUES ('b55c875a5d6b4483831817113e3c3171', 'c33c373d414741cab5ab80f4ffff2b0a', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-06 23:26:03', '2017-12-06 23:26:03', NULL, NULL, '78afcb2583b044f98510a0977c244a1f');
INSERT INTO `tz_user_pay_log` VALUES ('b7fc2185c5644ce089b644e732faed6a', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-08 16:17:45', '2017-12-08 16:17:45', NULL, NULL, 'a6858f7223624964b3339e4f09360e6b');
INSERT INTO `tz_user_pay_log` VALUES ('bb87100aace64987a33bad39c3983b93', 'b2572ce53c23461ea802900a4813fd39', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 20:32:08', '2017-11-30 20:32:08', NULL, NULL, 'daddb03a720b491d9fcf4136b1278524');
INSERT INTO `tz_user_pay_log` VALUES ('bcc29ecacd3347e49027b159a5741b17', '12d4ab2da7394354996db664828c7242', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-01 18:53:40', '2017-12-01 18:53:40', NULL, NULL, '1154336cf67b473da74e4783032c3446');
INSERT INTO `tz_user_pay_log` VALUES ('bda1f6dd08a2468d8769efcf651dd250', 'b852902504194934a2be63c2f394d335', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-07 17:11:53', '2017-12-07 17:11:53', NULL, NULL, '11f2d2973b7b4dd1bc9056eabbdcd8f0');
INSERT INTO `tz_user_pay_log` VALUES ('be550437253f40719fb468f6c11f00a3', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-27 20:53:28', '2017-11-27 20:53:28', NULL, NULL, '060960b73af74bac8e2624cfe0bf6c52');
INSERT INTO `tz_user_pay_log` VALUES ('bf929b09a81243528bbdf1a90dfe705d', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-29 14:32:33', '2017-11-29 14:32:33', NULL, NULL, '1a97c583ab414b5b8ee300aba85522b2');
INSERT INTO `tz_user_pay_log` VALUES ('c0c9ab24efed4890a76a589a3a80b27f', '7bee0bfe55ea439693c01c7271a83d0e', 0, 1, '52000', '爱心520商城', '爱心520商城订单购物支付！', 'oXoc71rwTDXXyYWKwiKJ0Lw6EzpY', 1, '2017-12-04 10:05:44', '2017-12-04 10:05:44', '2017-12-04 10:05:51', '4200000042201712048954127388', '2153e6d947914ff3bdd1e474902c210e');
INSERT INTO `tz_user_pay_log` VALUES ('c4e8bb05a45e422b97a154a82243e1fe', 'b2572ce53c23461ea802900a4813fd39', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 10:58:11', '2017-12-01 10:58:11', NULL, NULL, 'daddb03a720b491d9fcf4136b1278524');
INSERT INTO `tz_user_pay_log` VALUES ('c66a3eb74fe4494088d9b459ed23ee69', 'b2572ce53c23461ea802900a4813fd39', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 20:32:35', '2017-11-30 20:32:35', NULL, NULL, 'daddb03a720b491d9fcf4136b1278524');
INSERT INTO `tz_user_pay_log` VALUES ('c678808e59a6463d84a234d3d9ca4046', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-26 00:18:08', '2017-11-26 00:18:08', NULL, NULL, '9ec9b88ee5e64074915970405c924974');
INSERT INTO `tz_user_pay_log` VALUES ('c9f7464c4f06482fafb8ddeba0224577', 'b852902504194934a2be63c2f394d335', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-07 17:11:33', '2017-12-07 17:11:33', NULL, NULL, '11f2d2973b7b4dd1bc9056eabbdcd8f0');
INSERT INTO `tz_user_pay_log` VALUES ('cfcaf0ae5e7745f78c11318f77472d6a', 'fdc70f24eb954d77b2a5f5f5559bcfed', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-25 19:45:16', '2017-11-25 19:45:16', NULL, NULL, 'e6a0d1ba9979456a9e834c33f0614400');
INSERT INTO `tz_user_pay_log` VALUES ('d08f12be887a4e74b4ebd9b850c89cfe', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-01 22:41:07', '2017-12-01 22:41:07', NULL, NULL, '39391208f4904f498df934ea41dca2d8');
INSERT INTO `tz_user_pay_log` VALUES ('d49d9f523a744be392ee71aaff183c42', '3877878856b74d2c8e276945a392a44b', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2018-01-04 10:48:38', '2018-01-04 10:48:38', NULL, NULL, '6ada52219ea84b2d97920adf643cf50d');
INSERT INTO `tz_user_pay_log` VALUES ('d519664289674ec6a91c618c583cd1ed', 'a521d908027d4ff8b4d3b93ec588b931', 1, 1, '520.00', '爱心520商城', '爱心520商城订单购物支付！', '177****1383', 1, '2017-11-30 15:07:32', '2017-11-30 15:07:32', '2017-11-30 15:08:40', '2017113021001004670582440781', 'a9c9aeb6a0c34a6c9a7cd64db910d74f');
INSERT INTO `tz_user_pay_log` VALUES ('d56ab5c7156e4b16acf69ada7f5fd7bc', 'b2572ce53c23461ea802900a4813fd39', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-27 20:53:42', '2017-11-27 20:53:42', NULL, NULL, '060960b73af74bac8e2624cfe0bf6c52');
INSERT INTO `tz_user_pay_log` VALUES ('db827b2350304763a10a59bcea76a49d', 'b852902504194934a2be63c2f394d335', 0, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-07 17:11:40', '2017-12-07 17:11:40', NULL, NULL, '11f2d2973b7b4dd1bc9056eabbdcd8f0');
INSERT INTO `tz_user_pay_log` VALUES ('dbe6f80dca064d1c9722e8746f5a2f0e', '2029c8cfc0da4748ba83172d1e850dbc', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-30 19:22:40', '2017-11-30 19:22:40', NULL, NULL, '188ed236c05645989d9b56b512dd5453');
INSERT INTO `tz_user_pay_log` VALUES ('de521e28ca4c40b9b446a211a76a4867', '12d4ab2da7394354996db664828c7242', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 18:53:31', '2017-12-01 18:53:31', NULL, NULL, '1154336cf67b473da74e4783032c3446');
INSERT INTO `tz_user_pay_log` VALUES ('e1bd690382cb43a586c3f7ace4e92031', '74f0add8c7ad471980617bbf4709a23d', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-11-25 19:21:13', '2017-11-25 19:21:13', NULL, NULL, 'ce27083f647a4a199238e7d1ca59aac3');
INSERT INTO `tz_user_pay_log` VALUES ('e2bfb84ae03b4c509451b829ccf32f36', '0ef7fdf322934c55b0a804d561228328', 1, 1, '520.00', '爱心520商城', '爱心520商城订单购物支付！', 'dna***@163.com', 1, '2017-11-28 13:32:28', '2017-11-28 13:32:28', '2017-11-28 13:33:11', '2017112821001004720288427763', '202f62ff3aab430f80f8cb0f5ac4175e');
INSERT INTO `tz_user_pay_log` VALUES ('ea5df9f9ca02425b8a6b2c9bb537f2f4', '062fa5c384ac4439bbeff8150e34246b', 1, 0, '520', '爱心520商城', '爱心520商城账户会员升级,超值礼包购买！', NULL, 0, '2017-11-27 15:44:17', '2017-11-27 15:44:17', NULL, NULL, 'a03681ac9a634b838e8df25a2bd06b7c');
INSERT INTO `tz_user_pay_log` VALUES ('eb3f808925804e179fa8d2bbd96a11f2', 'e7196f6e94cc42f7ba293dabf1bc59a9', 1, 1, '520', '爱心520商城', '爱心520商城订单购物支付', NULL, 0, '2017-12-11 11:20:23', '2017-12-11 11:20:23', NULL, NULL, 'e2285a09710043cb871277e6f219e813');
INSERT INTO `tz_user_pay_log` VALUES ('f014a406f7a14feeac007b7d2bd61a00', 'fd3ee4ebc15346bfb52dfa7af1a4fbbd', 0, 0, '520', '爱心520商城', '爱心520商城订单购物支付！', NULL, 0, '2017-12-01 20:57:05', '2017-12-01 20:57:05', NULL, NULL, '92d98e9f8101474aa4d8eae494c50707');

-- ----------------------------
-- Table structure for tz_web_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tz_web_feedback`;
CREATE TABLE `tz_web_feedback`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户反馈信息编号',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户反馈的文字描述信息',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '用户反馈时间',
  `status` int(2) NOT NULL COMMENT '该信息是状态 1.未处理 0 已处理',
  `reply` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商城回复信息文字描述',
  `mall_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家id',
  `mall_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家名称',
  `reply_date` datetime(0) NULL DEFAULT NULL COMMENT '商城回复信息处理时间',
  `operater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `is_show` int(2) NULL DEFAULT NULL COMMENT '该反馈信息是否显示 0、显示 1、不显示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户对520商场反馈意见表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tz_zfb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tz_zfb_user_info`;
CREATE TABLE `tz_zfb_user_info`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `zfb_auth_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝授权code',
  `zfb_user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝授权登录id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联用户的id',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
