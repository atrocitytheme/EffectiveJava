import Method.*;

public class main {
    public static void main(String[] args) {

        Method <Function> m = new Method<Function>();

            // we find that anonymous class do extends the original class
            // but generic test <? extends function> will still consider it
            // as a superclass, since it doesn't have a subclass name.
            // So we cannot use Method <? extends function> m = ...

        m.push(new Function() {
            @Override
            public void call() {
                System.out.println("asd");
            }
        });

        m.push(new Function() {
            @Override
            public void call() {
                System.out.println("asd1");
            }
        });

        m.push(new Function() {
            @Override
            public void call() {
                System.out.println("asd2");
            }
        });

        m.callAll();
    }
}
