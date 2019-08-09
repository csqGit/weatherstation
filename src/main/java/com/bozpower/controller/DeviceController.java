package com.bozpower.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bozpower.entity.Company;
import com.bozpower.entity.Device;
import com.bozpower.entity.PageData;
import com.bozpower.entity.User;
import com.bozpower.service.DeviceService;

@Controller
@RequestMapping("/deviceController")
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;
	
//	@Autowired
//	private CompanyService companyService;
	
	/**
	 * 根据id查询设备信息
	 * url: http://localhost:8080/deviceController/selectDeviceById?id=2
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "selectDeviceById", method = RequestMethod.GET)
	@ResponseBody
	public String selectDeviceById(int id, Map<String, Device> map) {
		try {
			map.put("device", deviceService.selectDeviceById(id));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * url: http://localhost:8080/deviceController/selectDeviceList?pages=3
	 * 查询设备列表
	 * @param pageData
	 * @return
	 */
	@RequestMapping(value = "selectDeviceList", method = RequestMethod.GET)
//	@ResponseBody
	public String selectDeviceList(PageData pageData, HttpServletRequest request, Map<String, Object> map){
		try {
			List<Device> deviceList = deviceService.selectDeviceList(pageData);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			map.put("deviceList", deviceList);
			map.put("company", user.getCompanyId());
			return "device";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}
	
	/**
	 * url: http://localhost:8080/deviceController/selectDeviceListJSON
	 * @return
	 */
	@RequestMapping(value = "selectDeviceListJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<Device> selectDeviceListJSON(){
		try {
			List<Device> device = deviceService.selectDeviceList(new PageData());
			return device;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	
	/**
	 * url: http://localhost:8080/deviceController/selectDeviceByName?deviceName=
	 * 根据设备名称查询设备信息
	 * @param deviceName
	 * @param deviceMap
	 * @return
	 */
	@RequestMapping(value = "selectDeviceByName", method = RequestMethod.GET)
	@ResponseBody
	public String selectDeviceByName(String deviceName, Map<String, Device> deviceMap) {
		try {
			Device device = deviceService.selectDeviceByName(deviceName);
			deviceMap.put("device", device);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	/**
	 * 新增设备信息
	 * url: http://localhost:8080/deviceController/insertDevice?deviceName=cs&deviceNumber=23&remarks=cs
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "insertDevice", method = RequestMethod.POST)
	@ResponseBody
	public String insertDevice(Device device) {
		try {
			deviceService.insertDevice(device);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	/**
	 * 根据id更新设备编号
	 * url: http://localhost:8080/deviceController/updateDevice?deviceName=sb&id=4
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "updateDevice", method = RequestMethod.POST)
	@ResponseBody
	public String updateDevice(Device device) {
		try {
			Company c = new Company();
			c.setId(1);
			device.setCompanyId(c);
			deviceService.updateDevice(device);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}
	
	
	/**
	 * 根据id删除设备信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteDeviceById", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDeviceById(int id) {
		try {
			deviceService.deleteDeviceById(id);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	@RequestMapping(value = "selectDeviceNameByDeviceId", method = RequestMethod.POST)
	@ResponseBody
	public Device selectDeviceNameByDeviceId(String deviceId, Map<String, Device> map) {
		Device device =  null;
		try {
			device = deviceService.selectDeviceNameByDeviceId(deviceId);
			map.put("device", device);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return device;
	}
	
	
}











