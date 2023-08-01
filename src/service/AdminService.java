package service;

import dao.AdminDao;
import model.AdminModel;

public class AdminService {
    private AdminDao adminDao = new AdminDao();

    public boolean authenticateAdmin(String name, String password) throws ClassNotFoundException {
        AdminModel admin = adminDao.getAdminByUsername(name);
        if (admin != null && admin.getPassword().equals(password)) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }
}
