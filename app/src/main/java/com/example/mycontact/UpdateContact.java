package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class UpdateContact extends AppCompatActivity {
    EditText evName,evNumber;
    int id;
    DbHelper dbHelper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        final Bundle argss = getIntent().getExtras();
        evName = findViewById(R.id.ev_name_editContact);
        evNumber = findViewById(R.id.ev_number_editContact);
        evName.setText(argss.getString("name"));
        evNumber.setText(argss.getString("number"));
        id = argss.getInt("id");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("save").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ContentValues values = new ContentValues();
                values.put("name",evName.getText().toString().trim());
                values.put("number",evNumber.getText().toString().trim());
                dbHelper.Update(id,values);
                startActivity(new Intent(UpdateContact.this,MainActivity.class));
                finish();
                return false;
            }
        }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);

    }
}