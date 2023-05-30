package com.recruitercorp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.adapters.authorization.PolicyEnforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files/v1")
public class FileController {

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/test")
    @PreAuthorize("principal?.attributes['sub'] == 'foo'")
    public String test(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) throws JsonProcessingException {
        System.out.println(principal);
        return "OK";
    }
}
