package database;

public class ProceduresDB {
    public static final String GET_DOGS = "CALL sp_get_dogs(?);";
    public static final String FIND_DOG = "CALL sp_find_dog(?);"; 
    public static final String ADD_DOG = "CALL sp_add_dog(?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_DOG = "CALL sp_update_dog(?, ?, ?, ?, ?, ?, ?);";
    public static final String CARRY_DOG = "CALL sp_carry_dog(?);";    
}
