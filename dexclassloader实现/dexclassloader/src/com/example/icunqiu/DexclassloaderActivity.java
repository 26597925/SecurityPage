package com.example.icunqiu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tangsilian.utils.LogUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import dalvik.system.DexClassLoader;

public class DexclassloaderActivity  extends Activity{
LogUtils Log;
	@Override
protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         try {
			useDexclassloader();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         //useDexClassLoader2();
}

public void useDexclassloader() throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
  /**
   *   先找到要加載的jar包
   *   jar包位置
   *   類名
   */
	Toast.makeText(getApplicationContext(), "111", 0).show();
	DexClassLoader dexClassLoader=new DexClassLoader("/mnt/sdcard/Myplugdex.jar", "/data/data/com.example.icunqiu", null,this.getClass()
            .getClassLoader());

	/**
	 * java反射調用jar包裡面的方法
	 */
	try {
		//拿到jar包裡的uziclass對象
		Class<?> class1=dexClassLoader.loadClass("com.plug.PlugImpl");
	    //施樂華這個類的對象
		Object object =class1.newInstance();
		   Class[] param = new Class[2];  
	        param[0] = Integer.TYPE;  
	        param[1] = Integer.TYPE;
	        //找到要動態加載的方法并传入参数
		Method method=class1.getMethod("function_02", param);
		int uzi=(int) method.invoke(object, 1,2);  
		Log.Log("uzi");
		Toast.makeText(getApplicationContext(), uzi+"", 0).show();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
//利用接口再加載方法
public void useDexClassLoader2() {
    DexClassLoader cDexClassLoader =
            new DexClassLoader("/mnt/sdcard/Myplugdex.jar", "/data/data/com.example.icunqiu", null, this.getClass()
                    .getClassLoader());
   	Toast.makeText(getApplicationContext(), "ufiuhfu", 0).show();
    
    try {
        Class<?> class1 = cDexClassLoader.loadClass("com.plug.PlugImpl");
      
        Object interfacePlug =  class1.newInstance();
        Toast.makeText(getApplicationContext()," ret+", 0).show();
        //接口是不能实例化的，不过可以声明一个接口的引用变量，让它指向一个类的实例，当然那个类要实现了那个接口的
    	InterfacePlug interfacePlug2=(InterfacePlug) interfacePlug;
        int ret = interfacePlug2.function_02(12, 13);
    	Toast.makeText(getApplicationContext(), ret+"", 0).show();
    } catch (Exception e) {
    }
}
}
