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

import com.habitoplus.habitoplusback.Model.Group;
import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Model.GroupMemberPK;
import com.habitoplus.habitoplusback.Service.GroupMemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("groupMembers")
@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class GroupMemberController {
    @Autowired
    private GroupMemberService service;

    @Operation(summary = "Get all students")
    @ApiResponse(responseCode = "200", description = "Found Groups", content = {
        @Content(mediaType= "application/json", array= @ArraySchema(schema= @Schema(implementation = Group.class)))
    })
    @GetMapping
    public List<GroupMember> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get a group by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Group not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content)})
	@GetMapping("{idGroupMember}")
	public ResponseEntity<?> getByIdGroupMember(@PathVariable Integer idGroupMember) {
		GroupMember groupMember = service.getByIdGroupMember(idGroupMember);
		return new ResponseEntity<GroupMember>(groupMember, HttpStatus.OK);
	}

    @PostMapping
	public ResponseEntity<?> add(@RequestBody GroupMember groupMember) {
		service.save(groupMember);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

    // @PutMapping("{idGroupMember}")
	// public ResponseEntity<?> update(@RequestBody GroupMember groupMember, @PathVariable GroupMemberPK idGroupMember) {
	// 	GroupMember auxGroupMember = service.getByIdGroupMember(idGroupMember);
	// 	groupMember.setIdGroupMember(auxGroupMember.get);
	// 	service.save(groupMember);
	// 	return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	// }

    @DeleteMapping("{idGroupMember}")
	public ResponseEntity<?> delete(@PathVariable Integer idGroupMember ) {
		service.delete(idGroupMember);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}
}
