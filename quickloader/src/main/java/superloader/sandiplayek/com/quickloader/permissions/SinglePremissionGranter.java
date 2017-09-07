package superloader.sandiplayek.com.quickloader.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CALENDAR;

/**
 * Created by Android on 06-09-2017.
 */

public class SinglePremissionGranter {
    int PERMISSION_REQUEST_CODE = 99;

    public SinglePremissionGranter(Context context, int reqCode, GetPermissionResult getPermissionResult) {
        switch (reqCode) {
            case 1:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
                }
                break;
            case 2:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
                }
                break;
            case 3:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
                }
                break;
            case 4:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
                }
                break;
            case 5:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
                }
                break;
            case 6:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
                }
                break;
            case 7:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
                }
                break;
            case 8:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
                }
                break;
            case 9:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
                }
                break;
            case 10:
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    getPermissionResult.getPermissionMessage("OK");
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
                }
                break;
        }
    }


    public interface GetPermissionResult {
        void getPermissionMessage(String permissionStatus);
    }
}
