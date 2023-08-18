/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import basicobject.Category;
import basicobject.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Quang Phat
 */
public class CategoryDao {

    public static ArrayList<Category> getAllCategory() throws SQLException, Exception {
        Connection cn = DBUtils.makeConnection();
        ArrayList<Category> list = new ArrayList<>();
        if (cn != null) {
            String sql = "SELECT [CateId], [CateName], [Status]\n"
                    + "FROM dbo.Categories";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("CateId");
                    String name = rs.getString("CateName");
                    int status = rs.getInt("Status");
                    Category category = new Category(id, name, status);
                    list.add(category);
                }
            }
            cn.close();
        }
        return list;
    }

}
