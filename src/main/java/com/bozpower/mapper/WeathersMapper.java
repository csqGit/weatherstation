package com.bozpower.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bozpower.entity.PageData;
import com.bozpower.entity.Weathers;

@Mapper
public interface WeathersMapper {

	
	void insertWeathers(@Param("weathers") Weathers weathers);
	
	
	/**
	 * 分页查询设备id为deviceId的所有数据
	 * @param pageData
	 * @param deviceId
	 * @return
	 */
	List<Weathers> selectDeviceWeathersByDeviceId(@Param("pageData") PageData pageData, @Param("deviceId") String deviceId);
	
	
	/**
	 * 查询所有设备的实时数据
	 * 
	 * @param pageData
	 * @param weather
	 * @return
	 */
	List<Weathers> selectDeviceAllThisData(@Param("pageData") PageData pageData);
	
	/**
	 * 查询所有设备的实时数据的总条数
	 * @return
	 */
	int selectDeviceAllThisDataCount();
	
	
	/**c
	 * 查询某个时间段的数据集合
	 * @param pageData
	 * @param deviceId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Weathers> selectDeviceWeathersHistoryDataByYMDSAndHMSS( 
			@Param("pageData") PageData pageData,
			@Param("deviceId") String deviceId,
			@Param("startTime") String startTime, 
			@Param("endTime") String endTime);
	
	int selectDeviceWeathersHistoryDataCountByYMDSAndHMSS( 
			@Param("deviceId") String deviceId,
			@Param("startTime") String startTime, 
			@Param("endTime") String endTime);
	/**
	 * 查询年月日时分秒的数据集合
	 * @param pageData
	 * @param deviceId
	 * @param startTime 开始时间的年月日不同
	 * @param endTime 结束时间的时分秒相同
	 * @return
	 */
	List<Weathers> selectDeviceWeathersHistoryDataByYMDAndHMS(
			@Param("pageData") PageData pageData,
			@Param("deviceId") String deviceId,
			@Param("time") String time);
	
	
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
	List<Weathers> selectDeviceWeathersHistoryDataByYMD(
			@Param("pageData") PageData pageData,
			@Param("deviceId") String deviceId,
			@Param("time") String time);
	
	
	/**
	 * 按年月日查询设备的数据总数
	 * @param deviceId
	 * @param time
	 * @return
	 */
	int selectDeviceWeathersHistoryDataCountByYMD(
			@Param("deviceId") String deviceId,
			@Param("time") String time);
	
	
	
	/**
	 * 分页查询所有设备的历史数据集合
	 * @param pageData
	 * @return
	 */
	List<Weathers> selectDeviceAllWeathersHistoryDataList(
			@Param("pageData") PageData pageData);
	
	
	int selectDeviceAllWeathersHistoryDataCount();
	
	
	/**
	 * 根据设备id查询所有历史数据
	 * @param pageData
	 * @param deviceId
	 * @return
	 */
	List<Weathers> selectDeviceWeathersHistoryByDeviceId(
			@Param("pageData") PageData pageData,
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
			@Param("pageData") PageData pageData,
			@Param("deviceId") String deviceId,
			@Param("time") String time );
	
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
			@Param("pageData") PageData pageData,
			@Param("deviceId") String deviceId,
			@Param("time") String time );
	
	/**
	 * 按年查询id为deviceId的数据总数
	 * @param deviceId
	 * @param time
	 * @return
	 */
	int selectDeviceWeathersHistoryDataCountByYearAndDeviceId(
			@Param("deviceId") String deviceId,
			@Param("time") String time );
	
	
	List<Weathers> selectHistoryDataJSONList(@Param("time") String time);
	

}
