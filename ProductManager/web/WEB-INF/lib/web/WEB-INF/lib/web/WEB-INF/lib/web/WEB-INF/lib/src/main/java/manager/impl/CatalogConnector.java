package manager.impl;

import factory.CatalogFactory;
import manager.ICatalogConnector;

import manager.base.BaseConnector;
import model.Catalog;

import system.mongo.core.IDynamicObject;
import system.mongo.core.connector.MongoObjectConnector;


import java.util.List;

public class CatalogConnector extends BaseConnector implements ICatalogConnector {

    private MongoObjectConnector getMongoObjectConnectorCatalog(){


        MongoObjectConnector tmp = getMongoObjectConnector("Catalog");

        return getMongoObjectConnector("Catalog");
    }

    @Override
    public void save(Catalog catalog) {
        CatalogFactory factory = new CatalogFactory();
        IDynamicObject iDynamicObject =factory.createObject(catalog);
        getMongoObjectConnectorCatalog().insert(iDynamicObject);
    }

    @Override
    public Catalog getCatalog(String id) {

        return (Catalog) getMongoObjectConnectorCatalog().get(id,new CatalogFactory());
    }

    @Override
    public List getCatalogList() {

        return getMongoObjectConnectorCatalog().listAll(new CatalogFactory());
    }

    @Override
    public List<Catalog> searchCatalogByName(String searchValue) {
//        String propertyName = "name";
//        return getMongoObjectConnectorCatalog().getObjectMatchProperty(propertyName, searchValue);
        return null;
    }

    @Override
    public void delete(String id) {
        getMongoObjectConnectorCatalog().removeDocument(id);
    }

    @Override
    public void update(Catalog category) {
        CatalogFactory factory = new CatalogFactory();
        IDynamicObject iDynamicObject = factory.createObject(category);
        getMongoObjectConnectorCatalog().update(iDynamicObject);
    }
}
