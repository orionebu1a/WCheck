package WCheck.controllers;

import WCheck.converter.EntityDtoConverter;
import WCheck.dtos.BorderDTO;
import WCheck.dtos.LocationDTO;
import WCheck.dtos.PhotoDTO;
import WCheck.entities.Feedback;
import WCheck.entities.Location;
import WCheck.entities.Photo;
import WCheck.services.FeedbackService;
import WCheck.services.LocationService;
import WCheck.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api")
public class PhotoController {

    private final PhotoService photoService;
    private final LocationService locationService;

    @Autowired
    public PhotoController(PhotoService photoService, LocationService locationService) {
        this.photoService = photoService;
        this.locationService = locationService;
    }

    // Получение записи по ID
    @GetMapping("/photo/get/{id}")
    public ResponseEntity<PhotoDTO> getPhotoById(@PathVariable Long id) {
        Photo photo = photoService.getPhoto(id);
        return ResponseEntity.ok(EntityDtoConverter.convertToDto(photo, PhotoDTO.class));
    }

//    // Создание новой записи
//    @PostMapping("/photo/post")
//    public ResponseEntity<PhotoDTO> createPhoto(@RequestBody PhotoDTO photoDTO) {
//        Photo photo = EntityDtoConverter.convertToEntity(photoDTO, Photo.class);
//        Photo createdPhoto = photoService.createPhoto(photo);
//        return new ResponseEntity<>(EntityDtoConverter.convertToDto(createdPhoto, PhotoDTO.class), HttpStatus.CREATED);
//    }

    @PostMapping("/photo/add_to_location_{id}")
    public ResponseEntity<PhotoDTO> addPhotoToLocation(@RequestBody PhotoDTO photoDTO, @PathVariable Long locationId) {
        Photo photo = EntityDtoConverter.convertToEntity(photoDTO, Photo.class);
        Location location = locationService.getLocation(locationId);
        if(location == null){
            return new ResponseEntity<>(EntityDtoConverter.convertToDto(photo, PhotoDTO.class), HttpStatus.NOT_FOUND);
        }
        location.getPhotos().add(photo);
        locationService.saveLocation(location);
        return new ResponseEntity<>(EntityDtoConverter.convertToDto(photo, PhotoDTO.class), HttpStatus.CREATED);
    }

}

