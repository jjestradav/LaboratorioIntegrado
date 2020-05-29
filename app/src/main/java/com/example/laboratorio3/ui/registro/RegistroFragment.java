package com.example.laboratorio3.ui.registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.laboratorio3.R;

public class RegistroFragment extends Fragment {

    private RegistroViewModel registroViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registroViewModel =
                ViewModelProviders.of(this).get(RegistroViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registro, container, false);

        return root;
    }
}
