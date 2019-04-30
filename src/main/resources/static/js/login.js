jQuery('#loginBtn').click(function () {
	var username = jQuery('#username').val();
	var password = jQuery('#password').val();
	if(username=='' || password=='') {
		alert('用户名和密码必填！');		
		return false;
	}
	jQuery.ajax({
        url: "./signin-process.htm",
        type: 'POST',
        data: {
        	username : username,
        	password : password
        },
        success: function (result) {
        	console.log('success!'+JSON.stringify(result));
        	if (result.success) {
        		//跳转index
        		if (result.data && result.data != '/' && result.data != '') {//targetUrl
        			window.location.href = result.data;        		
        		} else {
        			window.location.href = './index.htm';
        		}
        	} else {
        		jQuery('#errMsg').html(result.message);
        		return false;
        	}
        },
        error: function (msg) {
        	console.log('error!'+msg);
        }
    });
	return false;
});
