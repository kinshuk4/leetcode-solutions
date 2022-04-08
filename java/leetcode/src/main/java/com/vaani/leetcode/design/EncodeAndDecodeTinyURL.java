package com.vaani.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 535. Encode and Decode TinyURL
 * Medium
 * <p>
 * Note: This is a companion problem to the System Design problem: [Design TinyURL](https://leetcode.com/discuss/interview-question/124658/Design-a-URL-Shortener-(-TinyURL-)-System/).
 * <p>
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 * <p>
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */
public class EncodeAndDecodeTinyURL {
    private final List<String> list = new ArrayList<>();
    private static final String URL = "http://tinyurl.com/";

    public static void main(String[] args) throws Exception {
        EncodeAndDecodeTinyURL encoder = new EncodeAndDecodeTinyURL();
        String shorterUrl = encoder.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(encoder.decode(shorterUrl));
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        list.add(longUrl);
        return URL.concat(String.valueOf(list.size()));
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String[] parts = shortUrl.split(URL);
        String code = parts[1];
        return list.get(Integer.parseInt(code) - 1);
    }
}
