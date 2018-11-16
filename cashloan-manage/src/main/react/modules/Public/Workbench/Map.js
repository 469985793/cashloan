import React from 'react';
var echarts = require('echarts');
require('echarts/map/js/china');
require("echarts/theme/macarons.js");
export default React.createClass({
    getInitialState() {
        return {
            menuData: [],
            assetsData: {},
            investmentData: {},
            loading: false,
            monthBorrowAmt: [],
            monthRegister: [],
            monthBorrowRepay: [],
            first: true
        }
    },
    fetch(data) {
        var me = this;
        var json1 = [];
        var json3 = [];
        var json4 = [];
        for (let item1 in data.monthBorrowAmt) {
            json1.push({ 'name': item1, 'value': data.monthBorrowAmt[item1] });

        }
        for (let item3 in data.monthRegister) {
            json3.push({ 'name': item3, 'value': data.monthRegister[item3] });
        }
        for (let item4 in data.monthBorrowRepay) {
            json4.push({ 'name': item4, 'value': data.monthBorrowRepay[item4] });
        }
        me.setState({
            monthBorrowAmt: json1,
            monthRegister: json3,
            monthBorrowRepay: json4,
            first: false
        },() => {
            me.drawMap();
        });
        

    },
    drawMap() {
        var me = this;
        var map = echarts.init(document.getElementById('map'), 'macarons');

        var mapOption = {
            title: {
                text: '用户地域分布',
                subtext: '',
                left: 'center',
                y: 10,
                textStyle: {
                    color: '#666',
                    fontWeight: 'normal'
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: function (params) {
                    var province = params.name;
                    if (!province) {
                        province = '南沙群岛';
                    }
                    var res = province + '</br>';
                    var series = mapOption.series;
                    var monad;
                    for (var j = 0; j < series.length; j++) {
                        for (var i = 0; i < series[j].data.length; i++) {
                            if (series[j].data[i].name == province) {
                                switch (series[j].name) {
                                    case '累计注册用户': monad = '个'; break;
                                    case '累计放款金额': monad = '元'; break;
                                    case '累计还款金额': monad = '元'; break;
                                    default: monad = ''; break;
                                }
                                res += series[j].name + ': ' + (series[j].data[i] && series[j].data[i].value ? series[j].data[i].value : 0) + monad + '<br/>';
                            }
                        }
                    }
                    return res;
                },
                textStyle: {
                    align: 'left'
                }
            },
            legend: {
                orient: 'vertical',
                left: '20',
                top: '20',
                data: ['累计注册用户', '累计放款金额', '累计还款金额']
            },
            toolbox: {
                show: true,
                orient: 'horizontal',
                right: '20',
                top: '20',
                feature: {
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '累计注册用户',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: me.state.monthRegister
                },
                {
                    name: '累计放款金额',
                    type: 'map',
                    mapType: 'china',
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: me.state.monthBorrowAmt
                },
                {
                    name: '累计还款金额',
                    type: 'map',
                    mapType: 'china',
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: me.state.monthBorrowRepay
                }
            ]
        };
        map.setOption(mapOption);
    },
    componentWillReceiveProps(nextProps) {
		if(nextProps.data.constructor == Object && this.state.first){
            this.fetch(nextProps.data);
        }
        
    },
    render() {
        return <div id="map" style={{ height: '400px', width: '750px', margin: '0 auto' }}></div>
    }
});