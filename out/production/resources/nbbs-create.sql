DROP TABLE IF EXISTS user;
create table user(
  id int auto_increment  comment '用户id，主键自增长',
  pickname varchar(50) not null  unique comment '用户昵称,唯一标示，但可以修改',
  email varchar(50) not null unique comment '用户邮箱',
  password varchar(50) not null  comment '用户密码',
  signature varchar(100) default '这个用户很懒，什么也没有留下',
  ex int default 0 comment '用户经验',
  sex int default null comment '用户性别',
  create_time datetime not null comment '注册时间',
  phone varchar(20) default null ,
  status int comment '用户状态，1代表正常，2代表被封禁 封禁期间不能发表回复主题帖',
  avatar varchar(256) comment '用户头像所在链接',
  primary key (id),check (status in (1,2))
)character set = utf8;
DROP TABLE IF EXISTS post;
create table post(
  id int auto_increment comment '帖子id，主键自增长',
  title varchar(50) not null comment '帖子题目，非空',
  content longtext not null  comment '帖子内容',
  user_id int DEFAULT NULL,
  type int DEFAULT NULL COMMENT '0代表被封禁帖子（可申请回复），1代表可正常浏览帖子，2代表精华帖',
  topping int default 0 comment '0代表不置顶，1代表本版块置顶，2代表全论坛置顶',
  create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' not null ,
  view int(11) DEFAULT NULL COMMENT '浏览量',
  section_id int not null comment '版块id',
  primary key (id),check (type in (0,1,2)),check (topping in (0,1,2))
) character set = utf8;
DROP TABLE IF EXISTS section;
create table section(
  id int auto_increment comment '版块id',
  title varchar(10) not null comment '版块题目',
  announcement longtext not null comment '版块的公告',
  partition_id int not null comment '分区id',
  primary key (id)
) character set =utf8;
DROP TABLE IF EXISTS bbs_partition;
create table bbs_partition(
  id int auto_increment comment '分区id',
  title varchar(10) unique not null  comment '分区标题' ,
  primary key (id)
) character set =utf8;
DROP TABLE IF EXISTS comment;
create table comment (
  id int(11) NOT NULL comment '评论的id',
  content varchar(255) DEFAULT NULL COMMENT '评论正文',
  post_id int(11) not null COMMENT '评论帖子的id',
  comment_id int(11) default null comment '如果用户评论其他用户的评论，该字段将不会为空',
  user_id int(11) DEFAULT NULL COMMENT '发表评论的用户的id',
  time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (id)
)character set = utf8;
DROP TABLE IF EXISTS user_fans;
create table user_fans(
  fllow_id int not null comment '关注人的id',
  fllowed_id int not null comment '被关注人的id',
  primary key (fllow_id,fllowed_id)
)character set =utf8;
DROP TABLE IF EXISTS user_black;
create table user_black(
  black_id int not null comment '拉黑人的id',
  blacked_id int not null comment '被拉黑人的id',
  primary key (black_id,blacked_id)
)character set = utf8 ;
DROP TABLE IF EXISTS user_word;
create table user_word(
  user_id int not null comment '用户',
  word varchar(50) not null comment '屏蔽关键词',
  primary key (user_id,word)
)character set = utf8;
DROP TABLE IF EXISTS user_collection;
create table user_collection(
  user_id int not null comment '用户',
  post_id int not null comment '收藏的帖子id',
  primary key (user_id,post_id)
)character set = utf8;
DROP TABLE IF EXISTS user_report;
create table user_report(
  user_id int not null comment '用户id',
  post_id int not null comment '举报的id',
  reason int not null comment '举报的理由',
  primary key (user_id,post_id)
)character set = utf8;
DROP TABLE IF EXISTS user_like;
create table user_like(
  user_id int not null comment '用户',
  post_id int not null comment '喜欢的帖子id',
  primary key(user_id,post_id)
)character set = utf8;
DROP TABLE IF EXISTS user_unlike;
create table user_unlike(
  user_id int not null comment '用户',
  post_id int not null comment '不喜欢的帖子id',
  primary key (user_id,post_id)
)character set = utf8;
DROP TABLE IF EXISTS mask_word;
create table mask_word(
  word varchar(100) comment '系统方面的屏蔽关键词,支持正则',
  primary key (word)
)character set = utf8;
DROP TABLE IF EXISTS admin_partition;
create table admin_partition(
  user_id int not null comment '分区版主id',
  partition_id int not null comment '分区id',
  primary key (user_id,partition_id)
)character set = utf8;
DROP TABLE IF EXISTS admin_section;
create table admin_section(
  user_id int not null comment '版主id',
  section_id int not null comment '版块id',
  primary key (user_id,section_id)
)character set = utf8;