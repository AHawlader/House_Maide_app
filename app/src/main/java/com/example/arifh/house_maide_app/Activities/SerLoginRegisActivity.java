package com.example.arifh.house_maide_app.Activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.arifh.house_maide_app.Adapter.ViewPagerAdapter;
import com.example.arifh.house_maide_app.Database.DbHelper;
import com.example.arifh.house_maide_app.Model.MainApp;
import com.example.arifh.house_maide_app.Model.User;
import com.example.arifh.house_maide_app.R;

public class SerLoginRegisActivity extends AppCompatActivity {

    TextView nameText, phn_num, emailtext, addresstext;


    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser_login_regis);


        tabLayout=findViewById(R.id.tablayout_id);
        appBarLayout=findViewById(R.id.appBarlayout);
        viewPager=findViewById(R.id.viewpager_id);

        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new ReviewServentFragment(),"Review");
        adapter.AddFragment(new RatingFragment(),"Rating");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        nameText = findViewById(R.id.showName);
        phn_num = findViewById(R.id.showPhonenum);
        addresstext = findViewById(R.id.showAddress);



        DbHelper dbHelper = new DbHelper(this);
        if (MainApp.getInstance().getUser()!=null){
            User user=dbHelper.viewServentProfile(MainApp.getInstance().getUser().getEmail());
            nameText.setText(user.getName());
            phn_num.setText(user.getPhone_num());
            addresstext.setText(user.getAddress());
        }


    }


}
