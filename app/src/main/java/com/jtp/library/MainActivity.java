package com.jtp.library;

import static com.jtp.library.DataBase.AUTHOR;
import static com.jtp.library.DataBase.KEY_ID;
import static com.jtp.library.DataBase.PAGES;
import static com.jtp.library.DataBase.TITLE;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jtp.library.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    // RecyclerView Rv;
    List<Booklist> bll=new ArrayList<>();
    ImageView  ei;
    TextView tv;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ei=findViewById(R.id.empty_imageview);
        tv=findViewById(R.id.no_data);
        //sr=findViewById(R.id.book_id_txt);

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddBookActivity.class);
                startActivity(intent);

            }
        });

        storeData();
        //Adapter conection and RecyclerView
        BookAdapter bookAdapter=new BookAdapter(MainActivity.this,this,bll);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(bookAdapter);

    }


    @SuppressLint("Range")
    void storeData(){
        DataBase db=new DataBase(MainActivity.this);
        Cursor cursor=db.showdata();

        if(cursor.getCount()==0) {
            ei.setVisibility(View.VISIBLE);
             tv.setVisibility(View.VISIBLE);
        }
        else{
            if (cursor.moveToFirst()) {
                do {
                    Booklist bb = new Booklist();
                    bb.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                    bb.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                    bb.setAuthor(cursor.getString(cursor.getColumnIndex(AUTHOR)));
                    bb.setPages(cursor.getString(cursor.getColumnIndex(PAGES)));

                    bll.add(bb);
                } while (cursor.moveToNext());

                ei.setVisibility(View.GONE);
                Log.d("store data ","visibility-113");

                tv.setVisibility(View.GONE);
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete)
            confirm();
        return super.onOptionsItemSelected(item);
    }

    void confirm(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure you want to delete all Data??");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBase db=new DataBase(MainActivity.this);
                db.deleteall();

                ei.setVisibility(View.VISIBLE);
                Log.d("store data ","visibility-155");
                tv.setVisibility(View.VISIBLE);
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}