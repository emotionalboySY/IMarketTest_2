package com.serahaeyum.imarkettest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Start_Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, SplashActivity.class));
        setContentView(R.layout.start_login);
    }

    public void onClick_Login(View v) {

        EditText text_id = (EditText) findViewById(R.id.login_id);
        EditText text_pw = (EditText) findViewById(R.id.login_pw);

        String id = text_id.getText().toString();
        String pw = text_pw.getText().toString();

        Intent login = new Intent(getApplicationContext(), MainActivity.class);

        login.putExtra("id", id);
        login.putExtra("pw", pw);

        startActivity(login);
    }
}
