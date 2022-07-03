package com.example.profilemanager;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button maps_btn;
    EditText teamAddress;
    ImageView avatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maps_btn = findViewById(R.id.google_maps_btn);
        teamAddress = findViewById(R.id.team_address_input);
        avatar = findViewById(R.id.img_main);
        ActivityResultLauncher<Intent> profileActivityResultLauncher;
        profileActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            assert data != null;
                            String drawableName;
                            switch (data.getIntExtra("imageID", R.id.img_00)){
                                case R.id.img_00:
                                    drawableName = "flag_ca";
                                    break;
                                case  R.id.img_01:
                                    drawableName = "flag_eg";
                                    break;
                                case  R.id.img_02:
                                    drawableName = "flag_fr";
                                    break;
                                case  R.id.img_10:
                                    drawableName = "flag_jp";
                                    break;
                                case  R.id.img_11:
                                    drawableName = "flag_sp";
                                    break;
                                case  R.id.img_12:
                                    drawableName = "flag_tr";
                                    break;
                                case  R.id.img_20:
                                    drawableName = "flag_uk";
                                    break;
                                case  R.id.img_21:
                                    drawableName = "flag_us";
                                    break;
                                default:
                                    drawableName = "flag_kr";
                                    break;
                            }
                            int resID = getResources().getIdentifier(drawableName,"drawable", getPackageName());
                            avatar.setImageResource(resID);
                        }
                    }
                }

        );
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                profileActivityResultLauncher.launch(intent);
            }
        });


        maps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=" + teamAddress.getText());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }



}