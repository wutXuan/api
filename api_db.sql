SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interface_info
-- ----------------------------
DROP TABLE IF EXISTS `interface_info`;
CREATE TABLE `interface_info`  (
  `id` bigint(0) NOT NULL COMMENT '用户id',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '接口地址',
  `requestHeader` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求头',
  `responseHeader` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '响应头',
  `status` int(0) NOT NULL COMMENT '接口状态（0-关闭，1-开启）',
  `method` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求类型',
  `userId` bigint(0) NOT NULL COMMENT '创建人',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `isDelete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除（0-未删，1-已删）',
  `requestParams` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'api_db.`interface_info`' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interface_info
-- ----------------------------
INSERT INTO `interface_info` VALUES (1, 'getNameByPost', '获取用户名', 'http://localhost:8123/api/name/getNameByPost', '{\"Content-Type\":\"application/json\"}', '{\"Content-Type\":\"application/json\"}', 1, 'POST', 1, '2024-03-25 13:52:25', NULL, 0, '{\"name\":\"username\",\"type\":\"string\"}');
INSERT INTO `interface_info` VALUES (2, 'getSexByPost', '获取用户姓名', 'http://localhost:8123/api/sex/getSexByPost', '{\"Content-Type\":\"application/json\"}', '{\"Content-Type\":\"application/json\"}', 1, 'POST', 1, '2022-02-14 13:32:10', '2022-08-29 02:16:44', 0, '{\"name\":\"sex\",\"type\":\"int\"}');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(0) NOT NULL DEFAULT 0 COMMENT '性别（0-男, 1-女）',
  `education` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学历',
  `place` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地点',
  `job` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职业',
  `contact` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `loveExp` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '感情经历',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容（个人介绍）',
  `photo` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '照片地址',
  `reviewStatus` int(0) NOT NULL DEFAULT 0 COMMENT '状态（0-待审核, 1-通过, 2-拒绝）',
  `reviewMessage` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核信息',
  `viewNum` int(0) NOT NULL DEFAULT 0 COMMENT '浏览数',
  `thumbNum` int(0) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `userId` bigint(0) NOT NULL COMMENT '创建用户 id',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isDelete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '帖子' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `userAccount` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `userAvatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别',
  `userRole` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin',
  `userPassword` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isDelete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `accessKey` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `secretKey` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_userAccount`(`userAccount`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'xuanxuan', '12345678', 'https://bucket.4ce.cn/storage/v1/ded7441922980e4439f161c117d37829.webp', 0, 'admin', '75a24cafc86f95204f0b2bcf40b19512', '2024-03-21 21:39:45', '2024-03-25 15:31:31', 0, 'xuanxuan', 'abcd');
INSERT INTO `user` VALUES (2, 'test', 'xuan', 'asdasd', 0, 'user', 'f307decdb741a95aee98ddef360d28ad', '2024-03-25 13:43:48', '2024-04-01 11:42:47', 0, '05a557c3d4d2bc42860f80904a07a51a', '7427a7d9f07422cff56aaa648b47f513');
INSERT INTO `user` VALUES (3, 'zwx', 'admin', 'https://bucket.4ce.cn/storage/v1/ded7441922980e4439f161c117d37829.webp', 0, 'admin', 'b0dd3697a192885d7c055db46155b26a', '2024-04-01 11:41:58', '2024-04-01 11:42:47', 0, '61d1695347e5c90e5afb25c500723f0d', 'a01af3e6cd15a0565e2a7ac04f6d71a6');

-- ----------------------------
-- Table structure for user_interface_info
-- ----------------------------
DROP TABLE IF EXISTS `user_interface_info`;
CREATE TABLE `user_interface_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `userId` bigint(0) NOT NULL,
  `interfaceId` bigint(0) NOT NULL,
  `totalNum` int(0) NOT NULL DEFAULT 0,
  `leftNum` int(0) NULL DEFAULT 0,
  `status` int(0) NULL DEFAULT NULL COMMENT '0-正常，1-禁用',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `idDelete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '0-未删，1-已删',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_interface_info
-- ----------------------------
INSERT INTO `user_interface_info` VALUES (1, 1, 1, 27, -2, 0, '2024-03-26 09:25:24', '2024-03-26 09:25:24', 0);
INSERT INTO `user_interface_info` VALUES (2, 2, 2, 22, 2, 0, '2024-03-29 11:27:56', '2024-03-29 11:27:56', 0);
INSERT INTO `user_interface_info` VALUES (3, 1, 2, 29, 8, 0, '2024-03-29 11:28:18', '2024-03-29 11:28:18', 0);

SET FOREIGN_KEY_CHECKS = 1;
