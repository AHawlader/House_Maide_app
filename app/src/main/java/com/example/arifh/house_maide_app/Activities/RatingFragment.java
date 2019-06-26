package com.example.arifh.house_maide_app.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.arifh.house_maide_app.Database.DbHelper;
import com.example.arifh.house_maide_app.Model.Rating;
import com.example.arifh.house_maide_app.R;

public class RatingFragment extends Fragment {

    private RatingBar ratingBar;
    Button ratingButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.rating_fragment,container,false);
        ratingBar=view.findViewById(R.id.rating_bar);
        ratingButton=view.findViewById(R.id.rating_button);


        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating=String.valueOf(ratingBar.getRating());
                if (rating.equals(rating)){
                    DbHelper db=new DbHelper(getContext());
                    db.addRating(rating);
                    Toast.makeText(v.getContext(), rating, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(v.getContext(),"Already Rating ",Toast.LENGTH_LONG).show();

                }
            }
        });

      return view;

    }


}
