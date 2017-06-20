package com.example.shruti.datastorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertItem extends AppCompatActivity {
    EditText editTextName, editTextdesc, editTextPrice, editTextReview;
    Button addbutton;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_item);

        editTextName= (EditText)findViewById(R.id.editText);
        editTextdesc= (EditText)findViewById(R.id.editText2);
        editTextPrice= (EditText)findViewById(R.id.editText3);
        editTextReview= (EditText)findViewById(R.id.editText4);
        addbutton= (Button)findViewById(R.id.button3);

        addbutton.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= editTextName.getText().toString();
                String desc= editTextdesc.getText().toString();
                String price= editTextPrice.getText().toString();
                String review= editTextReview.getText().toString();

                DatabaseHelper DB= new DatabaseHelper(ctx);
                Boolean isInserted= DB.insertData(DB, name, desc, price, review);
                if (isInserted)
                    Toast.makeText(getBaseContext(), "Data inserted", Toast.LENGTH_LONG).show();
                 finish();
            }
        }
        );
    }

    public void close(View v){
        InsertItem.this.finish();
    }
}
