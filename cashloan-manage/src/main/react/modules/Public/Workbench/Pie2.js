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
            item:[],
            value:[],
            first: true
        }
    },
	fetch(data) {
        var me = this;
        me.setState({
            value1: data.repaySource[0]['自动代扣'],
            value2: data.repaySource[1]['银行卡转账'],
            value3: data.repaySource[2]['支付宝转账'],
            value4: data.repaySource[3]['其它'],
            first: false
        },() => {
            me.drawPie();
        });
 
    },
    drawPie() {
        var me = this
        var pie = echarts.init(document.getElementById('pie2'),'macarons');
        var option = {
            title: {
                text: '还款方式',
                x: 400,
				y:20,
                textStyle: {
                    color: '#666',
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c}笔 ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 400,
				top:'30%', 
                data: ['自动代扣','银行卡转账','支付宝转账','其他']
            },
            series: [{
                name: '还款方式',
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
                    value: me.state.value1,
                    name: '自动代扣'
                }, {
                    value: me.state.value2,
                    name: '银行卡转账'
                }, {
                    value: me.state.value3,
                    name: '支付宝转账'
                },{
                    value:me.state.value4,
                    name:'其他'
                }]
            }]
        };
        pie.setOption(option);
    },
    componentWillReceiveProps(nextProps) {
		if(nextProps.data.constructor == Object && this.state.first){
            this.fetch(nextProps.data);
        }
    },
    render() {
        return <div id="pie2" style={{ height: '280px', width: '500px', margin: '0 auto'  }}></div>
    }
});