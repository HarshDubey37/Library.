package com.jtp.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jtp.library.databinding.ActivityAddBookBinding;

public class AddBookActivity extends AppCompatActivity {
        ActivityAddBookBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Booklist bl=new Booklist();
                String a=binding.authorInput.getText().toString();
                String t=binding.titleInput.getText().toString();
                String p= binding.pagesInput.getText().toString().trim();
                bl.setPages(p);
                bl.setAuthor(a);
                bl.setTitle(t);
                DataBase db=new DataBase(AddBookActivity.this);
                db.addBook(bl);

            }
        });


    }
}