package hh5.twogaether.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final ImageRepository imageRepository;

    public void saveImage(Image image) {
        imageRepository.save(image);
    }
}
