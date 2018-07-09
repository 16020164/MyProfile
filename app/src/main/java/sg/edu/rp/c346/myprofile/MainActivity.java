package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvGPA;
    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.textViewName);
        tvGPA = findViewById(R.id.textViewGPA);
        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);

    }

    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        Float fgpa = Float.parseFloat(etGPA.getText().toString());

        //Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1c: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //Step 1d: Add the key-value pair
        prefEdit.putString("Name", strName);
        prefEdit.putFloat("GPA", fgpa);
        prefEdit.putInt("gender", rgGender.getCheckedRadioButtonId());


        //Step 1e: call commit() method to save the changes into the SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data from the SharedPreferences object
        String name = prefs.getString("Name", "invalid name!");
        Float gpa = prefs.getFloat("GPA", 0);
        Integer gender = prefs.getInt("gender", 0);

        etName.setText(name);
        etGPA.setText(gpa.toString());
        rgGender.check(gender);

        //Step 2c: Update the UI element with the value
        Toast toast = Toast.makeText(getApplicationContext(), name + " " + gpa, Toast.LENGTH_LONG);
        toast.show();

    }
}
