package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{
        private EditText weightEntry;
        private EditText heightEntry;
        private TextView resultView;
        private String valueHeight;
        private String valueWeight;
        private String result;
        private String category;
        private double bmiCalcImp;
        private double bmiCalcMet;
        private double weightValue;
        private double heightValue;
        private RadioButton inch;
        private RadioButton cm;
        private RadioButton pounds;
        private RadioButton kgs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            weightEntry = findViewById(R.id.weight);
            heightEntry = findViewById(R.id.height);
            resultView = findViewById(R.id.results);
            Button calcButton = findViewById(R.id.calc);
            inch = findViewById(R.id.inch);
            cm = findViewById(R.id.cm);
            pounds = findViewById(R.id.pounds);
            kgs = findViewById(R.id.kgs);

            calcButton.setOnClickListener(view -> {

                valueWeight = weightEntry.getText().toString();
                valueHeight = heightEntry.getText().toString();

                if ((inch.isChecked() && pounds.isChecked()) && (!valueHeight.equals("") || !valueWeight.equals(""))) {

                    weightValue = Double.parseDouble(valueWeight);
                    heightValue = Double.parseDouble(valueHeight);

                    bmiCalcImp = (weightValue / Math.pow(heightValue, 2)) * 703;

                    if (bmiCalcImp > 30) {
                        category = "Obese";
                    }
                    if (bmiCalcImp < 18.5) {
                        category = "Underweight";
                    }
                    if (bmiCalcImp >= 25 && bmiCalcImp <= 29.9) {
                        category = "Overweight";
                    }
                    if (bmiCalcImp >= 18.5 && bmiCalcImp <= 24.9) {
                        category = "Normal Weight";
                    }
                    result = "Your BMI is: " + (String.format(Locale.ENGLISH, "%.2f", bmiCalcImp)) +
                            "\nCategory = " + category;

                    resultView.setText(result);


                } else if (cm.isChecked() && kgs.isChecked() && (!valueHeight.equals("") || !valueWeight.equals(""))) {

                    weightValue = Double.parseDouble(valueWeight);
                    heightValue = Double.parseDouble(valueHeight);

                    double meters = heightValue / 100;

                    bmiCalcMet = (weightValue / (Math.pow(meters, 2)));

                    if (bmiCalcMet > 30) {
                        category = "Obese";
                    }
                    if (bmiCalcMet < 18.5) {
                        category = "Underweight";
                    }
                    if (bmiCalcMet >= 25 && bmiCalcMet <= 29.9) {
                        category = "Overweight";
                    }
                    if (bmiCalcMet >= 18.5 && bmiCalcMet <= 24.9) {
                        category = "Normal Weight";
                    }

                    result = "Your BMI is: " + String.format(Locale.ENGLISH, "%.2f", bmiCalcMet) +
                            "\nCategory = " + category;
                    resultView.setText(result);

                } else {
                    Toast.makeText(MainActivity.this, R.string.invalid_nums, Toast.LENGTH_SHORT).show();
                    weightEntry.getText().clear();
                    heightEntry.getText().clear();
                    resultView.setText(R.string.values);
                }
            });

        }
}