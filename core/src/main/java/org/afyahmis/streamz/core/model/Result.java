package org.afyahmis.streamz.core.model;

import java.time.LocalDateTime;

public class Result {

    int id;
    String clientId;
    String name;
    String test;
    String result;
    LocalDateTime resultDate;

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

    public LocalDateTime getResultDate() {
        return resultDate;
    }

    public void setResultDate(LocalDateTime resultDate) {
        this.resultDate = resultDate;
    }
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Result() {
    }

    public Result(int id, String clientId, String name,String test,  String result, LocalDateTime resultDate) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.test=test;
        this.result = result;
        this.resultDate = resultDate;
    }

    public static Result Create(String printOut) {
        String[] cols = printOut.split("\\s+");
        return new Result(
                Integer.parseInt(cols[0]),
                cols[1],
                cols[2],
                cols[3],
                cols[4],
                LocalDateTime.parse(cols[5]));
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",name,result,resultDate);
    }

    public String getPrintOut(){
        return String.format("%s|%s|%s|%s",name,result,resultDate,LocalDateTime.now());
    }

}