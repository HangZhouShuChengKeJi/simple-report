//显示默认提示信息
function showTips(eid, defVal){
	if(defVal == undefined || defVal == null || defVal == ""){
		return;
	}
	var obj = jQuery('#'+eid);
	if(obj.val()==defVal){
	    obj.css('color','#999');
	}else if(obj.val()==""){
		obj.val(defVal);
		obj.css('color','#999');
	}

	obj.bind("focus",function(){
	    if($(this).val()==defVal){
		    $(this).val('');
			$(this).css('color', '#000');
		}
	});
	
	obj.bind("blur",function(){
	    if($(this).val()==""){
		    $(this).val(defVal);
			$(this).css('color', '#999');
		}
	});
}
//添加数组中的控件为空校验
function validateEmptyArr(objIdArr){
	if(objIdArr == undefined || jQuery.isArray(objIdArr) == false){
		return false;
	}
	for(var i=0; i<objIdArr.length; i++){
		validateEmpty(objIdArr[i]);
	}
}

//添加控件的为空校验(含默认值)(提示信息ID为：objId+"Tips")
function validateEmptyWithDefault(objId, defVal, msg){
	if(objId == null || objId == undefined){
		return false;
	}
	showTips(objId, defVal);
	var obj = $("#"+objId);
	var tips = $("#"+objId+"Tips");
	//如果是下拉框，绑定change事件
	if(obj.is("select")){
    	obj.bind("change", function(){
    		if(obj.is(":hidden")){
    			tips.hide();
    			return false;
    		}
    		if(obj.val() == "" || (defVal != undefined && defVal != null && defVal == obj.val())){
    			if(msg != undefined && msg != null && msg != ""){
    				tips.html(msg);
    			}
    			tips.show();
    		} else {
    			tips.hide();
    		}
    	});
	}
	obj.bind("blur", function(){
		if(obj.is(":hidden")){
			tips.hide();
			return false;
		}
		if(obj.is("input") && obj.is(":text")){
			obj.val(jQuery.trim(obj.val()));
		}
		if(obj.val() == "" || (defVal != undefined && defVal != null && defVal == obj.val())){
			if(msg != undefined && msg != null && msg != ""){
				tips.html(msg);
			}
			tips.show();
		} else {
			tips.hide();
		}
	});
	//obj.focus(function(){
	//	tips.hide();
	//});
}

//添加控件的为空校验
function validateEmpty(objId, msg){
	validateEmptyWithDefault(objId, null, msg);
}

//添加数组中的控件必须输入数字校验
function mustNumberArr(objIdArr){
	if(objIdArr == undefined || jQuery.isArray(objIdArr) == false){
		return false;
	}
	for(var i=0; i<objIdArr.length; i++){
		mustNumber(objIdArr[i]);
	}
}

// 控制空间只能输入数字
function numberInput(objId){
	var obj = $("#"+objId);
	obj.keyup(function(){  //keyup事件处理 
        $(this).val($(this).val().replace(/\D/g,''));  
    }).bind("paste",function(){  //CTR+V事件处理 
        $(this).val($(this).val().replace(/\D/g,''));  
    }).css("ime-mode", "disabled");  //CSS设置输入法不可用
}

//控制空间只能输入数字
function numberInputObj(obj){
	obj.keyup(function(){  //keyup事件处理 
        $(this).val($(this).val().replace(/\D/g,''));  
    }).bind("paste",function(){  //CTR+V事件处理 
        $(this).val($(this).val().replace(/\D/g,''));  
    }).css("ime-mode", "disabled");  //CSS设置输入法不可用
}

//控件必须输入数字校验(提示信息ID为：objId+"NumTips")
function mustNumber(objId){
	if(isEmptyObject(objId)){
		return false;
	}
	var pattern = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	var obj = $("#"+objId);
	var tips = $("#"+objId+"NumTips");
	obj.blur(function(){
		if(obj.val() != "" && !pattern.test(obj.val())){
			tips.show();
		} else {
			tips.hide();
		}
	});
	//obj.focus(function(){
	//	tips.hide();
	//});
}

//校验手机号码
function validateMobile(objId){
	if(isEmptyObject(objId)){
		return false;
	}
	var obj = $("#"+objId);
	var tips = $("#"+objId+"MobileTips");
	obj.blur(function(){
		if(obj.val() != "" && !isMobile(obj.val())){
			tips.show();
		} else {
			tips.hide();
		}
	});
}

//校验邮箱
function validateEmail(objId){
	if(isEmptyObject(objId)){
		return false;
	}   
	//验证邮箱的正则表达式
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
	var obj = $("#"+objId);
	var tips = $("#"+objId+"EmailTips");
	obj.blur(function(){
		if(obj.val() != "" && !reg.test(obj.val())){
			tips.show();
		} else {
			tips.hide();
		}
	});
}

//判断是否手机号码
function isMobile(mobile){
	var pattern = /^(13[0-9]|147|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
	if(pattern.test(mobile)){
		return true;
	}
	return false;
}

function checkMobile(phone)   
{
    //验证电话号码手机号码，包含至今所有号段   
    var ab=/^(13[0-9]|147|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
    if(ab.test(phone) == false)
    {
        return false;
    }
    return true;
}

//校验邮箱
function checkEmail(email){
	if(isEmptyObject(email)){
		return false;
	}   
	//验证邮箱的正则表达式
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
	if(reg.test(email)){
		return true;
	} else {
		return false;
	}
}
//校验固话
function checkTel(tel){
	if(isEmptyObject(tel)){
		return false;
	}   
	//验证固话正则表达式
	var reg = /^[0-9]*$/;

	if(reg.test(tel)){
		return true;
	} else {
		return false;
	}
}

//校验网址
function checkUrl(url){
	if(isEmptyObject(url)){
		return false;
	}   
	//验证网址正则表达式
    var reg=/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;

	if(reg.test(url)){
		return true;
	} else {
		return false;
	}
}


function isEmptyObject(obj){
	return (obj == undefined || obj == null || obj == "");
}

//校验日期格式
function validateDateYMD(objId){
	
}

//判断是否为中文
function isChinese(temp) 
{ 
	var re = /[^\u4e00-\u9fa5]/; 
	if(re.test(temp)) return false; 
	return true; 
}

// 判断密码是否输入正确
function checkPwd(s){
	var patrn=/^([a-zA-Z]|[0-9]|_){6,20}$/; 
	if (!patrn.exec(s)){
		return false ;
	}
	return true;
}

String.prototype.trim= function(){  
    // 用正则表达式将前后空格  
    // 用空字符串替代。  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
};

// 获得URL参数
function getUrlParam(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}

// 发送手机验证码
function bindSmsButton(buttonId, mobileId, smsType, defaultText, waitText){
	jQuery('#'+buttonId).click(function(){
		if(defaultText == undefined || defaultText == null || defaultText == ''){
			defaultText = "发送验证码";
		}
		if(waitText == undefined || waitText == null || waitText == ''){
			waitText = "秒后再次获取验证码";
		}
		var mobile=$('#'+mobileId).val();
		if(mobile == undefined || !checkMobile(mobile)){
			alert('请填写正确的手机号');
			return false;
		}
		
		jQuery.post('/common/sendSms.htm',{
			'mobile':mobile,
			'smsType': smsType
		},function(data){
			if(data=='fail'){
				alert('短息发送失败，请稍后再试！');
			}else{
				var count = 60;
				countdown = window.setInterval(function(){
					if(count <= 0){
						$("#sendSmsBtn").val(defaultText);
						$("#sendSmsBtn").prop("disabled",false);
						//$("#sendSmsBtn").addClass("btn-info");
						window.clearInterval(countdown);
					}else{
						$("#sendSmsBtn").val(count+waitText);	
						$("#sendSmsBtn").prop("disabled",true);
						//$("#sendSmsBtn").removeClass("btn-info");
						count--;
					}
				  }, 1000);
			}
		});  	
	});
}

String.prototype.startWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length)
	   return false;
	  if(this.substr(0,s.length)==s)
	     return true;
	  else
	     return false;
	  return true;
	}