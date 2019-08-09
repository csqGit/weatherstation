var developMode = false;
if (developMode) {
    window.esl = null;
    window.define = null;
    window.require = null;
    (function () {
        var script = document.createElement('script');
        script.async = true;

        script.src = '../../doc/asset/js/esl/esl.js';
        if (script.readyState) {
            script.onreadystatechange = fireLoad;
        }
        else {
            script.onload = fireLoad;
        }
        (document.getElementsByTagName('head')[0] || document.body).appendChild(script);
        
        function fireLoad() {
            script.onload = script.onreadystatechange = null;
            setTimeout(loadedListener,100);
        }
        function loadedListener() {
            // for develop
            require.config({
                packages: [
                    {
                        name: 'echarts',
                        location: '../../src',
                        main: 'echarts'
                    },
                    {
                        name: 'zrender',
                        //location: 'http://ecomfe.github.io/zrender/src',
                        location: '../../../zrender/src',
                        main: 'zrender'
                    }
                ]
            });
            launchExample();
        }
    })();
}
else {
    // for echarts online home page
    require.config({
        paths:{ 
            echarts: '../../build/dist'
        }
    });
    launchExample();
}

var isExampleLaunched;
function launchExample() {
    if (isExampleLaunched) {
        return;
    }

    // 按需加载
    isExampleLaunched = 1;
    require(
        [
            'echarts',
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/scatter',
            'echarts/chart/k',
            'echarts/chart/pie',
            'echarts/chart/radar',
            'echarts/chart/force',
            'echarts/chart/chord',
            'echarts/chart/map',
            'echarts/chart/gauge',
            'echarts/chart/funnel',
            'echarts/chart/venn',
            'echarts/chart/treemap',
            'echarts/chart/tree',
            'echarts/chart/eventRiver'
        ],
        requireCallback
    );
}

var echarts ;
var myChart;
var year = document.getElementById("year");
var yearMonth = document.getElementById("yearMonth");
var yearMonthDay = document.getElementById("yearMonthDay");
var hourMinuteSecond = document.getElementById("hourMinuteSecond");
var yearMonthDayHourMinuteSecond = document.getElementById("yearMonthDayHourMinuteSecond");



//该函数一直运行，被页面加载
function requireCallback (ec) {
	//ec的值为{"version":"2.2.7","dependencies":{"zrender":"2.1.1"}}
    echarts = ec;
    myChart = echarts.init(domMain);//将图形加载到#main标签中
    var opt = option('line');
    myChart.setOption(opt, true);//将option添加到页面中
    document.getElementById('res').innerHTML = 'ready!<br/>';
    document.getElementById('run').onclick = start;
    document.getElementById('auto-run').onclick = autoStart;
}
var domMain = document.getElementById('main');
var isRunning = false;

var isRunning;
var round;
var n;
var result;
var total;
var chartType;


//开始检测
function start() {
    if (isRunning) {
        return;
    }
    isRunning = true;
    document.getElementById('res').innerHTML += 'running ';

    round = document.getElementById('round').value;
    n = document.getElementById('count').value;
    result = [];//被赋值数据结果数组
    total = 0;
    chartType = document.getElementById('chart').value;
    setTimeout(run,50);
}

var cList = [
    'line','bar','scatter','k',
    'pie','radar','chord','map'
];
var autoIdx = 0;
var autoRun = false;


function autoStart() {
    if (isRunning) {
        return;
    }
    autoIdx = 0;
    autoRun = true;
    autoCheck();
}

//自动检测
function autoCheck() {
    if (autoRun && autoIdx < cList.length) {
        document.getElementById('chart').value = cList[autoIdx++];
        start();
    }
    else {
        autoRun = false;
        autoIdx = 0;
    }
}

//测试图形加载速度
function run(){
    if (round--) {//round的值为4	3	2	1	0
    	
//        var opt = option(chartType, n - 0);
    	opt = option(chartType)
    	var dataP = randomData[0];
        var ticket = new Date();
        myChart.setOption(opt, true);
        ticket = new Date() - ticket;
        total += ticket
        result.push(ticket);
        setTimeout(run,200);
//        myChart.showLoading();
        
    }
    else {
//        myChart.hideLoading();
        isRunning = false;
        autoCheck();
    }
}