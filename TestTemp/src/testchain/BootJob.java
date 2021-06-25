package testchain;

public interface BootJob {
    void start();
    void stop();
    BootJob setNext(BootJob next);
}
