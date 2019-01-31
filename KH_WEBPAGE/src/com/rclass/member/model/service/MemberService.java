package com.rclass.member.model.service;

import java.sql.Connection;

import com.rclass.member.model.vo.member.MemberDao;
import com.rclass.member.vo.Member;
import static common.JDBCTemplete.*;

public class MemberService {

	public Member selectOne(Member m)
	{
		Connection conn = getConnection();
		Member result=new MemberDao().selectOne(conn,m);
		return result;
	}
	
	public int insertMember(Member m)
	{
		Connection conn=getConnection();
		int result=new MemberDao().insertMember(conn , m);
		if (result>0)
		{
			commit(conn);
		}else
		{
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int updateMember(Member m)
	{
		Connection conn=getConnection();
		int result = new MemberDao().updateMember(conn,m);
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
			
		}
		close(conn);
		return result;
	}
	public int deleteMember(String userId)
	{
		Connection conn=getConnection();
		int result=new MemberDao().deleteMember(conn,userId);
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn)
		}
		close(conn);
		return result;
		}
	
	public int updatePassword(Member m)
	{
		Connection conn=getConnection();
		int result=new MemberDao().updatePassword(conn,m);
		
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		close(conn);
		return result;
		}
		
		
	}
	

