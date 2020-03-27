package com.sp.passwordjm;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PasswordListActivity extends Activity {
	ListView lv1;
	Button b1;
	Button b2;
	String tableName;
	LinkedList<nap> info;
	//可迭代的，保存数据信息
	LinkedList<nap> ll=new LinkedList<nap>(); 
	 class MyButtonlistener implements View.OnClickListener{
         @Override
         public void onClick(View v) {
             //相关事件处理
        	 switch(v.getId())
        	 {
        	 case R.id.bar_btn:
        		//在这里跳转到账号添加界面
 				Intent i=new Intent(PasswordListActivity.this,AddPasswordActivity.class);
 				startActivity(i);
 				break;
        	 case R.id.flush_btn:
        		 
        		 updateList();
        		 Toast.makeText(PasswordListActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
        	        break;
 				
        	 }
         }
     }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.password_activity);
	info=new LinkedList<nap>();
	lv1=(ListView)findViewById(R.id.passwordList);
	Intent i=getIntent();
	tableName=i.getExtras().getString("name");
	//Toast.makeText(PasswordListActivity.this,tableName, Toast.LENGTH_LONG).show();
	//获取顶部导航栏句柄
	ActionBar actionBar = getActionBar();
    if (actionBar != null) {
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //Enable自定义的View
        actionBar.setCustomView(R.layout.pwdlistactionbar);  //绑定自定义的布局：
        b1=(Button)actionBar.getCustomView().findViewById(R.id.bar_btn);
        b2=(Button)actionBar.getCustomView().findViewById(R.id.flush_btn);
        MyButtonlistener listener=new MyButtonlistener();
        b1.setOnClickListener(listener);
       b2.setOnClickListener(listener);
       //给ListView设置触摸事件
//      lv1.setOnItemClickListener(new OnItemClickListener() {
//		
//		@Override
//		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//			// TODO Auto-generated method stub
//			Toast.makeText(PasswordListActivity.this,String.valueOf(arg2)+info.get(arg2).getName(), Toast.LENGTH_LONG).show();
//		}
//	});
      //长按删除
      lv1.setOnItemLongClickListener(new OnItemLongClickListener() {
		
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub
			//Toast.makeText(PasswordListActivity.this,String.valueOf(arg2)+info.get(arg2).getName(), Toast.LENGTH_LONG).show();
			//删除
			deleteOneRow(info.get(arg2).getName());
			//更新
			updateList();
			return false;
		}
	});
        //更新数据
        updateList();
        
        
        
    }
    

    
	}
	//删除某一行
	public void deleteOneRow(String arg)
	{
		
        DataBaseHelper dbh=new DataBaseHelper(getApplicationContext(), "pwd",null,1);
        SQLiteDatabase db = dbh.getWritableDatabase();
        //表名 条件 参数
        db.delete("mytable", "name=?", new String[]{arg});
        
	       
       
	}
	//更新视图
	public void updateList()
	{
		 	info.clear();
	        DataBaseHelper dbh=new DataBaseHelper(getApplicationContext(), "pwd",null,1);
	        SQLiteDatabase db = dbh.getReadableDatabase();
	        Cursor result=db.rawQuery( 
	        	    "SELECT * FROM mytable", null);
	        result.moveToFirst(); 
	        while (!result.isAfterLast()) { 
	            String name=result.getString(0); 
	            String account=result.getString(1); 
	            String pwd=result.getString(2); 
	            
	            // do something useful with these 
	            info.add(new nap(name,account,pwd));
	            result.moveToNext(); 
	          } 
	          result.close();
	          db.close();
	       
	        napAdapter adapter=new napAdapter((LinkedList<nap>)info,PasswordListActivity.this);
	        lv1.setAdapter( adapter);
	        
	}

}
