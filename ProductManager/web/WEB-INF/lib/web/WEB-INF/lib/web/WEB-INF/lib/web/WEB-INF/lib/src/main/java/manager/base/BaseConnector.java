package manager.base;

import system.mongo.core.connector.MongoGatewayConnector;
import system.mongo.core.connector.MongoObjectConnector;

import java.util.HashMap;

public class BaseConnector {
    private final static String DATABASE_NAME = "Manager";
    private HashMap<String, MongoObjectConnector> mapConnector = new HashMap<String, MongoObjectConnector>();

    public MongoObjectConnector getMongoObjectConnector(String connectionName) {
        MongoObjectConnector mongoObjectConnector = mapConnector.get(connectionName);
        if (null == mongoObjectConnector) {
            mongoObjectConnector = MongoGatewayConnector.getMongoObjectConnector(DATABASE_NAME, connectionName);
            System.out.println("Connect " + connectionName + " success");
        }
        return mongoObjectConnector;
    }
}
