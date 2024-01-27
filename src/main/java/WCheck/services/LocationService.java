package WCheck.services;

import WCheck.dtos.BorderDTO;
import WCheck.entities.Feedback;
import WCheck.entities.Location;
import WCheck.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location getLocation(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public List<Location> getLocations(List<Long> ids){
        return locationRepository.findAllById(ids);
    }

    public List<Location> getAllLocationsInBorder(BorderDTO borderDTO) {
        return locationRepository.findByLatitudeBetweenAndLongitudeBetween(borderDTO.getSouth(), borderDTO.getNorth(), borderDTO.getWest(), borderDTO.getEast());
    }
}
