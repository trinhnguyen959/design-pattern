package proxy.odev;

public class ProxyMain {
    public static void main(String[] args) {
        Remote remoteProxy = new LazyRemoteProxy();
        remoteProxy.turnOn();
        remoteProxy.turnOff();
    }
}
