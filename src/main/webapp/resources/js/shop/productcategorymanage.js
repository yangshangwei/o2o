$(function () {

    var shopId = getQueryString("shopId");
    var productCategoryURL = '/o2o/shopadmin/getproductcategorybyshopId?shopId=' + shopId;

    $.getJSON(productCategoryURL,function(data){
    	if (data.success) {
			var dataList = data.data;
			$('.product-categroy-wrap').html('');
			var tempHtml = '';
			dataList.map(function(item, index) {
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
								+ '">删除</a></div>' + '</div>';
					});
			$('.product-categroy-wrap').append(tempHtml);
		}
    });

});