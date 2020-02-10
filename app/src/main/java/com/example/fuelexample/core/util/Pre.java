package com.example.fuelexample.core.util;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

public final class Pre {

    /**
     * Checks that the primitive value is not the default. If it is default, throws {@link
     * IllegalArgumentException}.
     *
     * @param v            argument to be checked
     * @param defaultValue comparison
     * @param message      the {@code detailMessage} of the {@link IllegalArgumentException} if thrown
     * @return {@code v} if not null
     */
    public static boolean checkPrimitive(boolean v, boolean defaultValue, @NonNull String message) {
        checkArgument(v != defaultValue, message);
        return v;
    }

    /**
     * Checks that the primitive value is not the default. If it is default, throws {@link
     * IllegalArgumentException}.
     *
     * @param v            argument to be checked
     * @param defaultValue comparison
     * @param message      the {@code detailMessage} of the {@link IllegalArgumentException} if thrown
     * @return {@code v} if not null
     */
    public static byte checkPrimitive(byte v, byte defaultValue, @NonNull String message) {
        checkArgument(v != defaultValue, message);
        return v;
    }

    /**
     * Checks that the primitive value is not the default. If it is default, throws {@link
     * IllegalArgumentException}.
     *
     * @param v            argument to be checked
     * @param defaultValue comparison
     * @param message      the {@code detailMessage} of the {@link IllegalArgumentException} if thrown
     * @return {@code v} if not null
     */
    public static short checkPrimitive(short v, short defaultValue, @NonNull String message) {
        checkArgument(v != defaultValue, message);
        return v;
    }

    /**
     * Checks that the primitive value is not the default. If it is default, throws {@link
     * IllegalArgumentException}.
     *
     * @param v            argument to be checked
     * @param defaultValue comparison
     * @param message      the {@code detailMessage} of the {@link IllegalArgumentException} if thrown
     * @return {@code v} if not null
     */
    public static char checkPrimitive(char v, char defaultValue, @NonNull String message) {
        checkArgument(v != defaultValue, message);
        return v;
    }

    /**
     * Checks that the primitive value is not the default. If it is default, throws {@link
     * IllegalArgumentException}.
     *
     * @param v            argument to be checked
     * @param defaultValue comparison
     * @param message      the {@code detailMessage} of the {@link IllegalArgumentException} if thrown
     * @return {@code v} if not null
     */
    public static int checkPrimitive(int v, int defaultValue, @NonNull String message) {
        checkArgument(v != defaultValue, message);
        return v;
    }

    /**
     * Checks that the primitive value is not the default. If it is default, throws {@link
     * IllegalArgumentException}.
     *
     * @param v            argument to be checked
     * @param defaultValue comparison
     * @param message      the {@code detailMessage} of the {@link IllegalArgumentException} if thrown
     * @return {@code v} if not null
     */
    public static long checkPrimitive(long v, long defaultValue, @NonNull String message) {
        checkArgument(v != defaultValue, message);
        return v;
    }

    /**
     * Checks that the primitive value is not the default. If it is default, throws {@link
     * IllegalArgumentException}.
     *
     * @param v            argument to be checked
     * @param defaultValue comparison
     * @param message      the {@code detailMessage} of the {@link IllegalArgumentException} if thrown
     * @return {@code v} if not null
     */
    public static float checkPrimitive(float v, float defaultValue, @NonNull String message) {
        checkArgument(v != defaultValue, message);
        return v;
    }

    /**
     * Checks that the primitive value is not the default. If it is default, throws {@link
     * IllegalArgumentException}.
     *
     * @param v            argument to be checked
     * @param defaultValue comparison
     * @param message      the {@code detailMessage} of the {@link IllegalArgumentException} if thrown
     * @return {@code v} if not null
     */
    public static double checkPrimitive(double v, double defaultValue, @NonNull String message) {
        checkArgument(v != defaultValue, message);
        return v;
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ensures that an expression checking an argument is true.
     *
     * @param expression   the expression to check
     * @param errorMessage the exception message to use if the check fails; will
     *                     be converted to a string using {@link String#valueOf(Object)}
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, @NonNull Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling
     * method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static @NonNull <T> T checkNotNull(@Nullable T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling
     * method is not null.
     *
     * @param reference    an object reference
     * @param errorMessage the exception message to use if the check fails; will
     *                     be converted to a string using {@link String#valueOf(Object)}
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static @NonNull <T> T checkNotNull(@Nullable T reference, @NonNull Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    /**
     * alias ~ {@link #checkNotNull(Object)}
     *
     * @throws NullPointerException if {@code reference} is null
     */
    public static @NonNull <T> T notNull(@Nullable T reference) {
        return checkNotNull(reference);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling
     * method is not null offering an alternative defaultValue.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated or defaultValue
     */
    public static @NonNull <T> T ensureNotNull(@Nullable T reference, @NonNull T defaultValue) {
        return reference == null ? defaultValue : reference;
    }

    /**
     * Ensures the truth of an expression involving the state of the calling
     * instance, but not involving any parameters to the calling method.
     *
     * @param expression a boolean expression
     * @param message    exception message
     * @throws IllegalStateException if {@code expression} is false
     */
    public static void checkState(boolean expression, @Nullable String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Ensures the truth of an expression involving the state of the calling
     * instance, but not involving any parameters to the calling method.
     *
     * @param expression a boolean expression
     * @throws IllegalStateException if {@code expression} is false
     */
    public static void checkState(final boolean expression) {
        checkState(expression, null);
    }

    /**
     * Ensures that that the argument numeric value is non-negative.
     *
     * @param value        a numeric int value
     * @param errorMessage the exception message to use if the check fails
     * @return the validated numeric value
     * @throws IllegalArgumentException if {@code value} was negative
     */
    public static @IntRange(from = 0) int checkArgumentNonnegative(final int value,
                                                                   @Nullable String errorMessage) {
        if (value < 0) {
            throw new IllegalArgumentException(errorMessage);
        }

        return value;
    }

    /**
     * Ensures that that the argument numeric value is non-negative.
     *
     * @param value a numeric int value
     * @return the validated numeric value
     * @throws IllegalArgumentException if {@code value} was negative
     */
    public static @IntRange(from = 0) int checkArgumentNonnegative(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }

        return value;
    }

    /**
     * Ensures that the argument int value is within the inclusive range.
     *
     * @param value     a int value
     * @param lower     the lower endpoint of the inclusive range
     * @param upper     the upper endpoint of the inclusive range
     * @param valueName the name of the argument to use if the check fails
     * @return the validated int value
     * @throws IllegalArgumentException if {@code value} was not within the range
     */
    public static int checkArgumentInRange(int value, int lower, int upper,
                                           @NonNull String valueName) {
        if (value < lower) {
            throw new IllegalArgumentException(
                    String.format(Locale.US,
                            "%s is out of range of [%d, %d] (too low)", valueName, lower, upper));
        } else if (value > upper) {
            throw new IllegalArgumentException(
                    String.format(Locale.US,
                            "%s is out of range of [%d, %d] (too high)", valueName, lower, upper));
        }

        return value;
    }

    private Pre() {
    }
}
