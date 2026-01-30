package com.paypal.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonGenerator {

    public static String generateJson(String intent) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("intent", intent);

        JsonObject paymentSource = new JsonObject();
        JsonObject experienceContext = new JsonObject();

        experienceContext.addProperty("return_url", "https://developer.paypal.com");
        experienceContext.addProperty("cancel_url", "https://www.bing.com");
        experienceContext.addProperty("user_action", "PAY_NOW");
        paymentSource.add("paypal", experienceContext);
        jsonObject.add("payment_source", paymentSource);

        JsonArray purchaseUnits = new JsonArray();
        JsonObject purchaseUnit = new JsonObject();
        JsonObject amount = new JsonObject();
        purchaseUnit.addProperty("currency_code", "USD");
        purchaseUnit.addProperty("value", "1");
        amount.add("amount", purchaseUnit);

        purchaseUnits.add(amount);
        jsonObject.add("purchase_units", purchaseUnits);
        System.out.println(jsonObject);
        Gson gson = new Gson();
        return gson.toJson(jsonObject);
    }

}
