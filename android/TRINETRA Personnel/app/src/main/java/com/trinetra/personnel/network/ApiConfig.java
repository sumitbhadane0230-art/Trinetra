package com.trinetra.personnel.network;

public final class ApiConfig {

    private ApiConfig() {
        // Utility class
    }

    /*
     * Android Emulator -> PC localhost
     *
     * 10.0.2.2 points to the host machine.
     *
     * FastAPI is running on:
     * http://127.0.0.1:8000
     */
    public static final String BASE_URL =
            "http://10.0.2.2:8000/";
}