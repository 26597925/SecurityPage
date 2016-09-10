package com.example.loginhook;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
//新建一個main類來實現xposed的接口
public class Main implements IXposedHookLoadPackage {
	/**
     * 包加载时候的回调
     */
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		// TODO Auto-generated method stub
	    //不是要hook的對象就return 
		if (!lpparam.packageName.equals("com.example.login"))
	            return;
	//打印要hook的類
	     XposedBridge.log("Loaded app: " + lpparam.packageName);
	     findAndHookMethod("com.example.login.MainActivity", lpparam.classLoader, "uzi", String.class,
	    		 String.class,    new XC_MethodHook() {

	                    @Override
	                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
	                        XposedBridge.log("开始劫持了~");
	                        XposedBridge.log("参数1 = " + param.args[0]);
	                        XposedBridge.log("参数1 = " + param.args[1]);
	                   
	                    }

	                    @Override
	                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
	                        XposedBridge.log("劫持结束了~");
	                        XposedBridge.log("参数1 = " + param.args[0]);
	                        XposedBridge.log("参数1 = " + param.args[1]);
	                  

	                    }
	
	     });
	
	}

}
