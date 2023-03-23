package aldoacosta.reproductormp3;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView portada;
    int cancion = 1;
    String nombreCancion;
    MediaPlayer[] reproductor = new MediaPlayer[5];
    Button btnPlay;
    TextView lblTitulo;

    Boolean yawe = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        portada = (ImageView) findViewById(R.id.portada);
        btnPlay = (Button) findViewById(R.id.play);
        lblTitulo = (TextView) findViewById(R.id.lblTitulo);
        cargarPortada();
        cargarCanciones();
    }

    public void cargarPortada(){
        if (cancion == 1){
            portada.setImageResource(R.drawable.damadivina);
            nombreCancion = "Dama Divina";
        }else if(cancion == 2){
            portada.setImageResource(R.drawable.smoothcriminal);
            nombreCancion = "Smooth Criminal";
        }else if(cancion == 3){
            portada.setImageResource(R.drawable.dress);
            nombreCancion = "Dress";
        }else if(cancion == 4){
            portada.setImageResource(R.drawable.masdeloqueaposte);
            nombreCancion = "Más de lo que aposté";
        }

        lblTitulo.setText(nombreCancion);
    }

    public void cargarCanciones(){
        reproductor[1] = MediaPlayer.create(this, R.raw.damadivina);
        reproductor[2] = MediaPlayer.create(this, R.raw.smoothcriminal);
        reproductor[3] = MediaPlayer.create(this, R.raw.dress);
        reproductor[4] = MediaPlayer.create(this, R.raw.masdeloqueaposte);
    }

    public void atras(View v){
        yawe = false;
        if (cancion >= 2){
            cancion--;
            Toast.makeText(this,"Canción anterior" + cancion, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Esta es la primera canción w", Toast.LENGTH_SHORT).show();
        }

        cargarPortada();
        stop(v);
        btnPlay.setBackgroundResource(android.R.drawable.ic_media_play);
    }

    public void play(View v){
        if(reproductor[cancion].isPlaying()){
            reproductor[cancion].pause();
            btnPlay.setBackgroundResource(android.R.drawable.ic_media_play);
            Toast.makeText(this, "Pausando '" + nombreCancion + "'", Toast.LENGTH_SHORT).show();
        }else{
            reproductor[cancion].start();
            btnPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
            Toast.makeText(this, "Reproduciendo '" + nombreCancion + "'", Toast.LENGTH_SHORT).show();
        }
        yawe = true;
    }

    public void stop(View v){
        reproductor[1].stop();
        reproductor[2].stop();
        reproductor[3].stop();
        reproductor[4].stop();
        btnPlay.setBackgroundResource(android.R.drawable.ic_media_play);
        if(yawe) Toast.makeText(this, "Deteniendo " + nombreCancion, Toast.LENGTH_SHORT).show();
        cargarCanciones();
    }

    public void siguiente(View v){
        yawe = false;
        if(cancion <= 3){
            cancion ++;
            Toast.makeText(this,"Siguiente canción", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Ya no hay mas canciones we", Toast.LENGTH_SHORT).show();
        }
        cargarPortada();
        stop(v);
        btnPlay.setBackgroundResource(android.R.drawable.ic_media_play);
    }
}
