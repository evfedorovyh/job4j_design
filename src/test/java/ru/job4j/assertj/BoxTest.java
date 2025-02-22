package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .isNotEqualTo("UNKNOWN");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 3);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .isNotEmpty()
                .isNotEqualTo("Unknown object");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(-1, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty();
    }

    @Test
    void whenNumberOfVerticesIsFour() {
        Box box = new Box(4, 6);
        int verticals = box.getNumberOfVertices();
        assertThat(verticals).isEqualTo(4)
                .isNotEqualTo(-1);
    }

    @Test
    void whenNumberOfVerticesIsMinusOne() {
        Box box = new Box(5, 6);
        int verticals = box.getNumberOfVertices();
        assertThat(verticals).isEqualTo(-1)
                .isNegative();
    }

    @Test
    void whenBoxIsExist() {
        Box box = new Box(8, 6);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isNotEqualTo(false);
    }

    @Test
    void whenBoxIsNotExist() {
        Box box = new Box(8, 0);
        boolean result = box.isExist();
        assertThat(result).isFalse()
                .isNotEqualTo(true);
    }

    @Test
    void whenAreaIs314() {
        Box box = new Box(0, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(314d, offset(1d))
                .isNotEqualTo(0d);
    }

    @Test
    void whenAreaIs300() {
        Box box = new Box(8, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(150d, offset(1d))
                .isNotEqualTo(0d);
    }

    @Test
    void whenAreaIsZero() {
        Box box = new Box(3, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(0d, offset(1d))
                .isEqualTo(0d);
    }
}