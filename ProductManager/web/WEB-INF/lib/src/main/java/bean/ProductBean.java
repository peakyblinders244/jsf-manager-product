package bean;

import manager.ICatalogConnector;
import manager.IProductConnector;
import manager.impl.CatalogConnector;
import manager.impl.ProductConnector;
import model.Catalog;
import model.Product;

import org.primefaces.model.file.UploadedFile;
import utils.DataFileUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "Product")
@SessionScoped
public class ProductBean extends BaseBean{
    private List<Product> productList;
    private IProductConnector productConnector;
    private ICatalogConnector catalogConnector;
    private String productId;
    private String productName;
    private UploadedFile productImage;
    private double productPrice;
    private String productCatalogId;
    private Product productSelected;
    private Map<String, Catalog> catalogMap;
    private final static String IMAGE_STORE="C:\\Users\\lhqua\\HongQuan\\Product-Manager\\jsf-product-manager\\ProductManager\\images";
    public List<Product> getProductList() {
        productList = productConnector.getProductList();
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public IProductConnector getProductConnector() {
        return productConnector;
    }

    public void setProductConnector(IProductConnector productConnector) {
        this.productConnector = productConnector;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UploadedFile getProductImage() {
        return productImage;
    }

    public void setProductImage(UploadedFile productImage) {
        this.productImage = productImage;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCatalogId() {
        return productCatalogId;
    }

    public void setProductCatalogId(String productCatalogId) {
        this.productCatalogId = productCatalogId;
    }

    public Product getProductSelected() {
        return productSelected;
    }

    public void setProductSelected(Product productSelected) {
        this.productSelected = productSelected;
    }

    @PostConstruct
    public void init(){

        productConnector = new ProductConnector();
        catalogConnector = new CatalogConnector();
        catalogMap = new HashMap<>();

        List<Catalog>catalogList = catalogConnector.getCatalogList();
        for (Catalog temp: catalogList) {
            catalogMap.put(temp.getId(),temp);
        }

    }

    public String getNameCatalogById(String catalogId){
        return catalogMap.get(catalogId).getName();
    }



    public void add(){
        try {
            Product model = new Product();
            model.setName(productName);
            model.setPrice(productPrice);
            model.setCatalogId(productCatalogId);
            File file = null;
            if(null == productImage){
                model.setImage("Default.jpg");
            }else{

                String imageName = productImage.getFileName();
                file = new File(IMAGE_STORE,imageName);
                try {
                    DataFileUtils.saveToDickImage(file,productImage.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(null != file){
                model.setImage(file.getName());
            }

            productConnector.save(model);
            addMessage("Add Product Successful!");
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
    }

    public void update(){
        try {
            File file = null;
            if(null == productImage){
                productSelected.setImage("Default.jpg");
            }else{
                String imageName = productImage.getFileName();
                file = new File(IMAGE_STORE,imageName);
                try {
                    DataFileUtils.saveToDickImage(file,productImage.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(file != null){
                productSelected.setImage(file.getName());
            }
            productConnector.update(productSelected);
            addMessage("Edit Product Successful!");
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
    }

    public void delete(String id){
        try{
            productConnector.delete(id);
            addMessage("Product deleted!");
        }catch (Exception ex){
            addError("Exception: " + ex.getMessage());
        }
    }
}
