package ru.job4j.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dto.FileDTO;
import ru.job4j.model.Candidate;
import ru.job4j.service.CandidateService;
import ru.job4j.service.CityService;

@ThreadSafe
@Controller
@RequestMapping("/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    private final CityService cityService;

    public CandidateController(CandidateService candidateService, CityService cityService) {
        this.candidateService = candidateService;
        this.cityService = cityService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("candidates", candidateService.findAll());
        return "candidates/list";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "candidates/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Candidate candidate, @RequestParam MultipartFile file, Model model) {
        try {
            candidateService.save(candidate, new FileDTO(file.getOriginalFilename(), file.getBytes()));
            return "redirect:/candidates";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "/errors/404";
        }
    }

    @GetMapping("/{id}")
    public String findByID(Model model, @PathVariable int id) {
        var candidateOptional = candidateService.findByID(id);
        if (candidateOptional.isEmpty()) {
            model.addAttribute("message", "Резюме с указанным идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("candidate", candidateOptional.get());
        return "candidates/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Candidate candidate, @RequestParam MultipartFile file, Model model) {
        try {
            var isUpdated = candidateService.update(candidate, null);
            if (!isUpdated) {
                model.addAttribute("message", "Резюме с указанным идентификатором не найдено");
                return "errors/404";
            }
            return "redirect:/candidates";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "/errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = candidateService.deleteByID(id);
        if (!isDeleted) {
            model.addAttribute("message", "Резюме с указанным идентификатором не найдено");
            return "errors/404";
        }
        return "redirect:/candidates";
    }
}
