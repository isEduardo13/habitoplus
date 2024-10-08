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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.Model.Category;
import com.habitoplus.habitoplusback.Model.Habit;
import com.habitoplus.habitoplusback.Service.HabitService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("habits")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE })
public class HabitController {

        @Autowired
        private HabitService service;

        @Operation(summary = "Get all the user's habits")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found habits", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class)))
                        }),
                        @ApiResponse(responseCode = "204", description = "No habits found"),
                        @ApiResponse(responseCode = "400", description = "Bad request", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        @GetMapping
        public List<Habit> getAll() {
                return service.getAll();
        }

        @Operation(summary = "Get habit by ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found habit", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid habit ID", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "404", description = "habit not found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        @GetMapping("{id_habit}")
        public ResponseEntity<?> getByHabitId(@PathVariable Integer id_habit) {
                Habit habit = service.getByHabitId(id_habit);
                return new ResponseEntity<Habit>(habit, HttpStatus.OK);
        }

        @Operation(summary = "Register a new habit")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Habit successfully registered", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Habit.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid habit data", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        @PostMapping
        public ResponseEntity<?> registerhabit(@RequestBody Habit habit) {
                service.save(habit);
                return new ResponseEntity<String>("Save record", HttpStatus.OK);
        }

        @Operation(summary = "Update a habit")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Habit successfully updated", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Habit.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid habit data or habit ID", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "404", description = "Habit not found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        @PutMapping("{habit_id}")
        public ResponseEntity<?> updateHabit(@RequestBody Habit habit, @PathVariable Integer id_habit) {
                Habit auxHabit = service.getByHabitId(id_habit);
                habit.setId_habit((auxHabit.getId_habit()));
                service.save(auxHabit);
                return new ResponseEntity<String>("Update record", HttpStatus.OK);
        }

        @Operation(summary = "Delete a habit by ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Habit successfully deleted", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid habit ID", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "404", description = "Habit not found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        @DeleteMapping("{id_habit}")
        public ResponseEntity<?> deleteHabit(@PathVariable Integer id_habit) {
                service.deleteHabit(id_habit);
                return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
        }
}
