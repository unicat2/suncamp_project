use positive_energy;
-- 用户信息表
-- deleted:1代表可用，0代表删除
DROP TABLE IF EXISTS login_user;
CREATE TABLE login_user
(
    phone_number varchar(18) not null,
    name         varchar(10)          DEFAULT NULL COMMENT '姓名',
    password     varchar(60)          DEFAULT NULL COMMENT '密码',
    person_id    varchar(18)          DEFAULT NULL COMMENT '身份证号码',
    deleted      tinyint     not null default 1 COMMENT '是否删除(1未删除；0已删除)',
    PRIMARY KEY (phone_number)
);


-- 作者信息表
DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id                  int(14) not null COMMENT '表单ID' auto_increment,
    writer_phone_number varchar(18) not null COMMENT '填写人手机号',
    form_type           varchar(12) default null COMMENT '表单类型',
    writer_type         tinyint     default 0 comment '申报类别，0组织，1个人',
    name                varchar(10) DEFAULT NULL COMMENT '作者姓名',
    person_id           varchar(18) DEFAULT NULL COMMENT '身份证号码',
    sex                 tinyint     DEFAULT null COMMENT '性别,1男,0 女',
    phone               varchar(11) DEFAULT NULL COMMENT '手机号码',
    email               varchar(20) DEFAULT NULL COMMENT '邮箱',
-- 	组织
    rec_org             varchar(12) default null COMMENT '推荐组织',
-- 	个人
    work_place          text        default null COMMENT '工作单位',
-- 	共同
    work_type           varchar(12) default null COMMENT '单位/组织类型',
    province            varchar(8)  default null COMMENT '联系省份',
    city                varchar(8)  default null COMMENT '联系城市',
    area                varchar(8)  default null COMMENT '联系区',

    asso_detail_place   text        default null COMMENT '详细地址',
    sec_phone           varchar(11) default null COMMENT '紧急联系人/组织联系电话',
    deleted             tinyint     default 1 COMMENT '是否删除(1未删除；0已删除)',
-- 	仅榜样
-- 	img longblob default null COMMENT '头像',
    star_name           varchar(20) default null comment '榜样名称',
    commit_state        tinyint     default 0 comment '0未提交，1已提交',
    identity_type       varchar(15) default null comment '证件类型',
    PRIMARY KEY (id)
);


-- 连接
select *
from author a
         join author_img ai on a.id = ai.form_id
where a.form_type = '榜样';

DROP TABLE IF EXISTS author_img;
CREATE TABLE author_img
(
-- 	id int(14) not null COMMENT '图片ID' auto_increment,
    form_id  int(14) not null COMMENT '表单ID',
    img_path varchar(200) default null COMMENT '头像',
    PRIMARY KEY (form_id)

);



DROP TABLE IF EXISTS file_path;
CREATE TABLE file_path
(
--   id int(14) not null COMMENT '文件号' auto_increment,
    form_id      int(14) not null COMMENT '表单号',
    phone_number varchar(11) not null comment '用户手机号',
    path         varchar(200)         DEFAULT NULL COMMENT '路径',
    deleted      tinyint     not null default 1 COMMENT '是否删除(1未删除；0已删除)',
    PRIMARY KEY (form_id)
);


DROP TABLE IF EXISTS works;
CREATE TABLE works
(
    id               int(14) not null COMMENT '作品表ID',
    phone_number     varchar(11) not null comment '用户手机号',
    type             varchar(12)  default null comment '推荐类别',
    title            varchar(15)  default '' comment '作品标题',
    link             varchar(100) default null comment '作品链接',
    platform         varchar(25)  default null comment '首发平台',
    author_introduce text         default null comment '作者简介',
    work_introduce   text         default null comment '作品简介',
    social_result    text         default null comment '社会效果',
    work_view        text         default null comment '单位意见',
    PRIMARY KEY (id)
);

-- -- 添加字段 提交
-- alter table author add commit_state tinyint default 0 comment '0未提交，1已提交';
-- -- 添加字段 证件类型
-- alter table author add identity_type varchar(15) default null comment '证件类型';