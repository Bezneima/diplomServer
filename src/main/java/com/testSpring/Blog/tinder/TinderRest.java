package com.testSpring.Blog.tinder;

import com.google.gson.Gson;
import com.testSpring.Blog.tinder.pojos.Photo;
import com.testSpring.Blog.tinder.pojos.TinderPeopleNearBy;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TinderRest {

    private String TinderURL = "https://api.gotinder.com";
    private String TinderXAuthToken = "7ba24daa-8dfc-4d39-af4b-51fa514fafca";
    private String URIListOfPeopleNearby = "/v2/recs/core";
    private String URITinderProfileInfo = "/v2/profile?include=account%2Cuser";
    private String URITinderMatches = "/v2/matches";
    private String URITinderLikePerson = "/like/";
    private String URITinderPassPerson = "/pass/";
    //Запихать в бин

    public String returnPeopleNearby() throws IOException {
        URL url = new URL(TinderURL + URIListOfPeopleNearby);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("x-auth-token", TinderXAuthToken);

        if (con.getResponseCode() == 200) {
            return getContentFromResponse(con);
        } else {
            System.err.print(con.getErrorStream());
        }
        con.disconnect();
        return ("");

        /*if (con.getResponseCode() == 200) {
            Gson gson = new Gson();
            return gson.fromJson(getContentFromResponse(con), TinderPeopleNearBy.class);
        } else {
            System.err.print(con.getErrorStream());
        }
        con.disconnect();
        return (new TinderPeopleNearBy());*/


    }

    private String getContentFromResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        System.out.print(content);
        in.close();
        return content.toString();
    }

    public List<String> getPhotoFromUser(List<Photo> inputList) {
        List<String> allPhoto = new ArrayList<>();
        inputList.forEach(photo ->
                allPhoto.add(photo.getUrl())

        );
        return allPhoto;
    }

    public String EstimatePerson(String personId, boolean estimateFlag) throws IOException {

        String makeUrl = (estimateFlag) ?
                TinderURL + URITinderLikePerson + personId :
                TinderURL + URITinderPassPerson + personId;

        URL url = new URL(makeUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("x-auth-token", TinderXAuthToken);
        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.err.print(con.getErrorStream());
        }
        con.disconnect();
        return Integer.toString(responseCode);
    }
}
