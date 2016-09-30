package com.orange.util;


public class PageUtil {
	
	public static int getPageSize(int page, int pageSize, String headFootFlg){
		if (StringUtil.isNotEmptyString(headFootFlg) && "1".equals(headFootFlg)) {
			pageSize = page * pageSize;
		}
		return pageSize;
	}
	
	public static int getOffset(int pageSize, int currentPage) {
//		int offset = 0;
//		if(currentPage <= 1){
//			offset = 0;
//		}else{
//			offset = pageSize * (currentPage - 1);
//		}
		return pageSize * (currentPage - 1);
	}

}
