-- 分组查询设备的最后一条数据
select
    w.*
from
    weather_tb w
inner join 
    (select w.id, max(w.id) as maxid from weather_tb w group by device_id) d 
on
    w.id = d.maxid
	
-- 分组查询气象数据的最后一条数据
 select
     w.id,w.dn, w.dm, w.dx, w.sn, w.sm, w.sx, w.ta, w.ua, w.pa, w.year, w.month, w.day, w.hour, w.minute,
		w.second, c.company_name, d.device_name
 from
     weather_tb w
 inner join 
     (select  max(w.id) as maxid from weather_tb w group by device_id) as d 
 on
     w.id = d.maxid
INNER JOIN
	company_tb c
ON	
	w.company_id = c.id
INNER JOIN
	device_tb d
ON
	d.id = w.device_id
	
	
-- 分组查询每一个气象设备的信息集合
 select
     w.* , d.device_name
 from
     weather_tb w
INNER JOIN
	device_tb d
ON
	w.device_id = d.id
where 
		w.device_id = 2
		
		-- 按时间降序查询
SELECT w.* , d.device_name FROM weather_tb w 
INNER JOIN device_tb d ON w.device_id = d.id 
where w.device_id = 1 

ORDER BY year desc, month desc, day desc, hour desc, 
minute desc, second desc
		
		
-- 按年查询一年的数据，单位为月，将月进行分组，并求出每月的平均数
-- select * from weather_tb
select avg(w.dm), avg(w.dx), avg(w.dn), avg(w.sn), avg(w.sm), avg(w.sx), 
avg(w.ta), avg(w.ua), avg(w.pa), w.year, w.month from weather_tb w 
where year = 2019  and device_id = 1 GROUP BY month
		
-- 按月查询，单位为日，将日进行分组，并求平均数
select avg(w.dm), avg(w.dx), avg(w.dn), avg(w.sn), avg(w.sm), avg(w.sx), 
avg(w.ta), avg(w.ua), avg(w.pa), w.year, w.month, w.day from weather_tb w 
where   device_id = 1
and year = 2019 
and month = 7
GROUP BY day		


-- 按日查询，单位为时，将时进行分组，求平均数
select avg(w.dm), avg(w.dx), avg(w.dn), avg(w.sn), avg(w.sm), avg(w.sx), 
avg(w.ta), avg(w.ua), avg(w.pa), w.year, w.month, w.day , w.hour
from weather_tb w 
where   device_id = 1
and year = 2019 
and month = 7
and day = 22
GROUP BY hour


-- 按时查询，单位为分，将分进行分组，求平均数
select avg(w.dm), avg(w.dx), avg(w.dn), avg(w.sn), avg(w.sm), avg(w.sx), 
avg(w.ta), avg(w.ua), avg(w.pa), w.year, w.month, w.day , w.hour, w.minute
from weather_tb w 
where   device_id = 1
and year = 2019 
and month = 7
and day = 22
and hour = 14
GROUP BY minute	
		
		
		
		
		
		
		
		
		
		
		