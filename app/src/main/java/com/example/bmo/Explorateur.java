package com.example.bmo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Explorateur extends AppCompatActivity {

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorateur);


        Button gauche = findViewById(R.id.buttontourneragauche);
        Button droite = findViewById(R.id.buttontourneradroite);
        Button photo = findViewById(R.id.buttonphoto);

        ImageView imagePiece = findViewById(R.id.vuedelapiece);





        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // récupérer la photo
                        Log.i("photo", "début on activity");
                        if(result.getResultCode() == RESULT_OK && result.getData() != null){
                            Bundle bundle = result.getData().getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");

                            Toast.makeText(Explorateur.this, "Photo prise taille : "+bitmap.getHeight() , Toast.LENGTH_SHORT).show();

                            //enregistrer
                            FileOutputStream fos = null;
                            try {
                                fos = openFileOutput("image.data", MODE_PRIVATE);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                            imagePiece.setImageBitmap(bitmap);// patch temporaire pour photo
                            try {
                                fos.flush();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }


                    }
                }
        );

        photo.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Log.i("photo", "début");
            if(intent.resolveActivity(getPackageManager()) != null){
                Log.i("photo", "dans le if");
                activityResultLauncher.launch(intent);
            }
        });


    }


}