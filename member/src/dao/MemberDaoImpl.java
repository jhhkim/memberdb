package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import comn.Util;


import dto.MemberDto;


public class MemberDaoImpl implements MemberDao {
	private Connection conn; // DB 커넥션 연결 객체
	static String[] code =Util.readLineFile("C:/dev/DBcode.txt").split("\\n");
    private static final String USERNAME = code[0];//DBMS접속 시 아이디
    private static final String PASSWORD = code[1];//DBMS접속 시 비밀번호
    private static final String URL = code[2];//DBMS접속할 DB명
	
	// 생성자에 연결 
	public MemberDaoImpl() {
		// 디비 연결 부분
		try {
            System.out.println("생성자");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("연결 성공");
        } catch (Exception e) {
            System.out.println("연결 실패 ");
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException e1) {    }
        }
        
    
	}

	@Override
	public void insert(MemberDto dto) {
		// TODO Auto-generated method stub
        String sql = "INSERT INTO member (name, major, email) VALUES(?,?,?)";
        
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getMajor());
            pstmt.setString(3, dto.getEmail());

            
            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("데이터 삽입 성공!");
                
            }
            
        } catch (Exception e) {
            System.out.println("member 데이터 삽입 실패!");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
        
        
    }   
    		
	

	@Override
	public MemberDto selectOne(int num) {
		// TODO Auto-generated method stub
        String sql = "select * from member where num = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                System.out.println("num: " + rs.getInt("num"));
                System.out.println("name: " + rs.getString("name"));
                System.out.println("major: " + rs.getString("major"));
                System.out.println("email: " + rs.getString("email"));
                
            }
            
            
            
        } catch (Exception e) {
            System.out.println("select 메서드 예외발생");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
		return null;
    }
	

	@Override
	public ArrayList<MemberDto> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
        String sql = "SELECT * FROM member";
        PreparedStatement pstmt = null;
        
    	
        try {
        	pstmt = conn.prepareStatement(sql);  
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	MemberDto dto = new MemberDto();//dto 안에 넣어야 행 갯수만큼 만들어짐
            	dto.setName(rs.getString("name"));
            	dto.setMajor(rs.getString("major"));
            	dto.setEmail(rs.getString("email"));
            	list.add(dto);
           }
        } catch (Exception e) {
            System.out.println("select 메서드 예외발생");
        } finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
        return list;
	}

	@Override
	public void update(MemberDto dto) {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET email =? WHERE num =?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"이메일수정");
            pstmt.setInt(2,dto.getNum());
            pstmt.executeUpdate();
            System.out.println("수정된 번호: " + dto.getNum());
            
        } catch (Exception e) {
            System.out.println("update 예외 발생");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }		
	}

}
