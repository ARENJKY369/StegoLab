package com.steglab;

import java.time.Instant;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SystemController {
  @GetMapping("/health")
  public Map<String,Object> health() { return Map.of("status","ok","service","steglab-api","timestamp",Instant.now().toString()); }
  @GetMapping("/capabilities")
  public Map<String,Object> capabilities() { return Map.of("version","0.1.0","max_upload_bytes",50*1024*1024,"ephemeral",true,"modules",new String[]{"image","audio","text","documents","network","analysis","utilities"}); }
}
