//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.Buyer;
import rs.np.storage_manager_common.domain.utility.DateParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class LegalPerson extends Buyer {
    private Buyer buyer;
    private String firmName;
    private Date foundingDate;

    public LegalPerson() {
    }

    public LegalPerson(Integer ID, Buyer buyer, String firmName, Date foundingDate) {
        this.ID = ID;
        this.buyer = buyer;
        this.firmName = firmName;
        this.foundingDate = foundingDate;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
    	if(buyer == null) {
    		throw new NullPointerException("Buyer must not be null.");
    	}
        this.buyer = buyer;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
    	if(firmName == null || firmName.isBlank()) {
    		throw new NullPointerException("Firm name must not be null.");
    	}
    	if(firmName.length() < 2 || firmName.length() > 30) {
    		throw new IllegalArgumentException("Firm name must not be "
    				+ "less than 2 characters or longer than 30 characters.");
    	}
        this.firmName = firmName;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
    	if(foundingDate == null) {
    		throw new NullPointerException("Founding date for this firm must not be set to null.");
    	}
    	if(foundingDate.after(new Date())) {
    		throw new DateTimeException("Founding date must not be in the future.");
    	}
        this.foundingDate = foundingDate;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
    	if(ID == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(ID < 0 || ID > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
        this.ID = ID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.buyer);
        hash = 53 * hash + Objects.hashCode(this.firmName);
        hash = 53 * hash + Objects.hashCode(this.foundingDate);
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
        final LegalPerson other = (LegalPerson) obj;
        if (!Objects.equals(this.firmName, other.firmName)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        return Objects.equals(this.foundingDate, other.foundingDate);
    }

    @Override
    public String toString() {
        return firmName;
    }

    @Override
    public String getTableName() {
        return "pravnoLice";
    }

    @Override
    public String getColumnNames() {
        return "(IDKupca, nazivFirme, datumOsnivanja)";
    }

    @Override
    public String getValues() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String foundingDateFormat = (foundingDate == null? "NULL" : format.format(foundingDate));
        return "(IDKupca = " + ID + ", nazivFirme = " + firmName 
                + ", datumOsnivanja = " + foundingDateFormat + ");" ;
    }

    @Override
    public String getInsertValues() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String foundingDateFormat = (foundingDate == null? "NULL" : format.format(foundingDate));
        return "(" + firmName + ", " + foundingDateFormat + ");";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID){
            return "(IDKupca = " + ID + ");";
        }
        return "true";
    }

    @Override
    public String getColumnsWithoutID() {
        return "(nazivFirme, datumOsnivanja)";
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Buyer buyer = new Buyer();
        buyer.setID(rs.getInt("IDKupca"));
        return new LegalPerson(
                rs.getInt("IDKupca"),
                buyer,
                rs.getString("nazivFirme"),
                DateParser.sqlDateToUtilDate(
                        rs.getDate("datumOsnivanja"))
        );
    }
    
    
}
