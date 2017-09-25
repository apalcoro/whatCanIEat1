package com.coral.whatcanieat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.backendless.Backendless;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Backendless.initApp(getApplicationContext(), "3C11FBA4-37D9-1C27-FFA2-E4C550999300","D9612CE6-8D51-E97E-FFE2-A1FD23B6F000");
    }


    public void Sign(View v) {
        startActivity(new Intent(this,login.class));
    }

    public void menu(View v) {
        startActivity( new Intent(this,Menu.class));
    }
}
