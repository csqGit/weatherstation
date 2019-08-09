package com.bozpower.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bozpower.entity.Device;
import com.bozpower.entity.PageData;

@Mapper
public interface DeviceMapper {

	public Device selectDeviceById(int id);
	
	public List<Device> selectDeviceList(PageData pageData);
	
	public Device selectDeviceNameByDeviceId(String deviceId) ;
	
	public Device selectDeviceByName(String deviceName);
	
	public void insertDevice(Device device);
	
	public void updateDevice(Device device);
	
	public void deleteDeviceById(int id);
	
	
	
}
