package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper = new DbHelper(this);
    ListView listView;
    public AdapterDb adapter;
    public List<Contact> ctList1 = new ArrayList<>();
    FloatingActionButton fabAdd;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        fabAdd = findViewById(R.id.floatingAction);
        searchView = findViewById(R.id.searchView);
        ctList1 = dbHelper.getAllObject();
        show();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ctList1 = dbHelper.getObeject(Contact.KEY_NAME + " LIKE'%" + query + "%'",null,null);
                show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ViewContact.class);
                intent.putExtra("name",ctList1.get(position).getName());
                intent.putExtra("number",ctList1.get(position).getNumber());
                intent.putExtra("id",ctList1.get(position).getId());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EditContact.class));
                // okgit remote add origin https://github.com/mdsehat/MyContact_test.git
            }
        });
    }

    private void getJson() throws IOException, JSONException {
        InputStream stream = getResources().openRawResource(R.raw.contacts);
        List<Contact> ctList = JsonPullParser.parser(stream);
        for (int i=0;i<ctList.size(); i++){
            dbHelper.InsertToDB(ctList.get(i));
        }

    }
    public void show() {
        adapter = new AdapterDb(this,ctList1);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("A-Z").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ctList1 = dbHelper.getObeject(null,null, Contact.KEY_NAME + " ASC");
                show();
                return false;
            }
        }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
    private void a(){}
}