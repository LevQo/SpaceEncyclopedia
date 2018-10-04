package com.levqo.spaceencyclopedia;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;
import org.w3c.dom.Text;

public class ArticleActivity extends AppCompatActivity {

    private String mPerson_key = null;

    private DatabaseReference mDatabase;
    private DatabaseReference mDataBaseUsersActions;
    private DatabaseReference mDataBaseUsersActions1;
    private FirebaseAuth mAuth;

    //private ImageView mainImage;
    private TextView titleArticle;
   // private TextView descriptionArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        //Подключаем базу данных

        final String article_key = getIntent().getExtras().getString("article_id");
        //Получаем уникальный ключ объекта, полученный из главного меню

        final HtmlTextView descriptionArticle = (HtmlTextView) findViewById(R.id.description);
        //Иниализируем объект для вывода текста из БД
        titleArticle = (TextView) findViewById(R.id.title); //Иниализируем заголовок

        mDatabase.child("Content").child("Paragraphs").child(article_key).addValueEventListener(new ValueEventListener() {
            //Подключаемся к нужному элементу базы данных
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String title = (String) dataSnapshot.child("Name").getValue();
                // Получаем в стринговую переменную поле Name из БД
                String description = (String) dataSnapshot.child("Description").getValue();
                // Получаем в стринговую переменную поле Description из БД

                titleArticle.setText(title);
                //Выводим полученный заголовок
                descriptionArticle.setHtml(description,
                        new HtmlHttpImageGetter(descriptionArticle));
                //Выводим полученный текст парагафа
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Content").child("Paragraphs").child(article_key).child("Test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String awardScore = (String) dataSnapshot.child("AwardScores").getValue();

                TextView testScore = (TextView) findViewById(R.id.test);
                testScore.setText("Тест:" + " " + awardScore);

                testScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent testIntent = new Intent(ArticleActivity.this, TestActivity.class);
                        testIntent.putExtra("article_id", article_key);
                        startActivity(testIntent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        ImageView backgroundImage = (ImageView) findViewById(R.id.background);
        Glide.with(this).load(R.drawable.background).into(backgroundImage);

        ImageView backImage = (ImageView) findViewById(R.id.back_button);
        Glide.with(this).load(R.drawable.left_arrow).into(backImage);



        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticleActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


}

