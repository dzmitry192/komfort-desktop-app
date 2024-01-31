package com.example.project_for_university.http;

import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class JsonToClass {
    public static <T> T parseToObject(Class<T> tClass, CloseableHttpResponse response) throws IOException {
        Gson gson = new Gson();
        JSON json = JSONSerializer.toJSON(EntityUtils.toString(response.getEntity()));
        return gson.fromJson(json.toString(), tClass);
    }

    public static <T> ArrayList<T> parseToListObject(Class<T> tClass, CloseableHttpResponse response) throws IOException {
        Gson gson = new Gson();
        JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(EntityUtils.toString(response.getEntity()));
        ArrayList<T> list = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i++) {
            list.add(gson.fromJson(jsonArray.get(i).toString(), tClass));
        }
        return list;
    }
}