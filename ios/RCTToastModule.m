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
        HUD.label.numberOfLines = 2;
        NSMutableParagraphStyle *paragraphStyle = [NSMutableParagraphStyle new];
        paragraphStyle.lineSpacing = 10 - (HUD.label.font.lineHeight - HUD.label.font.pointSize);
        NSMutableDictionary *attributes = [NSMutableDictionary dictionary];
        [attributes setObject:paragraphStyle forKey:NSParagraphStyleAttributeName];
        HUD.label.attributedText = [[NSAttributedString alloc] initWithString:HUD.label.text attributes:attributes];
        HUD.label.textAlignment = NSTextAlignmentCenter;
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

RCT_EXPORT_METHOD(showSuccess:(NSString *)message)
{
    
    dispatch_async(dispatch_get_main_queue(), ^{
        
        MBProgressHUD *HUD = [MBProgressHUD showHUDAddedTo:[UIApplication sharedApplication].keyWindow animated:YES];
        HUD.mode = MBProgressHUDModeCustomView;
        UIImage *image = [UIImage imageNamed:@"toast_success"];
        UIImageView *imageView = [[UIImageView alloc] initWithImage:image];
        
        HUD.customView = imageView;

        HUD.label.textColor = [UIColor whiteColor];
        HUD.label.text = message;
        HUD.label.numberOfLines = 2;
        
        NSMutableParagraphStyle *paragraphStyle = [NSMutableParagraphStyle new];
        paragraphStyle.lineSpacing = 10 - (HUD.label.font.lineHeight - HUD.label.font.pointSize);
        NSMutableDictionary *attributes = [NSMutableDictionary dictionary];
        [attributes setObject:paragraphStyle forKey:NSParagraphStyleAttributeName];
        HUD.label.attributedText = [[NSAttributedString alloc] initWithString:HUD.label.text attributes:attributes];
        HUD.label.textAlignment = NSTextAlignmentCenter;
        
        HUD.removeFromSuperViewOnHide = YES;
        HUD.animationType = MBProgressHUDAnimationZoom;
        HUD.bezelView.color = [UIColor colorWithWhite:0.f alpha:0.7f];
        HUD.bezelView.style = MBProgressHUDBackgroundStyleSolidColor;
        [HUD showAnimated:YES];
        
        [HUD hideAnimated:YES afterDelay:2];
        
    });
    
}

RCT_EXPORT_METHOD(showLoading:(NSString *)message)
{
    
    dispatch_async(dispatch_get_main_queue(), ^{
        
        MBProgressHUD *HUD = [MBProgressHUD showHUDAddedTo:[UIApplication sharedApplication].keyWindow animated:YES];
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
