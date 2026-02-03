package src.proxy;

public class VirtualProxy implements Service {
    private RealService realService;

    public String fetchData(String request) {
        if (realService == null) {
            realService = new RealService();
        }
        return realService.fetchData(request);
    }
}