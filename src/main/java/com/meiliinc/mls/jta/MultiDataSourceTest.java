package com.meiliinc.mls.jta;


import com.mysql.cj.jdbc.MysqlXADataSource;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 17/1/6
 * Time: 下午10:30
 * Email:jiexiu@mogujie.com
 */
public class MultiDataSourceTest {

    private static final String dataSourceUrlA = "jdbc:mysql://127.0.0.1:3306/leoNode?useUnicode=true&characterEncoding=utf-8&autoReconnectForPools=true&autoReconnect=true&connectTimeout=0";

    private static final String dataSourceUrlB = "jdbc:mysql://127.0.0.1:3306/leoblog?useUnicode=true&characterEncoding=utf-8&autoReconnectForPools=true&autoReconnect=true&connectTimeout=0";

    private static final String userName = "root";
    private static final String passWord = "1q2w3e4r";

    public static void main(String[] args) {
        testMultiDataSource();
    }

    /**
     * 测试多数据源正常提交
     */
    private static void testMultiDataSource() {
        XADataSource xaDataSourceA = getMysqlDataSource(dataSourceUrlA);
        XADataSource xaDataSourceB = getMysqlDataSource(dataSourceUrlB);

        XAConnection xaConnectionA = null;
        XAConnection xaConnectionB = null;

        Connection connA = null;
        Connection connB = null;
        Statement stmtA = null;
        Statement stmtB = null;

        XAResource xaResourceA = null;
        XAResource xaResourceB = null;
        try {
            xaConnectionA = xaDataSourceA.getXAConnection();
            xaConnectionB = xaDataSourceB.getXAConnection();

            connA = xaConnectionA.getConnection();
            connB = xaConnectionB.getConnection();
            stmtA = connA.createStatement();
            stmtB = connB.createStatement();

            xaResourceA = xaConnectionA.getXAResource();
            xaResourceB = xaConnectionB.getXAResource();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        MyXid xidA = new MyXid(0, new byte[] { 0x01 }, new byte[] { 0x02 });
        MyXid xidB = new MyXid(0, new byte[] { 0x01 }, new byte[] { 0x03 });

        try {
            xaResourceA.start(xidA, XAResource.TMNOFLAGS);
            stmtA.execute("insert into test(name) values ('test123')");
            xaResourceA.end(xidA, XAResource.TMSUCCESS);

            xaResourceB.start(xidB, XAResource.TMNOFLAGS);
            stmtB.execute("insert into test(name) values ('test123')");
            xaResourceB.end(xidB, XAResource.TMSUCCESS);
            //prepare 阶段
            int retA = xaResourceA.prepare(xidA);
            int retB = xaResourceB.prepare(xidB);
            //都准备好了
            if (XAResource.XA_OK == retA && XAResource.XA_OK == retB){
                // 提交阶段
                xaResourceA.commit(xidA, false);
                xaResourceB.commit(xidB, false);
            }else {
                //回滚
                xaResourceA.rollback(xidA);
                xaResourceB.rollback(xidB);
            }
        } catch (XAException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (stmtA != null){
                    stmtA.close();
                }
                if (connA != null){
                    connA.close();
                }
                if (xaConnectionA != null){
                    xaConnectionA.close();
                }
                if (stmtB != null){
                    stmtB.close();
                }
                if (connB != null) {
                    connB.close();
                }
                if (xaConnectionB != null){
                    xaConnectionB.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    private static XADataSource getMysqlDataSource(String dataSourceUrl){
        MysqlXADataSource dataSource = new MysqlXADataSource();
        dataSource.setURL(dataSourceUrl);
        dataSource.setUser(userName);
        dataSource.setPassword(passWord);
        return dataSource;
    }
}
