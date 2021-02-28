package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {
    EditText evName,evNumber;
    DbHelper dbHelper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        evName = findViewById(R.id.ev_name_editContact);
        evNumber = findViewById(R.id.ev_number_editContact);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("save").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Contact ct = new Contact();
                ct.setName(evName.getText().toString());
                ct.setNumber(evNumber.getText().toString());
                ct.setId(dbHelper.getAllObject().size() + 1);
                dbHelper.InsertToDB(ct);
                startActivity(new Intent(EditContact.this,MainActivity.class));
                finish();
                return false;
            }
        }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);

    }
}