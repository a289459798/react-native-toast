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

    static show(message) {

        if(Platform.OS == 'ios') {

            ToastModule.show(message);
        } else {

            var {ToastAndroid} = ReactNative;

            ToastAndroid.show(message, ToastAndroid.SHORT);
        }
    }
}