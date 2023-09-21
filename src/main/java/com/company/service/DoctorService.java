package com.company.service;

import com.company.dto.ChangeDoctorInfoDTO;
import com.company.dto.DoctorDTO;
import com.company.entity.Doctor;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.BrigadeRepository;
import com.company.repository.DoctorRepository;
import com.company.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DoctorService {
    private final DoctorRepository repository;
    private final RegionRepository regionRepository;
    private final BrigadeRepository brigadeRepository;

    public DoctorService(DoctorRepository repository, RegionRepository regionRepository, BrigadeRepository brigadeRepository) {
        this.repository = repository;
        this.regionRepository = regionRepository;
        this.brigadeRepository = brigadeRepository;
    }

    public void createDoctor(DoctorDTO dto) {
        Doctor doctor=new Doctor();
        doctor.setName(dto.getName());
        doctor.setRegion(regionRepository.getRegionByName(dto.getRegionName().toUpperCase()));
        doctor.setSurname(dto.getSurname());
        doctor.setPhoneNumber(dto.getPhoneNumber());

        repository.save(doctor);
    }

    public List<DoctorDTO> getAllFree() {
        return repository.findAllByBrigadeNull()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO toDTO(Doctor doctor){
        return DoctorDTO.builder().surname(doctor.getSurname())
                .regionName(doctor.getRegion().getName())
                .phoneNumber(doctor.getPhoneNumber())
                .name(doctor.getName()).build();
    }

    public String delete(String id) {
        if(!repository.existsById(id)){
            throw new ItemNotFoundException("Doctor doesn't exist with this id.");
        }
        repository.deleteById(id);
        return "Doctor successfully deleted.";
    }
    public String changeDoctorInfo(ChangeDoctorInfoDTO dto){
        Optional<Doctor> optional = repository.findById(dto.getDoctorId());
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Doctor doesn't exist with this id.");
        }
        Doctor doctor = optional.get();

        Stream.of(dto).forEach(d->{
            if(d.getPhoneNumber()!=null){
                doctor.setPhoneNumber(d.getPhoneNumber());
            }
            if(d.getSurname()!=null){
                doctor.setSurname(d.getSurname());
            }
            if(d.getName()!=null){
                doctor.setName(d.getName());
            }
            if(dto.getBrigade()!=null){
                if(!brigadeRepository.existsById(dto.getBrigade())){
                    throw new ItemNotFoundException("Brigade doesn't exist with this id.");
                }
                doctor.setBrigade(brigadeRepository.findById(d.getBrigade()).get());
            }
        });
        return "All info successfully changed.";
    }
}