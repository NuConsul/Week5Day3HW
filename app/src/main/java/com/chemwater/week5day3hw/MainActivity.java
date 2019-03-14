package com.chemwater.week5day3hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent ;
import android.content.IntentFilter ;
import android.support.v4.content.LocalBroadcastManager ;
import android.text.style.LocaleSpan ;
import android.view.View ;
import android.widget.EditText ;

public class MainActivity extends AppCompatActivity {
    public static final String SEND_BROADCAST = "com.chemwater.week5day3hw.SEND_BROADCAST" ;
    MyBroadcastReceiver myBroadcastReceiver ;
    IntentFilter intentFilter ;
    EditText etUserName, etUserIDNumber, etInventoryCount, etDescription ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = findViewById(R.id.etProductName) ;
        etUserIDNumber = findViewById(R.id.etProductIDNumber) ;
        etInventoryCount = findViewById(R.id.etProductInventoryCount) ;
        etDescription = findViewById(R.id.etProductDescription) ;


        intentFilter = new IntentFilter() ; //instantiated intent filter
        myBroadcastReceiver = new MyBroadcastReceiver() ;  //instantiated receiver
        intentFilter.addAction(SEND_BROADCAST) ;//Add a action to intent filter
    }

    @Override
    protected void onStart() {
        super.onStart() ;
        //register the receiver
        registerReceiver(myBroadcastReceiver, intentFilter) ;
        //register locally
        LocalBroadcastManager.getInstance(this).registerReceiver(myBroadcastReceiver, intentFilter) ;
    }

    @Override
    protected void onStop() {
        super.onStop() ;
        //unregister the receiver
        unregisterReceiver(myBroadcastReceiver) ;
        //unregister Locally
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myBroadcastReceiver) ;
    }

    public void onClick(View view) {
        String userName = etUserName.getText().toString() ;
        String userNumber = etUserIDNumber.getText().toString() ;
        String userInventory = etInventoryCount.getText().toString() ;
        String userDescription = etDescription.getText().toString() ;


        switch(view.getId()) {
            case R.id.BroadcastUserInfo:
                Intent intent = new Intent(SEND_BROADCAST) ; //create intent to send the broadcast
                intent.putExtra("name", userName) ; //insert our name into the intent
                intent.putExtra("number", userNumber) ; //insert our number into the intent
                intent.putExtra("inventory", userInventory) ; //insert our inventory into the intent
                intent.putExtra("description", userDescription) ; //insert our description into the intent

                sendBroadcast(intent, "my.permission") ; //send broadcast with permissions requirement
                break ;
        }
    }
}



/*
Develop 2 applications:
         App 1:
            Have the user enter the following information:
                      ProductName
                      ProductIDNumber
                      ProductInventoryCount
                      ProductDescription
              Have a button that will send the above info to App2 using a broadcast reciever
              Restrict broadcast so only the two apps can use that broadcast
            App2:
                Have a database that will hold the info sent from app1 and display all the info in a recyclerView.
                If the device is a tablet, show the description else do not show the description.
                If the device is a tablet, use font size 28sp for the Name, else use 18sp
 */