package com.cart.greenveg.admin.common;

public class SQLConstant {


    /**************  CATEGORY SERVICE *****************/

    public static final String SELECT_CATEGORY_LIST_QUERY = "SELECT CAT.ID AS CATEGORY_ID,CAT.NAME AS CATEGORY_NAME,CAT.DESCRIPTION AS CATEGORY_DESCRIPTION,SC.ID AS SUBCATEGORY_ID," +
            "SC.NAME AS SUBCATEGORY_NAME,SC.DESCRIPTION AS SUBCATEGORY_DESC FROM CATEGORY CAT LEFT JOIN SUB_CATEGORY SC ON SC.PARENTID = CAT.ID";

    public static final String SELECT_CATEGORY_LIST_QUERY_BY_ID = "SELECT CAT.ID AS CATEGORY_ID,CAT.NAME AS CATEGORY_NAME,SC.ID AS SUBCATEGORY_ID," +
            " SC.NAME AS SUBCATEGORY_NAME FROM SUB_CATEGORY SC LEFT JOIN CATEGORY CAT ON SC.PARENTID = CAT.ID WHERE CAT.ID=?";

    public static final String INSERT_CATEGORY="INSERT INTO `CATEGORY` (`DESCRIPTION`, `NAME`) VALUES (:desc,:name)";

    public static final String UPDATE_CATEGORY="UPDATE `CATEGORY` SET DESCRIPTION= :description, NAME= :name where ID= :id";

    public static final String VALIDATE_CATEGORY = "SELECT ID FROM CATEGORY WHERE ID = ?";

    public static final String INSERT_SUB_CATEGORY="INSERT INTO `SUB_CATEGORY` (`PARENTID`, `DESCRIPTION`, `NAME`) VALUES (:parentid,:description,:name)";

    public static final String UPDATE_SUB_CATEGORY="UPDATE `SUB_CATEGORY` SET DESCRIPTION=:description,NAME=:name,PARENTID=:parentid where ID=:id";

    public static final String VALIDATE_SUB_CATEGORY = "SELECT ID FROM SUB_CATEGORY WHERE ID = ?";

    /**************  PRODUCT SERVICE *****************/

    public static final String ALL_PRODUCTS="SELECT P.ID,P.TITLE,P.SUMMARY,P.SKU,P.PRICE,P.DISCOUNT,P.QUANTITY,P.SHOP,P.CREATEDAT,\n" +
            "P.UPDATEDAT,P.PUBLISHEDAT,P.STARTSAT,P.ENDSAT,P.CONTENT,PM.IMAGES,PM.ID AS META_ID,PM.TAGS,PC.SUBCATEGORYID AS SUB_CAT_ID,SUB_CAT.PARENTID AS CATEGORY_ID,\n" +
            "SUB_CAT.NAME AS SUB_CATEGORY_NAME,SUB_CAT.DESCRIPTION AS SUB_CATEGORY_DESC\n" +
            "FROM PRODUCT P \n" +
            "LEFT JOIN PRODUCT_META PM ON P.ID=PM.PRODUCTID\n" +
            "INNER JOIN PRODUCT_CATEGORY PC ON P.ID=PC.PRODUCTID\n" +
            "INNER JOIN SUB_CATEGORY SUB_CAT ON SUB_CAT.ID=PC.SUBCATEGORYID ORDER BY P.TITLE";


    public static final String PRODUCTS_BY_CATEGORY_AND_SUB_CATEGORY="SELECT P.ID,P.TITLE,P.SUMMARY,P.SKU,P.PRICE,P.DISCOUNT,P.QUANTITY,P.SHOP,P.CREATEDAT,\n" +
            "P.UPDATEDAT,P.PUBLISHEDAT,P.STARTSAT,P.ENDSAT,P.CONTENT,PM.IMAGES,PM.ID AS META_ID,PM.TAGS,PC.SUBCATEGORYID AS SUB_CAT_ID,SUB_CAT.PARENTID AS CATEGORY_ID,\n" +
            "SUB_CAT.NAME AS SUB_CATEGORY_NAME,SUB_CAT.DESCRIPTION AS SUB_CATEGORY_DESC\n" +
            "FROM PRODUCT P \n" +
            "LEFT JOIN PRODUCT_META PM ON P.ID=PM.PRODUCTID\n" +
            "INNER JOIN PRODUCT_CATEGORY PC ON P.ID=PC.PRODUCTID\n" +
            "INNER JOIN SUB_CATEGORY SUB_CAT ON SUB_CAT.ID=PC.SUBCATEGORYID\n" +
            "WHERE SUB_CAT.PARENTID=:categoryid AND PC.SUBCATEGORYID=:subcategoryid" +
            " ORDER BY P.TITLE";

    public static final String PRODUCTS_BY_CATEGORY_ONLY="SELECT P.ID,P.TITLE,P.SUMMARY,P.SKU,P.PRICE,P.DISCOUNT,P.QUANTITY,P.SHOP,P.CREATEDAT,\n" +
            "P.UPDATEDAT,P.PUBLISHEDAT,P.STARTSAT,P.ENDSAT,P.CONTENT,PM.IMAGES,PM.ID AS META_ID,PM.TAGS,PC.SUBCATEGORYID AS SUB_CAT_ID,SUB_CAT.PARENTID AS CATEGORY_ID,\n" +
            "SUB_CAT.NAME AS SUB_CATEGORY_NAME,SUB_CAT.DESCRIPTION AS SUB_CATEGORY_DESC\n" +
            "FROM PRODUCT P \n" +
            "LEFT JOIN PRODUCT_META PM ON P.ID=PM.PRODUCTID\n" +
            "INNER JOIN PRODUCT_CATEGORY PC ON P.ID=PC.PRODUCTID\n" +
            "INNER JOIN SUB_CATEGORY SUB_CAT ON SUB_CAT.ID=PC.SUBCATEGORYID\n" +
            "WHERE SUB_CAT.PARENTID=:categoryid ORDER BY P.TITLE";

    public static final String PRODUCTS_BY_PRODUCT_ID="SELECT P.ID,P.TITLE,P.SUMMARY,P.SKU,P.PRICE,P.DISCOUNT,P.QUANTITY,P.SHOP,P.CREATEDAT,\n" +
            "P.UPDATEDAT,P.PUBLISHEDAT,P.STARTSAT,P.ENDSAT,P.CONTENT,PM.IMAGES,PM.ID AS META_ID,PM.TAGS,PC.SUBCATEGORYID AS SUB_CAT_ID,SUB_CAT.PARENTID AS CATEGORY_ID,\n" +
            "SUB_CAT.NAME AS SUB_CATEGORY_NAME,SUB_CAT.DESCRIPTION AS SUB_CATEGORY_DESC\n" +
            "FROM PRODUCT P \n" +
            "LEFT JOIN PRODUCT_META PM ON P.ID=PM.PRODUCTID\n" +
            "INNER JOIN PRODUCT_CATEGORY PC ON P.ID=PC.PRODUCTID\n" +
            "INNER JOIN SUB_CATEGORY SUB_CAT ON SUB_CAT.ID=PC.SUBCATEGORYID\n" +
            "WHERE P.ID=:productid ORDER BY P.TITLE";


    public static final String INSERT_PRODUCT ="INSERT INTO PRODUCT(title,summary,sku,price,discount,quantity,shop," +
            "createdAt,updatedAt,publishedAt," +
            "startsAt,endsAt,content) VALUES(:title,:summary,:sku,:price,:discount,:quantity,:shop,:createdAt," +
            ":updatedAt,:publishedAt,:startsAt,:endsAt,:content);";

    public static final String INSERT_PRODUCT_META="INSERT INTO PRODUCT_META(productId,tags,images) VALUES(:productId,:tags,:images)";
    public static final String INSERT_PRODUCT_CATEGORY="INSERT INTO product_category(productId,subcategoryId) VALUES(:productId,:subcategoryId)";


    public static final String UPDATE_PRODUCT ="UPDATE PRODUCT SET title=:title,summary=:summary,sku=:sku,price=:price," +
            "discount=:discount,quantity=:quantity,shop=:shop,updatedAt=:updatedAt," +
            "publishedAt=:publishedAt,startsAt=:startsAt,endsAt=:endsAt,content=:content where id=:id";

    public static final String UPDATE_PRODUCT_META="UPDATE PRODUCT_META SET tags=:tags,images=:images where productId=:productId";
    public static final String UPDATE_PRODUCT_CATEGORY="UPDATE product_category SET subcategoryId=:subcategoryId where productId=:productId";
}
