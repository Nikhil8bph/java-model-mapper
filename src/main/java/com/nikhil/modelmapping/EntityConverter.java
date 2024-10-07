package com.nikhil.modelmapping;

import com.nikhil.modelmapping.example.entity.source.SourceAddress;
import com.nikhil.modelmapping.example.entity.source.SourceEntity;
import com.nikhil.modelmapping.example.entity.target.TargetEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityConverter {

    public static <T, U> U convertEntity(T source, Class<U> targetClass) throws Exception {
        U target = targetClass.getDeclaredConstructor().newInstance();

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = targetClass.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            Object sourceValue = sourceField.get(source);

            for (Field targetField : targetFields) {
                targetField.setAccessible(true);

                if (sourceField.getName().equals(targetField.getName())) {
                    // Handle primitive and wrapper types directly
                    if (isPrimitiveOrWrapperOrString(sourceField.getType()) &&
                            sourceField.getType().equals(targetField.getType())) {
                        targetField.set(target, sourceValue);
                    }
                    // Recursively convert if the field is not a primitive and is a different type
                    else if (sourceValue != null && !isPrimitiveOrWrapperOrString(sourceField.getType())) {
                        // Recursively convert nested objects even if they are of different types
                        Object nestedTarget = convertEntity(sourceValue, targetField.getType());
                        targetField.set(target, nestedTarget);
                    }
                    break;
                }
            }
        }

        return target;
    }

    // Utility method to check if the type is a primitive, wrapper, or String
    private static boolean isPrimitiveOrWrapperOrString(Class<?> type) {
        return type.isPrimitive() ||
                type == Integer.class || type == Long.class || type == Double.class || type == Boolean.class ||
                type == Float.class || type == Short.class || type == Byte.class || type == Character.class ||
                type == String.class;
    }

    public static <T, U> List<U> convertList(List<T> sourceList, Class<U> targetClass) throws Exception {
        List<U> targetList = new ArrayList<>();
        for (T source : sourceList) {
            targetList.add(convertEntity(source, targetClass));
        }
        return targetList;
    }

    public static void main(String[] args) {
        try {
            // Example usage
            SourceAddress sourceAddress = new SourceAddress("mother earth");
            SourceEntity source = new SourceEntity(1, "Nikhil Sharma", "Nikhil@example.com", sourceAddress);

            TargetEntity target = convertEntity(source, TargetEntity.class);

            System.out.println("Source Entity: " + source);
            System.out.println("Target Entity: " + target);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

