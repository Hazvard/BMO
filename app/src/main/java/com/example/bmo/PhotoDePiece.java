package com.example.bmo;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.*;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import model.Construction;
import model.Piece;
import model.outils.Direction;
import model.outils.MonJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;

public class PhotoDePiece extends AppCompatActivity {

    private int orientation;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private Bitmap nord;
    private Bitmap sud;
    private Bitmap est;
    private Bitmap ouest;
    private static Context context;

    private Construction construction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_de_piece);

        orientation = 0;
        nord = null;
        sud = null;
        est = null;
        ouest = null;

        construction = new Construction();
        construction.active();
        Piece piece = construction.reprendre();

        Button gauche = findViewById(R.id.Gauche);
        Button droite = findViewById(R.id.Droite);
        Button photo = findViewById(R.id.Photo);
        Button ajouterPorte = findViewById(R.id.AjouterPorte);
        ImageButton information = findViewById(R.id.imageButton5);
        TextView direction = findViewById(R.id.orientation);
        ImageView imagePiece = findViewById(R.id.ImageViewPhoto);





        InputStream in = null;
        JSONObject object = null;
        String json = null;
        MonJSON aideJson = new MonJSON();



        Log.i("photo", "json");
        try {
            in = getAssets().open("exemple.json");
            object = aideJson.readStream(in);
            Log.i("photo", "json 1");
            JSONArray piecejson = object.getJSONArray("pieces");
            Log.i("photo", "json 2" );

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // if(piece.getMurNord() != null)
            imagePiece.setImageBitmap(loadBitmap(piece.getId() + orientation));


        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // récupérer la photo
                        Log.i("photo", "début on activity");
                        if(result.getResultCode() == RESULT_OK && result.getData() != null){
                            Bundle bundle = result.getData().getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");

                            //Toast.makeText(PhotoDePiece.this, "Photo prise taille : "+bitmap.getHeight() , Toast.LENGTH_SHORT).show();

                            //enregistrer
                            /**
                            FileOutputStream fos = null;
                            try {
                                fos = openFileOutput(piece.getId() + orientation +".data", MODE_PRIVATE);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos);
                             **/

                            FileOutputStream fos;
                            try {
                                fos = openFileOutput(piece.getId() + orientation, MODE_PRIVATE);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos);
                                fos.close();
                            }
                            catch (FileNotFoundException e) {
                                Log.d("photo", "file not found");
                                e.printStackTrace();
                            }
                            catch (IOException e) {
                                Log.d("photo", "io exception");
                                e.printStackTrace();
                            }

                            //saveFile(context, bitmap, piece.getId() + orientation);






                            //imagePiece.setImageBitmap(bitmap);// patch temporaire pour photo

                           imagePiece.setImageBitmap(loadBitmap(piece.getId() + orientation));
                            Log.i("photo", piece.getId() + orientation + ".data");

                            if(orientation == 0){
                                nord = bitmap;
                                construction.ajouterMur(piece, piece.getId() + orientation + ".data", Direction.NORD);
                            }else if(orientation == 1){
                                est = bitmap;
                                construction.ajouterMur(piece, piece.getId() + orientation + ".data", Direction.EST);
                            } else if (orientation == 2) {
                                sud = bitmap;
                                construction.ajouterMur(piece, piece.getId() + orientation + ".data", Direction.SUD);
                            } else if (orientation == 3) {
                                ouest = bitmap;
                                construction.ajouterMur(piece, piece.getId() + orientation + ".data", Direction.OUEST);
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
                     imagePiece.setImageBitmap(loadBitmap(piece.getId() + orientation));
                 }else{
                        imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);
                 }
            }else if(orientation == 1){
                orientation = 0;
                direction.setText("Nord");
                if(nord != null){
                    imagePiece.setImageBitmap(loadBitmap(piece.getId() + orientation));
                }else{
                    imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);}
            } else if (orientation == 2) {
                orientation = 1;
                direction.setText("Est");

                if (est != null) {
                    imagePiece.setImageBitmap(loadBitmap(piece.getId() + orientation));
                } else {
                    imagePiece.setImageResource(R.drawable.adventure_time_bmo_png_file);
                }
            }else if (orientation == 3) {
                orientation = 2;
                direction.setText("Sud");

                if(sud != null){
                    imagePiece.setImageBitmap(loadBitmap(piece.getId() + orientation));
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

    public static void saveFile(Context context, Bitmap b, String picName){
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(picName, MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        }
        catch (FileNotFoundException e) {
            Log.d("photo", "file not found");
            e.printStackTrace();
        }
        catch (IOException e) {
            Log.d("photo", "io exception");
            e.printStackTrace();
        }
    }

    public Bitmap loadBitmap(String picName){
        Bitmap b = null;
        FileInputStream fis;
        try {
            fis = openFileInput(picName);
            b = BitmapFactory.decodeStream(fis);
            fis.close();
        }
        catch (FileNotFoundException e) {
            Log.d("photo", "file not found");
            e.printStackTrace();
        }
        catch (IOException e) {
            Log.d("photo", "io exception");
            e.printStackTrace();
        }
        return b;
    }


}