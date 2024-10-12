package com.habitoplus.habitoplusback.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.model.GroupMember;
import com.habitoplus.habitoplusback.model.Request;
import com.habitoplus.habitoplusback.service.GroupMemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("groupMembers")
@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Group members", description = "Provides methods for managing group members")
public class GroupMemberController {
    @Autowired
    private GroupMemberService service;

    @Operation(summary = "Get all members of groups")
    @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found members", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GroupMember.class)))
                        }),
                        @ApiResponse(responseCode = "204", description = "No members found"),
                        @ApiResponse(responseCode = "400", description = "Bad request", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
    @GetMapping
    public List<GroupMember> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get a group member by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group member found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GroupMember.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Group not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content)})
	@GetMapping("{idGroup}/{idProfile}")
	public ResponseEntity<?> getByIdGroupMember(@PathVariable Integer idGroup, @PathVariable Integer idProfile) {
		GroupMember groupMember = service.getById(idGroup, idProfile);
		return new ResponseEntity<GroupMember>(groupMember, HttpStatus.OK);
	}

	@Operation(summary = "Register a new group member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group member successfully registered", content = {
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
	public ResponseEntity<?> add(@RequestBody GroupMember groupMember) {
		service.save(groupMember);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

	@Operation(summary = "Update a group member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group member successfully updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Request.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid request data or group ID and profile ID", content = {
					@Content
			}),
			@ApiResponse(responseCode = "404", description = "Group member not found", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
    @PutMapping("{idGroup}/{idProfile}")
	public ResponseEntity<?> update(@RequestBody GroupMember groupMember, @PathVariable Integer idGroup, @PathVariable Integer idProfile) {
		GroupMember auxGroupMember = service.getById(idGroup, idProfile);
		groupMember.getGroup().setIdGroup(auxGroupMember.getGroup().getIdGroup());
		groupMember.getProfile().setIdProfile(auxGroupMember.getProfile().getIdProfile());
		service.save(groupMember);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}

	@Operation(summary = "Delete a group member by ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Group member successfully deleted", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid group ID or profile ID", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "404", description = "Group member not found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
    @DeleteMapping("{idGroup}/{idProfile}")
	public ResponseEntity<?> delete(@PathVariable Integer idGroup, @PathVariable Integer idProfile) {
		service.delete(idGroup, idProfile);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}

	@Operation(summary = "Get all groups with pagination")
	@GetMapping(value="pagination", params = {"page", "size"})
	public List<GroupMember> getAllPaginated(@RequestParam(value = "page", defaultValue="0", required=false) int
	page, @RequestParam(value = "pageSize", defaultValue="10", required = false) int pageSize){
		List<GroupMember> members = service.getAll(page, pageSize);
		return members;
	}

}
