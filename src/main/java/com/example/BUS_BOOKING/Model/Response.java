package com.example.BUS_BOOKING.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Getter
@Setter

public class Response {
    private String status;
    private  String message;
    private  Object data;


    public Response(String status,String message){
        this.status=status;
        this.message=message;
    }

}
