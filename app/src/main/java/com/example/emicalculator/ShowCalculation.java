package com.example.emicalculator;

import static java.lang.Math.round;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ShowCalculation extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormat df2 = new DecimalFormat("0");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_calculation);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        double p = bundle.getDouble("PRI");
        double a = bundle.getDouble("AMO");
        a *= 12;
        double i = bundle.getDouble("INT");
        i /= 1200;

        double emi = Math.round((p * ((i * Math.pow((1 + i), a)) /
                (Math.pow((1 + i), a) - 1))) * 100.0) / 100.0;
        double totalInterest = Math.round(((emi * a) - p) * 100.0) / 100.0;

        TextView resultsView = (TextView) findViewById(R.id.resultsView);
        resultsView.setText("For a mortgage of $" + df.format(p) + " over " + df2.format((a/12)) +
                " years at " + (i * 1200) + "%:\n\n" + "Your monthly payment is:\n$" + emi +
                "\nYour total interest is:\n$" + df.format(totalInterest));
    }

    public void launchMainActivity(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}