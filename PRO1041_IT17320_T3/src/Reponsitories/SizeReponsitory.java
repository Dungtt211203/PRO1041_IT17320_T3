/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reponsitories;

import DomainModel.Size;
import ViewModels.SizeVM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SizeReponsitory {
        
    public void insert(Size s) {
        try {
            Connection conn = Util.DBContext.getConnection();
            String sql = "INSERT INTO Size" + "(MaSIZE,TenSIZE)" + "VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMa());
            ps.setString(2, s.getTen());
            ps.execute();
            System.out.println("Thêm thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void update(Size s, String id) {
        try {
            Connection conn = Util.DBContext.getConnection();
            String sql = "UPDATE Size SET " + "MaSIZE=?,TenSIZE=? WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMa());
            ps.setString(2, s.getTen());
            ps.setString(3, id);
            ps.executeUpdate();
            System.out.println("Sửa thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void delete(String id) {
        try {
            Connection conn = Util.DBContext.getConnection();
            String sql = "DELETE FROM Size WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Xóa thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public ArrayList<SizeVM> all() {
        ArrayList<SizeVM> listSize = new ArrayList<>();
        try {
            Connection conn = Util.DBContext.getConnection();
            String sql = "SELECT * FROM Size";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("MaSIZE");
                String ten = rs.getString("TenSIZE");

                SizeVM s = new SizeVM(id, ma, ten);
                listSize.add(s);
            }
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
            e.getMessage();
        }
        return listSize;
    }
}
