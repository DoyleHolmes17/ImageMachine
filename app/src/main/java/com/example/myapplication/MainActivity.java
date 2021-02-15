package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button btnMachine, btnQr;
    private String imgDecodableString;
    private ArrayList<Image> images;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private static int RESULT_LOAD_IMG = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMachine = findViewById(R.id.btnMachine);
        btnQr = findViewById(R.id.btnQr);

        btnMachine.setOnClickListener(view -> {
            ImagePicker.with(this)
                    .setFolderMode(true)
                    .setFolderTitle("Select Picture")
                    .setRootDirectoryName(Environment.DIRECTORY_DCIM)
                    .setDirectoryName("ImagePicker")
                    .setMultipleMode(true)
                    .setShowNumberIndicator(true)
                    .setMaxSize(10)
                    .setLimitMessage("You can select up to 10 images")
                    .setSelectedImages(images)
                    .setRequestCode(2)
                    .start();
        });

        btnQr.setOnClickListener(view -> {
            Intent in = new Intent(this, CameraCaptureActivity.class);
            startActivity(in);
        });
    }

    protected void onResume() {
        super.onResume();
        String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (checkAndRequestPermissions()) {
            if (hasPermissionInManifest(this, PERMISSIONS)) {
                Toast.makeText(this, "All permission granted", Toast.LENGTH_LONG).show();
            } else {
                hasPermissionInManifest(this, PERMISSIONS);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 100)) {
            ArrayList<Image> images = ImagePicker.getImages(data);
            // Do stuff with image's path or id. For example:
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            for (int i = 0; i < images.size(); i++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Glide.with(MainActivity.this)
                            .load(images.get(i).getUri())
                            .into(imgView);
                } else {
                    Glide.with(MainActivity.this)
                            .load(images.get(i).getPath())
                            .into(imgView);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
//                    && null != data) {
//
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//
//                Cursor cursor = getContentResolver().query(selectedImage,
//                        filePathColumn, null, null, null);
//
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                imgDecodableString = cursor.getString(columnIndex);
//                cursor.close();
//                ImageView imgView = (ImageView) findViewById(R.id.imgView);
//                // Set the Image in ImageView after decoding the String
//                imgView.setImageBitmap(BitmapFactory
//                        .decodeFile(imgDecodableString));
//                Toast.makeText(this, imgDecodableString, Toast.LENGTH_LONG).show();
//
//            } else {
//                Toast.makeText(this, resultCode + "\n" + data.toString(),
//                        Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Log.e("ER", e.toString());
//            Toast.makeText(this, "Error", Toast.LENGTH_LONG)
//                    .show();
//        }
//
//    }

    public boolean hasPermissionInManifest(Context context, String[] permissionName) {
        final String packageName = context.getPackageName();
        try {
            final PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            final String[] declaredPermisisons = packageInfo.requestedPermissions;
            if (declaredPermisisons != null && declaredPermisisons.length > 0) {
                for (String p : declaredPermisisons) {
                    if (p.equals(permissionName)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("werreq", e + "");
        }
        return false;
    }

    private boolean checkAndRequestPermissions() {
        int read_external_storage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int write_external_storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (read_external_storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (write_external_storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

}