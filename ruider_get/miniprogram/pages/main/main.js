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
      url: '../user/userDetails'
    })
  },

  getAllMyList: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByUserId?userId=1', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../user/userNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../user/userNeedsManage'
          })
        }
      }
    })
  },

  addNew: function (e) {
    wx.navigateTo({
      url: '../addNew/addNew'
    })
  },

  carpoolingText: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByUserIdAndCategotyId?userId=1&categoryId=1', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../user/userNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../user/userNeedsManage'
          })
        }
      }
    })
  },

  filmText: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByUserIdAndCategotyId?userId=1&categoryId=3', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../user/userNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../user/userNeedsManage'
          })
        }
      }
    })
  },

  studyText: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByUserIdAndCategotyId?userId=1&categoryId=4', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../user/userNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../user/userNeedsManage'
          })
        }
      }
    })
  },

  competitionText: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByUserIdAndCategotyId?userId=1&categoryId=2', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../user/userNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../user/userNeedsManage'
          })
        }
      }
    })
  },

  mainPageBtn: function (e) {
    wx.navigateTo({
      url: '../main/main',
    })
  },

  myPageBtn:function(e) {
    wx.navigateTo({
      url: '../myList/myList',
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
