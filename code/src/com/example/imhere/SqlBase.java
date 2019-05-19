package com.example.imhere;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlBase {
  
	public static final String KEY_ROWID="_id";
	public static final String KEY_CONTACT="emergency_contact";
	public static final String KEY_MESSAGE="emergency_message";
	
	private static final String DATABASE_NAME="sos";
	private static final String DATABASE_TABLE="sosTable";
	private static final int DATABASE_VERSION=1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context,DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE +" (" +
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_CONTACT+" TEXT NOT NULL, " +
			KEY_MESSAGE+" TEXT NOT NULL);"
		);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	//..........constructor........
	public SqlBase(Context c){
		ourContext=c;
	}
	
	// ...open Database....
	public SqlBase open() throws SQLException{
		ourHelper=new DbHelper(ourContext);
		ourDatabase=ourHelper.getWritableDatabase();
		return this;
	}
	
	// ...Close Databse...
	public void close(){
		ourHelper.close();	
	}

	public long createEntry(String contact, String message) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(KEY_CONTACT, contact);
		cv.put(KEY_MESSAGE, message);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
		}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ROWID, KEY_CONTACT, KEY_MESSAGE};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result="";
		
		int iRow=c.getColumnIndex(KEY_ROWID);
		int iContact=c.getColumnIndex(KEY_CONTACT);
		int iMessage=c.getColumnIndex(KEY_MESSAGE);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			result=result + c.getString(iRow) + "  " + c.getString(iContact) + "    " + c.getString(iMessage) +"\n";
		}
		
		return result;
	}

	public String getNo (long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ROWID, KEY_CONTACT, KEY_MESSAGE};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns,null, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
		String num=c.getString(1);// 1 to represent second column as column numbering starts with 0
		return num;
		}
		return null;
	}

	public String getMsg(long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ROWID, KEY_CONTACT, KEY_MESSAGE};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
		String msg=c.getString(2);// 2 to represent third column as column numbering starts with 0
		return msg;
		}
		return null;
	}

	public void deleteEntry(long l) throws SQLException{
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + l,null);
		
		
	}
	
	}