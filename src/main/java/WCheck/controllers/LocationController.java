package WCheck.controllers;

import WCheck.converter.EntityDtoConverter;
import WCheck.dtos.BorderDTO;
import WCheck.dtos.LocationDTO;
import WCheck.entities.Feedback;
import WCheck.entities.Location;
import WCheck.entities.UserName;
import WCheck.services.FeedbackService;
import WCheck.services.LocationService;
import WCheck.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/api")
public class LocationController {

    private final LocationService locationService;
    private final FeedbackService feedbackService;
    private final UserService userService;

    @Autowired
    public LocationController(LocationService locationService, FeedbackService feedbackService, UserService userService) {
        this.locationService = locationService;
        this.feedbackService = feedbackService;
        this.userService = userService;
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

    @GetMapping("/location/{locationId}/feedbacks")
    public ResponseEntity<List<Feedback>> getFeedbacksForLocation(@PathVariable Long locationId) {
        Location location = locationService.getLocation(locationId);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }

        List<Feedback> feedbacks = location.getFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/location/{locationId}/averageMark")
    public OptionalDouble getAverageMark(@PathVariable Long locationId){
        Location location = locationService.getLocation(locationId);
        if (location == null) {
            return OptionalDouble.empty();
        }
        List<Feedback> feedbacks = location.getFeedbacks();
        return feedbacks.stream().mapToInt(Feedback::getMark).average();
    }

    @PostMapping("/location/mapBorder")
    public List<Location> getLocationsInBorder(@RequestBody BorderDTO borderDTO){
        return locationService.getAllLocationsInBorder(borderDTO);
    }

    @PostMapping("/location/{locationId}/upvote")
    public ResponseEntity<LocationDTO> upvoteLocation(@PathVariable Long locationId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserName userName = userService.loadUserByUsername(username);
        Location location = locationService.getLocation(locationId);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        if(location.getVotes() == null){
            location.setVotes(0);
        }
        if(location.getUpvoters().contains(userName)){
            return ResponseEntity.badRequest().build();
        }
        location.setVotes(location.getVotes() + 1);
        location.getUpvoters().add(userName);
        locationService.saveLocation(location);
        return new ResponseEntity<>(EntityDtoConverter.convertToDto(location, LocationDTO.class), HttpStatus.CREATED);
    }

}
