package com.group5.ecommerce.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CloudinaryUtils {

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        File file = this.convertFile(multipartFile);

        String uploadedImage = this.cloudinary
                .uploader()
                .unsignedUpload(
                        file,
                        "ecommerce_cloudinary",
                        ObjectUtils.emptyMap()
                )
                .get("secure_url")
                .toString();
        file.delete();

        return uploadedImage;
    }

    private File convertFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
