var Reflux = require('reflux');

var Actions= Reflux.createActions([
    "initStore",
    "postMessage", 
    "setSelectData", 
    "setFormData",
    "setCitySelectRecord"
]);

module.exports = Actions;
