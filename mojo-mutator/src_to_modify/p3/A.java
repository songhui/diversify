package p3;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.irisa.diversify.annotations.Perforable;

public class A implements KeyListener{

	public A() {
		k= this;
	}

	public static void main(String[] args) {
		new A().foo();
	}

	@Perforable(step = 5)
	public void foo() {
		int j = 0;
		for (int i = 0; i < 100; i++) {
			j++;
		}
		System.err.println(j);
		
		

	}

	@Perforable
	public void foo1() {
		int j = 0;
		while (true) {
			for (int i = 0; i < 100; i++) {
				j++;
			}
			System.err.println(j);
		}

	}
	
	KeyListener k ;

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
