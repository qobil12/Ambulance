package com.company.service;

import com.company.dto.ChangeDoctorInfoDTO;
import com.company.dto.DoctorDTO;
import com.company.entity.Doctor;
import com.company.exceptions.ItemNotFoundException;
import com.company.mapper.DoctorMapper;
import com.company.repository.BrigadeRepository;
import com.company.repository.DoctorRepository;
import com.company.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DoctorService {
    private final DoctorRepository repository;
    private final RegionRepository regionRepository;
    private final BrigadeRepository brigadeRepository;
    private final DoctorMapper doctorMapper = DoctorMapper.INSTANCE;

    public DoctorService(DoctorRepository repository, RegionRepository regionRepository, BrigadeRepository brigadeRepository) {
        this.repository = repository;
        this.regionRepository = regionRepository;
        this.brigadeRepository = brigadeRepository;
    }

    public DoctorDTO createDoctor(DoctorDTO dto) {
        regionRepository.findById(dto.getRegionId()).orElseThrow(() -> new ItemNotFoundException("Region doesn't exist with this ID"));
        return doctorMapper.toDoctorDTO(repository.save(doctorMapper.toDoctorEntity(dto)));
    }

    public List<DoctorDTO> getAllFree() {

        return repository.findAllByBrigadeNull()
                .stream().map(doctorMapper::toDoctorDTO)
                .collect(Collectors.toList());
    }

    public void delete(UUID id) {

        repository.delete(repository.findById(id).orElseThrow(() -> new ItemNotFoundException("Doctor doesn't exist with this ID")));

    }

    public DoctorDTO changeDoctorInfo(ChangeDoctorInfoDTO dto) {
        Doctor doctor = repository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ItemNotFoundException("Doctor doesn't exist with this ID"));

        Stream.of(dto).forEach(d -> {
            if (d.getPhoneNumber() != null) {
                doctor.setPhoneNumber(d.getPhoneNumber());
            }
            if (d.getSurname() != null) {
                doctor.setSurname(d.getSurname());
            }
            if (d.getName() != null) {
                doctor.setName(d.getName());
            }
            if (dto.getBrigade() != null) {
                doctor.setBrigade(brigadeRepository.findById(d.getBrigade())
                        .orElseThrow(() -> new ItemNotFoundException("Brigade doesn't exist with this ID")));
            }
        });

        return doctorMapper.toDoctorDTO(repository.save(doctor));
    }
}