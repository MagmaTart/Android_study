package com.magmatart.getuuidtest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.util.UUID;
import android.Manifest;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.textview);

        //Get user permission
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.M){
            int permissionResult = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            if(permissionResult == PackageManager.PERMISSION_DENIED){
                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Need Permission")
                            .setMessage("This needs to READ_PHONE_STATE Permission. Continue?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1000);
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_LONG).show();
                                }
                            })
                            .create()
                            .show();
                }
                else{
                    requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1000);
                }
            }
            else{
                tv.setText(GetDeviceUUID(getBaseContext()).toString());
            }
        }
        tv.setText(GetDeviceUUID(getBaseContext()).toString());
    }

    public static UUID GetDeviceUUID(Context mContext){
        //Get UUID using TelephonyManager
        TelephonyManager mgr = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, ID;
        tmDevice = "" + mgr.getDeviceId();
        tmSerial = "" + mgr.getSimSerialNumber();
        ID = "" + Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        UUID deviceUUID = new UUID(ID.hashCode(), ((long)tmDevice.hashCode()<<32) | tmSerial.hashCode());
        return deviceUUID;
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }
}
