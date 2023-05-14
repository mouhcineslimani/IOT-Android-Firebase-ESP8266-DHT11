package com.mql.iot_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mql.iot_project.models.TempHumidity;

public class Dashboard extends AppCompatActivity {
    // Firebase Database.
    private FirebaseDatabase firebaseDatabase;
    // Reference for Firebase.
    private DatabaseReference databaseReference;
    // our object
    private TempHumidity tempHumidity ;

    // our textView declaration
    private TextView temperature ;
    private TextView humidite ;

    // btn
    private Button demarrer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        temperature = findViewById(R.id.temperatureZone);
        humidite = findViewById(R.id.humiditeZone) ;
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();
        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("DHT");

        //saveData();
        getdata();

    }
    private void saveData()
    {
        //instace of our object
        tempHumidity = new TempHumidity();
        tempHumidity.setTemperature(13.5);
        tempHumidity.setHumidity(100);
        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // data base reference will sends data to firebase.
                databaseReference.setValue(tempHumidity);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Dashboard.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getdata() {
        // calling add value event listener method
        // for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            Object temp , humidity ;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot sshot : snapshot.getChildren()) {
                    if (sshot.getKey().equals("humidity")) {
                        humidity = sshot.getValue();
                        humidite.setText(humidity+" %");
                    }
                    else {
                        temp = sshot.getValue();
                        temperature.setText(temp+" °");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(Dashboard.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}