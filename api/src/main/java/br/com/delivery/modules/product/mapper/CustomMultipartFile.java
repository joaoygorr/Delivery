package br.com.delivery.modules.product.mapper;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class CustomMultipartFile implements MultipartFile {

    private final byte[] file;

    public CustomMultipartFile(byte[] file) {
        this.file = file;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getOriginalFilename() {
        return "";
    }

    @Override
    public String getContentType() {
        return "";
    }

    @Override
    public boolean isEmpty() {
        return file == null || file.length == 0;
    }

    @Override
    public long getSize() {
        return file.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(file);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        try(FileOutputStream fos = new FileOutputStream(dest)) {
            fos.write(file);
        }
    }
}
