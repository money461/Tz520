// function loading() {
//     //    获取浏览器页面可见高度和宽度
//     var _PageHeight = document.documentElement.clientHeight,
//         _PageWidth = document.documentElement.clientWidth;
//     var _LoadingHtml = "<div id='loading' style=\'display:none; width: "+_PageWidth+"px;height: "+_PageHeight+"px;background-color: rgba(0,0,0,0);position: absolute;top: 0;left: 0;z-index: 99;\'><div style='position: absolute;left: 0;right: 0;top: 0;bottom: 0;margin: auto;width: 20%;height: 30%;background-image: url(./loading.gif);background-size: 100% 100%'></div></div>"
//     //呈现loading效果
//     document.write(_LoadingHtml);
// }
// loading();

function createLoading() {
    //    获取浏览器页面可见高度和宽度
    var _PageHeight = document.documentElement.clientHeight,
        _PageWidth = document.documentElement.clientWidth;
    var _LoadingHtml = "<div id='loading' style='display:none;width: "+_PageWidth+"px;height: "+_PageHeight+"px;background-color: rgba(0,0,0,0);position: absolute;top: 0;left: 0;z-index: 99;'><div style='position: absolute;left: 0;right: 0;top: 0;bottom: 0;margin: auto;width: 360px;height: 300px;background-image: url(/tz/static/images/loading.gif);background-size: 100% 100%'></div></div>"
    this.loadingInit=function () {
        //呈现loading效果
        document.write(_LoadingHtml);
    };
    this.loadingShow=function(){
        document.getElementById('loading').style.display='block';
    };
    this.loadingHide=function () {
        document.getElementById('loading').style.display='none';
    };
    this.loadingInit();
}
var loading=new createLoading();