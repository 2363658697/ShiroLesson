<!DOCTYPE html>
<html style="height:100%">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<head>
<title>layout.html</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript" src="jquery.easyui.min.js"></script>
<style type="text/css">
body {
	font-size: 14px;
}
</style>

<script type="text/javascript">
	function tabClick(url,myTab){
	
		//获取选项卡
		var ifExist=$("#tabs").tabs("exists",myTab);
		//判断选项卡是否存在：不存在就添加
		if(!ifExist){
			$("#tabs").tabs("add",{    
			    title:myTab,    
			    content:'<iframe width="100%" height="100%" scrolling="no" frameborder="0" src="'+url+'"></iframe>',    
			    closable:true, 
			    select:myTab     
			}); 
		}
		//选择到当前的选项卡
		$("#tabs").tabs("select",myTab);
	}


</script>


</head>
<body style="margin: 0px;padding: 1px;height:100%">
	<div class="easyui-layout" style="width:100%;height:100%">
		<div data-options="region:'north'" style="height:20%"></div>
		<div data-options="region:'west',split:true" title="导航菜单" style="width:10%">
		
			<div class="easyui-accordion" style="width:100%;height:300px;">
				<div title="权限管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:5px;">
				
					<c:forEach var="menu" items="${requestScope.menuList }">
						<div>
							<a href="javascript:tabClick('${pageContext.request.contextPath}${menu.menuUrl }','${menu.menuName }')" style="text-decoration: none">
								<img alt="" src="themes/icons/man.png">${menu.menuName }
							</a>
						</div>
					</c:forEach>

				</div>
				<div title="帮助" data-options="iconCls:'icon-help'" style="padding:10px;"></div>
				<div title="查找" data-options="iconCls:'icon-search'" style="padding:10px;"></div>
			</div>		
		</div>
		<div data-options="region:'center',title:'',iconCls:'icon-ok'" >
			<div class="easyui-tabs" style="width:100%;height:100%" id="tabs">
				<div title="欢迎使用" style="padding:10px"></div>
		    </div>
		</div>
	</div>
</body>
</html>
