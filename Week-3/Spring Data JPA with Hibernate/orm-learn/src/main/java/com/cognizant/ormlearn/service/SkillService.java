package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);
    
    @Autowired
    private SkillRepository skillRepository;
    
    /**
     * Get all skills
     */
    @Transactional
    public List<Skill> getAllSkills() {
        LOGGER.info("Fetching all skills");
        return skillRepository.findAll();
    }
    
    /**
     * Get skill by ID
     */
    @Transactional
    public Skill get(int id) {
        LOGGER.info("Finding skill by id: {}", id);
        Optional<Skill> result = skillRepository.findById(id);
        if (result.isPresent()) {
            Skill skill = result.get();
            LOGGER.info("Found skill: {}", skill);
            return skill;
        } else {
            LOGGER.warn("Skill not found with id: {}", id);
            return null;
        }
    }
    
    /**
     * Save skill
     */
    @Transactional
    public Skill save(Skill skill) {
        LOGGER.info("Saving skill: {}", skill);
        Skill savedSkill = skillRepository.save(skill);
        LOGGER.info("Skill saved successfully: {}", savedSkill);
        return savedSkill;
    }
} 