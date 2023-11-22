package com.example.demo.service.server_impl;

import com.example.demo.dto.CaregiverDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.CaregiverMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Caregiver;
import com.example.demo.model.User;
import com.example.demo.repository.CaregiverRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.CaregiverService;
import com.example.demo.util.Merger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaregiverServiceImpl implements CaregiverService {
    private final CaregiverRepo caregiverRepo;
    private final UserRepo userRepo;

    @Override
    public List<CaregiverDto> getAll() {
        return caregiverRepo.findAll().stream()
                .map(CaregiverMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    public Optional<CaregiverDto> getById(Long id) {
        return Optional.ofNullable(id)
                .map(caregiverRepo::getReferenceById)
                .map(CaregiverMapper.INSTANCE::toDto);
    }

    @Override
    public CaregiverDto create(CaregiverDto caregiverDto) {
        if (caregiverDto == null) {
            throw new NotFoundException("caregiver is null");
        }
        caregiverDto.setCaregiverUserId(null);

        User user = UserMapper.INSTANCE.toModel(caregiverDto.getUser());
        user.setUserId(null);

        User newUser = userRepo.save(user);

        Caregiver caregiver = CaregiverMapper.INSTANCE.toModel(caregiverDto);
        caregiver.setUser(newUser);

        return Optional.of(caregiver)
                .map(caregiverRepo::save)
                .map(CaregiverMapper.INSTANCE::toDto)
                .orElseThrow();
    }

    @Override
    public Optional<CaregiverDto> updateById(Long id, CaregiverDto caregiverDto) {
        if (id == null) {
            throw new NotFoundException("id is null");
        } else if (caregiverDto == null || caregiverDto.getCaregiverUserId() == null) {
            throw new NotFoundException("caregiver is null");
        } else if (caregiverDto.getUser() == null || caregiverDto.getUser().getUserId() == null) {
            throw new NotFoundException("caregiver user not found");
        }

        Caregiver caregiver = caregiverRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("caregiver not found with id=" + id));
        User user = userRepo.findById(caregiver.getUser().getUserId())
                .orElseThrow(() -> new NotFoundException("user does not exists with id=" + caregiverDto.getUser().getUserId()));

        Merger.user(user, caregiverDto.getUser());
        caregiver.setUser(user);

        Merger.caregiver(caregiver, caregiverDto);

        return Optional.of(caregiver)
                .map(caregiverRepo::save)
                .map(CaregiverMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<CaregiverDto> deleteById(Long id) {
        if (id == null) {
            throw new NotFoundException("id is null");
        } else if (!caregiverRepo.existsById(id)) {
            throw new NotFoundException("caregiver not found with id=" + id);
        }
        Caregiver caregiver = caregiverRepo.getReferenceById(id);
        User user = caregiver.getUser();

        userRepo.delete(user);

        return Optional.of(caregiver)
                .map(CaregiverMapper.INSTANCE::toDto);
    }
}
