package com.example.arifh.house_maide_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arifh.house_maide_app.Database.DbHelper;
import com.example.arifh.house_maide_app.Model.User;
import com.example.arifh.house_maide_app.R;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {
    DbHelper dbHelper;
    EditText nameEdit,addressEdit,phn_numEdit,emailEdit,passEdit,confim_passEdit,dateOfBirth,jobJobless,jobAddress,starttime,endTime,currentSalary;
    Spinner typeEdit;

    Button RegistrationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        TextLogin();

        dbHelper=new DbHelper(this);

        nameEdit=findViewById(R.id.reg_name);
        addressEdit=findViewById(R.id.reg_address);
        phn_numEdit=findViewById(R.id.reg_phn_number);
        emailEdit=findViewById(R.id.reg_email);
        dateOfBirth=findViewById(R.id.date_of_birth);
        jobJobless=findViewById(R.id.job_jobless);
        jobAddress=findViewById(R.id.jod_address);
        starttime=findViewById(R.id.start_date);
        endTime=findViewById(R.id.end_date);
        currentSalary=findViewById(R.id.current_salary);
        passEdit=findViewById(R.id.reg_password);
        confim_passEdit=findViewById(R.id.reg_confirmpass);
        typeEdit=findViewById(R.id.TypeView);

        TypeSpinner();

        RegistrationBtn=findViewById(R.id.Reg_button);

        RegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass=passEdit.getText().toString().trim();
                String conPass=confim_passEdit.getText().toString().trim();

                if (pass.equals(conPass)){
                    if (!EmptyValidation()){
                        dbHelper.addUser(new User(
                                nameEdit.getText().toString(),
                                addressEdit.getText().toString(),
                                phn_numEdit.getText().toString(),
                                emailEdit.getText().toString(),
                                dateOfBirth.getText().toString(),
                                jobJobless.getText().toString(),
                                jobAddress.getText().toString(),
                                starttime.getText().toString(),
                                endTime.getText().toString(),
                                currentSalary.getText().toString(),
                                passEdit.getText().toString(), typeEdit.getSelectedItem().toString()
                        ));

                        Toast.makeText(RegistrationActivity.this,"Successfuly Registered",Toast.LENGTH_SHORT).show();
                        Intent moveLogintent=new Intent(RegistrationActivity.this,Login_Activity.class);
                        startActivity(moveLogintent);
                    }else {
                        Toast.makeText(RegistrationActivity.this,"Register Fields are Empty",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegistrationActivity.this,"Password is not match",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    private boolean EmptyValidation(){
        if (TextUtils.isEmpty(nameEdit.getText().toString())||TextUtils.isEmpty(addressEdit.getText().toString())||TextUtils.isEmpty(emailEdit.getText().toString())
                ||TextUtils.isEmpty(passEdit.getText().toString())||TextUtils.isEmpty(confim_passEdit.getText().toString())
                ){
            return true;
        }else {
            return false;
        }
    }

    private void TypeSpinner(){

        List<String> type=new ArrayList<>();
        type.add("Select a Type");
        type.add("Admin");
        type.add("Service Provider");
        type.add("User");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        typeEdit.setAdapter(dataAdapter);

    }



    public void TextLogin(){
        TextView textView=findViewById(R.id.textViewLinkLogin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrationActivity.this,Login_Activity.class);
                startActivity(intent);
            }
        });
    }


}
