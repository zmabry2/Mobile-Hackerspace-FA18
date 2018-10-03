package com.example.zach.cs196fall2018;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this.getApplicationContext();
        final Button next = findViewById(R.id.button);
        final Button add = findViewById(R.id.increment);
        final EditText name = findViewById(R.id.editText);
        final EditText email = findViewById(R.id.editText2);
        final EditText phone = findViewById(R.id.editText3);
        final EditText address = findViewById(R.id.editText4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, NewActivity.class);
                String extra = "This is a new activity";
                mIntent.putExtra("EXTRA_DATA", extra);
                startActivity(mIntent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person p = new Person();
                p.setName(name.getText().toString());
                p.setEmail(email.getText().toString());
                p.setPhone(phone.getText().toString());
                p.setAddress(address.getText().toString());
                Person.people.add(p);
                Toast.makeText(context, "Person Added", Toast.LENGTH_LONG).show();
            }
        });
    }
}
