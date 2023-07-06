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
import java.util.List;

@WebServlet(name = "MultipleFile", value = "/multiple-file")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 20, // 20MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class MultipleFile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Thư mục để lưu trữ các file được upload lên server
    private static final String UPLOAD_DIRECTORY = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy danh sách các file được upload lên server
        List<Part> parts = (List<Part>) request.getParts();

        // Lấy đường dẫn thực tế trên server
//        String uploadPath = getServletContext().getRealPath("")
//                + File.separator + UPLOAD_DIRECTORY;

        // Chỉ định đường dẫn lưu file trên server
        String uploadPath = "H:\\LanguageProgram\\Java\\Servlet\\UploadFile\\src\\main\\resources\\" + UPLOAD_DIRECTORY;

        // Tạo thư mục để lưu trữ các file được upload lên server (nếu chưa có)
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        for (Part part : parts) {
            // Lấy tên file
            String fileName = part.getSubmittedFileName();

            // kiem tra neu la file thi moi luu
            if (fileName != null) {

                // Đặt đường dẫn để lưu trữ file trên server
                String filePath = uploadPath + File.separator + fileName;
                System.out.println("Write attachment to file: " + filePath);

                // Thực hiện ghi file vào thư mục trên server
                part.write(filePath);
            }

            // Ghi thông báo thành công vào request
            request.setAttribute("message", "Upload file thành công!");
        }

        // Chuyển hướng về trang JSP để hiển thị thông báo upload file
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}