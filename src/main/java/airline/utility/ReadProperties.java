package airline.utility;
import java.io.FileInputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ReadProperties {

    public String getProperty(String property){
        String propertyValue = "";
    try {
        FileInputStream file = new FileInputStream("src\\main\\resources\\Property Files\\flight.properties");
        ResourceBundle resourceBundle = new PropertyResourceBundle(file);
        propertyValue = resourceBundle.getString(property);
    }catch (Exception e){
        e.printStackTrace();
    }
    return propertyValue;
    }
}
