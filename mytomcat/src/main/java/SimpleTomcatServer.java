

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class SimpleTomcatServer {
	static final int port = 8080;
	private static final String CONTEXT_PATH = "/hello";
	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        
        Context context = tomcat.addWebapp(CONTEXT_PATH, getAbsolutePath() + "src/main/webapp"); 
        
        tomcat.addServlet(CONTEXT_PATH, "loginServlet", new LoginServlet());
        context.addServletMappingDecoded("/login", "loginServlet");
        
        tomcat.start(); 
        tomcat.getServer().await();
	}
	
	private static String getAbsolutePath() {
        String path = null;
        String folderPath = SimpleTomcatServer.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                .substring(1);
        if (folderPath.indexOf("target") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("target"));
        }
        return path;
    }
}
