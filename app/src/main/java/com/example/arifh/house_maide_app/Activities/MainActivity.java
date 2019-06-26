package com.example.arifh.house_maide_app.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arifh.house_maide_app.Database.DbHelper;
import com.example.arifh.house_maide_app.Model.MainApp;
import com.example.arifh.house_maide_app.Model.User;
import com.example.arifh.house_maide_app.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mtoggle;
    NavigationView nav;


    TextView textView;
    DatePickerDialog.OnDateSetListener mDateSetListener;


    private Button btnBook;
    private TextView startSTextView;
    private Spinner startSpinner;
    private Spinner endSpinner;
    private Spinner areaSpinner;
    private EditText phnNEditText;

    Button loginButton;
    Button RegistrationButton;

    TextView nameText, emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        menu();
        DatePicker();
        navProfile();
        nav.setNavigationItemSelectedListener(this);
    }

    //Start menu item
    private void menu() {

        drawerLayout = findViewById(R.id.draw);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav = findViewById(R.id.navid);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contextmenu, menu);
        return true;
    }


    // End a  menu item
    /*........................................................*/
//Date picker is start .....!>
    public void DatePicker() {
        textView = findViewById(R.id.datepickerid);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: onDateSet: mm/dd/yyy:" + dayOfMonth + "/" + month + "/" + year + "/");

                String date = month + "/" + dayOfMonth + "/" + year;
                textView.setText(date);
            }
        };
    }



    public void navProfile() {
        View v = nav.getHeaderView(0);

        try {
            nameText = v.findViewById(R.id.navProfileNameText);
            emailText = v.findViewById(R.id.navProfileEmailText);

            DbHelper dbHelper = new DbHelper(this);
            if (MainApp.getInstance().getUser() != null) {
                User user = dbHelper.viewServentProfile(MainApp.getInstance().getUser().getEmail());
                nameText.setText(user.getName());
                emailText.setText(user.getEmail());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id==R.id.profile_nav){
            Intent intent=new Intent(MainActivity.this,SerLoginRegisActivity.class);
            startActivity(intent);

        }else if (id==R.id.profile_servent){
            Intent intent=new Intent(MainActivity.this,ServentProfile.class);
            startActivity(intent);
        }

        return false;
    }
}
