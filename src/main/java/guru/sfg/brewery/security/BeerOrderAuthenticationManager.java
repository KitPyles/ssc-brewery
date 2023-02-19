package guru.sfg.brewery.security;

// created by kp on 2/19/2023

import guru.sfg.brewery.domain.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BeerOrderAuthenticationManager {

    public boolean CustomerIdMatches(Authentication authentication, UUID customerId){
        User authenticatedUser = (User) authentication.getPrincipal();

        log.debug("Auth User Customer ID: " + authenticatedUser.getCustomer().getId() + "Customer ID: " + customerId);

        return authenticatedUser.getCustomer().getId().equals(customerId);
    }
}
