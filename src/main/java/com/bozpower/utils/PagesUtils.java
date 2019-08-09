package com.bozpower.utils;

import com.bozpower.entity.PageData;

public class PagesUtils {
	
	//分页数默认为10页
	public static int limit = 10;
	
	public static PageData getPageData(PageData pageData) {
		if(pageData.getLimit() == 0) {//
			pageData.setCurrentPage(1);
			pageData.setLimit(limit);
		}
		//设置分页的开始查询数
		int startPage = (pageData.getCurrentPage() - 1) * pageData.getLimit();
		pageData.setStartPage(startPage);
		return pageData;
	}
	
	
	/**
	 * 计算分页WeatherController
	 * @param limit
	 * @param count
	 * @return
	 */
	public static int getPages(int limit, int count) {
		
		if(limit == 0) {
			limit = 10;
		}
		int pages = count / limit;
		if(count % limit != 0)
			pages ++;
		return pages;
	}
	
	
	//计算某台设备的分页	条件为设备名称
	public static int selectDeviceWeatherHistoryPages(int limit, int count) {

		if(limit == 0) {
			limit = 10;
		}
		int pages = count / limit;
		if(count % limit != 0)
			pages ++;
		return pages;
	}

}
