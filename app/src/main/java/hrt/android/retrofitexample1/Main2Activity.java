package hrt.android.retrofitexample1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    RetroModal retroModal;
    List<Hero> heroList;
    ImageView ivHero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView = findViewById(R.id.tvDetail);
        ivHero = findViewById(R.id.ivHero);
        retroModal = new RetroModal();
       /* heroList = retroModal.getData();
        int position = getIntent().getIntExtra("position",0);
        String s = heroList.get(position).getName() +
                "\n" + heroList.get(position).getRealname() +
                "\n" + heroList.get(position).getCreatedby() +
                "\n" + heroList.get(position).getFirstappearance() +
                "\n" + heroList.get(position).getPublisher() +
                "\n" + heroList.get(position).getTeam() +
                "\n" + heroList.get(position).getCreatedby() ;
        String imgurl = heroList.get(position).getImageurl();
        textView.setText(s);
        Glide.with(getApplicationContext()).load(imgurl).into(ivHero);*/



        String s = getIntent().getStringExtra("name");
        s+= "\n" + getIntent().getStringExtra("realname");
        s+= "\n" +getIntent().getStringExtra("team");
        s+= "\n" +getIntent().getStringExtra("firstappearance");
        s+= "\n" +getIntent().getStringExtra("createdby");
        s+= "\n" +getIntent().getStringExtra("publisher");
       // s+= "\n" +getIntent().getStringExtra("bio");

        String imgurl = getIntent().getStringExtra("imageurl");
        textView.setText(s);
        Glide.with(getApplicationContext()).load(imgurl).into(ivHero);


    }
}
