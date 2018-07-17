package com.example.amineelouattar.starredrepos.utils;

/**
 * Created by amineelouattar on 12/30/17.
 */

public class GlobalVars {

    public final static String API_URL = "https://api.github.com/search/repositories?";
    public final static String API_QUERY = "q=created:>";
    public final static String API_PARAMS = "&sort=stars&order=desc&page=";
    //34 page because the default item count in every page is 30 e.g. 1000/30 ~ 34
    public final static int PAGE_LIMIT = 34;

}
