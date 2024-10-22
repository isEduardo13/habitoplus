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
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.model.Group;
import com.habitoplus.habitoplusback.service.GroupService;
import com.habitoplus.habitoplusback.dto.CommentWithProfileDTO;
import com.habitoplus.habitoplusback.dto.GroupDTO;
import com.habitoplus.habitoplusback.dto.GroupMemberWithProfileDTO;
import com.habitoplus.habitoplusback.dto.RequestWithProfileDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("groups")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name = "Groups", description = "Provides methods for managing groups")
public class GroupController {

	@Autowired
	private GroupService service;


	@Operation(summary = "Get all groups")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found groups", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Group.class)))
			}),
			@ApiResponse(responseCode = "204", description = "No groups found"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
	@GetMapping
	public List<Group> consultGroups() {
		return service.getAll();
	}

	@Operation(summary = "Get all groups with pagination")
	@GetMapping(value="pagination/{page}/{pageSize}")
	public List<Group> consultGroupsPaginated(@PathVariable Integer page, @PathVariable Integer pageSize){
		List<Group> groups = service.getAll(page, pageSize);
		return groups;
	}

	@Operation(summary = "Get a Group by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found Group", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid group ID", content = {
					@Content
			}),
			@ApiResponse(responseCode = "404", description = "Group not found", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
	@GetMapping("{idGroup}")
	public ResponseEntity<?> consultGroup(@PathVariable Integer idGroup) {
		Group group = service.getById(idGroup);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}

	@Operation(summary = "Create a new group")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group successfully registered", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid group data or profile ID", content = {
					@Content
			}),
			@ApiResponse(responseCode = "404", description = "Profile not found", content = {
				@Content
		}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
	@PostMapping("/profiles/{idProfile}")
	public ResponseEntity<?> createGroup(@RequestBody GroupDTO group, @PathVariable Integer idProfile) {
		service.save(group, idProfile);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

	@Operation(summary = "Update group information by its id and the group admin id. (Only the group admin can do this)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group successfully updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid group data or group ID", content = {
					@Content
			}),
			@ApiResponse(responseCode = "404", description = "Group not found", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
	@PutMapping("{idGroup}/administrators/{idAdmin}")
	public ResponseEntity<?> updateGroupInformation(@RequestBody GroupDTO groupDTO, @PathVariable Integer idGroup, @PathVariable Integer idAdmin) {
		service.save(groupDTO, idGroup, idAdmin);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}

	@Operation(summary = "Delete a group by its id and the group admin id. (Only the group admin can do this)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group successfully deleted", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid group ID", content = {
					@Content
			}),
			@ApiResponse(responseCode = "404", description = "Group not found", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
	@DeleteMapping("{idGroup}/administrators/{idAdmin}")
	public ResponseEntity<?> deleteGroup(@PathVariable Integer idGroup, @PathVariable Integer idAdmin) {
		service.delete(idGroup, idAdmin);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}

	@Operation(summary = "Get list of group members by group id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all users in the group", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Group.class)))
			}),
			@ApiResponse(responseCode = "204", description = "No habits found"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
	@GetMapping("/{idGroup}/groupMembers")
	public List<GroupMemberWithProfileDTO> consultTheGroupUsers(@PathVariable Integer idGroup) {
		return service.getGroupMembers(idGroup);
	}
	
	@Operation(summary = "Get list of group members by group id with pagination")
	@GetMapping(value="/{idGroup}/groupMembers/pagination/{page}/{pageSize}")
	public List<GroupMemberWithProfileDTO> consultTheGroupUsersPaginated(@PathVariable Integer idGroup, @PathVariable Integer page, @PathVariable Integer pageSize){
		List<GroupMemberWithProfileDTO> members = service.getGroupMembers(idGroup, page, pageSize);
		return members;
	}

	@Operation(summary = "Get list of requests by group id and the group's admin id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Group not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content) })
	@GetMapping("{idGroup}/requests/administrators/{idAdmin}")
	public ResponseEntity<?> consultRequests(@PathVariable Integer idGroup, @PathVariable Integer idAdmin) {
		List<RequestWithProfileDTO> requests = service.getRequests(idGroup, idAdmin);
		return new ResponseEntity<List<RequestWithProfileDTO>>(requests, HttpStatus.OK);
	}

	@Operation(summary = "Get list of requests by group id and the group's admin id with pagination")
	@GetMapping(value="{idGroup}/requests/administrators/{idAdmin}/pagination/{page}/{pageSize}")
	public List<RequestWithProfileDTO> consultRequestsPaginated(@PathVariable Integer idGroup, @PathVariable Integer idAdmin, @PathVariable Integer page, @PathVariable Integer pageSize){
		return service.getRequests(idGroup, idAdmin, page, pageSize);
	}

	@Operation(summary = "Get list of comments by group id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of comments found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Group not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content) })
	@GetMapping("{idGroup}/comments")
	public ResponseEntity<?> getComments(@PathVariable Integer idGroup) {
		List<CommentWithProfileDTO> comments = service.getComments(idGroup);
		return new ResponseEntity<List<CommentWithProfileDTO>>(comments, HttpStatus.OK);
	}

	@Operation(summary = "Get list of comments by group id with pagination")
	@GetMapping(value="{idGroup}/comments/pagination/{page}/{pageSize}")
	public List<CommentWithProfileDTO> consultCommentsPaginated(@PathVariable Integer idGroup, @PathVariable Integer page, @PathVariable Integer pageSize){
		return service.getComments(idGroup, page, pageSize);
	}

}
