package com.example.yuktibiduruproject_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class EnlargedImage extends AppCompatActivity {
// class to define activity to show full screen image of the animals
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        call to super class
        super.onCreate(savedInstanceState);
//        bind the layout of the activity
        setContentView(R.layout.activity_enlarged_image);

//      initialize image list for display using the resource ids
        int[] imageList = new int[]{
                R.drawable.cow,
                R.drawable.deer,
                R.drawable.hippo,
                R.drawable.lion,
                R.drawable.squirrel,
                R.drawable.tiger,
                R.drawable.zebra
        };
//      Bind the image view
        ImageView image = findViewById(R.id.imageView2);
//      Bind the image based on the information passed by the previous activity in regards to the position of the image
        image.setImageResource(imageList[getIntent().getIntExtra("position",0)]);
    }
}