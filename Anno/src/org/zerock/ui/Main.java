package org.zerock.ui;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.zerock.anno.Job;

public class Main {
	public static void main(String[] args) throws Exception{
		Scanner scanner = new Scanner(System.in);
		
		String className = "org.zerock.demo.";
		
		System.out.println("클래스 이름을 입력하세요");
		
		className = className + scanner.nextLine();
		
		Class<?> clz = Class.forName(className); 
		Constructor<?> con = clz.getConstructor(null); 
		
		Object obj = con.newInstance(null); 
		
		System.out.println(obj); 
		
		System.out.println("What do you want to do?");
		
		String jobType = scanner.nextLine();
		
		Method[] methods = clz.getDeclaredMethods();
		
		Method target = null;
		
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			System.out.println(m);
			Job anno = m.getAnnotation(Job.class); 
			System.out.println(anno);
			
			if(anno == null) {
				continue;
			}
			if(anno.value().equals(jobType)) { 
				target = m;
			}
			
			System.out.println("-------------------------");
		}
		
		if(target != null) { 
			target.invoke(obj, null);
		}
		
	}
}
