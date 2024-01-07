-- yangapi.`interface_info`
use yangapi;
create table if not exists yangapi.`interface_info`
(
    `id` bigint not null auto_increment comment '接口id' primary key,
    `name` varchar(256) not null comment '接口名',
    `description` varchar(256) null comment '接口描述',
    `url` varchar(512) not null comment '接口地址',
    `requestHeader` text not null comment '请求头',
    `responseHeader` text not null comment '响应头',
    `status` int not null comment '状态码',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人id',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
    ) comment 'yangapi.`interface_info`';

insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('董伟诚', '冯弘文', 'www.jude-west.name', '崔哲瀚', '蔡越泽', 0, '傅绍辉', 61714);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('朱晓博', '杜凯瑞', 'www.lesia-feil.io', '熊煜祺', '韩子轩', 0, '徐越彬', 48104972);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('马思', '宋鹏涛', 'www.celina-wolff.org', '李鹏', '龙远航', 0, '龙煜祺', 48195753);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('王博超', '夏致远', 'www.shelton-runte.co', '程雨泽', '邓君浩', 0, '曾泽洋', 82);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('黎鸿煊', '赵梓晨', 'www.rosaura-powlowski.biz', '孙泽洋', '覃雨泽', 0, '石子轩', 3);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龙鑫磊', '秦煜祺', 'www.darcey-will.co', '徐擎苍', '刘鸿涛', 0, '覃凯瑞', 768212);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('范鹏飞', '廖峻熙', 'www.eldon-sanford.biz', '龙懿轩', '卢志泽', 0, '孔文轩', 4847);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('顾绍齐', '高伟诚', 'www.silvia-cruickshank.info', '彭昊强', '邵立诚', 0, '黄天翊', 183167);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('阎擎宇', '莫航', 'www.vincent-keebler.name', '姚鑫磊', '魏鑫鹏', 0, '潘荣轩', 584860645);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('贾昊焱', '覃思', 'www.nilda-heller.com', '严嘉懿', '赖耀杰', 0, '韩擎宇', 258);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('钟明杰', '傅子轩', 'www.eldridge-mitchell.com', '程擎苍', '任皓轩', 0, '白致远', 972634);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('莫彬', '冯笑愚', 'www.fredricka-langosh.name', '武琪', '孟哲瀚', 0, '沈晋鹏', 4691394310);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('魏雨泽', '邹立诚', 'www.kerri-herman.com', '田越彬', '胡弘文', 0, '莫晓博', 1924263);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('蔡梓晨', '余博文', 'www.adrian-jacobson.com', '杨炎彬', '方泽洋', 0, '金弘文', 8262);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陈振家', '贾煜祺', 'www.brendon-heller.com', '陈文昊', '赖文博', 0, '梁明轩', 5323660449);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('孙聪健', '廖俊驰', 'www.shawnta-corkery.co', '曹皓轩', '张振家', 0, '蔡鹏煊', 998095);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('贺果', '黄思聪', 'www.oliva-mertz.biz', '赵雨泽', '陶嘉熙', 0, '崔驰', 218698922);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('刘昊天', '莫楷瑞', 'www.efrain-jast.io', '赵昊天', '孔黎昕', 0, '曹博超', 32);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('姜擎苍', '蔡炫明', 'www.taisha-gusikowski.name', '侯昊强', '蒋语堂', 0, '钟志泽', 343);
insert into yangapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('黎鸿涛', '王擎苍', 'www.risa-mckenzie.org', '余煜祺', '吕明杰', 0, '武凯瑞', 63164371);


create table if not exists yangapi.`user_interface_info`
(
    `id` bigint not null auto_increment comment 'id' primary key,
    `userId` bigint not null comment '调用用户id',
    `interfaceInfoId` bigint not null comment '接口id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment 'yangapi.`user_interface_info`';