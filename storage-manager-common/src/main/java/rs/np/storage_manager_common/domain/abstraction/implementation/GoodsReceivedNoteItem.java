//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocument;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class GoodsReceivedNoteItem extends AbstractDocumentItem implements DomainClass{
    private Partner partner;
    
    public GoodsReceivedNoteItem() {
    }

    public GoodsReceivedNoteItem(Integer ID, GoodsReceivedNote note,
            Firm firm, Partner patner, Integer amountAdded, Product product) {
        this.ID = ID;
        this.document = note;
        this.firm = firm;
        this.partner = patner;
        this.amount = amountAdded;
        this.product = product;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public AbstractDocument getDocument() {
        return document;
    }

    @Override
    public void setDocument(AbstractDocument note) {
        this.document = note;
    }

    @Override
    public Firm getFirm() {
        return firm;
    }

    @Override
    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public Partner getSecondParticipant() {
        return partner;
    }

    @Override
    public void setSecondParticipant(DomainClass partner) {
        this.partner = (Partner)partner;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public void setAmount(Integer amountAdded) {
        this.amount = amountAdded;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.ID);
        hash = 37 * hash + Objects.hashCode(this.document);
        hash = 37 * hash + Objects.hashCode(this.firm);
        hash = 37 * hash + Objects.hashCode(this.partner);
        hash = 37 * hash + Objects.hashCode(this.amount);
        hash = 37 * hash + Objects.hashCode(this.product);
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
        final GoodsReceivedNoteItem other = (GoodsReceivedNoteItem) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.document, other.document)) {
            return false;
        }
        if (!Objects.equals(this.firm, other.firm)) {
            return false;
        }
        if (!Objects.equals(this.partner, other.partner)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return Objects.equals(this.product, other.product);
    }

    @Override
    public String toString() {
        return "GoodsReceivedNoteItem{" + "ID=" + ID + ", note=" + document + ", firm=" + firm + ", patner=" + partner + ", amountAdded=" + amount + ", product=" + product + '}';
    }

    @Override
    public String getTableName() {
        return "stavkaprijemnice";
    }

    @Override
    public String getColumnNames() {
        return "(IDStavkePrij, IDPrijemnice, IDFirme, IDPartnera, PrimljenaKolP, sifraArtikla)";
    }

    @Override
    public String getValues() {
        return "(IDStavkePrij = " + ID + ", IDPrijemnice = " + 
                (document == null ? "NULL" : document.getID()) + ", IDFirme = " +
                (firm == null ? "NULL" : firm.getID()) + ", IDPartnera = " +
                (partner == null ? "NULL" : partner.getID()) + ", PrimljenaKolP = " +
                amount + ", sifraArtikla = " + 
                (product == null ? "NULL" : product.getID()) + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(IDStavkePrij=" + ID + ")" ;
        return "true";
    }

    @Override
    public String getInsertValues() {
        return "(" + (document == null? "NULL" : document.getID()) + ", " + (firm == null? "NULL" : firm.getID()) +
                ", " + (partner == null? "NULL" : partner.getID()) + ", " + amount + ", " + 
                (product == null? "NULL" : product.getID()) + ")";
    }
    /*
    private Integer ID;
    private GoodsReceivedNote note;
    private Firm firm;
    private Partner partner;
    private Integer amount;
    private Product product;
    */

    @Override
    public String getColumnsWithoutID() {
        return "(IDPrijemnice, IDFirme, IDPartnera, PrimljenaKolP, sifraArtikla)";
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int alterStock(int stock) {
        if(product == null){
            return 0;
        }
        else {
            return product.getAmount() + stock;
        }
    }
}
