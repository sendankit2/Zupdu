package com.example.kukuwalia.zupdu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;

public class SearchArrayAdapter extends ArrayAdapter {

    private List<SearchDetails> searchDetailsList;
    private int resource;
    private Context context;


    public SearchArrayAdapter(Context context, int resource, List<SearchDetails> searchDetails) {
        super(context, resource, searchDetails);
        this.context = context;
        this.searchDetailsList = searchDetails;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SearchDetails details = searchDetailsList.get(position);

        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView rent = (TextView) view.findViewById(R.id.rent);
        TextView stDate = (TextView) view.findViewById(R.id.startDate);

        new CheckConnectionStatus(image).execute(details.getThumbnailUrl());

        title.setText(details.getTitle());

        rent.setText(details.getRent()+"");
        stDate.setText(details.getStartDate());
        Glide.with(context).load(details.getThumbnailUrl()).into(image);

        return  view;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return searchDetailsList.get(position);
    }


    class CheckConnectionStatus extends AsyncTask<String, Void, Bitmap> {

        ImageView tIV;

        public CheckConnectionStatus(ImageView iv){
            tIV = iv;
        }

        //This method will run on UIThread and it will execute before doInBackground
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        //This method will run on background thread and after completion it will return result to onPostExecute
        @Override
        protected Bitmap doInBackground(String... params) {
            URL url = null;
            try {
                //As we are passing just one parameter to AsyncTask, so used param[0] to get value at 0th position that is URL
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return bmp;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage(), e);
            }
            return null;
        }
        //This method runs on UIThread and it will execute when doInBackground is completed
        @Override
        protected void onPostExecute(Bitmap bmp) {
            super.onPostExecute(bmp);
            tIV.setImageBitmap(bmp);
        }
    }
}