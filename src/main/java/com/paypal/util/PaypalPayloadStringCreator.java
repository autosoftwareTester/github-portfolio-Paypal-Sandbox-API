package com.paypal.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class PaypalPayloadStringCreator {
    /**
     * Creates a PayPal order payload JSON string.
     *
     * @param orderIntent The intent for the order (e.g., "CAPTURE", "AUTHORIZE").
     * @return A JSON string representing the payload.
     */
    public static String createPaypalOrderPayload(String orderIntent) {
        // Root object
        JSONObject payload = new JSONObject();
        payload.put("intent", orderIntent);

        // payment_source object
        JSONObject paymentSource = new JSONObject();
        JSONObject paypal = new JSONObject();
        JSONObject experienceContext = new JSONObject();

        experienceContext.put("return_url", "https://developer.paypal.com");
        experienceContext.put("cancel_url", "https://www.bing.com");
        experienceContext.put("user_action", "PAY_NOW");

        paypal.put("experience_context", experienceContext);
        paymentSource.put("paypal", paypal);
        payload.put("payment_source", paymentSource);

        // purchase_units array
        JSONArray purchaseUnits = new JSONArray();
        JSONObject purchaseUnit = new JSONObject();
        JSONObject amount = new JSONObject();

        amount.put("currency_code", "USD");
        amount.put("value", "1"); // Note: value is a string "1" as per your example

        purchaseUnit.put("amount", amount);
        purchaseUnits.put(purchaseUnit); // Add the purchaseUnit object to the array
        payload.put("purchase_units", purchaseUnits);

        // Convert the JSONObject to a JSON string with pretty printing (indentation)
        return payload.toString(4); // Indent by 4 spaces for readability
    }
}
