package com.example.unbosque.Model;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Path;

public interface UserApiService {
    @Headers("Content-Type: application/json")
    @POST("api/users/login")
    Call<String> loginUser(@Body LoginRequest loginRequest);

    @POST("api/users/register")
    Call<String> registerUser(@Body UserRegistrationRequest userRegistrationRequest);

    @POST("api/reminders/register")
    Call<String> registerReminder(@Body ReminderRegistration reminderRegistration);

    // Define a GET method for fetching reminders using a path parameter
    @GET("api/reminders/{correo}")
    Call<List<Reminder>> getReminders(@Path("correo") String correo);
}