package guru.sfg.brewery.security.perms;

// created by kp on 2/18/2023

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('beer.create')")
public @interface BeerCreatePermission {
}
