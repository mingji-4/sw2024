//
//  main.swift
//  swift_ex
//
//  Created by 김민지 on 9/11/24.
//

import Foundation

// 전화번호 목록을 저장할 배열
var phoneBook: [[String: String]] = []

// 문제 1: 메뉴 출력 함수
func menu() -> Int {
    print("[1]INPUT [2]OUTPUT [3]SEARCH [4]MODIFY [5]DELETE [6]END")
    if let input = readLine(), let choice = Int(input) {
        return choice
    }
    return 0
}

// 문제 2: 전화번호 추가 함수
func inputSaram() {
    print("-------- INPUT --------")
    print("성명 입력:")
    guard let name = readLine() else { return }
    print("전화번호 입력:")
    guard let phone = readLine() else { return }
    
    let person = ["name": name, "phone": phone]
    phoneBook.append(person)
    print("전화번호부에 저장되었습니다.\n")
}

// 문제 3: 전화번호 목록 출력 함수
func outputSaram() {
    print("-------- OUTPUT --------")
    print("NAME \t PHONE")
    for person in phoneBook {
        print("\(person["name"] ?? "") \t \(person["phone"] ?? "")")
    }
    print()
}

// 문제 4: 전화번호 조회 함수
func search() {
    print("-------- SEARCH --------")
    print("검색 할 이름:")
    guard let name = readLine() else { return }
    
    for person in phoneBook {
        if person["name"] == name {
            print("Found: \(person["phone"] ?? "")\n")
            return
        }
    }
    print("같은 이름의 정보가 없습니다!\n")
}

// 문제 5: 전화번호 수정 함수
func modify() {
    print("-------- MODIFY --------")
    print("수정 할 이름 입력:")
    guard let name = readLine() else { return }
    
    for (index, person) in phoneBook.enumerated() {
        if person["name"] == name {
            print("Enter new phone number:")
            guard let newPhone = readLine() else { return }
            phoneBook[index]["phone"] = newPhone
            print("수정 완료되었습니다.\n")
            return
        }
    }
    print("같은 이름의 정보가 없습니다!\n")
}

// 문제 6: 전화번호 삭제 함수
func delete() {
    print("-------- DELETE --------")
    print("삭제 할 이름:")
    guard let name = readLine() else { return }
    
    for (index, person) in phoneBook.enumerated() {
        if person["name"] == name {
            phoneBook.remove(at: index)
            print("삭제 완료되었습니다.\n")
            return
        }
    }
    print("같은 이름의 정보가 없습니다!\n")
}

// 문제 7: 메인 함수
func main() {
    print("::::::::: 전화 번호부 :::::::::")
    while true {
        let choice = menu()
        switch choice {
        case 1:
            inputSaram()
        case 2:
            outputSaram()
        case 3:
            search()
        case 4:
            modify()
        case 5:
            delete()
        case 6:
            print("프로세스 종료!")
            return
        default:
            print("주의: 잘못된 선택입니다!\n")
        }
    }
}

// 프로그램 실행
main()
