package com.example.kukuwalia.zupdu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class SearchDetailActivity extends AppCompatActivity {

    Button btnSend;
    EditText messageEditText;

    private ImageView image;
    private TextView title, bed, bath, rent, address, pets, startDate, sqft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        image = (ImageView) findViewById(R.id.poster);
        title = (TextView) findViewById(R.id.title);
        bed = (TextView) findViewById(R.id.bed);
        bath = (TextView) findViewById(R.id.bath);
        rent = (TextView) findViewById(R.id.rent);
        address = (TextView) findViewById(R.id.address);
        pets = (TextView)findViewById(R.id.pets);
        startDate = (TextView) findViewById(R.id.startDate);
        sqft = (TextView) findViewById(R.id.sqft);

        btnSend = (Button) findViewById(R.id.btnSend);
        messageEditText = (EditText) findViewById(R.id.messageEditText);

        //Getting the value from bundle, means the value which we had during switching to this activity from main activity
        SearchDetails details = (SearchDetails) getIntent().getExtras().getSerializable("SEARCH_DETAILS");

        if(details !=null)
        {
            //Showing image from the movie db api into imageview using glide library
            Glide.with(this).load(details.getThumbnailUrl()).into(image);
            title.setText(details.getTitle());
            bed.setText(Integer.toString(details.getBed()));
            bath.setText(Integer.toString(details.getBath()));
            rent.setText(Integer.toString(details.getRent()));
            address.setText(details.getAddress());
            pets.setText(details.getPets());
            startDate.setText(details.getStartDate());
            sqft.setText(Integer.toString(details.getSqft()));

            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageEditText.setText("");
                  Toast.makeText(getApplicationContext(), "message sent successfully", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
