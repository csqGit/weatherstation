package com.bozpower.service.impl;

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
	public List<Weathers> selectDeviceAllThisData(PageData pageData) {
		return weathersMapper.selectDeviceAllThisData(pageData);
	}

	@Override
	public int selectDeviceAllThisDataCount() {
		return weathersMapper.selectDeviceAllThisDataCount();
	}

	@Override
	public List<Weathers> selectDeviceWeathersHistoryDataByYMDSAndHMSS(PageData pageData, String deviceId, String startTime,
			String endTime) {
		return weathersMapper.selectDeviceWeathersHistoryDataByYMDSAndHMSS(pageData, deviceId, startTime, endTime);
	}


	@Override
	public List<Weathers> selectDeviceWeathersHistoryDataByYMDAndHMS(PageData pageData, String deviceId, String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataByYMDAndHMS(pageData, deviceId, time);
	}

	@Override
	public List<Weathers> selectDeviceWeathersHistoryDataByYMD(PageData pageData, String deviceId, String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataByYMD(pageData, deviceId, time);
	}

	@Override
	public List<Weathers> selectDeviceAllWeathersHistoryDataList(PageData pageData) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceAllWeathersHistoryDataList(pageData);
	}

	@Override
	public int selectDeviceAllWeathersHistoryDataCount() {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceAllWeathersHistoryDataCount();
	}

	@Override
	public int selectDeviceWeathersHistoryDataCountByYMD(String deviceId, String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataCountByYMD(deviceId, time);
	}

	
	@Override
	public int selectDeviceWeathersHistoryDataCountByYMDAndHMS(String deviceId, String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataCountByYMDAndHMS(deviceId, time);
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
		return weathersMapper.selectDeviceWeathersHistoryDataByYMAndDeviceId(pageData, deviceId, time);
	}

	@Override
	public List<Weathers> selectDeviceWeathersHistoryDataByYearAndDeviceId(PageData pageData, String deviceId,
			String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataByYearAndDeviceId(pageData, deviceId, time);
	}

	@Override
	public int selectDeviceWeathersHistoryDataCountByYMAndDeviceId(String deviceId, String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataCountByYMAndDeviceId(deviceId, time);
	}

	@Override
	public int selectDeviceWeathersHistoryDataCountByYearAndDeviceId(String deviceId, String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataCountByYearAndDeviceId(deviceId, time);
	}

	@Override
	public List<Weathers> selectHistoryDataJSONList(String time) {
		// TODO Auto-generated method stub
		return weathersMapper.selectHistoryDataJSONList(time);
	}

	@Override
	public int selectDeviceWeathersHistoryDataCountByYMDSAndHMSS(String deviceId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return weathersMapper.selectDeviceWeathersHistoryDataCountByYMDSAndHMSS(deviceId, startTime, endTime);
	}

}
