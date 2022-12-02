package com.ai.game.aajaoji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ai.game.aajaoji.StudentRoom.Student;
import com.ai.game.aajaoji.StudentRoom.StudentViewModel;

public class ScanResult extends AppCompatActivity {

    StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        String result = getIntent().getStringExtra("Result");
        Student student = studentViewModel.getStudent(result);

        ((TextView)findViewById(R.id.textView)).setText(student.name);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(intent);
    }
}