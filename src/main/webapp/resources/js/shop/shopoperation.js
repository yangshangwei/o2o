/**
 * 
 */
$(function() {
	// 通过url是否含有shopId来判断是注册还是编辑商铺
	var shopId  = getQueryString('shopId');
	// shopId非空返回true，空者返回false
	var isEdit = shopId ? true :false;
	
	// 获取基本信息的URL
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	// 注册店铺的URL
	var registerShopUrl = '/o2o/shopadmin/registshop';
	
	// 通过shopId获取商铺信息的URL
	var getShopInfoByShopId = '/o2o/shopadmin/getshopinfobyId?shopId=' + shopId;
	// 修改商铺的URL
	var modifyShopUrl = '/o2o/shopadmin/modifyshop';
	
	// 根据isEdit 来判断 是注册商品还是修改商铺
	if(!isEdit){
		// 调用getShopInitInfo，注册店铺用
		getShopInitInfo();
	}else{
		// 调用getShopInfoById，修改店铺用
		getShopInfoById(shopId);
	}
	
	
	/**
	 * 通过shopId获取shop信息
	 */
	function getShopInfoById(shopId){
		$.getJSON(getShopInfoByShopId,function(data){
			if(data.success){
				// 将后台返回的shop通过shop变量接收，方便赋值
				var shop =  data.shop;
				// 赋值   要和shop实体类中的属性名保持一致
				$('#shop-name').val(shop.shopName);
				$('#shop-name').attr('disabled','disabled');
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				
				// 商品目录进行赋值   商品目录仅仅加载对应的目录，且不可编辑
				var shopCategory = '<option data-id="'
					+shop.shopCategory.shopCategoryId + '" selected>'
					+shop.shopCategory.shopCategoryName +'</option>';
				$('#shop-category').html(shopCategory);
				// 设置为不可编辑
				$('#shop-category').attr('disabled','disabled');
				
				// 区域进行赋值  区域可以进行编辑，并且初始设置为后台对应的区域
				var tempShopAreaHtml = '';
				data.areaList.map(function(item, index) {
					tempShopAreaHtml += '<option data-id="' + item.areaId
							+ '">' + item.areaName + '</option>';
				});
				$('#shop-area').html(tempShopAreaHtml);
				//初始设置为后台对应的区域 
				$("#shop-area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");

			}else{
				$.toast(data.errMsg);
			}
		});
	};
	
	
	
	/**
	 * 从后台加载获取下拉菜单的值
	 */
	function getShopInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempShopCategoryHtml = '';
				var tempShopAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					tempShopCategoryHtml += '<option data-id="'
							+ item.shopCategoryId + '">' + item.shopCategoryName
							+ '</option>';
				});

				data.areaList.map(function(item, index) {
					tempShopAreaHtml += '<option data-id="' + item.areaId
							+ '">' + item.areaName + '</option>';
				});
				// 获取html中对应标签的id 赋值
				$('#shop-category').html(tempShopCategoryHtml);
				$('#shop-area').html(tempShopAreaHtml)

			}else{
				$.toast(data.errMsg);
			}
		});
	};
	
	
	
	
	
	
	/**
	 * submit按钮触发的操作
	 * 验证表单输入，省略。。。。
	 */
	$('#submit').click(function() {
		// 获取页面的值
		var shop = {};
		// 注意： 这个地方的变量名称要和Shop实体类中的属性保持一致，因为后台接收到shopStr后，会将Json转换为实体类，如果不一致会抛出异常
		// com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException

		// 如果是编辑，需要传入shopId
		if(isEdit){
               shop.shopId=shopId;
            }
		shop.shopName = $('#shop-name').val(); 
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();
		// 选择id,双重否定=肯定
		shop.shopCategory = {
			// 这里定义的变量要和ShopCategory.shopCategoryId保持一致，否则使用databind转换会抛出异常
			shopCategoryId:$('#shop-category').find('option').not(function(){
				return !this.selected;
				}).data('id')
		};
		shop.area = {
			// 这里定义的变量要和Area.areaId属性名称保持一致，否则使用databind转换会抛出异常
			areaId:$('#shop-area').find('option').not(function(){
				return !this.selected;
				}).data('id')
			};
		
		
		// 图片
		var shopImg = $('#shop-img')[0].files[0];
		
		// 验证码
		var verifyCodeActual =$('#j_kaptcha').val();
		if(!verifyCodeActual){
			$.toast('请输入验证码');
			return;
		}
		
		// 接收数据
		var formData = new FormData();
		// 和后端约定好，利用shopImg和 shopStr接收 shop图片信息和shop信息
		formData.append('shopImg',shopImg);
		// 转成JSON格式，后端收到后将JSON转为实体类
		formData.append('shopStr',JSON.stringify(shop));
		
		// 将数据封装到formData发送到后台
		formData.append('verifyCodeActual',verifyCodeActual);
		
		
		// 利用ajax提交
		$.ajax({
			// 动态判断 url
			url:isEdit ? modifyShopUrl:registerShopUrl,
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					$.toast('提示信息:'+data.errMsg);
				}else{
					$.toast('提示信息:' + data.errMsg);
				}
				// 点击提交后 不管成功失败都更换验证码，防止重复提交
				$('#kaptcha_img').click();
			}
		});
	
	});
});
	

