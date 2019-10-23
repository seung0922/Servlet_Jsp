package org.zerock.demo;

import org.zerock.anno.Job;

public class Calculator {
	
	@Job("plus")
	public void duhagi() {
		System.out.println("duhagi..");
	}
	
	@Job("minus")
	public void beagi() {
		System.out.println("beagi..");
	}
}
