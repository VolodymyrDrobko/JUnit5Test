import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When running MathUtils")
class MathUtilsTest {
    MathUtils mathUtils;

    @BeforeAll
    void setUpAll() {
        System.out.println("Set up before any test run");
        mathUtils = new MathUtils();
    }

    @BeforeEach
    void setUp(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry("Running " + testInfo.getTestMethod().get() + " with verification of " + testInfo.getDisplayName());
        System.out.println("Running " + testInfo.getTestMethod().get().getName() + " with verification of " + testInfo.getDisplayName());
    }

    @AfterEach
    void cleanUp() {
        System.out.println("Cleaning up...");
    }

    @AfterAll
    void cleanUpAll() {
        System.out.println("Cleaning after all");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void parameterizedTest(int number) {
        assertTrue(mathUtils.isOddNumbers(number));
    }

    @ParameterizedTest
    @NullSource
    void parameterizedNullTest(String string) {
        assertTrue(mathUtils.isBlank(string));
    }

    @ParameterizedTest
    @EmptySource
    void parameterizedEmptyTest(String string) {
        assertTrue(mathUtils.isBlank(string));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void parameterizedCSVTest(String input, String expected) {
        String actual = input.toUpperCase();
        assertEquals(actual, expected);
    }

    @Test
    @Order(1)
    @DisplayName("Order 1")
    void firstTest() {
    }

    @Tag("JUnitFunctionalityExamples")
    @DisplayName("Repeated tests")
    @RepeatedTest(3)
    void repeatedTest(RepetitionInfo repetitionInfo) {
        System.out.println(repetitionInfo.getCurrentRepetition());
        System.out.println(repetitionInfo.getTotalRepetitions());
        assertTrue(true);
    }

    @Nested
    @Tag("Calculation")
    @DisplayName("add method")
    class AddingTests {

        @Test
        @DisplayName("when adding two positive numbers")
        void testAddPositive() {
            int expected = 2;
            int actual = mathUtils.add(1, 1);
            assertEquals(expected, actual, "should return sum - " + actual);
        }

        @Test
        @DisplayName("when adding two negative numbers")
        void testAddNegative() {
            int expected = -2;
            int actual = mathUtils.add(-1, -3);
            assertEquals(expected, actual, () -> "should return sum - " + expected);
        }
    }

    @Test
    @Tag("Calculation")
    @DisplayName("Testing divide method")
    void testDivide() {
        System.out.println("Verify that divide method works correct, expected - " + ArithmeticException.class);
        int actual = mathUtils.add(1, 1);
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "Verification of divide method divide by 0 behaviour, found - " + actual);
    }

    @Test
    @Tag("JUnitFunctionalityExamples")
    @Disabled
    @DisplayName("Test 100% failed")
    void testFailed() {
        fail("Failed");
    }

    @Test
    @Tag("JUnitFunctionalityExamples")
    @DisplayName("Assume example")
    void assumeTest() {
        boolean precondition = false;
        Assumptions.assumeTrue(precondition);
        assertEquals(true, true, "Preconditions failed, test will be skipped");
    }

    @Test
    @Tag("JUnitFunctionalityExamples")
    @DisplayName("Assert all example")
    void multiplyTest() {
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2, 2)),
                () -> assertEquals(0, mathUtils.multiply(2, 0)),
                () -> assertEquals(-2, mathUtils.multiply(2, -1))
        );
    }

    @Test
    @Tag("Calculation")
    void testComputeCircleRadius() {
        double expected = 314.1592653589793;
        System.out.println("Verify that computeCircleRadius method works correct, expected - " + expected);
        double actual = mathUtils.computerCircleArea(10);
        assertEquals(expected, actual, "Verification of computeCircleRadius method, found - " + actual);
    }
}
