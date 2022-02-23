package manager;

import model.Catalog;
import model.Product;

import java.util.List;

public interface IProductConnector {
    void save(Product product);

    Product getProduct(String id);

    List<Product> getProductList();

    List<Product> searchProductByName(String value);

    void delete(String id);

    void update(Product product);
}
