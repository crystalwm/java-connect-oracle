package oracle.pool.database;

import java.sql.*;
import oracle.pool.database.DBConnectionManager;


public class DBConnect {
	private Connection conn = null;		//��ݿ����ӱ���
	private Statement stmt = null;		//Statement������
	private PreparedStatement prepstmt = null;		//Ԥ����Statement����
	private DBConnectionManager dcm=null;			//���ӳع������

    void init() {
        dcm = DBConnectionManager.getInstance();
		conn = dcm.getConnection("oracle");
    }

	/**
	 * ������ݿ�����Ӻͷ�����
	 */
	public DBConnect() throws Exception {
        init();
		stmt = conn.createStatement();
	}

    public DBConnect(int resultSetType, int resultSetConcurrency)
            throws Exception {
        init();
        stmt = conn.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * ������ݿ�����Ӻͷ�����
     * Ԥ����SQL���
     * @param sql SQL���
     */
	public DBConnect(String sql) throws Exception {
        init();
		this.prepareStatement(sql);
	}

	public DBConnect(String sql, int resultSetType, int resultSetConcurrency)
            throws Exception {
        init();
		this.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * ��������
	 * @return Connection ����
	 */
	public Connection getConnection() {
		return conn;
	}
	
	/**
	 * �ر�����
	 */
	public void close() throws Exception {
		if (stmt != null)  {
			stmt.close();
			stmt = null;
		}
		if (prepstmt != null) {
			prepstmt.close();
			prepstmt = null;
		}
		if (conn!=null)
		{
			
			dcm.freeConnection("oracle",conn);
			
		}
		
	}

	/**
	 * PreparedStatement
	 * ����һ��PreparedStatement����
	 * @return sql Ԥ��SQL���
	 */
	public void prepareStatement(String sql) throws SQLException {
		prepstmt = conn.prepareStatement(sql);
	}

	public void prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
		prepstmt = conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * ���ö�Ӧֵ
     *
	 * @param index ��������
	 * @param value ��Ӧֵ
	 */
	public void setString(int index,String value) throws SQLException {
		prepstmt.setString(index, value);
	}
	public void setInt(int index,int value) throws SQLException {
		prepstmt.setInt(index,value);
	}
	public void setBoolean(int index,boolean value) throws SQLException {
		prepstmt.setBoolean(index,value);
	}
	public void setDate(int index,Date value) throws SQLException {
		prepstmt.setDate(index,value);
	}
	public void setLong(int index,long value) throws SQLException {
		prepstmt.setLong(index,value);
	}
	public void setFloat(int index,float value) throws SQLException {
		prepstmt.setFloat(index,value);
	}
	public void setBytes(int index,byte[] value) throws SQLException{
		prepstmt.setBytes(index,value);
	}
	 
    public void clearParameters()
        throws SQLException
    {
        prepstmt.clearParameters();
	prepstmt=null;
    }
	/**
	 * ����Ԥ��״̬
	 */
	public PreparedStatement getPreparedStatement() {
		return prepstmt;
	}
	/**
	 * ����״̬
	 * @return Statement ״̬
	 */
	public Statement getStatement() {
		return stmt;
	}
	/**
	 * ִ��SQL��䷵���ֶμ�
	 * @param sql SQL���
	 * @return ResultSet �ֶμ�
	 */
	public ResultSet executeQuery(String sql) throws SQLException {
		if (stmt != null) {
			return stmt.executeQuery(sql);
		}
		else return null;
	}

	public ResultSet executeQuery() throws SQLException {
		if (prepstmt != null) {
			return prepstmt.executeQuery();
		}
		else return null;
	}

	/**
	 * ִ��SQL���
	 * @param sql SQL���
	 */
	public int executeUpdate(String sql) throws SQLException {
		if (stmt != null)
			return stmt.executeUpdate(sql);
		else
			return -1;
	}
	public int executeUpdate() throws SQLException {
		if (prepstmt != null)
			return prepstmt.executeUpdate();
		else
			return -1;
	}

}
