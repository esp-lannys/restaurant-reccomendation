package spm.project.restaurantrecommendation.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Long id){
        super("Could not find id: "+ id);
    }
}
