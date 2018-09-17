package com.demo.upload;

import com.demo.test.JsonReader;
import com.sun.deploy.net.HttpRequest;
import com.sun.net.httpserver.HttpContext;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/upload/file")
public class UploadFile extends HttpServlet {

    // 上传文件存储目录
    public static final String UPLOAD_DIRECTORY = "images";
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(req)) {
            // 如果不是则停止
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 针对post请求，设置允许接收中文
        req.setCharacterEncoding("utf-8");


        /* 允许跨域的主机地址 */
        resp.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        resp.setContentType("text/html;charset=utf-8");

        System.out.println("添加任务");
        PrintWriter out = resp.getWriter();


        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        //String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        String contextPath = req.getContextPath();
        System.out.println("contextPath:"+contextPath);;

        String uploadPath = getServletContext().getRealPath("/")+ File.separator + UPLOAD_DIRECTORY;
        System.out.println("上传路径:"+uploadPath);

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    System.out.println("表单数据:"+item);
                    //拿到文件
                    String fileName = new File(item.getName()).getName();
                    System.out.println("文件名："+fileName);
                    // 文件的上传路径
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println("上传路径"+filePath);
                    // 保存文件到硬盘
                    item.write(storeFile);

                    req.setAttribute("message", "文件上传成功!");
                }

            }
        } catch (Exception ex) {
            req.setAttribute("message", "错误信息: " + ex.getMessage());
        }


        // 输出数据
        JSONObject object = new JSONObject();
        object.put("id",200);
        object.put("message","接收数据成功！！");
        out.write(object.toString());
        out.flush();
//        out.close();
    }

}
