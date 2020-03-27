package com.sp.passwordjm;

import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class napAdapter extends BaseAdapter {
	

	    private LinkedList<nap> mData;
	    private Context mContext;

	    public napAdapter(LinkedList<nap> mData, Context mContext) {
	        this.mData = mData;
	        this.mContext = mContext;
	    }
	    //获取数据总数
	    @Override
	    public int getCount() {
	        return mData.size();
	    }
	    //获取一条数据
	    @Override
	    public Object getItem(int position) {
	        return null;
	    }
	    //获取数据位置
	    @Override
	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	//获取布局文件
	        convertView = LayoutInflater.from(mContext).inflate(R.layout.nap_list,parent,false);
	        //获取布局文件中的控件
	        TextView name = (TextView) convertView.findViewById(R.id.textView1);
	        TextView account = (TextView) convertView.findViewById(R.id.textView2);
	        TextView pwd = (TextView) convertView.findViewById(R.id.textView3);
	      //给布局文件添加数据
	        name.setText(mData.get(position).getName());
	        account.setText(mData.get(position).getAccount());
	        pwd.setText(mData.get(position).getPassword());
	        return convertView;
	    }

		
	
}
