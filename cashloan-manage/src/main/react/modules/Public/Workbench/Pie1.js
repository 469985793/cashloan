import React from 'react';
var echarts = require('echarts');
require("echarts/theme/macarons.js");
export default React.createClass({
    drawPie() {
        var pie = echarts.init(document.getElementById('pie'),'macarons');
        var option = {
            title: {
                text: '用户渠道来源',
                x: 'right',
                y:20,
                textStyle: {
                    color: '#666', 
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c}个 ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'right', 
                top:'30%',
                data: ['掌贷宝', '今日头条', '好友邀请', '自主下载', '其他']
            },
            series: [{
                name: '用户渠道来源',
                type: 'pie',
                radius: ['35%', '65%'],
                itemStyle: {
                    normal: {
                        label: {
                            position: 'inner',
                            formatter: function (params) {
                                return (params.percent - 0).toFixed(0) + '%'
                            }
                        },
                        labelLine: {
                            show: false
                        }
                    },
                },
                data: [{
                    value: 335,
                    name: '掌贷宝'
                }, {
                    value: 310,
                    name: '今日头条'
                }, {
                    value: 234,
                    name: '好友邀请'
                }, {
                    value:323,
                    name:'自主下载'
                },{
                    value: 135,
                    name: '其他'
                }]
            }]
        };
        pie.setOption(option);
    },
    componentDidMount() {
        this.drawPie();
    },
    render() {
        return <div id="pie" style={{ height: '280px', width: '370px' }}></div>
    }
});