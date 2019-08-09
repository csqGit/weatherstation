package com.bozpower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bozpower.entity.Device;
import com.bozpower.entity.PageData;
import com.bozpower.mapper.DeviceMapper;
import com.bozpower.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {
	
	@Autowired
	private DeviceMapper deviceMapper;

	@Override
	public Device selectDeviceById(int id) {
		return deviceMapper.selectDeviceById(id);
	}

	@Override
	public List<Device> selectDeviceList(PageData pageData) {
		return deviceMapper.selectDeviceList(pageData);
	}

	@Override
	public Device selectDeviceByName(String deviceName) {
		return deviceMapper.selectDeviceByName(deviceName);
	}

	@Override
	public void insertDevice(Device device) {
		deviceMapper.insertDevice(device);
	}

	@Override
	public void updateDevice(Device device) {
		deviceMapper.updateDevice(device);
	}

	@Override
	public void deleteDeviceById(int id) {
		deviceMapper.deleteDeviceById(id);
	}

	@Override
	public Device selectDeviceNameByDeviceId(String deviceId) {
		// TODO Auto-generated method stub
		return deviceMapper.selectDeviceNameByDeviceId(deviceId);
	}

}
