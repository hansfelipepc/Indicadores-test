package com.hans.indicadores.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hans.indicadores.R;
import com.hans.indicadores.io.IndicadorService;
import com.hans.indicadores.model.Indicador;
import com.hans.indicadores.model.IndicadoresResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "INDICADORES";
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://mindicador.cl/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getData();
    }
    private void getData(){
        IndicadorService service = retrofit.create(IndicadorService.class);
        Call<IndicadoresResponse> indicadoresResponseCall = service.getIndicadorList();

        indicadoresResponseCall.enqueue(new Callback<IndicadoresResponse>() {
            // new, + ctrl+ spacebar
            @Override
            public void onResponse(Call<IndicadoresResponse> call, Response<IndicadoresResponse> response) {
                if (response.isSuccessful()){
                    IndicadoresResponse indicadoresResponse = response.body();
                    Indicador uf = indicadoresResponse.getUf();
                    Log.v(TAG, "uf "+uf.toString());
                }else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<IndicadoresResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
