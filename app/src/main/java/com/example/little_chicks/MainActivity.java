package com.example.little_chicks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    EditText txtData;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtData =findViewById(R.id.txtData);
        btnAdd =findViewById(R.id.btnAdd);
        listview =findViewById(R.id.listview);
        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview , list   );
        listview.setAdapter(adapter);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("shops");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                   // Products products = snapshot1.getValue(Products.class);
                 //   String txt = products.getName()+" "+products.getColor();
                 //   Shops shop = snapshot1.getValue(Shops.class);
                   // String txt = shop.getName() +" "+shop.getAdr();
                  //  list.add(txt);
                    for(DataSnapshot snapshot2 : snapshot1.getChildren()){
                        for (DataSnapshot snapshot3 : snapshot2.getChildren())
                        {
                            list.add(snapshot3.getValue().toString());
//                            for(int i = 0;i<list.size();i++)
//                            {
                                String[] product = list.get(0).split(",");
                                txtData.setText(product[0].toString());
//                            }
                        }
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}