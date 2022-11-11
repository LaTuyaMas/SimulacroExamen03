package com.example.simulacroexamen03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.simulacroexamen03.constantes.Constantes;
import com.example.simulacroexamen03.databinding.ActivityCrearPartidoBinding;
import com.example.simulacroexamen03.modelos.Partido;

public class CrearPartidoActivity extends AppCompatActivity {

    private ActivityCrearPartidoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearPartidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Partido p;
                if ( (p = partidoRelleno()) != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.PARTIDO, p);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(CrearPartidoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Partido partidoRelleno(){
        if (binding.txtGoles1CrearPartido.getText().toString().isEmpty() ||
            binding.txtGoles2CrearPartido.getText().toString().isEmpty() ||
            binding.txtResumenCrearPartido.getText().toString().isEmpty() ||
            binding.spEquipo1CrearPartido.getSelectedItemPosition() == 0 ||
            binding.spEquipo2CrearPartido.getSelectedItemPosition() == 0) {
            return  null;
        }

        String equipo1 = binding.spEquipo1CrearPartido.getSelectedItem().toString();
        String equipo2 = binding.spEquipo2CrearPartido.getSelectedItem().toString();
        int goles1 = Integer.parseInt(binding.txtGoles1CrearPartido.getText().toString());
        int goles2 = Integer.parseInt(binding.txtGoles2CrearPartido.getText().toString());
        String resumen = binding.txtResumenCrearPartido.getText().toString();

        return new Partido(equipo1, equipo2, goles1, goles2, resumen);
    }
}