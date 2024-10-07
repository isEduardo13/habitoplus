package com.habitoplus.habitoplusback.Controller;

import java.util.List;

import org.apache.el.stream.Optional;
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

import com.habitoplus.habitoplusback.Model.Group;
import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Model.Request;
import com.habitoplus.habitoplusback.Service.GroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("groups")
@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class GroupController {

    @Autowired
    private GroupService service;

    @Operation(summary = "Get all students")
    @ApiResponse(responseCode = "200", description = "Found Groups", content = {
        @Content(mediaType= "application/json", array= @ArraySchema(schema= @Schema(implementation = Group.class)))
    })
    @GetMapping
    public List<Group> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get a group by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Group not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content)})
	@GetMapping("{idGroup}")
	public ResponseEntity<?> getByControlNumber(@PathVariable Integer idGroup) {
		Group group = service.getByIdGroup(idGroup);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}

    @PostMapping
	public ResponseEntity<?> add(@RequestBody Group group) {
		service.save(group);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

    @PutMapping("{idGroup}")
	public ResponseEntity<?> update(@RequestBody Group group, @PathVariable Integer idGroup) {
		Group auxGroup = service.getByIdGroup(idGroup);
		group.setIdGroup(auxGroup.getIdGroup());
		service.save(group);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}

    @DeleteMapping("{idGroup}")
	public ResponseEntity<?> delete(@PathVariable Integer idGroup ) {
		service.delete(idGroup);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}

	//Get group member
    @GetMapping("/{idGroup}/members")
    public List<GroupMember> getGroupMembers(@PathVariable Integer idGroup){
        return service.getGroupMembers(idGroup);
    }

	@Operation(summary = "Get a group by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Group not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content)})
	@GetMapping("{idGroup}/requests")
	public ResponseEntity<?> getRequests(@PathVariable Integer idGroup) {
		List<Request> requests = service.getByIdGroup(idGroup).getRequests();
		return new ResponseEntity<List<Request>>(requests, HttpStatus.OK);
	}
    
}
