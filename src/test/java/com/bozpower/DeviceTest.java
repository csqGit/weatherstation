package com.bozpower;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bozpower.entity.Company;
import com.bozpower.entity.Device;
import com.bozpower.entity.PageData;
import com.bozpower.mapper.CompanyMapper;
import com.bozpower.mapper.DeviceMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceTest {
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	
//	@Test
	public void selectDeviceTest() {
		Device d = deviceMapper.selectDeviceById(1);
		System.out.println(d);
	}
	
//	@Test
	public void selectDeviceByNameTest() {
		Device d = deviceMapper.selectDeviceByName("设备1");
		System.out.println(d);
	}
	
//	@Test
	public void selectDeviceListTest() {
		PageData pageData = new PageData();
		pageData.setStartPage(0);
		pageData.setPages(2);
		List<Device> d = deviceMapper.selectDeviceList(pageData, 9);
		System.out.println(d);
	}
	
	/**
	 * 测试未成功
	 */
//	@Test
	public void insertDeviceTest() {
		Device device = new Device();
		device.setId(3);
		device.setDeviceName("设备2");
		device.setRemarks("测试备注");
		Company c = new Company();
		c.setId(3);
		System.out.println(c);
		device.setCompanyId(c);
		deviceMapper.insertDevice(device);
	}
	
//	@Test
	public void updateDevice() {
		Device device = new Device();
		device.setId(2);
		device.setDeviceName("设备2修改");
		device.setRemarks("测试备注");
		Company c = companyMapper.selectCompanyById(1);
//		Company c = new Company();
//		c.setId(1);
//		System.out.println(c);
		device.setCompanyId(c);
		deviceMapper.updateDevice(device);
	}
	
//	@Test
	public void deleteDevice() {
		deviceMapper.deleteDeviceById(3);
	}
	

}
