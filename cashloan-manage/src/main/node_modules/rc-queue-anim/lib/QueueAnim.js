'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

var _reactDom = require('react-dom');

var _utils = require('./utils');

var _animTypes = require('./animTypes');

var _animTypes2 = _interopRequireDefault(_animTypes);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _defaults(obj, defaults) { var keys = Object.getOwnPropertyNames(defaults); for (var i = 0; i < keys.length; i++) { var key = keys[i]; var value = Object.getOwnPropertyDescriptor(defaults, key); if (value && value.configurable && obj[key] === undefined) { Object.defineProperty(obj, key, value); } } return obj; }

function _objectWithoutProperties(obj, keys) { var target = {}; for (var i in obj) { if (keys.indexOf(i) >= 0) continue; if (!Object.prototype.hasOwnProperty.call(obj, i)) continue; target[i] = obj[i]; } return target; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : _defaults(subClass, superClass); }

var _ease = {
  easeInElastic: function easeInElastic(_p, o, t) {
    var p = _p;
    var _p1 = o >= 1 ? o : 1;
    var _p2 = (t || 1) / (o < 1 ? o : 1);
    var _p3 = _p2 / Math.PI * 2 * (Math.asin(1 / _p1) || 0);
    return -(_p1 * Math.pow(2, 10 * (p -= 1)) * Math.sin((p - _p3) * _p2));
  },
  easeOutElastic: function easeOutElastic(p, o, t) {
    var _p1 = o >= 1 ? o : 1;
    var _p2 = (t || 1) / (o < 1 ? o : 1);
    var _p3 = _p2 / Math.PI * 2 * (Math.asin(1 / _p1) || 0);
    return _p1 * Math.pow(2, -10 * p) * Math.sin((p - _p3) * _p2) + 1;
  },
  easeInOutElastic: function easeInOutElastic(_p, o, t) {
    var p = _p;
    var _p1 = o >= 1 ? o : 1;
    var _p2 = (t || 1) / (o < 1 ? o : 1);
    var _p3 = _p2 / Math.PI * 2 * (Math.asin(1 / _p1) || 0);
    p *= 2;
    return p < 1 ? -0.5 * (_p1 * Math.pow(2, 10 * (p -= 1)) * Math.sin((p - _p3) * _p2)) : _p1 * Math.pow(2, -10 * (p -= 1)) * Math.sin((p - _p3) * _p2) * 0.5 + 1;
  },
  easeInBounce: function easeInBounce(_p) {
    var p = _p;
    var __p = 1 - p;
    if (__p < 1 / 2.75) {
      return 1 - 7.5625 * p * p;
    } else if (p < 2 / 2.75) {
      return 1 - (7.5625 * (p -= 1.5 / 2.75) * p + 0.75);
    } else if (p < 2.5 / 2.75) {
      return 1 - (7.5625 * (p -= 2.25 / 2.75) * p + 0.9375);
    }
    return 1 - (7.5625 * (p -= 2.625 / 2.75) * p + 0.984375);
  },
  easeOutBounce: function easeOutBounce(_p) {
    var p = _p;
    if (p < 1 / 2.75) {
      return 7.5625 * p * p;
    } else if (p < 2 / 2.75) {
      return 7.5625 * (p -= 1.5 / 2.75) * p + 0.75;
    } else if (p < 2.5 / 2.75) {
      return 7.5625 * (p -= 2.25 / 2.75) * p + 0.9375;
    }
    return 7.5625 * (p -= 2.625 / 2.75) * p + 0.984375;
  },
  easeInOutBounce: function easeInOutBounce(_p) {
    var p = _p;
    var invert = p < 0.5;
    if (invert) {
      p = 1 - p * 2;
    } else {
      p = p * 2 - 1;
    }
    if (p < 1 / 2.75) {
      p = 7.5625 * p * p;
    } else if (p < 2 / 2.75) {
      p = 7.5625 * (p -= 1.5 / 2.75) * p + 0.75;
    } else if (p < 2.5 / 2.75) {
      p = 7.5625 * (p -= 2.25 / 2.75) * p + 0.9375;
    } else {
      p = 7.5625 * (p -= 2.625 / 2.75) * p + 0.984375;
    }
    return invert ? (1 - p) * 0.5 : p * 0.5 + 0.5;
  }
};

var velocity = void 0;
if (typeof document !== 'undefined' && typeof window !== 'undefined') {
  // only load velocity on the client
  velocity = require('velocity-animate');
  Object.keys(_ease).forEach(function (key) {
    if (velocity.Easings) {
      velocity.Easings[key] = _ease[key];
    }
  });
} else {
  // provide a velocity stub for the server
  velocity = function velocityServerDummy() {
    var callback = arguments[arguments.length - 1];
    // call after stack flushes
    // in case you app depends on the asyncron nature of this function
    setImmediate(function () {
      return callback();
    });
  };
}

var BackEase = {
  easeInBack: [0.6, -0.28, 0.735, 0.045],
  easeOutBack: [0.175, 0.885, 0.32, 1.275],
  easeInOutBack: [0.68, -0.55, 0.265, 1.55]
};

var placeholderKeyPrefix = 'ant-queue-anim-placeholder-';

var QueueAnim = function (_React$Component) {
  _inherits(QueueAnim, _React$Component);

  function QueueAnim() {
    _classCallCheck(this, QueueAnim);

    var _this = _possibleConstructorReturn(this, _React$Component.apply(this, arguments));

    _this.keysToEnter = [];
    _this.keysToLeave = [];
    _this.keysAnimating = [];
    _this.placeholderTimeoutIds = {};

    // 第一次进入，默认进场
    var children = (0, _utils.toArrayChildren)((0, _utils.getChildrenFromProps)(_this.props));
    children.forEach(function (child) {
      if (!child || !child.key) {
        return;
      }
      _this.keysToEnter.push(child.key);
    });

    _this.originalChildren = (0, _utils.toArrayChildren)((0, _utils.getChildrenFromProps)(_this.props));

    _this.state = {
      children: children,
      childrenShow: {}
    };

    ['performEnter', 'performLeave', 'enterBegin', 'leaveComplete'].forEach(function (method) {
      return _this[method] = _this[method].bind(_this);
    });
    return _this;
  }

  QueueAnim.prototype.componentDidMount = function componentDidMount() {
    this.componentDidUpdate();
  };

  QueueAnim.prototype.componentWillReceiveProps = function componentWillReceiveProps(nextProps) {
    var _this2 = this;

    var nextChildren = (0, _utils.toArrayChildren)(nextProps.children);
    var currentChildren = this.originalChildren;
    var newChildren = (0, _utils.mergeChildren)(currentChildren, nextChildren);

    this.keysToEnter = [];
    this.keysToLeave = [];
    this.keysAnimating = [];

    // need render to avoid update
    this.setState({
      children: newChildren
    });

    nextChildren.forEach(function (c) {
      if (!c) {
        return;
      }
      var key = c.key;
      var hasPrev = (0, _utils.findChildInChildrenByKey)(currentChildren, key);
      if (!hasPrev && key) {
        _this2.keysToEnter.push(key);
      }
    });

    currentChildren.forEach(function (c) {
      if (!c) {
        return;
      }
      var key = c.key;
      var hasNext = (0, _utils.findChildInChildrenByKey)(nextChildren, key);
      if (!hasNext && key) {
        _this2.keysToLeave.push(key);
      }
    });
  };

  QueueAnim.prototype.componentDidUpdate = function componentDidUpdate() {
    this.originalChildren = (0, _utils.toArrayChildren)((0, _utils.getChildrenFromProps)(this.props));
    var keysToEnter = Array.prototype.slice.call(this.keysToEnter);
    var keysToLeave = Array.prototype.slice.call(this.keysToLeave);
    if (this.keysAnimating.length === 0) {
      this.keysAnimating = keysToEnter.concat(keysToLeave);
    }
    keysToEnter.forEach(this.performEnter);
    keysToLeave.forEach(this.performLeave);
  };

  QueueAnim.prototype.componentWillUnmount = function componentWillUnmount() {
    var _this3 = this;

    [].concat(this.keysToEnter, this.keysToLeave, this.keysAnimating).forEach(function (key) {
      return _this3.refs[key] && velocity((0, _reactDom.findDOMNode)(_this3.refs[key]), 'stop');
    });
    Object.keys(this.placeholderTimeoutIds).forEach(function (key) {
      clearTimeout(_this3.placeholderTimeoutIds[key]);
    });
    this.keysToEnter = [];
    this.keysToLeave = [];
    this.keysAnimating = [];
  };

  QueueAnim.prototype.getVelocityConfig = function getVelocityConfig(index) {
    for (var _len = arguments.length, args = Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
      args[_key - 1] = arguments[_key];
    }

    if (this.props.animConfig) {
      return _utils.transformArguments.apply(undefined, [this.props.animConfig].concat(args))[index];
    }
    return _animTypes2["default"][_utils.transformArguments.apply(undefined, [this.props.type].concat(args))[index]];
  };

  QueueAnim.prototype.getVelocityEnterConfig = function getVelocityEnterConfig() {
    for (var _len2 = arguments.length, args = Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
      args[_key2] = arguments[_key2];
    }

    return this.getVelocityConfig.apply(this, [0].concat(args));
  };

  QueueAnim.prototype.getVelocityLeaveConfig = function getVelocityLeaveConfig() {
    for (var _len3 = arguments.length, args = Array(_len3), _key3 = 0; _key3 < _len3; _key3++) {
      args[_key3] = arguments[_key3];
    }

    var config = this.getVelocityConfig.apply(this, [1].concat(args));
    var ret = {};
    Object.keys(config).forEach(function (key) {
      if (Array.isArray(config[key])) {
        ret[key] = Array.prototype.slice.call(config[key]).reverse();
      } else {
        ret[key] = config[key];
      }
    });
    return ret;
  };

  QueueAnim.prototype.getVelocityEasing = function getVelocityEasing() {
    for (var _len4 = arguments.length, args = Array(_len4), _key4 = 0; _key4 < _len4; _key4++) {
      args[_key4] = arguments[_key4];
    }

    return _utils.transformArguments.apply(undefined, [this.props.ease].concat(args)).map(function (easeName) {
      if (typeof easeName === 'string') {
        return BackEase[easeName] || easeName;
      }
    });
  };

  QueueAnim.prototype.performEnter = function performEnter(key, i) {
    var interval = (0, _utils.transformArguments)(this.props.interval, key, i)[0];
    var delay = (0, _utils.transformArguments)(this.props.delay, key, i)[0];
    this.placeholderTimeoutIds[key] = setTimeout(this.performEnterBegin.bind(this, key, i), interval * i + delay);
    if (this.keysToEnter.indexOf(key) >= 0) {
      this.keysToEnter.splice(this.keysToEnter.indexOf(key), 1);
    }
  };

  QueueAnim.prototype.performEnterBegin = function performEnterBegin(key, i) {
    var childrenShow = this.state.childrenShow;
    childrenShow[key] = true;
    this.setState({ childrenShow: childrenShow }, this.realPerformEnter.bind(this, key, i));
  };

  QueueAnim.prototype.realPerformEnter = function realPerformEnter(key, i) {
    var node = (0, _reactDom.findDOMNode)(this.refs[key]);
    if (!node) {
      return;
    }
    var duration = (0, _utils.transformArguments)(this.props.duration, key, i)[0];
    node.style.visibility = 'hidden';
    velocity(node, 'stop');
    velocity(node, this.getVelocityEnterConfig(key, i), {
      duration: duration,
      easing: this.getVelocityEasing(key, i)[0],
      visibility: 'visible',
      begin: this.enterBegin.bind(this, key),
      complete: this.enterComplete.bind(this, key)
    });
  };

  QueueAnim.prototype.performLeave = function performLeave(key, i) {
    clearTimeout(this.placeholderTimeoutIds[key]);
    delete this.placeholderTimeoutIds[key];
    var node = (0, _reactDom.findDOMNode)(this.refs[key]);
    if (!node) {
      return;
    }
    var interval = (0, _utils.transformArguments)(this.props.interval, key, i)[1];
    var delay = (0, _utils.transformArguments)(this.props.delay, key, i)[1];
    var duration = (0, _utils.transformArguments)(this.props.duration, key, i)[1];
    var order = this.props.leaveReverse ? this.keysToLeave.length - i - 1 : i;
    velocity(node, 'stop');
    velocity(node, this.getVelocityLeaveConfig(key, i), {
      delay: interval * order + delay,
      duration: duration,
      easing: this.getVelocityEasing(key, i)[1],
      begin: this.leaveBegin.bind(this),
      complete: this.leaveComplete.bind(this, key)
    });
  };

  QueueAnim.prototype.enterBegin = function enterBegin(key, elements) {
    var _this4 = this;

    elements.forEach(function (elem) {
      var animatingClassName = _this4.props.animatingClassName;
      elem.className = elem.className.replace(animatingClassName[1], '');
      if (elem.className.indexOf(animatingClassName[0]) === -1) {
        elem.className += ' ' + animatingClassName[0];
      }
    });
  };

  QueueAnim.prototype.enterComplete = function enterComplete(key, elements) {
    var _this5 = this;

    if (this.keysAnimating.indexOf(key) >= 0) {
      this.keysAnimating.splice(this.keysAnimating.indexOf(key), 1);
    }
    elements.forEach(function (elem) {
      elem.className = elem.className.replace(_this5.props.animatingClassName[0], '').trim();
    });
  };

  QueueAnim.prototype.leaveBegin = function leaveBegin(elements) {
    var _this6 = this;

    elements.forEach(function (elem) {
      var animatingClassName = _this6.props.animatingClassName;
      elem.className = elem.className.replace(animatingClassName[0], '');
      if (elem.className.indexOf(animatingClassName[1]) === -1) {
        elem.className += ' ' + animatingClassName[1];
      }
    });
  };

  QueueAnim.prototype.leaveComplete = function leaveComplete(key, elements) {
    var _this7 = this;

    if (this.keysAnimating.indexOf(key) < 0) {
      return;
    }
    this.keysAnimating.splice(this.keysAnimating.indexOf(key), 1);
    var childrenShow = this.state.childrenShow;
    childrenShow[key] = false;
    if (this.keysToLeave.indexOf(key) >= 0) {
      this.keysToLeave.splice(this.keysToLeave.indexOf(key), 1);
    }
    var needLeave = this.keysToLeave.some(function (c) {
      return childrenShow[c];
    });
    if (!needLeave) {
      var currentChildren = (0, _utils.toArrayChildren)((0, _utils.getChildrenFromProps)(this.props));
      this.setState({
        children: currentChildren,
        childrenShow: childrenShow
      });
    }
    elements.forEach(function (elem) {
      elem.className = elem.className.replace(_this7.props.animatingClassName[1], '').trim();
    });
  };

  QueueAnim.prototype.render = function render() {
    var _this8 = this;

    var childrenToRender = (0, _utils.toArrayChildren)(this.state.children).map(function (child) {
      if (!child || !child.key) {
        return child;
      }
      return _this8.state.childrenShow[child.key] ? (0, _react.cloneElement)(child, {
        ref: child.key,
        key: child.key
      }) : (0, _react.createElement)('div', {
        ref: placeholderKeyPrefix + child.key,
        key: placeholderKeyPrefix + child.key
      });
    });

    var tagProps = _objectWithoutProperties(this.props, []);

    ['component', 'interval', 'duration', 'delay', 'type', 'animConfig', 'ease', 'leaveReverse', 'animatingClassName'].forEach(function (key) {
      return delete tagProps[key];
    });
    return (0, _react.createElement)(this.props.component, _extends({}, tagProps), childrenToRender);
  };

  return QueueAnim;
}(_react2["default"].Component);

var numberOrArray = _react2["default"].PropTypes.oneOfType([_react2["default"].PropTypes.number, _react2["default"].PropTypes.array]);
var stringOrArray = _react2["default"].PropTypes.oneOfType([_react2["default"].PropTypes.string, _react2["default"].PropTypes.array]);
var objectOrArray = _react2["default"].PropTypes.oneOfType([_react2["default"].PropTypes.object, _react2["default"].PropTypes.array]);
var funcOrString = _react2["default"].PropTypes.oneOfType([_react2["default"].PropTypes.func, _react2["default"].PropTypes.string]);
var funcOrStringOrArray = _react2["default"].PropTypes.oneOfType([_react2["default"].PropTypes.func, stringOrArray]);
var funcOrObjectOrArray = _react2["default"].PropTypes.oneOfType([_react2["default"].PropTypes.func, objectOrArray]);
var funcOrNumberOrArray = _react2["default"].PropTypes.oneOfType([_react2["default"].PropTypes.func, numberOrArray]);

QueueAnim.propTypes = {
  component: funcOrString,
  interval: numberOrArray,
  duration: funcOrNumberOrArray,
  delay: funcOrNumberOrArray,
  type: funcOrStringOrArray,
  animConfig: funcOrObjectOrArray,
  ease: funcOrStringOrArray,
  leaveReverse: _react2["default"].PropTypes.bool,
  animatingClassName: _react2["default"].PropTypes.array
};

QueueAnim.defaultProps = {
  component: 'div',
  interval: 100,
  duration: 450,
  delay: 0,
  type: 'right',
  animConfig: null,
  ease: 'easeOutQuart',
  leaveReverse: false,
  animatingClassName: ['queue-anim-entering', 'queue-anim-leaving']
};

exports["default"] = QueueAnim;
module.exports = exports['default'];