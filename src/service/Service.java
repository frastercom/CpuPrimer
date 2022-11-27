package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import json.object.CPU;
import json.object.Status;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

/**
 *
 * Отправка/получение данных
 */
public class Service {

    private final static String URL = "http://gitmyserver.ddns.net:8080/api/cpu/";
    private final static Gson gson = new Gson();

    public static void sendAdd(CPU cpu) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().timeout(Duration.ofMillis(10000))
                .header("content-type", "application/json; charset utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(cpu)))
                .uri(URI.create(URL.concat("create")))
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CPU> getCpu() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().timeout(Duration.ofMillis(10000))
                .uri(URI.create(URL))
                .build();
        try {
            return gson.fromJson(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), new TypeToken<List<CPU>>() {}.getType());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteCpu(String id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().timeout(Duration.ofMillis(10000))
                .uri(URI.create(URL.concat("delete?id={0}".replace("{0}", id))))
                .build();
        try {
            if ("ok".equals(gson.fromJson(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), Status.class).getStatus())) {
                return;
            }
            throw new RuntimeException();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
