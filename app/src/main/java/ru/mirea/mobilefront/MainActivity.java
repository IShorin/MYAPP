package ru.mirea.mobilefront;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import lombok.SneakyThrows;

import ru.mirea.mobilefront.dto.LoginFormDto;
import ru.mirea.mobilefront.dto.RoleEnum;
import ru.mirea.mobilefront.dto.UserDto;
import ru.mirea.mobilefront.service.AuthService;
import ru.mirea.mobilefront.service.UserService;
import ru.mirea.mobilefront.service.UserSession;

public class MainActivity extends AppCompatActivity {
    Button testforanim; //переменная для id кнопки (для анимации)
    Button loginButton;
    EditText loginField;
    EditText passwordField;

    UserService userService = new UserService();
    AuthService authService = new AuthService();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Intent intent = new Intent(this, MenuActivity.class);
        //startActivity(intent);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testforanim=(Button)findViewById(R.id.switch_remember);//получаем id кнопочки
        testforanim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation Bounce = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein); //лучаем анимацию
                testforanim.startAnimation(Bounce);//Запуск анимации кнопки
                LoginFormDto dto = null;
                dto = authService.test();
                //changeViewColor(view);//вызов метода снизу
            }
        });


        //уведомление ошибки логина
        TextView errorTextLogin = (TextView) findViewById(R.id.login_error_text);

        //Логин юзера
        loginField=(EditText)findViewById(R.id.login_text);
        passwordField=(EditText)findViewById(R.id.password_text);
        loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authService.login(loginField.getText().toString()
                        ,passwordField.getText().toString());
              //  Здесь врубается кнопка логина, загрузочка все такое ее надо будет вырубить
                //  через секунд 10 в случае безуспешности логина и все такое
            }
        });



        //Подписка на изменение токена
        Intent intent = new Intent(this, MenuActivity.class); //переключение на другой активити
        MutableLiveData<String> liveData = AuthService.getLiveData();
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String userToken) {
                if (userToken.equals("Logout")){
                    return;
                }
                if (userToken.equals("errorLogin")){
                    //Вывести ошибочку логина на экран
                    errorTextLogin.setVisibility(View.VISIBLE);
                    return;
                }
                try {
                    errorTextLogin.setVisibility(View.INVISIBLE);
                    userService.getUserData(userToken);
                    Log.d("auth", "login success");
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                    Log.d("error", "Server userCheck problem"); //вывод текста об ошибке логина
                }
            }
        });
    }
}