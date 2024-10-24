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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.model.Category;
import com.habitoplus.habitoplusback.model.Habit;
import com.habitoplus.habitoplusback.service.HabitService;
import com.habitoplus.habitoplusback.dto.HabitDTO;
import com.habitoplus.habitoplusback.enums.Priority;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Habit", description = "")
@RequestMapping("habits")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE })
public class HabitController {

        @Autowired
        private HabitService habitService;

        @Operation(summary = "Get all habits")
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
                return habitService.getAll();
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
        @GetMapping("{idHabit}")
        public ResponseEntity<?> getByHabitId(@PathVariable Integer idHabit) {
                Habit habit = habitService.getById(idHabit);
                return new ResponseEntity<Habit>(habit, HttpStatus.OK);
        }

        @PostMapping("/register")
        @Operation(summary = "Register a new habit")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Habit successfully registered", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = HabitDTO.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid habit data", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        public ResponseEntity<?> registerHabit(@Valid @RequestBody HabitDTO habitDTO) {
                habitService.save(habitDTO);
                return new ResponseEntity<String>("Saved Successfully", HttpStatus.OK);
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
        @PutMapping("{idHabit}")
        public ResponseEntity<?> updateHabit(@RequestBody HabitDTO habitDTO, @PathVariable Integer idHabit) {
                habitService.updateHabit(idHabit, habitDTO);
                return new ResponseEntity<>("Habit updated successfully", HttpStatus.OK);
        }

        @Operation(summary = "Update habit status")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Habit status successfully updated", content = {
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
        @PutMapping("{idHabit}/status")
        public ResponseEntity<?> update(@PathVariable Integer idHabit, @RequestBody Boolean status) {
                habitService.update(idHabit, status);
                return new ResponseEntity<String>("Updated status", HttpStatus.OK);
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
        @DeleteMapping("{idHabit}")
        public ResponseEntity<?> deleteHabit(@PathVariable Integer idHabit) {
                habitService.deleteHabit(idHabit);
                return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
        }

        @PutMapping("/updateStatus/{idHabit}")
        @Operation(summary = "Update habit status and streak")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Habit status and streak updated", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                        }),
                        @ApiResponse(responseCode = "404", description = "Habit not found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        public ResponseEntity<String> updateHabitStatus(
                        @PathVariable Integer idHabit,
                        @RequestParam Integer idProfile, 
                        @RequestParam Boolean status) {
                habitService.updateHabitStatus(idHabit, idProfile, status); 
                return new ResponseEntity<>("Habit status and streak updated successfully", HttpStatus.OK);
        }

}
