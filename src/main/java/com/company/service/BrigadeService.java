package com.company.service;

import com.company.dto.BrigadeCarDTO;
import com.company.dto.BrigadeDTO;
import com.company.dto.BrigadeDoctorDTO;
import com.company.entity.Brigade;
import com.company.exceptions.ItemNotFoundException;
import com.company.mapper.BrigadeMapper;
import com.company.repository.BrigadeRepository;
import com.company.repository.CarRepository;
import com.company.repository.DoctorRepository;
import com.company.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BrigadeService {
    final
    BrigadeRepository brigadeRepository;
    final
    RegionRepository regionRepository;
    final
    DoctorRepository doctorRepository;
    final
    CarRepository carRepository;
    final BrigadeMapper brigadeMapper = BrigadeMapper.INSTANCE;

    public BrigadeService(BrigadeRepository brigadeRepository, RegionRepository regionRepository, DoctorRepository doctorRepository, CarRepository carRepository) {
        this.brigadeRepository = brigadeRepository;
        this.regionRepository = regionRepository;
        this.doctorRepository = doctorRepository;
        this.carRepository = carRepository;
    }

    public BrigadeDTO createBrigade(BrigadeDTO dto) {
        regionRepository.findById(dto.getRegionId()).orElseThrow(() -> new ItemNotFoundException("Region doesn't exist with this ID !"));
        carRepository.findById(dto.getCarId()).orElseThrow(() -> new ItemNotFoundException("Car doesn't exist with this ID !"));
        doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new ItemNotFoundException("Doctor doesn't exist with this ID"));

        return brigadeMapper.toBrigadeDTO(brigadeRepository.save(brigadeMapper.toBrigadeEntity(dto)));
    }

    public List<BrigadeDTO> getAllByIsBusyField(Boolean isBusy) {
        return brigadeRepository.findAllByIsBusy(isBusy)
                .stream()
                .map(brigadeMapper::toBrigadeDTO)
                .toList();
    }

    public void deleteById(UUID id) {
        brigadeRepository.delete(brigadeRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Brigade doesn't exist with this ID")));
    }

    public BrigadeDTO changeBrigadeCar(BrigadeCarDTO dto) {
        Brigade brigade = brigadeRepository.findById(dto.getBrigadeId())
                .orElseThrow(() -> new ItemNotFoundException("Brigade doesn't exist with this ID"));
        brigade.setCar(carRepository.findById(dto.getCarId()).orElseThrow(() -> new ItemNotFoundException("Car doesn't exist with this ID")));


        return brigadeMapper.toBrigadeDTO(brigadeRepository.save(brigade));
    }

    public BrigadeDTO changeBrigadeDoctor(BrigadeDoctorDTO dto) {
        Brigade brigadeById = brigadeRepository.findById(dto.getBrigadeId())
                .orElseThrow(() -> new ItemNotFoundException("Brigade doesn't exist with this ID"));

        brigadeById.setDoctor(doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ItemNotFoundException("Doctor doesn't exist with this ID")));

        return brigadeMapper.toBrigadeDTO(brigadeRepository.save(brigadeById));
    }

    public List<BrigadeDTO> getAllList() {
        return brigadeRepository.findAll()
                .stream().map(brigadeMapper::toBrigadeDTO)
                .toList();
    }

}
