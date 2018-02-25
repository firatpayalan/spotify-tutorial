package com.firat.questiontwo;

public abstract class ConfigurationMap<S,T> {

    public S source;
    public T target;

    private Class<T> targetType;
    private Object oSource;

    public ConfigurationMap(Class<T> target,Object source) throws IllegalAccessException, InstantiationException {
        this.targetType = target;
        this.oSource = source;
        this.target = this.targetType.newInstance();
    }

    public ConfigurationMap(){}

    public abstract void configure(S source);

    public Class<T> getTargetType() {
        return targetType;
    }

    public void setTargetType(Class<T> targetType) {
        this.targetType = targetType;
    }
}
