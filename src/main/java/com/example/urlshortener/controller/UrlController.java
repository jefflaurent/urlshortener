package com.example.urlshortener.controller;

import com.sun.net.httpserver.HttpsServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    // call a service

    public ResponseEntity<URLShortenResponse> shortenUrl(URLShortenRequest request) {
        // do param checking

        // call the service

        // do return response
        return new ResponseEntity<>(null, HttpStatus.MOVED_PERMANENTLY);
    }

}
