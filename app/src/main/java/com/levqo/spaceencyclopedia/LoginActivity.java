package com.levqo.spaceencyclopedia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
    private TextView loginRegBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance(); //Подключаемся к базе данных
        loginEmailText = (EditText) findViewById(R.id.editText);
        //Объявляем текстовое поле для ввода e-mail
        loginPassText = (EditText) findViewById(R.id.editText2);
        //Объявляем текстовое поле для ввода пароля
        loginBtn = (Button) findViewById(R.id.button2);
        //Объявляем кнопку для авторизации
        loginRegBtn = (TextView) findViewById(R.id.button3);
        //Объявляем кнопку для регистрации
        ImageView backgroundImage = (ImageView) findViewById(R.id.background); //Объявляем изображение для фона
        Glide.with(this).load(R.drawable.background).into(backgroundImage); //Загружаем изображение для фона

        loginRegBtn.setOnClickListener(new View.OnClickListener() {//Обработчик нажатия на кнопку регистрации
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                //Объявлеем переход на новый экран
                startActivity(regIntent); //Переходим на новый экран
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//Обработчик нажатия на кнопку «Войти»
                String loginEmail = loginEmailText.getText().toString();
                //Записываем в стринговую переменную полученный E-mail
                String loginPass = loginPassText.getText().toString();
                //Записываем в стринговую переменную полученный пароль
                if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)){ //Проверяем на заполненность
                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           //Обрабатываем вход
                            if(task.isSuccessful()){
                                sendToMain();// Если успешно, то переходим в главное меню
                            } else {
                                Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_LONG).show();
                                //Если данные введены неверно, то выводится ошибка
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){

            sendToMain();

        }

    }

    private void sendToMain() {

        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }

}


