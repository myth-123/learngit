/**
 * Created by Administrator on 2018/6/6.
 */
//获取cookie、
export function getCookie(name) {
  var v = window.document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return v ? v[2] : null;
}

//设置cookie,增加到vue实例方便全局调用
export function setCookie (name, value, days) {
  var d = new Date
  d.setTime(d.getTime() + 24 * 60 * 60 * 1000 * days)
  window.document.cookie = name + '=' + value + ';path=/;expires=' + d.toGMTString()
}

//删除cookie
export function delCookie (name) {
  this.setCookie(name, '', -1)
}
