package com.thejackfolio.microservices.instagramoauthclient.controllers;

import com.thejackfolio.microservices.instagramoauthclient.models.InstagramPostsResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Access Denied", description = "Access Denied by Instagram User")
@RestController
public class AccessDeniedController {

    @Operation(
            summary = "Access Denied",
            description = "Access Denied"
    )
    @GetMapping("/access-denied")
    public ResponseEntity<InstagramPostsResponseWrapper> accessDenied(){
        InstagramPostsResponseWrapper wrapper = new InstagramPostsResponseWrapper();
        wrapper.setMessage("Access Denied by the instagram user");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(wrapper);
    }
}
