package com.levqo.spaceencyclopedia;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    //private RecyclerAdapter adapter;

    private ImageView logOutBtn;

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Content").child("Paragraphs");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView backgroundImage = (ImageView) findViewById(R.id.background);
        Glide.with(this).load(R.drawable.background).into(backgroundImage);

        ImageView searchImage = (ImageView) findViewById(R.id.search);
        Glide.with(this).load(R.drawable.solar_system_btn).into(searchImage);

        ImageView logoutImage = (ImageView) findViewById(R.id.button_LogOut);
        Glide.with(this).load(R.drawable.logout).into(logoutImage);

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent solarSystem = new Intent(MainActivity.this, SolarSystemActivity.class);
               // testIntent.putExtra("article_id", article_key);
                startActivity(solarSystem);
            }
        });


        mAuth = FirebaseAuth.getInstance();

        logOutBtn = (ImageView) findViewById(R.id.button_LogOut);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();

            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if(currentUser == null) {
                sendToLogin();
            }
        //Выводим список параграфов
        Query query = mDatabase.orderByChild("name");
            //Сортируем список по полю базы данных name
        FirebaseRecyclerAdapter<ContentData, ContentDataViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ContentData, ContentDataViewHolder>(
                //Инициализируем объект FirebaseRecyclerAdapter для вывода в него объектов из базы данных
                ContentData.class,
                R.layout.item_card,
                ContentDataViewHolder.class,
                query

        ){
            @Override
            protected void populateViewHolder(ContentDataViewHolder viewHolder, ContentData model, final int position) {
                //Обрабатываем отдельынй элемент списка
                final String article_key = getRef(position).getKey();
                //Получаем уникальный ключ каждого объекта базы данных
                viewHolder.setImage(getApplicationContext(), model.getImage());
                //Получаем изображения
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Обрабатываем нажатие на элемент списка
                       Intent articleIntent = new Intent(MainActivity.this, ArticleActivity.class);
                       //Объявляем переход на нвоый экран
                       articleIntent.putExtra("article_id", article_key);
                       //Передаем уникальный ключ объекта на другой экран
                       startActivity(articleIntent);
                       //Переходимна новый экран
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

        public void setImage(Context ctx, String Image){

            ImageView imageView = (ImageView) mView.findViewById(R.id.image);

              Glide.with(ctx).load(Image).into(imageView);

        }

    }

    private void logOut() {

        mAuth.signOut();
        sendToLogin();

    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();

    }

}
