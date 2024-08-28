package org.comstudy;

// Servlet&JSP는 Eclipse에서 하는것이 편함.

import java.util.Scanner;

// JAVA 기본이나 Srping boot는 VS-Code도 편함.
// 이유는 VS-Code가 가볍고 편함.
// 그리고 많은 확장기능이 있다.

public class App {
    public static void main(String[] args) {
        // 변수명 초기화는 변수를 선언할때 초기값을 넣어 주는것.
        // 초기값은 리터럴이라고도 표현한다.
        // 변수 선언 : 타입 변수명 = 초기값;
        // 타입은 기본타입(8가지), 참조타입
        // 기본타입: 
        // ---- 논리형 boolean
        // ---- 실수형 float, double (부동 소수점을 갖는다, 기본형은 double) 3.14, 3.14f
        // ---- 정수형 byte, short, int, long (정수형의 기본형은 int) 365, 365L
        // ---- 문수형 char (문자형도 정수형에 속한다. 문자는 아스키코드이기 때문)
        // String은 문자열을 다루는 타입으로 기본형처럼 쓰이는 참조형(클래스)

        // 키보드 입력
        Scanner scan = null;
        try {
            scan = new Scanner(System.in);

            String greeting = "안녕 세계";
            System.out.print("이름 입력: ");
            String name = scan.next();

            System.out.println(greeting);
            System.out.println(name);
        } finally {
            // Scanner 객체를 닫아 자원 누수를 방지합니다.
            if (scan != null) {
                scan.close();
            }
        }
    }
}
