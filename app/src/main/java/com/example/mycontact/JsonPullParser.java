package com.example.mycontact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonPullParser {

    public static List<Contact> parser(InputStream inputStream) throws IOException, JSONException {
        StringBuilder sb = new StringBuilder();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        while (bis.available() != 0){
            sb.append((char) bis.read());
        }
        bis.close();
        return parser(sb.toString());
    }


    public static List<Contact> parser(String jsonParser) throws JSONException {
        List<Contact> ctList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonParser);
        for (int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Contact ct = new Contact();
            ct.setName(jsonObject.getString(Contact.KEY_NAME));
            ct.setNumber(jsonObject.getString(Contact.KEY_NUMBER));
            ct.setPhoto(jsonObject.getString(Contact.KEY_PHOTO));
            ct.setId(jsonObject.getInt(Contact.KEY_ID));
            ctList.add(ct);
        }
        return ctList;
    }
}
