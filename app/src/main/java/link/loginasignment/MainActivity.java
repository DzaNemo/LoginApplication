package link.loginasignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    EditText editTextUsername;
    EditText editTextPassword;
    EditText editTextConfirmPassword;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        editTextUsername = findViewById(R.id.usernameReg);
        editTextPassword = findViewById(R.id.passwordReg);
        editTextConfirmPassword = findViewById(R.id.confirmPasswordReg);
        registerBtn = findViewById(R.id.registerButton);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPass = editTextConfirmPassword.getText().toString().trim();

                if (userName.equals("") || password.equals("") || confirmPass.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();
                }else{
                    if (password.equals(confirmPass)){
                        boolean checkUsername = databaseHelper.checkUsername(userName);
                        if (checkUsername){
                            boolean insert = databaseHelper.insertData(userName,password);
                            if (insert){
                                Toast.makeText(MainActivity.this, "Registration is sucssessfull", Toast.LENGTH_SHORT).show();
                                editTextUsername.setText("");
                                editTextPassword.setText("");
                                editTextConfirmPassword.setText("");
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
