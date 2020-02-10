package com.example.fuelexample.core.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public class Views {

    /**
     * Based on the honor system. This blows away any null or type checking for the sake of brevity.<br>
     * Why?<br>
     * Because ultimately finding a view and assigning to a type is cast anyway -- so lets at least keep it tidy.
     *
     * @throws NullPointerException if {@code parent} is null or no view can be located by {@code id}
     */
    public static @NonNull <T extends View> T findView(View parent, @IdRes int id) {
        Pre.notNull(parent);
        //noinspection unchecked
        return (T) Pre.notNull(parent.findViewById(id));
    }

    public static void merge(ViewGroup parent, @LayoutRes int id) {
        LayoutInflater.from(parent.getContext()).inflate(id, parent, true);
    }

}
