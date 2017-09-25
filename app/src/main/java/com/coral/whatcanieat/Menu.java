package com.coral.whatcanieat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.coral.whatcanieat.adpter.CatAdapter;
import com.coral.whatcanieat.adpter.NamesAdapter;

import java.util.List;
import java.util.Map;

public class Menu extends AppCompatActivity {
    Spinner spiner;
    List<Map> items;
    String text;
    ListView list;
    String[] cat = {"Cookies", "Desserts", "ice creams"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu);
        spiner = (Spinner) findViewById(R.id.ctgoriess);
        list = (ListView)findViewById(R.id.list);
        spiner.setAdapter(new NamesAdapter(cat, this));
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text = spiner.getSelectedItem().toString();

                Backendless.UserService.login("coral@gmail.com", "1234", new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        getDishes(text);

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });


            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


    }


    public void getDishes(String text) {
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("category = '" + text + "'");
        Backendless.Persistence.of("dishes").find(queryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {
                //do stuff here
                String []array=new String[response.size()];
                for(int i=0;i<response.size();i++) {
                    array[i] = response.get(i).toString();
                }
                list.setAdapter(new CatAdapter(array,getApplicationContext()));
                Toast.makeText(Menu.this, "Stuff", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //failed
                Log.e("error in dishes_____", fault.getDetail());
                Toast.makeText(Menu.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Backendless.UserService.logout();
    }
}

