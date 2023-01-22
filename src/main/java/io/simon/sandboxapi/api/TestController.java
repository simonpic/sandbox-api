package io.simon.sandboxapi.api;

import io.simon.sandboxapi.api.dto.TestDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.Instant;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
//    @PreAuthorize("hasAuthority('USER')")
    public TestDto test(Principal principal) {
        System.out.println(principal.getName());
        return new TestDto(Instant.now().toEpochMilli() + " - API says ¡Hola!");
    }

}
