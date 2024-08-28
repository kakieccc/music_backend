create table admin
(
    id       int unsigned auto_increment
        primary key,
    name     varchar(45) not null comment '管理员昵称',
    password varchar(45) not null comment '管理员密码',
    constraint name_UNIQUE
        unique (name)
)
    charset = utf8mb3;

create table banner
(
    id  int auto_increment comment '轮播图id'
        primary key,
    pic varchar(255) not null comment '轮播图图片'
)
    charset = utf8mb3;

create table collect
(
    id           int unsigned auto_increment
        primary key,
    user_id      int unsigned             not null comment '我喜欢_对应的userid',
    type         tinyint                  not null comment '收藏的类型_歌曲/歌单',
    song_id      int unsigned             null comment '收藏的歌曲id',
    song_list_id int unsigned             null comment '收藏的歌单id',
    create_time  datetime default (now()) not null
)
    charset = utf8mb3;

create table comment
(
    id           int unsigned auto_increment
        primary key,
    user_id      int unsigned                           not null comment '评论对应userid',
    song_id      int unsigned                           null comment '评论对应歌曲id',
    song_list_id int unsigned                           null comment '评论对应歌单id',
    content      varchar(255)                           not null comment '评论内容',
    create_time  datetime     default CURRENT_TIMESTAMP not null,
    type         tinyint                                not null,
    up           int unsigned default '0'               not null comment '点赞数'
)
    charset = utf8mb3;

create table consumer
(
    id           int unsigned auto_increment
        primary key,
    username     varchar(255)             not null comment '用户名',
    password     varchar(100)             not null comment '密码',
    sex          tinyint                  not null comment '性别',
    phone_num    char(15)                 null comment '电话号',
    email        char(30)                 null comment '邮箱',
    birth        datetime                 not null comment '生日',
    introduction varchar(255)             null comment '简介',
    location     varchar(45)              null comment '地址',
    avator       varchar(255)             null comment '头像',
    create_time  datetime default (now()) not null,
    update_time  datetime default (now()) not null,
    constraint email_UNIQUE
        unique (email),
    constraint phone_num_UNIQUE
        unique (phone_num),
    constraint username_UNIQUE
        unique (username)
)
    charset = utf8mb3;

create table list_song
(
    id           int unsigned auto_increment
        primary key,
    song_id      int unsigned not null comment '歌曲id',
    song_list_id int unsigned not null comment '歌单id'
)
    charset = utf8mb3;

create table rank_list
(
    id           bigint unsigned auto_increment
        primary key,
    song_list_id bigint unsigned          not null,
    consumer_id  bigint unsigned          not null,
    score        int unsigned default '0' not null,
    constraint consumerId
        unique (consumer_id, song_list_id)
)
    charset = utf8mb3;

create table singer
(
    id           int unsigned auto_increment
        primary key,
    name         varchar(45)  not null comment '歌手名',
    sex          tinyint      null comment '歌手性别',
    pic          varchar(255) null comment '歌手图片',
    birth        datetime     null comment '歌手生日',
    location     varchar(45)  null comment '歌手ip',
    introduction varchar(255) null comment '歌手简介'
)
    charset = utf8mb3;

create table song
(
    id           int unsigned auto_increment
        primary key,
    singer_id    int unsigned             not null comment '歌手id',
    name         varchar(45)              not null comment '歌曲名',
    introduction varchar(255)             null comment '歌曲介绍',
    create_time  datetime default (now()) not null comment '发行时间',
    update_time  datetime default (now()) not null,
    pic          varchar(255)             null comment '歌曲封面',
    lyric        text                     null comment '歌词',
    url          varchar(255)             not null comment '歌曲url'
)
    charset = utf8mb3;

create table song_list
(
    id           int unsigned auto_increment
        primary key,
    title        varchar(255)             not null comment '歌单名',
    pic          varchar(255)             null comment '歌单封面',
    introduction text                     null comment '歌单介绍',
    style        varchar(10) default '无' null comment '风格'
)
    charset = utf8mb3;

create table user_support
(
    id         int auto_increment
        primary key,
    comment_id int         not null comment '评论id',
    user_id    varchar(45) not null comment 'userid'
)
    charset = latin1;

