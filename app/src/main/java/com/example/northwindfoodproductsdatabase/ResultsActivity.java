package com.example.northwindfoodproductsdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.LinearLayout.VERTICAL;

public class ResultsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent NorthwindsIntent = getIntent();
        String Q = NorthwindsIntent.getStringExtra("Query");
        ArrayList<Row> rows= queryDatabase(Q);
        RecyclerView rv = (RecyclerView) findViewById(R.id.QueryResults);
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        rv.addItemDecoration(decoration);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RowsAdapter adapter = new RowsAdapter(rows, this);
        rv.setAdapter(adapter);
    }

    ArrayList<Row> queryDatabase(String Query) {
        ArrayList<Row> QueryResult = new ArrayList<Row>();
        try {
            SQLiteDatabase db = openOrCreateDatabase("northwind.db", Context.MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery(Query, null);
            while (cursor.moveToNext()) {
                Row r = new Row("", "", "");
                if(cursor.getColumnCount()==1)
                    r = new Row(cursor.getString(0), "", "");
                else if(cursor.getColumnCount()==2)
                    r = new Row(cursor.getString(0), cursor.getString(1), "");
                else if(cursor.getColumnCount()>2)
                    r = new Row(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                QueryResult.add(r);
            }
            cursor.close();
            db.close();
        } catch (Exception NorthwindsError){
            Toast.makeText(getApplicationContext(), "Error opening/querying Database", Toast.LENGTH_LONG).show();
        }
        return QueryResult;
    }
}