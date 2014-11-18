package com.searshc.mygarage.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 *
 * The {@link VGUtils} is a general helper class of the Virtual Garage project.
 *
 * @author Jero
 *
 */
public class VGUtils {

    /**
     *
     * Return the new string changed that are the cut text that have exactly the
     * same count of delimiter that was defined by parameter.
     *
     * NOTE:<br>
     * Create this simple method because stringTokenizer not work just expected
     * for this case.
     *
     * @param textLine
     * @param delimiter
     * @param maxDelimiterCount
     * @return
     */
    public static String substringByRepeatDelimiter(final String textLine, final char delimiter,
            final int maxDelimiterCount) {

        Assert.notNull(delimiter, "The delimiter can't be null");
        Assert.notNull(textLine, "The textLine can't be null");
        Assert.hasLength(textLine, "The The textLine can't be empty");

        char[] digit = textLine.toCharArray();
        StringBuilder sb = new StringBuilder();
        int cntDelimiter = 0;
        for (int i = 0; i < digit.length; i++) {
            char word = digit[i];
            if (delimiter == word) {
                cntDelimiter++;
                if (cntDelimiter > maxDelimiterCount) {
                    break;
                }
            }
            sb.append(word);
        }
        Assert.state((cntDelimiter >= maxDelimiterCount), "The line [" + textLine + "] must have " + maxDelimiterCount + ", but instead have " + cntDelimiter + ", check & fix this line and try again.");
        return sb.toString();
    }

    public static float parseSafeFloat(final String stringNumber) {
        Assert.notNull(stringNumber, "The stringNumber can't be null");
        Assert.hasLength(stringNumber, "The The stringNumber can't be empty");

        String safeParseFloat = StringUtils.replace(stringNumber, ",", ".");

        return Float.parseFloat(safeParseFloat);
    }

    public static <E> List<E> toList(final Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

}
