package com.company.service;

import com.company.dto.BrigadeCarDTO;
import com.company.dto.BrigadeDTO;
import com.company.dto.BrigadeDoctorDTO;
import com.company.entity.Brigade;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.BrigadeRepository;
import com.company.repository.CarRepository;
import com.company.repository.DoctorRepository;
import com.company.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public BrigadeService(BrigadeRepository brigadeRepository, RegionRepository regionRepository, DoctorRepository doctorRepository, CarRepository carRepository) {
        this.brigadeRepository = brigadeRepository;
        this.regionRepository = regionRepository;
        this.doctorRepository = doctorRepository;
        this.carRepository = carRepository;
    }

    public void createBrigade(BrigadeDTO dto) {
        Brigade brigade = new Brigade();
        brigade.setRegion(regionRepository.getRegionByName(dto.getRegionName().toUpperCase()));
        brigade.setCar(carRepository.getByNumber(dto.getCarNumber()));
        brigade.setDoctor(doctorRepository.getByPhoneNumber(dto.getDoctorPhoneNumber()));

        brigadeRepository.save(brigade);
    }

    public List<BrigadeDTO> getAllByIsBusyField(Boolean isBusy) {
        return brigadeRepository.findAllByIsBusy(isBusy)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(String id) {
        brigadeRepository.deleteById(id);
    }

    public String changeBrigadeCar(BrigadeCarDTO dto) {

        Brigade brigadeById = brigadeRepository.getBrigadeById(dto.getBrigadeId());
        if(brigadeById==null){
            throw new ItemNotFoundException("There is no brigade with this id !");
        }
        brigadeById.setCar(carRepository.getByNumber(dto.getCarNumber()));
        brigadeRepository.save(brigadeById);

        return "Brigade's car successfully changed !";
    }

    public String changeBrigadeDoctor(BrigadeDoctorDTO dto){
        Brigade brigadeById = brigadeRepository.getBrigadeById(dto.getBrigadeId());
        if(brigadeById==null){
            throw new ItemNotFoundException("There is no brigade with this id !");
        }
        brigadeById.setDoctor(doctorRepository.getByPhoneNumber(dto.getDoctorPhoneNumber()));
        brigadeRepository.save(brigadeById);
        return "Brigade's doctor successfully changed !";
    }
    public List<BrigadeDTO> getAllList() {
       return brigadeRepository.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BrigadeDTO toDTO(Brigade brigade){
        return BrigadeDTO.builder().carNumber(brigade.getCar().getNumber())
                .regionName(brigade.getRegion().getName())
                .doctorPhoneNumber(brigade.getDoctor().getPhoneNumber())
                .build();
    }
}
