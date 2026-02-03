package src.proxy;

public class LoggingProxy implements Service {
    private Service realService;

    public LoggingProxy(Service service) {
        this.realService = service;
    }

    public String fetchData(String request) {
        System.out.println("Log: Start fetching");
        String result = realService.fetchData(request);
        System.out.println("Log: End fetching");
        return result;
    }
}