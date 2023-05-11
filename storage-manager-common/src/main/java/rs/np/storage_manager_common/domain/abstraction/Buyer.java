//package domain.abstraction;
package rs.np.storage_manager_common.domain.abstraction;

//import domain.DomainClass;
//import domain.WhereClauseMode;
import rs.np.storage_manager_common.domain.DomainClass;
import rs.np.storage_manager_common.domain.WhereClauseMode;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class Buyer implements DomainClass, Serializable, SecondParticipant {
    protected Integer ID;
    private String buyerAddress;
    private WhereClauseMode mode;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
    	if(ID == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(ID < 0 || ID > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
        this.ID = ID;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
    	if(buyerAddress == null || buyerAddress.isBlank()) {
    		throw new NullPointerException("Buyer address cannot be set to null or blank.");
    	}
    	if(buyerAddress.length() < 5 
    			|| buyerAddress.length() > 50) {
    		throw new IllegalArgumentException("Buyer address cannot be set to "
    				+ "a length less than 5 or greater than 50");
    	}
        this.buyerAddress = buyerAddress;
    }

    public WhereClauseMode getMode() {
        return mode;
    }

    public void setMode(WhereClauseMode mode) {
    	if(mode == null) {
    		throw new NullPointerException("Mode must be set with this method.");
    	}
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Buyer{" + "ID=" + ID + ", buyerAddress=" + buyerAddress + ", mode=" + mode + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.ID);
        hash = 79 * hash + Objects.hashCode(this.buyerAddress);
        hash = 79 * hash + Objects.hashCode(this.mode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Buyer other = (Buyer) obj;
        if (!Objects.equals(this.buyerAddress, other.buyerAddress)) {
            return false;
        }
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return this.mode == other.mode;
    }

    @Override
    public String getTableName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getColumnNames() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getColumnsWithoutID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
