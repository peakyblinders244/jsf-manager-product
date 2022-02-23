package bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class BaseBean {
    public void addValidateError(String errorMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                errorMessage, null));
    }

    public void addError(String errorMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                errorMessage, null));
    }

    public void addMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                message, null));
    }
}
