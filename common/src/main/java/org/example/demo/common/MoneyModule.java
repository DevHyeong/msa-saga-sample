package org.example.demo.common;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;

public class MoneyModule extends SimpleModule {
    public MoneyModule(){
        addDeserializer(Money.class, new MoneyDeserializer());
        addSerializer(Money.class, new MoneySerializer());
    }
    class MoneyDeserializer extends StdScalarDeserializer<Money> {
        protected MoneyDeserializer(){
            super(Money.class);
        }

        @Override
        public Money deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
            JsonToken token = jp.getCurrentToken();
            if(token == JsonToken.VALUE_STRING) {
                String str = jp.getText().trim();
                if (str.isEmpty())
                    return null;
                else
                    return new Money(str);
            }else
                throw ctxt.mappingException(getValueClass());
        }
    }

    class MoneySerializer extends StdScalarSerializer<Money> {
        public MoneySerializer(){
            super(Money.class);
        }

        @Override
        public void serialize(Money value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(value.asString());
        }
    }
}
