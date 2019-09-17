package com.bozpower.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bozpower.entity.Company;
import com.bozpower.entity.Device;
import com.bozpower.entity.PageData;
import com.bozpower.entity.Weathers;
import com.bozpower.service.DeviceService;
import com.bozpower.service.WeathersService;
import com.bozpower.utils.PagesUtils;
import com.bozpower.utils.RequestUtils;

@Controller
@RequestMapping(value = "weathersController")
public class WeathersController {

	@Autowired
	private WeathersService weathersService;

	@Autowired
	private DeviceService deviceService;

	/**
	 * 分页查询实时数据
	 * 
	 * @param pageData 分页
	 * @param map      传参
	 * @param deviceId 设备编号
	 * @param time     时间
	 * @return
	 */
	@RequestMapping(value = "selectDeviceData", method = RequestMethod.GET)
	public String selectDeviceData(HttpServletRequest request, PageData pageData, Map<String, Object> map, String deviceId,  String yearMonthDayAndHourMinuteSecond,
			String yearMonthDayAndHourMinuteSecond2,  String param) {
		int id = new RequestUtils().getCompanyId(request);
		List<Device> deviceList = deviceService.selectDeviceList(new PageData(), id);
		deviceList = deviceService.selectDeviceList(new PageData(), id);
		pageData = PagesUtils.getPageData(pageData);
		
		try {
			// 查询设备id为deviceId，时间为yearMonthDay和hms的设备数据集合
			if ("this".equals(param)) {// 查询实时数据
				map = this.selectThisData(request, pageData, map, param);
			} else if ("history".equals(param)) {// 表示查询历史数据
				map = this.selectHistoryData(request, pageData, map, param, deviceId, yearMonthDayAndHourMinuteSecond,
						yearMonthDayAndHourMinuteSecond2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
		map.put("type", param);
		map.put("deviceList", deviceList);
		return "main";
	}



	/**
	 * 实时数据
	 * 
	 * @param pagedata
	 * @param map
	 * @param param
	 * @return
	 */
	private Map<String, Object> selectThisData(HttpServletRequest request, PageData pageData, Map<String, Object> map, String param) {

		// 查询所有设备的实时数据集合
		List<Weathers> weathersList = null;
		map.put("location", "../weathersController/selectDeviceData?param=" + param);
		int companyId = new RequestUtils().getCompanyId(request);
		weathersList = weathersService.selectDeviceAllThisData(pageData, companyId);
		int count = weathersService.selectDeviceAllThisDataCount(companyId);
		int pages = PagesUtils.getPages(pageData.getLimit(), count);
		pageData.setCount(count);
		pageData.setPages(pages);
		map.put("weathersList", weathersList);
		map.put("pageData", pageData);
		return map;
	}

	/**
	 * 历史数据
	 * 
	 * @param pageData
	 * @param map
	 * @param param
	 * @return
	 */
	private Map<String, Object> selectHistoryData(HttpServletRequest request,PageData pageData, Map<String, Object> map, String param,
			String deviceId, String yearMonthDayAndHourMinuteSecond, String yearMonthDayAndHourMinuteSecond2) {
		List<Weathers> weathersList = null;
//		pageData = PagesUtils.getPageData(pageData);
		int count = 0;
		int pages = 0;
		int companyId = new RequestUtils().getCompanyId(request);
		try {
			if (deviceId != null && !"".equals(deviceId)) {// 查询设备编号为deviceId的所有历史记录
				if (yearMonthDayAndHourMinuteSecond != null && !"".equals(yearMonthDayAndHourMinuteSecond)) {// 查询该区间范围的历史记录
					// 查询数据
					String startTime = yearMonthDayAndHourMinuteSecond.substring(0, 13);
					String endTime = yearMonthDayAndHourMinuteSecond2.substring(0, 13);
					weathersList = weathersService.selectDeviceWeathersHistoryDataByYMDSAndHMSS(
							pageData, deviceId, companyId,	startTime,
							endTime);
					count = weathersService.selectDeviceWeathersHistoryDataCountByYMDSAndHMSS(
							deviceId,  companyId,startTime, endTime);
					pages = PagesUtils.getPages(pageData.getLimit(), count);
					map.put("yearMonthDayAndHourMinuteSecond", yearMonthDayAndHourMinuteSecond);
					map.put("yearMonthDayAndHourMinuteSecond2", yearMonthDayAndHourMinuteSecond2);
				} else {// 只用设备编号查询
					weathersList = weathersService.selectDeviceWeathersHistoryByDeviceId(pageData, deviceId);
					count = weathersService.selectDeviceWeathersHistoryCountByDeviceId(deviceId);
					pages = PagesUtils.getPages(pageData.getLimit(), count);
				}
				map.put("deviceId", deviceId);
			} else {// 表示查询全部
				weathersList = weathersService.selectDeviceAllWeathersHistoryDataList(pageData, companyId);
				count = weathersService.selectDeviceAllWeathersHistoryDataCount(companyId);
				pages = PagesUtils.getPages(pageData.getLimit(), count);
			}
			pageData.setCount(count);
			pageData.setPages(pages);
			map.put("weathersList", weathersList);
			map.put("pageData", pageData);

			map.put("location", "../weathersController/selectDeviceData?param=" + param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return map;
	}

	
	/**
	 * 前端页面请求创建 图形的数据，格式为JSON格式
	 * @param yearMonthDayAndHourMinuteSecond
	 * @param deviceId
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "selectHistoryDataJSONList", method = RequestMethod.POST)
	@ResponseBody
	public List<Weathers> selectHistoryDataJSONList(
			String yearMonthDayAndHourMinuteSecond, 
			String deviceId, 
			Integer type) {
		List<Weathers> weathersList = null;
		try {
			if (deviceId == null) {
				weathersList = new ArrayList<Weathers>();
				return weathersList;
			} else {
				weathersList = weathersService.selectDeviceDataByDeviceIdAndTypeAndTime(type, deviceId, yearMonthDayAndHourMinuteSecond);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return weathersList;

	}
	
	
	@RequestMapping(value = "insertWeathers")
	@ResponseBody
	public String insertWeathers(Weathers weathers) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(new Date());
			System.out.println(weathers);
			Company companyId = new Company();
			companyId.setId(1);
			Device deviceId = new Device();
			deviceId.setDeviceId("$A2XDR");
			weathers.setCompanyId(companyId);
			weathers.setDeviceId(deviceId);
			weathers.setTime(time);
			weathers.setTa(weathers.getTa()  / 10);
			weathers.setPa(weathers.getPa() / 10);
			weathers.setUa(weathers.getUa() / 10);
			weathers.setSm(weathers.getSm() / 10);
			weathers.setDm(weathers.getDm() / 10);
			weathersService.insertWeathers(weathers);
			
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
		

}
