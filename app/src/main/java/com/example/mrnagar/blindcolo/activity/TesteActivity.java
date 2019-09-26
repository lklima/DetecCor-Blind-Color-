package com.example.mrnagar.blindcolo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mrnagar.blindcolo.R;
import com.example.mrnagar.blindcolo.adapter.CustomViewPager;
import com.example.mrnagar.blindcolo.adapter.ImageAdapter;

public class TesteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private int [] images = {R.drawable.daltonismo_3, R.drawable.daltonismo_4, R.drawable.daltonismo_5, R.drawable.daltonismo_6, R.drawable.daltonismo_1, R.drawable.daltonismo_2};
    private String[] certo = {"16", "42", "2", "5", "10", "29"};
    private String[] errado = {"19", "72", "7", "6", "11", "70"};
    private ImageAdapter imageAdapter;
    private int c = 1, count = 1;
    private Button bt_certo, bt_errado;
    CustomViewPager viewPager;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        toolbar = findViewById(R.id.toolbar_main);
        viewPager = findViewById(R.id.viwpager);
        bt_certo = findViewById(R.id.bt_certo);
        bt_errado = findViewById(R.id.bt_errado);

        bt_certo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextImage(view);
                c++;
            }
        });

        toolbar.setTitle("Teste Rápido");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imageAdapter = new ImageAdapter(this, images);
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(imageAdapter);
    }

    public void nextImage(View v){
        viewPager.setCurrentItem(getItem(+1), true);
            bt_errado.setText(errado[viewPager.getCurrentItem()]);
            bt_certo.setText(certo[viewPager.getCurrentItem()]);
            count ++;
            if (count==7){
            if (c ==6){

                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.alert1, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Resultado")
                        .setView(view)
                        .setPositiveButton("Refazer o Teste", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(TesteActivity.this, TesteActivity.class));
                            }
                        }).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setCancelable(false);
                alerta = builder.create();
                alerta.show();
            }else{
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.alert2, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Resultado")
                        .setView(view)
                        .setPositiveButton("Refazer o Teste", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(TesteActivity.this, TesteActivity.class));
                            }
                        }).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setCancelable(false);
                alerta = builder.create();
                alerta.show();
            }
        }

    }

    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }
}
