package com.zzy.toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by zzy on 2017/3/9.
 * Date : 2017/3/9 17:10
 */
public class ToastModule extends ReactContextBaseJavaModule {

    private KProgressHUD kProgressHUD;

    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ToastModule";
    }

    @ReactMethod
    public void showLoading(String message) {
        kProgressHUD = KProgressHUD.create(getCurrentActivity())
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(message)
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show();
    }


    @ReactMethod
    public void hide() {
        if (kProgressHUD != null && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }

    }

}
