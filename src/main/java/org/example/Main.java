package org.example;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static java.net.HttpURLConnection.HTTP_OK;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .header("accept","application/json")
                .uri(URI.create(URL))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HTTP_OK) {
                Gson gson = new Gson();
                List<Posts> posts = gson.fromJson(response.body(), new TypeToken<List<Posts>>() {}.getType());

                Map<Integer, List<Posts>> groupedPosts = (Map<Integer, List<Posts>>) posts.stream()
                        .collect(Collectors.groupingBy(Posts::getUserId));

                Iterator<Integer> iterator = groupedPosts.keySet().iterator();
                while ((iterator.hasNext())){
                    Integer userId = iterator.next();
                    System.out.println("Индификатор пользователя: " + userId);
                    List<Posts> list = groupedPosts.get(userId);
                    for (Posts post: list){
                        System.out.println(post.getTitle());
                    }
                    
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка подключения к интернету: " + e.toString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}