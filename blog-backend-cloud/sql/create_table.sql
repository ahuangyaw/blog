# 数据库初始化

-- 创建库
create database if not exists blog;

-- 切换库
use blog;

-- 用户表
create table if not exists user
(
    id            bigint auto_increment comment 'id' primary key,
    user_account  varchar(256)                           not null comment '账号',
    user_password varchar(512)                           not null comment '密码',
    union_id      varchar(256)                           null comment '微信开放平台id',
    mp_open_id    varchar(256)                           null comment '公众号openId',
    nickname      varchar(256)                           null comment '用户昵称',
    gender        tinyint(1)                             not null default 0 comment '用户性别(0,未定义,1,男,2女)',
    user_avatar   varchar(1024)                          null comment '用户头像',
    user_profile  varchar(512)                           null comment '用户简介',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (union_id)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    id            bigint auto_increment comment 'id' primary key,
    Title         varchar(512)                                                   null comment '标题',
    Content       text                                                           null comment '内容',
    Tags          varchar(1024)                                                  null comment '标签列表（json 数组）',
    article_cover varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci not null comment '文章缩略图',
    is_top        tinyint                                                        not null comment '是否置顶 (0否 1是）',
    status        tinyint                                                        not null comment '文章状态 (1公开 2私密 3草稿)',
    visit_count   bigint                                                         not null default 0 comment '访问量',
    thumb_num     int      default 0                                             not null comment '点赞数',
    favour_num    int      default 0                                             not null comment '收藏数',
    user_id       bigint                                                         not null comment '创建用户 id',
    create_time   datetime default CURRENT_TIMESTAMP                             not null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP                             not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0                                             not null comment '是否删除',
    index idx_userId (user_id)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id          bigint auto_increment comment 'id' primary key,
    post_id     bigint                             not null comment '帖子 id',
    user_id     bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (post_id),
    index idx_userId (user_id)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id          bigint auto_increment comment 'id' primary key,
    post_id     bigint                             not null comment '帖子 id',
    user_id     bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (post_id),
    index idx_userId (user_id)
) comment '帖子收藏';

-- 帖子评论表
DROP TABLE IF EXISTS post_comment;
CREATE TABLE post_comment
(
    id              bigint                                                NOT NULL AUTO_INCREMENT COMMENT '评论id',
    type            tinyint(1)                                            NOT NULL COMMENT '评论类型 (1文章 2留言板)',
    type_id         bigint                                                NOT NULL COMMENT '类型id',
    parent_id       bigint                                                NULL     DEFAULT NULL COMMENT '父评论id',
    reply_id        bigint                                                NULL     DEFAULT NULL COMMENT '回复评论id',
    comment_content text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论的内容',
    comment_user_id bigint                                                NOT NULL COMMENT '评论用户的id',
    reply_user_id   bigint                                                NULL     DEFAULT NULL COMMENT '回复用户的id',
    is_check        tinyint(1)                                            NOT NULL DEFAULT 1 COMMENT '是否通过 (0否 1是)',
    create_time     datetime                                              NOT NULL COMMENT '评论时间',
    update_time     datetime                                              NOT NULL COMMENT '更新时间',
    is_deleted      tinyint(1)                                            NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：已删除）',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 57
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

