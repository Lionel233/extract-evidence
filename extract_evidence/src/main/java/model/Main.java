package model;

import java.util.ArrayList;

public class Main {
	public static void main(String [] args){
		ArrayList<Litigant> list = Litigant.getLitigants("D://workspace/extract_evidence/src/main/resources/in/刑事一审/11.xml");
		for(Litigant litigant:list){
			System.out.println(litigant.getName());
			System.out.println(litigant.getType().getType());
		}
	}
}
