package org.pract.characters;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.pract.Army;
import org.pract.Battle;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FightTest {
    @Test
    @DisplayName("1. Fight")
    void test01(){
        // Arrange  | Given
        var carl = new Warrior();
        var jim = new Knight();
        // Act      | When
        var res = Battle.fight(carl, jim);
        // Assert   | Then
        assertEquals(false, res);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Duel for two warriors")
    void testTwoWarriorsDuel(Warrior warrior1, Warrior warrior2, boolean expected){
        // Arrange  | Given
        // Act      | When
        var res = Battle.fight(warrior1, warrior2);
        // Assert   | Then
        assertEquals(expected, res);
    }

    static Stream<Arguments> testTwoWarriorsDuel()
    {
        return Stream.of(
                Arguments.of(new Knight(), new Warrior(), true),
                Arguments.of(new Warrior(), new Warrior(), true),
                Arguments.of(new Warrior(), new Knight(), false),
                Arguments.of(new Knight(), new Knight(), true),
                Arguments.of(new Defender(), new Warrior(), true)
        );
    }

    @Test
    @DisplayName("7. Fight")
    void test07(){
        // Arrange  | Given
        var unit_1  = new Warrior();
        var unit_2  = new Knight();
        var unit_3  = new Warrior();
        Battle.fight(unit_1, unit_2);
        // Act      | When
        var res = Battle.fight(unit_2, unit_3);
        // Assert   | Then
        assertEquals(false, res);
    }

/////////////////////////////////////////////////

    @ParameterizedTest
    @MethodSource
    @DisplayName("Battle for two armies")
    void testTwoArmiesBattle(Army army1, Army army2, boolean expected){
        // Arrange  | Given
        // Act      | When
        var res = Battle.fight(army1, army2);
        // Assert   | Then
        assertEquals(expected, res);
    }

    static Stream<Arguments> testTwoArmiesBattle()
    {
        return Stream.of(
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Knight::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Knight::new, 1),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 3),
                        new Army()
                                .addUnits(Knight::new, 3),
                        false
                )
        );
    }

    @Test
    @DisplayName("1. Battle")
    void test08(){
        // Arrange  | Given
        var army_1  = new Army();
        var army_2  = new Army();

        army_1.addUnits(() -> new Warrior(), 1);
        army_2.addUnits(() -> new Warrior(), 2);
        // Act      | When
        var res = Battle.fight(army_1, army_2);
        // Assert   | Then
        assertEquals(false, res);
    }

    @Test
    @DisplayName("2. Battle")
    void test09(){
        // Arrange  | Given
        var army_1  = new Army();
        var army_2  = new Army();

        army_1.addUnits(() -> new Warrior(), 2);
        army_2.addUnits(() -> new Warrior(), 3);
        // Act      | When
        var res = Battle.fight(army_1, army_2);
        // Assert   | Then
        assertEquals(false, res);
    }

    @Test
    @DisplayName("3. Battle")
    void test10(){
        // Arrange  | Given
        var army_1  = new Army();
        var army_2  = new Army();

        army_1.addUnits(() -> new Warrior(), 5);
        army_2.addUnits(() -> new Warrior(), 7);
        // Act      | When
        var res = Battle.fight(army_1, army_2);
        // Assert   | Then
        assertEquals(false, res);
    }

    @Test
    @DisplayName("4. Battle")
    void test11(){
        // Arrange  | Given
        var army_1  = new Army();
        var army_2  = new Army();

        army_1.addUnits(() -> new Warrior(), 20);
        army_2.addUnits(() -> new Warrior(), 21);
        // Act      | When
        var res = Battle.fight(army_1, army_2);
        // Assert   | Then
        assertEquals(true, res);
    }

    @Test
    @DisplayName("5. Battle")
    void test12(){
        // Arrange  | Given
        var army_1  = new Army();
        var army_2  = new Army();

        army_1.addUnits(() -> new Warrior(), 10);
        army_2.addUnits(() -> new Warrior(), 11);
        // Act      | When
        var res = Battle.fight(army_1, army_2);
        // Assert   | Then
        assertEquals(true, res);
    }

    @Test
    @DisplayName("6. Battle")
    void test13(){
        // Arrange  | Given
        var army_1  = new Army();
        var army_2  = new Army();

        army_1.addUnits(() -> new Warrior(), 11);
        army_2.addUnits(() -> new Warrior(), 7);
        // Act      | When
        var res = Battle.fight(army_1, army_2);
        // Assert   | Then
        assertEquals(true, res);
    }

    @ParameterizedTest
    @ValueSource(classes = {Warrior.class, Knight.class})
    void name(Class<?> cls)
    {
        assertTrue(Warrior.class.isAssignableFrom(cls));
    }

    @Test
    @DisplayName("1. Вампір проти Дефендера")
    void test14(){
        // Arrange  | Given
        var vamp  = new Vampire();
        var defen  = new Defender();
        // Act      | When
        var res = Battle.fight(defen, vamp);
        // Assert   | Then
        assertEquals(true, res);
    }

    @Test
    @DisplayName("1. Лансер проти варіора")
    void test15(){
        // Arrange  | Given
        var lanc  = new Lancer();
        var war  = new Warrior();
        // Act      | When
        var res = Battle.fight(lanc, war);
        // Assert   | Then
        assertEquals(true, res);
    }

    @Test
    @DisplayName("2. Лансер против армии")
    void test16(){
        // Arrange  | Given
        var army_1  = new Army();
        var army_2  = new Army();

        army_1.addUnits(new Lancer());
        army_2.addUnits(new Knight());
        army_2.addUnits(new Warrior());
        // Act      | When
        var res = Battle.fight(army_1, army_2);
        // Assert   | Then
        assertEquals(false, res);
    }

    @Test
    @DisplayName("Тестировка лансера")
    void smokeTest5() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();
        var freelancer = new Lancer();
        var vampire = new Vampire();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());
        assertFalse(Battle.fight(bob, mike));
        assertTrue(Battle.fight(lancelot, rog));
        assertFalse(Battle.fight(eric, richard));
        assertTrue(Battle.fight(ogre, adam));
        assertTrue(Battle.fight(freelancer, vampire));
        assertTrue(freelancer.isAlive());

        var myArmy = new Army()
                .addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 2)
                .addUnits(Lancer::new, 4)
                .addUnits(Warrior::new, 1);

        var enemyArmy = new Army()
                .addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 2)
                .addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 3);

        var army3 = new Army()
                .addUnits(Warrior::new, 1)
                .addUnits(Lancer::new, 1)
                .addUnits(Defender::new, 2);

        var army4 = new Army()
                .addUnits(Vampire::new, 3)
                .addUnits(Warrior::new, 1)
                .addUnits(Lancer::new, 2);

        assertTrue(Battle.fight(myArmy, enemyArmy));
        assertFalse(Battle.fight(army3, army4));
    }

    @Test
    @DisplayName("Тестировка хилера")
    void testHealer() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();
        var freelancer = new Lancer();
        var vampire = new Vampire();
        var priest = new Healer();

        assert Battle.fight(chuck, bruce) == true;
        assert Battle.fight(dave, carl) == false;
        assert chuck.isAlive() == true;
        assert bruce.isAlive() == false;
        assert carl.isAlive() == true;
        assert dave.isAlive() == false;
        assert Battle.fight(carl, mark) == false;
        assert carl.isAlive() == false;
        assert Battle.fight(bob, mike) == false;
        assert Battle.fight(lancelot, rog) == true;
        assert Battle.fight(eric, richard) == false;
        assert Battle.fight(ogre, adam) == true;
        assert Battle.fight(freelancer, vampire) == true;
        assert freelancer.isAlive() == true;
        assert freelancer.getHealth() == 14;
        priest.heal(freelancer);
        assert freelancer.getHealth() == 16;

        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Warrior::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Lancer::new, 4);
        enemyArmy.addUnits(Healer::new, 1);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);
        enemyArmy.addUnits(Healer::new, 1);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Lancer::new, 1);
        army3.addUnits(Healer::new, 1);
        army3.addUnits(Defender::new, 2);

        var army4 = new Army();
        army4.addUnits(Vampire::new, 3);
        army4.addUnits(Warrior::new, 1);
        army4.addUnits(Healer::new, 1);
        army4.addUnits(Lancer::new, 2);

        var army5 = new Army();
        army5.addUnits(Warrior::new, 10);

        var army6 = new Army();
        army6.addUnits(Warrior::new, 6);
        army6.addUnits(Lancer::new, 5);

        assert Battle.fight(myArmy, enemyArmy) == false;
        assert Battle.fight(army4, army3) == true;
        assert Battle.fight(army5, army6) == false;
    }
}