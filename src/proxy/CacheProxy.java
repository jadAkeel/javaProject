package src.proxy;

import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements Service {
    private Service realService;
    private Map<String, String> cache = new HashMap<>();

    public CacheProxy(Service service) {
        this.realService = service;
    }

    public String fetchData(String request) {
        if (cache.containsKey(request)) {
            System.out.println("Returning cached data for: " + request);
            return cache.get(request);
        }
        String result = realService.fetchData(request);
        cache.put(request, result);
        return result;
    }
}