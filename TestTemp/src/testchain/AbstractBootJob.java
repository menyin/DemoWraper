package testchain;

public abstract class AbstractBootJob implements BootJob{
    private BootJob next;

    public BootJob setNext(BootJob next) {
        this.next = next;
        return this.next;
    }

    protected abstract void startTask();
    protected abstract void stopTask();

    public void start(){
        startTask();
        if(this.next!=null){
            this.next.start();
        }
    }
    public void stop(){
        stopTask();
        if(this.next!=null){
            this.next.stop();
        }
    }

}
