package com.Invoicer.Biller.utils;

import com.Invoicer.Biller.BillerApplication;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static InputStream getResource(String file) {
        try {
            return BillerApplication.class.getResourceAsStream(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static String streamToString(String file) throws IOException {
        return IOUtils.toString(getResource(file),StandardCharsets.UTF_8);
    }
}
