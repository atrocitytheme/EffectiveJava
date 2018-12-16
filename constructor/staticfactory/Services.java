package staticfactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Services {
    private Services() {

    }

    private static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();

    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    // provider registration API

    public static void registerDefaultProvider(Provider p) {
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }

    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    public static Service newInstance(String name) throws IllegalAccessException{
        Provider p = providers.get(name);

        if (p == null) {
            throw new IllegalAccessException("no pro");
        }

        return p.newService();
    }
}
