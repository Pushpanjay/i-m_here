package com.example.imhere;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SOS extends Activity implements OnClickListener{
	
	Button bt;
	EditText rno;
	TextView tv;
	
		

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sos);
        rno=(EditText)findViewById(R.id.rno);
        tv=(TextView)findViewById(R.id.textView1);
        bt=(Button)findViewById(R.id.bt);
        bt.setOnClickListener(this);
    }
    
    
    @Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
    	float slat=LocWithMap.getLat();
    	float slng=LocWithMap.getLng();
    	tv.setText("Latitude :"+String.valueOf(slat)+"    longitude :"+String.valueOf(slng));
    	getVal(slat,slng);
    	
    	
	} 
    
    
  

 public void getVal(float slat,float slng)
  {
	 String returnedNo;
	 String returnedMsg;

	
 try{
  SqlBase val=new SqlBase(this);
  val.open();   
  returnedNo=val.getNo(0);
  returnedMsg=val.getMsg(0);	
	val.close();

sendSMS(returnedNo,returnedMsg+"   I'M HERE :  Latitude : "+slat+"Longitude : "+slng);
 }catch(Exception e){
		
		String error=e.toString();
		Dialog d=new Dialog(this);
		d.setTitle("Hell no...!! Unable to acces the DataBase :(( ");
		TextView tv=new TextView(this);
		tv.setText(error);
		d.setContentView(tv);
		d.show();
	}
	
	}	 
 
    
    
    
    
    //sends sms
    private void sendSMS(String phoneNumber,String message)
    {
    	SmsManager sms=SmsManager.getDefault();
    	sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

	   
}

