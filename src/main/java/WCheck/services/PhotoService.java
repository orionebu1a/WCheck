package WCheck.services;

import WCheck.entities.Feedback;
import WCheck.entities.Photo;
import WCheck.entities.UserName;
import WCheck.repos.FeedbackRepository;
import WCheck.repos.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Photo getPhoto(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }

    @Transactional
    public Photo saveUserNAme(Photo photo) {
        return photoRepository.save(photo);
    }

    public List<Photo> getPhotos(List<Long> ids){
        return photoRepository.findAllById(ids);
    }
}
