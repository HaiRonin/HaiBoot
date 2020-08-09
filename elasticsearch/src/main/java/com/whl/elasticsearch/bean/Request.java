package com.whl.elasticsearch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
   private Integer pageNo;

   private Integer pageSize;

   private String key;
}
