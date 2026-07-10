package com.ngo.ngo_event_management.service;

import com.ngo.ngo_event_management.entity.NgoDetails;
import com.ngo.ngo_event_management.repository.NgoDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NgoDetailsService {

    @Autowired
    private NgoDetailsRepository ngoDetailsRepository;

    public Optional<NgoDetails> getNgoDetails() {
        return ngoDetailsRepository.findById(1);
    }

    public NgoDetails createNgoDetails(NgoDetails ngoDetails) {
        return ngoDetailsRepository.save(ngoDetails);
    }

    public NgoDetails updateNgoDetails(Integer id, NgoDetails ngoDetails) {
        Optional<NgoDetails> optionalNgoDetails = ngoDetailsRepository.findById(id);
        if (optionalNgoDetails.isPresent()) {
            NgoDetails existingDetails = optionalNgoDetails.get();
            existingDetails.setNgoName(ngoDetails.getNgoName());
            existingDetails.setMission(ngoDetails.getMission());
            existingDetails.setVision(ngoDetails.getVision());
            existingDetails.setEmail(ngoDetails.getEmail());
            existingDetails.setPhone(ngoDetails.getPhone());
            existingDetails.setAddress(ngoDetails.getAddress());
            existingDetails.setWebsite(ngoDetails.getWebsite());
            return ngoDetailsRepository.save(existingDetails);
        }
        return null;
    }

    public boolean deleteNgoDetails(Integer id) {
        if (ngoDetailsRepository.existsById(id)) {
            ngoDetailsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
