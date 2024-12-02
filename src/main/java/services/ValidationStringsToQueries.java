package services;


import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


@Getter
@Setter
public class ValidationStringsToQueries {

    StringBuilder valueToValidation;

    public String validationColumn(String[] value){

        StringBuilder validationString = new StringBuilder();

        Arrays.stream(value).toList().forEach(p -> {
            String updateString = p.replace("'", " ");

            validationString.append(updateString);
        });

        return new String(validationString);
    }
}
