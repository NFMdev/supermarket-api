package com.github.NFMdev.supermarket_api.service;

import com.github.NFMdev.supermarket_api.model.Section;
import com.github.NFMdev.supermarket_api.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    @Autowired
    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Section addSection(Section section) {
        if (
                sectionRepository.findByNameAndSupermarketId(
                    section.getName(), section.getSupermarket().getId()
                ).isPresent()
        ) {
            throw new IllegalArgumentException("Section already exists");
        }
        return sectionRepository.save(section);
    }

    public Section updateSection(Section section) {
        if (sectionRepository.findById(section.getId()).isPresent()) {
            return sectionRepository.save(section);
        } else {
            throw new IllegalArgumentException("Section not found");
        }
    }

    public void deleteSection(Long id) {
        if (sectionRepository.findById(id).isPresent()) {
            sectionRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Section not found");
        }
    }

    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).orElse(null);
    }

    public List<Section> getAllSectionsBySupermarketId(Long id) {
        return sectionRepository.findBySupermarketId(id);
    }
}
