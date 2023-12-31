package com.gabriel_torelo.game_list.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gabriel_torelo.game_list.dto.GameLongDTO;
import com.gabriel_torelo.game_list.dto.GameMinDTO;
import com.gabriel_torelo.game_list.dto.GameShortDTO;
import com.gabriel_torelo.game_list.entities.Game;
import com.gabriel_torelo.game_list.projections.GameMinProjection;
import com.gabriel_torelo.game_list.repositories.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> readAll() {
        List<Game> rGames = gameRepository.findAll();

        return rGames.stream().map(gameEntity -> new GameMinDTO(gameEntity)).toList();
    }

    @Transactional(readOnly = true)
    public GameShortDTO readID(Long id) {
        Game rGame = gameRepository.findById(id).get();

        return new GameShortDTO(rGame);
    }

    @Transactional(readOnly = true)
    public GameLongDTO readMoreID(Long id) {
        Game rGame = gameRepository.findById(id).get();

        return new GameLongDTO(rGame);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> readListID(Long id) {
        List<GameMinProjection> rGameProj = gameRepository.readListID(id);

        return rGameProj.stream().map(gameProject -> new GameMinDTO(gameProject)).toList();
    }
}
