//
//  RCTToastModule.m
//  RCTToastModule
//
//  Created by zhangzy on 2017/2/22.
//  Copyright © 2017年 ichong. All rights reserved.
//

#import "RCTToastModule.h"
#import "MBProgressHUD.h"

static MBProgressHUD *HUD;

@implementation RCTToastModule

RCT_EXPORT_MODULE();

- (void) getHUD {
    if(!HUD) {
        HUD = [MBProgressHUD showHUDAddedTo:[UIApplication sharedApplication].keyWindow animated:YES];
    }
}

RCT_EXPORT_METHOD(show:(NSString *)message)
{

    dispatch_async(dispatch_get_main_queue(), ^{

        MBProgressHUD *HUD = [MBProgressHUD showHUDAddedTo:[UIApplication sharedApplication].keyWindow animated:YES];
        HUD.mode = MBProgressHUDModeText;
        HUD.label.textColor = [UIColor whiteColor];
        HUD.label.text = message;
        HUD.margin = 10.f;
        HUD.removeFromSuperViewOnHide = YES;
        HUD.userInteractionEnabled= NO;
        HUD.animationType = MBProgressHUDAnimationZoom;
        HUD.bezelView.color = [UIColor colorWithWhite:0.f alpha:0.6f];
        // 设置模式
        HUD.bezelView.style = MBProgressHUDBackgroundStyleSolidColor;

        [HUD hideAnimated:YES afterDelay:2];

    });

}

RCT_EXPORT_METHOD(showLoading:(NSString *)message)
{

    dispatch_async(dispatch_get_main_queue(), ^{

        [self getHUD];
        HUD.mode = MBProgressHUDModeIndeterminate;
        HUD.activityIndicatorColor = [UIColor whiteColor];
        HUD.label.textColor = [UIColor whiteColor];
        HUD.label.text = message;
        HUD.removeFromSuperViewOnHide = YES;
        [HUD showAnimated:YES];

    });

}

RCT_EXPORT_METHOD(hide)
{

    dispatch_async(dispatch_get_main_queue(), ^{

        [HUD hideAnimated: YES];
        HUD = nil;
    });

}

@end
