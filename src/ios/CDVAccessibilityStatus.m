//
//  CDVAccessibilityStatus.m
//  OUAnywhere
//

#import "CDVAccessibilityStatus.h"

@implementation CDVAccessibilityStatus {
    CDVPluginResult *pluginResult;
    NSString *callbackID;
}

-(void)registerAccessibilityStatusUpdate:(CDVInvokedUrlCommand *)command {
    callbackID = command.callbackId;
    
    // listen for VO status changes
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(voiceOverStatusChanged)
                                                 name:UIAccessibilityVoiceOverStatusChanged
                                               object:nil];

    [self voiceOverStatusChanged];
}


-(void)voiceOverStatusChanged {
    // check on VO status
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:UIAccessibilityIsVoiceOverRunning() ? @"YES" : @"NO"];
    
    // keep callback open
    [pluginResult setKeepCallback:[NSNumber numberWithBool:YES]];
    
    // fire...
    [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackID];
}

@end
