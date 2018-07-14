# NBBS
##NBBS 前后端规范(仅限 ajax请求，页面之间的url跳转，以后再说)

###1、概述

####1、采用前后端分离的模式进行开发，前端以html Css JavaScript 为主， 后端以Java为开发语言，使用开源框架spring+springmvc+mybatis，前后端通过json数据格式进行沟通

####2、后端统一返回格式(如果没有特殊书名，返回格式，为代码段4),注：在此文档中 json中”int“表示 int型 不是 字符行，”String“ 代表字符：

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
        "message": "具体的错误信息，例如用户名不存在，用户名账号密码错误"
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

success success 为布尔值，false代表本次请求失败，message为具体的错误信息或者错误异常，data 为null，

success 为布尔值，true代表本次请求成功，message一定为OK，data为null ，代表无数据返回，但是成功，代码段4

注意，上述三种情况，均是在服务端可控制的情况下返回的，因此不存在500错误，对应的ajax均为成功后执行的回调函数，

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
    "verificationCode":"String(如果是服务端验证需要这一条，本地不需要)"
  }
```
* 登录时，需求用户邮箱，用户密码，验证码(本地，通过验证才可以提交) url: /user/login post请求
```json
    {
      "user":{
      "email":"String"
      },
      "password": "String"
    }
```
* 注册1 或者登录2 或者找回密码3，更新邮箱4 需要通知服务器端发送验证码 url : /user/verification post请求
```json
    {
      "email":"String",
      "type": "int"
    }
```
* 找回密码,需求的数据，用户邮箱，新的密码，服务器端的验证码 url : /user/forget/password put 请求
```json
    {
      "user":{
       "email":"String",
       "password":"String"
      },
      "verificationCode": "String"
    }
```  
###3、个人信息模块(注： 若有新增加 数据，例如，用户论坛坛龄，请自行在相应的的json代码段中添加)
* 获取个人信息(已登录的用户查看自己的信息) url:/user/info get请求，下面是返回的数据，均位于上述data里面
```json
  {
    "email":"String",
    "userImg":"String(照片所在的url)",
    "nickname":"String",
    "sex":"String",
    "signature": "String",
    "fans":[{
        "nickname":"String",
        "userImg":"String",
        "sex":"String"
    }],
    "attention":[{
      "nickname":"String",
      "userImg":"String",
      "sex":"String"
    }],
    "blacklist":[{
            "nickname":"String",
            "userImg":"String",
            "sex":"String"
    }]
  }
```
* 上传照片(具体的大小在说) url :/user/uploadImg post 请求 (这个应该使用form表单，所以，就不用json格式，加一个隐藏的input，把用户名 nickname 一同上传)

* 更新非重要个人信息(除密码，邮箱) url:/user/info put请求 (这个和上传照片我也不是很清楚应该怎么弄，涉及到form)
```json
  {
     "nickname":"String",
     "sex":"String",
     "signature": "String"
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
           "verificationCode":"String"
    }
```
* 获取关注人，拉黑人，粉丝的数量 url:/user/attentionNum(blacklistNum,fansNum) get 请求 返回格式
```json
{
     "meta": {
            "success": true,
            "message": "ok"
        },
        "data": "int"
}
```
* 获取关注人，拉黑人，粉丝的简介信息 url: /user/attention(blacklist,fans) get请求 返回格式
```json
{
     "meta": {
            "success": true,
            "message": "ok"
        },
        "data": [{
              "nickname":"String",
              "userImg":"String",
              "sex":"String"
        }]
}
```
* 获取其他用户的详细信息 url: /usr/${pickName} get请求，返回格式
```json
{
    "email":"String",
    "userImg":"String(照片所在的url)",
    "nickname":"String",
    "sex":"String",
    "signature": "String"
}
```


