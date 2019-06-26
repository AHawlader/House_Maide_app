package com.example.arifh.house_maide_app.Activities;

import android.database.Cursor;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.arifh.house_maide_app.Database.DbHelper;
import com.example.arifh.house_maide_app.Model.Review;
import com.example.arifh.house_maide_app.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReviewServentFragment extends Fragment {

    DbHelper dbHelper;

    Button review_button;
    EditText review;
    ListView viewRevierw;


    ArrayList<String> listItem;
    ArrayAdapter<String> adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_fragment, container, false);

        dbHelper=new DbHelper(getContext());

        listItem=new ArrayList<>();

        review = view.findViewById(R.id.reviewEdit);
        review_button = view.findViewById(R.id.reviewButton);
        viewRevierw=view.findViewById(R.id.listReview);

        viewData();
        getReview();

        viewRevierw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    String text=viewRevierw.getItemAtPosition(position).toString();
                    Toast.makeText(getParentFragment().getContext(),""+text,Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return view;



    }

    private void getReview(){

        review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment=review.getText().toString();
                if (!TextUtils.isEmpty(comment)){
                    DbHelper db=new DbHelper(getContext());
                    db.addReview(comment);
                    Toast.makeText(getActivity(),"review added",Toast.LENGTH_SHORT);
                    viewData();
                    review.setText("");
                }else{
                    Toast.makeText(getActivity(),"Can't Review added",Toast.LENGTH_SHORT);

                }
            }

        });
    }

    private  void  viewData(){
        Cursor cursor=dbHelper.viewReview();
        if (cursor.getCount()==1){
            Toast.makeText(getActivity(),"No data show",Toast.LENGTH_SHORT);
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            adapter =new ArrayAdapter (getContext(), android.R.layout.simple_list_item_1, listItem);
            viewRevierw.setAdapter(adapter);
        }

    }
}
