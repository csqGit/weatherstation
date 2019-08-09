package com.bozpower.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bozpower.entity.PageData;
import com.bozpower.entity.Weathers;

public interface WeathersService {
	/**
	 * 查询所有设备的实时数据
	 * 
	 * @param pageData
	 * @param weather
	 * @return
	 */
	List<Weathers> selectDeviceAllThisData(PageData pageData);
	
	/**
	 * 查询所有设备的实时数据的总条数
	 * @return
	 */
	int selectDeviceAllThisDataCount();
	
	/**c
	 * 分页查询设备id=deviceId在时间段(startTime, endTime)内的数据
	 * @param pageData
	 * @param deviceId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Weathers> selectDeviceWeathersHistoryDataByYMDSAndHMSS( PageData pageData,
			String deviceId, String startTime,  String endTime);
	
	
	int selectDeviceWeathersHistoryDataCountByYMDSAndHMSS( 
			String deviceId, String startTime,  String endTime);
	

	
	/**
	 * 按年月日时分秒
	 * @param pageData
	 * @param deviceId
	 * @param time
	 * @return
	 */
	List<Weathers> selectDeviceWeathersHistoryDataByYMDAndHMS( PageData pageData,
			String deviceId,
			 String time);
	
	/**
	 * 查询年月日时分秒的数据总数
	 * @param deviceId
	 * @param time
	 * @return
	 */
	int selectDeviceWeathersHistoryDataCountByYMDAndHMS(
			@Param("deviceId") String deviceId,
			@Param("time") String time);
	
	
	
	
	/**
	 * 查询年月日的数据集合
	 * @param pageData
	 * @param deviceId
	 * @param time
	 * @return
	 */
	List<Weathers> selectDeviceWeathersHistoryDataByYMD( PageData pageData,
			String deviceId,
			 String time);
	
	
	/**
	 * 按年月日查询设备的数据总数
	 * @param deviceId
	 * @param time
	 * @return
	 */
	int selectDeviceWeathersHistoryDataCountByYMD(@Param("deviceId") String deviceId,
			@Param("time") String time);
	
	
	/**
	 * 分页查询所有设备的历史数据集合
	 * @param pageData
	 * @return
	 */
	List<Weathers> selectDeviceAllWeathersHistoryDataList(PageData pageData);
	
	
	/**
	 * 查询所有设备的历史总数
	 * @return
	 */
	int selectDeviceAllWeathersHistoryDataCount();
	
	
	
	/**
	 * 根据设备id查询所有历史数据
	 * @param pageData
	 * @param deviceId
	 * @return
	 */
	List<Weathers> selectDeviceWeathersHistoryByDeviceId(@Param("pageData") PageData pageData,
			@Param("deviceId") String deviceId);
	
	/**
	 * 根据设备id查询所有历史数据总数
	 * @param deviceId
	 * @return
	 */
	int selectDeviceWeathersHistoryCountByDeviceId(
			@Param("deviceId") String deviceId);
	
	
	
	/**
	 * 按年月查询id为deviceId的数据集合
	 * @param pageData
	 * @param deviceId
	 * @param time
	 * @return
	 */
	List<Weathers> selectDeviceWeathersHistoryDataByYMAndDeviceId(
			PageData pageData,
			String deviceId,
			String time );
	/**
	 * 按年月查询id为deviceId的数据总数
	 * @param deviceId
	 * @param time
	 * @return
	 */
	int selectDeviceWeathersHistoryDataCountByYMAndDeviceId(
			@Param("deviceId") String deviceId,
			@Param("time") String time );
	
	
	/**
	 * 按年查询id为deviceId的数据集合
	 * @param pageData
	 * @param deviceId
	 * @param time
	 * @return
	 */
	List<Weathers> selectDeviceWeathersHistoryDataByYearAndDeviceId(
			PageData pageData,
			String deviceId,
			String time );
	
	
	int selectDeviceWeathersHistoryDataCountByYearAndDeviceId(
			@Param("deviceId") String deviceId,
			@Param("time") String time );
	
	
	
	List<Weathers> selectHistoryDataJSONList(String time);
	
	
	
	
	
	
	
	
	
	
	
}
