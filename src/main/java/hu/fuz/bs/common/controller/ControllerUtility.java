package hu.fuz.bs.common.controller;

import hu.fuz.bs.common.exceptions.BSEntityNotFoundException;

import java.util.Optional;

public class ControllerUtility {
    public static <T> T getOrNotFound(Optional<T> entity,String errorMessage){
        if(entity.isPresent()){
            return entity.get();
        }else{
            throw new BSEntityNotFoundException(errorMessage);
        }
    }
}
