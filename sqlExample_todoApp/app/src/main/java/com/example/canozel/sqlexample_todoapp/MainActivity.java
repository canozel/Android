package com.example.canozel.sqlexample_todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    EditText etAdd;
    ListView lvList;
    ArrayList<HashMap<String, String>> todo_list;
    String todo[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        etAdd = (EditText) findViewById(R.id.etAdd);
    }

    @Override
    public void onResume(){
        super.onResume();
        DBHelper db = new DBHelper(getApplicationContext());
        todo_list = db.list();
        if(todo_list.size()==0){
            Toast.makeText(getApplicationContext(), "Henüz Eklenmemiş.\nYukarıdaki Ekle Butonundan Ekleyiniz", Toast.LENGTH_LONG).show();
            todo = new String[]{""};
        }else {
            todo = new String[todo_list.size()];
            for (int i = 0; i < todo_list.size(); i++) {
                todo[i] = todo_list.get(i).get("name");
            }
        }

        lvList =(ListView) findViewById(R.id.lvList);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, todo);
        lvList.setAdapter(adapter);

    }

    @Override
    protected void onRestart() {

        super.onRestart();
        Intent i = new Intent(MainActivity.this, MainActivity.class);  //your class
        startActivity(i);
        finish();

    }

    public void addTodo(View view) {
        String todo = etAdd.getText().toString();
        if(todo.matches("")){
            Toast.makeText(getApplicationContext(), "Boş Giremezsiniz!", Toast.LENGTH_SHORT).show();
        }else{
            DBHelper db = new DBHelper(getApplicationContext());
            db.kitapEkle(todo);
            db.close();
            Toast.makeText(getApplicationContext(), "Başarıyla Eklendi", Toast.LENGTH_SHORT).show();
            etAdd.setText("");
        }
        onRestart();
    }
}
