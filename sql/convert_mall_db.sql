# 数据库 
#创建数据库
DROP DATABASE IF EXISTS convert_mall_db;
CREATE DATABASE convert_mall_db;

#使用数据库 
use convert_mall_db;

#创建商品类型表 
CREATE TABLE mer_cate_tb(
mer_cate_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类型id',
name varchar(255) COMMENT '商品类型名称',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (mer_cate_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品类型表';

#创建商品表 
CREATE TABLE mer_tb(
mer_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
title varchar(255) COMMENT '商品名称',
old_price decimal(10,2) COMMENT '原始价格',
price decimal(11,2) COMMENT '销售价格',
stock int(11) COMMENT '库存',
sale_number  int(11) DEFAULT 0 COMMENT '销售数量',
sale_money  decimal(11,2) DEFAULT 0.00 COMMENT '销售额',
discount decimal(11,2) DEFAULT 1.00 COMMENT '折扣',
postage decimal(11,2) DEFAULT 0.00 COMMENT '包邮,默认为0包邮费',
status tinyint(4) DEFAULT 1 COMMENT '下架0,上架1，售完2',
detail longtext COMMENT '商品详情', 
mer_cate_id int(11) COMMENT '商品类型id,外键',
create_date datetime  COMMENT '商品创建时间',
update_date datetime  COMMENT '商品更新时间',
PRIMARY KEY (mer_id),
INDEX INDEX_PRICE (price) USING BTREE,
INDEX INDEX_SALENUMBER (sale_number) USING BTREE,
INDEX INDEX_SALEMONEY (sale_money) USING BTREE,
INDEX INDEX_MERCATEID (mer_cate_id) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品表';

#创建商品图片表 
CREATE TABLE mer_img_tb(
mer_img_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品图片id',
img_address varchar(255) COMMENT '商品图地址',
order_num int(11) COMMENT '排序数字',
update_date datetime  COMMENT '商品图片更新时间',
mer_id int(11) COMMENT '商品id,外键',
PRIMARY KEY (mer_img_id),
INDEX INDEX_MERID (mer_id) USING BTREE,
INDEX INDEX_ORDERNUM (order_num) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

#创建收货信息表 
CREATE TABLE receipt_info_tb(
receipt_info_id int(11) NOT NULL AUTO_INCREMENT COMMENT '收货信息id',
name varchar(255) COMMENT '收货地址姓名',
phone varchar(255) COMMENT '收货地址手机号',
address varchar(255) COMMENT '收货地址',
is_default tinyint(4) DEFAULT 0 COMMENT '默认为0不是，1是',
create_date datetime   COMMENT '创建时间',
update_date datetime   COMMENT '更新时间',
acount_id int(11) COMMENT '账户id,外键',
PRIMARY KEY (receipt_info_id),
INDEX INDEX_ACOUNTID (acount_id) USING BTREE,
INDEX INDEX_ISDEFAULT (is_default) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='收货地址表 ';

#创建商品订单表 
CREATE TABLE mer_order_tb(
mer_order_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单id',
order_number varchar(255) COMMENT '订单号',
create_date datetime  COMMENT '创建时间',
update_date datetime  COMMENT '更新时间',
acount_id int(11) COMMENT '下单人',
receipt_info_id int(11) COMMENT '收货信息ID',
PRIMARY KEY (mer_order_id),
INDEX INDEX_ACOUNTID (acount_id) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品订单表';

#创建订单商品表 
CREATE TABLE order_mer_tb(
order_mer_id int(11) NOT NULL AUTO_INCREMENT COMMENT '订单商品id',
price decimal(11,2)  COMMENT '商品单价',
number int(11)  COMMENT '商品数目',
total_price decimal(11,2)  COMMENT '商品总价',
create_date datetime  COMMENT '创建时间',
update_date datetime  COMMENT '更新时间',
mer_order_id int(11) COMMENT '商品的订单Id',
status tinyint(4) COMMENT '状态，0已下单-未支付，1已支付-未发货，2已发货-未完成，3申请退款，4已退款，5拒绝退款,6已完成',
mer_id int(11) COMMENT '商品Id',
PRIMARY KEY (order_mer_id),
INDEX INDEX_MERORDERID (mer_order_id) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='订单商品表';