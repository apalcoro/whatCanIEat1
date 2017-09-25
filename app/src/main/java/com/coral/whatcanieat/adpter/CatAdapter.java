package com.coral.whatcanieat.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coral.whatcanieat.R;


/**
 * Created by hackeru on 13/08/2017.
 */

public class CatAdapter extends BaseAdapter {
    private final String [] names;//Raw data
    private final Context context;//Context
    //Dependency Injection to constructor of raw data & context
    public CatAdapter(String [] names, Context context){
        this.names = names;
        this.context = context;
    }
    @Override//How many items (Strings) in raw data
    public int getCount() {
        return names.length;
    }
    @Override//Raw data item by index (position)
    public String getItem(int i) {
        return names[i];
    }
    @Override//Id of raw data item
    public long getItemId(int i) {
        return i;
    }
    @Override//Actual view - that is shown on the screen
    public View getView(int i, View recycView, ViewGroup viewGroup) {
        //if recycled view not exists -> create
        if(recycView == null) recycView = LayoutInflater.from(context).inflate(R.layout.dish_catgori, viewGroup, false);
        //use recycled view and assign relevant text
        ((TextView)recycView).setText(getItem(i));
        return recycView;//show on the screen
    }
}
