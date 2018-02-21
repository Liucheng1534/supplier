var pageSize = 10; //每页条数
var currentPage = 1;
webix.ready(function() {
	/**
	 * 调用数据刷新方法，把数据添加到创建的表格中
	 */
	var testdata;
	refreshTable();
	/**
	 * 创建一个datatable数据表格
	 */
	var grida = webix.ui({
		container : "testA",
		view : "datatable",
		id : "table",
		
		autowidth:true,		//字适应宽度
		yCount:10,		//10行的高度
		scrollY:false,	//滚动条
		navigation:"true",
		columns : [ {
			id : "supplierid",
			name : 'supplierid',
			header : "供应商编号",
			width : 150,
			sort : "int",
			css:"left_area"
		}, {
			id : "suppliername",
			name : 'suppliername',
			header : "供应商名称",
			width : 150,
			sort : "string",
			css:"left_area"
		}, {
			id : "supplylegaler",
			name : 'supplylegaler',
			header : "法定代表人",
			width : 110,
			sort : "string",
			css:"left_area"
		}, {
			id : "businesser",
			name : 'businesser',
			header : "业务联系人",
			width : 110,
			sort : "string",
			css:"left_area"
		}, {
			id : "phonenumber",
			name : 'phonenumber',
			header : "联系方式",
			width : 110,
			sort : "string",
			css:"left_area"
		}, {
			id : "licenseid",
			name : 'licenseid',
			header : "营业执照编号",
			width : 170,
			sort : "string",
			css:"left_area"
		}, {
			id : "revenueid",
			name : 'revenueid',
			header : "税务登记证编号",
			width : 170,
			sort : "string",
			css:"left_area"
		}, {
			id:"caozuo",
			header:"用户操作",
			width : 115,
			css : 'right_area',
			template : `
				<button type = 'button' onclick = "setWindow();setData('#supplierid#','#suppliername#','#supplylegaler#','#businesser#','#phonenumber#','#licenseid#','#revenueid#')" class = 'webix_pager_item' style = 'width: 40px;' >
					修改
				</button>
				<button type = 'button' id = 'del' class = 'webix_pager_item del_' onclick = "judgeWindow('#supplierid#');"  style = 'width: 40px;' >
					删除
				</button>
			`
		} ],
		yCount : pageSize,// 每页行数
		scrollY : false,// 滚动条
		pager : {
			container : "paging_here",
			id:"pager",
			view:"pager",
			size : pageSize, // 每页行数
			group : 5, // 控制显示页签数量
			template: '{common.first()} {common.prev()} {common.pages()} {common.next()} {common.last()}  ',
			on : {
				"onItemClick" : function(start) {
					if("first"==start){
						currentPage=1;
						refreshTable();
					}else if("last"==start){
						currentPage=totalPage;
						refreshTable();
					}else if("prev"==start){
						currentPage=currentPage-1;
						if (0 >= currentPage) {
							currentPage = 1;
						}
						refreshTable();
					}else if("next"==start){
						currentPage=currentPage+1;
						if (currentPage>totalPage) {
							currentPage = totalPage;
						}
						refreshTable();
					}else{
					currentPage = parseInt(start)+1;
					refreshTable();
					}
				}
			},
		},
		
	data:testdata
	});

	/**
	 * 创建一个label，把当前显示的数据显示到页面上
	 */
	webix.ui({
		container : "content",
		id : "count",
		view : "label",
		minWidth:200
	});
	
	/**
	 * 创建一个注册按钮，把实现弹窗功能
	 */
	webix.ui({
		container : "zhuce",
		view : "button",
		inputWidth:150,
		value : "供应商添加",
		on:{
			onItemClick:function(){
				createWindow();
			}
		}
	});
	
	/**
	 * 创建一个注册按钮，把实现弹窗功能
	 */
	webix.ui({
		container : "search",
		view:"search",
		align:"center",
		placeholder:"Search..",
		id:"search",
		width: 150,
		on:{
			onItemClick:function(){
				var content = $$("search").getValue();
				webix.ajax().get("../supplier/getAll",{
					concent : content,
					currpage : currentPage,
					pageSize : pageSize
				}, function(text) {
					var result = JSON.parse(text);
					testdata = result;
					judgesize = result.size;	//判断是否是最后一条数据，如11条
					var table = $$('table');
					table.clearAll();
					result.data.forEach(function(item) {
						table.add(item);
					});
					var size = (result.totalPage)*10;
					totalPage = result.totalPage;
					table.config.pager.page = currentPage-1;
					table.config.pager.count = size;
					table.getPager().refresh();
					$$("count").setValue("当前第" + currentPage + "页,总共" + result.size + "条数据.");
				});
			}
		}
	});
	
});

/**
 * 把窗体的创建封装到一个方法中
 */
var create;
function createWindow() {
	
	if(0==create){
		return;
	}
	create = 0;
	webix.ui({
		view : "window",
		id : 'win1',
		width : 800,
		position : "center",
		move : true,
		head : {
			view : 'toolbar',
			margin : -4,
			cols : [ {
				view : 'label',
				label : '内部供应商注册'
			}, {
				view : 'icon',
				icon : 'times-circle',
				click : "$$('win1').close();create=1"
			} ]
		},
		body : {
			view : "form",
			id : "form1",
			width : 400,
			elements : [{
				view : "text",
				label : "供应商名称：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'suppliername'
			}, {
				view : "text",
				label : "法定代表人：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'supplylegaler'
			}, {
				view : "text",
				label : "业务联系人：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'businesser'
			}, {
				view : "text",
				label : "联系方：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'phonenumber'
			}, {
				view : "text",
				label : "营业执照：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'licenseid',
				value : getDateAndRandom()
			}, {
				view : "text",
				label : "税务登记号：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'revenueid',
				value : getDateAndRandom()
			}, {
				cols:[{
					view : "button",
					label : "注册",
					type : "form",
					on:{
						"onItemClick" : function() {
							webix.ajax().post("../supplier/insert", $$("form1").getValues(),{ 
								success:function(text){
									create = 1;
									$$('win1').close();
									if(text=="true"){
										alert("添加成功!");
									}else{
										alert("添加失败!");
										return;
									}
									refreshTable();
								},
								error:function(){
							    	alert("添加失败!");
							    	create = 1;
							    	$$('win1').close();
							    }
							});
						}
					}
				},{
					view : "button",
					label : "重置",
					click : "$$('form1').clear();"
				}]
			}]
		}
	}).show();
}

/**
 * 修改窗体的创建
 */
var set;
function setWindow() {
	if(0==set){
		return;
	}
	set = 0;
	webix.ui({
		view : "window",
		id : 'win2',
		width : 800,
		position : "center",
		move : true,
		head : {
			view : 'toolbar',
			margin : -4,
			cols : [ {
				view : 'label',
				label : '内部供应商注册'
			}, {
				view : 'icon',
				icon : 'times-circle',
				click : "$$('win2').close();set = 1;"
			} ]
		},
		body : {
			view : "form",
			id : "form2",
			width : 400,
			elements : [{
				view : "text",
				id:"t1",
				readonly : true,
				disable : "disable",
				label : "供应商编号：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'supplierid'
			},{
				view : "text",
				id:"t2",
				label : "供应商名称：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'suppliername'
			}, {
				view : "text",
				id:"t3",
				label : "法定代表人：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'supplylegaler'
			}, {
				view : "text",
				id:"t4",
				label : "业务联系人：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'businesser'
			}, {
				view : "text",
				id:"t5",
				label : "联系方：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'phonenumber'
			}, {
				view : "text",
				id:"t6",
				label : "营业执照：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'licenseid',
			}, {
				view : "text",
				id:"t7",
				label : "税务登记号：",
				labelAlign:"right",
				labelWidth : 120,
				name : 'revenueid',
			}, {
				cols:[{
					view : "button",
					label : "修改",
					type : "form",
					on:{
						"onItemClick" : function() {
							var setData = $$("form2").getValues();
							var supplierid = setData.supplierid;
							var suppliername = setData.suppliername;
							var supplylegaler = setData.supplylegaler;
							var businesser = setData.businesser;
							var phonenumber = setData.phonenumber;
							var licenseid = setData.licenseid;
							var revenueid = setData.revenueid;
							var params = "?supplierid="+supplierid+"&suppliername="+suppliername+"&supplylegaler="+supplylegaler+"&businesser="+businesser+"&phonenumber="+phonenumber+"&licenseid="+licenseid+"&revenueid="+revenueid;
							webix.ajax().put("../supplier/update"+params,{ 
								success:function(text){
									set = 1;
									$$('win2').close();
									if(text=="true"){
										alert("修改成功!");
									}else{
										alert("修改失败!");
										return;
									}
									refreshTable();
								},
								error:function(){
							    	alert("修改失败!");
							    	set = 1;
							    	$$('win2').close();
							    }
							});
						}
					}
				},{
					view : "button",
					label : "重置",
					click : "setData(supplierid1,suppliername2,supplylegaler2,businesser2,phonenumber2,licenseid2,revenueid2);"
				}]
			}]
		}
	}).show();
}

/**
 * 修改窗体创建
 */
var supplierid1;
var suppliername2;
var supplylegaler2;
var businesser2;
var phonenumber2;
var licenseid2;
var revenueid2;
function setData(supplierid,suppliername,supplylegaler,businesser,phonenumber,licenseid,revenueid){
	supplierid1=supplierid;
	suppliername2=suppliername;
	supplylegaler2=supplylegaler;
	businesser2=businesser;
	phonenumber2=phonenumber;
	licenseid2=licenseid;
	revenueid2=revenueid;
	$$("t1").setValue(supplierid);
	$$("t2").setValue(suppliername);
	$$("t3").setValue(supplylegaler);
	$$("t4").setValue(businesser);
	$$("t5").setValue(phonenumber);
	$$("t6").setValue(licenseid);
	$$("t7").setValue(revenueid);
}

/**
 * 把数据刷新封装到方法中
 */
var judgesize;
var totalPage;
function refreshTable() {
	webix.ajax().get("../supplier/getAll",{
		currpage : currentPage,
		pageSize : pageSize
	}, function(text) {
		var result = JSON.parse(text);
		testdata = result;
		judgesize = result.size;	//判断是否是最后一条数据，如11条
		var table = $$('table');
		table.clearAll();
		result.data.forEach(function(item) {
			table.add(item);
		});
		var size = (result.totalPage)*10;
		totalPage = result.totalPage;
		table.config.pager.page = currentPage-1;
		table.config.pager.count = size;
		table.getPager().refresh();
		$$("count").setValue("当前第" + currentPage + "页,总共" + result.size + "条数据.");
	});
}

/**
 * 获得年月日加随机数
 */
function getDateAndRandom() {
	var now = new Date();
	var temp = now.getFullYear();
	temp = temp * 100 + now.getMonth()+1;
	temp = temp * 100 + now.getDate();
	temp = temp * 100 + now.getHours();
	temp = temp * 100 + now.getMinutes();
	temp = temp * 100 + now.getSeconds();
	temp = temp * 10000 + Math.floor(Math.random()*10000);
	return temp;
}

/**
 * 删除数据方法
 * @param id
 * @returns
 */
function deleteData(id){
	var test = judgesize%10;
	if(1==test){
		currentPage=currentPage-1;
	}
	webix.ajax().del("../supplier/delete?supplierid="+id,{
	    error:function(){
	        alert("删除失败!");
	    },
	    success:function(text){
	    	if(text=="true"){
	    		refreshTable();
				alert("删除成功!");
				$$('win3').close();judge=1;
	    	}else{
	    		alert("删除失败!");
	    		$$('win3').close();judge=1;
	    	}
	    }
	});
}

/**
 * 是否删除方法
 * @param id
 * @returns
 */
var judge;
function judgeWindow(id) {
	
	if(0==judge){
		return;
	}
	judge = 0;
	webix.ui({
		view : "window",
		id : 'win3',
		width : 800,
		position : "center",
		move : true,
		head : {
			view : 'toolbar',
			margin : -4,
			cols : [ {
				view : 'label',
				label : '操作确认'
			}, {
				view : 'icon',
				icon : 'times-circle',
				click : "$$('win3').close();judge=1"
			} ]
		},
		body : {
			view : "form",
			id : "form3",
			width : 200,
			elements : [{
				view : "label",
				label : "是否确认删除此数据?",
				name : 'suppliername'
			}, {
				cols:[{
					view : "button",
					label : "取消",
					type : "form",
					click : "$$('win3').close();judge=1;"
				},{
					view : "button",
					label : "确认",
					click : "deleteData("+id+")"
				}]
			}]
		}
	}).show();
}