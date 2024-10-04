package com.example.form;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText, idEditText, emailEditText, phoneEditText, instituteEditText, nationalityEditText;
    private Spinner deptSpinner;
    private String name, id, email, phone, dept, institute, nationality;
    private Button submit;


    private Pattern namePattern = Pattern.compile("[a-zA-Z\\s]+");
    private Pattern idPattern = Pattern.compile("\\d{1,10}");
    private Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private Pattern phonePattern = Pattern.compile("\\d{11}"); // Phone: 10 digits
    private Pattern institutePattern = Pattern.compile("[a-zA-Z\\s]+");
    private Pattern nationalityPattern = Pattern.compile("[a-zA-Z\\s]+");

    LinearLayout inputLayout, outputLayout;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        nameEditText = findViewById(R.id.name);
        idEditText = findViewById(R.id.sId);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.num);
        instituteEditText = findViewById(R.id.institute);
        nationalityEditText = findViewById(R.id.nationality);
        deptSpinner = findViewById(R.id.spinner);
        submit = findViewById(R.id.submit);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);


        String[] items = new String[]{ "Your Future Plan", "Software Engineer", "App Development", "AI Engineer", "Data Science", "Web Development", "UX Designer", "IT Project Manager", "Game Developer"};
        deptSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept = deptSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString();
                id = idEditText.getText().toString();
                email = emailEditText.getText().toString();
                phone = phoneEditText.getText().toString();
                institute = instituteEditText.getText().toString();
                nationality = nationalityEditText.getText().toString();


                if (name.isEmpty()) {
                    nameEditText.setError("Empty!");
                    nameEditText.requestFocus();
                } else if (!namePattern.matcher(name).matches()) {
                    nameEditText.setError("Name can only contain letters and spaces");
                    nameEditText.requestFocus();
                } else if (id.isEmpty()) {
                    idEditText.setError("Empty!");
                    idEditText.requestFocus();
                } else if (!idPattern.matcher(id).matches()) {
                    idEditText.setError("ID must be 1 to 10 digits.");
                    idEditText.requestFocus();
                } else if (email.isEmpty()) {
                    emailEditText.setError("Empty!");
                    emailEditText.requestFocus();
                } else if (!emailPattern.matcher(email).matches()) {
                    emailEditText.setError("Enter a valid email address.");
                    emailEditText.requestFocus();
                } else if (phone.isEmpty()) {
                    phoneEditText.setError("Empty!");
                    phoneEditText.requestFocus();
                } else if (!phonePattern.matcher(phone).matches()) {
                    phoneEditText.setError("Mobile number must be 11 digits.");
                    phoneEditText.requestFocus();
                } else if (institute.isEmpty()) {
                    instituteEditText.setError("Empty!");
                    instituteEditText.requestFocus();
                } else if (!institutePattern.matcher(institute).matches()) {
                    instituteEditText.setError("Institute can only contain letters and spaces");
                    instituteEditText.requestFocus();
                } else if (nationality.isEmpty()) {
                    nationalityEditText.setError("Empty!");
                    nationalityEditText.requestFocus();
                } else if (!nationalityPattern.matcher(nationality).matches()) {
                    nationalityEditText.setError("Nationality can only contain letters and spaces");
                    nationalityEditText.requestFocus();
                } else if (Objects.equals(dept, "Choose Your Plan")) {
                    Toast.makeText(getApplicationContext(), "Please Select Your Future Plan", Toast.LENGTH_SHORT).show();
                } else {

                    inputLayout.setVisibility(View.GONE);
                    outputLayout.setVisibility(View.VISIBLE);
                    String output = "Name: " + name + "\nID: " + id + "\nEmail: " + email + "\nMobile Number: " + phone
                            + "\nInstitute: " + institute + "\nNationality: " + nationality + "\nDepartment: " + dept;
                    outputText.setText(output);
                }
            }
        });
    }
}
