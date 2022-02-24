package factory;

import model.Catalog;
import net.lvs.mongo.connector.model.Person;
import org.bson.Document;
import system.mongo.core.IDynamicObject;
import system.mongo.core.data.IMongoObjectFactory;
import system.mongo.core.data.MongoDynamicObject;

import java.util.UUID;

public class CatalogFactory implements IMongoObjectFactory<Catalog> {
    public Catalog createObject(Document document) {
        Catalog model = new Catalog();
        if(null!= document.get("id")){
            model.setId(document.get("id").toString());
        }
        if(null!=document.get("name")){
            model.setName(document.get("name").toString());
        }
        return model;
    }

    public IDynamicObject createObject(Catalog model){
        IDynamicObject iDynamicObject = null;
        if (null == model.getId() || model.getId().length() == 0) {
            iDynamicObject = new MongoDynamicObject(UUID.randomUUID().toString());
        } else {
            iDynamicObject = new MongoDynamicObject(model.getId());
        }
        iDynamicObject.put("name", model.getName());

        return iDynamicObject;
    }
}
