'use strict';

var deprecate = require('util-deprecate');
var classNames = require('classnames');

module.exports = deprecate(classNames, '`rcUtil.joinClasses()` is deprecated, use `classNames()` by `require(\'classnames\')` instead');