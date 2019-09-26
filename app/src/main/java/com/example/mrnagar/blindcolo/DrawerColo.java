package com.example.mrnagar.blindcolo;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DrawerColo extends AppCompatActivity {

    private GridView img_colo;
    Bundle bundle = new Bundle();
    static ArrayList<Integer> img = new ArrayList<>();
    ArrayList<Integer> recent = new ArrayList<>();
    DrawerDataBase drawerDataBase = new DrawerDataBase(this);
    Cursor cursor;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_colo);

        bundle = getIntent().getExtras();
        int j = bundle.getInt("Color_int");
        if (img.size() == 0) {  //used to call saved last time img_color
            cursor = drawerDataBase.getColorData();
            while (cursor.moveToNext()) {
                img.add(Integer.valueOf(cursor.getString(0)));
            }
        }
        if(j != 0 ) {
            drawerDataBase.insertColor(j); // used to add in table
            img.add(j); //add in List. To retrive for Adapter.
        }
        for(int i = (img.size()-1);i>-1;i--){
            recent.add(img.get(i));
        }
        img_colo = findViewById(R.id.img_colo);
        img_colo.setAdapter(new Img_coloAdapter(this,R.layout.img_color,recent));
        toolbar = findViewById(R.id.toolbar_cor);
        toolbar.setTitle("CORES SALVAS");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public class Img_coloAdapter extends ArrayAdapter<Integer>{

        private LayoutInflater layoutInflater;
        private Resources resources;
        private ArrayList<Integer> imgs ;

        public Img_coloAdapter(Context context, int resource, ArrayList<Integer> imgcolors) {
            super(context, resource, imgcolors);
            layoutInflater = LayoutInflater.from(context);
            resources = getResources();
            imgs = imgcolors;
        }

        public Img_coloAdapter(@NonNull Context context, int resource, LayoutInflater layoutInflater) {
            super(context, resource);
            this.layoutInflater = layoutInflater;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            Holder holder = null;

            if(view == null){
                view = layoutInflater.inflate(R.layout.img_color,null);

                ImageView colorbck = view.findViewById(R.id.colorbck);
                TextView colorint = view.findViewById(R.id.colorint);

                holder = new Holder(colorbck,colorint);

                view.setTag(holder);
            }
            else {
                holder = (Holder) view.getTag();
            }
            try {
                holder.colorbck.setBackgroundColor(imgs.get(position));
                int color = imgs.get(position);
                String hexColor = String.format("#%06X",(0xFFFFFF & color ));
                holder.colorint.setText(hexColor);
            }catch (Exception e){}
            return view ;
        }
    }

    static  class Holder{

        public ImageView colorbck;
        public TextView colorint;

        public Holder(ImageView colorbck,TextView colorint){
            this.colorbck = colorbck;
            this.colorint = colorint;
        }
    }

}


