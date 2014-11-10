//
//  CDVAccessibilityStatus.h
//  OUAnywhere
//

#import <Cordova/CDV.h>

@interface CDVAccessibilityStatus : CDVPlugin

-(void)registerAccessibilityStatusUpdate:(CDVInvokedUrlCommand *)command;

@end
