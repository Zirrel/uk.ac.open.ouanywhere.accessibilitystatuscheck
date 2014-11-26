package uk.ac.open.ouanywhere;

import static android.content.Context.ACCESSIBILITY_SERVICE;
import android.view.accessibility.AccessibilityManager;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;

/**
 * @class AccessibilityStatusCheck, monitors the status of device screen readers
 * and informs the app if status changes. Also informs the app of screen reader
 * status when first called.
 *
 * @author Nigel Clarke <nigel.clarke@pentahedra.com>
 */
public class AccessibilityStatusCheck extends CordovaPlugin {

    Boolean screenReaderOn;

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {

        cordova.getThreadPool().execute(new Runnable() { // run in the background
            public void run() {
                // Check initial status and return result
                screenReaderOn = isScreenReaderActive();
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, screenReaderOn ? "YES" : "NO");
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);

                // Add timer to monitor status
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        boolean newState = isScreenReaderActive();
                        if (newState != screenReaderOn) {
                            screenReaderOn = newState;
                            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, screenReaderOn ? "YES" : "NO");
                            pluginResult.setKeepCallback(true);
                            callbackContext.sendPluginResult(pluginResult);
                        }
                    }
                }, 1000, 1000);

            }

            private boolean isScreenReaderActive() {
                AccessibilityManager am = (AccessibilityManager) cordova.getActivity().getSystemService(ACCESSIBILITY_SERVICE);
                return am.isEnabled();
            }
        }
                );

        return true;
    }
}
