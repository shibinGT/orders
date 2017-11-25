package listenter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class Listenter2
 *
 */
@WebListener
public class Listenter2 implements HttpSessionListener{
	private static Integer number=0;
	
    public static Integer getNumber() {
		return number;
	}

	/**
     * Default constructor. 
     */
    public Listenter2() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
        number++;
       
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	 number--;
    	
    }
	
}
