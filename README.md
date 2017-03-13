# react-native-toast

react-native version > 0.4

IOS: 基于MBProgressHUD
Android: 基于原生Toast

### 安装

npm intsall react-native-zzy-toast --save

react-native link

### 使用

```
import Toast from 'react-native-toast';
...
Toast.show('提示信息');

Toast.showLoading('加载中');

Toast.hide();
```

### API

```
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