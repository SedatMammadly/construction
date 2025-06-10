package org.example.construction.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class SlugUtil {

    public static String toSlug(String input) {
        if (input == null || input.isEmpty()) return "";
        input = input.replace("ə", "e")
                .replace("ç", "c")
                .replace("ğ", "g")
                .replace("ı", "i")
                .replace("ö", "o")
                .replace("ş", "s")
                .replace("ü", "u")
                .replace("Ə", "e")
                .replace("Ç", "c")
                .replace("Ğ", "g")
                .replace("I", "i")
                .replace("Ö", "o")
                .replace("Ş", "s")
                .replace("Ü", "u");
        input = input.toLowerCase();
        input = input.replaceAll("[^a-z0-9]+", "-");
        input = input.replaceAll("^-+|-+$", "");
        return input;
    }
}