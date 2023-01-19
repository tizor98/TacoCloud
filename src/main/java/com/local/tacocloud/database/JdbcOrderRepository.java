package com.local.tacocloud.database;

import com.local.tacocloud.database.repository.OrderRepository;
import com.local.tacocloud.domain.Ingredient;
import com.local.tacocloud.domain.Taco;
import com.local.tacocloud.domain.TacoOrder;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcOrderRepository implements OrderRepository {

   @Autowired
   private JdbcOperations jdbcOperations;

   @Override
   @Transactional
   public TacoOrder save(TacoOrder tacoOrder) {

      PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
         "INSERT INTO Taco_Order "
         + "(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, createdAt) "
         + "VALUES (?,?,?,?,?,?,?,?,?)",
         Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
         Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
      );
      pscf.setReturnGeneratedKeys(true);

      tacoOrder.setCreatedAt(new Date());
      PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
         Arrays.asList(
            tacoOrder.getDeliveryName(),
            tacoOrder.getDeliveryStreet(),
            tacoOrder.getDeliveryCity(),
            tacoOrder.getDeliveryState(),
            tacoOrder.getDeliveryZip(),
            tacoOrder.getCcNumber(),
            tacoOrder.getCcExpiration(),
            tacoOrder.getCcCVV(),
            tacoOrder.getCreatedAt()
         )
      );
      GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

      jdbcOperations.update(psc, keyHolder);

      long tacoOrderId = Objects.requireNonNull(keyHolder.getKey()).longValue();
      tacoOrder.setId(tacoOrderId);

      List<Taco> tacos = tacoOrder.getTacos();
      int i = 0;

      for(Taco taco : tacos) {
         saveTaco(tacoOrderId, i++, taco);
      }

      return tacoOrder;
   }

   private long saveTaco(Long id, int orderKey, Taco taco) {
      taco.setCreatedAt(new Date());
      PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
         "INSERT INTO Taco "
         + "(name, createdAt, taco_order, taco_order_key) "
         + "VALUES (?,?,?,?)",
         Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
      );
      pscf.setReturnGeneratedKeys(true);

      PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
         Arrays.asList(
            taco.getName(),
            taco.getCreatedAt(),
            id,
            orderKey
         )
      );
      GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

      jdbcOperations.update(psc, keyHolder);
      long tacoId = Objects.requireNonNull(keyHolder.getKey()).longValue();
      taco.setId(tacoId);

      saveIngredientRefs(tacoId, taco.getIngredients());

      return tacoId;

   }

   private void saveIngredientRefs(long id, List<Ingredient> ingredients) {
      int key = 0;

      for(Ingredient ingredient : ingredients) {
         jdbcOperations.update(
            "INSERT INTO Ingredient_Ref (ingredient, taco, taco_key) "
            + "VALUES (?,?,?)",
            ingredient.getId(), id, key++
         );
      }
   }

}
