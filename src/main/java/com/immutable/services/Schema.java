package com.immutable.services;

import java.util.Map;

public class Schema {
    private  long clientId;
    private Map<String, Object> schemaMap;
    private  String schema;

    public long getClientId() {
        return clientId;
    }

    public Map<String, Object> getSchemaMap() {
        return schemaMap;
    }

    public String getSchema() {
        return schema;
    }

    public static  class Builder {
          long clientId;
          Map<String, Object> schemaMap;
          String schema;

          Builder(long _clientId,String _schema){
              this.clientId = _clientId;
              this.schema = _schema;
          }
    }
}
