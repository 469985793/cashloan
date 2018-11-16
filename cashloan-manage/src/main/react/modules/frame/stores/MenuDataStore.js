var Reflux = require('reflux');
var reqwest = require('reqwest');

var AppActions = require('../actions/AppActions');

export default Reflux.createStore({
    listenables: [AppActions],
    menuData: [],
    onInitStore() {
        this.queryData();
    },
    queryData() {
        Utils.ajaxData({
            url: '/modules/manage/system/roleMenu/find.htm?sysType=1',
            method: 'get',
            type: 'json',
            callback: (result) => {
                this.menuData = result.data;
                this.update();
            }
        });
    },
    update() {
        this.trigger(this.menuData);
    }
});