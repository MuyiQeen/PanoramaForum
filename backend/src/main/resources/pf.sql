/*
 Navicat Premium Dump SQL

 Source Server         : Mysql8
 Source Server Type    : MySQL
 Source Server Version : 80045 (8.0.45)
 Source Host           : localhost:3306
 Source Schema         : pf

 Target Server Type    : MySQL
 Target Server Version : 80045 (8.0.45)
 File Encoding         : 65001

 Date: 16/09/2026 14:14:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board`  (
  `board_id` int NOT NULL AUTO_INCREMENT COMMENT '板块ID',
  `board_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '板块名称',
  `creator_id` int NOT NULL COMMENT '创建者ID',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '板块详情',
  `post_count` int NULL DEFAULT 0 COMMENT '总贴数',
  `view_count` bigint NULL DEFAULT 0 COMMENT '浏览量',
  `sort_order` int NULL DEFAULT 0 COMMENT '权重',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`board_id`) USING BTREE,
  INDEX `idx_board_creator`(`creator_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '板块表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for follow_board
-- ----------------------------
DROP TABLE IF EXISTS `follow_board`;
CREATE TABLE `follow_board`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `board_id` int NOT NULL COMMENT '板块ID',
  `status` int NOT NULL COMMENT '状态',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`, `board_id`) USING BTREE,
  INDEX `idx_follow_board_board`(`board_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '板块关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for follow_post
-- ----------------------------
DROP TABLE IF EXISTS `follow_post`;
CREATE TABLE `follow_post`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `post_id` int NOT NULL COMMENT '帖子ID',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE,
  INDEX `idx_follow_post_post`(`post_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '帖子关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for follow_user
-- ----------------------------
DROP TABLE IF EXISTS `follow_user`;
CREATE TABLE `follow_user`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `follow_id` int NOT NULL COMMENT '被关注用户ID',
  `status` int NOT NULL COMMENT '状态',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`, `follow_id`) USING BTREE,
  INDEX `idx_follow_user_follow`(`follow_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for id_pool
-- ----------------------------
DROP TABLE IF EXISTS `id_pool`;
CREATE TABLE `id_pool`  (
  `seq` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `is_used` tinyint(1) NULL DEFAULT 0,
  `created_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`seq`) USING BTREE,
  UNIQUE INDEX `uk_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_used_seq`(`is_used` ASC, `seq` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for like_post
-- ----------------------------
DROP TABLE IF EXISTS `like_post`;
CREATE TABLE `like_post`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `post_id` int NOT NULL COMMENT '帖子ID',
  `status` int NOT NULL COMMENT '状态',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE,
  INDEX `idx_like_post_post`(`post_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '帖子点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for like_reply
-- ----------------------------
DROP TABLE IF EXISTS `like_reply`;
CREATE TABLE `like_reply`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `reply_id` int NOT NULL COMMENT '回复ID',
  `status` int NULL DEFAULT 0 COMMENT '状态',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`, `reply_id`) USING BTREE,
  INDEX `idx_like_reply_reply`(`reply_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '回复点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `post_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `board_id` int NOT NULL COMMENT '板块ID',
  `post_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `post_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `user_id` int NOT NULL COMMENT '发帖人ID',
  `view_counts` bigint NULL DEFAULT 0 COMMENT '浏览量',
  `reply_counts` int NULL DEFAULT 0 COMMENT '回复总量',
  `like_counts` int NULL DEFAULT 0 COMMENT '点赞数',
  `dislike_counts` int NULL DEFAULT 0 COMMENT '点踩数',
  `favorite_counts` int NULL DEFAULT 0 COMMENT '收藏数',
  `share_counts` int NULL DEFAULT 0 COMMENT '转发数',
  `is_pinned` tinyint(1) NULL DEFAULT 0 COMMENT '置顶',
  `is_featured` tinyint(1) NULL DEFAULT 0 COMMENT '精华',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `idx_post_board`(`board_id` ASC) USING BTREE,
  INDEX `idx_post_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '帖子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `replay_id` int NOT NULL AUTO_INCREMENT COMMENT '回复ID',
  `post_id` int NOT NULL COMMENT '所属帖子ID',
  `parent_id` int NOT NULL COMMENT '父回复ID',
  `reply_to_id` int NOT NULL COMMENT '被回复ID',
  `reporter_id` int NOT NULL COMMENT '回复者ID',
  `reply_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回复内容',
  `reply_counts` int NULL DEFAULT 0 COMMENT '回复总数',
  `like_counts` int NULL DEFAULT 0 COMMENT '点赞数',
  `dislike_counts` int NULL DEFAULT 0 COMMENT '点踩数',
  `is_hoted` tinyint(1) NULL DEFAULT 0 COMMENT '神评',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '软删除',
  PRIMARY KEY (`replay_id`) USING BTREE,
  INDEX `idx_reply_post`(`post_id` ASC) USING BTREE,
  INDEX `idx_reply_reporter`(`reporter_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `tag_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名',
  `tag_bgc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#808080' COMMENT '背景颜色',
  `tag_color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#000000' COMMENT '文本颜色',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`tag_id`) USING BTREE,
  UNIQUE INDEX `uk_tag_name`(`tag_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像路径',
  `role` int NULL DEFAULT 0 COMMENT '角色',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '软删除',
  `disable` tinyint(1) NULL DEFAULT 0 COMMENT '禁用',
  `points` bigint NULL DEFAULT 0 COMMENT '积分',
  `gender` int NULL DEFAULT 0 COMMENT '性别',
  `following_count` int NULL DEFAULT 0 COMMENT '关注数',
  `follower_count` int NULL DEFAULT 0 COMMENT '粉丝数',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_user_email`(`email` ASC) USING BTREE,
  INDEX `idx_user_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
