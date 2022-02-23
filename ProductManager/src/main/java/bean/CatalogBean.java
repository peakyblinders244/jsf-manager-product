package bean;

import manager.*;
import manager.impl.CatalogConnector;
import model.Catalog;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "Catalog")
@SessionScoped
public class CatalogBean extends BaseBean {
    private List<Catalog> catalogList;
    private ICatalogConnector catalogConnector;
    private String catalogId;
    private String catalogName;
    private Catalog selectedCatalog;

    public List<Catalog> getCatalogList() {
        catalogList = catalogConnector.getCatalogList();
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    public ICatalogConnector getCatalogConnector() {
        return catalogConnector;
    }

    public void setCatalogConnector(ICatalogConnector catalogConnector) {
        this.catalogConnector = catalogConnector;
    }

    public String getCatalogId() {
        //catalogList=catalogConnector.getCatalogList();
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Catalog getSelectedCategory() {
        return selectedCatalog;
    }

    public void setSelectedCategory(Catalog selectedCategory) {
        this.selectedCatalog = selectedCategory;
    }

    @PostConstruct
    public void init(){

        catalogConnector = new CatalogConnector();
    }

    public void add() {
        try {
            Catalog model = new Catalog();
            model.setName(catalogName);
            catalogConnector.save(model);
            addMessage("Add Catalog Success");
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
    }

    public void update(){
        try {
            catalogConnector.update(selectedCatalog);
            addMessage("Edit Catalog Success");
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
    }

    public void delete(String id) {
        try {
            catalogConnector.delete(id);
            addMessage("Record deleted!");
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
    }
}
