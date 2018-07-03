/**
 * Created by Administrator on 2018/6/6.
 */
exports.install = function (Vue, options) {
  Vue.prototype.getAPI = function (){//全局函数1
    var base = 'https://easy-mock.com/mock/5b1725a76665bd525d11f4b7/example'
    var data = {
      'workList': base + '/worklist'
    }
    return data
  }
}
