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
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<URLShortenResponse> shortenUrl(URLShortenRequest request) {
        // do param checking
        if (request == null || request.getLongUrl() == null || request.getLongUrl().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // call the service
        URLShortenResponse response = urlShortenerService.shortenUrl(request);

        // respond with created short URL
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String shortUrl) {
        // Call the service to get the long URL
        String longUrl = urlShortenerService.getLongUrl(shortUrl);

        if (longUrl == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Redirect to the long URL
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                             .header("Location", longUrl)
                             .build();
    }
}
