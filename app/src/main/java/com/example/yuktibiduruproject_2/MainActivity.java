package com.example.yuktibiduruproject_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int layoutStyle = 1;                                    // initial layout style is list. 1 - grid, 2 - list

    // initialize value required for the activity
    ArrayList<String> myList;
    int[] imageList;
    ArrayList<Uri> uriList = new ArrayList<Uri>();
    RVClickListener listener;
    int _position;

// On create function for binding the recycler view and other actions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // super class call
        super.onCreate(savedInstanceState);
//        bind the layout of the activity
        setContentView(R.layout.activity_main);
//         bind the recycler view of the activity
        RecyclerView recycler_view_grid = findViewById(R.id.recycler_view);

//      initialize values required for the display
        myList = new ArrayList(Arrays.asList("Cow", "Deer", "Hippo", "Lion", "Squirrel",
                "Tiger", "Zebra"));
        imageList =
                new int[]{
                R.drawable.cow,
                R.drawable.deer,
                R.drawable.hippo,
                R.drawable.lion,
                R.drawable.squirrel,
                R.drawable.tiger,
                R.drawable.zebra
        };

        initializeUriList();

//        define the recycler view listener to start a wikipedia page when click using start activity
        listener = (view, position) ->{
            _position = position;
            Uri aUri =uriList.get(position);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(aUri);
            startActivity(intent);

        };

//        initialize the adapter for teh grid layout
        MyGridAdapter adapter = new MyGridAdapter(this,myList,imageList,uriList, listener);
        recycler_view_grid.setHasFixedSize(false);
        recycler_view_grid.setAdapter(adapter);
        recycler_view_grid. setLayoutManager(new GridLayoutManager(this, 2));
    }

//   Define options menu creation by inflating the options menu layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

//    Define the actions to be done on the selection of the options menu items
    public boolean onOptionsItemSelected(MenuItem item)
    {
     switch(item.getItemId())
     {
//         if grid layout is selected, show grid layout unless it was already selected. If so, show a
//          toast message informing the same
         case R.id.menu1:
             if(layoutStyle == 1)
                 Toast.makeText(this,"You are already in grid view", Toast.LENGTH_LONG).show();
             else
             {
                layoutStyle = 1;
                 RecyclerView recycler_view_grid = findViewById(R.id.recycler_view);
                 MyGridAdapter adapter = new MyGridAdapter( this,myList, imageList,uriList, listener);
                 recycler_view_grid.setHasFixedSize(false);
                 recycler_view_grid.setAdapter(adapter);
                 recycler_view_grid.setLayoutManager(new GridLayoutManager(this, 2));
             }
             return true;
         case R.id.menu2:
//         if list layout is selected, show list layout unless it was already selected. If so, show a
//          toast message informing the same

             if(layoutStyle == 2)
                 Toast.makeText(this,"You are already in list view", Toast.LENGTH_LONG).show();
             else
             {
                 layoutStyle = 2;
                 RecyclerView recycler_view = findViewById(R.id.recycler_view);
                 MyAdapter adapter = new MyAdapter(this, myList, imageList,uriList, listener);
                 recycler_view.setHasFixedSize(false);
                 recycler_view.setAdapter(adapter);
                 recycler_view.setLayoutManager(new LinearLayoutManager(this));
             }
             return true;
     }
     return false;

    }

//    function to initialize the urls of wikipedia pages of all the different animals
    public void initializeUriList()
    {
        uriList.add(Uri.parse("https://en.wikipedia.org/wiki/Cattle")) ;
        uriList.add(Uri.parse("https://en.wikipedia.org/wiki/Deer")) ;
        uriList.add(Uri.parse("https://en.wikipedia.org/wiki/Hippopotamus")) ;
        uriList.add(Uri.parse("https://en.wikipedia.org/wiki/Lion")) ;
        uriList.add(Uri.parse("https://en.wikipedia.org/wiki/Squirrel")) ;
        uriList.add(Uri.parse("https://en.wikipedia.org/wiki/Tiger")) ;
        uriList.add(Uri.parse("https://en.wikipedia.org/wiki/Zebra")) ;
    }

}