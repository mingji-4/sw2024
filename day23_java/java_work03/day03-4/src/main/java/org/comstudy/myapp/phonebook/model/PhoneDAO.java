package org.comstudy.myapp.phonebook.model;

import java.util.ArrayList;

public class PhoneDAO {
	public static ArrayList<Phone> phoneList = new ArrayList<Phone>();
	public static int phoneSeq = 1;
	static {
		phoneList.add(new Phone(phoneSeq++, "홍길동", "010-1111-1111"));
		phoneList.add(new Phone(phoneSeq++, "김길동", "010-1111-2222"));
		phoneList.add(new Phone(phoneSeq++, "박길동", "010-1111-3333"));
	}
}