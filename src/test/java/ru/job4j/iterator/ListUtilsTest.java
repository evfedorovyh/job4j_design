package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfDone() {
        ListUtils.removeIf(input, a -> a < 2);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenRemoveIfNotDone() {
        ListUtils.removeIf(input, a -> a > 5);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIfDone() {
        ListUtils.replaceIf(input, a -> a < 2, 5);
        assertThat(input).hasSize(2).containsSequence(5, 3);
    }

    @Test
    void whenReplaceIfNotDone() {
        ListUtils.replaceIf(input, a -> a > 5, 5);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveAllDoneAndResultIs0() {
        ListUtils.removeAll(input, List.of(1, 3, 4, 5));
        assertThat(input).hasSize(0);
    }

    @Test
    void whenRemoveAllDoneAndResultIs1() {
        ListUtils.removeAll(input, List.of(1, 4, 5));
        assertThat(input).hasSize(1);
    }
}