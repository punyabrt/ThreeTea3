package com.example.punyabrt.threetea;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.punyabrt.threetea.Databasetea.DatabaseTea;

import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.ID;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.NAME;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.PRICE;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.LOCATION;
import static com.example.punyabrt.threetea.Databasetea.DatabaseTea.TABLE_NAME;


public class EditMenu extends AppCompatActivity {

    private EditText tNameEditText;
    private EditText tPriceEditText;
    private EditText tlocationEditText;
    private Button tSaveButton;

    private long bubble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String location = intent.getStringExtra("location");
        bubble = intent.getLongExtra("id", 0);

        tNameEditText = findViewById(R.id.rename_editText);
        tPriceEditText = findViewById(R.id.reprice_editText);
        tlocationEditText = findViewById(R.id.reprice_editText);
        tSaveButton = findViewById(R.id.save_button);

        tNameEditText.setText(name);
        tPriceEditText.setText(price);
        tlocationEditText.setText(location);
        tSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseTea helper = new DatabaseTea(EditMenu.this);
                SQLiteDatabase db = helper.getWritableDatabase();

                String newName = tNameEditText.getText().toString().trim();
                String newPrice = tPriceEditText.getText().toString().trim();
                String newLocation = tlocationEditText.getText().toString().trim();

                ContentValues cv = new ContentValues();
                cv.put(NAME, newName);
                cv.put(PRICE, newPrice);
                cv.put(LOCATION, newLocation);

                db.update(
                        TABLE_NAME,
                        cv,
                        ID + " = ?",
                        new String[]{String.valueOf(bubble)}
                );
                finish();
            }
        });
    }
    }

