package com.example.administrator.project207.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.example.administrator.project207.R;

import java.util.UUID;

public class SplashActivity extends Activity {

    private BeaconManager beaconManager ;
    private String beacon_uuid;
    private int beacon_major;
    private int beacon_minor;
    private Region region;
    public boolean finishReservation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toast.makeText(getApplicationContext(),"블루투스 키지 않으면 진료접수가 진행되지 않습니다",Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"블루투스 메시지에 동의해주세요!!",Toast.LENGTH_SHORT).show();
        beaconManager = new BeaconManager(this);
        boolean finishReservation = false;
        region = new Region("ranged region",
                UUID.fromString("b9407f30-f5f8-466e-aff9-25556b57fe70"),null,null);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {



                finish();
            }
        }, 3000); // 3초 후 이미지를 닫습니다

    }

    protected void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause(){

        super.onPause();
    }


}
