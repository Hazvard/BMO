package com.example.bmo;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import model.Construction;
import model.Piece;

public class Menu extends AppCompatActivity {

    private Construction constructionEnCours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        Button creation = findViewById(R.id.creationconstruction);
        Button repprendre = findViewById(R.id.ouvrirderniereconstruction);

        creation.setOnClickListener(view -> {
            constructionEnCours = new Construction();
            Intent intent = new Intent(Menu.this, PhotoDePiece.class);
            startActivity(intent);
        } );
    }


    public void ajouterPiece(Piece piece){
        constructionEnCours.ajouterPiece(piece);
    }
}