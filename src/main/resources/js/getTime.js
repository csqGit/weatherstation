 $(function () {
          setInterval("GetTime()", 1000);
      });
      //获取时间并设置格式
      function GetTime() {
          var mon, day, now, hour, min, ampm, time, str, tz, end, beg, sec;
          /*
          mon = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
                 "Sep", "Oct", "Nov", "Dec");
         */
         mon = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月",
                 "九月", "十月", "十一月", "十二月");
         /*
         day = new Array("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
         */
         day = new Array("周日", "周一", "周二", "周三", "周四", "周五", "周六");
         now = new Date();
         hour = now.getHours();
         min = now.getMinutes();
         sec = now.getSeconds();
         if (hour < 10) {
             hour = "0" + hour;
         }
         if (min < 10) {
             min = "0" + min;
         }
         if (sec < 10) {
             sec = "0" + sec;
         }
         $("#Timer").html(
             now.getFullYear() + "年" + (now.getMonth() + 1) + "月" + now.getDate() + "日" + hour + ":" + min + ":" + sec
             );
         //$("#Timer").html(
         //        day[now.getDay()] + ", " + mon[now.getMonth()] + " "
         //                + now.getDate() + ", " + now.getFullYear() + " " + hour
         //                + ":" + min + ":" + sec);

     }