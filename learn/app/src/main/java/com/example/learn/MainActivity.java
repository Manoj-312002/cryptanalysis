package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText e1 = (EditText) findViewById(R.id.editTextTextPersonName);
        Button b1 = (Button) findViewById(R.id.button);

        Bitmap bg = Bitmap.createBitmap(720, 1024 , Bitmap.Config.ARGB_8888);
        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.setImageBitmap(bg);

        Canvas cv = new Canvas(bg);

        Paint pt = new Paint();
        pt.setColor(Color.BLUE);

        RectF rectf = new RectF(200, 200 , 400 , 400);
        cv.drawArc(rectf , 0 , 90 , true , pt);

        Animation a1 = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.scale);
        i.startAnimation(a1);

        DBhandler db = new DBhandler(this);
        db.add();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e1.getText().toString();
                NotificationManager notif= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify = new Notification.Builder
                        (getApplicationContext()).setContentTitle("Napp").setContentText(name)
                        .setContentTitle(db.get()).setSmallIcon(R.drawable.ic_launcher_background).build();
                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);
            }
        });
    }
}