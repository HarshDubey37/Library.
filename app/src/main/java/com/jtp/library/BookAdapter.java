package com.jtp.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jtp.library.databinding.ItemBookBinding;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context context;
    Activity activity;
    List<Booklist> booklist;
    Animation am;
    int i=0;
    MainActivity m=new MainActivity();
    public BookAdapter(Activity activity,Context context,List<Booklist> booklist){
        this.context=context;
        this.activity=activity;
        this.booklist=booklist;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        ItemBookBinding binding=ItemBookBinding.inflate(LayoutInflater.from(context));
        return new BookViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
         Booklist bl=booklist.get(position);
         holder.title.setText(bl.getTitle());
         holder.author.setText(bl.getAuthor());
         holder.pages.setText(bl.getPages());

        Log.d("Holder","get id"+bl.getId());
        // holder.idd.setText(Integer.toString(bl.getId()));
         holder.idd.setText(Integer.toString(++i));

         holder.ll.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(context,UpdateActivity.class);
                 intent.putExtra("id",bl.getId());
                 intent.putExtra("title",bl.getTitle());
                 intent.putExtra("author",bl.getAuthor());
                 intent.putExtra("pages",bl.getPages());
                 Log.d("getsetintent","before ll click btn id= "+bl.getId());
                    activity.startActivityForResult(intent,1);
                 Log.d("getsetintent","lll btn after click id= "+bl.getId());
             }
         });
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView author;
        TextView pages;
        TextView idd;
        LinearLayout ll;
        LinearLayout spll;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.book_title_txt);
            author=itemView.findViewById(R.id.book_author_txt);
            pages=itemView.findViewById(R.id.book_pages_txt);
            idd=itemView.findViewById(R.id.book_id_txt);
            ll=itemView.findViewById(R.id.mainLayout);
            //animation
            am= AnimationUtils.loadAnimation(context,R.anim.movedata);
            ll.setAnimation(am);
        }
    }
}
