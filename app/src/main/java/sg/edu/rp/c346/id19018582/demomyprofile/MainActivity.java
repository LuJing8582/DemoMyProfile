package sg.edu.rp.c346.id19018582.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etGPA;
    TextView tvName;
    TextView tvGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        tvName = findViewById(R.id.textViewName);
        tvGPA = findViewById(R.id.textViewGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        //1a Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        float fltGPA = Float.parseFloat(etGPA.getText().toString());
        int chckBtn = rgGender.getCheckedRadioButtonId();


        //1b Obtain an instance of the SharedPreferences
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        //1c Obtain an instance of the SharedPreferences Editor for update later
        SharedPreferences.Editor prefEdit = pref.edit();

        //1d Add the key_value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", fltGPA);
        prefEdit.putInt("gender", chckBtn);

        //1e Call commit() to save the changes into Shared preferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //2a Obtain instance of the SharedPreferences
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        //2b Retrieve the saved data from the SharedPreferences object
        String msgName = pref.getString("name", "");
        float fltGPA = pref.getFloat("gpa", 0.0f);
        int chckBtn = pref.getInt("gender", R.id.radioButtonGenderMale);

        //2c Update the UI element with the value
        etName.setText(msgName);
        etGPA.setText(fltGPA + "");
        rgGender.check(chckBtn);

    }
}
