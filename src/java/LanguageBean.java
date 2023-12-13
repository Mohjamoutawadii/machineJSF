import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class LanguageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String currentLanguage;

    @PostConstruct
    public void init() {
        // Initialize the language to the default language
        currentLanguage = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().getLanguage();
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String language) {
        // Set the current language and update the view locale
        currentLanguage = language;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }

    public void preRenderView(ComponentSystemEvent event) {
    if (!FacesContext.getCurrentInstance().isPostback()) {
        init();
        System.out.println("preRenderView method called. Current language: " + currentLanguage);
    }
}

}
