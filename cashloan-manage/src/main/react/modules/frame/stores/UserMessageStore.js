var Reflux = require('reflux');
var AppActions = require('../actions/AppActions');

var UserMessageStore = Reflux.createStore({
    listenables: [AppActions],
    userMessage: {},
    onInitUserMessageStore() {
        this.queryUserMessage();
    },
    queryUserMessage() {
        Utils.ajaxData({
            url: '/modules/manage/system/user/find.htm',
            method: 'get',
            callback: (result) => {
                this.userMessage = result;
                this.update();
            }
        });
    },
    update() {
        this.trigger(this.userMessage);
    }
});

module.exports = UserMessageStore;