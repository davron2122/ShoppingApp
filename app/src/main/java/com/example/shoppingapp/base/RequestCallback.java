package com.example.shoppingapp.base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RequestCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponseSuccess(call, response);
        } else {
            onResponseFailed(call, new Throwable("Failed"));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onResponseFailed(call, t);
    }

    protected abstract void onResponseSuccess(Call<T> call, Response<T> response);

    protected abstract void onResponseFailed(Call<T> call, Throwable t);
}
