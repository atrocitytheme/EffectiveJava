package thirdsingleton;
/*
* this automatically creates an instance of Elvis
* */
public enum Elvis {
    INSTANCE;
    public void leaveTheBuilding() {
    }
}

class test {
    public static void test() {
        Elvis.INSTANCE.leaveTheBuilding();
    }
}