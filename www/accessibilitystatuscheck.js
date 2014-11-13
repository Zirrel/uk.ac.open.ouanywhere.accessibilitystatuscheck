var argscheck = require('cordova/argscheck'),
	    channel = require('cordova/channel'),
	    utils = require('cordova/utils'),
	    exec = require('cordova/exec'),
	    cordova = require('cordova');
	
AccessibilityStatusCheck.prototype.registerAccessibilityStatusUpdate = function(successCallback, errorCallback) {
	exec(successCallback, errorCallback, "AccessibilityStatusCheck", "registerAccessibilityStatusUpdate", []);
};
	
module.exports = new AccessibilityStatusCheck();
