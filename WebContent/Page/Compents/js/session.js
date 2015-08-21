/** 
1. 设置cookie的值，把name变量的值设为value   
example $.cookie('name', 'value');
2.新建一个cookie 包括有效期 路径 域名等
example $.cookie('name', 'value', {expires: 7, path: '/', domain: 'jquery.com', secure: true});
3.新建cookie
example $.cookie('name', 'value');
4.删除一个cookie
example $.cookie('name', null);
5.取一个cookie(name)值给myvar
var account= $.cookie('name');
**/
jQuery.cookie = function (name, value, options) {
    if (typeof value != 'undefined') {
        options = options || {};
        if (value === null) {
            value = '';
            options = $.extend({}, options);
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString();
        }
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else {
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};

var urlIP1 = 'localhost:5143';
var urlIP = '127.0.0.1:8080';
var userInfo ;
function getUser(cookieName) {
    userInfo = eval($.cookie(cookieName))[0];
}

/*
function html_encode(str) {
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/</g, "cmrxleftcmrx");
    s = s.replace(/>/g, "cmrxrightcmrx");
    s = s.replace(/\//g, "cmrxlinecmrx");
    return s;
}

function html_decode(str) {
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/cmrxleftcmrx/g, "<");
    s = s.replace(/cmrxrightcmrx/g, ">");
    s = s.replace(/cmrxlinecmrx/g, "/");
    return s;
}

var getEncodeString = function (srcString) {
    var BASE32CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

    var i = 0;
    var index = 0;
    var digit = 0;
    var currByte;
    var nextByte;
    var retrunString = '';

    for (var i = 0; i < srcString.length; ) {
        //var          index    = 0;   
        currByte = (srcString.charCodeAt(i) >= 0) ? srcString.charCodeAt(i)
                                       : (srcString.charCodeAt(i) + 256);

        if (index > 3) {
            if ((i + 1) < srcString.length) {
                nextByte = (srcString.charCodeAt(i + 1) >= 0)
                                                 ? srcString.charCodeAt(i + 1)
                                                  : (srcString.charCodeAt(i + 1) + 256);
            } else {
                nextByte = 0;
            }

            digit = currByte & (0xFF >> index);
            index = (index + 5) % 8;
            digit <<= index;
            digit |= (nextByte >> (8 - index));
            i++;
        } else {
            digit = (currByte >> (8 - (index + 5))) & 0x1F;
            index = (index + 5) % 8;

            if (index == 0) {
                i++;
            }
        }

        retrunString = retrunString + BASE32CHAR.charAt(digit);
    }
    return retrunString.toLowerCase();
}
*/