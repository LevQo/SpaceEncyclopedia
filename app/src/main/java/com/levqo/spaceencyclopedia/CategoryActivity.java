package com.levqo.spaceencyclopedia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    //private RecyclerAdapter adapter;

    private ImageView logOutBtn;

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String article_key = getIntent().getExtras().getString("article_id");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Content").child("Stars").child(article_key);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView backgroundImage = (ImageView) findViewById(R.id.background);
        Glide.with(this).load(R.drawable.background).into(backgroundImage);

        ImageView searchImage = (ImageView) findViewById(R.id.search);
        Glide.with(this).load(R.drawable.search).into(searchImage);

        ImageView logoutImage = (ImageView) findViewById(R.id.button_LogOut);
        Glide.with(this).load(R.drawable.logout).into(logoutImage);

        mAuth = FirebaseAuth.getInstance();

        logOutBtn = (ImageView) findViewById(R.id.button_LogOut);

        /* logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
        */
    }


    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ContentData, CategoryActivity.ContentDataViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ContentData, CategoryActivity.ContentDataViewHolder>(

                ContentData.class,
                R.layout.item_card,
                CategoryActivity.ContentDataViewHolder.class,
                mDatabase

        ){

            @Override
            protected void populateViewHolder(CategoryActivity.ContentDataViewHolder viewHolder, ContentData model, final int position) {

                final String article_key1 = getRef(position).getKey();

                // viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImage());
                //viewHolder.setWiki(model.getWiki());

                // viewHolder.setTitle(model.getPhoto());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent articleIntent = new Intent(CategoryActivity.this, ArticleActivity.class);
                        articleIntent.putExtra("article_id", article_key1);
                        startActivity(articleIntent);

                    }
                });

            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    public static class ContentDataViewHolder extends RecyclerView.ViewHolder{

        View mView;


        public ContentDataViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setName(String Name){
            //TextView name = (TextView) mView.findViewById(R.id.name);
            // name.setText(Name);
        }


        public void setWiki(String wiki){

        }

        public void setImage(Context ctx, String Image){

            ImageView imageView = (ImageView) mView.findViewById(R.id.image);

            Glide.with(ctx).load(Image).into(imageView);

        }

    }

}

