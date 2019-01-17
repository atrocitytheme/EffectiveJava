package proxy;

public class ProxyPattern {
    public static void main(String[] args) {
        Prop p = new PropProxy(10);
        callProp(p);
    }

    static void callProp(Prop p) {
        p.displayProp();
    }
}


interface Prop {
    void displayProp();
}

//Proxy to display the real one
final class PropProxy implements Prop {
    private final int sha;
    public PropProxy(int sha) {
        this.sha = sha;
    }

    @Override
    public void displayProp() {
        RealProp prop = new RealProp(sha);
        prop.displayProp();
    }
}

// Real Object
class RealProp implements Prop {
    private final int sha;

    RealProp(int sha) {
        this.sha = sha;
    }

    @Override
    public void displayProp() {
        System.out.println(sha);
    }
}