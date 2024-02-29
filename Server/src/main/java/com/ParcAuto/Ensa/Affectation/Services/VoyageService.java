package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.VoyageDTO;
import com.ParcAuto.Ensa.Affectation.Repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

   }

