package com.sp.passwordjm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "SdCardPath", "ShowToast" })
public class LoginActivityActivity extends Activity  {
	EditText t1;
	TextView tv1;
	
	Context mContext;
	String pwd = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		mContext=LoginActivityActivity.this;
		//获取输入的密码
		t1=(EditText)findViewById(R.id.passward);
		//获取提示
		tv1=(TextView)findViewById(R.id.errorTip);
		//获取密码
		
		pwd = getPassword();
		
		//判断是否已经设置密码，如果没设置就跳转到设置界面
		if(pwd.length()<30)
		{
			Intent i=new Intent(LoginActivityActivity.this,RegisterActivity.class);
			startActivity(i);
			//清除当前activity
			finish();
		}
		Button b=(Button)findViewById(R.id.login);
		
		//给按钮添加事件监听
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				switch(arg0.getId())
				{
				case R.id.login:
					if(verifyPassword(t1))
					{
						Intent i=new Intent(LoginActivityActivity.this,PasswordListActivity.class);
						i.putExtra("name", pwd.subSequence(0, 5));
						startActivity(i);
						finish();
					}else
					{
						//Toast.makeText(LoginActivityActivity.this, pwd+"读取", Toast.LENGTH_SHORT).show();
						tv1.setText("密码输入错误，请重新输入");
					}
					break;
				}
			}
		});
		//删除登录文件
		Button b1=(Button)findViewById(R.id.button1);
		b1.setVisibility(8);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getApplicationContext().deleteFile("pwd.txt");
				Toast.makeText(LoginActivityActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
			}
		});
	}
	//获取密码，比对密码，只需要密码输入框即可验证密码，true为密码正确，false为密码错误
	public boolean verifyPassword(EditText et) 
	{
		
		
		//输入的密码
	
		String _password=getJiaMi(et.getText().toString());
		Integer s=pwd.length();
		Toast.makeText(LoginActivityActivity.this,pwd+"\n\t"+"--"+_password, Toast.LENGTH_SHORT).show();
		if(pwd.equalsIgnoreCase(_password))
			return true;
		else
			return false;
		
		
	}
	//获取密码
	public  String getPassword()
	{
		//从文件中中获取密码
		
		String path = "pwd.txt";
		FileInputStream input;
		
		StringBuilder sb=new StringBuilder("");
		try {
			input = getApplicationContext().openFileInput(path);
			byte[] temp=new byte[1024];
			
			int len=0;
			
				try {
					while((len=input.read(temp))>0)
					{
						sb.append(new String(temp,0,len));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					try {
						input.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//没有密码文件
			return "";
		}    //,mContext.MODE_PRIVATE);
		//正常获取密码
		return sb.toString();
	}
	//获取加密输入的密码
	public String getJiaMi(String s)
	{
		return md5JM.encrypt(s);
	}
//	//动态获取权限
//	public void getPermission()
//	{
//		
//
//	}
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login_activity, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
