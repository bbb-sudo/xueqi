package dao;
import java.util.List;

import model.*;
public interface AdministratorDAO {
    Administrator getAdministratorById(int id);
    boolean addAdministrator(Administrator admin);
    boolean deleteAdministrator(int id);
    
}
