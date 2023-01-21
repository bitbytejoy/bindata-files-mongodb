package com.hextrix.bindatafilesmongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping
    public File uploadFile(
        @RequestParam("file") MultipartFile multipartFile
    ) throws IOException {
        return fileService.saveFile(multipartFile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> getFile (
        @PathVariable String id
    ) {
        File file = fileService.getFile(id);

        return ResponseEntity.ok()
            .contentType(MediaType.valueOf(file.getType()))
            .body(new InputStreamResource(
                new ByteArrayInputStream(file.getData().getData())
            ));
    }

    @GetMapping("/{id}/metadata")
    public File getFileMetadata (@PathVariable String id) {
        return fileService.getFileMetadata(id);
    }
}
