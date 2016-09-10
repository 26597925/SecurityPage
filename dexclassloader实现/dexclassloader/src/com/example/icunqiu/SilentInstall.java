package com.example.icunqiu;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class SilentInstall {
	Context context;
	//��֪�����context�ܷ���ʾ
	/**
	 * ִ�о���ľ�Ĭ��װ�߼�����Ҫ�ֻ�ROOT��
	 * 
	 * @param apkPath
	 *            Ҫ��װ��apk�ļ���·��
	 * @return ��װ�ɹ�����true����װʧ�ܷ���false��
	 */
	public boolean install(String apkPath) {
		boolean result = false;
		DataOutputStream dataOutputStream = null;
		BufferedReader bufferedReader = null;
		try {
			//����rootȨ��
			Process process=Runtime.getRuntime().exec("su");
			dataOutputStream =new DataOutputStream(process.getOutputStream());
			//ִ��pm install����
			String command="pm install -r"+apkPath+"\n";
            dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));  
            dataOutputStream.flush();  
            dataOutputStream.writeBytes("exit\n");  
            dataOutputStream.flush();  
            process.waitFor();  
            bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));  
            String msg = "";  
            String line;  
            //��ȡ�����ִ�н��
            while((line=bufferedReader.readLine())!=null){
                msg += line;  
            }
            //�����װʧ�� toast��ʾʧ����Ϣ
            if(msg.contains("Failure")){
            	Toast.makeText(context, msg, 0).show();
            }else {
                result = true;  
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			 try {  
	                if (dataOutputStream != null) {  
	                    dataOutputStream.close();  
	                }  
	                if (bufferedReader != null) {  
	                	bufferedReader.close();  
	                }  
	            } catch (Exception e) {  
	                Log.e("TAG", e.getMessage(), e);  
	            }  
	        }  
	        return result;
			
		}
		
		
		
		
		



}
