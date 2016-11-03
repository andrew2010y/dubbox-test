<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>   
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>     
<%-- 这里并没有指定action=""，说明表单是提交给自己的，说白了就是"哪来的回哪儿去 --%>     
   <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="../static/ajaxfileupload/jquery.js"></script>
	<script type="text/javascript" src="../static/ajaxfileupload/ajaxfileupload.js"></script>
  </head>  
    
<form method="POST" modelAttribute="user">     
    <input type="file" name="file" id="file_input" value="" />
    <input type="button" value="上传" onclick="uploadFile();"/>
    <script type="text/javascript">
    function uploadFile(){
    	$.ajaxFileUpload
		(
			{
				url:'<%=path%>/test/upload',
				secureuri:false,
				fileElementId:'file_input',
				dataType: 'text/html',
				success: function (data, status)
				{
					alert(data);
					//document.getElementById('photo').value = data;
				},
				error: function (data, status, e)
				{
					alert(e);
				}
			}
		)
		
    }
    </script>
</form>