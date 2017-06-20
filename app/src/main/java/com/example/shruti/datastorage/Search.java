package com.example.shruti.datastorage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Search extends AppCompatActivity {

    EditText search;
    TextView op;
    String SEARCH;
    Button searchbtn;
    Context ctx= this;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search= (EditText) findViewById(R.id.editText);
        searchbtn= (Button)findViewById(R.id.button4);


        searchbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SEARCH= search.getText().toString();
                DatabaseHelper db= new DatabaseHelper(ctx);
                sqLiteDatabase= db.getReadableDatabase();
                Cursor cursor= db.getData(sqLiteDatabase, SEARCH);
                if (cursor != null && cursor.getCount()>0){
                    cursor.moveToFirst();
                    String name= cursor.getString(0);
                    String desc= cursor.getString(1);
                    String price= cursor.getString(2);
                    String review= cursor.getString(3);

                    TextView op= (TextView)findViewById(R.id.textView6);
                    op.setText(name);
                    op.setText(desc);
                    op.setText(price);
                    op.setText(review);
                    op.setText("Search Results:" + "\n"+ "Name:"+ name+ "\n"+ "Description:"+ desc +
                    "\n" + "Price:" + price +"\n"+ "Review:"+ review + "\n");
                    op.setMovementMethod(new ScrollingMovementMethod());
                }

                else {
                    TextView op= (TextView) findViewById(R.id.textView6);
                    op.setText("Not Found");
                }
            }

        });
    }

    public void close(View v){
        Search.this.finish();
    }
}
