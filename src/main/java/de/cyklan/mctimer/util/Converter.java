package de.cyklan.mctimer.util;


import com.google.gson.Gson;
import com.mojang.authlib.minecraft.client.ObjectMapper;
import de.cyklan.mctimer.MCTimer;

import java.io.*;

public class Converter {
    private final ObjectMapper mapper = new ObjectMapper(new Gson());

    public String objectToJSON(Object obj) {
        return mapper.writeValueAsString(obj);
    }

    public <T> T JSONToObject(String json, Class<T> typeParameterClass) {
        return mapper.readValue(json, typeParameterClass);
    }

    public byte[] objectToBytes(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        byte[] bytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            MCTimer.LOGGER.error("Could not serialize times");
        } finally {
            try {
                bos.close();
            } catch (IOException e) {}
        }

        return bytes;
    }

    public Object bytesToObject(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in;

        Object o = null;
        try {
            in = new ObjectInputStream(bis);
            o = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            MCTimer.LOGGER.error("Could not deserialize times");
        }

        return o;
    }
}
