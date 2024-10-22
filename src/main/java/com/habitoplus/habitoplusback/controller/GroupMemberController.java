package com.habitoplus.habitoplusback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.model.Request;
import com.habitoplus.habitoplusback.service.GroupMemberService;
import com.habitoplus.habitoplusback.dto.GroupMemberDTO;

import io.swagger.v3.oas.annotations.Operation;
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

	@Operation(summary = "Add user to group, only group admin can do this by entering their id")
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
    @PostMapping("/administrators/{idAdmin}")
	public ResponseEntity<?> addUserToGroup(@RequestBody GroupMemberDTO groupMemberDTO, @PathVariable Integer idAdmin) {
		service.save(groupMemberDTO, idAdmin);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

	@Operation(summary = "Leave group", description="")
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
	public ResponseEntity<?> leaveGroup(@PathVariable Integer idGroup, @PathVariable Integer idProfile) {
		service.delete(idGroup, idProfile);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}

	@Operation(summary = "Delete user from group ")
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
	@DeleteMapping("{idGroup}/{idProfile}/administrators/{idAdmin}")
	public ResponseEntity<?> deleteUserFromGroup(@PathVariable Integer idGroup, @PathVariable Integer idProfile, @PathVariable Integer idAdmin) {
		service.delete(idGroup, idProfile, idAdmin);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}

}
