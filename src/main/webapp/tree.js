//性别转换
function rep(value, row, index) {
	if (value == 0) {
		return '男'
	} else {
		return '女'
	}
}
//部门名称转换
function dnameFun(value, row, index) {
	if (value == 5) {
		return '服装研发部'
	} else if(value==6){
		return 'it研发部'
	}
}

/*  根据员工姓名查询	  */
function submitQuery() {

	var ename = $("#tb").textbox("getValue");

	$("#dg").datagrid("load", {
		ename : ename
	});
};

/* 修改员工信息 */
function submitUpdate() {
	var emp = $("#dg").datagrid("getSelected");
	$("#ff").form("submit",{
		url: "emp/"+emp.empno,
		success : function(msg) {	
			msg=JSON.parse(msg);				
			if (msg.code == 0) {
				$.messager.alert("提示","修改成功");
				$("#w").window("close");
				submitQuery();    
			}else{
				var message=msg.message;
				$.messager.alert("提示",message); 							
			}
		}		
	})
};		

/* 添加员工 */
function submitSave() {
	$("#f").form("submit",{
		url: "emp",
		success : function(msg) {	
			msg=JSON.parse(msg);				
			if (msg.code == 0) {
				$.messager.alert("提示","添加成功");
				$("#ww").window("close");

				$("#dg").datagrid("load", {
					ename : null
				});   
			}else{
				var message=msg.message;
				$.messager.alert("提示",message); 							
			}
		}		
	})
};	


/* 重置修改表单信息 */
function clearForm(){
	$("#ff").form("clear");
};

/* 重置新增表单信息 */
function clear1Form(){
	$("#f").form("clear");
};

$(function() {
	$("#dg").datagrid({
		toolbar : [ {
			iconCls : "icon-insert",
			text : "增加",
			onClick : function() {			
				$("#ww").window("open");
			}
			
		}, {
			iconCls : "icon-edit",
			text : "修改",
			onClick : function() {
				var emp = $("#dg").datagrid("getSelected");

				if (emp == null) {
					$.messager.alert("请选择一行数据");
					return;
				}

				$("#w").window("open");
				$("#ff").form("load",emp);					
			}
			
		}, {
			iconCls : "icon-remove",
			text : "删除",
			onClick : function() {
				//获取单行
				var emp = $("#dg").datagrid("getSelected");

				if (emp == null) {
					$.messager.alert("请选择一行或多行数据");
					return;
				}
				//获取多行
				var emps = $("#dg").datagrid("getSelections");
				
				var empno="";
				for(var i=0;i<emps.length;i++){
					empno+=emps[i].empno+",";
				};
				
				var empno=empno.substring(0,empno.length-1);
				
				$.ajax({
					type : "POST",
					url : "emp/" + empno,
					data : "_method=delete",
					dataType : "json",
					success : function(msg) {
						if (msg.code == 0) {
							$.messager.alert("提示","删除成功");
							submitQuery();    
						}else{
							var message=msg.message;
							$.messager.alert("提示",message); 							
						}
					}
				});
			}
		}]
	})
});
