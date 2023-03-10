package com.example.instagramclone;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnserver;
    EditText boxername,pspeed,ppower,kspeed,kpower;
    TextView getdata;
    TextView getalldata,cleardata;
    String alldata;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnserver=findViewById(R.id.btnserver);
        boxername=findViewById(R.id.boxername);
        pspeed=findViewById(R.id.punchspeed);
        ppower=findViewById(R.id.punchpower);
        kpower=findViewById(R.id.kickpower);
        kspeed=findViewById(R.id.kickspeed);
        getdata=findViewById(R.id.getdata);
        getalldata=findViewById(R.id.getalldata);
        cleardata=findViewById(R.id.clearalldata);

        cleardata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getalldata.setText("");
            }
        });

        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alldata="";
                ParseQuery<ParseObject> queryAll=ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e==null){

                            if (objects.size()>0){
                                for(ParseObject kickBoxer: objects){
                                     alldata+=alldata+kickBoxer+"\n";
                                    Log.i("alldata------>",""+alldata);
                                }
                                Log.i("finalalldata------>",""+alldata);
                                getalldata.setText(alldata);

                            }

                        }

                    }
                });

            }
        });

        btnserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseObject kickBoxer=new ParseObject("KickBoxer");
                kickBoxer.put("name",boxername.getText().toString());
                kickBoxer.put("punchspeed",parseInt(pspeed.getText().toString()));
                kickBoxer.put("punchpower",parseInt(ppower.getText().toString()));
                kickBoxer.put("kickspeed",parseInt(kspeed.getText().toString()));
                kickBoxer.put("kickpower",parseInt(kpower.getText().toString()));
                kickBoxer.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                            FancyToast.makeText(MainActivity.this, kickBoxer.get("name")+" is saved successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

//                            Toast.makeText(MainActivity.this, kickBoxer.get("name")+" is saved successfully", Toast.LENGTH_LONG).show();
                        else
                        FancyToast.makeText(MainActivity.this,  e.getMessage(), FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

//                            Toast.makeText(MainActivity.this,  e.getMessage(), Toast.LENGTH_LONG).show();

                    }

                });
            }
        });

    }



//    public void helloWorldTapped(View view){
//        ParseObject boxer= new ParseObject("Boxer");
//        boxer.put("punch_speed",200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null){
//                    Toast.makeText(MainActivity.this, "boxer  object is saved successfully", Toast.LENGTH_LONG).show();
//                }
//            }
//        });



            }
