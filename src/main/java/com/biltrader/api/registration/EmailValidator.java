package com.biltrader.api.registration;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // Any email containing "@" (but not starting with it) and ending with "bilkent.edu.tr"
        return Pattern.matches("^.+@.*bilkent\\.edu\\.tr$", s);
    }

}
