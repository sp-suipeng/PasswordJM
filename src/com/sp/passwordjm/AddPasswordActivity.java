package com.sp.passwordjm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class AddPasswordActivity extends Activity {
	EditText name;
	EditText account;
	EditText password;
	Button store;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_password);
		name=(EditText)findViewById(R.id.editText1);
		account=(EditText)findViewById(R.id.editText2);
		password=(EditText)findViewById(R.id.editText3);
		store=(Button)findViewById(R.id.button1);
		store.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//存储密码
				storePassword(name.getText().toString(),account.getText().toString(),password.getText().toString());
				//提示
				Toast.makeText(AddPasswordActivity.this, "添加成功", Toast.LENGTH_SHORT).show();;
				//清空
				name.setText("");
				account.setText("");
				password.setText("");
			}
		});
	}
	//使用sqlite存储密码
	public void storePassword(String n,String a,String p)
	{
		//判断是否未输入就保存
		if(n.length()==a.length()&&n.length()==p.length())
		{
			Toast.makeText(AddPasswordActivity.this, "输入不能全为空", Toast.LENGTH_SHORT).show();
		}else
		{
			DataBaseHelper dbh=new DataBaseHelper(getApplicationContext(), "pwd",null,1);
			SQLiteDatabase db = dbh.getWritableDatabase();
			//插入数据
			ContentValues cv=new ContentValues(); 
			cv.put("name", n); 
			cv.put("account", a); 
			cv.put("password", p); 
			db.insert("mytable", "", cv);
		}
		
		
	}
	//加密密码
	public String jiami(String p)
	{
		return AESUtil.AESJiaMi(p);
	}

}
