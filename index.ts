/**
 * Created by zhangzy on 16/7/27.
 */

'use strict';

import {
    NativeModules,
} from 'react-native';

const ToastModule = NativeModules.ToastModule;

export default class Toast {

    /**
     * 显示文字提示
     * @param message
     */
    static show(message: string) {

        ToastModule.show(message);
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
