package com.example.civicgrievance;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class sanitizationraise extends AppCompatActivity implements LocationListener {
    EditText e1,desc,el;
    Button b1;
    String address;
    String ddesc;
    ProgressDialog pd;
    int avi;
    FirebaseDatabase mDatabase;
    private static final int Gallery_Code = 1;
    Uri imageUri = null;
    MaterialButton b2;
    Button ib;
    DatabaseReference mRef;
    ImageView imageView;
    FirebaseStorage mstorage;
    String adavi;
    DatabaseReference mref;
    ImageButton imageButton;
    LocationManager locationManager;
    private StorageReference reference= FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanitizationraise);

        desc=findViewById(R.id.desctxt);
        e1=findViewById(R.id.doorno);
        ib=findViewById(R.id.capture_image_button);
        imageButton = findViewById(R.id.img1);
        mstorage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        pd=new ProgressDialog(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });


        b2=findViewById(R.id.currloc);
        if(ContextCompat.checkSelfPermission(sanitizationraise.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(sanitizationraise.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        b1=findViewById(R.id.next);
        el=findViewById(R.id.phn);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Filter").child("SanitizationProblems");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ddesc = desc.getText().toString();
                address = e1.getText().toString();
                String mmobile = el.getText().toString();
                String prblm="Sanitization";
                Boolean check=validateinfo(ddesc,address,mmobile,prblm);
                try{
                if(true){
                    pd.setTitle("Please wait a moment");
                    pd.show();
                    StorageReference filepath = mstorage.getReference().child("ImagePost").child(imageUri.getLastPathSegment());
                    filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloaduri = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();

                                    DatabaseReference newPost = mRef.push();
                                    newPost.child("Problem").setValue(prblm);
                                    newPost.child("Description").setValue(ddesc);
                                    newPost.child("Address").setValue(address);
                                    newPost.child("Mobile_Number").setValue(mmobile);
                                    newPost.child("Image").setValue(task.getResult().toString());
                                    pd.dismiss();

                                    Toast.makeText(sanitizationraise.this, "Data Upload Success", Toast.LENGTH_SHORT).show();
                                    MainActivity10();
                                }
                            });
                        }
                    });
                }
                else{
                    pd.dismiss();
                    Toast.makeText(sanitizationraise.this, "Data Upload Failed", Toast.LENGTH_SHORT).show();
                }}
                catch (Exception e){
                    Toast.makeText(sanitizationraise.this, "Please Enter all the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){
                sendSMS();

            }
            else{
                requestPermissions(new String[] {Manifest.permission.SEND_SMS},1);
            }
        }

    }

    private Boolean validateinfo(String ddesc, String address, String mmobile, String prblm) {
        if(ddesc.length()==0){
            desc.requestFocus();
            desc.setError("This field cannot be empty");
            return false;
        }else if(address.length()==0){
            e1.requestFocus();
            e1.setError("This field cannot be empty");
            return false;
        }
        else if(mmobile.length()==0){
            el.requestFocus();
            el.setError("This field cannot be empty");
            return false;

        }
        else if(!mmobile.matches("[6-9][0-9]{9}")){
            el.requestFocus();
            el.setError("Enter proper mobile number");
            return false;
        }
        else{
            return true;
        }
    }

    private void sendSMS() {
        try{
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage("8074529629",null,"A  Sanitization has been raised ",null,null);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Gallery_Code && resultCode == RESULT_OK )
        {
            imageUri = data.getData();
            imageButton.setImageURI(imageUri);

        }
    }

    public void MainActivity10() {
        Intent intent = new Intent(this,MainActivity10.class);
        startActivity(intent);
    }
    public void MainActivity7() {
        Intent intent = new Intent(this,MainActivity7.class);
        startActivity(intent);
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        try{
            Geocoder geocoder=new Geocoder(sanitizationraise.this, Locale.getDefault());
            List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address=addresses.get(0).getAddressLine(0);
            e1.setText(address);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("MissingPermission")
    private void getLocation(){
        try{
            locationManager=(LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,sanitizationraise.this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

}

