package com.steglab;

import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/text")
public class TextController {
  private static final String[] ZW={"\u200b","\u200c","\u200d","\ufeff"};
  @PostMapping("/encode/zero-width") public Map<String,String> encode(@RequestBody Pair p){StringBuilder out=new StringBuilder(p.text());for(byte b:p.message().getBytes(java.nio.charset.StandardCharsets.UTF_8)){for(int shift=6;shift>=0;shift-=2)out.append(ZW[(b>>shift)&3]);}return Map.of("text",out.toString(),"method","zero-width pairs");}
  @PostMapping("/decode/zero-width") public Map<String,String> decode(@RequestBody Text p){StringBuilder bits=new StringBuilder();for(char c:p.text().toCharArray())for(int i=0;i<4;i++)if(ZW[i].charAt(0)==c)bits.append(String.format("%2s",Integer.toBinaryString(i)).replace(' ','0'));byte[] raw=new byte[bits.length()/8];for(int i=0;i<raw.length;i++)raw[i]=(byte)Integer.parseInt(bits.substring(i*8,i*8+8),2);return Map.of("message",new String(raw,java.nio.charset.StandardCharsets.UTF_8));}
  @PostMapping("/scan") public Map<String,Object> scan(@RequestBody Text p){long count=p.text().chars().filter(c->c==0x200b||c==0x200c||c==0x200d||c==0xfeff).count();return Map.of("count",count,"has_html_comments",p.text().contains("<!--"),"invisible_characters",count>0?new String[]{"zero-width Unicode"}:new String[]{});}
  public record Text(String text){} public record Pair(String text,String message){}
}
