var Reflux = require('reflux');

var CustomerActions= Reflux.createActions([
    "initStore",
    "initDetailStore",
    "postMessage", 
    "setSelectData",
    "setSelectData2", 
    "setFormData",
    "initIncreaseInspectionStore"
]);

module.exports = CustomerActions;
