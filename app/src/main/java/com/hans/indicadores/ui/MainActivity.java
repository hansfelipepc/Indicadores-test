package com.hans.indicadores.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.hans.indicadores.R;
import com.hans.indicadores.io.IndicadorService;
import com.hans.indicadores.model.Indicador;
import com.hans.indicadores.model.IndicadoresResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "INDICADORES";
    private Retrofit retrofit;
    private TextView tvUf, tvDolar, tvUtm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://mindicador.cl/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tvUf = (TextView) findViewById(R.id.tvUf);
        tvDolar = (TextView) findViewById(R.id.tvDolar);
        tvUtm = (TextView) findViewById(R.id.tvUtm);
        getData();


    }
    private void getData(){
        IndicadorService service = retrofit.create(IndicadorService.class);
        Call<IndicadoresResponse> indicadoresResponseCall = service.getIndicadorList();

        indicadoresResponseCall.enqueue(new Callback<IndicadoresResponse>() {
            @Override
            public void onResponse(Call<IndicadoresResponse> call, Response<IndicadoresResponse> response) {
                if (response.isSuccessful()){
                    IndicadoresResponse indicadoresResponse = response.body();
                    Indicador uf = indicadoresResponse.getUf();
                    Log.v(TAG, "uf "+uf.toString());
                    tvUf.setText(uf.getNombre()+ " vale: " + uf.getValor() + " pesos");
                    Indicador dolar = indicadoresResponse.getDolar();
                    tvDolar.setText(dolar.getNombre()+ " vale: " + dolar.getValor() + " pesos");
                    Indicador utm = indicadoresResponse.getUtm();
                    tvUtm.setText(utm.getNombre()+ " vale: " + utm.getValor() + " pesos");
                    Toast.makeText(getApplicationContext(), "API Rest: Consumo exitoso.", Toast.LENGTH_SHORT).show();
                }else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                    Toast.makeText(getApplicationContext(), "API Rest: Respuesta insatisfactoria.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IndicadoresResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "API Rest: Sin respuesta, revise su conexión a internet.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
