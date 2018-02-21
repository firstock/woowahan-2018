package com.woowahan.woowahan2018.dto;

import com.woowahan.woowahan2018.domain.Deck;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class DeckDto {

    @NotNull(message = "Deck이름을 입력해주세요.")
    @NotBlank(message = "Deck이름을 입력해주세요.")
    private String name;

    public DeckDto() {

    }

    public String getName() {
        return name;
    }

    public DeckDto setName(String name) {
        this.name = name;
        return this;
    }

    public Deck toDeck() {
        return new Deck(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckDto deckDto = (DeckDto) o;
        return Objects.equals(name, deckDto.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String
    toString() {
        return "DeckDto{" +
                "name='" + name + '\'' +
                '}';
    }
}