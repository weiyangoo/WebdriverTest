
import java.io.*;
//import java.util.*;

public class HandleCsv{ 
	    	  
	public String[] readCsv(int n) throws IOException {
        @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(
    		             new InputStreamReader(
    		              new FileInputStream("D:\\javawork\\info.csv")
    		             )
    		            );
        String line;
        String[] user = new String[109];
        String[] email = new String[109];
        String[] password = new String[109];
        int i=0;
        while ((line = br.readLine()) != null ) {
           //System.out.println(line); 
    	    String[] info = line.split(",");
            user[i] = info[0];
            email[i] = info[1];          
            password[i] = user[i].substring(4,10);
           // System.out.print(user[i] + "\t" + password[i] + "\t" + email[i] + "\n"); 
            i=i+1;
            if (i == 109) {
            	break;
            }
        }
     
	    if (n == 1){
		    return user;
	    }
	    else if(n == 2){
		    return email;
	    }
	    else if(n == 3){
		    return password;
	    }
	    else
	        return null;   
     }
}
