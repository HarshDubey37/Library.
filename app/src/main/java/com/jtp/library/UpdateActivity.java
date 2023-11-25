package com.jtp.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jtp.library.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {
        ActivityUpdateBinding binding;
        EditText title,author,pages;
        DataBase db;
        String t,a,p;
    Booklist bk=new Booklist();
        int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        title=findViewById(R.id.title_inputUP);
        author=findViewById(R.id.author_inputUP);
        pages=findViewById(R.id.pages_inputUP);

        getandsetdata();

        ActionBar ab=getSupportActionBar();
        if(ab!=null)
            ab.setTitle(t);

        binding.updbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t=title.getText().toString();
                a=author.getText().toString();
                p=pages.getText().toString();
                Log.d("getsetintent","update btn id= "+id);
                Log.d("getsetintent","update btn ti= "+t);
                Log.d("getsetintent","update btn au= "+a);
                Log.d("getsetintent","update btn pa= "+p);


                bk.setTitle(t);
                bk.setId(id);
                bk.setAuthor(a);
                bk.setPages(p);
                DataBase db=new DataBase(UpdateActivity.this);
                db.updatebook(bk);

                finish();
            }
        });

        binding.delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    confirm();
            }
        });

    }

    void getandsetdata(){
        if (getIntent().hasExtra("id")&&getIntent().hasExtra("title")&&getIntent().hasExtra("author")&&getIntent().hasExtra("pages")){

            id=getIntent().getIntExtra("id",-1);
            Log.d("getsetintent","update id= "+id);
            t=getIntent().getStringExtra("title");
            a=getIntent().getStringExtra("author");
            p=getIntent().getStringExtra("pages");
            Log.d("getsetintent","update id= "+id);
            Log.d("getsetintent","update ti= "+t);
            Log.d("getsetintent","update au= "+a);
            Log.d("getsetintent","update pa= "+p);


            title.setText(t);
            author.setText(a);
            pages.setText(p);

        }
        else
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
    }
    void confirm(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete " + t+" ??");
        builder.setMessage("Are you sure you want to delete "+ t+" ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBase db=new DataBase(UpdateActivity.this);
                db.deleteOne(id);

                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create().show();
    }
}