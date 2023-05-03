/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.stripe.Stripe;

/**
 *
 * @author ALPHA
 */
public class StripeConfig {

    public static final String STRIPE_SECRET_KEY = "sk_test_51MhGaAGeGEgrQ6hOFaUPvKPr8iOv7UjDwPJ22UAHMhCVD0VCQw3CmEGh0mQoVN7b635WeO2rilB94j2hSWMNDxhu00UQXAHDAc";
    public static final String STRIPE_PUBLIC_KEY = "pk_test_51MhGaAGeGEgrQ6hOEnXatmbTvZcPd3ll7Z5G4IsG18jdvInJbqQPR24LgWUZcnKp7NLZZh2K8LX0RYnRIGwi33IS00NUy6pYHg";

    static {
        Stripe.apiKey = STRIPE_SECRET_KEY;
    }
}
