package com.example.unbosque.Model;


import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface UserApiService {
    @Headers("Content-Type: application/json")
    @POST("api/users/login")
    Call<String> loginUser(@Body LoginRequest loginRequest);

    @POST("api/users/register")
    Call<String> registerUser(@Body UserRegistrationRequest userRegistrationRequest);
}