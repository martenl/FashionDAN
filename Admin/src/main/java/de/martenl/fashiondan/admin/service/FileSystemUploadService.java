package de.martenl.fashiondan.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Executor;

public class FileSystemUploadService implements UploadService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileSystemUploadService.class);

    private final String directoryPath;
    private final Executor executor;

    public FileSystemUploadService(final String directoryPath, final Executor executor) {
        this.directoryPath = directoryPath;
        this.executor = executor;
    }

    @Override
    public void storeUploadedFile(final Part uploadedFile) {
        executor.execute(() -> {
            final String filePath = Path.of(directoryPath, uploadedFile.getSubmittedFileName()).toString();
            try {
                uploadedFile.write(filePath);
                uploadedFile.delete();
            } catch (IOException e) {
                LOGGER.error("Error while writing uploaded file to disk", e);
            }
        });
    }
}
