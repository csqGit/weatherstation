package com.bozpower;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bozpower.entity.Company;
import com.bozpower.entity.Device;
import com.bozpower.entity.PageData;
import com.bozpower.entity.Weathers;
import com.bozpower.mapper.WeathersMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeathersTest {
	
	@Autowired
	private WeathersMapper weathersMapper;
	
//	@Test
	public void insertWeathers() {
		Weathers weathers = new Weathers();
		weathers.setDm(20.0);
		try {
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//			formate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			formate.setTimeZone(TimeZone.getTimeZone("GMT-8"));
			String time = formate.format(new Date());
            Date targeDate = (Date)formate.parseObject(time);
            System.err.println(targeDate);
//			weathers.setTime(targeDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		Device device = new Device();
		device.setId(1);
		weathers.setDeviceId(device);
		Company c = new Company();
		c.setId(1);
		weathers.setCompanyId(c);
		weathersMapper.insertWeathers(weathers);
		System.out.println("新增成功");
		
	}
	
	
	@Test
	public void selectWeathers() {
		List<Weathers> list = null;
//		List<Weathers> list = weathersMapper.selectDeviceWeathersByDeviceId(new PageData(), 1);
//		list = weathersMapper
//				.selectDeviceWeathersHistoryDataByYMDSAndHMSS(
//						new PageData(), 
//						1, 
//						"2019-07-25 06:00:29", 
//						"2019-07-25 06:48:59");
//		list = weathersMapper
//				.selectDeviceWeathersHistoryDataByYMDAndHMS(
//						new PageData(), 
//						1, 
//						"2019-07-25 06");
//		list = weathersMapper
//				.selectDeviceWeathersHistoryDataByYMD(
//						new PageData(), 
//					"1",
//						"2019-07-25");
		System.out.println(list);
	}
	
	
//	@Test
	public void selectDeviceAllThisData() {
		Weathers weathers = new Weathers();
		Device device = new Device();
		device.setId(1);
		weathers.setDeviceId(device);
		Company c = new Company();
		c.setId(1);
		weathers.setCompanyId(c);
		List<Weathers> list = weathersMapper.selectDeviceAllThisData(new PageData(), 1);
		System.out.println(list);
	}

}
