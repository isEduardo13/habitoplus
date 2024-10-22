package com.habitoplus.habitoplusback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.dto.RequestDTO;
import com.habitoplus.habitoplusback.model.Request;
import com.habitoplus.habitoplusback.service.RequestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("requests")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name = "Requests", description = "Provides methods for managing requests")
public class RequestController {
	@Autowired
	private RequestService service;

	@Operation(summary = "Register a new request")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Request successfully registered", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Request.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid request data", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
	@PostMapping
	public ResponseEntity<?> requestToJoinAGroup(@RequestBody RequestDTO requestDTO) {
		service.save(requestDTO);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

	@Operation(summary = "Accept a request by its ID and the group ADMIN ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Request update successful, the request was accepted and the user was added to the request group.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Request.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid group ID or profile ID", content = {
					@Content
			}),
			@ApiResponse(responseCode = "404", description = "Request not found", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
	@PutMapping("{idGroup}/{idProfile}/acceptUser/administrators/{idAdmin}")
	public ResponseEntity<?> acceptUser(@PathVariable Integer idGroup, @PathVariable Integer idProfile, @PathVariable Integer idAdmin) {
		service.acceptUser(idGroup, idProfile, idAdmin);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}
}
