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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText regEmailField;
    private EditText regPassField;
    private EditText regConfirmPassField;
    private Button regBtn;
    private TextView regLoginBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();//Подключаемся к базе данных
        regEmailField = (EditText) findViewById(R.id.editText4);//Объявляем поле e-mail
        regPassField = (EditText) findViewById(R.id.editText5);//Объявляем поле пароля
        regConfirmPassField = (EditText) findViewById(R.id.editText6);//Объявляем поле потдверждения пароля
        regBtn = (Button) findViewById(R.id.button5);//Объявляем кнопку регистрации
        regLoginBtn = (TextView) findViewById(R.id.button6);//Объявляем кнопку перехода к авторизации
        ImageView backgroundImage = (ImageView) findViewById(R.id.background);
        //Объявляем изображение для фона
        Glide.with(this).load(R.drawable.background).into(backgroundImage);
        //Загружаем изображение для фона

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Обработчик нажатия на кнопку регистрации
                String email = regEmailField.getText().toString();
                //Записываем в стринговую переменную введенный E-mail
                String password = regPassField.getText().toString();
                //Записываем в стринговую переменную введенный пароль
                String confirmPassword = regConfirmPassField.getText().toString();
                //Записываем в стринговую переменную подтвержденный пароль
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)){
                    //Проверяем заполненность
                    if(password.equals(confirmPassword)){
                        //Проверяем, равен ли пароль подтвержденному паролю
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this , new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Выоплняем регистрацию
                                if(task.isSuccessful()){
                                    sendToMain();
                                    //Если успешно, то переходим в главное меню
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Ошибка регистрации", Toast.LENGTH_LONG);
                                    //Если не успешно, то выводим ошибку
                                }
                            }
                        });
                    }
                }
            }
        });

        regLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();

        //FirebaseUser currentUser = mAuth.getCurrentUser();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            sendToMain();

        }

    }

    private void sendToMain() {

        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }

}
