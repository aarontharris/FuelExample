package com.example.fuelexample.core.util;

import androidx.annotation.CheckResult;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * A collection of Documenting & Policy/Contract Enforcing Annotations.<br>
 * Ideally these have lint checks backing them, but... priorities.
 * <br>
 * <br>
 * Other great suggestions Documenting & Policing Annotations:
 * <pre>
 * {@link CheckResult}
 * </pre>
 */
public final class Doc {

    /**
     * Ensures NonNull during creation phase.<br>
     * <br>
     * Requires IDE support - IntelliJ Preferences -> Editor -> Intentions -> Nullable Problems
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({TYPE, METHOD, FIELD})
    public @interface LateInit {
    }


    @Retention(RetentionPolicy.SOURCE)
    @Target({TYPE, METHOD, FIELD})
    public @interface ThreadSafe {
    }

    @Retention(RetentionPolicy.SOURCE)
    @Target({TYPE})
    public @interface Immutable {
    }

    /**
     * Field:<br>
     * Indicates this field is managed by a method. This field should not be read or written directly.<br>
     * <br>
     * Commonly used for one-off cases where access is directed through a method due to more complex logic such as caching, concurrency, etc.
     * Touching this state directly would be dangerous.<br>
     * <br>
     * If this gets too complex, consider true encapsulation via it's own class.<br>
     * <br>
     *
     * Ex:
     * <pre>
     *     public class MyClass {
     *
     *         // describes this field is managed elsewhere, don't touch it directly except within that elsewhere.
     *         private @Managed ComplexObject data;
     *
     *         // describes this data is not computed on demand and it is safe to call repeatedly
     *         private @Cached ComplexObject getData() {
     *             if ( data = null ) {
     *                 data = new ComplexObject();
     *             }
     *             return data;
     *         }
     *
     *     }
     * </pre>
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({FIELD})
    public @interface Managed {
    }

    /**
     * Method:<br>
     * This result is not computed on demand. It is obtained intelligently and safe to call this method repeatedly.<br>
     * <br>
     * Field:<br>
     * See: {@link Managed}
     *
     * Best used as a reminder for implementation details of private methods.
     * However, when it is part of the external contract that a method must be cached this annotation
     * serves as nice documentation nod to future readers.<br>
     * <br>
     * Ex:
     * <pre>
     *     public class MyClass {
     *
     *         // describes this field is managed elsewhere, don't touch it directly except within that elsewhere.
     *         private @Managed ComplexObject data;
     *
     *         // describes this data is not computed on demand and it is safe to call repeatedly
     *         private @Cached ComplexObject getData() {
     *             if ( data = null ) {
     *                 data = new ComplexObject();
     *             }
     *             return data;
     *         }
     *
     *     }
     * </pre>
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({METHOD})
    public @interface Cached {
    }

    /**
     * #BecauseDagger<br>
     * <br>
     * What:<br>
     * Enforce contract of max scope for this object -- in cases where it must be treated as a singleton.
     *
     * <br>
     * <br>
     * Why:<br>
     * We want to enforce the contract stating that this object must be a Singleton.
     * One might say this is an implementation detail. It's best to design a class implementation understanding that
     * there will be one or many instances for things like caching, thread safety, etc.<br>
     * <br>
     * Ordinarily we'd just scope the object and let that be the contract. Unfortunately for Subcomponents that does not work.
     * Interfaces also cannot be scoped, but in some cases a Singleton pattern must be part of the contract.
     * <br>
     * Dagger uses @Singleton to describe a scope for both Components and POJOs.<br>
     * Not only does that make @Singleton confusing between scope or "there can be only one" --
     * We cannot define Subcomponents with @Singleton when a parent is @Singleton -- Dagger considers this redundant.<br>
     * That leaves it unclear about how a Subcomponent is scoped -- forcing you to find-usages, to see where it's provider
     * is defined in order to determine it's scope -- messy<br>
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface IsAppSingleton { // TODO: #lint
    }

    /**
     * #BecauseDagger<br>
     * <br>
     * What:<br>
     * Enforce scope contract for this object -- it must be treated as a singleton.
     *
     * <br>
     * <br>
     * Why:<br>
     * We want to enforce the contract stating that this object must be a Singleton.
     * One might say this is an implementation detail. It's best to design a class implementation understanding that
     * there will be one or many instances for things like caching, thread safety, etc.<br>
     * <br>
     * Ordinarily we'd just scope the object and let that be the contract. Unfortunately for Subcomponents that does not work.
     * Interfaces also cannot be scoped, but in some cases a Singleton pattern must be part of the contract.
     * <br>
     * Dagger uses @Singleton to describe a scope for both Components and POJOs.<br>
     * Not only does that make @Singleton confusing between scope or "there can be only one" --
     * We cannot define Subcomponents with @Singleton when a parent is @Singleton -- Dagger considers this redundant.<br>
     * That leaves it unclear about how a Subcomponent is scoped -- forcing you to find-usages, to see where it's provider
     * is defined in order to determine it's scope -- messy<br>
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface IsActivitySingleton { // TODO: #lint
    }

}
