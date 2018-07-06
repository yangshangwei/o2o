/**
 *  因为商品的添加和编辑复用同一个页面，所以需要根据url中的商品Id来判断
 */
$(function(){
	//通过url是否含有productId来判断是添加商品还是编辑
	var productId = getQueryString('productId');
	// 标示符  productId非空则为true即编辑，否则为添加商品
	var isEdit = productId ? true : false ;
	
	// 商品添加URL 用于提交 
	var addProductURL = '/o2o/shopadmin/addproduct';
	// 商品编辑URL 用于提交 
	var editProductURL = '/o2o/shopadmin/modifyproduct?productId=' + productId;
	// 获取商品初始化信息的URL  根据页面原型只需要获取productCategory即可，后台调用之前写好的路由方法即可
	var categoryInfoURL = '/o2o/shopadmin/getproductcategorybyshopId';
	// 商品编辑URL 用于从后台加载该product的基本信息，页面展示用
	var productInitURL = '/o2o/shopadmin/getproductbyid?productId=' + productId;
	
	
	
	// 通过标示符，确定调用的方法
	if(isEdit){
		// 为true，则根据productId调用获取product信息的方法  
		showEditProductPage(productId);
	}else{
		// 为false，则初始化新增product页面
		showAddProductPage();
	}
	
	/**
	 * 始化新增product页面
	 * 
	 * 根据页面原型和数据模型，需要加载该shop对应的productCategory信息(shop信息从服务端session中获取)
	 */
	function showAddProductPage(){
		$.getJSON(categoryInfoURL,
				function(data){
					if(data.success){
						// 设置product_category
						var productCategoryList = data.data;
						var productCategoryTempHtml = '';
						productCategoryList.map(function(item, index) {
//							productCategoryTempHtml += '<option data-id="'
//									+ item.productCategoryId + '">' + item.productCategoryName
//									+ '</option>';
							productCategoryTempHtml += '<option data-value="'
								+ item.productCategoryId + '">'
								+ item.productCategoryName + '</option>';
						});
						$('#product-category').html(productCategoryTempHtml);
					}else{
						$.toast(data.errMsg)
					}
		});
	};
	
	/**
	 * 点击控件的最后一个且图片数量小于6个的时候，生成一个选择框
	 */
	$('.detail-img-div').on('change', '.detail-img:last-child', function() {
		if ($('.detail-img').length < 6) {
			$('#detail-img').append('<input type="file" class="detail-img">');
		}
	});
	
	
	/**
	 * 编辑页面调用的function
	 */
	function showEditProductPage(productId){
		$.getJSON(
				productInitURL,
				function(data) {
					if (data.success) {
						var product = data.product;
						$('#product-name').val(product.productName);
						$('#product-desc').val(product.productDesc);
						$('#priority').val(product.priority);
						$('#normal-price').val(product.normalPrice);
						$('#promotion-price').val(
								product.promotionPrice);

						var optionHtml = '';
						var optionArr = data.productCategoryList;
						var optionSelected = product.productCategory.productCategoryId;
						optionArr.map(function(item, index) {
									var isSelect = optionSelected === item.productCategoryId ? 'selected'
											: '';
									optionHtml += '<option data-value="'
											+ item.productCategoryId
											+ '"'
											+ isSelect
											+ '>'
											+ item.productCategoryName
											+ '</option>';
								});
						$('#product-category').html(optionHtml);
					}
				});
	};
	
	/**
	 * 提交按钮的响应时间,分别对商品添加和商品编辑做不同的相应
	 */
	$('#submit').click(
			function(){
				// 创建商品Json对象，并从表单对象中获取对应的属性值
				var product = {};
				
				// 如果是编辑操作,需要传入productId
				if(isEdit){
					product.productId = productId;
				}
				
				product.productName = $('#product-name').val();
				product.productDesc = $('#product-desc').val();
				
				// 获取商品的特定目录值
				product.productCategory = {
						productCategoryId : $('#product-category').find('option').not(
								function() {
									return !this.selected;
								}).data('value')
					};
				
				product.priority = $('#priority').val();
				product.normalPrice = $('#normal-price').val();
				product.promotionPrice = $('#promotion-price').val();
				
				// 生成表单对象用于接收参数并传递给后台
				var formData = new FormData();
				
				// 缩略图 （只有一张），获取缩略图的文件流
				var thumbnail = $('#small-img')[0].files[0];
				formData.append('thumbnail',thumbnail);
				
				// 图片详情
				$('.detail-img').map(
						function(index, item) {
							// 判断该控件是否已经选择了文件	
							if ($('.detail-img')[index].files.length > 0) {
								// 将第i个文件流赋值给key为productImgi的表单键值对里
								formData.append('productImg' + index,
										$('.detail-img')[index].files[0]);
							}
						});
				// 将product 转换为json ,添加到forData
				formData.append('productStr', JSON.stringify(product));
				
				// 获取表单中的验证码
				var verifyCodeActual = $('#j_captcha').val();
				if (!verifyCodeActual) {
					$.toast('请输入验证码！');
					return;
				}
				formData.append("verifyCodeActual", verifyCodeActual);
				
				
				// 使用ajax异步提交
				$.ajax({
					url: isEdit?editProductURL:addProductURL,
					type: 'POST' ,
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success: function(data){
						if (data.success) {
							$.toast('提交成功！');
							$('#captcha_img').click();
						} else {
							$.toast('提交失败！');
							$('#captcha_img').click();
						}
					}
				});
			});
});