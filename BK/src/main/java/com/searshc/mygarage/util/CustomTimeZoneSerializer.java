package com.searshc.mygarage.util;

import java.io.IOException;

import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomTimeZoneSerializer extends JsonSerializer<DateTimeZone> {

    @Override
    public void serialize(DateTimeZone value, JsonGenerator gen, SerializerProvider arg2) throws IOException,
            JsonProcessingException {

        gen.writeString(value.getID());

    }

}
