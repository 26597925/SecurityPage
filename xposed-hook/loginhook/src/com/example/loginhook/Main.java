package com.example.loginhook;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
//�½�һ��main팍�Fxposed�Ľӿ�
public class Main implements IXposedHookLoadPackage {
	/**
     * ������ʱ��Ļص�
     */
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		// TODO Auto-generated method stub
	    //����Ҫhook�Č����return 
		if (!lpparam.packageName.equals("com.example.login"))
	            return;
	//��ӡҪhook���
	     XposedBridge.log("Loaded app: " + lpparam.packageName);
	     findAndHookMethod("com.example.login.MainActivity", lpparam.classLoader, "uzi", String.class,
	    		 String.class,    new XC_MethodHook() {

	                    @Override
	                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
	                        XposedBridge.log("��ʼ�ٳ���~");
	                        XposedBridge.log("����1 = " + param.args[0]);
	                        XposedBridge.log("����1 = " + param.args[1]);
	                   
	                    }

	                    @Override
	                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
	                        XposedBridge.log("�ٳֽ�����~");
	                        XposedBridge.log("����1 = " + param.args[0]);
	                        XposedBridge.log("����1 = " + param.args[1]);
	                  

	                    }
	
	     });
	
	}

}
