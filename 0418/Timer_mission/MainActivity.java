package com.example.eggtimer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.edit);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "My Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationChannel.setDescription("Channel description");

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void sendNotification() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.google.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Egg Timer")
                        .setContentText("계란 삶기가 완료되었습니다.")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }

    private void showExtendDialog() {
    new androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("타이머 종료")
            .setMessage("10초를 더 연장하시겠습니까?")
            .setPositiveButton("연장", (dialog, which) -> {
                startTimer(null); // 타이머 다시 시작
            })
            .setNegativeButton("종료", (dialog, which) -> {
                finishAffinity(); // 현재 액티비티와 그 부모 액티비티 모두 종료
                System.exit(0);    // 완전히 앱 프로세스 종료
            })
            .setCancelable(false)
            .show();
    }

    public void startTimer(View view) {
        new CountDownTimer(10 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mEditText.setText((millisUntilFinished / 1000) + "초");
            }

            public void onFinish() {
                mEditText.setText("done!");
                sendNotification();
                showExtendDialog(); // 타이머 종료 후 다이얼로그 표시
            }
        }.start();
    }
}
