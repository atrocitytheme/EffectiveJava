/*
    *java support for model view and controller
    (or MVC structure)

    * Observer -- any object that wishes to
      be notified when the state of another
      object changes

    * Observable -- any object whose state may
      be of interest, and in whom another object may
      register an interest

    *Observer (observe the observable)

    public void update(Observable obs, Object obj)

    Called when a change has occurred in the state of the observable.

*/

import java.util.Observable;
import java.util.Observer;

public class Observe {

    public static void main(String[] args) {
        ObservableValue ov = new ObservableValue(5);
        TextObserver to = new TextObserver(ov);

        ov.addObserver(to);

        ov.setValue(10);
    }

}


class ObservableValue extends Observable {
    private int n = 0;

    public ObservableValue(int n) {
        this.n = n;
    }

    public void setValue(int n) {
        this.n = n;
        setChanged();
        notifyObservers();
    }

    public int getValue() {
        return n;
    }
}

class TextObserver implements Observer {
    private ObservableValue ov = null;

    public TextObserver(ObservableValue ov) {
        this.ov = ov;
    }

    public void update(Observable obs, Object obj) {
        if (obs == ov) {
            System.out.println(ov.getValue());
        }
    }
}

