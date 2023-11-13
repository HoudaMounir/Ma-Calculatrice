package ma.ensaf.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class login extends AppCompatActivity {
    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;
    private boolean clicked = false;
    private FloatingActionButton add_btn;
    private FloatingActionButton addUser_btn;
    private FloatingActionButton removeUser_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialisation des animations
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);


        add_btn = findViewById(R.id.button_Add);
        addUser_btn = findViewById(R.id.button_AddUser);
        removeUser_btn = findViewById(R.id.button_RemoveUser);

        EditText loginEditText = findViewById(R.id.login_email);
        EditText passwordEditText = findViewById(R.id.login_password);
        Button loginButton = findViewById(R.id.login_button);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });

        addUser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, addUser.class);
                startActivity(intent);
            }
        });

        removeUser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, remove_user.class);

                // Démarrez l'activité
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)) {
                    Toast.makeText(login.this, "Login or password empty. Try again.", Toast.LENGTH_SHORT).show();
                } else {
                    if (validateCredentials(login, password)) {
                        Toast.makeText(login.this, "Welcome " + getFullName(login), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(login.this, "Login or password incorrect. Try again.", Toast.LENGTH_SHORT).show();
                    }
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
            addUser_btn.setVisibility(View.VISIBLE);
            removeUser_btn.setVisibility(View.VISIBLE);
        } else {
            addUser_btn.setVisibility(View.INVISIBLE);
            removeUser_btn.setVisibility(View.INVISIBLE);
        }
    }
    private void setAnimation(boolean clicked) {
        if (!clicked) {
            addUser_btn.startAnimation(fromBottom);
            removeUser_btn.startAnimation(fromBottom);
            add_btn.startAnimation(rotateOpen);
        } else {
            addUser_btn.startAnimation(toBottom);
            removeUser_btn.startAnimation(toBottom);
            add_btn.startAnimation(rotateClose);
        }
    }

    private boolean validateCredentials(String login, String password) {
        DBHelper dbHelper = new DBHelper(login.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM user WHERE login = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{login, password});

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }

    private String getFullName(String login) {
        DBHelper dbHelper = new DBHelper(login.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Effectuez la recherche du nom complet dans votre base de données
        String query = "SELECT nom, prenom FROM user WHERE login = ?";
        Cursor cursor = db.rawQuery(query, new String[]{login});

        String fullName = "";

        if (cursor.moveToFirst()) {
            int nomIndex = cursor.getColumnIndex("nom");
            int prenomIndex = cursor.getColumnIndex("prenom");

            if (nomIndex != -1 && prenomIndex != -1) {
                String nom = cursor.getString(nomIndex);
                String prenom = cursor.getString(prenomIndex);
                fullName = nom + " " + prenom;
            }
        }
        cursor.close();
        db.close();

        return fullName;
    }
}