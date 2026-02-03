package src.proxy;

public class SecurityProxy implements Service {
    private Service realService;
    private String password;

    public SecurityProxy(Service service, String password) {
        this.realService = service;
        this.password = password;
    }

    public String fetchData(String request) {
        if ("1234".equals(password)) {
            return realService.fetchData(request);
        } else {
            return "Access denied: wrong password";
        }
    }
}