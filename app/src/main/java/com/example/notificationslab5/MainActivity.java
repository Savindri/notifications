package com.example.notificationslab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText inputDes;
    private Button buttonShowNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel(); //Creating the Notification Channel

        inputDes = findViewById(R.id.editTextDes);
        buttonShowNotification = findViewById(R.id.show);

        buttonShowNotification.setOnClickListener(v -> {

            String Des = inputDes.getText().toString();

            //to navigate to 2nd activity
            Intent intent = new Intent(getApplicationContext(),Activity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,intent,PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "lemubitA")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                    .setContentTitle("Notification Title")
                    .setContentText("Hello "+Des+"!, welcome to the MAD team.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true); //to make the notification disappear after clicking on it.

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(100,builder.build());
        });
    }

    //Creating the Notification Chanel
    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "Notification";   //getString(R.string.channel_name);
            String description = "This is my notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("lemubitA", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}