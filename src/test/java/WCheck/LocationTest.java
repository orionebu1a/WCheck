package WCheck;

import WCheck.controllers.LocationController;
import WCheck.dtos.LocationDTO;
import WCheck.dtos.UserDTO;
import WCheck.entities.Location;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationTest {
    @Autowired
    private LocationController locationController;

    @Test
    public void postGetLocation(){
        LocationDTO location = new LocationDTO(15, 46.57, 28.32);
        locationController.createLocation(location);
        ResponseEntity<LocationDTO> responseEntity = locationController.getLocationById(location.getId());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(location.getLatitude(), responseEntity.getBody().getLatitude(), 0.00001);
        Assert.assertEquals(location.getLongitude(), responseEntity.getBody().getLongitude(), 0.00001);
        Assert.assertEquals(location.getId(), responseEntity.getBody().getId());
    }

}
