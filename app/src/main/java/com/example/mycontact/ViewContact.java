package com.example.mycontact;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearSmoothScroller;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewContact extends AppCompatActivity {
    TextView tvName,tvPhone;
    Button btnDelete,btnEdit;
    DbHelper dbHelper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_contact_second);
        final Bundle args = getIntent().getExtras();
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        tvName = findViewById(R.id.tv_viewContact_name);
        tvPhone = findViewById(R.id.tv_viewContact_number);
        tvName.setText(args.getString("name"));
        tvPhone.setText(args.getString("number"));
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dbHelper.DeleteObject(args.getInt("id"));
                Intent intent = new Intent(ViewContact.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewContact.this,UpdateContact.class);
                intent.putExtra("name",args.getString("name"));
                intent.putExtra("number",args.getString("number"));
                intent.putExtra("id",args.getInt("id"));
                startActivity(intent);
                finish();
            }
        });
    }
}