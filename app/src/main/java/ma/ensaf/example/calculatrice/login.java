package ma.ensaf.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class login extends AppCompatActivity {
    EditText loginInput, passwordInput;
    Button addButton;
    DBHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginInput = findViewById(R.id.logLayout);
        passwordInput = findViewById(R.id.passLayout);
        addButton = findViewById(R.id.LoginBtn);

        databaseHelper = new DBHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = loginInput.getText().toString();
                String password = passwordInput.getText().toString();

                if(login.equals("") || password.equals(""))
                    Toast.makeText(login.this, "login or password empty.Try again", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCredentials = databaseHelper.checkPassword(login, password);
                    if(checkCredentials == true){
                        Toast.makeText(login.this, "Bienvenue!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(login.this, "Lo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}