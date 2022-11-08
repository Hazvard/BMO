package com.example.bmo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import model.Piece;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoDePiece extends AppCompatActivity {

    private int orientation;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private Bitmap nord;
    private Bitmap sud;
    private Bitmap est;
    private Bitmap ouest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_de_piece);

        orientation = 0;
        Piece piece  = new Piece();
        nord = null;
        sud = null;
        est = null;
        ouest = null;





        Button gauche = findViewById(R.id.Gauche);
        Button droite = findViewById(R.id.Droite);
        Button photo = findViewById(R.id.Photo);
        Button ajouterPorte = findViewById(R.id.AjouterPorte);
        TextView direction = findViewById(R.id.orientation);
        ImageView imagePiece = findViewById(R.id.ImageViewPhoto);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // récupérer la photo
                        Log.i("photo", "début on activity");
                        if(result.getResultCode() == RESULT_OK && result.getData() != null){
                            Bundle bundle = result.getData().getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");

                            Toast.makeText(PhotoDePiece.this, "Photo prise taille : "+bitmap.getHeight() , Toast.LENGTH_SHORT).show();

                            //enregistrer
                            FileOutputStream fos = null;
                            try {
                                fos = openFileOutput("image.data", MODE_PRIVATE);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos);
                            imagePiece.setImageBitmap(bitmap);// patch temporaire pour photo

                            if(orientation == 0){
                                nord = bitmap;
                            }else if(orientation == 1){
                                est = bitmap;
                            } else if (orientation == 2) {
                                sud = bitmap;
                            } else if (orientation == 3) {
                                ouest = bitmap;
                            }





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

        gauche.setOnClickListener(view -> {
            if(orientation == 0){
                orientation = 3;
                direction.setText("Ouest");
                 if (ouest != null){
                        imagePiece.setImageBitmap(ouest);
                 }else{
                        imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);
                 }
            }else if(orientation == 1){
                orientation = 0;
                direction.setText("Nord");
                if(nord != null){
                    imagePiece.setImageBitmap(nord);
                }else{
                    imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);}
            } else if (orientation == 2) {
                orientation = 1;
                direction.setText("Est");

                if (est != null) {
                    imagePiece.setImageBitmap(est);
                } else {
                    imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);
                }
            }else if (orientation == 3) {
                orientation = 2;
                direction.setText("Sud");

                if(sud != null){
                    imagePiece.setImageBitmap(sud);
                }else{
                    imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);                }
            }

        } );

        droite.setOnClickListener(view -> {
            if(orientation == 0){
                orientation = 1;
                direction.setText("Est");
                if (est != null){
                    imagePiece.setImageBitmap(est);
                }else{
                    imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);
                }
            }else if(orientation == 1){
                orientation = 2;
                direction.setText("Sud");
                if (sud != null){
                    imagePiece.setImageBitmap(sud);
                }else{
                    imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);
                }
            } else if (orientation == 2) {
                orientation = 3;
                direction.setText("Ouest");
                if (ouest != null){
                    imagePiece.setImageBitmap(ouest);
                }else{
                    imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);
                }

            } else if (orientation == 3) {
                orientation = 0;
                direction.setText("Nord");
                if (nord != null){
                    imagePiece.setImageBitmap(nord);
                }else{
                    imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);
                }

            }

        } );

    }
}