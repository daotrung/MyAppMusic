package com.daotrung.myappmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daotrung.myappmusic.Adapter.ViewPagerPlayListNhac;
import com.daotrung.myappmusic.Fragment.FragmentPlayDSBaiHat;
import com.daotrung.myappmusic.Fragment.Fragment_Dia_Nhac;
import com.daotrung.myappmusic.Model.BaiHat;
import com.daotrung.myappmusic.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarPlayNhac ;
    TextView txtimeSong , txttotalSong ;
    SeekBar sktime ;
    ImageButton imgplay , imgrepeat , imgnext , imgpre , imgrandom ;
    ViewPager viewPagerplay ;
    Fragment_Dia_Nhac fragment_dia_nhac ;
    FragmentPlayDSBaiHat fragmentPlayDSBaiHat ;

    MediaPlayer mediaPlayer ;
    int postion = 0 ;
    boolean repeat = false ;
    boolean checkrandom= false ;
    boolean next = false ;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlayListNhac adapternhac ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getDataIntent();
        init();
        evenClick();

        }

    private void evenClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) !=null){
                    if(mangbaihat.size() > 0) {
                        fragment_dia_nhac.playNhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else{
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repeat == false){
                     if(checkrandom == true){
                         checkrandom = false;
                         imgrepeat.setImageResource(R.drawable.iconsyned);
                         imgrandom.setImageResource(R.drawable.iconshuffled);
                     }
                     imgrepeat.setImageResource(R.drawable.iconsyned);
                     repeat = true;
                }else{
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkrandom == false){
                    if(repeat== true){
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconsyned);

                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else{
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false ;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                 mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer !=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null ;
                    }
                    if(postion < mangbaihat.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        postion++;
                        if(repeat == true){
                            if(postion==0){
                                postion = mangbaihat.size();
                            }
                            postion -= 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == postion){
                                postion = index - 1;
                            }
                            postion = index ;
                        }
                        if(postion > mangbaihat.size()-1){
                            postion = 0 ;
                        }
                        new PlayMP3().execute(mangbaihat.get(postion).getLinkbaihat());
                        fragment_dia_nhac.playNhac(mangbaihat.get(postion).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(postion).getTenbaihat());
                        updateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer !=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null ;
                    }
                    if(postion < mangbaihat.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        postion--;
                        if(postion < 0){
                            postion = mangbaihat.size() -1 ;
                        }
                        if(repeat == true){

                            postion += 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == postion){
                                postion = index - 1;
                            }
                            postion = index ;
                        }
                        new PlayMP3().execute(mangbaihat.get(postion).getLinkbaihat());
                        fragment_dia_nhac.playNhac(mangbaihat.get(postion).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(postion).getTenbaihat());
                        updateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if(intent !=null){
            if(intent.hasExtra("cakhuc")){
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHat);

            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baiHatArrayList ;

            }
        }

}
    private  void init(){
        toolbarPlayNhac = findViewById(R.id.toolbarplaynhac);
        txtimeSong = findViewById(R.id.textviewtimesong);
        txttotalSong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgpre = findViewById(R.id.imagebuttonpreview);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerplay = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragmentPlayDSBaiHat = new FragmentPlayDSBaiHat() ;
        adapternhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        adapternhac.addFragment(fragmentPlayDSBaiHat);
        adapternhac.addFragment(fragment_dia_nhac);
        viewPagerplay.setAdapter(adapternhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        if(mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMP3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause); // chuyen tu play -> pause(icon)
        }
    }

    class PlayMP3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            updateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotalSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void updateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    if (postion < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        postion++;
                        if(repeat == true){
                            if(postion == 0){
                                postion = mangbaihat.size();
                            }
                            postion -= 1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == postion){
                                postion = index - 1;
                            }
                            postion = index;
                        }
                        if(postion > (mangbaihat.size()) - 1){
                            postion = 0;
                        }
                        new PlayMP3().execute(mangbaihat.get(postion).getLinkbaihat());
                        fragment_dia_nhac.playNhac(mangbaihat.get(postion).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(postion).getTenbaihat());
                    }

                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    },5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }

}