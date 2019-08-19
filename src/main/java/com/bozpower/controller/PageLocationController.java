package com.bozpower.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bozpower.entity.Company;
import com.bozpower.entity.PageData;
import com.bozpower.entity.User;
import com.bozpower.service.DeviceService;
import com.bozpower.utils.RequestUtils;

@Controller
@RequestMapping(value = "pageLocationController")
public class PageLocationController {
	
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value = "indexRequest")
	public String indexRequest(HttpServletRequest request, String param, Integer deviceId, Map<String , Object> map) {
		String local = "";
		int id = new RequestUtils().getCompanyId(request);
		//页面请求进入图表页面
		if("listCharts".equals(param)) {
			map.put("deviceId", deviceId);
			map.put("deviceList", deviceService.selectDeviceList(new PageData(), id));
			local = "list_charts";
		}
		
		return local;
		
	}
	
	
	/**
	 * url: pageLocationController/page?param=devicePage
	 * @param param
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "page")
	public String page(HttpServletRequest request,String param, Integer type, Map<String , Object> map) {
		String local = "";
		int id = new RequestUtils().getCompanyId(request);
		//页面请求进入图表页面
		if("devicePage".equals(param)) {
			map.put("deviceList", deviceService.selectDeviceList(new PageData(), id));
			String title = "";
			switch(type) {
			case 0: //风向
				title = "风向";
				break;
			case 1://风速
				title = "风速";
				break;
			case 2://温度
				title = "温度";
				break;
			case 3://湿度
				title = "湿度";
				break;
			case 4://压强
				title = "压强";
				break;
				default :
					break;
			}
			map.put("title", title);
			map.put("type", type);
			local = "device_list";
		}
		else if("charts".equals(param)) {
			local = "line_chart";
		}
		
		return local;
		
	}

}
