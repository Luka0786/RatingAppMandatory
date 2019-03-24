package com.example.mandatorycourserating;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button btnLogin;
    SharedPreferences sharedPreferences;
    String usernamePrefs, passwordPrefs, usernameInput, passwordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        sharedPrefSetup();
    }

    public void init(){
        btnLogin = findViewById(R.id.btn_login);
        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
    }

    public void sharedPrefSetup() {
        sharedPreferences = getSharedPreferences(getString(R.string.MY_PREFS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.USERNAME_KEY), getString(R.string.adminUsername));
        editor.putString(getString(R.string.PASSWORD_KEY), getString(R.string.adminPassword));
        editor.commit();
    }

    public void login(View view){
        Intent loginIntent = new Intent(this, MenuActivity.class);
        SharedPreferences sharedPreferencesLogin = this.getSharedPreferences(getString(R.string.MY_PREFS),Context.MODE_PRIVATE);

        usernameInput = editTextUsername.getText().toString();
        passwordInput = editTextPassword.getText().toString();

        usernamePrefs = sharedPreferencesLogin.getString(getString(R.string.USERNAME_KEY), null);
        passwordPrefs = sharedPreferencesLogin.getString(getString(R.string.PASSWORD_KEY), null);

        validateLogin(usernameInput, passwordInput, usernamePrefs, passwordPrefs, loginIntent);
    }

    public void validateLogin(String username, String password, String correctUsername, String correctPassword, Intent loginIntent) {
        if (username.equals(correctUsername) && password.equals(correctPassword)) {
            Toast.makeText(this, getString(R.string.login_message), Toast.LENGTH_SHORT).show();
            startActivity(loginIntent);
        }
        else if (!username.equals(correctUsername) && password.equals(correctPassword)){
            Toast.makeText(this, getString(R.string.loginWrongUsername), Toast.LENGTH_LONG).show();
        }
        else if (username.equals(correctUsername) && !password.equals(correctPassword)) {
            Toast.makeText(this, getString(R.string.loginWrongPassword), Toast.LENGTH_LONG).show();
        }
        else if (username.length() == 0 && password.length() == 0) {
            Toast.makeText(this, getString(R.string.loginNoInput), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, getString(R.string.loginWrongCredentials), Toast.LENGTH_LONG).show();
        }
    }
}
