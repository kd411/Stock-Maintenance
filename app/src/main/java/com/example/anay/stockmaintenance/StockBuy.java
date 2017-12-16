package com.example.anay.stockmaintenance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class StockBuy extends AppCompatActivity {

    static ArrayList<ItemModel> buylist = new ArrayList<>();
    RecyclerView recyclerView;
    NoteAdapter itemAdapter;
    Button gohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_buy);
        buylist=readFromBuy();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        itemAdapter = new NoteAdapter(buylist);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gohome = (Button) findViewById(R.id.gohome);
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StockBuy.this, HomeActivity.class));
            }
        });
    }
    public ArrayList<ItemModel> readFromBuy(){
        String filename="Buy.txt";
        ArrayList<ItemModel> stocklist = new ArrayList<>();
        Gson gson=new Gson();
        try{
            File file=new File(getApplicationContext().getFilesDir(),filename);
            String line;
            BufferedReader br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                ItemModel item=gson.fromJson(line,ItemModel.class);
                stocklist.add(item);
            }
            br.close();
        }catch (Exception e){
            e.getMessage();
        }
        return stocklist;
    }
}