package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.MainActivityViewModel;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainConstraintBinding;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    private ActivityMainConstraintBinding binding;
    boolean ischked;
    String state;

    MainActivityViewModel viewModel;
    @Override  //This starts the application
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding = ActivityMainConstraintBinding.inflate( getLayoutInflater() );
        setContentView(  binding.getRoot() );

        binding.switch2.setOnCheckedChangeListener( (btn, isChecked ) -> {
            if (isChecked) {
                ischked = true;
                state = "on";
            }
            else {
                ischked = false;
                state = "off";
            }
            viewModel.isSelected.postValue( isChecked );
            Toast.makeText(MainActivity.this, "The switch is now " + state, Toast.LENGTH_LONG).show();
            Snackbar.make(binding.switch2, "Undo?", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", click -> {
                    viewModel.isSelected.postValue(isChecked);
                    binding.switch2.setChecked(!ischked);
                    }).show();

        } );
        binding.button2.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, this.getString(R.string.toast_message), Toast.LENGTH_LONG).show();
        });

    }
}