package com.habitoplus.habitoplusback.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

import com.habitoplus.habitoplusback.Model.Comment;
import com.habitoplus.habitoplusback.Model.Group;
import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Model.Request;
import com.habitoplus.habitoplusback.Service.GroupService;
import com.habitoplus.habitoplusback.dto.CommentWithProfileDto;
import com.habitoplus.habitoplusback.dto.GroupMemberWithProfileDto;
import com.habitoplus.habitoplusback.dto.RequestWithProfileDto;

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
	public List<Group> getAll() {
		return service.getAll();
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
	public ResponseEntity<?> getByControlNumber(@PathVariable Integer idGroup) {
		Group group = service.getById(idGroup);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}

	@Operation(summary = "Register a new group")
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
	@PostMapping("{idProfile}")
	public ResponseEntity<?> add(@RequestBody Group group, @PathVariable Integer idProfile) {
		service.save(group, idProfile);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

	@Operation(summary = "Update a group")
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
	@PutMapping("{idGroup}")
	public ResponseEntity<?> update(@RequestBody Group group, @PathVariable Integer idGroup) {
		service.update(group, idGroup);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}

	@Operation(summary = "Delete a group by ID")
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
	@DeleteMapping("{idGroup}")
	public ResponseEntity<?> delete(@PathVariable Integer idGroup) {
		service.delete(idGroup);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}

	@Operation(summary = "Get all the group's member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found memembers", content = {
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
	@GetMapping("/{idGroup}/members")
	public List<GroupMemberWithProfileDto> getGroupMembers(@PathVariable Integer idGroup) {
		return service.getGroupMembers(idGroup);
	}

	@Operation(summary = "Get a group by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Group found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Group not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content) })
	@GetMapping("{idGroup}/requests")
	public ResponseEntity<?> getRequests(@PathVariable Integer idGroup) {
		List<RequestWithProfileDto> requests = service.getRequests(idGroup);
		return new ResponseEntity<List<RequestWithProfileDto>>(requests, HttpStatus.OK);
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
		List<CommentWithProfileDto> comments = service.getComments(idGroup);
		return new ResponseEntity<List<CommentWithProfileDto>>(comments, HttpStatus.OK);
	}

	@Operation(summary = "Get all groups with pagination")
	@GetMapping(value="pagination", params = {"page", "size"})
	public List<Group> getAllPaginated(@RequestParam(value = "page", defaultValue="0", required=false) int
	page, @RequestParam(value = "pageSize", defaultValue="10", required = false) int pageSize){
		List<Group> groups = service.getAll(page, pageSize);
		return groups;
	}

}
