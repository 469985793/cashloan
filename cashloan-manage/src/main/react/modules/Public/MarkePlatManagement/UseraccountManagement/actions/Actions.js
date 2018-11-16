var Reflux = require('reflux');

var Actions= Reflux.createActions([
    "initStore",
    "postMessage", 
    "setSelectData", 
    "setFormData"
]);

module.exports = Actions;
