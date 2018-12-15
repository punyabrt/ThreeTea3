package com.example.punyabrt.threetea.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.punyabrt.threetea.R;
import com.example.punyabrt.threetea.model.TeaItem;

import java.util.List;

public class TealistAdapter extends ArrayAdapter<TeaItem> {
    private Context tContext;
    private int tResource;
    private List<TeaItem> teaItemList;

    public TealistAdapter(@NonNull Context context,
                          int resource,
                          @NonNull List<TeaItem> teaItemList) {
        super(context, resource, teaItemList);
        this.tContext = context;
        this.tResource = resource;
        this.teaItemList = teaItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) tContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(tResource, parent, false);

        TextView nameTextView = view.findViewById(R.id.nameitem_editText);
        TextView priceTextView = view.findViewById(R.id.priceitem_editText);
        TextView locationTextView = view.findViewById(R.id.locationitem_editText);

        TeaItem teaItem = teaItemList.get(position);
        String name = teaItem.name;
        String price = teaItem.price;
        String location = teaItem.location;
        nameTextView.setText(name);
        priceTextView.setText(price);
        locationTextView.setText(location);

        return view;
    }

}
