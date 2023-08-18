/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import basicobject.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Quang Phat
 */
public class ItemDao {

    public static ArrayList<Item> getAllItem() throws Exception {
        Connection cn = DBUtils.makeConnection();
        ArrayList<Item> list = new ArrayList<>();
        if (cn != null) {
            String sql = "SELECT ItemId,ItemName,Price,CateId FROM dbo.Items";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("ItemId");
                    String name = rs.getString("ItemName");
                    int price = rs.getInt("Price");
                    int cateId = rs.getInt("CateId");
                    Item item = new Item(id, name, price, cateId);
                    list.add(item);
                }
            }
            cn.setAutoCommit(true);
            cn.close();
        }
        return list;
    }

    public static int insertITem(int itemId, String itemName, int price_raw, int cateID) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "INSERT dbo.Items\n"
                    + "(\n"
                    + "    ItemId,\n"
                    + "    ItemName,\n"
                    + "    Price,\n"
                    + "    CateId,\n"
                    + "    Status\n"
                    + ")\n"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, itemId);
            pst.setString(2, itemName);
            pst.setInt(3, price_raw);
            pst.setInt(4, cateID);
            pst.setInt(5, 1);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    public static ArrayList<Item> getItemByID(int id) throws Exception {
        try ( Connection cn = DBUtils.makeConnection()) {
            ArrayList<Item> items = new ArrayList<>();
            if (cn != null) {
                String sql = "SELECT i.ItemId, i.ItemName,i.Price,c.CateName,c.CateId FROM dbo.Items i LEFT JOIN dbo.Categories c \n"
                        + "ON i.CateId = c.CateId \n"
                        + "WHERE c.Status = 1 AND i.ItemId = ?";
                try ( PreparedStatement ps = cn.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    try ( ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            int itemId = rs.getInt("ItemId");
                            String name = rs.getString("ItemName");
                            int price = rs.getInt("Price");
                            int type = rs.getInt("CateId");
                            Item item = new Item(itemId, name, price, itemId);
                            items.add(item);
                        }
                    }
                }
            }
            return items;
        }
    }
    
    

    public static void updateItem(Item item) throws Exception {
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE dbo.Items SET ItemId = ?, ItemName =?, Price = ?, CateId = ? WHERE\n"
                    + "ItemId = ? ";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, item.getItemId());
            ps.setString(2, item.getItemName());
            ps.setInt(3, item.getPrice());
            ps.setInt(4, item.getCateId());
            ps.setInt(5, item.getItemId());
            ps.executeUpdate();
            ps.close();
        }
        cn.setAutoCommit(true);
        cn.close();
    }

    /*  có 2 trường h  hợp xử lí
1 là xóa cái reference 

2 là chạy đoạn code 3 dòng 
-- Step 1: Identify the referencing table (assuming it's called "ReferencingTable")
-- Step 2: Find the referencing records
SELECT * FROM ReferencingTable WHERE ItemId = <ItemId_value>;

-- Step 3: Update the referencing records (remove the reference)
UPDATE ReferencingTable SET ItemId = NULL WHERE ItemId = <ItemId_value>;

-- Step 4: Delete the record from the dbo.FAQ table
DELETE FROM dbo.FAQ WHERE ItemId = <ItemId_value>;

     */
    // truong hop khong tham chieu
    public static int deleteItem2(int id) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "DELETE FROM dbo.Items WHERE ItemId = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeUpdate();
            cn.setAutoCommit(true);
            cn.close();
        }
        return rs;
    }

    // truong hop co tham chieu
    public static int deleteItem(int id) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            try {
                String sql = "DELETE FROM dbo.FAQ WHERE ItemId = ?";
                String sql1 = "DELETE FROM dbo.Items WHERE ItemId = ?";

                PreparedStatement ps = null;
                PreparedStatement ps1 = null;

                try {
                    ps = cn.prepareStatement(sql);
                    ps.setInt(1, id);
                    int rowsAffected1 = ps.executeUpdate();

                    ps1 = cn.prepareStatement(sql1);
                    ps1.setInt(1, id);
                    int rowsAffected2 = ps1.executeUpdate();

                    cn.commit();

                    // Process rowsAffected1 and rowsAffected2 if needed
                } catch (SQLException e) {
                    // Handle any database-related errors
                    e.printStackTrace();

                    // Rollback the transaction if needed
                    cn.rollback();

                } finally {
                    // Close resources in a finally block
                    if (ps != null) {
                        ps.close();
                    }

                    if (ps1 != null) {
                        ps1.close();
                    }

                    cn.close();
                }
            } catch (SQLException e) {
                // Handle any connection-related errors
                e.printStackTrace();
            }
        }
        return rs;
    }

}
