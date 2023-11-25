package com.jtp.library;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="lIBRARY.db";
    public static final String TABLE_NAME="book_table";
    public static final String KEY_ID="id";
    public static final String TITLE="title";
    public static final String AUTHOR="athorname";
    public static final String PAGES="pages";
    Context context;
      public DataBase(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
        this.context=context;

    }

    String CREATE_TABLE_BOOK="CREATE TABLE " + TABLE_NAME + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + TITLE + " TEXT, " + AUTHOR + " TEXT, " +
            PAGES + " INTEGER " + " )";

    @Override
    public void onCreate(SQLiteDatabase sqdb) {
            sqdb.execSQL(CREATE_TABLE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqdb, int i, int i1) {
        sqdb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqdb);

    }

    @SuppressLint("Range")
    Cursor showdata(){

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=null;
        if(sqLiteDatabase != null)
            cursor= sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        return cursor;

    }

    void addBook(Booklist bk){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TITLE,bk.getTitle());
        cv.put(AUTHOR,bk.getAuthor());
        cv.put(PAGES,bk.getPages());

        long id=db.insert(TABLE_NAME,null,cv);
        if (id==-1)
            Toast.makeText(context,"Failed to Add Book",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context,"Book Added Successfully!!",Toast.LENGTH_LONG).show();
    }

    void updatebook(Booklist bk){
        SQLiteDatabase sq=getWritableDatabase();
        ContentValues cv=new ContentValues();
        Log.d("getsetintent","update start in db id= "+bk.getId());
        cv.put(KEY_ID,bk.getId());
        cv.put(TITLE,bk.getTitle());
        cv.put(AUTHOR,bk.getAuthor());
        cv.put(PAGES,bk.getPages());
        Log.d("getsetintent","update done in db id= "+bk.getId());

       long r= sq.update(TABLE_NAME,cv,KEY_ID + " LIKE ? ",new String[]{String.valueOf(bk.getId())});

       if (r==-1)
           Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
       else
           Toast.makeText(context,"Update Success!!",Toast.LENGTH_SHORT).show();
    }

    public void deleteOne(int id){
        SQLiteDatabase sq=getWritableDatabase();
        long r=sq.delete(TABLE_NAME,KEY_ID + " LIKE?",new String[]{String.valueOf(id)});
        if (r==-1)
            Toast.makeText(context,"Failed to Delete ",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"Deleted!!",Toast.LENGTH_SHORT).show();
    }
    void deleteall(){
        SQLiteDatabase sq=getWritableDatabase();
        sq.execSQL("DELETE FROM "+ TABLE_NAME);
    }
}
