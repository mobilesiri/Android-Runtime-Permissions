package com.mobilesiri.runtimepermissions;

import android.annotation.TargetApi;
import android.app.usage.UsageEvents;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    Switch contactbutton, camerabutton, locationbutton;
    final private int contactPERMISSIONS = 1, cameraPERMISSIONS = 2, locationPERMISSIONS = 3;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactbutton = (Switch) findViewById(R.id.switch1);
        camerabutton = (Switch) findViewById(R.id.switch2);
        locationbutton = (Switch) findViewById(R.id.switch3);
        layout = (RelativeLayout) findViewById(R.id.coordinate);
        contactbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int Permission = ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_CONTACTS);
                    if (Permission != PackageManager.PERMISSION_GRANTED) {
                        Snackbar snackbar = Snackbar.make(layout, "You need to allow access to Contacts", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_CONTACTS}, contactPERMISSIONS);
                            }
                        });
                        snackbar.show();

                    } else if (Permission == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "automatically granted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        camerabutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int Permission = ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA);
                    if (Permission != PackageManager.PERMISSION_GRANTED) {
                        Snackbar snackbar = Snackbar.make(layout, "You need to allow access to Camera", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CAMERA}, cameraPERMISSIONS);
                            }
                        });
                        snackbar.show();

                    } else if (Permission == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "automatically granted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        locationbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int Permission = ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
                    if (Permission != PackageManager.PERMISSION_GRANTED) {
                        Snackbar snackbar = Snackbar.make(layout, "You need to allow access to Location", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, locationPERMISSIONS);
                            }
                        });
                        snackbar.show();

                    } else if (Permission == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "automatically granted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case cameraPERMISSIONS:
            case locationPERMISSIONS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "granted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "not granted", Toast.LENGTH_SHORT).show();
                    if (requestCode == contactPERMISSIONS) contactbutton.setChecked(false);
                    if (requestCode == cameraPERMISSIONS) camerabutton.setChecked(false);
                    if (requestCode == locationPERMISSIONS) locationbutton.setChecked(false);
                }
                return;
            }
        }
    }
}