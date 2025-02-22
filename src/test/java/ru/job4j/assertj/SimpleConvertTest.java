package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).isNotEmpty()
                .hasSize(5)
                .contains("second")
                .containsOnly("first", "second", "three", "four", "five")
                .containsExactly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("three", "first", "five", "second",  "four")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first")
                .endsWith("five")
                .containsSequence("second", "three")
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(3);
                })
                .anySatisfy(e -> {
                    assertThat(e.length()).isLessThan(5);
                    assertThat(e.length()).isEqualTo(4);
                })
                .allMatch(e -> e.length() < 10)
                .anyMatch(e -> e.length() == 5)
                .noneMatch(e -> e.length() < 2);
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> array = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(array).isNotEmpty()
                .hasSize(5)
                .contains("second")
                .containsOnly("first", "second", "three", "four", "five")
                .containsExactly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("three", "first", "five", "second",  "four")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first")
                .endsWith("five")
                .containsSequence("second", "three")
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(3);
                })
                .anySatisfy(e -> {
                    assertThat(e.length()).isLessThan(5);
                    assertThat(e.length()).isEqualTo(4);
                })
                .allMatch(e -> e.length() < 10)
                .anyMatch(e -> e.length() == 5)
                .noneMatch(e -> e.length() < 2);

        assertThat(array).first().isEqualTo("first");
        assertThat(array).element(1).isEqualTo("second");
        assertThat(array).last().isEqualTo("five");

        assertThat(array).filteredOn(e -> e.length() > 5).first().isEqualTo("second");
        assertThat(array).filteredOnAssertions(e -> assertThat(e).contains("f"))
                .hasSize(3)
                .first().isEqualTo("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> array = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(array).isNotEmpty()
                .hasSize(5)
                .contains("second")
                .containsOnly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("three", "first", "five", "second",  "four")
                .contains("first")
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("six")
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(3);
                })
                .anySatisfy(e -> {
                    assertThat(e.length()).isLessThan(5);
                    assertThat(e.length()).isEqualTo(4);
                })
                .allMatch(e -> e.length() < 10)
                .anyMatch(e -> e.length() == 5)
                .noneMatch(e -> e.length() < 2);

        assertThat(array).filteredOn(e -> e.length() > 5).hasSize(1);
        assertThat(array).filteredOnAssertions(e -> assertThat(e).contains("f"))
                .hasSize(3);
    }

    @Test
    void assertMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "three", "five")
                .containsValues(3, 1, 2, 0)
                .doesNotContainKey("six")
                .doesNotContainValue(5)
                .containsEntry("three", 2);
    }
}