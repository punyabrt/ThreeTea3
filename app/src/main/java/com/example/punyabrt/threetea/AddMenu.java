package com.example.punyabrt.threetea;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.punyabrt.threetea.Databasetea.DatabaseTea;


import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.NAME;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.PRICE;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.LOCATION;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.TABLE_NAME;


import com.example.punyabrt.threetea.Databasetea.DatabaseTea;

public class AddMenu extends AppCompatActivity {

    private DatabaseTea mHelper;
    private SQLiteDatabase mDb;
    private String mLogoFilename = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        mHelper = new DatabaseTea(this);
        mDb = mHelper.getWritableDatabase();

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doInsertPhoneItem();
            }
        });

        Button back = findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddMenu.this, showdata.class);
                startActivity(i);
            }
        });

    }

    private void doInsertPhoneItem() {
        EditText nameEditText = findViewById(R.id.name_editText);
        EditText priceEditText = findViewById(R.id.price_editText);
        EditText locationEditText = findViewById(R.id.location_editText);

        String name = nameEditText.getText().toString();
        String price = priceEditText.getText().toString();
        String location = locationEditText.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(PRICE, price);
        cv.put(LOCATION, location);
        mDb.insert(TABLE_NAME, null, cv);

        finish();
    }
    }

