package WCheck.controllers;

import WCheck.converter.EntityDtoConverter;
import WCheck.dtos.BorderDTO;
import WCheck.dtos.LocationDTO;
import WCheck.dtos.UserDTO;
import WCheck.entities.Feedback;
import WCheck.entities.Location;
import WCheck.services.FeedbackService;
import WCheck.services.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api")
public class LocationController {

    private final LocationService locationService;
    private final FeedbackService feedbackService;

    @Autowired
    public LocationController(LocationService locationService, FeedbackService feedbackService) {
        this.locationService = locationService;
        this.feedbackService = feedbackService;
    }

    // Получение записи по ID
    @GetMapping("/location/get/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id) {
        Location location = locationService.getLocation(id);
        return ResponseEntity.ok(EntityDtoConverter.convertToDto(location, LocationDTO.class));
    }

    // Создание новой записи
    @PostMapping("/location/post")
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        Location location = EntityDtoConverter.convertToEntity(locationDTO, Location.class);
        Location createdLocation = locationService.createLocation(location);
        return new ResponseEntity<>(EntityDtoConverter.convertToDto(createdLocation, LocationDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/{locationId}/feedbacks")
    public ResponseEntity<List<Feedback>> getFeedbacksForLocation(@PathVariable Long locationId) {
        Location location = locationService.getLocation(locationId);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }

        List<Long> feedbackIds = location.getListFeedbackIds();
        List<Feedback> feedbacks = feedbackService.getFeedbacks(feedbackIds);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{locationId}/averageMark")
    public OptionalDouble getAverageMark(@PathVariable Long locationId){
        Location location = locationService.getLocation(locationId);
        if (location == null) {
            return OptionalDouble.empty();
        }
        List<Long> feedbackIds = location.getListFeedbackIds();
        List<Feedback> feedbacks = feedbackService.getFeedbacks(feedbackIds);
        return feedbacks.stream().mapToInt(Feedback::getMark).average();
    }

    @PostMapping("/location/mapBorder")
    public List<Location> getLocationsInBorder(@RequestBody BorderDTO borderDTO){
        return locationService.getAllLocationsInBorder(borderDTO);
    }

}
