package edu.usm.healthsystem.storage;

public class MethodsOfChoice {
    private int id;
    private String methodName;

    public MethodsOfChoice() {}

    public MethodsOfChoice(String methodName) {
        this.methodName = methodName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMethodName() { return methodName; }
    public void setMethodName(String methodName) { this.methodName = methodName; }
}