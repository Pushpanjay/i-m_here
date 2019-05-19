package com.example.imhere;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Sql_view extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
	TextView tv=(TextView)findViewById(R.id.tvdb);	
	SqlBase info=new SqlBase(this);
	info.open();
	String data=info.getData();
	info.close();
	tv.setText(data);
	
	}
	
}
