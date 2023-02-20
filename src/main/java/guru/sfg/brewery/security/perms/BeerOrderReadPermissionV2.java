package guru.sfg.brewery.security.perms;

// created by kp on 2/19/2023

import org.springframework.security.access.prepost.PreAuthorize;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('order.read', 'customer.order.read')")
public @interface BeerOrderReadPermissionV2 {
}
