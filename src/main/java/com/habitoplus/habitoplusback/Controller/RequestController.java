package com.habitoplus.habitoplusback.Controller;

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

import com.habitoplus.habitoplusback.Model.Request;
import com.habitoplus.habitoplusback.Service.RequestService;
import com.habitoplus.habitoplusback.dto.RequestDTO;

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

	// @Operation(summary = "Get all requests")
	// @ApiResponses(value = {
	// 		@ApiResponse(responseCode = "200", description = "Found requests", content = {
	// 				@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Request.class)))
	// 		}),
	// 		@ApiResponse(responseCode = "204", description = "No request found"),
	// 		@ApiResponse(responseCode = "400", description = "Bad request", content = {
	// 				@Content
	// 		}),
	// 		@ApiResponse(responseCode = "500", description = "Internal server error", content = {
	// 				@Content
	// 		})
	// })
	// @GetMapping
	// public List<Request> getAll() {
	// 	return service.getAll();
	// }

	// @Operation(summary = "Get a Request by its ID")
	// @ApiResponses(value = {
	// 		@ApiResponse(responseCode = "200", description = "Found request", content = {
	// 				@Content(mediaType = "application/json", schema = @Schema(implementation = Request.class))
	// 		}),
	// 		@ApiResponse(responseCode = "400", description = "Invalid request ID", content = {
	// 				@Content
	// 		}),
	// 		@ApiResponse(responseCode = "404", description = "Request not found", content = {
	// 				@Content
	// 		}),
	// 		@ApiResponse(responseCode = "500", description = "Internal server error", content = {
	// 				@Content
	// 		})
	// })
	// @GetMapping("{idGroup}/{idProfile}")
	// public ResponseEntity<?> getByIdRequest(@PathVariable Integer idGroup, @PathVariable Integer idProfile) {
	// 	Request request = service.getById(idGroup, idProfile);
	// 	return new ResponseEntity<Request>(request, HttpStatus.OK);
	// }

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

	// @Operation(summary = "Update a request")
	// @ApiResponses(value = {
	// 		@ApiResponse(responseCode = "200", description = "Request successfully updated", content = {
	// 				@Content(mediaType = "application/json", schema = @Schema(implementation = Request.class))
	// 		}),
	// 		@ApiResponse(responseCode = "400", description = "Invalid request data or group ID y profile ID", content = {
	// 				@Content
	// 		}),
	// 		@ApiResponse(responseCode = "404", description = "Request not found", content = {
	// 				@Content
	// 		}),
	// 		@ApiResponse(responseCode = "500", description = "Internal server error", content = {
	// 				@Content
	// 		})
	// })
	// @PutMapping("{idRequest}")
	// public ResponseEntity<?> updateRequest(@RequestBody Request request, @PathVariable Integer idGroup, @PathVariable Integer idProfile) {
	// 	Request auxRequest = service.getById(idGroup, idProfile);
	// 	auxRequest.getGroup().setIdGroup(idGroup);
	// 	auxRequest.getProfile().setIdProfile(idProfile);
	// 	service.save(auxRequest);
	// 	return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	// }

	// @Operation(summary = "Delete a request by ID")
    //     @ApiResponses(value = {
    //                     @ApiResponse(responseCode = "200", description = "Request successfully deleted", content = {
    //                                     @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    //                     }),
    //                     @ApiResponse(responseCode = "400", description = "Invalid group ID or profile ID", content = {
    //                                     @Content
    //                     }),
    //                     @ApiResponse(responseCode = "404", description = "Request not found", content = {
    //                                     @Content
    //                     }),
    //                     @ApiResponse(responseCode = "500", description = "Internal server error", content = {
    //                                     @Content
    //                     })
    //     })
	// @DeleteMapping("{idGroup}/{idProfile}")
	// public ResponseEntity<?> deleteRequest(@PathVariable Integer idGroup, @PathVariable Integer idProfile) {
	// 	service.delete(idGroup, idProfile);
	// 	return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	// }
	
}
