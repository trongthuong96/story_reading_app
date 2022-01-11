package com.example.story_reading_app.account;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lib.interfaceRepository.Methods;
import com.example.lib.model.UserModel;
import com.example.story_reading_app.MainActivity;
import com.example.story_reading_app.R;
import com.example.story_reading_app.session.SessionUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap,btnDangKi;
    SessionUser sessionUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionUser = new SessionUser(getApplicationContext());

        AnhXa();

        /*btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManDangNhap.this,ManDangKi.class);
                startActivity(intent);
            }
        });*/
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel = new UserModel();
                userModel.setName(edtTaiKhoan.getText().toString());
                userModel.setPass(edtMatKhau.getText().toString());
                loginUser(userModel);
            }
        });
    }

    private  void AnhXa(){
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKi = findViewById(R.id.dangki);
        btnDangNhap = findViewById(R.id.dangnhap);
    }

    //check login
    private void loginUser(UserModel userModel){
        Methods methods = getRetrofit().create(Methods.class);
        Call<UserModel> call = methods.postUser(userModel);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body()!=null){
                    UserModel model = response.body();
                    sessionUser.createLoginSession(model.getName(), model.getEmail(),model.getRole());

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }
}