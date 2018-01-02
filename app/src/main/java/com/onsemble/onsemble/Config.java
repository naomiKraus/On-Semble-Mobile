package com.onsemble.onsemble;
public class Config {


    //FB URLs for all environment
    private static String fb_dev = "https://attendancesuite-dev.firebaseio.com";
    private static String fb_test = "https://attendancesuite-test.firebaseio.com";
    private static String fb_prod = "https://attendancesuite-1-0-0.firebaseio.com";

    public static final String FB_URL = fb_dev;

    private static String server_dev = "http://devserver.on-semble.com:3002";
    private static String server_test = "http://devserver.on-semble.com:3000";
    private static String server_prod = "https://app.on-semble.com";
  /*  use local ip (ipconfig in cmd)*/
    private static String localhost = "http://50.50.50.26:3002";

    public static final String SERVER_URL = localhost;

}


