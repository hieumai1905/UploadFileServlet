package com.example.uploadfile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "SingleFile", value = "/single-file")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 20, // 20MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class SingleFile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Thư mục để lưu trữ các file được upload lên server
    private static final String UPLOAD_DIRECTORY = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy file upload lên từ request
        Part part = request.getPart("file");


        if (part != null) {
            // Chỉ định đường dẫn lưu file trên server
            String uploadPath = "H:\\LanguageProgram\\Java\\Servlet\\UploadFile\\src\\main\\resources\\" + UPLOAD_DIRECTORY;

            // Tạo thư mục để lưu trữ các file được upload lên server (nếu chưa có)
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Lấy tên file
            String fileName = part.getSubmittedFileName();

            if (fileName != null) {
                // Đường dẫn lưu file
                String filePath = uploadPath + File.separator + fileName;
                System.out.println("Write attachment to file: " + filePath);

                part.write(filePath);

                // Gửi thông báo
                request.setAttribute("message", "Upload file thành công!");
            }
        }

        // Chuyển hướng về trang JSP để hiển thị thông báo upload file
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}