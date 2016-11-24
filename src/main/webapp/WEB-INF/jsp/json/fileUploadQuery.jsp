<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/skins/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/skins/uploadify/jquery.uploadify.min.js"></script>
<link   rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skins/uploadify/uploadify.css">

<script type="text/javascript">
$(document).ready(function() {
    $('#file_upload').uploadify( {
        'debug':'true',
        'swf' : '${pageContext.request.contextPath}/skins/uploadify/uploadify.swf',//上传按钮的图片，默认是这个flash文件
        'uploader' : '${pageContext.request.contextPath}/json/fileUpload.action',//上传所处理的服务器
        'cancelImg' : '${pageContext.request.contextPath}/skins/uploadfiy/uploadify-cancel.png',//取消图片
        'method':'post',
        'folder' : '/UploadFile',//上传后，所保存文件的路径
        'queueID' : 'fileQueue',//上传显示进度条的那个div
        'buttonText' : '请选择文件',
        //'onUploadComplete': function(file){alert('The file'+file.name+'finished processing!')},//每个文件上传成功后的函数
        progressData : 'percentage',
        'auto' : false,
        'multi' : true,
        //'onSelect':function(file){
        //alert("文件"+file.name+"被选择了！");
        //}
        //'onQueueComplete' : function(queueData) {
        //    alert(queueData.filesQueued + 'files were successfully!')
        //},//当队列中的所有文件上传成功后，弹出共有多少个文件上传成功
        'onDisable' : function() {
            alert('uploadify is disable');
        },//在调用disable方法时候触发
        //'onCancel':function(){alert('你取消了文件上传')}
        //'onUploadStart' : function(file) {//在调用上传前触发
        //alert('The file ' + file.name + ' is being uploaded.')}
        'onError' : function(errorType,errObj) {
            alert('The error was: ' + errObj.info)
        }

    });
});

</script>

  

</head>
<body>
	<div id="fileQueue"></div>
    <input id="file_upload" name="file_upload" type="file" multiple="true">
       <p>
           <!-- 加上“*”表示当第一个文件上传成功后，立即上传以后队列中的文件，否则需要自己手动 -->
           <a href="javascript:$('#file_upload').uploadify('upload','*')">上传</a>|
           <a
               href="javascript:$('#file_upload').uploadify('cancel',$('.uploadifive-queue-item').first().data('file'))">取消上传</a>
           <a href="javascript:$('#file_upload').uploadify('cancel','*')">清空所有的上传文件</a>
           <a href="javascript:$('#file_upload').uploadify('stop','*')">暂停</a>
           <!-- 如果填入true则表示禁用上传按钮 -->
           <a href="javascript:$('#file_upload').uploadify('disable','true')">禁用</a>
           <a href="javascript:$('#file_upload').uploadify('debug')">调试</a>
       </p>

</div>
</body>
</html>