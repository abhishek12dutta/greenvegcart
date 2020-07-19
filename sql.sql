use green_veg_cart_db_instance;

CREATE TABLE `green_veg_cart_db_instance`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(200) NOT NULL,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));
  
    CREATE TABLE `green_veg_cart_db_instance`.`sub_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `parentId` BIGINT NULL DEFAULT NULL,
  `description` VARCHAR(200) NOT NULL,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));
  
  ALTER TABLE `green_veg_cart_db_instance`.`sub_category` 
  ADD CONSTRAINT `fk_sub_category_parent`
  FOREIGN KEY (`parentId`)
  REFERENCES `green_veg_cart_db_instance`.`category` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
    
  CREATE TABLE `green_veg_cart_db_instance`.`product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(75) NOT NULL,
  `summary` TINYTEXT NULL,
  `sku` VARCHAR(100) NOT NULL,
  `price` FLOAT NOT NULL DEFAULT 0,
  `discount` FLOAT NOT NULL DEFAULT 0,
  `quantity` SMALLINT(6) NOT NULL DEFAULT 0,
  `shop` TINYINT(1) NOT NULL DEFAULT 0,
  `createdAt` DATETIME NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT NULL,
  `publishedAt` DATETIME NULL DEFAULT NULL,
  `startsAt` DATETIME NULL DEFAULT NULL,
  `endsAt` DATETIME NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`));
  
  
   CREATE TABLE `green_veg_cart_db_instance`.`product_meta` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `productId` BIGINT NOT NULL,
  `key` VARCHAR(50) NULL,
  `images` VARCHAR(50) NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_meta_product` (`productId` ASC),
  UNIQUE INDEX `uq_product_meta` (`productId` ASC, `key` ASC),
  CONSTRAINT `fk_meta_product`
    FOREIGN KEY (`productId`)
    REFERENCES `green_veg_cart_db_instance`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    	
	CREATE TABLE `green_veg_cart_db_instance`.`product_category` (
  `productId` BIGINT NOT NULL,
  `subcategoryId` BIGINT NOT NULL,
  PRIMARY KEY (`productId`, `subcategoryId`),
  INDEX `idx_pc_category` (`subcategoryId` ASC),
  INDEX `idx_pc_product` (`productId` ASC),
  CONSTRAINT `fk_pc_product`
    FOREIGN KEY (`productId`)
    REFERENCES `green_veg_cart_db_instance`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pc_category`
    FOREIGN KEY (`subcategoryId`)
    REFERENCES `green_veg_cart_db_instance`.`sub_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE `green_veg_cart_db_instance`.`cart` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `userId` BIGINT NULL DEFAULT NULL,
  `status` SMALLINT(6) NOT NULL DEFAULT 0,
  `createdAt` DATETIME NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`));
  
 ALTER TABLE `green_veg_cart_db_instance`.`cart` 
ADD 
  CONSTRAINT `fk_cart_user`
    FOREIGN KEY (`userId`)
    REFERENCES `green_veg_cart_db_instance`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
    
    CREATE TABLE `green_veg_cart_db_instance`.`cart_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `productId` BIGINT NOT NULL,
  `cartId` BIGINT NOT NULL,
  `sku` VARCHAR(100) NOT NULL,
  `price` FLOAT NOT NULL DEFAULT 0,
  `discount` FLOAT NOT NULL DEFAULT 0,
  `quantity` SMALLINT(6) NOT NULL DEFAULT 0,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  `createdAt` DATETIME NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_cart_item_product` (`productId` ASC),
  CONSTRAINT `fk_cart_item_product`
    FOREIGN KEY (`productId`)
    REFERENCES `green_veg_cart_db_instance`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `green_veg_cart_db_instance`.`cart_item` 
ADD INDEX `idx_cart_item_cart` (`cartId` ASC);


ALTER TABLE `green_veg_cart_db_instance`.`cart_item` 
ADD CONSTRAINT `fk_cart_item_cart`
  FOREIGN KEY (`cartId`)
  REFERENCES `green_veg_cart_db_instance`.`cart` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
  
  
  CREATE TABLE `green_veg_cart_db_instance`.`order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `userId` BIGINT NULL DEFAULT NULL,
  `status` SMALLINT(6) NOT NULL DEFAULT 0,
  `subTotal` FLOAT NOT NULL DEFAULT 0,
  `itemDiscount` FLOAT NOT NULL DEFAULT 0,
  `tax` FLOAT NOT NULL DEFAULT 0,
  `shipping` FLOAT NOT NULL DEFAULT 0,
  `total` FLOAT NOT NULL DEFAULT 0,
  `promo` VARCHAR(50) NULL DEFAULT NULL,
  `discount` FLOAT NOT NULL DEFAULT 0,
  `grandTotal` FLOAT NOT NULL DEFAULT 0,
  `firstName` VARCHAR(50) NULL DEFAULT NULL,
  `middleName` VARCHAR(50) NULL DEFAULT NULL,
  `lastName` VARCHAR(50) NULL DEFAULT NULL,
  `mobile` VARCHAR(15) NULL,
  `email` VARCHAR(50) NULL,
  `line1` VARCHAR(50) NULL DEFAULT NULL,
  `line2` VARCHAR(50) NULL DEFAULT NULL,
  `city` VARCHAR(50) NULL DEFAULT NULL,
  `province` VARCHAR(50) NULL DEFAULT NULL,
  `country` VARCHAR(50) NULL DEFAULT NULL,
  `createdAt` DATETIME NOT NULL,
  `updatedAt` DATETIME NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`));
  
  
  ALTER TABLE `green_veg_cart_db_instance`.`order` 
ADD INDEX `idx_order_user` (`userId` ASC);


  ALTER TABLE `green_veg_cart_db_instance`.`order` 
  ADD CONSTRAINT `fk_order_user`
    FOREIGN KEY (`userId`)
    REFERENCES `green_veg_cart_db_instance`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
    
    
	CREATE TABLE `green_veg_cart_db_instance`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NULL DEFAULT NULL,
  `middleName` VARCHAR(50) NULL DEFAULT NULL,
  `lastName` VARCHAR(50) NULL DEFAULT NULL,
  `mobile` VARCHAR(15) NULL,
  `email` VARCHAR(50) NULL,
  `passwordHash` VARCHAR(32) NOT NULL,
  `admin` TINYINT(1) NOT NULL DEFAULT 0,
  `vendor` TINYINT(1) NOT NULL DEFAULT 0,
  `registeredAt` DATETIME NOT NULL,
  `lastLogin` DATETIME NULL DEFAULT NULL,
  `intro` TINYTEXT NULL DEFAULT NULL,
  `profile` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_mobile` (`mobile` ASC),
  UNIQUE INDEX `uq_email` (`email` ASC) );
    
    
    
    
  use green_veg_cart_db_instance;  


  SELECT pc.productId,c.name as categoryname,pc.subcategoryId,p.title,p.summary,p.sku,p.price,p.discount,p.quantity,p.shop,p.content
  FROM product p join product_category pc on  pc.productId = p.id
   left JOIN sub_category sc
    ON sc.parentId = pc.productId;
    
    select pc.productId as product_id, pc.subcategoryId as subcategory_id, sc.parentId as category_id
FROM product_category pc INNER JOIN sub_category sc  ON sc.parentId = pc.subcategoryId;

select p.id as product_id,p.title as product_name,cat.id as category_id,cat.name as category_name,pc.subcategoryId
,sc.name as subcategory_name 
FROM product p
left join product_category pc on  pc.productId = p.id
left JOIN sub_category sc ON sc.id = pc.subcategoryId
left join category cat ON cat.id=sc.parentId where p.shop=1;


select cat.id as category_id,cat.name as category_name,sc.id as subcategory_id, sc.name as subcategory_name
from category cat
LEFT JOIN sub_category sc ON sc.parentId = cat.id ;



SELECT 
  TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME
FROM
  INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
  REFERENCED_TABLE_SCHEMA = 'green_veg_cart_db_instance' ;
  
  SELECT * FROM information_schema.TABLE_CONSTRAINTS 
WHERE 
--information_schema.TABLE_CONSTRAINTS.CONSTRAINT_TYPE = 'INDEX' AND 
information_schema.TABLE_CONSTRAINTS.TABLE_SCHEMA = 'green_veg_cart_db_instance'
AND information_schema.TABLE_CONSTRAINTS.TABLE_NAME = 'mytable';


select cat.id as category_id,cat.name as category_name,sc.id as subcategory_id, sc.name as subcategory_name
from sub_category sc
left JOIN category cat ON sc.parentId = cat.id where cat.id=1;
--------------------




CREATE TABLE `permission` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PERMISSION_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);


CREATE TABLE `assign_user_to_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `assign_user_to_role_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `assign_user_to_role_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
);

CREATE TABLE `assign_permission_to_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PERMISSION_ID` bigint(20) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `PERMISSION_ID` (`PERMISSION_ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `assign_permission_to_role_ibfk_1` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `permission` (`ID`),
  CONSTRAINT `assign_permission_to_role_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
);





SELECT DISTINCT P.PERMISSION_NAME FROM PERMISSION P 
INNER JOIN ASSIGN_PERMISSION_TO_ROLE P_R ON P.ID=P_R.PERMISSION_ID
 INNER JOIN ROLE R ON R.ID=P_R.ROLE_ID 
INNER JOIN ASSIGN_USER_TO_ROLE U_R ON U_R.ROLE_ID=R.ID
 INNER JOIN USER U ON U.ID=U_R.USER_ID where U.ID=2;
 
 SELECT * FROM USER;
 SELECT * FROM ASSIGN_USER_TO_ROLE;
 
 
 SELECT P.ID,P.TITLE,P.SUMMARY,P.SKU,P.PRICE,P.DISCOUNT,P.QUANTITY,P.SHOP,P.CREATEDAT,
P.UPDATEDAT,P.PUBLISHEDAT,P.STARTSAT,P.ENDSAT,P.CONTENT,PM.IMAGES,PM.ID AS META_ID,PM.TAGS,PC.SUBCATEGORYID AS SUB_CAT_ID,SUB_CAT.PARENTID AS CATEGORY_ID,
SUB_CAT.NAME AS SUB_CATEGORY_NAME,SUB_CAT.DESCRIPTION AS SUB_CATEGORY_DESC FROM PRODUCT P  LEFT JOIN PRODUCT_META PM ON P.ID=PM.PRODUCTID INNER JOIN PRODUCT_CATEGORY PC ON P.ID=PC.PRODUCTID 
INNER JOIN SUB_CATEGORY SUB_CAT ON SUB_CAT.ID=PC.SUBCATEGORYID ORDER BY P.TITLE;category


select * from SUB_CATEGORY;

select * from CATEGORY;

select * from product_meta;

select * from product;

delete from green_veg_cart_db_instance.product_meta;
delete from green_veg_cart_db_instance.product;
delete from green_veg_cart_db_instance.product_category;
delete from green_veg_cart_db_instance.sub_category;
delete from green_veg_cart_db_instance.category;
commit;












































    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    