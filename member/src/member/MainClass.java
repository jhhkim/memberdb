package member;

import java.util.ArrayList;

import comn.Util;

public class MainClass {

	public static void main(String[] args) {
		//멤버 정보 DB 활용
		
		//파일에 있는 데이터를 읽어오기
		String filePath = "C:/dev/전공정보.txt";
		Member member = new Member();
		ArrayList<MemberDto> list = member.getMemberList(filePath);
		for (MemberDto d:list) {
			System.out.println(d.getName());
		}
	}
//		System.out.println("쉼표로 구분되는 데이터 갯수: "+ spData.length);
		//읽어온 데이터를 DB에 넣기
		
		//데이터 보기
		
		//데이터 수정
		

	}

}
