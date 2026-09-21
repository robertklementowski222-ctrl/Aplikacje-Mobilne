package com.example.projekt;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editSurname;
    private EditText editEmail;
    private EditText editPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        editName = findViewById(R.id.name);
        editSurname = findViewById(R.id.secname);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.button);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnRegister.setOnClickListener(v -> {
            if (!checkValues()) return;
            if (!checkEmail()) return;
            if (!checkPassword()) return;

            Toast.makeText(this, "Dane są poprawne!", Toast.LENGTH_SHORT).show();
        });
    }


    private boolean checkValues() {
        String name = editName.getText().toString().trim();
        String surname = editSurname.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Uzupełnij pola", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private boolean checkEmail() {
        String email = editEmail.getText().toString().trim();
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!Pattern.matches(emailPattern, email)) {
            Toast.makeText(this, "Wpisz poprawny email!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private boolean checkPassword() {
        String password = editPassword.getText().toString().trim();
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$";

        if (!Pattern.matches(passwordPattern, password)) {
            Toast.makeText(this, "Hasło musi mieć min. 8 znaków, 1 małą, 1 dużą literę i 1 znak specjalny", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
