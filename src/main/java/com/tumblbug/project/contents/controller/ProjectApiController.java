package com.tumblbug.project.contents.controller;

import com.tumblbug.project.common.exceptions.CustomRequestException;
import com.tumblbug.project.contents.dto.CreateProject;
import com.tumblbug.project.contents.dto.CustomRequest;
import com.tumblbug.project.contents.dto.ProjectDto;
import com.tumblbug.project.contents.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Api(tags = "Project")
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectApiController {

    private final ProjectService projectService;

    @ApiOperation(value = "프로젝트 리스트")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ProjectDto.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad_request", response = CustomRequestException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = CustomRequestException.class)})
    @GetMapping
    public ResponseEntity<?> projectList(CustomRequest req) {
        return ResponseEntity.ok().body(projectService.getProjectList(req.of()));
    }

    @ApiOperation(value = "프로젝트 리스트 상세")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ProjectDto.class),
            @ApiResponse(code = 400, message = "Bad_request", response = CustomRequestException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = CustomRequestException.class)})
    @GetMapping("/{id}")
    public ResponseEntity<?> projectList(@PathVariable("id") final UUID uuid) {
        return ResponseEntity.ok().body(projectService.getProjectDetail(uuid));
    }

    @ApiOperation(value = "프로젝트 등룩")
    @ApiResponse(code = 204, message = "")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody final CreateProject req) {
        projectService.createProject(req);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "프로젝트 삭제")
    @ApiResponse(code = 204, message = "")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") final UUID uuid) {
        projectService.deleteProject(uuid);
        return ResponseEntity.noContent().build();
    }
}
