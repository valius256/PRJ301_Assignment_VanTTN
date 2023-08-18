    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basicobject;

import java.io.Serializable;

/**
 *
 * @author Quang Phat
 */
public class Faq implements Serializable {
    private int id;
    private String custName;
    private String  cusContent;
    private int itemId;
    private String itemName;
    
    public Faq() {
    }

    public Faq(int id, String custName, String cusContent, int itemId, String itemName) {
        this.id = id;
        this.custName = custName;
        this.cusContent = cusContent;
        this.itemId = itemId;
        this.itemName = itemName;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCusContent() {
        return cusContent;
    }

    public void setCusContent(String cusContent) {
        this.cusContent = cusContent;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Faq{");
        sb.append("id=").append(id);
        sb.append(", custName=").append(custName);
        sb.append(", cusContent=").append(cusContent);
        sb.append(", itemId=").append(itemId);
        sb.append(", itemName=").append(itemName);
        sb.append('}');
        return sb.toString();
    }

    
}
