package airline.utility;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

public class ReadProperties {

    @Autowired
    private Environment env;

    public String getProperty(String property){
        ResourceBundle resourceBundle =
                ResourceBundle.getBundle("flight.properties");
        return resourceBundle.getString(property);
    }
}
