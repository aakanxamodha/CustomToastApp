package com.example.customtoast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.bank_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bank_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedBank = parent.getItemAtPosition(position).toString();
                showCustomToast(selectedBank);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //without this, it shows an error
            }
        });
    }

    private void showCustomToast(String bankName) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));

        ImageView logo = layout.findViewById(R.id.toast_logo);
        TextView ifsc = layout.findViewById(R.id.toast_ifsc);

        if (bankName.equals("SBI")) {
            logo.setImageResource(R.drawable.sbi);
            ifsc.setText("SBI001");
        } else if (bankName.equals("ICICI")) {
            logo.setImageResource(R.drawable.icici);
            ifsc.setText("ICICI002");
        } else if (bankName.equals("HDFC")) {
            logo.setImageResource(R.drawable.hdfc);
            ifsc.setText("HDFC003");
        } else if (bankName.equals("BOB")) {
            logo.setImageResource(R.drawable.bob);
            ifsc.setText("BOB004");
        }

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
