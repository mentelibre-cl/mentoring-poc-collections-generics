package com.globant.mentoring.utils;

import com.globant.mentoring.exceptions.MentoringException;

public class MentoringUtils {

    public static Long stringToLong(final String number) throws MentoringException {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new MentoringException(e.getMessage());
        }
    }
}
