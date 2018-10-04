package com.levqo.spaceencyclopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import junit.framework.Test;

public class TestActivity extends AppCompatActivity {

    private String mPerson_key = null;

    private DatabaseReference mDatabase;
    private DatabaseReference mDataBaseUsersActions;
    private DatabaseReference mDataBaseUsersActions1;
    private FirebaseAuth mAuth;

    private Button doneButton;
    private RadioGroup question1RadioGroup;
    private RadioGroup question2RadioGroup;
    private RadioGroup question3RadioGroup;
    private RadioGroup question4RadioGroup;

    int q2_correct_answer_int;
    int q3_correct_answer_int;

    private int testScore_q1;
    private int testScore_q2;
    private int testScore_q3;
    private ImageView mainImage;
    private TextView titleQuestion1;
    private TextView titleQuestion2;
    private TextView titleQuestion3;
    private TextView titleQuestion4;
    private TextView descriptionArticle;
    boolean getScore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDataBaseUsersActions = FirebaseDatabase.getInstance().getReference().child("UsersActions");

        mAuth = FirebaseAuth.getInstance();

        final String article_key = getIntent().getExtras().getString("article_id");

       // descriptionArticle = (TextView) findViewById(R.id.description);
       // mainImage = (ImageView) findViewById(R.id.mainImage);
        titleQuestion1 = (TextView) findViewById(R.id.question1);
        titleQuestion2 = (TextView) findViewById(R.id.question2);
        titleQuestion3 = (TextView) findViewById(R.id.question3);

        question1RadioGroup = (RadioGroup) findViewById(R.id.q1_rg);
        question2RadioGroup = (RadioGroup) findViewById(R.id.q2_rg);
        question3RadioGroup = (RadioGroup) findViewById(R.id.q3_rg);

        final RadioButton q1_radioBtn1 = (RadioButton) findViewById(R.id.q1_answer1);
        final RadioButton q1_radioBtn2 = (RadioButton) findViewById(R.id.q1_answer2);
        final RadioButton q1_radioBtn3 = (RadioButton) findViewById(R.id.q1_answer3);
        final RadioButton q1_radioBtn4 = (RadioButton) findViewById(R.id.q1_answer4);

        final RadioButton q2_radioBtn1 = (RadioButton) findViewById(R.id.q2_answer1);
        final RadioButton q2_radioBtn2 = (RadioButton) findViewById(R.id.q2_answer2);
        final RadioButton q2_radioBtn3 = (RadioButton) findViewById(R.id.q2_answer3);
        final RadioButton q2_radioBtn4 = (RadioButton) findViewById(R.id.q2_answer4);

        final RadioButton q3_radioBtn1 = (RadioButton) findViewById(R.id.q3_answer1);
        final RadioButton q3_radioBtn2 = (RadioButton) findViewById(R.id.q3_answer2);
        final RadioButton q3_radioBtn3 = (RadioButton) findViewById(R.id.q3_answer3);
        final RadioButton q3_radioBtn4 = (RadioButton) findViewById(R.id.q3_answer4);

        question1RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId) {
                    case R.id.q1_answer1:
                        testScore_q1 = 1;
                        break;
                    case R.id.q1_answer2:
                        testScore_q1 = 2;
                        break;
                    case R.id.q1_answer3:
                        testScore_q1 = 3;
                        break;
                    case R.id.q1_answer4:
                        testScore_q1 = 4;
                        break;
                }
            }
        });

        question2RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId) {
                    case R.id.q2_answer1:
                        testScore_q2 = 1;
                        break;
                    case R.id.q2_answer2:
                        testScore_q2 = 2;
                        break;
                    case R.id.q2_answer3:
                        testScore_q2 = 3;
                        break;
                    case R.id.q2_answer4:
                        testScore_q2 = 4;
                        break;
                }
            }
        });

        question3RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId) {
                    case R.id.q3_answer1:
                        testScore_q3 = 1;
                        break;
                    case R.id.q3_answer2:
                        testScore_q3 = 2;
                        break;
                    case R.id.q3_answer3:
                        testScore_q3 = 3;
                        break;
                    case R.id.q3_answer4:
                        testScore_q3 = 4;
                        break;
                }
            }
        });


        mDatabase.child("Content").child("Paragraphs").child(article_key).child("Test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                String question1title = (String) dataSnapshot.child("Question1").getValue();
                String q1_answer1 = (String) dataSnapshot.child("Q1Answer1").getValue();
                String q1_answer2 = (String) dataSnapshot.child("Q1Answer2").getValue();
                String q1_answer3 = (String) dataSnapshot.child("Q1Answer3").getValue();
                String q1_answer4 = (String) dataSnapshot.child("Q1Answer4").getValue();

                String question2title = (String) dataSnapshot.child("Question2").getValue();
                String q2_answer1 = (String) dataSnapshot.child("Q2Answer1").getValue();
                String q2_answer2 = (String) dataSnapshot.child("Q2Answer2").getValue();
                String q2_answer3 = (String) dataSnapshot.child("Q2Answer3").getValue();
                String q2_answer4 = (String) dataSnapshot.child("Q2Answer4").getValue();

                String question3title = (String) dataSnapshot.child("Question3").getValue();
                String q3_answer1 = (String) dataSnapshot.child("Q3Answer1").getValue();
                String q3_answer2 = (String) dataSnapshot.child("Q3Answer2").getValue();
                String q3_answer3 = (String) dataSnapshot.child("Q3Answer3").getValue();
                String q3_answer4 = (String) dataSnapshot.child("Q3Answer4").getValue();

                final String q1_correct_answer = (String) dataSnapshot.child("CorrectQ1").getValue();
                final String q2_correct_answer = (String) dataSnapshot.child("CorrectQ2").getValue();
                final String q3_correct_answer = (String) dataSnapshot.child("CorrectQ3").getValue();
                final String scores_award = (String) dataSnapshot.child("AwardScores").getValue();

                titleQuestion1.setText(question1title);

                q1_radioBtn1.setText(q1_answer1);
                q1_radioBtn2.setText(q1_answer2);
                q1_radioBtn3.setText(q1_answer3);
                q1_radioBtn4.setText(q1_answer4);


                if(dataSnapshot.hasChild("Question2")){

                    question2RadioGroup.setVisibility(View.VISIBLE);
                    titleQuestion2.setVisibility(View.VISIBLE);

                    titleQuestion2.setText(question2title);

                    q2_radioBtn1.setText(q2_answer1);
                    q2_radioBtn2.setText(q2_answer2);
                    q2_radioBtn3.setText(q2_answer3);
                    q2_radioBtn4.setText(q2_answer4);

                  // Toast.makeText(TestActivity.this, "AAAAAAA", Toast.LENGTH_SHORT).show();
                }

                if(dataSnapshot.hasChild("Question3")){

                    question3RadioGroup.setVisibility(View.VISIBLE);
                    titleQuestion3.setVisibility(View.VISIBLE);

                    titleQuestion3.setText(question3title);

                    q3_radioBtn1.setText(q3_answer1);
                    q3_radioBtn2.setText(q3_answer2);
                    q3_radioBtn3.setText(q3_answer3);
                    q3_radioBtn4.setText(q3_answer4);

                    // Toast.makeText(TestActivity.this, "AAAAAAA", Toast.LENGTH_SHORT).show();
                }

                final int q1_correct_answer_int = Integer.parseInt(q1_correct_answer);
                if(q2_correct_answer != null) {
                    q2_correct_answer_int = Integer.parseInt(q2_correct_answer);
                }
                if(q3_correct_answer != null) {
                    q3_correct_answer_int = Integer.parseInt(q3_correct_answer);
                }
                final int score_awards_int = Integer.parseInt(scores_award);

               // Toast.makeText(TestActivity.this, testScore_int, Toast.LENGTH_SHORT).show();

                Button doneButton = (Button) findViewById(R.id.buttonDone);
                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(dataSnapshot.hasChild("Question3") && q1_correct_answer_int == testScore_q1 && q2_correct_answer_int == testScore_q2 && q3_correct_answer_int == testScore_q3
                                || !dataSnapshot.hasChild("Question3") && dataSnapshot.hasChild("Question2") && q1_correct_answer_int == testScore_q1 && q2_correct_answer_int == testScore_q2
                                ||  !dataSnapshot.hasChild("Question2") && q1_correct_answer_int == testScore_q1) {
                            mDataBaseUsersActions.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    if(dataSnapshot.child(article_key).hasChild(mAuth.getCurrentUser().getUid())) {
                                        ImageView correct_answer_btn = (ImageView) findViewById(R.id.correct_answer);
                                      //  Glide.with(TestActivity.this).load(R.drawable.not_test).into(correct_answer_btn);
                                    }else{
                                        //Начисляем очки за правильно выполненный тест
                                        mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                            //Подключаемся к элементу базы данных, отображающего очки пользователя
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                ImageView correct_answer_btn = (ImageView) findViewById(R.id.correct_answer); //Объявляем изображение правильного ответа
                                                Glide.with(TestActivity.this).load(R.drawable.correct_answer).into(correct_answer_btn); //Загружаем это изображение
                                                String users_scores = (String) dataSnapshot.getValue(); //Получаем очки пользователя из базы данных
                                                if (getScore == false)  {
                                                    //Проверяем проходил ли пользователь тест
                                                    if (users_scores != null) {
                                                        //Проверяем есть ли уже у пользователя очки
                                                        int users_scores_int = Integer.parseInt(users_scores);
                                                        //Переводим в переменную int полученные очки
                                                        int result_users_scores = users_scores_int + score_awards_int;
                                                        //Начисляем очки пользователю
                                                        mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue((String.valueOf(result_users_scores)));
                                                        //Записываем очки пользователю
                                                        DatabaseReference scores_to_user = mDataBaseUsersActions.child(article_key);
                                                        scores_to_user.child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(score_awards_int));
                                                        //Обновляем очки пользователя
                                                        getScore = true;
                                                    } else {
                                                        mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue((String.valueOf(score_awards_int)));
                                                        //Записываем очки пользователю
                                                        DatabaseReference scores_to_user = mDataBaseUsersActions.child(article_key);
                                                        scores_to_user.child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(score_awards_int));
                                                        //Обновляем очки пользователя
                                                        getScore = true;
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }else{
                            ImageView correct_answer_btn = (ImageView) findViewById(R.id.correct_answer);
                            Glide.with(TestActivity.this).load(R.drawable.wrong_answer).into(correct_answer_btn);

                        }
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
                //Intent intent = new Intent(TestActivity.this, ArticleActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }

}
