package com.example.java_midterm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.java_midterm_project.R;

import java.util.List;
import java.util.ArrayList;

public class ConvertorActivity extends AppCompatActivity {

    EditText decimal_input, byte_input, celcius_input;
    Spinner decimal_spinner, byte_spinner;
    TextView decimal_sonuc, byte_sonuc, celcius_sonuc;
    RadioGroup radio_group;
    RadioButton fahrenheit_button, kelvin_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        decimal_input = findViewById(R.id.decimal_input);
        byte_input = findViewById(R.id.byte_input);
        celcius_input = findViewById(R.id.celcius_input);
        decimal_spinner = findViewById(R.id.decimal_spinner);
        byte_spinner = findViewById(R.id.byte_spinner);
        decimal_sonuc = findViewById(R.id.decimal_sonuc);
        byte_sonuc = findViewById(R.id.byte_sonuc);
        celcius_sonuc = findViewById(R.id.celcius_sonuc);
        radio_group = findViewById(R.id.radio_group);
        fahrenheit_button = findViewById(R.id.fahrenheit_button);
        kelvin_button = findViewById(R.id.kelvin_button);

        List<String> decimal_categories = new ArrayList<>();
        decimal_categories.add("Binary");
        decimal_categories.add("Octal");
        decimal_categories.add("Hexadecimal");

        ArrayAdapter<String> decimal_list = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, decimal_categories);
        decimal_list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        decimal_spinner.setAdapter(decimal_list);

        decimal_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected_decimal_category = decimal_categories.get(position);
                String decimal_input_value = decimal_input.getText().toString();
                convertAndDisplayResult(selected_decimal_category, decimal_input_value);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        List<String> byte_categories = new ArrayList<>();
        byte_categories.add("KiloByte");
        byte_categories.add("Byte");
        byte_categories.add("KibiByte");


        ArrayAdapter<String> byte_list = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, byte_categories);
        byte_list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        byte_spinner.setAdapter(byte_list);

        byte_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_byte_category = byte_categories.get(position);
                String byte_input_value = byte_input.getText().toString();
                convertAndDisplayResult2(selected_byte_category, byte_input_value);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int radio_group) {
                RadioButton tiklanan_buton = findViewById(radio_group);
                String conversionType = tiklanan_buton.getText().toString();
                String celcius_input_value = celcius_input.getText().toString();
                convertAndDisplayResult3(celcius_input_value, conversionType);
            }
        });
    }

    private void convertAndDisplayResult(String decimal_input_value, String selected_decimal_category){
        if(decimal_input_value.isEmpty()){
            Toast.makeText(getApplicationContext(),"Lütfen boş değer girmeyiniz.", Toast.LENGTH_SHORT ).show();
            return;
        }
        try {
            int decimal_number = Integer.parseInt(decimal_input_value);
            String result = "";
            switch (selected_decimal_category){
                case "Binary":
                    result = Integer.toBinaryString(decimal_number);
                    break;
                case "Octal":
                    result = Integer.toOctalString(decimal_number);
                    break;
                case "Hexadecimal":
                    result = Integer.toHexString(decimal_number);
                    break;
            }
            decimal_sonuc.setText(result);
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Integer bir sayı giriniz", Toast.LENGTH_SHORT).show();
        }
    }
    private void convertAndDisplayResult2(String byte_input_value, String selected_byte_category){
        if(byte_input_value.isEmpty()){
            Toast.makeText(getApplicationContext(),"Lütfen boş değer girmeyiniz.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int decimal_number_2 = Integer.parseInt(byte_input_value);
            String result2 = "";
            switch (selected_byte_category){
                case "kiloByte":
                    double kilobyte = decimal_number_2 / 1024;
                    result2 = String.valueOf(kilobyte);
                    break;
                case "byte":
                    double bytee = decimal_number_2 ;
                    result2 = String.valueOf(bytee);
                    break;
                case "kibiByte":
                    double kibibyte = decimal_number_2 * 1000;
                    result2 = String.valueOf(kibibyte);
                    break;
            }
            byte_sonuc.setText(result2);

        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Integer bir sayı giriniz", Toast.LENGTH_SHORT).show();
        }
    }
    private void convertAndDisplayResult3(String celcius_input_value, String conversionType){
        if(celcius_input_value.isEmpty()){
            Toast.makeText(getApplicationContext(),"Lütfen boş değer girmeyiniz.",Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double tempature = Double.parseDouble(celcius_input_value);
            String result3="";
            if(conversionType.equals("Fahrenheit")){

                double fahrenheit = (tempature *9/5)+32;
                result3 = String.valueOf(fahrenheit);

            } else if (conversionType.equals("Kelvin")) {

                double kelvin = tempature +273.15;
                result3 = String.valueOf(kelvin);
            }
            celcius_sonuc.setText(result3);
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Double sayı giriniz.",Toast.LENGTH_SHORT).show();
        }
    }
}