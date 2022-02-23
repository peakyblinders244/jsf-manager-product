package factory;

import model.Catalog;
import model.Product;
import org.bson.Document;
import system.mongo.core.IDynamicObject;
import system.mongo.core.data.IMongoObjectFactory;
import system.mongo.core.data.MongoDynamicObject;

import java.util.UUID;

public class ProductFactory implements IMongoObjectFactory<Product> {

    @Override
    public Product createObject(Document document) {
        Product model = new Product();

        if(null!= document.get("id")){
            model.setId(document.get("id").toString());
        }
        if(null!=document.get("name")){
            model.setName(document.get("name").toString());
        }
        if(null!= document.get("image")){
            model.setImage(document.get("image").toString());
        }
        if(null!=document.get("price")){
            model.setPrice(Double.parseDouble(document.get("price").toString()));
        }
        if(null!=document.get("catalogId")){
            model.setCatalogId(document.get("catalogId").toString());
        }
        return model;
    }

    public IDynamicObject createObject(Product model){
        IDynamicObject iDynamicObject = null;
        if (null == model.getId() || model.getId().length() == 0) {
            iDynamicObject = new MongoDynamicObject(UUID.randomUUID().toString());
        } else {
            iDynamicObject = new MongoDynamicObject(model.getId());
        }
        iDynamicObject.put("name", model.getName());
        iDynamicObject.put("image",model.getImage());
        iDynamicObject.put("price",model.getPrice());
        iDynamicObject.put("catalogId",model.getCatalogId());
        return iDynamicObject;
    }
}
