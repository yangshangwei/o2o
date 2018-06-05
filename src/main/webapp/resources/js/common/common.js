/**
 * 
 */
function changeVerifyCode(img){
	img.src="../Kaptcha?" + Math.floor(Math.random() * 1000)
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}