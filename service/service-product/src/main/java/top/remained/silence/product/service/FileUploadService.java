package top.remained.silence.product.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    //图片上传的方法 oos
    String uploadFile(MultipartFile file);
    //图片上传的方法 oos
    String uploadFile1(MultipartFile file);
}
