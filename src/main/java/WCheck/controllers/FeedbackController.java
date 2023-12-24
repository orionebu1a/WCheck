package WCheck.controllers;

import WCheck.converter.EntityDtoConverter;
import WCheck.dtos.FeedbackDTO;
import WCheck.dtos.UserDTO;
import WCheck.entities.Feedback;
import WCheck.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // Получение записи по ID
    @GetMapping("/feedback/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Feedback feedback = feedbackService.getFeedback(id);
        return ResponseEntity.ok(EntityDtoConverter.convertToDto(feedback, UserDTO.class));
    }

    // Создание новой записи
    @PostMapping("/feedback/post")
    public ResponseEntity<FeedbackDTO> createYourEntity(@RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedback = EntityDtoConverter.convertToEntity(feedbackDTO, Feedback.class);
        Feedback createdFeedback = feedbackService.createFeedback(feedback);
        return new ResponseEntity<>(EntityDtoConverter.convertToDto(createdFeedback, FeedbackDTO.class), HttpStatus.CREATED);
    }
}
