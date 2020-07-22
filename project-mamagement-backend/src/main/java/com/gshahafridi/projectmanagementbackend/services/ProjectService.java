package com.gshahafridi.projectmanagementbackend.services;

import com.gshahafridi.projectmanagementbackend.domain.Project;
import com.gshahafridi.projectmanagementbackend.exceptions.ProjectIdException;
import com.gshahafridi.projectmanagementbackend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception e){
            throw new ProjectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exist");
        }
    }
    public Project findProjectByIdentifier(String projectIdentifier){
        Project project=projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project==null){
            throw new ProjectIdException("Project Id '"+projectIdentifier.toUpperCase()+"' does not exist");
        }
        return project;
    }
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }
    public void deleteProject(String projectIdentifier){
        Project project=projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project==null){
            throw new ProjectIdException("Cannot deleted project with identifier '"+projectIdentifier.toUpperCase()+"'. The project does not exist");
        }
        projectRepository.delete(project);
    }
}
