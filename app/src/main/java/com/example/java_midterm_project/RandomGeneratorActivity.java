package com.example.java_midterm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import java.util.Random;

public class RandomGeneratorActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView progress_text;
    EditText random_maks, random_min;
    Button random_button;

    TextView random_sonuc;

    Random random;
    int min, maks,sonuc, progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_generator);

        random = new Random();


        progressBar = findViewById(R.id.progressBar);
        progress_text = findViewById(R.id.progress_text);
        random_maks = findViewById(R.id.random_maks);
        random_min = findViewById(R.id.random_min);
        random_button = findViewById(R.id.random_button);
        random_sonuc =findViewById(R.id.random_sonuc);

        generateRandomProgressBar();

        random_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempMin, tempMaks;
                tempMin = random_min.getText().toString();
                tempMaks = random_maks.getText().toString();
                if(!tempMin.equals("") && !tempMaks.equals("")) {
                    min = Integer.parseInt(tempMin);
                    maks = Integer.parseInt(tempMaks);
                    if(maks > min) {
                        sonuc = random.nextInt((maks - min) + 1) + min;
                        random_sonuc.setText("" + sonuc);
                        progress = (int) ((sonuc - min) / (float) (maks - min) * 100);
                        progressBar.setProgress(progress);
                        progress_text.setText("Progress: " + progress + "%");
                    }
                }

            }
        });
    }
    private void generateRandomProgressBar() {
        Random random = new Random();

        int randomValue = random.nextInt((maks - min) + 1) + min;
        int progress = (int) ((randomValue - min) / (float) (maks - min) * 100);

        progressBar.setProgress(progress);
        progress_text.setText("Progress: " + progress + "%");
    }
}

