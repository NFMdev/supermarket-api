package com.github.NFMdev.supermarket_api.controller;

import com.github.NFMdev.supermarket_api.model.Section;
import com.github.NFMdev.supermarket_api.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section ")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @PostMapping("/add")
    public ResponseEntity<?> addSection(@RequestBody Section rq) {
        try {
            Section section = sectionService.addSection(rq);
            return ResponseEntity.ok(section);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error adding section: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateSection(@RequestBody Section rq) {
        try {
            Section section = sectionService.updateSection(rq);
            return ResponseEntity.ok(section);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating section: " + e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteSection(@RequestBody Long id) {
        try {
            sectionService.deleteSection(id);
            return ResponseEntity.ok("Section deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting section: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSectionById(@PathVariable Long id) {
        try {
            Section section = sectionService.getSectionById(id);
            if (section != null) {
                return ResponseEntity.ok(section);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching section: " + e.getMessage());
        }
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<?> getAllSections(@PathVariable Long id) {
        try {
            List<Section> sections = sectionService.getAllSectionsBySupermarketId(id);
            return ResponseEntity.ok(sections);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching sections: " + e.getMessage());
        }
    }
}
