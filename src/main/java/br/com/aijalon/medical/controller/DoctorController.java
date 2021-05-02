package br.com.aijalon.medical.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aijalon.medical.service.DoctorService;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {

    private DoctorService doctorService;
    
}
