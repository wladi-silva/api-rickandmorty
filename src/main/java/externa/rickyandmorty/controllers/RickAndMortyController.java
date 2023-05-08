package externa.rickyandmorty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import externa.rickyandmorty.client.RickAndMortyClient;
import externa.rickyandmorty.response.CharacterResponse;

@Controller
@RequestMapping("/rickandmorty")
public class RickAndMortyController {
    
    @Autowired
    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/personagem/{id}")
    public ModelAndView getCharacterById(@PathVariable String id) {
        CharacterResponse personagem = rickAndMortyClient.findAndCharacterById(id).block();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("personagem", personagem);
        return mv;
    }

}