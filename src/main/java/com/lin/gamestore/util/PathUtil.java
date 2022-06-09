package com.lin.gamestore.util;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/www/images/";
        } else {
            basePath = "/home/gamestore/image/";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    public static String getTypeImagePath() {
        String imagePath = "upload/item/";
        return imagePath.replace("/", separator);
    }
}
