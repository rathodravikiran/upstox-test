package com.upstox.test;

public class TestConstants {

    public interface SUB_BODY{
        String post = "{\"event\": \"subscribe\", \"symbol\": \"XXBTZUSD\", \"interval\": 15}";
        String negativePost = "{\"event\": \"subscribe\", \"symbol\": \"\", \"interval\": 15}";

    }
}
