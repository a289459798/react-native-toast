package com.zzy.toast;

import android.os.Handler;
import android.widget.ImageView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.views.imagehelper.ImageSource;
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
    public void showSuccess(String message) {

        ImageView imageView = new ImageView(getCurrentActivity());
        imageView.setBackgroundResource(R.drawable.toast_success);

        kProgressHUD = KProgressHUD.create(getCurrentActivity())
            .setCustomView(imageView)
            .setLabel(message)
            .setCancellable(true)
            .setAnimationSpeed(2)
            .show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (kProgressHUD != null) {
                    kProgressHUD.dismiss();
                }
            }
        }, 2000);
    }


    @ReactMethod
    public void hide() {
        if (kProgressHUD != null) {
            kProgressHUD.dismiss();
        }

    }

}
