var Reflux = require('reflux');

var CustomerActions= Reflux.createActions([
    "initStore",
    "postMessage", 
    "setSelectData", 
    "setFormData"
]);

module.exports = CustomerActions;
