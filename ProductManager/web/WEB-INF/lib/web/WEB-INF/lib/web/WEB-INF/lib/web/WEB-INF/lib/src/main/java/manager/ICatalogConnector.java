package manager;

import model.Catalog;

import java.util.List;

public interface ICatalogConnector {
    void save(Catalog catalog);

    Catalog getCatalog(String id);

    List<Catalog> getCatalogList();

    List<Catalog> searchCatalogByName(String searchValue);

    void delete(String id);

    void update(Catalog category);
}
