package com.utstar.c1handler.repositories;

import com.utstar.c1handler.config.ConfigReader;
import com.utstar.c1handler.config.SpringContextUtil;
import com.utstar.c1handler.util.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class JdbcDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcDao.class);

    private static final ConfigReader configReader = SpringContextUtil.getBean(ConfigReader.class);

    //查询epggroupid
    public static Integer getEpggroupid(String code){
        Integer epgpageid = 1;
        try {
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement prepareStatement = connection
                    .prepareStatement("select epgpageid from epgpage where code=?");
            prepareStatement.setString(1, code);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                epgpageid = resultSet.getInt(1);
            }
            JDBCUtil.colseResource(connection, prepareStatement, resultSet);
        }catch (Exception ex) {
            log.error("getEpggroupid fail", ex.getStackTrace());
        }
        return  epgpageid;
    }

    /**
     * Get the next object ID from Sequence
     *
     * @param sequenceName
     * @return
     */
    public static Integer getNextObjectIDFromSQ(String sequenceName) {
        String id = "";
        if (configReader.getDatasource().equalsIgnoreCase("1")) {
            CallableStatement cstmt = null;
            try {
                cstmt = JDBCUtil.getConnection().prepareCall("{ call nextval_safe(?,?) }");
                cstmt.setString(1, sequenceName);
                cstmt.setInt(2, 0);
                cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
                cstmt.execute();
                id = cstmt.getString(2);
                JDBCUtil.closeCallableStatement(cstmt);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                JDBCUtil.closeCallableStatement(cstmt);
                log.error("Get the next sequence failure for " + sequenceName
                        + ":" + sqle.getMessage());
            } finally {
                JDBCUtil.closeCallableStatement(cstmt);
            }
        } else {
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = JDBCUtil.getConnection().createStatement();
                String sql = "SELECT " + sequenceName + ".nextval FROM DUAL";
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    id = JDBCUtil.getString(rs, 1);
                }
                if (rs != null) {
                    rs.close();
                }
                JDBCUtil.closeRsStatement(stmt, rs);

            } catch (Exception sqle) {
                JDBCUtil.closeRsStatement(stmt, rs);
                log.error("Get the next sequence failure for " + sequenceName
                        + ":" + sqle.getMessage());
            } finally {
                JDBCUtil.closeRsStatement(stmt, rs);
            }
        }
        return Integer.parseInt(id);
    }
}
