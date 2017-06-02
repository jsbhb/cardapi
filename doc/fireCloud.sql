drop SCHEMA if exists  `firecloud`;
CREATE SCHEMA `firecloud` ;

use firecloud;

drop table if exists  `account`;
CREATE TABLE `firecloud`.`account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` VARCHAR(50) NOT NULL COMMENT '�˺�',
  `pwd` VARCHAR(50) NOT NULL COMMENT '����',
  `nickName` VARCHAR(45) NOT NULL COMMENT '�ǳ�',
  `mobile` VARCHAR(11) NULL COMMENT '�ֻ�',
  `mobileValidate` INT(1) NULL DEFAULT 0 COMMENT '�ֻ��Ƿ���֤',
  `email` VARCHAR(50) NULL COMMENT '����',
  `emailValidate` INT(1) NULL DEFAULT 0 COMMENT '�����Ƿ���֤',
  `name` VARCHAR(50) NULL COMMENT '����',
  `certificates` INT(1) NULL COMMENT '֤������',
  `cardNum` VARCHAR(50) NULL COMMENT '֤������',
  `createTime` DATETIME NULL COMMENT 'ע��ʱ��',
  `lastLoginTime` DATETIME NULL COMMENT '����¼ʱ��',
  `lastLoginIP` VARCHAR(20) NULL COMMENT '����¼IP',
  `IPCity` VARCHAR(20) NULL COMMENT 'IP��������',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `account_UNIQUE` (`account` ASC),
  UNIQUE INDEX `nickName_UNIQUE` (`nickName` ASC),
  UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `cardNum_UNIQUE` (`cardNum` ASC)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '�˺ű�';


drop table if exists  `delivery`;
CREATE TABLE `firecloud`.`delivery` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�ջ���ַID',
  `accountId` INT(11) NOT NULL COMMENT '�˺�ID',
  `defaultFlg` INT(1) NULL DEFAULT 0 COMMENT 'Ĭ�ϵ�ַ',
  `name` VARCHAR(50) NULL COMMENT '�ջ���',
  `mobile` VARCHAR(30) NULL COMMENT '�ֻ�',
  `phone` VARCHAR(30) NULL COMMENT '�绰',
  `country` VARCHAR(20) NULL COMMENT '����',
  `provice` VARCHAR(20) NULL COMMENT 'ʡ',
  `city` VARCHAR(20) NULL COMMENT '��',
  `area` VARCHAR(20) NULL COMMENT '��',
  `address` VARCHAR(200) NULL COMMENT '��ַ',
  `zipCode` VARCHAR(10) NULL COMMENT '�ʱ�',
  PRIMARY KEY (`id`),
  INDEX `delivery_accountId` (`accountId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '�ջ���ַ��';


drop table if exists  `member`;
CREATE TABLE `firecloud`.`member` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '��ԱID',
  `accountId` INT(11) NOT NULL COMMENT '�˺�ID',
  `industry` Varchar(30) NULL COMMENT '������ҵ',
  `category` Varchar(30) NULL COMMENT '��Ӫ��Ŀ',
  `product` VARCHAR(200) NULL COMMENT '��Ҫ��Ʒ',
  `notice` VARCHAR(400) NULL COMMENT '��Ա����',
  `aboutUs` TEXT(1000) NULL COMMENT '��Ա���',
  `licensePath` VARCHAR(200) NULL COMMENT 'Ӫҵִ��·��',
  `operator` VARCHAR(30) NULL COMMENT '��Ӫ��',
  `mobile` VARCHAR(20) NULL COMMENT '�ֻ�',
  `phone` VARCHAR(20) NULL COMMENT '��˾�绰',
  `email` VARCHAR(50) NULL COMMENT '��ϵ����',
  `qq` VARCHAR(30) NULL COMMENT 'QQ�ͷ�',
  `reputation` VARCHAR(30) NULL COMMENT '�̼�������',
  `logoPath` VARCHAR(200) NULL COMMENT 'LogoͼƬ��ַ',
  `frontPicPath` VARCHAR(200) NULL COMMENT '������ͼƬ��ַ',
  `isGuarantee` INT(1) NULL DEFAULT 0 COMMENT '�Ƿ�ƽ̨����',
  `isHighQuality` INT(1) NULL DEFAULT 0 COMMENT '�Ƿ������̼�',
  `isSincerity` INT(1) NULL DEFAULT 0 COMMENT '�Ƿ����ʾ��',
  `isReturnGoods` INT(1) NULL DEFAULT 0 COMMENT '�Ƿ�֧���˻���',
  `province` VARCHAR(20) NULL COMMENT 'ʡ',
  `city` VARCHAR(20) NULL COMMENT '��',
  `area` VARCHAR(20) NULL COMMENT '��',
  `address` VARCHAR(200) NULL COMMENT '��ַ',
  `enterTime` DATETIME NULL COMMENT '��פʱ��',
  `lastUpdateTime` DATETIME NULL COMMENT '����޸�ʱ��',
  `isDel` INT(1) NULL DEFAULT 0 COMMENT '�Ƿ�ϳ�',
  PRIMARY KEY (`id`),
  INDEX `member_industry` (`industry`),
  INDEX `member_category` (`category`),
  INDEX `member_product` (`product`),
  INDEX `member_reputation` (`reputation`),
  INDEX `member_isGuarantee` (`isGuarantee`),
  INDEX `member_isHighQuality` (`isHighQuality`),
  INDEX `member_isSincerity` (`isSincerity`),
  INDEX `member_isReturnGoods` (`isReturnGoods`),
  INDEX `member_province` (`province`),
  INDEX `member_city` (`city`),
  INDEX `member_area` (`area`),
  INDEX `member_enterTime` (`enterTime`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '��Ա��';


drop table if exists  `member_pic`;
CREATE TABLE `firecloud`.`member_pic` (
  `memberId` INT(11) NOT NULL COMMENT '��ԱID',
  `fileId` INT(11) NOT NULL COMMENT '�ļ�ID',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  INDEX `member_pic_memberId` (`memberId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '��ԱͼƬ';


drop table if exists  `file`;
CREATE TABLE `firecloud`.`file` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�ļ�ID',
  `origialName` VARCHAR(50) NULL COMMENT 'ԭʼ����',
  `finalName` VARCHAR(50) NULL COMMENT '��������',
  `suffix` VARCHAR(20) NULL COMMENT '��׺',
  `rule` INT(2) NOT NULL COMMENT '���Ƽ��ܷ�ʽ',
  `storeType` INT(2) NULL COMMENT '�洢����',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '�ļ�����';


drop table if exists  `member_category`;
CREATE TABLE `firecloud`.`member_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '��Ա��ĿID',
  `memberId` INT(11) NOT NULL COMMENT '��ԱID',
  `industry` VARCHAR(30) NULL COMMENT '������ҵ',
  `category` VARCHAR(30) NULL COMMENT '��ҵ��Ŀ',
  PRIMARY KEY (`id`),
  INDEX `member_category_memberId` (`memberId`),
  INDEX `member_category_industry` (`industry`),
  INDEX `member_category_category` (`category`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '��Ա��Ŀ��';


drop table if exists  `popularize_dict`;
CREATE TABLE `firecloud`.`popularize_dict` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�ƹ��ֵ�ID',
  `popularizeCode` VARCHAR(50) NULL COMMENT '�ƹ����',
  `name` VARCHAR(50) NULL COMMENT '�ƹ�����',
  `type` INT(3) NULL COMMENT '�ƹ�����',
  `status` INT(1) NULL DEFAULT 0 COMMENT '�ƹ�״̬',
  `price` DECIMAL(10,2) NULL COMMENT '�ƹ���',
  `pitNum` INT(2) NULL COMMENT '�ƹ��λ��',
  `subPitNum` INT(2) NULL COMMENT '��ҵ+��Ʒ���͵���Ʒ��λ',
  `sort` INT(2) NULL COMMENT '�ֵ�����',
  `industry` VARCHAR(30) NULL COMMENT '������ҵ',
  `category` VARCHAR(30) NULL COMMENT '��ҵ��Ŀ',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '�ƹ��ֵ�';


drop table if exists  `popularize_banner`;
CREATE TABLE `firecloud`.`popularize_banner` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�ֲ�ID',
  `popularizeId` VARCHAR(50) NULL COMMENT '�ƹ����',
  `href` VARCHAR(300) NULL COMMENT '��ת��ַ',
  `status` INT(1) NULL DEFAULT 0 COMMENT '�ƹ�״̬',
  `picPath` VARCHAR(300) NULL COMMENT '�ֲ�ͼƬ��ַ',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX `popularize_banner_popularizeId` (`popularizeId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '�ֲ�';


drop table if exists  `popularize_product`;
CREATE TABLE `firecloud`.`popularize_product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `popularizeId` INT(11) NOT NULL COMMENT '�ƹ��ֵ�ID',
  `productHref` VARCHAR(300) NULL COMMENT '��ת��ַ',
  `picPath` VARCHAR(300) NULL COMMENT 'ͼƬ��ַ',
  `title` VARCHAR(50) NULL COMMENT '����',
  `status` INT(1) NULL DEFAULT 0 COMMENT '�ƹ�״̬',
  `shortTitle` VARCHAR(50) NULL COMMENT '��������',
  `description` VARCHAR(450) NULL COMMENT '����',
  `price` DECIMAL(10,2) NULL COMMENT '��Ʒ�۸�',
  `productId`  INT(11) NULL COMMENT '��ƷID',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  `startTime` DATETIME NULL COMMENT '��ʼʱ��',
  `day` INT(3) NULL COMMENT '��������',
  `endTime` DATETIME NULL COMMENT '����ʱ��',
  `sort` INT(2) NULL COMMENT '�ֵ�����',
  PRIMARY KEY (`id`),
  INDEX `popularize_product_popularizeId` (`popularizeId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '�ƹ���Ʒ';


drop table if exists  `popularize_member`;
CREATE TABLE `firecloud`.`popularize_member` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�ƹ�ʵ��ID',
  `popularizeId` INT(11) NULL COMMENT '�ƹ��ֵ�ID',
  `memberHref` VARCHAR(300) NULL COMMENT '�ƹ��ַ',
  `picPath` VARCHAR(300) NULL COMMENT 'ͼƬ��ַ',
  `logoPath` VARCHAR(300) NULL COMMENT 'Logo��ַ',
  `description` VARCHAR(450) NULL COMMENT '����',
  `status` INT(1) NULL DEFAULT 0 COMMENT '�ƹ�״̬',
  `startTime` DATETIME NULL COMMENT '��ʼʱ��',
  `day` INT(3) NULL COMMENT '��������',
  `endTime` DATETIME NULL COMMENT '����ʱ��',
  `sort` INT(2) NULL COMMENT '�ֵ�����',
  `memberId`  INT(11) NULL COMMENT '��ԱID',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX `popularize_member_popularizeId` (`popularizeId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '�ƹ���ҵ';


drop table if exists  `popularize_member_prod`;
CREATE TABLE `firecloud`.`popularize_member_prod` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�ƹ���ҵID',
  `popularizeId` INT(11) NULL COMMENT '�ƹ��ֵ�ID',
  `memberHref` VARCHAR(300) NULL COMMENT '�ƹ��ַ',
  `picPath1` VARCHAR(300) NULL COMMENT 'ͼƬ��ַ1',
  `picPath2` VARCHAR(300) NULL COMMENT 'ͼƬ��ַ2',
  `picPath3` VARCHAR(300) NULL COMMENT 'ͼƬ��ַ3',
  `logoPath` VARCHAR(300) NULL COMMENT 'Logo��ַ',
  `description1` VARCHAR(450) NULL COMMENT '����1',
  `description2` VARCHAR(450) NULL COMMENT '����2',
  `description3` VARCHAR(450) NULL COMMENT '����3',
  `status` INT(1) NULL DEFAULT 0 COMMENT '�ƹ�״̬',
  `memberId`  INT(11) NULL COMMENT '��ԱID',
  `startTime` DATETIME NULL COMMENT '��ʼʱ��',
  `day` INT(3) NULL COMMENT '��������',
  `endTime` DATETIME NULL COMMENT '����ʱ��',
  `sort` INT(2) NULL COMMENT '�ֵ�����',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX `popularize_member_prod_popularizeId` (`popularizeId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '�ƹ���ҵ����Ʒ��ҵ��';


drop table if exists  `popularize_member_prod_sub`;
CREATE TABLE `firecloud`.`popularize_member_prod_sub` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�ƹ���ƷID',
  `memberProdId` INT(11) NULL COMMENT '�ƹ���ҵ��ƷID',
  `productHref` VARCHAR(300) NULL COMMENT '��ת��ַ',
  `picPath` VARCHAR(300) NULL COMMENT 'ͼƬ��ַ',
  `title` VARCHAR(50) NULL COMMENT '����',
  `shortTitle` VARCHAR(50) NULL COMMENT '��������',
  `description` VARCHAR(450) NULL COMMENT '����',
  `status` INT(1) NULL DEFAULT 0 COMMENT '�ƹ�״̬',
  `startTime` DATETIME NULL COMMENT '��ʼʱ��',
  `day` INT(3) NULL COMMENT '��������',
  `endTime` DATETIME NULL COMMENT '����ʱ��',
  `sort` INT(2) NULL COMMENT '�ֵ�����',
  `price` DECIMAL(10,2) NULL COMMENT '��Ʒ�۸�',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  `productId`  INT(11) NULL COMMENT '��ƷID',
  PRIMARY KEY (`id`),
  INDEX `popularize_member_prod_sub_memberProdId` (`memberProdId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '�ƹ���ҵ����Ʒ��Ʒ��';


drop table if exists  `auth_func`;
CREATE TABLE `firecloud`.`auth_func` (
  `funcId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `funcName` VARCHAR(50) NULL COMMENT '��������',
  `url` VARCHAR(300) NULL COMMENT '����',
  `funcGroupId` VARCHAR(50) NULL COMMENT '������',
  `parentId` INT(11) NULL COMMENT '������ID',
  `tag` VARCHAR(50) NULL COMMENT 'С��ǩ',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  `updateTime` DATETIME NULL COMMENT '����ʱ��',
  `optName` Varchar(50) NULL COMMENT '������',
  `remark` VARCHAR(200) NULL COMMENT '��ע',
  PRIMARY KEY (`funcId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '���ܱ�';


drop table if exists  `auth_role`;
CREATE TABLE `firecloud`.`auth_role` (
  `roleId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `roleName` VARCHAR(50) NULL COMMENT '��ɫ����',
  `status` INT(1) NULL DEFAULT 0 COMMENT '�Ƿ����',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  `updateTime` DATETIME NULL COMMENT '����ʱ��',
  `optName` Varchar(50) NULL COMMENT '������',
  `remark` VARCHAR(200) NULL COMMENT '��ע',
  PRIMARY KEY (`roleId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '��ɫ��';


drop table if exists  `auth_operator`;
CREATE TABLE `firecloud`.`auth_operator` (
  `optId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `badge` VARCHAR(50) NOT NULL COMMENT '����',
  `name` VARCHAR(50) NULL COMMENT 'Ա������',
  `pwd` VARCHAR(50) NULL COMMENT '����',
  `status` INT(1) NULL COMMENT '�Ƿ����',
  `mobil` VARCHAR(20) NULL COMMENT '�绰',
  `email` VARCHAR(50) NULL COMMENT '����',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  `updateTime` DATETIME NULL COMMENT '����ʱ��',
  `optName` Varchar(50) NULL COMMENT '������',
  `remark` VARCHAR(200) NULL COMMENT '��ע',
  PRIMARY KEY (`optId`),
  UNIQUE INDEX `badge_UNIQUE` (`badge` ASC)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = 'Ա����';


drop table if exists  `auth_optrole`;
CREATE TABLE `firecloud`.`auth_optrole` (
  `roleId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'roleID',
  `optId` INT(11) NULL COMMENT 'optID',
  `partId` INT(11) NULL COMMENT '��ְroleID',
  `status` INT(1) NULL DEFAULT 0 COMMENT '��ְ�Ƿ���Ч',
  `partEndTime` DATETIME NULL COMMENT '��ְ����ʱ��',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  `updateTime` DATETIME NULL COMMENT '����ʱ��',
  `optName` VARCHAR(50) NULL COMMENT '������',
  `remark` VARCHAR(200) NULL COMMENT '��ע',
  PRIMARY KEY (`roleId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = 'Ա����ɫ��';


drop table if exists  `auth_rolefunc`;
CREATE TABLE `firecloud`.`auth_rolefunc` (
  `roleId` INT(11) NOT NULL COMMENT 'roleID',
  `funcId` INT(11) NOT NULL COMMENT 'funcID',
  `privilege` INT(2) NULL COMMENT 'Ȩ��',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  `updateTime` DATETIME NULL COMMENT '����ʱ��',
  `optName` VARCHAR(50) NULL COMMENT '������',
  `remark` VARCHAR(200) NULL COMMENT '��ע') ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '��ɫ���ܰ�';


drop table if exists  `commodity`;
CREATE TABLE `firecloud`.`commodity` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '��ƷID',
  `memberId` INT(11) NOT NULL COMMENT '�̼�ID',
  `commodityCategory1` VARCHAR(30) NULL COMMENT '��Ʒ������Ŀ1',
  `commodityCategory2` VARCHAR(30) NULL COMMENT '��Ʒ������Ŀ2',
  `commodityCategory3` VARCHAR(30) NULL COMMENT '��Ʒ������Ŀ3',
  `commodityName` VARCHAR(200) NULL COMMENT '��Ʒ����',
  `brand` VARCHAR(100) NULL COMMENT 'Ʒ��',
  `price` DECIMAL(10,2) NULL COMMENT '�۸�',
  `quantity` INT(11) NULL COMMENT '����',
  `color` VARCHAR(30) NULL COMMENT '��ɫ',
  `size` VARCHAR(30) NULL COMMENT '�ߴ�',
  `saleState` VARCHAR(10) NULL COMMENT '����״̬',
  `goodFlg` INT(1) NULL COMMENT '�û�',
  `hotFlg` INT(1) NULL COMMENT '����',
  `choiceFlg` INT(1) NULL COMMENT '��ѡ',
  `appreciateFlg` INT(1) NULL COMMENT '����',
  `picture1` VARCHAR(200) NULL COMMENT '��Ʒʵ��ͼƬ1',
  `picture2` VARCHAR(200) NULL COMMENT '��Ʒʵ��ͼƬ2',
  `picture3` VARCHAR(200) NULL COMMENT '��Ʒʵ��ͼƬ3',
  `picture4` VARCHAR(200) NULL COMMENT '��Ʒʵ��ͼƬ4',
  `picture5` VARCHAR(200) NULL COMMENT '��Ʒʵ��ͼƬ5',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  `createOpt` VARCHAR(50) NULL COMMENT '������',
  `updateTime` DATETIME NULL COMMENT '����ʱ��',
  `updateOpt` VARCHAR(50) NULL COMMENT '������',
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
  INDEX `commodity_appreciateFlg` (`appreciateFlg`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '��Ʒ��';


drop table if exists  `commodity_show`;
CREATE TABLE `firecloud`.`commodity_show` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'չʾID',
  `commodityId` INT(11) NULL COMMENT '��ƷID',
  `commodityIntroduction` VARCHAR(200) NULL COMMENT '��Ʒ����',
  `packingSpecification` VARCHAR(200) NULL COMMENT '������װ',
  `instructions` VARCHAR(200) NULL COMMENT 'ʹ��˵��',
  `customerService` VARCHAR(400) NULL COMMENT '�����ۺ�',
  `showPagePath` VARCHAR(200) NULL COMMENT 'չʾҳ��·��',
  `showPageSource` VARCHAR(2000) NULL COMMENT 'չʾҳ�����',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  `createOpt` VARCHAR(50) NULL COMMENT '������',
  `updateTime` DATETIME NULL COMMENT '����ʱ��',
  `updateOpt` VARCHAR(50) NULL COMMENT '������',
  PRIMARY KEY (`id`),
  INDEX `commodity_show_commodityId` (`commodityId`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 
COMMENT = '��Ʒչʾ��';


drop table if exists  `product_pic`;
CREATE TABLE `firecloud`.`product_pic` (
  `productId` INT(11) NOT NULL COMMENT '��ԱID',
  `fileId` INT(11) NOT NULL COMMENT '�ļ�ID',
  `createTime` DATETIME NULL COMMENT '����ʱ��',
  INDEX `product_pic_productId` (`productId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 
COMMENT = '��ƷͼƬ';