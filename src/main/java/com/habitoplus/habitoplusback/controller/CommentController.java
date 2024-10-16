package com.habitoplus.habitoplusback.controller;

import java.time.LocalDateTime;
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

import com.habitoplus.habitoplusback.model.Comment;
import com.habitoplus.habitoplusback.model.Request;
import com.habitoplus.habitoplusback.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("comments")
@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Comments", description = "Provides methods for managing comments")
public class CommentController {
    @Autowired
    private CommentService service;

    @Operation(summary = "Get all comments")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found comments", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comment.class)))
			}),
			@ApiResponse(responseCode = "204", description = "No comments found"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
    @GetMapping
    public List<Comment> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get a Comment by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found comment", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid group ID, profile Id or date time", content = {
					@Content
			}),
			@ApiResponse(responseCode = "404", description = "Comment not found", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
	@GetMapping("{idGroup}/{idProfile}/{dateTime}")
	public ResponseEntity<?> getById(@PathVariable Integer idGroup, @PathVariable Integer idProfile, @PathVariable LocalDateTime dateTime) {
		Comment comment = service.getById(idGroup, idProfile, dateTime);
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}


	@Operation(summary = "Register a new comment ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Comment successfully registered", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid request data", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
    @PostMapping
	public ResponseEntity<?> add(@RequestBody Comment comment) {
		service.save(comment);
		return new ResponseEntity<String>("Saved record", HttpStatus.OK);
	}

	@Operation(summary = "Update a comment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Comment successfully updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Request.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid request data or group ID, profile ID and date time", content = {
					@Content
			}),
			@ApiResponse(responseCode = "404", description = "Comment not found", content = {
					@Content
			}),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content
			})
	})
    @PutMapping("{idGroup}/{idProfile}/{dateTime}")
	public ResponseEntity<?> update(@RequestBody Comment comment, @PathVariable Integer idGroup, @PathVariable Integer idProfile, @PathVariable LocalDateTime dateTime) {
		Comment auxComment = service.getById(idGroup, idProfile, dateTime);
		comment.setIdGroup(auxComment.getIdGroup());
		comment.setIdProfile(auxComment.getIdProfile());
		comment.setDateTime(auxComment.getDateTime());
		service.update(comment);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}

	@Operation(summary = "Delete a comment by ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Comment successfully deleted", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid group ID, profile ID or date time", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "404", description = "Comment not found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
    @DeleteMapping("{idGroup}/{idProfile}/{dateTime}")
	public ResponseEntity<?> delete(@PathVariable Integer idGroup, @PathVariable Integer idProfile, @PathVariable LocalDateTime dateTime) {
		service.delete(idGroup, idProfile, dateTime);
		return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
	}

	@Operation(summary = "Get all comments with pagination")
	@GetMapping(value="pagination", params = {"page", "size"})
	public List<Comment> getAllPaginated(@RequestParam(value = "page", defaultValue="0", required=false) int
	page, @RequestParam(value = "pageSize", defaultValue="10", required = false) int pageSize){
		List<Comment> comments = service.getAll(page, pageSize);
		return comments;
	}
}
