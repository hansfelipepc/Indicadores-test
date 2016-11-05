# Consumir servicios REST con Retrofit

## Dependencias 
 ```
    compile 'com.android.support:appcompat-v7:23.4.0'
    compile 'com.android.support:support-v4:23.4.0'
    compile 'com.android.support:design:23.4.0'
    compile 'com.android.support:cardview-v7:23.4.0'
    compile 'com.android.support:recyclerview-v7:23.4.0'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
 ```

## Servicio REST - JSON
 - [http://mindicador.cl/api](http://mindicador.cl/api)
 - Trama Json
 
  ```
     {"version":"1.4.0","autor":"mindicador.cl","fecha":"2016-11-05T11:00:00.000Z","uf":{"codigo":"uf","nombre":"Unidad de fomento (UF)","unidad_medida":"Pesos","fecha":"2016-11-05T04:00:00.000Z","valor":26269.98},"ivp":{"codigo":"ivp","nombre":"Indice de valor promedio (IVP)","unidad_medida":"Pesos","fecha":"2016-11-05T04:00:00.000Z","valor":27319.1},"dolar":{"codigo":"dolar","nombre":"Dólar observado","unidad_medida":"Pesos","fecha":"2016-11-04T04:00:00.000Z","valor":650.72},"dolar_intercambio":{"codigo":"dolar_intercambio","nombre":"Dólar acuerdo","unidad_medida":"Pesos","fecha":"2014-11-13T05:00:00.000Z","valor":758.87},"euro":{"codigo":"euro","nombre":"Euro","unidad_medida":"Pesos","fecha":"2016-11-04T04:00:00.000Z","valor":722.62},"ipc":{"codigo":"ipc","nombre":"Indice de Precios al Consumidor (IPC)","unidad_medida":"Porcentaje","fecha":"2016-09-01T04:00:00.000Z","valor":0.2},"utm":{"codigo":"utm","nombre":"Unidad Tributaria Mensual (UTM)","unidad_medida":"Pesos","fecha":"2016-11-01T04:00:00.000Z","valor":46091},"imacec":{"codigo":"imacec","nombre":"Imacec","unidad_medida":"Porcentaje","fecha":"2016-08-01T04:00:00.000Z","valor":2.5},"tpm":{"codigo":"tpm","nombre":"Tasa Política Monetaria (TPM)","unidad_medida":"Porcentaje","fecha":"2016-11-04T04:00:00.000Z","valor":3.5},"libra_cobre":{"codigo":"libra_cobre","nombre":"Libra de Cobre","unidad_medida":"Dólar","fecha":"2016-11-04T04:00:00.000Z","valor":2.23},"tasa_desempleo":{"codigo":"tasa_desempleo","nombre":"Tasa de desempleo","unidad_medida":"Porcentaje","fecha":"2016-09-01T04:00:00.000Z","valor":6.8}}
  ```
  
## Modelos (Entidades)

- Entidad de Respuesta

```
    public class IndicadoresResponse {

       private String version;
       private String autor;
       private String fecha;
       private Indicador uf;

       public String getVersion() {
           return version;
       }

       public void setVersion(String version) {
           this.version = version;
       }

       public String getAutor() {
           return autor;
       }

       public void setAutor(String autor) {
           this.autor = autor;
       }

       public String getFecha() {
           return fecha;
       }

       public void setFecha(String fecha) {
           this.fecha = fecha;
       }

       public Indicador getUf() {
           return uf;
       }

       public void setUf(Indicador uf) {
           this.uf = uf;
       }
   }

```
  - Entidad Indicador
  ```
     public class Indicador {

       private String codigo;
       private String nombre;
       private String unidad_medida;
       private String fecha;
       private double valor;

       public double getValor() {
           return valor;
       }

       public void setValor(double valor) {
           this.valor = valor;
       }

       public String getNombre() {
           return nombre;
       }

       public void setNombre(String nombre) {
           this.nombre = nombre;
       }

       public String getCodigo() {
           return codigo;
       }

       public void setCodigo(String codigo) {
           this.codigo = codigo;
       }

       public String getUnidad_medida() {
           return unidad_medida;
       }

       public void setUnidad_medida(String unidad_medida) {
           this.unidad_medida = unidad_medida;
       }

       public String getFecha() {
           return fecha;
       }

       public void setFecha(String fecha) {
           this.fecha = fecha;
       }

       @Override
       public String toString() {
           return "Indicador{" +
                   "codigo='" + codigo + '\'' +
                   ", nombre='" + nombre + '\'' +
                   ", unidad_medida='" + unidad_medida + '\'' +
                   ", fecha='" + fecha + '\'' +
                   ", valor=" + valor +
                   '}';
       }
   }
  ```
  
## Consumir el servicio REST con Retrofit

```
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

                      Indicador uf= indicadoresResponse.getUf();
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

```

## Output

```
 V/INDICADORES: uf Indicador{codigo='uf', nombre='Unidad de fomento (UF)', unidad_medida='Pesos', fecha='2016-11-05T04:00:00.000Z', valor=26269.98}
```
