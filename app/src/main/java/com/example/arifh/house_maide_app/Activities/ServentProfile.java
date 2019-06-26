package com.example.arifh.house_maide_app.Activities;

import android.media.Rating;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.arifh.house_maide_app.Database.DbHelper;
import com.example.arifh.house_maide_app.Model.MainApp;
import com.example.arifh.house_maide_app.Model.User;
import com.example.arifh.house_maide_app.R;

public class ServentProfile extends AppCompatActivity {

    private TextView name, email,phone_number, address,job_or_jobless,start_time,end_time,rating;

    Rating rat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servent_profile);


        name=findViewById(R.id.seventName);
        email=findViewById(R.id.servent_email);
        phone_number=findViewById(R.id.servent_phone_number);
        address=findViewById(R.id.servent_address);
        job_or_jobless=findViewById(R.id.servent_job_jobless);
        start_time=findViewById(R.id.servent_start_time);
        end_time=findViewById(R.id.servent_end_time);


        DbHelper dbHelper = new DbHelper(this);
        if (MainApp.getInstance().getUser()!=null){
            User user=dbHelper.viewServentProfile(MainApp.getInstance().getUser().getEmail());
            name.setText(user.getName());
            email.setText(user.getEmail());
            phone_number.setText(user.getPhone_num());
            address.setText(user.getAddress());
            job_or_jobless.setText(user.getJobJobless());
            start_time.setText(user.getStartTime());
            end_time.setText(user.getEndTime());

        }
        getRating();


    }

    private void getRating() {
        rating=findViewById(R.id.servent_rating);
        String name=rating.getText().toString();
        DbHelper db=new DbHelper(getApplicationContext());
        db.viewRating(name);
        rating.setText("");
    }


}
