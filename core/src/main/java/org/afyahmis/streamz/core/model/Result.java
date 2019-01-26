package org.afyahmis.streamz.core.model;

public class Result {

    int id;
    String clientId;
    String name;
    String result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Result() {
    }

    public Result(int id, String clientId, String name, String result) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.result = result;
    }
}


