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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Model.Request;
import com.habitoplus.habitoplusback.Service.GroupMemberService;
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

	 // @Operation(summary = "Get all members of groups")
    // @ApiResponses(value = {
    //                     @ApiResponse(responseCode = "200", description = "Found members", content = {
    //                                     @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GroupMember.class)))
    //                     }),
    //                     @ApiResponse(responseCode = "204", description = "No members found"),
    //                     @ApiResponse(responseCode = "400", description = "Bad request", content = {
    //                                     @Content
    //                     }),
    //                     @ApiResponse(responseCode = "500", description = "Internal server error", content = {
    //                                     @Content
    //                     })
    //     })
    // @GetMapping
    // public List<GroupMember> getAll(){
    //     return service.getAll();
    // }

    // @Operation(summary = "Get a group member by its ID")
	// @ApiResponses(value = {
	// 		@ApiResponse(responseCode = "200", description = "Group member found", content = {
	// 				@Content(mediaType = "application/json", schema = @Schema(implementation = GroupMember.class)) }),
	// 		@ApiResponse(responseCode = "400", description = "Invalid identifier supplied", content = @Content),
	// 		@ApiResponse(responseCode = "404", description = "Group not found", content = @Content),
	// 		@ApiResponse(responseCode = "500", description = "Internal error on the server", content = @Content)})
	// @GetMapping("{idGroup}/{idProfile}")
	// public ResponseEntity<?> getByIdGroupMember(@PathVariable Integer idGroup, @PathVariable Integer idProfile) {
	// 	GroupMember groupMember = service.getById(idGroup, idProfile);
	// 	return new ResponseEntity<GroupMember>(groupMember, HttpStatus.OK);
	// }

	// @Operation(summary = "Update a group member")
	// @ApiResponses(value = {
	// 		@ApiResponse(responseCode = "200", description = "Group member successfully updated", content = {
	// 				@Content(mediaType = "application/json", schema = @Schema(implementation = Request.class))
	// 		}),
	// 		@ApiResponse(responseCode = "400", description = "Invalid request data or group ID and profile ID", content = {
	// 				@Content
	// 		}),
	// 		@ApiResponse(responseCode = "404", description = "Group member not found", content = {
	// 				@Content
	// 		}),
	// 		@ApiResponse(responseCode = "500", description = "Internal server error", content = {
	// 				@Content
	// 		})
	// })
    // @PutMapping("{idGroup}/{idProfile}/groups/administrators/{idAdmin}")
	// public ResponseEntity<?> update(@RequestBody GroupMember groupMember, @PathVariable Integer idGroup, @PathVariable Integer idProfile, @PathVariable Integer idAdmin) {
	// 	GroupMember auxGroupMember = service.getById(idGroup, idProfile);
	// 	groupMember.getGroup().setIdGroup(auxGroupMember.getGroup().getIdGroup());
	// 	groupMember.getProfile().setIdProfile(auxGroupMember.getProfile().getIdProfile());
	// 	service.save(groupMember, idAdmin);
	// 	return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	// }

}