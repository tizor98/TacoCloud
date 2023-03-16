package com.local.tacocloud.web.api;

import com.local.tacocloud.database.repository.TacoRepository;
import com.local.tacocloud.database.entity.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "https://127.0.0.1:8443") // For development purpose
public class TacoApiController {

   @Autowired
   private TacoRepository tacoRepo;

   @GetMapping(params = "recent")
   public ResponseEntity<Iterable<Taco>> recentTacos() {
      PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());
      Page<Taco> list = tacoRepo.findAll(page);
      if(list.hasContent()) {
         return new ResponseEntity<>(list.getContent(), HttpStatus.OK);
      } else {
         return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }
   }

   @GetMapping("/{id}")
   public ResponseEntity<Taco> tacoById(@PathVariable("id") String id) {
      Optional<Taco> optTaco = tacoRepo.findById(id);
      return optTaco.map( taco -> new ResponseEntity<>(taco, HttpStatus.OK)).orElseGet( () -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
   }

   @PostMapping(consumes = "application/json")
   @ResponseStatus(HttpStatus.CREATED)
   public Taco postTaco(@RequestBody Taco taco) {
      return tacoRepo.save(taco);
   }

}
