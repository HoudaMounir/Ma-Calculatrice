package ma.ensaf.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class addUser extends AppCompatActivity {
    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;
    private boolean clicked = false;
    private FloatingActionButton add_btn;
    private FloatingActionButton removeUser_btn;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);

        add_btn = findViewById(R.id.button_Add);
        removeUser_btn = findViewById(R.id.button_RemoveUser);

        mydb = new DBHelper(this);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });

        removeUser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addUser.this, remove_user.class);
                startActivity(intent);
            }
        });

        Button addButton = findViewById(R.id.button_ajoutUser);
        EditText loginEditText = findViewById(R.id.loginEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText nomEditText = findViewById(R.id.nomEditText);
        EditText prenomEditText = findViewById(R.id.prenomEditText);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String nom = nomEditText.getText().toString().trim();
                String prenom = prenomEditText.getText().toString().trim();

                try {
                    mydb.addUser(login, password, nom, prenom);
                    mydb.close();
                    Toast.makeText(addUser.this, "User added successfully", Toast.LENGTH_SHORT).show();
                    loginEditText.getText().clear();
                    passwordEditText.getText().clear();
                    nomEditText.getText().clear();
                    prenomEditText.getText().clear();
                } catch (SQLiteException e) {
                    Toast.makeText(addUser.this, "Failed to add user. User may already exist.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(addUser.this, "Failed to add user. An error occurred.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }
    private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked;
    }
    private void setVisibility(boolean clicked) {
        if (!clicked) {
            removeUser_btn.setVisibility(View.VISIBLE);
        } else {
            removeUser_btn.setVisibility(View.INVISIBLE);
        }
    }
    private void setAnimation(boolean clicked) {
        if (!clicked) {
            removeUser_btn.startAnimation(fromBottom);
            add_btn.startAnimation(rotateOpen);
        } else {
            removeUser_btn.startAnimation(toBottom);
            add_btn.startAnimation(rotateClose);
        }
    }

}