package com.example.imhere;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SOS_Setting extends Activity implements OnClickListener {
	EditText cont1;
	EditText msg;
	EditText rno;
	Button bt1;
	Button bt2;
	Button bt3;
	
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sos_setting);
        cont1=(EditText)findViewById(R.id.cont1);
    	msg=(EditText)findViewById(R.id.msg);
    	rno=(EditText)findViewById(R.id.rno);
    	bt1=(Button)findViewById(R.id.btUpdate);
    	bt2=(Button)findViewById(R.id.btView);
    	bt3=(Button)findViewById(R.id.btDel);
    	bt1.setOnClickListener(this);
    	bt2.setOnClickListener(this);
    	bt3.setOnClickListener(this);
    }


 @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btUpdate:
		boolean didItWork=true;
			try{	
			String contact=cont1.getText().toString();
			String message=msg.getText().toString();
			
			SqlBase entry=new SqlBase(SOS_Setting.this);
			entry.open();
			entry.createEntry(contact,message); 
			entry.close();
			}catch(Exception e){
				didItWork=false;
				String error=e.toString();
				Dialog d=new Dialog(this);
				d.setTitle("Hell no...!!");
				TextView tv=new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}finally{
				if(didItWork){
					Dialog d=new Dialog(this);
					d.setTitle("hell yeah !!!");
					TextView tv=new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			
			break;
			
		
		case R.id.btView:
			Intent i=new Intent("android.intent.action.SQL_VIEW");
			startActivity(i);
			
			break;
			
		
		case R.id.btDel:	
			try{
			String s=rno.getText().toString();
			long a=Long.parseLong(s);
			SqlBase val=new SqlBase(this);
			val.open();
			val.deleteEntry(a);
			val.close();
			}catch(Exception e){
				
				String error=e.toString();
				Dialog d=new Dialog(this);
				d.setTitle("Hell no...!!");
				TextView tv=new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			
			break;
		}
	}
}
