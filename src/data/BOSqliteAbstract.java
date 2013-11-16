package data;

import tools.sqlite.DatabaseHelper;
import android.R.integer;
import android.R.string;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//数据层抽象类，主要实现数据库的创建和各种数据库命令的封装
abstract public class BOSqliteAbstract {
	
	public DatabaseHelper dbHelper;
	
	public SQLiteDatabase db;
	
	public int db_type = 0;
	
	public final int DB_READ = 1;
	public final int DB_WRITE = 2;
	
	
	//表名
	public String query = "";
	
	//构造函数
	public void BOSqliteAbstract(Context context) throws Exception{
		Log.i("数据基类","数据基类");
		if(query!=""){
			//创建一个DatabaseHelper对象
			DatabaseHelper dbHelper = new DatabaseHelper(context,query);
		}else {
			throw new Exception("invalid table name");
		}
		//读写分开
		switch (db_type) {
		case DB_READ:
			//只有调用了DatabaseHelper对象的getReadableDatabase()方法，或者是getWritableDatabase()方法之后，才会创建，或打开一个数据库
			db = dbHelper.getReadableDatabase();
			break;
		case DB_WRITE:
			db = dbHelper.getWritableDatabase();
			break;
		default:
			throw new Exception("invalue db type");
		}
		
	}
	
}