#  demo简单介绍

##  1 跨域请求后台的时候，servlet不能通过直接requset.getParameter('filename')获取到file文件的值

> 对于文件上传在跨域请求的时候有两种方式：
-一种是把文件加密成base64,通过前台发送json字符串来实现。
  --缺点很明显：base64文件太大，不利于传值
-另一种：通过js的FormData对象，这样做就要后台引入新的jar包进行。
 -- 1. 新的jar包  （like commons-fileupload-1.3.3.jar） (like commons-io-2.0.1-URBA2209RC1.jar)
 -- 2. js的(like FormData对象)提交表单，的js简单写法
```
    $("#myImage").on("change",function(){
        		//alert("状态改变！！！");
        		var formData = new FormData($("#myImage")[0]);
        		console.log($("#myImage")[0].files);
				formData.append("file", $("#myImage")[0].files[0]);
				
				console.log(formData.get("file"));
	            $.ajax({
	                url : 'http://localhost:8080/demo/upload/file',
	                type:"POST",
	                data:formData,
	                dateType:'json',
	                processData:false,
	                contentType:false,
	                success:function(data){
	                	var json=JSON.parse(data);
	                    alert(json.message);
	                }
	            });
        	});
```
   3. FormData 对象的使用：
        1.用一些键值对来模拟一系列表单控件：即把form中所有表单元素的name与value组装成一个queryString
        2. 异步上传二进制文件。       
```
     let formData = new FormData()
     formData.append('user', 'zhang')
     获取 formData.get('user')  //zhang
     删除 formData.delete('user')
```
   3  注意2：参数 
   
   new FormData的参数是一个DOM对象，而非jQuery对象
   
```
     var formData = new FormData($("#file")[0]);
```
## 2 demo的介绍

  IDEA打开项目之后，运行项目，端口为8080端口
  
  


