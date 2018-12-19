//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: '我看你的认真，小伙子',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../tem/tem'    //'../user/userDetails'
    })
  },
  
  bindMainPage: function (e) {
    var data = { "userName": app.globalData.userInfo.nickName, "sex": app.globalData.userInfo.gender, "image": app.globalData.userInfo.avatarUrl};
    wx.showLoading({
      title: '加载中...',
    }),
    wx.request({
      url: 'http://localhost:8080/userInfo/addUserInfoIfNoSaved', //仅为示例，并非真实的接口地址
      method: 'POST',
      data: JSON.stringify(data),
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (!res.data.isSuccess) {
          wx.navigateTo({
            url: '../myList/myList'
          })
        } else {
          wx.navigateTo({
            url: '../myList/myList'
          })
        }
      }
    })
  },

  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
