package com.zzy.toast;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zzy on 2017/3/9.
 * Date : 2017/3/9 17:10
 */
public class ToastModule extends ReactContextBaseJavaModule {

    private KProgressHUD kProgressHUD;
    private final List<Snackbar> mActiveSnackbars = new ArrayList<>();

    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ToastModule";
    }

    @ReactMethod
    public void show(String message) {
        ViewGroup view;
        try {
            view = (ViewGroup) getCurrentActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (view == null) return;

        mActiveSnackbars.clear();
        // `hasWindowFocus`: Returns true if this activity's *main* window currently has window focus.
        // Note that this is not the same as the view itself having focus.
        if (!view.hasWindowFocus()) {
            // Get all modal views on the screen.
            ArrayList<View> modals = recursiveLoopChildren(view, new ArrayList<View>());

            // Reverse array in order to get first the last modal rendered.
            Collections.reverse(modals);

            for (View modal : modals) {
                if (modal == null) continue;
                displaySnackbar(modal, message);
                return;
            }

            // No valid modals.
            if (view.getVisibility() == View.VISIBLE) {
                displaySnackbar(view, message);
            }

            return;
        }

        displaySnackbar(view, message);
    }

    private void displaySnackbar(View view, String text) {
        Snackbar snackbar;
        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);
        snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE);
        Snackbar.SnackbarLayout snackbarView = (Snackbar.SnackbarLayout) snackbar.getView();
        View inflate = LayoutInflater.from(snackbar.getView().getContext()).inflate(R.layout.toast, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(snackbarView.getLayoutParams().width, snackbarView.getLayoutParams().height);
        params.gravity = Gravity.CENTER;
        snackbarView.setLayoutParams(params);

        TextView snackbarText = inflate.findViewById(R.id.toast_text);
        snackbarText.setText(text);
        mActiveSnackbars.add(snackbar);

        // 设置SnackbarView的padding都为0，避免上图中出现黑色边框背景的情况
        snackbarView.setPadding(0, 0, 0, 0);
        // 将SnackbarView的背景颜色设置为透明，避免在自定义布局中有圆角或者自适应宽度的时候显示一块黑色背景的情况
        snackbarView.setBackgroundColor(Color.TRANSPARENT);
        snackbarView.addView(inflate);
        snackbar.show();
    }

    private ArrayList<View> recursiveLoopChildren(ViewGroup view, ArrayList<View> modals) {
        if (view.getClass().getSimpleName().equalsIgnoreCase("ReactModalHostView")) {
            modals.add(view.getChildAt(0));
        }

        for (int i = view.getChildCount() - 1; i >= 0; i--) {
            final View child = view.getChildAt(i);

            if (child instanceof ViewGroup) {
                recursiveLoopChildren((ViewGroup) child, modals);
            }
        }

        return modals;
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
