package com.example.emicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void launchShowCalculation(View v) {

        Intent i = new Intent(this, ShowCalculation.class);

        EditText principalText = (EditText) findViewById(R.id.principalText);
        EditText amortizationText = (EditText) findViewById(R.id.amortizationText);
        EditText interestText = (EditText) findViewById(R.id.interestText);

        double principal = Double.parseDouble(principalText.getText().toString());
        double amortization = Double.parseDouble(amortizationText.getText().toString());
        double interest = Double.parseDouble(interestText.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putDouble("PRI", principal);
        bundle.putDouble("AMO", amortization);
        bundle.putDouble("INT", interest);

        i.putExtras(bundle);

        startActivity(i);
    }
}