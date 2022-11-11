package com.example.simulacroexamen03;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.example.simulacroexamen03.adapters.partidoAdapter;
import com.example.simulacroexamen03.constantes.Constantes;
import com.example.simulacroexamen03.databinding.ActivityMainBinding;
import com.example.simulacroexamen03.modelos.Partido;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Partido> listaPartidos;
    private ActivityResultLauncher<Intent> launcherCrearPartido;

    private partidoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // INICIALIZAR ELEMENTOS
        listaPartidos = new ArrayList<>();

        int columnas;
        columnas = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;

        adapter = new partidoAdapter(MainActivity.this, listaPartidos, R.layout.partido_view_model);
        layoutManager = new GridLayoutManager(MainActivity.this, columnas);
        binding.contentMain.contenedor.setAdapter(adapter);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

        inicializarLaunchers();
        // YA NO SE INICIALIZAN MÁS ELEMENTOS

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearPartido.launch(new Intent(MainActivity.this, CrearPartidoActivity.class));
            }
        });
    }

    private void inicializarLaunchers(){
        launcherCrearPartido = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null) {
                                if (result.getData().getExtras() != null) {
                                    if (result.getData().getExtras().getSerializable(Constantes.PARTIDO) != null) {
                                        Partido p = (Partido) result.getData().getExtras().getSerializable(Constantes.PARTIDO);
                                        listaPartidos.add(0, p);
                                        adapter.notifyItemInserted(0);
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "El bundle no lleva el tag "+Constantes.PARTIDO, Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "NO HAY BUNDLE EN EL INTENT", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(MainActivity.this, "NO HAY INTENT EN EL RESULT", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Ventana Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}