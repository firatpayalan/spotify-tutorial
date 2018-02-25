package com.firat.questiontwo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class MyMapper {

    private ConfigurationMap configurationMap;

    public <T> T read(Object source,Class<T> target) throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        T t = target.newInstance(); //create a new target object.
        Map<String,Field> targetAttributes = listOfAttributes(target);
        List<Field> sourceAttributes =  Arrays.asList(source.getClass().getDeclaredFields());
        //iterator
        Iterator<Field> fieldIterator = sourceAttributes.iterator();
        while (fieldIterator.hasNext())
        {
            Field current = fieldIterator.next();
            current.setAccessible(true);

            if (targetAttributes.containsKey(current.getName()))
            {
                Object value = current.get(source);
                Field valueToBeWritten = t.getClass().getDeclaredField(current.getName());
                valueToBeWritten.setAccessible(true);
                valueToBeWritten.set(t,value);
            }
        }
        return t;
    }
    public <T> T read(Object source,Class<T> target,ConfigurationMap map) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        map.configure(source);
        Object t = map.target;
//        T t = target.newInstance(); //create a new target object.
        Map<String,Field> targetAttributes = listOfAttributes(t.getClass());
        List<Field> sourceAttributes =  Arrays.asList(source.getClass().getDeclaredFields());
        //iterator
        Iterator<Field> fieldIterator = sourceAttributes.iterator();
        while (fieldIterator.hasNext())
        {
            Field current = fieldIterator.next();
            current.setAccessible(true);

            if (targetAttributes.containsKey(current.getName()))
            {
                Object value = current.get(source);
                Field valueToBeWritten = t.getClass().getDeclaredField(current.getName());
                valueToBeWritten.setAccessible(true);
                valueToBeWritten.set(t,value);
            }
        }
        return target.cast(t);
    }
    public MyMapper()
    {
    }

    private Map<String,Field> listOfAttributes(Class<?> input)
    {
        Field[] arrayOfFields = input.getDeclaredFields();
        return asList(arrayOfFields).stream().collect(Collectors.toMap(Field::getName, Function.identity()));
    }

}
