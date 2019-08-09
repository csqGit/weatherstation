package com.bozpower.service;

import java.util.List;

import com.bozpower.entity.Device;
import com.bozpower.entity.PageData;

public interface DeviceService {
	public Device selectDeviceById(int id);
	
	public List<Device> selectDeviceList(PageData pageData);
	
	public Device selectDeviceByName(String deviceName);
	
	public void insertDevice(Device device);
	
	public void updateDevice(Device device);
	
	public void deleteDeviceById(int id);
	
	
	public Device selectDeviceNameByDeviceId(String deviceId) ;
	
}
