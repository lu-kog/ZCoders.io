package com.user.profile;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.MultipartConfig;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.Part;

// import org.apache.log4j.Logger;
// import org.json.JSONObject;

// import com.user.UserDAO;

// import utils.CommonLogger;
// import utils.JSON;

// /**
//  * Servlet implementation class UploadProfilePic
//  */
// // @WebServlet("/UploadProfilePic")
// // @MultipartConfig
// public class UploadProfilePic extends HttpServlet {
// 	private static final long serialVersionUID = 1L;
       
//     /**
//      * @see HttpServlet#HttpServlet()
//      */
//     public UploadProfilePic() {
//         super();
//         // TODO Auto-generated constructor stub
//     }

// 	/**
// 	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
// 	 */
// 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 		// TODO Auto-generated method stub
// 		response.getWriter().append("Served at: ").append(request.getContextPath());
// 	}

// 	/**
// 	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
// 	 */
// 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 		Logger logger = new CommonLogger(UploadProfilePic.class).getLogger();
// 		String mailID = "";
// 		try {
			
// 			mailID = request.getParameter("mailId"); 

// 			String userName = UserDAO.getObj().getUserNameFromMailID(mailID);

// 	        Part filePart = request.getPart("file");

// 	        String folderpath = "src/main/..";
// 	        String fileName = userName+".jpg";
	        
// 	        File profileFolder = new File(folderpath);
	        
// 	        System.out.println(profileFolder.exists());

// 	        // Writes uploaded file to the directory
// 	        FileOutputStream out = new FileOutputStream(new File(folderpath, fileName));
// 	        try {
// 	            byte[] buffer = new byte[1024];
// 	            int bytesRead;
// 	            while ((bytesRead = filePart.getInputStream().read(buffer)) != -1) {
// 	                out.write(buffer, 0, bytesRead);
// 	            }
// 	        } finally {
// 	            out.close();
// 	        }
	        
	        
// 	        logger.info("Profile pic uploaded successfully:"+fileName);
	        
// 	        JSONObject respJson = JSON.Create(200, "Changed successfully!");
// 	        response.getWriter().write(respJson.toString());
			
// 		} catch (Exception e) {
// 			logger.error("Error on uploading profile pic for:"+mailID+" error:"+e);
// 			JSONObject errJson = JSON.Create(400, "Can't change profile.. Please contact admin!");
// 			response.getWriter().write(errJson.toString());
// 		}
// 	}
	
// }
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/v1/UploadProfilePic")
@MultipartConfig
public class UploadProfilePic extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("drfhugjv");
            Part filePart = request.getPart("img");
            String username = request.getParameter("username");
            InputStream inputStream = filePart.getInputStream();
            
            // Specify the file path where you want to store the image on your server
            String filePath = "/home/workspace/Coders.io/webapps/Demo/images/"+username+".png";
            
            File file = new File(filePath);
            file.createNewFile();
            
            // Write the image content to the file
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            
            System.out.println("Image stored successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred: " + e.getMessage());
        }
    }
}
