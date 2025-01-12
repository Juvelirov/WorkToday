package com.example.worktodayproject.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DropBoxService {

    DbxClientV2 dbxClientV2;

    public String uploadFile(MultipartFile file, String filename) throws IOException, DbxException {
        InputStream inputStream = new ByteArrayInputStream(file.getBytes());
        FileMetadata metadata = dbxClientV2.files().uploadBuilder("/" + filename)
                .withMode(WriteMode.ADD)
                .uploadAndFinish(inputStream);
        return generateShareableLink(metadata.getPathLower());
    }

    public void deleteFile(String path) throws DbxException {
        dbxClientV2.files().deleteV2(path);
    }

    private String generateShareableLink(String path) throws DbxException {
        SharedLinkMetadata sharedLinkMetadata = dbxClientV2.sharing()
                .createSharedLinkWithSettings(path);
        String url = sharedLinkMetadata.getUrl();
        return transformUrl(url);
    }

    public String generateFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        if (originalFileName != null && !originalFileName.isEmpty()) {
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            return  uuid + extension;
        } else {
            return  uuid + ".jpg";
        }
    }

    private String transformUrl(String url){
        return  url.replace("www.dropbox.com", "dl.dropboxusercontent.com")
                .replace("&dl=0", "&raw=1");
    }
}
