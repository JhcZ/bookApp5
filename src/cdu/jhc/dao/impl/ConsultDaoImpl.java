package cdu.jhc.dao.impl;

import cdu.jhc.dao.AdminUserDao;
import cdu.jhc.dao.BaseDao;
import cdu.jhc.dao.ConsultDao;
import cdu.jhc.model.Consult;
import cdu.jhc.model.ConsultStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultDaoImpl extends BaseDao implements ConsultDao{
    AdminUserDao adminUserDao = new AdminUserDaoImpl();
    @Override
    public Consult findById(int id) {
        Consult consult = null;
        String sql = "SELECT * FROM consult_table WHERE id=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                consult = new Consult();
                consult.setId(rs.getInt("id"));
                consult.setBookId(rs.getInt("bookId"));
                consult.setConsultingUser(rs.getNString("consultingUser"));
                consult.setQuestion(rs.getNString("question"));
                consult.setAskTime(rs.getLong("askTime"));
                consult.setReply(rs.getNString("reply"));
                consult.setReTime(rs.getLong("reTime"));
                consult.setAdminUser(adminUserDao.findById(rs.getInt("adminId")));
                consult.setReStatus(rs.getNString("reStatus"));
            }
        }catch(SQLException e){
            System.out.println("DAO查询咨询出错：" + sql + "," +  e.getMessage());
        }
        return consult;
    }

    @Override
    public List<Consult> query(Consult condition) {
        List<Consult> consultList = new ArrayList<>();
        String sql = "SELECT * FROM consult_table" + getQuerySql(condition) + " ORDER BY askTime DESC";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Consult consult = new Consult();
                consult.setId(rs.getInt("id"));
                consult.setBookId(rs.getInt("bookId"));
                consult.setConsultingUser(rs.getString("consultingUser"));
                consult.setQuestion(rs.getString("question"));
                consult.setAskTime(rs.getLong("askTime"));
                consult.setReply(rs.getString("reply"));
                consult.setReTime(rs.getLong("reTime"));
                consult.setAdminUser(adminUserDao.findById(rs.getInt("adminId")));
                consult.setReStatus(rs.getString("reStatus"));
                consultList.add(consult);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询咨询出错：" + e.getMessage());
        }
        return consultList;
    }

    @Override
    public List<Consult> query(Consult condition, int start, int num) {
        List<Consult> consultList = new ArrayList<>();
        String sql = "SELECT * FROM consult_table" + getQuerySql(condition) + " ORDER BY askTime DESC LIMIT ?,?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, num);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Consult consult = new Consult();
                consult.setId(rs.getInt("id"));
                consult.setBookId(rs.getInt("bookId"));
                consult.setConsultingUser(rs.getString("consultingUser"));
                consult.setQuestion(rs.getString("question"));
                consult.setAskTime(rs.getLong("askTime"));
                consult.setReply(rs.getString("reply"));
                consult.setReTime(rs.getLong("reTime"));
                consult.setAdminUser(adminUserDao.findById(rs.getInt("adminId")));
                consult.setReStatus(rs.getString("reStatus"));
                consultList.add(consult);
            }
        }catch (SQLException e){
            System.out.println("DAO分页查询咨询出错：" + e.getMessage());
        }
        return consultList;
    }

    @Override
    public int insert(Consult consult) {
        int rows = 0;
        String sql = "INSERT INTO consult_table(bookId,question,askTime,consultingUser,reStatus) " +
                "VALUES(?,?,?,?,?) ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, consult.getBookId());
            pstmt.setString(2, consult.getQuestion());
            pstmt.setLong(3, consult.getAskTime());
            pstmt.setString(4, consult.getConsultingUser());
            pstmt.setString(5, consult.getReStatus().getName());
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("DAO添加咨询出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int update(Consult consult) {
        int rows = 0;
        String sql = "UPDATE consult_table SET reply=?,reTime=?,adminId=?,reStatus=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, consult.getReply());
            pstmt.setLong(2, consult.getReTime());
            pstmt.setInt(3, consult.getAdminUser().getId());
            pstmt.setString(4, consult.getReStatus().getName());
            pstmt.setInt(5, consult.getId());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO修改咨询回复出错：" + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM consult_table WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO删除咨询出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int deleteByBookId(int bookId, ConsultStatus status) {
        int rows = 0;
        String sql = "DELETE FROM consult_table WHERE bookId=?";
        if (status != ConsultStatus.ALL) {
            sql += "AND reStatus='" + status.getName() + "'";
        }
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO删除咨询出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(Consult condition) {
        int num = 0;
        String sql = "SELECT count(*) FROM consult_table" + getQuerySql(condition);
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询咨询数量出错：" + e.getMessage());
        }
        return num;
    }

    private String getQuerySql(Consult condition){
        String sql = "";
        if(condition != null){
            sql += " WHERE 1=1";
            if(condition.getBookId() != 0){
                sql += " AND bookId='" + condition.getBookId() + "'";
            }
            if (condition.getQuestion() != null && !condition.getQuestion().isEmpty()) {
                sql += " AND question LIKE '%" + condition.getQuestion() + "%'";
            }
            if (condition.getConsultingUser() != null && !condition.getConsultingUser().isEmpty()) {
                sql += " AND consultingUser LIKE '%" + condition.getConsultingUser() + "%'";
            }
            if (condition.getAdminUser() != null) {
                sql += " AND adminId='" + condition.getAdminUser().getId() + "'";
            }
            if (condition.getReStatus() != ConsultStatus.ALL) {
                sql += " AND reStatus='" + condition.getReStatus().getName() + "'";
            }
        }
        System.out.println("DAO模糊查询Consult条件：" + sql);
        return sql;
    }
}
