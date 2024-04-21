package com.example.unbosque.Model;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceBuilder {

    private static final String BASE_URL = "http://10.0.2.2:3000/"; // Asegúrate de que esta es la URL base correcta

    public static UserApiService createService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // Configura el nivel de log a BODY para ver todo el cuerpo de las respuestas.

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor) // Añade el interceptor de logging
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) // Establece la URL base para las llamadas de Retrofit
                .addConverterFactory(GsonConverterFactory.create()) // Añade el convertidor de Gson para la deserialización automática
                .client(okHttpClient) // Utiliza el cliente HTTP personalizado
                .build();

        return retrofit.create(UserApiService.class); // Crea la implementación de la interfaz API
    }
}
