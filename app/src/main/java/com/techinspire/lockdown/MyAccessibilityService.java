package com.techinspire.lockdown;

import android.accessibilityservice.AccessibilityService;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

public class MyAccessibilityService extends AccessibilityService {

    private KeyguardManager mKeyguardManager;
    private KeyguardManager.KeyguardLock mKeyguardLock;
    private static final String APP_PACKAGE_NAME = "com.techinspire.lockdown";

    @Override
    public void onCreate() {
        super.onCreate();
        mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Toast.makeText(this, event.getEventType(), Toast.LENGTH_SHORT).show();
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
                event.getPackageName().equals(APP_PACKAGE_NAME)) {
            performGlobalAction(AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS);

        }

    }

    private void lockDevice() {
        if (mKeyguardLock == null) {
            mKeyguardLock = mKeyguardManager.newKeyguardLock("MyAccessibilityService");
        }
        mKeyguardLock.disableKeyguard();
    }

    private void unlockDevice() {
        if (mKeyguardLock != null) {
            mKeyguardLock.reenableKeyguard();
            mKeyguardLock = null;
        }
    }

    @Override
    public void onInterrupt() {
        Toast.makeText(this, "get", Toast.LENGTH_SHORT).show();
        // TODO: Handle accessibility interrupt
    }
}



