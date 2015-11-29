package com.hourglass.lingaraj.gsonhandlingjsonarrayexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ProgressDialog myProgressDialog;
    List<ContactDetail> contactDetailsTemporaryHolder;
    SerializableJsonDataHolder serializableJsonDataHolder;
    Button data1,data2,data3,data4,getJsonData;
    TextView contactName,contactNumber,emailId,addres;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data1=(Button)findViewById(R.id.button_fetch_first_data_from_json);
        data2=(Button)findViewById(R.id.button_fetch_second_data_from_json);
        data3=(Button)findViewById(R.id.button_fetch_third_data_from_json);
        data4=(Button)findViewById(R.id.button_fetch_fourth_data_from_json);
        contactName=(TextView) findViewById(R.id.contact_name);
        contactNumber=(TextView)findViewById(R.id.contact_number);
        emailId=(TextView)findViewById(R.id.email_id);
        addres=(TextView)findViewById(R.id.address);
        getJsonData=(Button)findViewById(R.id.getJsonData);
        myProgressDialog=new ProgressDialog(MainActivity.this);
        myProgressDialog.setMessage("Getting Data from Json and Adding it Serializable class");
        serializableJsonDataHolder=new SerializableJsonDataHolder();
        getJsonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myProgressDialog.show();
                setContactDetailsToSerializableClass();

            }
        });


        data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactName.setText(serializableJsonDataHolder.contactDetailsList.get(0).name);
                contactNumber.setText(serializableJsonDataHolder.contactDetailsList.get(0).phoneNumber);
                emailId.setText(serializableJsonDataHolder.contactDetailsList.get(0).emailId);
                addres.setText(serializableJsonDataHolder.contactDetailsList.get(0).address);
            }
        });
        data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactName.setText(serializableJsonDataHolder.contactDetailsList.get(1).name);
                contactNumber.setText(serializableJsonDataHolder.contactDetailsList.get(1).phoneNumber);
                emailId.setText(serializableJsonDataHolder.contactDetailsList.get(1).emailId);
                addres.setText(serializableJsonDataHolder.contactDetailsList.get(1).address);


            }
        });
        data3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactName.setText(serializableJsonDataHolder.contactDetailsList.get(2).name);
                contactNumber.setText(serializableJsonDataHolder.contactDetailsList.get(2).phoneNumber);
                emailId.setText(serializableJsonDataHolder.contactDetailsList.get(2).emailId);
                addres.setText(serializableJsonDataHolder.contactDetailsList.get(2).address);


            }
        });
        data4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactName.setText(serializableJsonDataHolder.contactDetailsList.get(3).name);
                contactNumber.setText(serializableJsonDataHolder.contactDetailsList.get(3).phoneNumber);
                emailId.setText(serializableJsonDataHolder.contactDetailsList.get(3).emailId);
                addres.setText(serializableJsonDataHolder.contactDetailsList.get(3).address);


            }
        });

    }

    private void setContactDetailsToSerializableClass() {

        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        String url="http://demo5585860.mockable.io/contactDetailJson";
        asyncHttpClient.get(MainActivity.this,url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.v("success", response.toString());
                try {
                    contactDetailsTemporaryHolder = new ArrayList<ContactDetail>();
                    Type typeIndicatorForGson = new TypeToken<ArrayList<ContactDetail>>() {}.getType();
                    Gson myGson = new Gson();
                    contactDetailsTemporaryHolder = myGson.fromJson(response.toString(), typeIndicatorForGson);
                    serializableJsonDataHolder.contactDetailsList = contactDetailsTemporaryHolder;
                    myProgressDialog.dismiss();

                }
                catch (Exception e)
                {
                    myProgressDialog.dismiss();
                    Log.e("GsonError", e.toString());
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
               }
        } );

    }
}
