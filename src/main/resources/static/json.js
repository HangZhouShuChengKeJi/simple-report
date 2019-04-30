// var jsrsasign = require("./js/jsrsasign-all-min");



var requestData = {
    "method": "login.login",
    "token": "",
    "version": "1.0.0",
    "sign": "",
    "body": {
        "accountNo": "18839000327",
        "password": "123456",
        "aesKey": "123456"
    }
};

/**
 * 对 obj 转换，按照属性名的字典序升序返回转换后的对象
 * 例如： {c: 1, b: 2, a: 3} ==> {a: 3, b: 2, c: 1}
 *
 * @param obj
 * @returns {*}
 */
var sortFileds = function(obj, writeNullToEmptyString){
    if(typeof obj === 'undefined'){
        if(writeNullToEmptyString){
            return "";
        } else {
            return null;
        }
    }
    if(typeof obj === 'function'){
        if(writeNullToEmptyString){
            return "";
        } else {
            return null;
        }
    }
    if(typeof obj === 'object'){
        var fileds = Object.keys(obj);
        var filedsArr = fileds.sort();
        var newObj = {};
        for(var k in filedsArr){
            var f = filedsArr[k];
            var v = obj[f];
            if(v instanceof Object){
                newObj[f] = sortFileds(v);
            } else {
                newObj[f] = v;
            }
        }
        return newObj;
    }
    return obj;
};

var requestDataStr = JSON.stringify(requestData);
console.log("requestData = " + requestDataStr);

requestDataStr = JSON.stringify(sortFileds(requestData, true));
console.log("requestData = " + requestDataStr);

// var rsaPriKey = "-----BEGIN RSA PRIVATE KEY-----\n"
//                 + "MIICWwIBAAKBgQDRhGF7X4A0ZVlEg594WmODVVUIiiPQs04aLmvfg8SborHss5gQ\n"
//                 + "Xu0aIdUT6nb5rTh5hD2yfpF2WIW6M8z0WxRhwicgXwi80H1aLPf6lEPPLvN29EhQ\n"
//                 + "NjBpkFkAJUbS8uuhJEeKw0cE49g80eBBF4BCqSL6PFQbP9/rByxdxEoAIQIDAQAB\n"
//                 + "AoGAA9/q3Zk6ib2GFRpKDLO/O2KMnAfR+b4XJ6zMGeoZ7Lbpi3MW0Nawk9ckVaX0\n"
//                 + "ZVGqxbSIX5Cvp/yjHHpww+QbUFrw/gCjLiiYjM9E8C3uAF5AKJ0r4GBPl4u8K4bp\n"
//                 + "bXeSxSB60/wPQFiQAJVcA5xhZVzqNuF3EjuKdHsw+dk+dPECQQDubX/lVGFgD/xY\n"
//                 + "uchz56Yc7VHX+58BUkNSewSzwJRbcueqknXRWwj97SXqpnYfKqZq78dnEF10SWsr\n"
//                 + "/NMKi+7XAkEA4PVqDv/OZAbWr4syXZNv/Mpl4r5suzYMMUD9U8B2JIRnrhmGZPzL\n"
//                 + "x23N9J4hEJ+Xh8tSKVc80jOkrvGlSv+BxwJAaTOtjA3YTV+gU7Hdza53sCnSw/8F\n"
//                 + "YLrgc6NOJtYhX9xqdevbyn1lkU0zPr8mPYg/F84m6MXixm2iuSz8HZoyzwJARi2p\n"
//                 + "aYZ5/5B2lwroqnKdZBJMGKFpUDn7Mb5hiSgocxnvMkv6NjT66Xsi3iYakJII9q8C\n"
//                 + "Ma1qZvT/cigmdbAh7wJAQNXyoizuGEltiSaBXx4H29EdXNYWDJ9SS5f070BRbAIl\n"
//                 + "dqRh3rcNvpY6BKJqFapda1DjdcncZECMizT/GMrc1w==\n"
//                 + "-----END RSA PRIVATE KEY-----";
//
// var sig = new KJUR.crypto.Signature({"alg": "SHA256withRSA"});
// sig.init(rsaPriKey);
// sig.updateString(requestDataStr);
// var signValue = sig.sign();
//
// console.log("signValue = " + signValue);

