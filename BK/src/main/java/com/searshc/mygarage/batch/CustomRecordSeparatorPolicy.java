package com.searshc.mygarage.batch;

import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;

import com.searshc.mygarage.util.VGUtils;

/**
 *
 * The {@link CustomRecordSeparatorPolicy} is just a separator policy that help
 * to cut the text line in one defined ({@code maxLineToken}) length.
 *
 * @author Jero
 *
 */
public class CustomRecordSeparatorPolicy extends SimpleRecordSeparatorPolicy {

    /**
     * Default delimiter line token.
     */
    private char delimiterLineToken = ';';

    /**
     * Default max line token
     */
    private int maxLineToken = 31;

    public CustomRecordSeparatorPolicy(final int maxLineToken, final char delimiterLineToken) {
        this.maxLineToken = maxLineToken;
        this.delimiterLineToken = delimiterLineToken;
    }

    /**
     * Default constructor
     */
    public CustomRecordSeparatorPolicy() {
    }

    @Override
    public String postProcess(final String record) {
        return VGUtils.substringByRepeatDelimiter(record, delimiterLineToken, maxLineToken);
    }
}
