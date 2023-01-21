package com.hextrix.bindatafilesmongodb;

import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public File saveFile (MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "File is empty"
            );
        }

        File file = new File(
            null,
            multipartFile.getOriginalFilename(),
            multipartFile.getContentType(),
            new Binary(
                BsonBinarySubType.USER_DEFINED,
                multipartFile.getBytes()
            )
        );

        fileRepository.save(file);

        file.setData(null);

        return file;
    }

    public File getFile(String id) {
        return fileRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public File getFileMetadata (String id) {
        File file = getFile(id);
        file.setData(null);
        return file;
    }
}
