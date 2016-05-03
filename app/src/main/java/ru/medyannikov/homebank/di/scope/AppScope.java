package ru.medyannikov.homebank.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by vladimir on 30.04.16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
