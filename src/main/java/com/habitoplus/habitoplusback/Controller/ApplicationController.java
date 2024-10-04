package com.habitoplus.habitoplusback.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.Model.Application;
import com.habitoplus.habitoplusback.Service.ApplicationService;
import com.habitoplus.habitoplusback.Service.ApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("applications")
@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ApplicationController {
    @Autowired
    private ApplicationService service;

    @Operation(summary = "Get all students")
    @ApiResponse(responseCode = "200", description = "Found Applications", content = {
        @Content(mediaType= "application/json", array= @ArraySchema(schema= @Schema(implementation = Application.class)))
    })
    @GetMapping
    public List<Application> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get a Application by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Application found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Application.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Application not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content)})
	@GetMapping("{idApplication}")
	public ResponseEntity<?> getByIdApplication(@PathVariable Integer idApplication) {
		Application application = service.getByIdApplication(idApplication);
		return new ResponseEntity<Application>(application, HttpStatus.OK);
	}

    @PostMapping
	public ResponseEntity<?> add(@RequestBody Application application) {
		service.save(application);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

    @PutMapping("{idApplication}")
	public ResponseEntity<?> update(@RequestBody Application application, @PathVariable Integer idApplication) {
		Application auxApplication = service.getByIdApplication(idApplication);
		application.setIdApplication(auxApplication.getIdApplication());
		service.save(application);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}

    @DeleteMapping("{idApplication}")
	public ResponseEntity<?> delete(@PathVariable Integer idApplication ) {
		service.delete(idApplication);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}
}
