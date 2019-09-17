package com.bozpower.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bozpower.entity.PageData;
import com.bozpower.entity.Weathers;
import com.bozpower.mapper.WeathersMapper;
import com.bozpower.service.WeathersService;

@Service
public class WeathersServiceImpl implements WeathersService {
	
	@Autowired
	private WeathersMapper weathersMapper;

	@Override
	public List<Weathers> selectDeviceAllThisData(PageData pageData, int companyId) {
		return weathersMapper.selectDeviceAllThisData(pageData, companyId);
	}

	@Override
	public int selectDeviceAllThisDataCount(int companyId) {
		return weathersMapper.selectDeviceAllThisDataCount(companyId);
	}

	@Override
	public List<Weathers> selectDeviceWeathersHistoryDataByYMDSAndHMSS(PageData pageData, String deviceId,
			 int companyId, String startTime, String endTime) {
		return weathersMapper.selectDeviceWeathersHistoryDataByYMDSAndHMSS(pageData, deviceId,companyId, startTime, endTime);
	}


	@Override
	public List<Weathers> selectDeviceWeathersHistoryDataByYMDAndHMS(
			PageData pageData, String deviceId,  String time) {
		// TODO Auto-generated method stub
//		return weathersMapper.selectDeviceWeathersHistoryDataByYMDAndHMS(pageData, deviceId, time);
		return null;
	}

	@Override
	public List<Weathers> selectDeviceWeathersHistoryDataByYMD(PageData pageData, String deviceId, String time) {
		// TODO Auto-generated method stub
//		return weathersMapper.selectDeviceWeathersHistoryDataByYMD(pageData, deviceId, time);
		return null;
	}

	@Override
	public List<Weathers> selectDeviceAllWeathersHistoryDataList(PageData pageData, int companyId) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceAllWeathersHistoryDataList(pageData, companyId);
	}

	@Override
	public int selectDeviceAllWeathersHistoryDataCount(int companyId) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceAllWeathersHistoryDataCount(companyId);
	}

	@Override
	public int selectDeviceWeathersHistoryDataCountByYMD(String deviceId, String time) {
		// TODO Auto-generated method stub
//		return weathersMapper.selectDeviceWeathersHistoryDataCountByYMD(deviceId, time);
		return 0;
	}

	
	@Override
	public int selectDeviceWeathersHistoryDataCountByYMDAndHMS(String deviceId, String time) {
		// TODO Auto-generated method stub
//		return weathersMapper.selectDeviceWeathersHistoryDataCountByYMDAndHMS(deviceId, time);
		return 0;
	}

	@Override
	public List<Weathers> selectDeviceWeathersHistoryByDeviceId(PageData pageData, String deviceId) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryByDeviceId(pageData, deviceId);
	}

	@Override
	public int selectDeviceWeathersHistoryCountByDeviceId(String deviceId) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryCountByDeviceId(deviceId);
	}

	@Override
	public List<Weathers> selectDeviceWeathersHistoryDataByYMAndDeviceId(PageData pageData, String deviceId, String time) {
		// TODO Auto-generated method stub
//		return weathersMapper.selectDeviceWeathersHistoryDataByYMAndDeviceId(pageData, deviceId, time);
		return null;
	}

	@Override
	public List<Weathers> selectDeviceWeathersHistoryDataByDeviceIdAndTime(PageData pageData, String deviceId,
			String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataByDeviceIdAndTime(pageData, deviceId, time);
	}

	@Override
	public int selectDeviceWeathersHistoryDataCountByYMAndDeviceId(String deviceId, String time) {
		// TODO Auto-generated method stub
//		return weathersMapper.selectDeviceWeathersHistoryDataCountByYMAndDeviceId(deviceId, time);
		return 0;
	}

	@Override
	public int selectDeviceWeathersHistoryDataCountByDeviceIdAndTime(String deviceId, String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataCountByDeviceIdAndTime(deviceId, time);
	}

	@Override
	public List<Weathers> selectHistoryDataJSONList(String time) {
		// TODO Auto-generated method stub
//		return weathersMapper.selectHistoryDataJSONList(time);
		return null;
	}

	@Override
	public int selectDeviceWeathersHistoryDataCountByYMDSAndHMSS(
			String deviceId, int companyId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataCountByYMDSAndHMSS(deviceId, companyId,startTime, endTime);
	}

	@Override
	public List<Weathers> selectDeviceDataByDeviceIdAndTypeAndTime(
			Integer type, 
			String deviceId, 
			String time) {
		
		if (time != null && !"".equals(time)) {
			String[] ymd = time.split("~");
			// 按时分秒区间查询
			String startTime = ymd[0];
			String endTime = ymd[1];
			// 查询数据
			return weathersMapper.selectDeviceDataByDeviceIdAndTypeAndTime(
							type, deviceId, null, startTime, endTime);
		}else {
			// 查询当前时间的设备数据集合
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
			time = format.format(new Date());
			return weathersMapper.selectDeviceDataByDeviceIdAndTypeAndTime(type, deviceId, time, null, null);
		}

	}

	@Override
	public int insertWeathers(Weathers weathers) {
		// TODO Auto-generated method stub
		return weathersMapper.insertWeathers(weathers);
	}

}
