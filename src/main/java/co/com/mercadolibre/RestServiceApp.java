package co.com.mercadolibre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadolibre.dao.MutantsDao;
import co.com.mercadolibre.dao.MutantsRepository;
import co.com.mercadolibre.dto.RequestMutantDto;
import co.com.mercadolibre.dto.ResponseStats;


@RestController
@RequestMapping("/")
public class RestServiceApp {

    @Autowired
    MutantsRepository repository;

    @PostMapping("/mutant")
    public Response mutant(@RequestBody RequestMutantDto request) {

        if(isMutant(request.getDna())) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            return Response.status(Response.Status.OK).build();
        }

    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = {"application/json"})
    public Response stats() {
        try {
            List<MutantsDao> list = repository.findAll();
            int i = 0;
            int j = 0;
            for(MutantsDao dao : list) {
                if(dao.getIsMutant()) {
                    i++;
                } else {
                    j++;
                }
            }

            Double d = (double) i / j;
            return Response.status(Response.Status.OK).entity(new Gson().toJson(new ResponseStats(String.valueOf(i), String.valueOf(j), d.toString()))).build();

        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error no controlado, " + e).build();
        }
    }

    private boolean isMutant(String[] dna) {

        String dnaJunto = "";
        for(String data : dna) {
            dnaJunto += data;
        }

        MutantsDao dao = validateExist(dnaJunto);
        if(dao != null) {
            return dao.getIsMutant();
        }

        if(validateMutant(dna)) {
            repository.saveAndFlush(new MutantsDao(dnaJunto, true));
            return true;
        }
        final Map<Integer, List<String>> temp = new HashMap();
        for(int i = 0; i < dna.length; i++) {
            for(int j = 0; j < dna.length; j++) {
                if(temp.get(j) == null) {
                    temp.put(j, new ArrayList<>(Arrays.asList(dna[i].substring(j, j + 1))));
                } else {
                    temp.get(j).add(dna[i].substring(j, j + 1));
                }
            }
        }
        String[] data = new String[temp.size()];
        for(Map.Entry<Integer, List<String>> map : temp.entrySet()) {
            data[map.getKey()] = map.getValue().stream().collect(Collectors.joining());
        }
        if(validateMutant(data)) {
            repository.saveAndFlush(new MutantsDao(dnaJunto, true));
            return true;
        }

        repository.saveAndFlush(new MutantsDao(dnaJunto, false));
        return false;

    }

    private MutantsDao validateExist(String dna) {
        try {
            List<MutantsDao> list = repository.findAll();
            for(MutantsDao dao : list) {
                if(dao.getDna().toUpperCase().equals(dna.toUpperCase())) {
                    return dao;
                }
            }

        } catch(Exception e) {
            System.out.println("Ha ocurrido un error: " + e);
        }

        return null;
    }

    private boolean validateMutant(String[] dna) {
        for(int i = 0; i < dna.length; i++) {
            for(int j = 0; j < dna.length; j++) {
                if(j <= dna[i].length() - 4 && validate(dna[i].substring(j, j + 4))) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean validate(String cadena) {
        String temp = "";
        int j = 0;
        for(int i = 0; i <= cadena.length(); i++) {
            if(i == 0) {
                temp = cadena.substring(i, i + 1);
            } else {
                if(j == 3) {
                    return true;
                }else if(temp.equalsIgnoreCase(cadena.substring(i, i + 1))) {
                    temp = cadena.substring(i, i + 1);
                    j++;
                } else {
                    return false;
                }
            }
        }
        
        return false;
    }
    
}