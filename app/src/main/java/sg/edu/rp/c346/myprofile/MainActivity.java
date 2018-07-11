package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
//        int idGender = rgGender.getCheckedRadioButtonId();

    }

    @Override
    protected void onPause() {
        super.onPause();

        //step 1a: Get the user input from the EditText and store it in in var
        String strName = etName.getText().toString();
        String strGPA = etGPA.getText().toString();
        float gpa = Float.parseFloat(strGPA);
        int idGender = rgGender.getCheckedRadioButtonId();


        //step 1b : obtain an instance of the sharedpreference editor for update later
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        // step 1c: Add thekey-value pair
        prefEdit.putString("Name",strName);
        prefEdit.putFloat("gpa",gpa);
        prefEdit.putInt("gender", idGender);
// step 1d:call commit() method to save the changes into the sharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

      //  String strGPA = etGPA.getText().toString();
     //  Float.parseFloat(strGPA);

        //step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // step 2b: Retrieve the saved data with the key "greeting" from the SharedPreferences object
        String strName = prefs.getString("Name","Default name");
        int gender = prefs.getInt("gender",0);
        float gpa = prefs.getFloat("gpa",0);

        etName.setText(strName);
        etGPA.setText(Float.toString(gpa));
        rgGender.check(gender);
    }
}
