//
//  RCTToastModule.m
//  RCTToastModule
//
//  Created by zhangzy on 2017/2/22.
//  Copyright © 2017年 ichong. All rights reserved.
//

#import "RCTToastModule.h"
#import "MBProgressHUD.h"

@implementation RCTToastModule

RCT_EXPORT_MODULE();


RCT_EXPORT_METHOD(show:(NSString *)message)
{
    
    dispatch_async(dispatch_get_main_queue(), ^{
        
        MBProgressHUD *HUD = [MBProgressHUD showHUDAddedTo:[UIApplication sharedApplication].keyWindow animated:YES];
        
        HUD.mode = MBProgressHUDModeText;
        HUD.label.textColor = [UIColor whiteColor];
        HUD.label.text = message;
        HUD.margin = 10.f;
        HUD.removeFromSuperViewOnHide = YES;
        
        [HUD hideAnimated:YES afterDelay:2];
        
    });
    
}

@end
