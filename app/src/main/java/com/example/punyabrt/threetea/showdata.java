package com.example.punyabrt.threetea;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.punyabrt.threetea.Databasetea.DatabaseTea;
import com.example.punyabrt.threetea.adapter.TealistAdapter;
import com.example.punyabrt.threetea.model.TeaItem;

import java.util.ArrayList;
import java.util.List;


import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.ID;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.NAME;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.PRICE;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.LOCATION;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.TABLE_NAME;


public class showdata extends AppCompatActivity {

    private DatabaseTea mHelper;
    private SQLiteDatabase mDb;
    private List<TeaItem> teaItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        mHelper = new DatabaseTea(showdata.this);
        mDb = mHelper.getWritableDatabase();


        Button add = findViewById(R.id.addmenu_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(showdata.this, AddMenu.class);
                startActivity(i);
            }
        });


        Button back = findViewById(R.id.backTeaIn_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(showdata.this, Threetea_home.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPhoneData();
        setupListView();
    }

    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
        teaItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(ID));
            String name = c.getString(c.getColumnIndex(NAME));
            String price = c.getString(c.getColumnIndex(PRICE));
            String location = c.getString(c.getColumnIndex(LOCATION));

            TeaItem item = new TeaItem(id, name, price, location);
            teaItemList.add(item);
        }
        System.out.println(c.getCount());
        c.close();
    }

    private void setupListView() {
        TealistAdapter adapter = new TealistAdapter(
                showdata.this,
                R.layout.activity_tea_item,
                teaItemList
        );
        ListView lv = findViewById(R.id.list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TeaItem item = teaItemList.get(position);

                Toast t = Toast.makeText(showdata.this, item.name, Toast.LENGTH_SHORT);
                t.show();


            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                String[] items = new String[]{
                        "Edit",
                        "Delete"
                };

                new AlertDialog.Builder(showdata.this)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final TeaItem TeaItem = teaItemList.get(position);

                                switch (i) {
                                    case 0: // Edit
                                        Intent intent = new Intent(showdata.this, EditMenu.class);
                                        intent.putExtra("name", TeaItem.name);
                                        intent.putExtra("price", TeaItem.price);
                                        intent.putExtra("location", TeaItem.location);
                                        intent.putExtra("id", TeaItem.id);
                                        startActivity(intent);
                                        break;
                                    case 1: // Delete
                                        new AlertDialog.Builder(showdata.this)
                                                .setMessage("ต้องการลบรายการ ใช่หรือไม่")
                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        mDb.delete(
                                                                TABLE_NAME,
                                                                ID + " = ?",
                                                                new String[]{String.valueOf(TeaItem.id)}
                                                        );
                                                        loadPhoneData();
                                                        setupListView();
                                                    }
                                                })
                                                .setNegativeButton("No", null)
                                                .show();
                                        break;
                                }
                            }
                        })
                        .show();

                return true;
            }
        });
    }



    }
