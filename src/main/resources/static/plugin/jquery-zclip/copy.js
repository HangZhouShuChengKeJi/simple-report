function doCopy(data, obj) {
        	console.log(data);
        	console.log(obj);
        	$(obj).zclip({
				path:"ZeroClipboard.swf", 
				copy:function(){return data;},
				afterCopy:function(){
			 		alert('数据已经复制到粘贴板！')
				}
			});
        }