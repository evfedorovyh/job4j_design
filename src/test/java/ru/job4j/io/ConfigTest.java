package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pare_without_comments.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Ivan Petrov");
        assertThat(config.value("nickname")).isEqualTo("Ivan=1=");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pare_with_comments.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Ivan Petrov");
    }

    @Test
    void whenPairWithExceptionOne() {
        String path = "./data/pare_with_exception_one.txt";
        Config config = new Config(path);
        assertThatThrownBy(config :: load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithExceptionTwo() {
        String path = "./data/pare_with_exception_two.txt";
        Config config = new Config(path);
        assertThatThrownBy(config :: load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithExceptionThree() {
        String path = "./data/pare_with_exception_three.txt";
        Config config = new Config(path);
        assertThatThrownBy(config :: load).isInstanceOf(IllegalArgumentException.class);
    }
}