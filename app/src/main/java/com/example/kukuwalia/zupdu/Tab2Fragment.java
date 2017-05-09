package com.example.kukuwalia.zupdu;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private Button btnTEST;
    private EditText locationNameEditText ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout,container,false);
        btnTEST = (Button) view.findViewById(R.id.btnTEST);
        locationNameEditText = (EditText) view.findViewById(R.id.locationNameEditText);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tab2Fragment.this.getActivity(), SearchMain.class);
                startActivity(intent);
               /*final Address address = getLatLong();
                if(address !=null) {
                    List<String> latLong = new ArrayList<String>();
                    latLong.add(address.getLatitude() + "");
                    latLong.add(address.getLongitude() + "");

                    new JSONCall().execute(latLong);
                }*/
            }
        });

        return view;
    }
    public Address getLatLong(){
        final Geocoder geocoder = new Geocoder(this.getContext());
        Address address = null;
        final String locationName = locationNameEditText.getText()+"";
        try {
            List<Address> addresses = geocoder.getFromLocationName(locationName, 1);
            if (addresses != null && !addresses.isEmpty()) {
                address = addresses.get(0);
                return address;
            } else {

                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

}


/*
class JSONCall extends AsyncTask<List<String>,String,String> {

    HttpURLConnection con = null;
    BufferedReader reader = null;
    StringBuffer buffer = null;
    @Override
    protected String doInBackground(List<String>... params) {
        try {

            List<String> latlongList = params[0];
            String url = "https://www.zupdu.com/api/search";
            URL object = null;
            object = new URL(url);
            con = (HttpURLConnection) object.openConnection();
            con.setRequestMethod("POST");
            JSONObject json = new JSONObject();
            JSONObject data = new JSONObject();
            json.put("latitude", latlongList.get(0));
            json.put("longitude", latlongList.get(1));
            data.put("data", json);
            Log.i("Json Data",data.toString());


            OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());
            if (con.getResponseCode() == 200) {
                InputStream responseBody = con.getInputStream();
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                String empObj = jsonReader.toString();
                Log.i("string *************",empObj);

            } else {
                // Error handling code goes here
            }

            InputStream inputStream = con.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            buffer = new StringBuffer();
            String line = "";
            while((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }
            return buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(con != null) {
                con.disconnect();
            }
            try {
                if(reader!=null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
    @Override
    protected void onPostExecute (String result)
    {
        super.onPostExecute(result);
        System.out.println(result);
    }

}*/

