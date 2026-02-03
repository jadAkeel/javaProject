package src.proxy;

public class Main {
    public static void main(String[] args) {
        Service service = new RealService();

        System.out.println("\n--- Security Proxy ---");
        Service secProxy = new SecurityProxy(service, "1234");
        System.out.println(secProxy.fetchData("user1"));
        Service secProxyWrong = new SecurityProxy(service, "0000");
        System.out.println(secProxyWrong.fetchData("user2"));

        System.out.println("\n--- Virtual Proxy ---");
        Service virtProxy = new VirtualProxy();
        System.out.println(virtProxy.fetchData("request1"));

        System.out.println("\n--- Logging Proxy ---");
        Service logProxy = new LoggingProxy(service);
        System.out.println(logProxy.fetchData("request2"));

        System.out.println("\n--- Cache Proxy ---");
        Service cacheProxy = new CacheProxy(service);
        System.out.println(cacheProxy.fetchData("request3")); // fetch
        System.out.println(cacheProxy.fetchData("request3")); // cached
    }
}