package link.loginasignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    EditText loginName, loginPass;
    Button loginBtn,regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        loginName = findViewById(R.id.loginUsername);
        loginPass = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginButton);
        regBtn = findViewById(R.id.registerHereBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameLogin = loginName.getText().toString().trim();
                String passwordLogin = loginPass.getText().toString().trim();

                boolean checkLogin = databaseHelper.checkLogin(usernameLogin,passwordLogin);
                if (checkLogin){
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    loginName.setText("");
                    loginPass.setText("");
                }else{
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
