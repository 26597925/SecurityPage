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
   *   ���ҵ�Ҫ���d��jar��
   *   jar��λ��
   *   ���
   */
	Toast.makeText(getApplicationContext(), "111", 0).show();
	DexClassLoader dexClassLoader=new DexClassLoader("/mnt/sdcard/Myplugdex.jar", "/data/data/com.example.icunqiu", null,this.getClass()
            .getClassLoader());

	/**
	 * java�����{��jar���e��ķ���
	 */
	try {
		//�õ�jar���e��uziclass����
		Class<?> class1=dexClassLoader.loadClass("com.plug.PlugImpl");
	    //ʩ���A�@��Č���
		Object object =class1.newInstance();
		   Class[] param = new Class[2];  
	        param[0] = Integer.TYPE;  
	        param[1] = Integer.TYPE;
	        //�ҵ�Ҫ�ӑB���d�ķ������������
		Method method=class1.getMethod("function_02", param);
		int uzi=(int) method.invoke(object, 1,2);  
		Log.Log("uzi");
		Toast.makeText(getApplicationContext(), uzi+"", 0).show();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
//���ýӿ��ټ��d����
public void useDexClassLoader2() {
    DexClassLoader cDexClassLoader =
            new DexClassLoader("/mnt/sdcard/Myplugdex.jar", "/data/data/com.example.icunqiu", null, this.getClass()
                    .getClassLoader());
   	Toast.makeText(getApplicationContext(), "ufiuhfu", 0).show();
    
    try {
        Class<?> class1 = cDexClassLoader.loadClass("com.plug.PlugImpl");
      
        Object interfacePlug =  class1.newInstance();
        Toast.makeText(getApplicationContext()," ret+", 0).show();
        //�ӿ��ǲ���ʵ�����ģ�������������һ���ӿڵ����ñ���������ָ��һ�����ʵ������Ȼ�Ǹ���Ҫʵ�����Ǹ��ӿڵ�
    	InterfacePlug interfacePlug2=(InterfacePlug) interfacePlug;
        int ret = interfacePlug2.function_02(12, 13);
    	Toast.makeText(getApplicationContext(), ret+"", 0).show();
    } catch (Exception e) {
    }
}
}
