package com.gshahafridi.projectmanagementbackend.repositories;

import com.gshahafridi.projectmanagementbackend.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Long> {

    public Project findByProjectIdentifier(String projectIdentifier);
}
