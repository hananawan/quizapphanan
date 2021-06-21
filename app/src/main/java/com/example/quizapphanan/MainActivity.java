package com.example.quizapphanan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quizapphanan.adapters.quizadapter;
import com.example.quizapphanan.models.questions;
import com.example.quizapphanan.models.quiz;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    //private ImageView image;
    ActionBarDrawerToggle actionBarDrawerToggle;
    quizadapter adapter;
    FloatingActionButton dPicker;
    GridLayoutManager gridLayoutManager;
    private List<quiz> quiz_list = new ArrayList<>();
    RecyclerView c_view;
    FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c_view = findViewById(R.id.quizRecyclerView);
        dPicker=findViewById(R.id.btnDatePicker);

        setUpView();
        populatedummydata();


    }

    private void populatedummydata(){
        quiz_list.add(new quiz("12-10-2021","MAD"));
        quiz_list.add(new quiz("12-10-2021","Web-Tech"));
        quiz_list.add(new quiz("12-10-2021","OS"));
        quiz_list.add(new quiz("12-10-2021","OS-Lab"));
        quiz_list.add(new quiz("12-10-2021","CN"));
        quiz_list.add(new quiz("12-10-2021","CN-L"));
        quiz_list.add(new quiz("12-10-2021","IR"));
        quiz_list.add(new quiz("12-10-2021","DSA"));
        quiz_list.add(new quiz("12-10-2021","DSA-L"));
        quiz_list.add(new quiz("12-10-2021","AOA"));
    }

    void setUpView() {
        //setUpFirestore();
        setUpLayout();
        SetUpRecyclerview();
        setUpDatePicker();


    }

    /*private void setUpFirestore() {
        database=FirebaseFirestore.getInstance();
        CollectionReference collectionReference = database.collection("quizzes");
        collectionReference.addSnapshotListener((value, error) -> {
            if(value == null || error!=null){
                Toast.makeText(MainActivity.this,"Error! Fetching the data!", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.d("DATA", value.toObjects(quiz.class).toString());
            quiz_list.clear();
            quiz_list.addAll(value.toObjects(quiz.class));
            adapter.notifyDataSetChanged();
        });
    }*/

    void SetUpRecyclerview() {
        adapter = new quizadapter(this, quiz_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //recyclerView.setAdapter(new quizadapter(this, quiz_list ));
        c_view.setAdapter(adapter);
        c_view.setLayoutManager(gridLayoutManager);
    }

    private void  setUpDatePicker(){
        dPicker.setOnClickListener(v -> {
            MaterialDatePicker<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker().build();
            materialDateBuilder.show(getSupportFragmentManager(),"DatePicker");
            materialDateBuilder.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                //recyclerView.setAdapter(new quizadapter(this, quiz_list ));
                @Override
                public void onPositiveButtonClick(Long selection) {
                    Log.d("DatePicker", materialDateBuilder.getHeaderText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    String date = dateFormat.format(new Date(selection));
                    Intent intent = new Intent(MainActivity.this, questionactivity.class); //will be shifted to question activity
                    intent.putExtra("DATE", date);
                    MainActivity.this.startActivity(intent); } });
            materialDateBuilder.addOnNegativeButtonClickListener(selection -> {
                Log.d("Date", materialDateBuilder.getHeaderText());
            });
            materialDateBuilder.addOnCancelListener(selection -> {
                Log.d("Date", "Date Picker closed");
            });
        });
    }

    void setUpLayout() {
        setSupportActionBar(findViewById(R.id.appBar));
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, findViewById(R.id.mainDrawer),R.string.app_name , R.string.app_name);
        actionBarDrawerToggle.syncState();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}




