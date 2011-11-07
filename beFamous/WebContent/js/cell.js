$(function(){
	//Full Caption Sliding (Hidden to Visible)
	imageHover();
	//縮圖
	$("img").each(function(i){
		if($(this).attr("reduce")=="true"){
			//移除目前設定的影像長寬
			$(this).removeAttr('width');
			$(this).removeAttr('height');
 
			//取得影像實際的長寬
			var imgW = $(this).width();
			var imgH = $(this).height();
 
			//計算縮放比例
			var w=$(this).attr("_w")/imgW;
			var h=$(this).attr("_h")/imgH;
			var pre=1;
			if(w>h){
				pre=h;
			}else{
				pre=w;
			}
 
			//設定目前的縮放比例
			$(this).width(imgW*pre);
			$(this).height(imgH*pre);
		}
	});
});

function imageHover(){
	$('.boxgrid.captionfull').unbind('mouseenter mouseleave');
	$('.boxgrid.captionfull').hover(function(){
		$(".cover", this).stop().animate({top:'80px'},{queue:false,duration:160});
	}, function() {
		$(".cover", this).stop().animate({top:'100px'},{queue:false,duration:160});
	});
	$('.labeltext a').each(function(index){
		
	});
}