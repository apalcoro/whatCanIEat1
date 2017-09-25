package com.coral.whatcanieat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class SignUp extends AppCompatActivity {
    EditText email;
    EditText pass;
    EditText name;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        name = (EditText) findViewById(R.id.name);
        signup = (Button) findViewById(R.id.signup);
        //
        Backendless.initApp(this,
                Defaults.APPLICATION_ID,
                Defaults.API_KEY);

    }

    public void CreateAccount(View view) {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BackendlessUser user = new BackendlessUser();
                user.setProperty("email", email.getText().toString());
                user.setProperty("name", name.getText().toString());
                user.setPassword(pass.getText().toString());
                Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                    public void handleResponse(BackendlessUser registeredUser) {
                        Toast.makeText(SignUp.this, "Registration Complete", Toast.LENGTH_LONG).show();
                    }

                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(SignUp.this, "Fail to register", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}


