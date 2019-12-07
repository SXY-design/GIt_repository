package com.itheima.web.utils;

import java.util.UUID;

public class UploadUtils {
	public static String getUuidFileName(String fileName){
		int index = fileName.lastIndexOf(".");
		String extendtion = fileName.substring(index);
		String main = UUID.randomUUID().toString().replace("-", "");
		return main+extendtion;
	}
	public static String getUuidPath(String uuidFileName){
		int code1 = uuidFileName.hashCode();
		int pathName1 = code1 & 0xf;
		int code2 = code1 >>> 4;
		int pathName2 = code2 & 0xf;
		return "/"+pathName1+"/"+pathName2;
	}
}
