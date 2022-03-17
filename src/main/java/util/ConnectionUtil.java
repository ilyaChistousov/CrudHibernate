package util;


import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@UtilityClass
public class ConnectionUtil {

    public static Session getSession() {
        SessionFactory factory = ConfigurationUtil.getConfiguration().buildSessionFactory();
        return factory.openSession();
    }
}
