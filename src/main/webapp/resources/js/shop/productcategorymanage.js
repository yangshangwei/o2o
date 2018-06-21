$(function () {
	// 后台从session中获取shop的信息，这里就不传shopId了
	//var shopId = getQueryString("shopId");
    //var productCategoryURL = '/o2o/shopadmin/getproductcategorybyshopId?shopId=' + shopId;
    
    var getProductCategoryURL = '/o2o/shopadmin/getproductcategorybyshopId';
    var addProductCategoryURL = '/o2o/shopadmin/addproductcategory';
    // 调用getProductCategoryList，加载数据
    getProductCategoryList();
    
    function getProductCategoryList() {
		$.getJSON(getProductCategoryURL,
					function(data) {
						if (data.success) {
							var dataList = data.data;
							$('.product-categroy-wrap').html('');
							var tempHtml = '';
							dataList
									.map(function(item, index) {
										tempHtml += ''
												+ '<div class="row row-product-category now">'
												+ '<div class="col-33 product-category-name">'
												+ item.productCategoryName
												+ '</div>'
												+ '<div class="col-33">'
												+ item.priority
												+ '</div>'
												+ '<div class="col-33"><a href="#" class="button delete" data-id="'
												+ item.productCategoryId
												+ '">删除</a></div>'
												+ '</div>';
									});
							$('.product-categroy-wrap').append(tempHtml);
						}
					});
	}
	
    
    
    //  新增按钮的点击事件
    $('#new').click(
    		function(){
    			// 新增数据 以 temp 为标识，便于和库表中的数据区分开来
    			var tempHtml = '<div class="row row-product-category temp">'
					+ '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
					+ '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
					+ '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
					+ '</div>';
    			$('.product-categroy-wrap').append(tempHtml);
    		});
    
    
	$('#submit').click(function() {
		// 通过temp 获取新增的行
		var tempArr = $('.temp');
		// 定义数据接收新增的数据
		var productCategoryList = [];
		tempArr.map(function(index, item) {
			var tempObj = {};
			tempObj.productCategoryName = $(item).find('.category').val();
			tempObj.priority = $(item).find('.priority').val();
			if (tempObj.productCategoryName && tempObj.priority) {
				productCategoryList.push(tempObj);
			}
		});
		$.ajax({
			url : addProductCategoryURL,
			type : 'POST',
			// 后端通过 @HttpRequestBody直接接受
			data : JSON.stringify(productCategoryList),
			contentType : 'application/json',
			success : function(data) {
				if (data.success) {
					$.toast('新增【' + data.effectNum + '】条成功！');
					// 重新加载数据
					getProductCategoryList();
				} else {
					$.toast('提交失败！');
				}
			}
		});
	});
    
});