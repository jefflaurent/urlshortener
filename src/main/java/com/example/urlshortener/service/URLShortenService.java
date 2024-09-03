package com.example.urlshortener.service;

import java.util.Random;

interface URLShortenServiceInterface {
    String shortenURL(String longURL);
}

public class URLShortenService implements URLShortenServiceInterface {
    DigitService digitService = new DigitService();
    URLDAO urlDAO = new URLDAO();

    public String shortenURL(String longURL) {
        int length = digitService.queryStringLength();

        for (int i = 0; i < 10; i++) {
            String shortenedURL = generateRandomString(length);

            boolean exists = urlDAO.queryShortenedURL(shortenedURL);
            if (!exists) {
                return shortenedURL;
            } else if (i == 9) {
                length = digitService.increaseStringLength();
                return generateRandomString(length);
            }
        }
    }

    String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
