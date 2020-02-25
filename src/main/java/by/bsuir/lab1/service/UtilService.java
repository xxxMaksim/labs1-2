package by.bsuir.lab1.service;

import org.springframework.stereotype.Component;

@Component
public class UtilService {

    public String getReversedString(String string) {
        return new StringBuilder(string).reverse().toString();
    }

}
