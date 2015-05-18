package com.sds.icto.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sds.icto.guestbook.vo.GuestBookVO;

@Repository
public class GuestBookDAO {

	private Connection getConnection() throws ClassNotFoundException,
	SQLException {

		Connection con = null;

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(dbURL, "webdb", "webdb");

		return con;
	}

	public void insert(GuestBookVO vo) {
		try {
			Connection con = getConnection();

			String sql = "insert into guestbook values(guestbook_seq.nextval, ?, ?, ?, sysdate )";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());

			pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(Long no, String password) {
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sql = "delete from guestbook where no = ? and password = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete() {
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sql = "delete from guestbook";
			stmt.executeUpdate(sql);

			// 자원정리
			if (stmt != null) {
				stmt.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<GuestBookVO> fetchList() {

		List<GuestBookVO> list = new ArrayList<GuestBookVO>();
		try {
			// 1 connection 생성
			Connection con = getConnection();

			// 2 statement 생성
			Statement stmt = con.createStatement();
			String sql = "select * from guestbook";

			// rs 생성
			ResultSet rs = stmt.executeQuery(sql);

			// 결과처리
			while (rs.next()) {

				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String message = rs.getString(4);
				String reg_date = rs.getString(5);

				GuestBookVO vo = new GuestBookVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setMessage(message);
				vo.setReg_date(reg_date);
				list.add(vo);

			}

			// 자원정리
			if (rs != null) {
				rs.close();
			}

			if (stmt != null) {
				stmt.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
