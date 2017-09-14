package com.example.mheljohn.qrsys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QrActivity extends AppCompatActivity {
    public static int white = 0xFFFFFFFF;
    public static int black = 0xFF000000;
    public final static int WIDTH=500;
    private static final String TAG = "QRActivity";
    private String cName;
    private ImageView imageView;
    private String cFirst;
    private String cLast;
    private String cUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Intent intent = getIntent();
        String cQr = intent.getStringExtra("qrCode");
        cName = intent.getStringExtra("nameDetail");
        imageView = (ImageView) findViewById(R.id.qrCode);
        cFirst = MainActivity.myBundle.getString("fname");
        cLast = MainActivity.myBundle.getString("lname");
        cUser = MainActivity.myBundle.getString("username");
        try {
            Bitmap bitmap = encodeAsBitmap(cQr);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    saveImage(imageView,cName);
                    /*imageView.setDrawingCacheEnabled(true);
                    Bitmap b = imageView.getDrawingCache();
                    storeImage(b,cName);*/
                Toast.makeText(v.getContext(), "Your Image has been saved. ",
                        Toast.LENGTH_SHORT).show();
                Intent main = new Intent(QrActivity.this,MainActivity.class);
                main.putExtra("username", cUser);
                main.putExtra("fname", cFirst);
                main.putExtra("lname", cLast);
                QrActivity.this.startActivity(main);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Did you already save the QR Code?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent main = new Intent(QrActivity.this,MainActivity.class);
                        main.putExtra("username", cUser);
                        main.putExtra("fname", cFirst);
                        main.putExtra("lname", cLast);
                        QrActivity.this.startActivity(main);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        Bitmap bitmap=null;
        try
        {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);

            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? black:white;
                }
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
        } catch (Exception iae) {
            iae.printStackTrace();
            return null;
        }
        return bitmap;
    }
    private void storeImage(Bitmap image, String fileName) {
        File pictureFile = getOutputMediaFile(fileName);

        if (pictureFile == null) {
            Log.d(TAG,"Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }
    /** Create a File for saving an image */
    private  File getOutputMediaFile(String flName){
        String root = Environment.getExternalStorageDirectory().toString();
        File mediaStorageDir = new
                File(Environment.getExternalStorageDirectory().toString()
                + "/DCIM/Camera/Thesis");
                /*+ getApplicationContext().getPackageName()
                + "/Files");
*/

// Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
// Create a media file name
        String timeStamp = new SimpleDateFormat("MM-dd_HH:mm").format(new Date());
        File mediaFile;
        String mImageName=flName+" "+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }
    private void saveImage(ImageView imageView,String cName){
        imageView.setDrawingCacheEnabled(true);
        Bitmap b = imageView.getDrawingCache();
        storeImage(b,cName);
    }
}
