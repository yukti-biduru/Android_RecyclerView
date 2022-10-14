package com.example.yuktibiduruproject_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.ArrayList;
// adapter for the grid view
public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.ViewHolder>
{
    // initialize the variables required for the adapter
    private ArrayList<String> nameList;
    private int[] imageList;
    private ArrayList<Uri> uriList;
    private RVClickListener RVlistener;
    private Context _context;

//    Constructor of the adapter assigning the various values
    public MyGridAdapter(Context context,ArrayList<String> name_List, int[] image_List, ArrayList<Uri> uri_List, RVClickListener listener)
    {
        nameList = name_List;
        imageList = image_List;
        uriList = uri_List;
        this.RVlistener = listener;
        _context = context;
    }

//    Function to create the view holder using the layout inflater of the defined grid layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context =parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.recycler_view_grid, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView,RVlistener);
        return viewHolder;
    }

//    After creating the view holder the data to be shown is bound to the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.name.setText(nameList.get(position));
        holder.image.setImageResource(imageList[position]);
    }

//    Function to get the item count of the view
    @Override
    public int getItemCount() {return nameList.size();}


// class view holder has been defined here
public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener, View.OnCreateContextMenuListener
{
//     declare variables required for the class
    public TextView name;
    public ImageView image;
    public RVClickListener listener;
    private View ItemView;

    // Constructor initializing the class variables
    public ViewHolder(@NonNull View itemView, RVClickListener passedListener)
    {
        super(itemView);
        name = itemView.findViewById(R.id.textView3);
        image = itemView.findViewById(R.id.imageView);
        this.ItemView = itemView;
        itemView.setOnCreateContextMenuListener(this);
        this.listener =passedListener;
        itemView.setOnClickListener(this);
    }

//  On click of the grid items, recycler listener is called to carry out further actions
    @Override
    public void onClick (View v)
    {
        listener.onClick(v, getAdapterPosition());
    }

//    Creation of the context menu using the layout file defined for the context menu
//    the two menu items are defined as well
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater inflater = new MenuInflater(v.getContext());
        inflater.inflate(R.menu.context_menu, menu);
        menu.getItem(0).setOnMenuItemClickListener(onMenu);
        menu.getItem(1).setOnMenuItemClickListener(onMenu);
    }
//    Function to define the actions on context menu click
    private final MenuItem.OnMenuItemClickListener onMenu = new MenuItem.OnMenuItemClickListener(){
        @Override
        public boolean onMenuItemClick(MenuItem item){

            switch(item.getItemId()) {
//                Open wikipedia
                case R.id.menu3: {
                    View v = item.getActionView();
                    listener.onClick(v, getAdapterPosition());
                    break;
                }
                case R.id.menu4:
//                Open Enlarged image activity to show full screen image
                {   Intent intent = new Intent(_context, EnlargedImage.class);
                    intent.putExtra("position", getAdapterPosition());
                    _context.startActivity(intent);
                    break;
                }
            }
            return true;
        }
    };


    }
}
