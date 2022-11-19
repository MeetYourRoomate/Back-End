package com.meetyourroommate.app.profile.interfaces.rest;

import com.meetyourroommate.app.profile.application.communication.responses.AtributeDtoListResponse;
import com.meetyourroommate.app.profile.application.services.AtributeService;
import com.meetyourroommate.app.profile.application.transform.AtributeDtoMapper;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class AtributeController {

    private final AtributeService atributeService;
    private final AtributeDtoMapper atributeDtoMapper;

    public AtributeController(AtributeService atributeService, AtributeDtoMapper atributeDtoMapper) {
        this.atributeService = atributeService;
        this.atributeDtoMapper = atributeDtoMapper;
    }

    @GetMapping("/atributes")
    public ResponseEntity<AtributeDtoListResponse> getAllAtributes(){
        try{
            List<Atribute> atributes = atributeService.findAll();
            return new ResponseEntity<>(
                    new AtributeDtoListResponse(
                            atributeDtoMapper.toDtoList(atributes)
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new AtributeDtoListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
