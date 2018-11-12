package com.hsjavaclass.app;

public class Concurrency {
	static class Philosopher implements Runnable {
		private String name;
		private Object leftFork;
		private Object rightFork;

		public Philosopher(String name, Object leftFork, Object rightFork) {
			this.name = name;
			this.leftFork = leftFork;
			this.rightFork = rightFork;
		}

		private void think() throws InterruptedException {
			System.out.println(name + " is thinking");
			Thread.sleep((long) Math.random() * 1000);
		}

		private void eat() throws InterruptedException {
			System.out.println(name + " is eating");
			Thread.sleep((long) Math.random() * 1000);
		}

		@Override
		public void run() {
			try {
				while(true) {
					think();
					synchronized (leftFork) {
						System.out.println(name + " picking up left fork");
						synchronized (rightFork) {
							System.out.println(name + " picking up right fork");
							eat();
							System.out.println(name + " putting down right fork");
						}
						System.out.println(name + " putting down left fork");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Philosopher[] philosophers = new Philosopher[5];
		Object[] forks = new Object[5];

		for(int i = 0; i < 5; i ++) {
			forks[i] = new Object();
		}

		for(int i = 0; i < 5; i ++) {
			Object leftFork = forks[i];
			Object rightFork = forks[(i + 1) % 5];

			if(i == 0)
				philosophers[i] = new Philosopher("Bob " + (i + 1), rightFork, leftFork);
			else
				philosophers[i] = new Philosopher("Bob " + (i + 1), leftFork, rightFork);

			Thread t = new Thread(philosophers[i]);
			t.start();
		}
	}
}