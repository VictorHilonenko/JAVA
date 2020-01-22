package spring.scheduler.entity;

import java.util.Optional;

public enum ServiceType {
	HAIRDRESSING,
	MAKEUP,
	COSMETOLOGY;
	
    public static ServiceType lookup(String name) {
        try {
        	return ServiceType.valueOf(Optional.ofNullable(name).orElse(""));
        } catch (IllegalArgumentException e) {
            return null; //yes, it's normal, because only for Masters service type will be not null
        }
    }
}