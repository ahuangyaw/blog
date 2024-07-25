# 数据库初始化


-- 创建库
create database if not exists my_db;
qrtz_blob_triggers
-- 切换库
use my_db;

-- 用户表
create table if not exists user
(
    Id           bigint auto_increment comment 'id' primary key,
    user_account varchar(256)                           not null comment '账号',
    user_password varchar(512)                           not null comment '密码',
    union_id varchar(256)                           null comment '微信开放平台id',
    mp_open_id varchar(256)                           null comment '公众号openId',
    user_name varchar(256)                           null comment '用户昵称',
    user_avatar varchar(1024)                          null comment '用户头像',
    user_profile varchar(512)                           null comment '用户简介',
    user_role varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (union_id)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    Id         bigint auto_increment comment 'id' primary key,
    Title      varchar(512)                       null comment '标题',
    Content    text                               null comment '内容',
    Tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumb_num int      default 0                 not null comment '点赞数',
    favour_num int      default 0                 not null comment '收藏数',
    user_id bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete tinyint  default 0                 not null comment '是否删除',
    index idx_userId (user_id)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    Id         bigint auto_increment comment 'id' primary key,
    post_id bigint                             not null comment '帖子 id',
    user_id bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (post_id),
    index idx_userId (user_id)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    Id         bigint auto_increment comment 'id' primary key,
    post_id bigint                             not null comment '帖子 id',
    user_id bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (post_id),
    index idx_userId (user_id)
) comment '帖子收藏';
