package com.example.kukuwalia.zupdu;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SearchMain extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_search);

//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//        .cacheInMemory(true)
//        .cacheOnDisk(true)
//        .build();
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//        .defaultDisplayImageOptions(defaultOptions)
//        .build();
//        ImageLoader.getInstance().init(config);

        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        new CheckConnectionStatus().execute("https://memebers.000webhostapp.com/search.json");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, SearchDetailActivity.class);
        intent.putExtra("SEARCH_DETAILS", (SearchDetails) parent.getItemAtPosition(position));
        startActivity(intent);
    }

    class CheckConnectionStatus extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            try {
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String s = bufferedReader.readLine();

                String jsonReply = "";

                while ((s != null) && (!s.isEmpty())) {
                    jsonReply += s;
                    s = bufferedReader.readLine();
                }

                bufferedReader.close();
                return jsonReply;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);
                ArrayList<SearchDetails> searchList = new ArrayList<>();

                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray jsonArray = dataObject.getJSONArray("listings");

     //           Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
     //               SearchDetails searchDetails = gson.fromJson(object.toString(), SearchDetails.class);
                    SearchDetails searchDetails = new SearchDetails();
                    searchDetails.setTitle(object.getString("title"));
                    searchDetails.setBed(object.getInt("bed"));
                    searchDetails.setBath(object.getInt("bath"));
                    searchDetails.setRent(object.getInt("rent"));
                    JSONObject addressObj = new JSONObject(object.getString("address"));
                    try {
                        searchDetails.setAddress(addressObj.getString("city") +" , " + addressObj.getString("neighborhood"));
                    }
                    catch(Exception e) {

                    }
                    searchDetails.setPets(object.getString("pets"));
                    searchDetails.setStartDate(object.getString("startDate"));
                   // searchDetails.setSqft(object.getInt("sqft"));
                    searchDetails.setSqft(0);

                    JSONArray photosArray = object.getJSONArray("photos");
                    JSONObject photoObj = photosArray.getJSONObject(0);
                    searchDetails.setThumbnailUrl(photoObj.getString("thumbnailUrl"));
//                    ImageLoader.getInstance().displayImage(this.getThumbnailUrl(), thumbnailUrl);
                       searchList.add(searchDetails);
                }
                SearchArrayAdapter searchArrayAdapter = new SearchArrayAdapter(SearchMain.this, R.layout.search_list, searchList);
                listView.setAdapter(searchArrayAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}