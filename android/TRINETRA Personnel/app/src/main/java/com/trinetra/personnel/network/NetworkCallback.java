package com.trinetra.personnel.network;

public interface NetworkCallback<T> {

    void onSuccess(T result);

    void onError(String message);
}