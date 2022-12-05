package model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import model.outils.ConstructionData;
import model.outils.Direction;
import model.outils.MonJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarException;

public class Construction {
    private HashMap<String,Piece> ensembleDesPieces;
    private String id;
    private String idPieceActive;

    public Construction(){
        ensembleDesPieces = new HashMap<>();
        Date maintenant = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddHHmmss");
        id = "Construction"+ formatter.format(maintenant);
        idPieceActive = null;
    }

    public void active(){
        Log.i("construction", "active");
        id = "sauvegarde";
        ensembleDesPieces = new HashMap<>();

        try {

            File dir = Environment.getExternalStorageDirectory();
            File yourFile = new File(dir, "storage/sdcard/MyIdea/BMO/\" + \"save\" + \".json");
            FileInputStream stream = new FileInputStream(yourFile);
            String jString = null;
            try {
                FileChannel fc = stream.getChannel();
                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
                /* Instead of using default, pass in a decoder. */
                jString = Charset.defaultCharset().decode(bb).toString();
            }
            finally {
                stream.close();
            }


            JSONObject jObject = new JSONObject(jString);
            JSONArray jsonpieces = jObject.getJSONArray("pieces");
            for (int i = 0; i < jsonpieces.length(); i++) {
                String id = jsonpieces.getJSONObject(i).getString("nom");
                Boolean active = jsonpieces.getJSONObject(i).getBoolean("active");
                String murSud = jsonpieces.getJSONObject(i).getString("murSud");
                String murNord = jsonpieces.getJSONObject(i).getString("murNord");
                String murEst = jsonpieces.getJSONObject(i).getString("murEst");
                String murOuest = jsonpieces.getJSONObject(i).getString("murOuest");
                Piece piece = new Piece(active, id, murOuest, murEst, murNord, murSud);
                if(piece.getActive()){
                    Log.i("construction", "piece active detectée");
                    idPieceActive = piece.getId();
                }
                ensembleDesPieces.put(id, piece);
            }



        } catch (Exception e) {e.printStackTrace();}
    }

    public String getId() {
        return id;
    }

    public int getNbPieces(){
        return ensembleDesPieces.size();
    }

    public HashMap<String, Piece> getPieces(){
        return ensembleDesPieces;
    }

    public void enregistreActive() {


        ConstructionData data = new ConstructionData(this);

        Log.i("enregistrerActive", data.toString());
        JSONObject sauv = null;
        try {
            sauv = new JSONObject(data.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



        try {
            Writer output = null;
            File file = new File("storage/sdcard/MyIdea/BMO/" + "save" + ".json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(sauv.toString());
            output.close();


        } catch (Exception e) {

        }
    }

    public Piece recupererPieceActive(){
        InputStream in = null;
        JSONObject object = null;
        String json = null;
        MonJSON aideJson = new MonJSON();



        Log.i("construction", "json");
        try {



            //in = getContext.getAssets().open("exemple.json");
            object = aideJson.readStream(in);
            Log.i("photo", "json 1");
            JSONArray piecejson = object.getJSONArray("pieces");
            Log.i("photo", "json 2" );

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void enregistrerPiece(Piece piece){

        String filename = "save.json";
        String fileContents = "Your config content..";
        FileOutputStream outputStream;


    }


    public void enregistrer(Construction construction){

    }

    public Piece ajouterPiece(){
        inactivePartout();
        Log.i("construction", "ajouterPiece");
        Date maintenant = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddHHmmss");
        String idPiece = "Piece"+ formatter.format(maintenant);
        Piece piece = new Piece(idPiece);
        Log.i("construction", "ensemble");
        idPieceActive = idPiece;
        piece.setActive(true);
        ensembleDesPieces.put(idPiece, piece);

        Log.i("construction", "id piece active : " + idPieceActive);
        return piece;
    }

    public void ajouterPiece(Piece piece){
        ensembleDesPieces.put(piece.getId(), piece);
    }

    public Piece getPiece(String id){
        return ensembleDesPieces.get(id);
    }

    public void ajouterMur(Piece piece, String photo, Direction direction){
        piece.ajouterMur(photo, direction);
        if(piece.complete()){
            this.enregistreActive();
            Log.i("construction", "la piece à 4 murs");
        }
    }

    public void inactivePartout(){
        if(!ensembleDesPieces.isEmpty()){
            ensembleDesPieces.get(idPieceActive).setActive(false);
            idPieceActive = null;
        }

    }

    public Piece reprendre(){
        if(idPieceActive != null){
            Log.i("construction", "Pas de piece active");
            return ensembleDesPieces.get(idPieceActive);
        }else {
            return ajouterPiece();
        }
    }






}
