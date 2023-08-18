/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import basicobject.Faq;
import basicobject.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Quang Phat
 */
public class FaqDao {

    public static ArrayList<Faq> getFaqByID(int id) throws Exception {
        try ( Connection cn = DBUtils.makeConnection()) {
            ArrayList<Faq> items = new ArrayList<>();
            if (cn != null) {
                String sql = "SELECT i.ItemName, f.Id, f.CustName, f.CustContent, i.ItemId  FROM dbo.Items i LEFT JOIN dbo.FAQ f\n"
                        + "ON i.ItemId = f.ItemId WHERE f.id = ?";
                try ( PreparedStatement ps = cn.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    try ( ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            String itemName = rs.getString("ItemName");
                            int Id = rs.getInt("Id");
                            String custName = rs.getString("CustName");
                            String custContent = rs.getString("CustContent");
                            int itemId = rs.getInt("ItemId");
                            Faq faq = new Faq(id, custName, custContent, itemId, itemName);
                            items.add(faq);
                        }
                    }
                }
            }
            return items;
        }
    }
}
