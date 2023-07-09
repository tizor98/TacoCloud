package com.local.tacocloud.actuator;

import com.local.tacocloud.database.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

@Component
public class TacoInfoContributor implements InfoContributor {

   private final TacoRepository tacoRepository;

   @Autowired
   public TacoInfoContributor(TacoRepository tacoRepository) {
      this.tacoRepository = tacoRepository;
   }

   @Override
   public void contribute(Info.Builder builder) {
      long tacoCount = tacoRepository.count();
      List<String> tacoList = new ArrayList<>();
      tacoRepository.findAll().forEach(taco -> tacoList.add(taco.getName()));
      Map<String, Object> tacoMap = new HashMap<>();
      tacoMap.put("count", tacoCount);
      tacoMap.put("taco-names", tacoList);
      builder.withDetail("taco-status", tacoMap);
   }
}
