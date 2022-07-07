import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author anadh
 */
@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
@MultipartConfig
public class UploadFile extends HttpServlet { 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        HashMap<String, Integer> map = new HashMap<>();
        Part filePart = request.getPart("myfile");
        String fileName = filePart.getSubmittedFileName();
        InputStream is = filePart.getInputStream();
        try{
                if (is != null) {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);
                PrintWriter writer = response.getWriter();
                String line; String[] arrOfStr;
                while ((line = reader.readLine()) != null) {
                    arrOfStr = line.split(";");
                    if (map.containsKey(arrOfStr[2])) {
                        map.put(arrOfStr[2], map.get(arrOfStr[2])+1);
                    } else {
                        map.put(arrOfStr[2], 1);
                    }
                }
               writer.println("Filename: "+fileName+"<br/>Results: "+map);
            }
        }
        catch(IOException e){
            System.out.println("Exception: " + e.getMessage());
        }
    }

}
