package com.remindpay.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remindpay.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private JsonUtil() {
        // Private constructor to prevent instantiation
    }

    public static String toJson(Object obj, String logPrefix) {
        try {
            String json = mapper.writeValueAsString(obj);
            if (logPrefix != null && !logPrefix.isBlank()) {
                log.info(logPrefix + ": " + json);
            }
            return json;
        } catch (Exception e) {
            log.error("Erro ao converter objeto para JSON: " + e.getMessage());
            throw new RuntimeException("Falha na serialização JSON", e);
        }
    }
}
