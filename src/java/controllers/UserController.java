package controllers;

import entities.User;
import controllers.util.JsfUtil;
import controllers.util.JsfUtil.PersistAction;
import services.UserFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private services.UserFacade ejbFacade;
    private String userName;
    private String password;
    private String newuserName;
    private String newpassword;

    public UserController() {
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewuserName() {
        return newuserName;
    }

    public void setNewuserName(String newuserName) {
        this.newuserName = newuserName;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    
    

    public void signup() {
        User newUser = new User(newuserName, newpassword);
        getFacade().create(newUser);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        
       
    }
    public void check(){
        List<User> users = getFacade().findAll();
    for (User user : users) {
        if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
           
            redirectToPage("/employe/List");
            return; 
        }
    }
    JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("UserNotFound"));
    }
    private void redirectToPage(String page) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
    navigationHandler.handleNavigation(facesContext, null, page + "?faces-redirect=true");
}

    public List<User> getItems() {
        return getFacade().findAll();
    }

    public User getUser(java.lang.Integer id) {
        return getFacade().find(id);
    }
}
