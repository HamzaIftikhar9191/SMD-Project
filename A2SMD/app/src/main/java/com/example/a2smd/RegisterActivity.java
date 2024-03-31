package com.example.a2smd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        sharedPreferences = getSharedPreferences("com.example.a2smd.preferences", MODE_PRIVATE);

        Button addButton = findViewById(R.id.btn_add_restaurant);
        Button backButton = findViewById(R.id.btn_back);
        EditText nameEditText = findViewById(R.id.reg_name);
        EditText locationEditText = findViewById(R.id.reg_location);
        EditText phoneEditText = findViewById(R.id.reg_phone);
        EditText descriptionEditText = findViewById(R.id.reg_description);
        EditText ratingEditText = findViewById(R.id.reg_rating);

        addButton.setOnClickListener(view -> {
            // Save the entered data to SharedPreferences
            saveDataToSharedPreferences(
                    nameEditText.getText().toString(),
                    locationEditText.getText().toString(),
                    phoneEditText.getText().toString(),
                    descriptionEditText.getText().toString(),
                    ratingEditText.getText().toString()
            );

            // Set the result to indicate success and finish the activity
            setResult(RESULT_OK);
            finish();
        });

        backButton.setOnClickListener(view -> {
            // Simply finish the activity without saving data
            finish();
        });
    }

    private void saveDataToSharedPreferences(String name, String location, String phone, String description, String rating) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("location", location);
        editor.putString("phone", phone);
        editor.putString("description", description);
        editor.putString("rating", rating);
        editor.apply();
    }
}
