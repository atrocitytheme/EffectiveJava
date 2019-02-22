Sometimes we may encounter situation where we need to store different types of data in memory, then, instead of 
statically defining type in the container, such as Set<<specific type>>, we can use another method, that is, dynamically define a class in the getter, in this way, when we utilize the Class.cast method, we can still ensure the safety in type and at the same time maintain a container that stores multiple types.

```
package code_feature.dynamiccode;

import java.util.HashMap;
import java.util.Map;

public class DynamicCast {
	
	private Map<Class<?>, Object> map = new HashMap<>(); // we can simulate the python list in this way

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private <T> void putFavoriate(Class<T> type, T instance) { // cast the expected class type in this way
		// put the type in the input
		map.put(type, type.cast(instance));
	}
	
	private <T> T get(Class<T> type) {
		return type.cast(map.get(type));
	}
}

```