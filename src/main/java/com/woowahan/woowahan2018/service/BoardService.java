package com.woowahan.woowahan2018.service;

import com.woowahan.woowahan2018.domain.Board;
import com.woowahan.woowahan2018.domain.BoardRepository;
import com.woowahan.woowahan2018.domain.Deck;
import com.woowahan.woowahan2018.domain.DeckRepository;
import com.woowahan.woowahan2018.dto.BoardDto;
import com.woowahan.woowahan2018.dto.DeckDto;
import com.woowahan.woowahan2018.exception.BoardNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private static final Logger log = LoggerFactory.getLogger(BoardService.class);

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private DeckRepository deckRepository;

    public Board createBoard(BoardDto boardDto) {
        return boardRepository.save(boardDto.toBoard());
    }

    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    public List<Deck> findAllDecks(long boardId) throws BoardNotFoundException {
        Board board = findOneBoard(boardId);
        return board.getDecks();
    }

    @Transactional
    public Deck createDeck(long boardId, DeckDto deckDto) throws BoardNotFoundException {
        Deck deck = deckRepository.save(deckDto.toDeck());

        Board board = findOneBoard(boardId);
        board.addDeck(deck);
        return deck;
    }

    @Transactional
    public void deleteDeck(long boardId, long deckId) throws BoardNotFoundException {
        Board board = findOneBoard(boardId);

        board.deleteDeck(deckId);
        deckRepository.delete(deckId);
    }

    public Board findOneBoard(long boardId) throws BoardNotFoundException {
        return Optional.ofNullable(boardRepository.findOne(boardId))
                .orElseThrow(BoardNotFoundException::new);
    }
}