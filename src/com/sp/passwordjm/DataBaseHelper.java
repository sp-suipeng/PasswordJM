package com.sp.passwordjm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {     
	 public DataBaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}
	

	 

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		//首次创建数据库的时候创建个表
		arg0.execSQL("CREATE TABLE mytable ( name varchar(20) PRIMARY KEY  , account varchar(20), password varchar(20));");
	}



	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}     
	}
