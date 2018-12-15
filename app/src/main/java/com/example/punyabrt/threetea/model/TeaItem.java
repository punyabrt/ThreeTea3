package com.example.punyabrt.threetea.model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.punyabrt.threetea.R;

import java.util.Locale;

public class TeaItem {

    public final long id;
    public final String name;
    public final String price;
    public final String location;

    public TeaItem(long id, String name, String price, String location) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.location= location;
    }

    @Override
    public String toString() {
        String msg = String.format(
                Locale.getDefault(),
                "%s (%s)",
                this.id,
                this.name,
                this.location
        );
        return msg;
    }
}
