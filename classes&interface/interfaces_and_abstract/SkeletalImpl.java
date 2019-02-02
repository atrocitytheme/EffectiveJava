/**
* https://dzone.com/articles/favour-skeletal-interface-in-java
*
* https://stackoverflow.com/questions/12891211/are-utility-classes-allowed-with-the-single-responsibility-principle-srp
* https://stackoverflow.com/questions/3340032/utility-classes-are-evil
* interface with default methods is better than utility class
* The skeletal implementatoin can also be replaced by default method in interfae in release of jdk8,
that is, functionally abstract class is currently replacable by interface, 
but Once an interface is released and widely implemented (interfaces are made to be widely implemented), it is almost impossible to change,
    we can still use abstract class as a symbol of a specific skeletal impl
* */

interface Ivenging {
    void start();
    void chooseProduct();
    void stop();
    void process();
}

class VendingService {
    public void service()
    {
        System.out.println("Clean the vending machine");
    }
}

abstract class AbstractVending implements Ivenging {
    public void start()
    {
        System.out.println("Start Vending machine");
    }
    public void stop()
    {
        System.out.println("Stop Vending machine");
    }
    public void process()
    {
        start();
        chooseProduct();
        stop();
    }
}

class CandyVending implements Ivenging{

    private class AbstractVendingDelegator extends AbstractVending {
        @Override
        public void chooseProduct() {
            System.out.println("Produce diiferent candies");
            System.out.println("Choose a type of candy");
            System.out.println("pay for candy");
            System.out.println("collect candy");
        }
    }

    AbstractVendingDelegator delegator = new AbstractVendingDelegator();

    @Override
    public void start() {
        delegator.start();
    }
    @Override
    public void chooseProduct() {
        delegator.chooseProduct();
    }
    @Override
    public void stop() {
        delegator.stop();
    }
    @Override
    public void process() {
        delegator.process();
    }

}
public class SkeletalImpl {

}
