# react-native-toast

react-native version > 0.4

IOS: 基于 [MBProgressHUD](https://github.com/jdg/MBProgressHUD)
Android: 基于原生Toast 和 [KProgressHUD](https://github.com/Kaopiz/KProgressHUD)

### 作者

QQ: 289459798
QQ群: 161263093
欢迎更多的喜欢开源的小伙伴加入

### IOS效果

[![](http://dl.dropbox.com/u/378729/MBProgressHUD/v1/7-thumb.png)](http://dl.dropbox.com/u/378729/MBProgressHUD/v1/7.png)
[![](http://dl.dropbox.com/u/378729/MBProgressHUD/v1/1-thumb.png)](http://dl.dropbox.com/u/378729/MBProgressHUD/v1/1.png)
[![](http://dl.dropbox.com/u/378729/MBProgressHUD/v1/2-thumb.png)](http://dl.dropbox.com/u/378729/MBProgressHUD/v1/2.png)

### Android效果

![](https://raw.githubusercontent.com/Kaopiz/KProgressHUD/master/demo/screenshots/screencast.gif)

目前之后上图菜单1和菜单2的效果

### 安装

```sh
npm intsall react-native-zzy-toast --save

react-native link
```

### 使用

```js
import Toast from 'react-native-toast';
...
Toast.show('提示信息');

Toast.showLoading('加载中');

Toast.hide();
```

### API

```js
/**
 * 显示文字提示
 * @param message
 */
static show(message);

/**
 * 显示loading
 * @param message
 */
static showLoading(message);

/**
 * 隐藏提示信息
 */
static hide();
```
