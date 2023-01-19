package com.local.tacocloud.database;

import com.local.tacocloud.database.repository.IngredientRepository;
import com.local.tacocloud.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   @Override
   public List<Ingredient> findAll() {
      return jdbcTemplate.query("SELECT id, name, type FROM ingredient", this::mapRowToIngredient);
   }

   @Override
   public Optional<Ingredient> findById(String id) {
      List<Ingredient> results = jdbcTemplate.query("SELECT id, name, type FROM ingredient WHERE id=?",
         this::mapRowToIngredient,
         id);

      return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
   }

   @Override
   public Ingredient save(Ingredient ingredient) {
      jdbcTemplate.update("INSERT INTO ingredient (id, name, type) VALUES (?,?,?)",
         ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
      return ingredient;
   }

   public Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
      return new Ingredient(
         rs.getString("id"),
         rs.getString("name"),
         Ingredient.Type.valueOf(rs.getString("type")));
   }
}
