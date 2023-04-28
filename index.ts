/**
 * Created by zhangzy on 16/7/27.
 */

'use strict';

import {
    NativeModules,
    Platform,
    ToastAndroid,
} from 'react-native';

const ToastModule = NativeModules.ToastModule;

export default class Toast {

    /**
     * 显示文字提示
     * @param message
     */
    static show(message: string) {

        if (Platform.OS == 'ios') {

            ToastModule.show(message);
        } else {

            ToastAndroid.showWithGravity(message, ToastAndroid.SHORT, ToastAndroid.CENTER);
        }
    }

    /**
     * @param message
     */
    static success(message: string) {

        ToastModule.showSuccess(message);
    }

    /**
     * 显示loading
     * @param message
     */
    static showLoading(message: string) {

        ToastModule.showLoading(message);
    }

    /**
     * 隐藏提示信息
     */
    static hide() {

        ToastModule.hide();
    }

}
