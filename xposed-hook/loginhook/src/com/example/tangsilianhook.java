package com.example;

import java.security.PublicKey;

import android.graphics.Color;
import android.widget.TextView;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class tangsilianhook implements IXposedHookLoadPackage {
	

	
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {

		if (!lpparam.equals("com.example.login")) 
			return;
	XposedBridge.log("find the class");
	
	findAndHookMethod("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader, "updateClock", 
		    new XC_MethodHook() {
		        @Override
		        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
		            TextView tv = (TextView) param.thisObject;
		            String text = tv.getText().toString();
		            tv.setText(text + " :)");
		            tv.setTextColor(Color.RED);
		        }
		});

	
	}

}
