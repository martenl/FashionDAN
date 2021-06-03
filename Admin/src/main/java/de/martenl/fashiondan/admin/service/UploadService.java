package de.martenl.fashiondan.admin.service;

import javax.servlet.http.Part;

public interface UploadService {

    void storeUploadedFile(Part uploadedPart);
}
