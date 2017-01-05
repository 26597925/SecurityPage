package com.example.loginhook;


import android.content.Intent;
import android.os.BatteryManager;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class HelloXposedWorld implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
    	  XposedHelpers.findAndHookMethod(
                  "android.content.Intent",    // 类全路径
                  lpparam.classLoader,        //ClassLoader
                  "getIntExtra",             //HOOK目标函数名
                  String.class,              //参数1类型
                  int.class,                 //参数2类型
                  new XC_MethodHook() {
                      @Override
                      protected void beforeHookedMethod(MethodHookParam param)
                              throws Throwable {
                          Intent intent = (Intent) param.thisObject;
                          final String action = intent.getAction();
                          if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                              if (BatteryManager.EXTRA_LEVEL.equals(param.args[0] + "")) {
                                  param.setResult(1);
                              } else if ("status".equals(param.args[0] + "")) {
                                  XposedBridge.log("status");
                                  param.setResult(BatteryManager.BATTERY_STATUS_NOT_CHARGING);
                              }
                          }
                      }
/*public static boolean fun1(String[][] strAry, Map mp1, Map<String,String> mp2, Map<Integer, String> mp3,
                      ArrayList<String> al1, ArrayList<Integer> al2, ArgClass ac) 
 ([[Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;
 Ljava/util/ArrayList;Ljava/util/ArrayList;Laqcxbom/xposedhooktarget/ArgClass;)
 final Class<?> ArgClass= XposedHelpers.findClass("aqcxbom.xposedhooktarget.ArgClass", loadPackageParam.classLoader);
            final Class<?> ArrayList= XposedHelpers.findClass("java.util.ArrayList", loadPackageParam.classLoader);
            final Class<?> Map= XposedHelpers.findClass("java.util.Map", loadPackageParam.classLoader);

//包名一致时查找是否有匹配参数的类及函数
            XposedHelpers.findAndHookMethod(mStrClassPath, //类路径
loadPackageParam.classLoader, //ClassLoader
mStrMethodName, //目标函数名
                    "[[Ljava.lang.String;", //参数1
                    Map, //参数2
                    Map, //参数3
                    Map, //参数4
                    ArrayList, //参数5
                    ArrayList, //参数6
                    ArgClass, //参数7                     
                      
          如果要hook当前的dialog之类的，需要传入context
          可以直接get即可  AndroidAppHelper.currentApplication().getApplicationContext() 
          1.有全局context处理的hook那个get方法
2.方法所在类继承context可以用MethodHookParam实例的thisObject 
          
                      */
                      
                      
                      @Override
                      protected void afterHookedMethod(MethodHookParam param)
                          throws Throwable {
                      }
                  }
           );  }
}
