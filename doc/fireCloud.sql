drop SCHEMA if exists  `cardapi`;
CREATE SCHEMA `cardapi` ;


use cardapi;



drop table if exists  `account`;

CREATE TABLE `cardapi`.`account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` VARCHAR(50) NOT NULL COMMENT '账号',
  `pwd` VARCHAR(50) NOT NULL COMMENT '密码',
  `nickName` VARCHAR(45) NOT NULL COMMENT '昵称',
  `mobile` VARCHAR(11) NULL COMMENT '手机',
  `mobileValidate` INT(1) NULL DEFAULT 0 COMMENT '手机是否验证',
  `email` VARCHAR(50) NULL COMMENT '邮箱',
  `emailValidate` INT(1) NULL DEFAULT 0 COMMENT '邮箱是否验证',
  `name` VARCHAR(50) NULL COMMENT '姓名',
  `certificates` INT(1) NULL COMMENT '证件类型',
  `cardNum` VARCHAR(50) NULL COMMENT '证件号码',
  `createTime` DATETIME NULL COMMENT '注册时间',
  `lastLoginTime` DATETIME NULL COMMENT '最后登录时间',
  `lastLoginIP` VARCHAR(20) NULL COMMENT '最后登录IP',
  `IPCity` VARCHAR(20) NULL COMMENT 'IP所属城市',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `account_UNIQUE` (`account` ASC),
  UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `cardNum_UNIQUE` (`cardNum` ASC)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '账号表';




drop table if exists  `delivery`;

CREATE TABLE `cardapi`.`delivery` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址ID',
  `accountId` INT(11) NOT NULL COMMENT '账号ID',
  `defaultFlg` INT(1) NULL DEFAULT 0 COMMENT '默认地址',
  `name` VARCHAR(50) NULL COMMENT '收货人',
  `mobile` VARCHAR(30) NULL COMMENT '手机',
  `phone` VARCHAR(30) NULL COMMENT '电话',
  `country` VARCHAR(20) NULL COMMENT '国家',
  `provice` VARCHAR(20) NULL COMMENT '省',
  `city` VARCHAR(20) NULL COMMENT '市',
  `area` VARCHAR(20) NULL COMMENT '区',
  `address` VARCHAR(200) NULL COMMENT '地址',
  `zipCode` VARCHAR(10) NULL COMMENT '邮编',
  PRIMARY KEY (`id`),
  INDEX `delivery_accountId` (`accountId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '收货地址表';





drop table if exists  `member`;

CREATE TABLE `cardapi`.`member` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `accountId` INT(11) NOT NULL COMMENT '账号ID',
  `memberName` varchar(100) NOT NULL COMMENT '会员名称',
  `product` VARCHAR(200) NULL COMMENT '主要产品',
  `notice` VARCHAR(400) NULL COMMENT '会员公告',
  `aboutUs` TEXT(1000) NULL COMMENT '会员简介',
  `licensePath` VARCHAR(500) NULL COMMENT '营业执照路径',
  `operator` VARCHAR(30) NULL COMMENT '经营者',
  `mobile` VARCHAR(50) NULL COMMENT '手机',
  `phone` VARCHAR(50) NULL COMMENT '公司电话',
  `fax` VARCHAR(50) NULL COMMENT '传真',
  `email` VARCHAR(150) NULL COMMENT '联系邮箱',
  `qq` VARCHAR(50) NULL COMMENT 'QQ客服',
  `reputation` int(11) NULL COMMENT '商家信誉度',
  `logoPath` VARCHAR(500) NULL COMMENT 'Logo图片地址',
  `topPicPath` VARCHAR(500) NULL COMMENT '顶部图片地址',
  `frontPicPath` VARCHAR(500) NULL COMMENT '正面照图片地址',
  `guarantee` INT(1) NULL DEFAULT 0 COMMENT '是否平台担保',
  `highQuality` INT(1) NULL DEFAULT 0 COMMENT '是否优质商家',
  `sincerity` INT(1) NULL DEFAULT 0 COMMENT '是否诚信示范',
  `returnGoods` INT(1) NULL DEFAULT 0 COMMENT '是否支持退换货',
  `popularize` INT(1) NULL DEFAULT 0 COMMENT '是否推广（搜索时靠前）',
  `province` VARCHAR(20) NULL COMMENT '省',
  `city` VARCHAR(20) NULL COMMENT '市',
  `area` VARCHAR(20) NULL COMMENT '区',
  `address` VARCHAR(200) NULL COMMENT '地址',
  `enterTime` DATETIME NULL COMMENT '入驻时间',
  `lastUpdateTime` DATETIME NULL COMMENT '最后修改时间',
  `isDel` INT(1) NULL DEFAULT 0 COMMENT '是否废除 0:否；1：是',
  `isRel` INT(1) NULL DEFAULT 0 COMMENT '是否真 0:否；1：是',
  `index_status` INT(1) NULL DEFAULT 0 COMMENT '是否建立lucene索引 0:否；1：是',
  PRIMARY KEY (`id`),
  UNIQUE `memberName` (`memberName`),
  INDEX `index_status` (`index_status`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '会员表';




drop table if exists  `member_pic`;

CREATE TABLE `cardapi`.`member_pic` (
  `memberId` INT(11) NOT NULL COMMENT '会员ID',
  `fileId` INT(11) NOT NULL COMMENT '文件ID',
  `createTime` DATETIME NULL COMMENT '创建时间',
  INDEX `member_pic_memberId` (`memberId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '会员图片';


drop table if exists  `file`;
CREATE TABLE `cardapi`.`file` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `origialName` VARCHAR(500) NULL COMMENT '原始名称',
  `finalName` VARCHAR(500) NULL COMMENT '最终名称',
  `suffix` VARCHAR(20) NULL COMMENT '后缀',
  `rule` INT(2) NOT NULL COMMENT '名称加密方式',
  `type` INT(2) NOT NULL COMMENT '文档类型',
  `storeType` INT(2) NULL COMMENT '存储类型',
  `createTime` DATETIME NULL COMMENT '创建时间',
  INDEX `type_IND` (`type`),
  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '文件管理';



drop table if exists  `member_category`;

CREATE TABLE `cardapi`.`member_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '会员类目ID',
  `memberId` INT(11) NOT NULL COMMENT '会员ID',
  `industry` INT(11) NULL COMMENT '所在行业第一级',
  `categoryDict` INT(11) NULL COMMENT '所在行业',
  `categoryEntry` INT(11) NULL COMMENT '行业类目',
  `industryName` VARCHAR(50) NULL COMMENT '所在行业第一级名称',
  `dictName` VARCHAR(50) NULL COMMENT '行业类目名称',
  `entryName` VARCHAR(50) NULL COMMENT '行业类目名称',
  PRIMARY KEY (`id`),
  INDEX `member_category_memberId` (`memberId`),
  INDEX `industry_IND` (`industry`),
  INDEX `member_category_industry` (`categoryDict`),
  INDEX `member_category_category` (`categoryEntry`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '会员类目表';



drop table if exists  `popularize_dict`;

CREATE TABLE `cardapi`.`popularize_dict` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '推广字典ID',
  `popularizeCode` VARCHAR(50) NULL COMMENT '推广代号',
  `name` VARCHAR(50) NULL COMMENT '推广名称',
  `type` INT(3) NULL COMMENT '推广类型',
  `status` INT(1) NULL DEFAULT 0 COMMENT '推广状态',
  `price` DECIMAL(10,2) NULL COMMENT '推广金额',
  `pitNum` INT(2) NULL COMMENT '推广坑位数',
  `subPitNum` INT(2) NULL COMMENT '企业+商品类型的商品坑位',
  `sort` INT(2) NULL COMMENT '字典排序',
  `industry` INT(11) NULL COMMENT '所在行业第一级',
  `categoryDict` INT(11) NULL COMMENT '所在行业',
  `categoryEntry` INT(11) NULL COMMENT '行业类目',
  `createTime` DATETIME NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '推广字典';



drop table if exists  `popularize_banner`;
CREATE TABLE `cardapi`.`popularize_banner` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '轮播ID',
  `popularizeId` VARCHAR(50) NULL COMMENT '推广代号',
  `href` VARCHAR(300) NULL COMMENT '跳转地址',
  `status` INT(1) NULL DEFAULT 0 COMMENT '推广状态',
  `picPath` VARCHAR(300) NULL COMMENT '轮播图片地址',
  `createTime` DATETIME NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `popularize_banner_popularizeId` (`popularizeId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '轮播';


drop table if exists  `popularize_product`;
CREATE TABLE `cardapi`.`popularize_product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `popularizeId` INT(11) NOT NULL COMMENT '推广字典ID',
  `productHref` VARCHAR(300) NULL COMMENT '跳转地址',
  `picPath` VARCHAR(300) NULL COMMENT '图片地址',
  `title` VARCHAR(50) NULL COMMENT '名称',
  `status` INT(1) NULL DEFAULT 0 COMMENT '推广状态',
  `shortTitle` VARCHAR(50) NULL COMMENT '缩略名称',
  `description` VARCHAR(450) NULL COMMENT '描述',
  `price` DECIMAL(10,2) NULL COMMENT '商品价格',
  `productId`  INT(11) NULL COMMENT '商品ID',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `startTime` DATETIME NULL COMMENT '开始时间',
  `day` INT(3) NULL COMMENT '持续天数',
  `endTime` DATETIME NULL COMMENT '结束时间',
  `sort` INT(2) NULL COMMENT '字典排序',
  PRIMARY KEY (`id`),
  INDEX `popularize_product_popularizeId` (`popularizeId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '推广商品';



drop table if exists  `popularize_member`;

CREATE TABLE `cardapi`.`popularize_member` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '推广实体ID',
  `popularizeId` INT(11) NULL COMMENT '推广字典ID',
  `memberHref` VARCHAR(300) NULL COMMENT '推广地址',
  `picPath` VARCHAR(300) NULL COMMENT '图片地址',
  `logoPath` VARCHAR(300) NULL COMMENT 'Logo地址',
  `description` VARCHAR(450) NULL COMMENT '描述',
  `status` INT(1) NULL DEFAULT 0 COMMENT '推广状态',
  `startTime` DATETIME NULL COMMENT '开始时间',
  `day` INT(3) NULL COMMENT '持续天数',
  `endTime` DATETIME NULL COMMENT '结束时间',
  `sort` INT(2) NULL COMMENT '字典排序',
  `memberId`  INT(11) NULL COMMENT '会员ID',
  `createTime` DATETIME NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `popularize_member_popularizeId` (`popularizeId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '推广企业';




drop table if exists  `popularize_member_prod`;
CREATE TABLE `cardapi`.`popularize_member_prod` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '推广企业ID',
  `popularizeId` INT(11) NULL COMMENT '推广字典ID',
  `memberHref` VARCHAR(300) NULL COMMENT '推广地址',
  `picPath1` VARCHAR(300) NULL COMMENT '图片地址1',
  `picPath2` VARCHAR(300) NULL COMMENT '图片地址2',
  `picPath3` VARCHAR(300) NULL COMMENT '图片地址3',
  `logoPath` VARCHAR(300) NULL COMMENT 'Logo地址',
  `description1` VARCHAR(450) NULL COMMENT '描述1',
  `description2` VARCHAR(450) NULL COMMENT '描述2',
  `description3` VARCHAR(450) NULL COMMENT '描述3',
  `status` INT(1) NULL DEFAULT 0 COMMENT '推广状态',
  `memberId`  INT(11) NULL COMMENT '会员ID',
  `startTime` DATETIME NULL COMMENT '开始时间',
  `day` INT(3) NULL COMMENT '持续天数',
  `endTime` DATETIME NULL COMMENT '结束时间',
  `sort` INT(2) NULL COMMENT '字典排序',
  `createTime` DATETIME NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `popularize_member_prod_popularizeId` (`popularizeId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '推广企业和商品企业表';


drop table if exists  `popularize_member_prod_sub`;
CREATE TABLE `cardapi`.`popularize_member_prod_sub` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '推广商品ID',
  `memberProdId` INT(11) NULL COMMENT '推广企业商品ID',
  `productHref` VARCHAR(300) NULL COMMENT '跳转地址',
  `picPath` VARCHAR(300) NULL COMMENT '图片地址',
  `title` VARCHAR(50) NULL COMMENT '名称',
  `shortTitle` VARCHAR(50) NULL COMMENT '缩略名称',
  `description` VARCHAR(450) NULL COMMENT '描述',
  `status` INT(1) NULL DEFAULT 0 COMMENT '推广状态',
  `startTime` DATETIME NULL COMMENT '开始时间',
  `day` INT(3) NULL COMMENT '持续天数',
  `endTime` DATETIME NULL COMMENT '结束时间',
  `sort` INT(2) NULL COMMENT '字典排序',
  `price` DECIMAL(10,2) NULL COMMENT '商品价格',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `productId`  INT(11) NULL COMMENT '商品ID',
  PRIMARY KEY (`id`),
  INDEX `popularize_member_prod_sub_memberProdId` (`memberProdId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '推广企业和商品商品表';





drop table if exists  `auth_func`;

CREATE TABLE `cardapi`.`auth_func` (
  `funcId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `funcName` VARCHAR(50) NULL COMMENT '功能名称',
  `url` VARCHAR(300) NULL COMMENT '链接',
  `funcGroupId` VARCHAR(50) NULL COMMENT '功能组',
  `parentId` INT(11) NULL COMMENT '父功能ID',
  `tag` VARCHAR(50) NULL COMMENT '小标签',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `optName` Varchar(50) NULL COMMENT '操作人',
  `remark` VARCHAR(200) NULL COMMENT '备注',
  PRIMARY KEY (`funcId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '功能表';




drop table if exists  `auth_role`;

CREATE TABLE `cardapi`.`auth_role` (
  `roleId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `roleName` VARCHAR(50) NULL COMMENT '角色名称',
  `status` INT(1) NULL DEFAULT 0 COMMENT '是否可用',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `optName` Varchar(50) NULL COMMENT '操作人',
  `remark` VARCHAR(200) NULL COMMENT '备注',
  PRIMARY KEY (`roleId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '角色表';




drop table if exists  `auth_operator`;

CREATE TABLE `cardapi`.`auth_operator` (
  `optId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `badge` VARCHAR(50) NOT NULL COMMENT '工号',
  `name` VARCHAR(50) NULL COMMENT '员工名称',
  `pwd` VARCHAR(50) NULL COMMENT '密码',
  `status` INT(1) NULL COMMENT '是否可用',
  `mobil` VARCHAR(20) NULL COMMENT '电话',
  `email` VARCHAR(50) NULL COMMENT '邮箱',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `optName` Varchar(50) NULL COMMENT '操作人',
  `remark` VARCHAR(200) NULL COMMENT '备注',
  PRIMARY KEY (`optId`),
  UNIQUE INDEX `badge_UNIQUE` (`badge` ASC)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '员工表';




drop table if exists  `auth_optrole`;

CREATE TABLE `cardapi`.`auth_optrole` (
  `roleId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'roleID',
  `optId` INT(11) NULL COMMENT 'optID',
  `partId` INT(11) NULL COMMENT '兼职roleID',
  `status` INT(1) NULL DEFAULT 0 COMMENT '兼职是否有效',
  `partEndTime` DATETIME NULL COMMENT '兼职结束时间',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `optName` VARCHAR(50) NULL COMMENT '操作人',
  `remark` VARCHAR(200) NULL COMMENT '备注',
  PRIMARY KEY (`roleId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '员工角色绑定';




drop table if exists  `auth_rolefunc`;

CREATE TABLE `cardapi`.`auth_rolefunc` (
  `roleId` INT(11) NOT NULL COMMENT 'roleID',
  `funcId` INT(11) NOT NULL COMMENT 'funcID',
  `privilege` INT(2) NULL COMMENT '权限',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `optName` VARCHAR(50) NULL COMMENT '操作人',
  `remark` VARCHAR(200) NULL COMMENT '备注') ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '角色功能绑定';




drop table if exists  `commodity`;

CREATE TABLE `cardapi`.`commodity` (
  `id` bigint(20) NOT NULL COMMENT '商品ID',
  `memberId` INT(11) NOT NULL COMMENT '商家ID',
  `commodityCategory1` VARCHAR(30) NULL COMMENT '商品所属类目1',
  `commodityCategory2` VARCHAR(30) NULL COMMENT '商品所属类目2',
  `commodityCategory3` VARCHAR(30) NULL COMMENT '商品所属类目3',
  `commodityName` VARCHAR(200) NULL COMMENT '商品名称',
  `brand` VARCHAR(100) NULL COMMENT '品牌',
  `price` DECIMAL(10,2) NULL COMMENT '价格',
  `quantity` BIGINT(20) NULL COMMENT '数量',
  `uom` VARCHAR(30) NULL COMMENT '单位',
  `color` VARCHAR(300) NULL COMMENT '颜色',
  `size` VARCHAR(300) NULL COMMENT '尺寸',
  `saleState` VARCHAR(10) NULL COMMENT '销售状态',
  `goodFlg` INT(1) NULL COMMENT '好货',
  `hotFlg` INT(1) NULL COMMENT '热卖',
  `choiceFlg` INT(1) NULL COMMENT '精选',
  `appreciateFlg` INT(1) NULL COMMENT '好评',
  `picture1` VARCHAR(200) NULL COMMENT '商品实拍图片1',
  `picture2` VARCHAR(200) NULL COMMENT '商品实拍图片2',
  `picture3` VARCHAR(200) NULL COMMENT '商品实拍图片3',
  `picture4` VARCHAR(200) NULL COMMENT '商品实拍图片4',
  `picture5` VARCHAR(200) NULL COMMENT '商品实拍图片5',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `createOpt` VARCHAR(50) NULL COMMENT '创建者',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `updateOpt` VARCHAR(50) NULL COMMENT '更新者',
  `index_status` INT(1) NULL DEFAULT 0 COMMENT '是否建立lucene索引 0:否；1：是',
  PRIMARY KEY (`id`),
  INDEX `commodity_memberId` (`memberId`),
  INDEX `commodity_commodityCategory1` (`commodityCategory1`),
  INDEX `commodity_commodityCategory2` (`commodityCategory2`),
  INDEX `commodity_commodityCategory3` (`commodityCategory3`),
  INDEX `commodity_commodityName` (`commodityName`),
  INDEX `commodity_brand` (`brand`),
  INDEX `commodity_price` (`price`),
  INDEX `commodity_color` (`color`),
  INDEX `commodity_size` (`size`),
  INDEX `commodity_saleState` (`saleState`),
  INDEX `commodity_goodFlg` (`goodFlg`),
  INDEX `commodity_hotFlg` (`hotFlg`),
  INDEX `commodity_choiceFlg` (`choiceFlg`),
  INDEX `commodity_index_status` (`index_status`),
  INDEX `commodity_appreciateFlg` (`appreciateFlg`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '商品表';

drop table if exists  `commodity_show`;

CREATE TABLE `cardapi`.`commodity_show` (
  `commodityId` bigint(20) NOT NULL COMMENT '商品ID',
  `commodityIntroduction` VARCHAR(2000) NULL COMMENT '商品介绍',
  `packingSpecification` VARCHAR(2000) NULL COMMENT '规格与包装',
  `instructions` VARCHAR(2000) NULL COMMENT '使用说明',
  `customerService` VARCHAR(400) NULL COMMENT '购买及售后',
  `showPagePath` VARCHAR(200) NULL COMMENT '展示页面路径',
  `showPageSource` LONGTEXT NULL COMMENT '展示页面代码',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `createOpt` VARCHAR(50) NULL COMMENT '创建者',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `updateOpt` VARCHAR(50) NULL COMMENT '更新者',
  PRIMARY KEY (`commodityId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '商品展示表';

drop table if exists  `commodity_price`;

CREATE TABLE `cardapi`.`commodity_price` (
  `commodityId` bigint(20) NOT NULL COMMENT '商品ID',
  `quantityStart` BIGINT(20) DEFAULT 1 COMMENT '起始数量',
  `quantityEnd` BIGINT(20) NULL COMMENT '终止数量',
  `price` DECIMAL(10,2) NULL COMMENT '价格',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `createOpt` VARCHAR(50) NULL COMMENT '创建者',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `updateOpt` VARCHAR(50) NULL COMMENT '更新者',
  INDEX `commodity_price_commodityId` (`commodityId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '商品价格关联表';

drop table if exists  `commodity_color`;

CREATE TABLE `cardapi`.`commodity_color` (
  `commodityId` bigint(20) NOT NULL COMMENT '商品ID',
  `color` VARCHAR(30) NULL COMMENT '颜色',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `createOpt` VARCHAR(50) NULL COMMENT '创建者',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `updateOpt` VARCHAR(50) NULL COMMENT '更新者',
  INDEX `commodity_color_commodityId` (`commodityId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '商品颜色关联表';

drop table if exists  `commodity_size`;

CREATE TABLE `cardapi`.`commodity_size` (
  `commodityId` bigint(20) NOT NULL COMMENT '商品ID',
  `size` VARCHAR(30) NULL COMMENT '尺寸',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `createOpt` VARCHAR(50) NULL COMMENT '创建者',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `updateOpt` VARCHAR(50) NULL COMMENT '更新者',
  INDEX `commodity_size_commodityId` (`commodityId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '商品尺寸关联表';


drop table if exists  `product_pic`;
CREATE TABLE `cardapi`.`product_pic` (
  `productId` INT(11) NOT NULL COMMENT '会员ID',
  `fileId` INT(11) NOT NULL COMMENT '文件ID',
  `createTime` DATETIME NULL COMMENT '创建时间',
  INDEX `product_pic_productId` (`productId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '商品图片';

drop table if exists  `member_industry`;
CREATE TABLE `cardapi`.`member_industry` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `industryName` VARCHAR(50) NULL,
  `status` INT(1) default 1 COMMENT '类目状态',
  `createTime` DATETIME NULL,
  `createOpt` INT(11) NULL,
  `updateTime` DATETIME NULL,
  `updateOpt` INT(11) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `industryName_UNIQUE` (`industryName` ASC))ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '行业名称';

drop table if exists  `member_category_dict`;
CREATE TABLE `cardapi`.`member_category_dict` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `industryId` INT(11) NULL,
  `dictName` VARCHAR(50) NULL,
  `status` INT(1) default 1 COMMENT '类目状态',
  `createTime` DATETIME NULL,
  `createOpt` INT(11) NULL,
  `updateTime` DATETIME NULL,
  `updateOpt` INT(11) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `dictName_UNIQUE` (`industryId`,`dictName` ASC))ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '行业名称';

drop table if exists  `member_category_entry`;
CREATE TABLE `cardapi`.`member_category_entry` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `dictId` INT(11) NULL,
  `entryName` VARCHAR(50) NULL,
  `status` INT(1) default 1 COMMENT '类目状态',
  `createTime` DATETIME NULL,
  `createOpt` INT(11) NULL,
  `updateTime` DATETIME NULL,
  `updateOpt` INT(11) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `dictId_Name` (`dictId` , `entryName`)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '行业分类细分';

drop table if exists  `commodityCategoryA`;

CREATE TABLE `cardapi`.`commodityCategoryA` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `categoryName` VARCHAR(50) NULL COMMENT '类目名称',
  `status` INT(1) NULL COMMENT '类目状态',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `createOpt` VARCHAR(50) NULL COMMENT '创建者',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `updateOpt` VARCHAR(50) NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  INDEX `commodityCategoryA_id` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '商品一级类目表';

drop table if exists  `commodityCategoryB`;

CREATE TABLE `cardapi`.`commodityCategoryB` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `categoryAId` INT(11) NOT NULL COMMENT '一级类目ID',
  `categoryName` VARCHAR(50) NULL COMMENT '类目名称',
  `status` INT(1) NULL COMMENT '类目状态',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `createOpt` VARCHAR(50) NULL COMMENT '创建者',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `updateOpt` VARCHAR(50) NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  INDEX `commodityCategoryB_id` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '商品二级类目表';

drop table if exists  `commodityCategoryC`;

CREATE TABLE `cardapi`.`commodityCategoryC` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `categoryBId` INT(11) NOT NULL COMMENT '二级类目ID',
  `categoryName` VARCHAR(50) NULL COMMENT '类目名称',
  `status` INT(1) NULL COMMENT '类目状态',
  `createTime` DATETIME NULL COMMENT '创建时间',
  `createOpt` VARCHAR(50) NULL COMMENT '创建者',
  `updateTime` DATETIME NULL COMMENT '更新时间',
  `updateOpt` VARCHAR(50) NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  INDEX `commodityCategoryC_id` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '商品三级类目表';



 CREATE TABLE `cardapi`.`apiprivilege` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `publicKey` TEXT(1024) NULL,
  `status` INT(2) NULL DEFAULT 1 COMMENT '0:失效；1：正常',
  `opt` INT(2) NULL,
  `createTime` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC)
  )ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT = '调用API权限表';