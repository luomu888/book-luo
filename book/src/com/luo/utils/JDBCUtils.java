package com.luo.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    static {
        try {
            Properties properties = new Properties();
            // ��ȡ jdbc.properties ���������ļ�
            InputStream inputStream =
                    JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // �����м�������
            properties.load(inputStream);
            // ���� ���ݿ����� ��
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * ��ȡ���ݿ����ӳ��е�����
     * @return ������� null,˵����ȡ����ʧ��<br/>��ֵ���ǻ�ȡ���ӳɹ�
     */
    public static Connection getConnection(){
        Connection conn = conns.get();
        if (conn == null) {
            try {
                //�����ݿ����ӳ��л�ȡ����
                conn = dataSource.getConnection();
                // ���浽 ThreadLocal �����У�������� jdbc ����ʹ��
                conns.set(conn);
                // ����Ϊ�ֶ���������
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    /**
     * �ύ���񣬲��ر��ͷ�����
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        // ��������� null��˵�� ֮ǰʹ�ù����ӣ����������ݿ�
        if (connection != null) {
            try {
                // �ύ ����
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    // �ر����ӣ���Դ��Դ
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // һ��Ҫִ�� remove ����������ͻ��������Ϊ Tomcat �������ײ�ʹ�����̳߳ؼ�����
        conns.remove();
    }
    /**
     * �ع����񣬲��ر��ͷ�����
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        // ��������� null��˵�� ֮ǰʹ�ù����ӣ����������ݿ�
        if (connection != null) {
            try {
                //�ع�����
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    // �ر����ӣ���Դ��Դ
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // һ��Ҫִ�� remove ����������ͻ��������Ϊ Tomcat �������ײ�ʹ�����̳߳ؼ�����
        conns.remove();
    }
    /**
     * �ر����ӣ��Ż����ݿ����ӳ�
     * @param conn
     * */
//    public static void close(Connection conn){
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
