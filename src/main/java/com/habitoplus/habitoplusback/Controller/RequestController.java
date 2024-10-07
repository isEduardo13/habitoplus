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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.Model.Request;
import com.habitoplus.habitoplusback.Service.RequestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("request")
@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class RequestController {
    @Autowired
    private RequestService service;

    @Operation(summary = "Get all request")
    @ApiResponse(responseCode = "200", description = "Found Requests", content = {
        @Content(mediaType= "application/json", array= @ArraySchema(schema= @Schema(implementation = Request.class)))
    })
    @GetMapping
    public List<Request> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get a Request by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Request found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Request.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Request not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content)})
	@GetMapping("{idRequest}")
	public ResponseEntity<?> getByIdRequest(@PathVariable Integer idRequest) {
		Request request = service.getByIdRequest(idRequest);
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}

    @PostMapping
	public ResponseEntity<?> add(@RequestBody Request request) {
		service.save(request);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

    @PutMapping("{idRequest}")
	public ResponseEntity<?> update(@RequestBody Request request, @PathVariable Integer idRequest) {
		Request auxRequest = service.getByIdRequest(idRequest);
		request.setIdRequest(auxRequest.getIdRequest());
		service.save(request);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}

    @DeleteMapping("{idRequest}")
	public ResponseEntity<?> delete(@PathVariable Integer idRequest ) {
		service.delete(idRequest);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}

	@PutMapping("{idRequest}/acceptUser")
	public ResponseEntity<?> acceptUser(@PathVariable Integer idRequest) {
		service.acceptUser(idRequest);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}
}
