https://www.cnblogs.com/dolphin0520/p/3920373.html
```
// synchronized keywords: (usually used in instance method, other methods are valid too )

synchronized(Context) {
	// block of code
}

```

e.g: 

```
private int bb = 0;
public void yield() {
	synchronized(this) {
		bb++;
	}
}
```