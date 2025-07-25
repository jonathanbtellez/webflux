package com.mycode.ecommerce.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseDto <T>{
   private String status;
   private String message;
   private T data;
}
