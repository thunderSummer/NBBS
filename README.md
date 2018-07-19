# NBBS
##NBBS 前后端规范(仅限 ajax请求，页面之间的url跳转，以后再说)

###1、概述

####1、采用前后端分离的模式进行开发，前端以html Css JavaScript 为主， 后端以Java为开发语言，使用开源框架spring+springmvc+mybatis，前后端通过json数据格式进行沟通

####2、后端统一返回格式(如果没有特殊书名，返回格式，为代码段4),注：在此文档中 json中”int“表示 int型 不是 字符，”String“ 代表字符：

* 代码段1 
```json
{
    "meta": {
        "success": true,
        "message": "ok"
    },
    "data": "123"
}
```
* 代码段2
```json
{
    "meta": {
        "success": false,
        "message": "具体的错误信息，例如用户名不存在，用户名账号密码错误，或者异常名字"
    },
    "data": null
}
```
* 代码段3
```json
{
    "meta": {
        "success": true,
        "message": "ok"
    },
    "data": {
        "name":"夏效雷",
        "age":18,
        "address" :{
            "country":"中国",
            "province":"辽宁",
            "city":"大连"
        }
    }
}
```
* 代码段4
```json
{
    "meta": {
        "success": true,
        "message": "ok"
    },
    "data": null
}
```

success 为布尔值，true代表本次请求成功，message一定为OK，data代表返回的数据，当返回的数据只有一个，所返回的格式，类似于代码段1

success 为布尔值，true代表本次请求成功，message一定为OK，data代表返回的数据，当返回的数据为一个对象时，所返回的格式，类似于代码段3

success success 为布尔值，false代表本次请求失败，message为具体的错误信息或者错误异常，data 为null,类似于代码段2

success 为布尔值，true代表本次请求成功，message一定为OK，data为null ，代表无数据返回，但是成功，类似于代码段4

注意，上述四种情况，均是在服务端可控制的情况下返回的，因此不存在500错误，对应的ajax均为成功后执行的回调函数，

####3、前端发送格式：
* 具体的发送格式由具体功能确定，但大致如以下代码
```json
    {
      "account":"这是一个账号名字",
      "list":[
      {
        "listName": "name",
        "listIndex": 1
      },
      {
              "listName": "name",
              "listIndex": 2
      }
      ]
    }
```
###2 注册登录模块
* 注册时，需求的数据：用户邮箱，用户密码（最好md5加密，明文也行），用户账号名称(类似于百度贴吧或者游戏里面的名字，不可重复，唯一标示，但是可以更改)，验证码(本地验证,或者邮箱验证)
url: /user/register post请求
```json
  {
    "user":{
        "email":"String",
        "password": "String",
        "nickname":"String"
    },
    "vCode":"String"
  }
```
* 登录时，需求用户邮箱，用户密码，验证码(本地，通过验证才可以提交) url: /user/login post请求
```json
    {
      "email":"String",
      "password": "String"
    }
```
* 注册 typ11 或者登录type2(可能用不到) 或者找回密码3，更新邮箱4 需要通知服务器端发送验证码 url : /user/verification post请求
```json
    {
   
      "email":"String(我qq邮箱接受不到消息。163邮箱可以)",
      "type": "int"
    }
```
* 找回密码,需求的数据，新的密码，服务器端的验证码 url : /user/password put 请求
```json
    {
      "user":{
       "password":"String"
      },
      "vCode": "String"
    }
```  
* 获取某个用户的状态，url: /user/status post 请求

返回格式 第一个代表他不是不是登录了，第二个代表他是不是本版块的版主，或者他是不是版主，第三个类似第二个
```json
{
  "user":"布尔型",
  "adminSection":"布尔型",
  "adminPartition":"布尔型"
}
```

请求格式 如果是针对特对版块，特对分区填上id，如果不针对特定版块，特定分区 那就为0,

```json
{
  "partitionId":"int",
  "sectionId":"int"
}

```
###3、个人信息模块(注： 若有新增加 数据，例如，用户论坛坛龄，请自行在相应的的json代码段中添加)
* 获取个人信息(已登录的用户查看自己的信息) url:/user/info get请求，下面是返回的数据，均位于上述data里面
```json
  {
    "email":"String",
    "avatar":"String(照片所在的url)",
    "nickname":"String",
    "sex":"String(男女)",
    "signature": "String",
    "ex":"int(用户经验)",
    "createTime": "String",
    "phone":"String",
    "status":"int(用户当前的状态，1代表正常，2代表被封禁)"
  }
```
* 上传照片(具体的大小在说) url :/upload/avatar post 请求 (这个应该使用form表单，所以，就不用json格式，加一个隐藏的input，把用户名 nickname 一同上传)

* 更新非重要个人信息(除密码，邮箱) url:/user/info put请求 (这个和上传照片我也不是很清楚应该怎么弄，涉及到form)
```json
  {
     "nickname":"String",
     "sex":"String(“男” “女”)",
     "signature": "String",
     "phone":"String"
  }
``` 
* 更新邮箱 url : /user/email put请求(必须 先发送验证码)
```json
    {
    "user":{
      "email":"String"
    },
    "verificationCode":"String"
    }
```
* 更新密码 url: /user/password put请求
```json
    {
       "user":{
             "password":"String"
           },
           "vCode":"String"
    }
```
* 获取关注人，拉黑人，粉丝的数量 url:/user/FBF/num get 请求 返回格式
```json
{
     "meta": {
            "success": true,
            "message": "ok"
        },
        "data": {
        "fans":"int",
        "black":"int",
        "follow":"int"
        }
}
```
* 获取关注人，拉黑人，粉丝的简介信息 url: /user/follow(black,fans) get请求 返回格式
```json
{
     "meta": {
            "success": true,
            "message": "ok"
        },
        "data": [{
              "nickname":"String",
              "avatar":"String",
              "sex":"String",
              "createTime":"String"
        }]
}
```
* 获取其他用户的详细信息 url: /user/userInfo/${nickName} get请求，返回格式
```json
{
    "email":"String",
    "avatar":"String(照片所在的url)",
    "nickname":"String",
    "sex":"String",
    "signature": "String",
    "ex":"int"
}
```
* 关注某人，拉黑某人 url: /user/follow(black) post 請求
发送
```json
{
 "nickname":"String"
}
```
* 取消关注某人，取消拉黑某人，取消某人当我的粉丝 /user/follow(fans/black) delete请求
发送
```json
{
 "nickname":"String"
}
```
* ，某人是不是我的关注，是不是我的黑名单，是不是我的粉丝 /user/isFollow(isFans 、isBlack)/{nickname} get请求


