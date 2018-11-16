import React from 'react';
var echarts = require('echarts');
require("echarts/theme/macarons.js");
export default React.createClass({
    getInitialState() {
        return {
            menuData: [],
            assetsData: {},
            investmentData: {},
            loading: false,
            data: [],
            data1: [],
            item: [],
            data2: [],
            max: 1,
            first: true
        }
    },
    fetch(data) {
        var me = this;
        var data3 = [];
        var data1 = [];
        var data2 = [];
        var item1 = [];
        var item2 = [];
        var max = 0;
        for (let item in data.fifteenDaysRealRepay) {
            item1.push(item.substring(5));
            item2.push(item);
        }
        for (var i = 0; i < item1.length - 1; i++) {
            for (var j = i + 1; j < item1.length; j++) {
                if (item1[i] > item1[j]) {
                    var z = item1[i];
                    item1[i] = item1[j];
                    item1[j] = z;
                    var q = item2[i];
                    item2[i] = item2[j];
                    item2[j] = q;
                }
            }
            data3.push(data.fifteenDaysNeedRepay[item2[i]]);
            data1.push(data.fifteenDaysRealRepay[item2[i]]);
            data2.push((parseFloat(data.fifteenDaysOverdueApr[item2[i]]) * 100).toFixed(2));
        }
        data3.push(data.fifteenDaysNeedRepay[item2[item1.length - 1]]);
        data1.push(data.fifteenDaysRealRepay[item2[item1.length - 1]]);
        data2.push((parseFloat(data.fifteenDaysOverdueApr[item2[item1.length - 1]]) * 100).toFixed(2))
        for (var i = 0; i < item1.length; i++) {
            if (max < data3[i]) {
                max = data3[i];
            }
            if (max < data1[i]) {
                max = data1[i];
            }
        }
        me.setState({
            data: data3,
            data1: data1,
            data2: data2,
            item: item1,
            max: max,
            first: false
        },() => {
            me.drawBar();
        });
        
    },
    drawBar() {
        var me = this;
        var bar = echarts.init(document.getElementById('bar2'), 'macarons');
        var option = {
            title: {
                text: '应还款量与实还款量',
                x: 'center',
                y: 10,
                textStyle: {
                    color: '#666',
                    fontWeight: 'normal'
                }
            },
            tooltip: {
                trigger: 'axis',
                formatter: function (params) {
                    var xAxis = option.xAxis;
                    var res = params[0].name + "<br/>"
                    var series = option.series;
                    for (var i = 0; i < xAxis[0].data.length; i++) {
                        if(xAxis[0].data[i] == params[0].name){
                            res += series[0].name + ': ' + series[0].data[i] + '笔<br/>' + series[1].name + ': ' + series[1].data[i] + '笔<br/>' + series[2].name + ': ' + series[2].data[i] + '%<br/>'
                        }
                    }
                    return res;
                },
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                textStyle: {
                    align: 'left'
                }
            },
            barWidth: '10',
            legend: {
                orient: 'horizontal',
                x: 'center',
                y: 50,
                itemGap: 12,
                data: ['应还款量', '实还款量']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: me.state.item
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    splitLine: { show: false },
                    scale: true,
                    name: '还款量',
                    max: me.state.max % 2 == 0 ? me.state.max : me.state.max + 1,
                    min: 0,
                },
                {
                    type: 'value',
                    splitLine: { show: false },
                    scale: true,
                    name: '逾期率(%)',
                    max: 100,
                    min: 0,
                }
            ],
            series: [
                {
                    name: '应还款量',
                    type: 'bar',
                    data: me.state.data
                },
                {
                    name: '实还款量',
                    type: 'bar',
                    stack: '广告',
                    data: me.state.data1
                },
                {
                    name: '逾期率',
                    type: 'line',
                    yAxisIndex: 1,
                    data: me.state.data2
                }
            ]
        };
        bar.setOption(option);
    },
    componentWillReceiveProps(nextProps) {
		if(nextProps.data.constructor == Object && this.state.first){
            this.fetch(nextProps.data);
        }
        
    },
    render() {
        return <div id="bar2" style={{ height: '340px', width: '550px', margin: '0 auto' }}></div>
    }
});