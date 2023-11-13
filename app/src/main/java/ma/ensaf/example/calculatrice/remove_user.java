package ma.ensaf.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class remove_user extends AppCompatActivity {
    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;
    private boolean clicked = false;
    private FloatingActionButton add_btn;
    private FloatingActionButton addUser_btn;
    private EditText loginEditText;
    private Button removeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);

        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);


        add_btn = findViewById(R.id.button_Add);
        addUser_btn = findViewById(R.id.button_AddUser);

        loginEditText = findViewById(R.id.loginEditText);
        removeButton = findViewById(R.id.button_suppUser);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });

        addUser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(remove_user.this, addUser.class);

                // Démarrez l'activité
                startActivity(intent);            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEditText.getText().toString().trim();

                if (!login.isEmpty()) {
                    DBHelper dbHelper = new DBHelper(remove_user.this);
                    dbHelper.removeUser(login);
                    Toast.makeText(remove_user.this, "User removed successfully", Toast.LENGTH_SHORT).show();
                    loginEditText.getText().clear();
                } else {
                    Toast.makeText(remove_user.this, "Please enter a login", Toast.LENGTH_SHORT).show();
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
        } else {
            addUser_btn.setVisibility(View.INVISIBLE);
        }
    }
    private void setAnimation(boolean clicked) {
        if (!clicked) {
            addUser_btn.startAnimation(fromBottom);
            add_btn.startAnimation(rotateOpen);
        } else {
            addUser_btn.startAnimation(toBottom);
            add_btn.startAnimation(rotateClose);
        }
    }
}