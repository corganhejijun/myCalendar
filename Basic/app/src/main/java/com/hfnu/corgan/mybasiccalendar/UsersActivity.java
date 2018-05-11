package com.hfnu.corgan.mybasiccalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UsersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);

        Button reg = findViewById(R.id.user_register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText confirm = findViewById(R.id.user_confirm_password);
                if (confirm.getVisibility() == View.VISIBLE){
                    EditText password = findViewById(R.id.user_passord);
                    if (password.getText().toString().equals("")){
                        Toast.makeText(UsersActivity.this, R.string.text_password_empty, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!password.getText().toString().equals(confirm.getText().toString())){
                        Toast.makeText(UsersActivity.this, R.string.text_password_confirm_failed, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = getIntent();
                    Bundle bun = new Bundle();
                    EditText username = findViewById(R.id.user_username);
                    bun.putString("username", username.getText().toString());
                    bun.putString("password", password.getText().toString());
                    intent.putExtras(bun);
                    UsersActivity.this.setResult(getResources().getInteger(R.integer.register_ok), intent);
                    UsersActivity.this.finish();
                    return;
                }
                confirm.setVisibility(View.VISIBLE);
                TextView text = findViewById(R.id.user_confirm_text);
                text.setVisibility(View.VISIBLE);
                Button btn = findViewById(R.id.user_login);
                btn.setVisibility(View.INVISIBLE);
            }
        });
        Button login = findViewById(R.id.user_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bun = new Bundle();
                EditText username = findViewById(R.id.user_username);
                EditText password = findViewById(R.id.user_passord);
                bun.putString("username", username.getText().toString());
                bun.putString("password", password.getText().toString());
                intent.putExtras(bun);
                UsersActivity.this.setResult(getResources().getInteger(R.integer.login_ok), intent);
                UsersActivity.this.finish();
            }
        });
    }
}
