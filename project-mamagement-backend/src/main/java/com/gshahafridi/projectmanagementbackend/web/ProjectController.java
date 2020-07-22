package com.gshahafridi.projectmanagementbackend.web;

import com.gshahafridi.projectmanagementbackend.domain.Project;
import com.gshahafridi.projectmanagementbackend.services.ProjectService;
import com.gshahafridi.projectmanagementbackend.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ValidationErrorService validationErrorService;
    @PostMapping("")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        ResponseEntity<?> errors=validationErrorService.checkForErrors(bindingResult);
        if(errors!=null){
            return  errors;
        }
        Project project1 = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> findProjectByIdentifier(@PathVariable String projectIdentifier) {
        Project project = projectService.findProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<Iterable<Project>> findAllProjects() {
      Iterable<Project> projects=projectService.findAllProjects();
        return new ResponseEntity(projects, HttpStatus.OK);
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier){
        projectService.deleteProject(projectIdentifier);
        return new ResponseEntity<String>("Project with Identifier: '"+projectIdentifier+"' Deleted successfully",HttpStatus.OK);
    }
}
