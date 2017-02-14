package com.form2bgames.megarisk.api;

import java.io.Console;

public class MRAPIMain {
	public static Console cons;
	public static void main(String[] args) {
		 if ((cons = System.console()) == null ) {
		     throw new RuntimeException("A console must be present");
		 }
		 new ConsoleThread().run();
	}

}
