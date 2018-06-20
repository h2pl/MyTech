package httpkids.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class User {

	private String name;

	private AtomicInteger counter;

	public User(String name) {
		this.name = name;
		this.counter = new AtomicInteger();
	}

	public String getName() {
		return name;
	}

	public int getCounter() {
		return counter.get();
	}

	public int incrCounter() {
		return this.counter.incrementAndGet();
	}

}
