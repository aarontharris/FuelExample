package com.example.fuelexample.core.util;

import androidx.annotation.Nullable;

public class Log {
    public static final String TAG = "MYAPP";

    public static void d(@Nullable String message) {
        if (message != null) {
            android.util.Log.d(TAG, message);
        }
    }

    public static void d(@Nullable String format, Object... args) {
        if (format != null) {
            android.util.Log.d(TAG, String.format(format, args));
        }
    }

    public static void e(@Nullable Throwable throwable, @Nullable String message) {
        android.util.Log.e(TAG, message, throwable);
    }

    public static void e(@Nullable String format, Object... args) {
        if (format != null) {
            android.util.Log.e(TAG, String.format(format, args));
        }
    }

}
