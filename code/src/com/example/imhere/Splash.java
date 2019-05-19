package com.example.imhere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		/*Thread to sleep the splash screen for few sec  and then the homescreen*/
		Thread timer=new Thread(){
			public void run(){
			try{
				sleep(2000);
			} catch (InterruptedException e){
				e.printStackTrace();
			}finally{
				Intent openMain=new Intent("com.example.imhere.MENU");
				startActivity(openMain);
			}
		}
		};
		timer.start();
	
	}
	}
